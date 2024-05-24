package com.example.iattendance.Faculty_Attendance_Screen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

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
import com.google.android.material.button.MaterialButton;
import com.google.android.material.tabs.TabLayout;

import java.util.Objects;

public class FacultyAttendancePg extends AppCompatActivity {
    Toolbar toolbar;
    RelativeLayout alert_layout;
    Spinner session_mode_spinner;
    RecyclerView stud_list_rv;
    TextView alert_tv, first_letter_tv, subj_name_tv, prof_name_tv, div_tv, date_tv, present_count_tv, total_count_tv, percent_tv;
    MaterialButton startSessionBtn, endSessionBtn;
    String alert_txt, subj_name_txt, prof_name_txt, div_txt, date_txt, present_count_txt, total_count_txt, percent_txt;

    CustomSpinnerAdapter adapter;

    String[] spinner_items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.faculty_attendance_pg);

        initializeViews();

        session_mode_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = (String) parent.getItemAtPosition(position);
                if (position != 0) {  // Ignore the hint item
                    Toast.makeText(FacultyAttendancePg.this, "Selected: " + selectedItem, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
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

        spinner_items = new String[]{
                "Select session mode",
                "Automatic (3 min)",
                "Manual"
        };

        adapter = new CustomSpinnerAdapter(this, spinner_items);
        session_mode_spinner.setAdapter(adapter);
    }
}