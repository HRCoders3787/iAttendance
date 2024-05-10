package com.example.iattendance.Dashboard_Fragments.Faculty;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.example.iattendance.R;
import com.google.android.material.textfield.TextInputLayout;

public class FacultyAddSubject extends AppCompatActivity {
    TextInputLayout year_tb;
    AutoCompleteTextView autoCompleteTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.faculty_add_subject);

        year_tb = findViewById(R.id.sub_code_tb);
        autoCompleteTextView = findViewById(R.id.autoCompleteTextView);
    }
}