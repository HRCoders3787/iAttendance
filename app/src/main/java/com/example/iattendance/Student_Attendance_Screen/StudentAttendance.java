package com.example.iattendance.Student_Attendance_Screen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toolbar;

import com.example.iattendance.R;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;

public class StudentAttendance extends AppCompatActivity {
    MaterialToolbar toolbar;
    MaterialButton mark_att_btn;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_attendance);

        toolbar = findViewById(R.id.toolbar);
        mark_att_btn = findViewById(R.id.mark_att_btn);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Inside your Activity
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.popBackStack();

                // Inside your Activity
                finish();
            }
        });

        mark_att_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}