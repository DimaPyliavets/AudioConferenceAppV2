package com.example.audioconferenceappv2;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class StartloginActivity extends AppCompatActivity {

    private EditText email, password;
    private Button login_btn;
    private TextView registration_text;

    private ProgressDialog progressDialog;

    FirebaseUser firebaseUser;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startlogin);

        checkUser();

        //Verhnia panel
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        login_btn = findViewById(R.id.login_button);
        registration_text = findViewById(R.id.clickable_text);

        firebaseAuth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("wait");
        progressDialog.setMessage("LogIn");
        progressDialog.setCanceledOnTouchOutside(false);

        //Vydilenyj tekst
        SpannableString spannableString = new SpannableString("click here");
        ClickableSpan span = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View view) {
                Intent intent = new Intent(StartloginActivity.this, RegistrationActivity.class);
                StartloginActivity.this.startActivity(intent);
            }
        };
        spannableString.setSpan(span, 0, 10, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        registration_text.setText(spannableString);
        registration_text.setMovementMethod(LinkMovementMethod.getInstance());

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateData();;
            }
        });
    }

    private void validateData() {
        String txt_email = email.getText().toString();
        String txt_password = password.getText().toString();

        if (TextUtils.isEmpty(txt_email) || TextUtils.isEmpty(txt_password)) {
            Toast.makeText(StartloginActivity.this, "can not be empty", Toast.LENGTH_SHORT).show();
        } else if (txt_password.length() < 6) {
            Toast.makeText(StartloginActivity.this, "Password must be longer 6 symbols", Toast.LENGTH_SHORT).show();
        } else {
            firebaseLogin();
        }
    }

    private void firebaseLogin() {
        String txt_email = email.getText().toString();
        String txt_password = password.getText().toString();

        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(txt_email, txt_password)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        progressDialog.dismiss();
                        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                        Toast.makeText(StartloginActivity.this, "LogIn to your account\n" + txt_email, Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(StartloginActivity.this, MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        finish();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressDialog.dismiss();
                        Toast.makeText(StartloginActivity.this, "check your email or password", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void checkUser(){
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if (firebaseUser != null) {
            Intent intent = new Intent(StartloginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_startactivity, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item) {
        switch (item.getItemId()){
            case R.id.exit_menu_btn:
                finish();
                System.exit(1);
                break;
            case R.id.cl_menu_btn:

                break;
            case R.id.ct_menu_btn:

                break;
        }
        return super.onOptionsItemSelected(item);
    }
}