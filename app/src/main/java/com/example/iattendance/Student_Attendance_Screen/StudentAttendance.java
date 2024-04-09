package com.example.iattendance.Student_Attendance_Screen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Toolbar;

import com.example.iattendance.R;
import com.google.android.material.appbar.MaterialToolbar;

public class StudentAttendance extends AppCompatActivity {
    MaterialToolbar toolbar;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_attendance);

        toolbar = findViewById(R.id.toolbar);

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



    }
}