package com.example.iattendance.Bottom_navigation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.example.iattendance.Dashboard_Fragments.Faculty.HomeFragment_faculty;
import com.example.iattendance.Dashboard_Fragments.Faculty.SettingsFragment_faculty;
import com.example.iattendance.Dashboard_Fragments.Faculty.StatsFragment_faculty;
import com.example.iattendance.Dashboard_Fragments.Student.HomeFragment_student;
import com.example.iattendance.Dashboard_Fragments.Student.SettingsFragment_student;
import com.example.iattendance.Dashboard_Fragments.Student.StatsFragment_student;
import com.example.iattendance.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Faculty_bottom_nav extends AppCompatActivity {

    FrameLayout frame_layout;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.faculty_bottom_nav);

        initialiseViews();

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            int itemId = item.getItemId();

            if (itemId == R.id.home) {
                item.setChecked(true);
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new HomeFragment_faculty()).commit();
            } else if (itemId == R.id.stats) {
                item.setChecked(true);
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new StatsFragment_faculty()).commit();
            } else if (itemId == R.id.settings) {
                item.setChecked(true);
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new SettingsFragment_faculty()).commit();
            }
            return true;
        });
    }

    private void initialiseViews() {
        frame_layout = findViewById(R.id.frame_layout);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        getSupportFragmentManager().beginTransaction().add(R.id.frame_layout, new HomeFragment_student()).commit();
    }
}