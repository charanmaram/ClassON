package com.example.chara.classon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.logging.Handler;

/**
 * Created by charan on 4/19/2017.
 * <p>
 * Splash Screen
 */

public class Splash extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Thread myThread = new Thread() { // New Thread
            @Override
            public void run() {
                try {

                    // Wait before the home page os popped up
                    sleep(4000);

                    // Intent from the splash screen to the Login Activity
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intent);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        myThread.start(); // Starting the Thread
    }
}
