package com.example.iattendance.Faculty_Attendance_Screen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.view.View;
import android.widget.AdapterView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.iattendance.R;
import com.example.iattendance.Utils.Attendance.DB.FacultyAttendanceDb;
import com.example.iattendance.Utils.Faculty.FacultySessionManager;
import com.google.android.material.button.MaterialButton;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class FacultyAttendancePg extends AppCompatActivity {
    Toolbar toolbar;
    RelativeLayout alert_layout;
    Spinner session_mode_spinner;
    RecyclerView stud_list_rv;
    TextView alert_tv, first_letter_tv, subj_name_tv, prof_name_tv, div_tv, date_tv, present_count_tv, total_count_tv, percent_tv;
    MaterialButton startSessionBtn, endSessionBtn;
    String alert_txt, subj_name_txt, prof_name_txt, div_txt, date_txt, present_count_txt, total_count_txt, percent_txt;
    String facultyName, courseName, collCode;
    CustomSpinnerAdapter adapter;
    HashMap<String, String> mapData;
    String selectedMode;
    ArrayList<String> listData;
    FacultyAttendanceDb attendanceDb;
    FacultySessionManager facultySession;
    String[] spinner_items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.faculty_attendance_pg);

        Intent intent = getIntent();
        listData = intent.getStringArrayListExtra("Attendance Data");
        initializeViews();

        session_mode_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = (String) parent.getItemAtPosition(position);
                if (position != 0) {  // Ignore the hint item
                    selectedMode = selectedItem;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });

        startSessionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedMode.equals("Manual")) {
                    startSessionBtn.setAlpha(0.5f);
                    HashMap<String, Boolean> statusMap = new HashMap<String, Boolean>();
                    statusMap.put("status", true);
                    attendanceDb = new FacultyAttendanceDb(getApplicationContext(), statusMap);
                    attendanceDb.startAttendance(collCode, listData.get(0), courseName, listData.get(3));
                }
            }
        });

        endSessionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startSessionBtn.setAlpha(1);
                HashMap<String, Boolean> statusMap = new HashMap<String, Boolean>();
                statusMap.put("status", false);
                attendanceDb = new FacultyAttendanceDb(getApplicationContext(), statusMap);
                attendanceDb.startAttendance(collCode, listData.get(0), courseName, listData.get(3));
            }
        });
    }

    private void initializeViews() {

//        Hooks
        toolbar = findViewById(R.id.toolbar);
        alert_layout = findViewById(R.id.alert_layout);
        session_mode_spinner = findViewById(R.id.session_mode_spinner);
        stud_list_rv = findViewById(R.id.stud_list_rv);

        alert_tv = findViewById(R.id.alert_tv);
        first_letter_tv = findViewById(R.id.first_letter_tv);
        subj_name_tv = findViewById(R.id.subj_name_tv);
        prof_name_tv = findViewById(R.id.prof_name_tv);
        div_tv = findViewById(R.id.div_tv);
        date_tv = findViewById(R.id.date_tv);
        present_count_tv = findViewById(R.id.present_count_tv);
        total_count_tv = findViewById(R.id.total_count_tv);
        percent_tv = findViewById(R.id.percent_tv);
        startSessionBtn = findViewById(R.id.startSessionBtn);
        endSessionBtn = findViewById(R.id.endSessionBtn);

//        Setting Toolbar view
        {
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
        }

        subj_name_tv.setText(listData.get(0));
        div_tv.setText("Div - " + listData.get(1));
        prof_name_tv.setText(listData.get(2));

        date_tv.setText(getCurrentDate());


        spinner_items = new String[]{
                "Select session mode",
                "Automatic (3 min)",
                "Manual"
        };

        facultySession = new FacultySessionManager(getApplicationContext());
        mapData = facultySession.getUserDetails();
        collCode = mapData.get(FacultySessionManager.KEY_FC_COLLEGE);
        courseName = mapData.get(FacultySessionManager.KEY_FC_COURSE);
        facultyName = mapData.get(FacultySessionManager.KEY_FC_NAME);

        adapter = new CustomSpinnerAdapter(this, spinner_items);
        session_mode_spinner.setAdapter(adapter);
    }

    private String getCurrentDate() {
        LocalDate currentDate = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            currentDate = LocalDate.now();
        }

        // Define the date format
        DateTimeFormatter formatter = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            formatter = DateTimeFormatter.ofPattern("MMM dd, E");
        }

        // Format the current date
        String formattedDate = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            formattedDate = currentDate.format(formatter);
        }

        // Print the formatted date
        return formattedDate;
    }
}