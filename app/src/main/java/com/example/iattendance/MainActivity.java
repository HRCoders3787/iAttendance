package com.example.iattendance;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.example.iattendance.Bottom_navigation.Admin_bottom_nav;
import com.example.iattendance.Utils.Admin.SessionManager;

public class MainActivity extends AppCompatActivity {
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sessionManager = new SessionManager(MainActivity.this);

        new Handler().postDelayed(() -> {
            if (sessionManager.isLoggedIn()) {
                Intent intent = new Intent(MainActivity.this, Admin_bottom_nav.class);
                startActivity(intent);
                finish();
            } else {
                Intent intent = new Intent(MainActivity.this, Welcome_screen.class);
                startActivity(intent);
                finish();
            }
        }, 3000);
    }
}