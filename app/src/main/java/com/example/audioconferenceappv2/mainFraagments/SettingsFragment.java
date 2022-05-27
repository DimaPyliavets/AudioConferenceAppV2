package com.example.audioconferenceappv2.mainFraagments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.audioconferenceappv2.R;
import com.example.audioconferenceappv2.UserInfoActivity;
import com.example.audioconferenceappv2.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class SettingsFragment extends Fragment {


    ImageView profile_image;
    TextView username;
    TextView email;
    Button edit_BTN;
    Button delete_BTN;

    FirebaseUser firebaseUser;
    DatabaseReference databaseReference;
    Intent intent;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_settings, container, false);


        profile_image = view.findViewById(R.id.imageView);
        username = view.findViewById(R.id.username);
        email = view.findViewById(R.id.email);

        delete_BTN = view.findViewById(R.id.delete_button);
        edit_BTN = view.findViewById(R.id.edit_button);

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference("users").child(firebaseUser.getUid());

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user = snapshot.getValue(User.class);
                    username.setText(user.getUsername());
                    email.setText(user.getEmail());
                if (user.getImageURL().equals("default")) {
                    profile_image.setImageResource(R.drawable.ic_baseline_account_circle);
                } else {
                    Glide.with(getContext()).load(user.getImageURL()).into(profile_image);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        });
        return view;
    }
}