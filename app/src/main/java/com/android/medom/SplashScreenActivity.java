package com.android.medom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashScreenActivity extends AppCompatActivity {
    /** Duration of wait **/
    private final int SPLASH_DISPLAY_LENGTH = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash_screen);
        /* New Handler to start the LandingActivity
             * and close this Splash-Screen after some seconds.*/
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    /* Creates an Intent that will start the LandingActivity. */
                    startActivity(new Intent(SplashScreenActivity.this, LandingActivity.class));
                    finish();
                }
            },  SPLASH_DISPLAY_LENGTH);


        }
}
