package com.searchforhealth.census;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import com.searchforhealth.census.Preference.SharedCS;


public class SplashActivity extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        SharedCS scs = new SharedCS(SplashActivity.this);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
                if(!SharedCS.getPreferences().getString(SharedCS.LOGIN_STATUS,"").equalsIgnoreCase("Y")) {
                    Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(intent);
                }else{
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        }, SPLASH_TIME_OUT);
    }

}