package com.example.loginsql.customer.Auth;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.example.loginsql.R;
import com.example.loginsql.customer.Auth.LoginActivity;
import com.example.loginsql.customer.HomeActivity;

public class SplashScreenLOGO extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen_logo);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                SharedPreferences preferences = getSharedPreferences("Auth", MODE_PRIVATE);
                Intent intent;
                boolean validate = preferences.getBoolean("flag", false);
                if (validate) {
                    intent = new Intent(SplashScreenLOGO.this, HomeActivity.class);
                    finish();
                } else {
                    intent = new Intent(SplashScreenLOGO.this, LoginActivity.class);
                    finish();

                }
                startActivity(intent);

            }
        }, 4000);
    }
}