package com.example.audioconferenceappv2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SplashScreen extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        ImageView imageView = findViewById(R.id.splashscreenlogobackground);
        ImageView imageView1 = findViewById(R.id.splashscreenlogo);
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_out_back);
        Animation animation1 = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_out_logo);
        imageView.startAnimation(animation);
        imageView1.startAnimation(animation1);


        Thread timer = new Thread(){
            @Override
            public void run(){
                try {
                    sleep(2000);
                    Intent intent = new Intent(getApplicationContext(), StartloginActivity.class);
                    startActivity(intent);
                    finish();
                    super.run();
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } ;
        timer.start();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}