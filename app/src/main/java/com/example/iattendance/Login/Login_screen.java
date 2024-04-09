package com.example.iattendance.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.iattendance.Bottom_navigation.Admin_bottom_nav;
import com.example.iattendance.Bottom_navigation.Student_bottom_nav;
import com.example.iattendance.R;
import com.google.android.material.button.MaterialButton;

public class Login_screen extends AppCompatActivity {

    MaterialButton loginBtn, signupBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen);

//        Hooks
        loginBtn = findViewById(R.id.loginBtn);
        signupBtn = findViewById(R.id.signupBtn);

        loginBtn.setOnClickListener(v -> {
            Intent intent = new Intent(Login_screen.this, Admin_bottom_nav.class);
            startActivity(intent);
        });
    }
}