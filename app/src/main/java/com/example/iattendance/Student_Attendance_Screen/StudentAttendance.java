package com.example.iattendance.Student_Attendance_Screen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toolbar;

import com.example.iattendance.R;
import com.example.iattendance.Utils.Student.StudentSessionManager;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Objects;

public class StudentAttendance extends AppCompatActivity {
    StudentSessionManager studentSessionManager;
    HashMap<String, String> studentMembers;
    Intent prevIntent;
    RelativeLayout alert_layout;
    TextView alert_tv, first_letter_tv, subj_name_tv, prof_name_tv, div_tv, date_tv, present_count_tv, total_count_tv, percent_tv;
    String alert_txt, subj_name_txt, prof_name_txt, div_txt, date_txt, present_count_txt, total_count_txt, percent_txt;
    MaterialToolbar toolbar;
    MaterialButton mark_att_btn;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_attendance);

        initializeViews();

        // Base text
        SpannableString attendanceText = new SpannableString("Attendance");

        // Extra text
        SpannableString extraText = new SpannableString("  time");
        extraText.setSpan(new ForegroundColorSpan(Color.GRAY), 0, extraText.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        extraText.setSpan(new RelativeSizeSpan(0.9f), 0, extraText.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        // Combine texts
        SpannableStringBuilder title = new SpannableStringBuilder();
        title.append(attendanceText);
        title.append(extraText);

        // Set the toolbar title
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);

        // Additional toolbar setup if needed
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(v -> onBackPressed());


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

    @SuppressLint("SetTextI18n")
    private void initializeViews() {
//        StudentSessionManager
        studentSessionManager = new StudentSessionManager(getApplicationContext());
        studentMembers = studentSessionManager.getUserDetails();

//        Previous intent data
        prevIntent = getIntent();
        prof_name_txt = prevIntent.getStringExtra("faculty_name");
        subj_name_txt = prevIntent.getStringExtra("sub_name");
        present_count_txt = prevIntent.getStringExtra("current_att");
        total_count_txt = prevIntent.getStringExtra("total_att");

//        Hooks
        toolbar = findViewById(R.id.toolbar);
        alert_layout = findViewById(R.id.alert_layout);
        alert_tv = findViewById(R.id.alert_tv);
        first_letter_tv = findViewById(R.id.first_letter_tv);
        subj_name_tv = findViewById(R.id.subj_name_tv);
        prof_name_tv = findViewById(R.id.prof_name_tv);
        div_tv = findViewById(R.id.div_tv);
        date_tv = findViewById(R.id.date_tv);
        present_count_tv = findViewById(R.id.present_count_tv);
        total_count_tv = findViewById(R.id.total_count_tv);
        percent_tv = findViewById(R.id.percent_tv);
        mark_att_btn = findViewById(R.id.mark_att_btn);

        if (Integer.parseInt(present_count_txt) != 0) {
            percent_txt = String.valueOf((Integer.parseInt(present_count_txt) * 100) / Integer.parseInt(total_count_txt));
        } else {
            percent_txt = "0";
        }
        div_txt = studentMembers.get(StudentSessionManager.KEY_ST_DIV);
        date_txt = getCurrentDateFormatted();

//        Setting views with values
        first_letter_tv.setText(subj_name_txt.substring(0, 1));
        prof_name_tv.setText(prof_name_txt);
        subj_name_tv.setText(subj_name_txt);
        present_count_tv.setText(present_count_txt);
        total_count_tv.setText("/ " + total_count_txt);
        percent_tv.setText(percent_txt + "%");
        div_tv.append(" " + div_txt);
        date_tv.setText(date_txt);
    }

    private String getCurrentDateFormatted() {
        Date currentDate = Calendar.getInstance().getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, EEE", Locale.getDefault());
        return dateFormat.format(currentDate);
    }

}