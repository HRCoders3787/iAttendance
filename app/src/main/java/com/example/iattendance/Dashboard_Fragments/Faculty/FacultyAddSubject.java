package com.example.iattendance.Dashboard_Fragments.Faculty;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.example.iattendance.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.HashMap;

public class FacultyAddSubject extends AppCompatActivity {

    TextInputEditText subjectName, subjectCode, subjectType, subSemester;
    TextInputEditText division, batchCount;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.faculty_add_subject);

        initializeViews();
        if ()

    }



    private void initializeViews() {
        subjectName = findViewById(R.id.subjectName);
        subjectCode = findViewById(R.id.subjectCode);
        subjectType = findViewById(R.id.subjectType);
        subSemester = findViewById(R.id.subSemester);
        division = findViewById(R.id.division);
        batchCount = findViewById(R.id.batchCount);
    }
}