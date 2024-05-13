package com.example.iattendance.Dashboard_Fragments.Faculty;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.example.iattendance.R;
import com.example.iattendance.Utils.Subjects.Validation.subjectValidation;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.HashMap;

public class FacultyAddSubject extends AppCompatActivity {

    TextInputEditText subjectName, subjectCode, subjectType, subSemester;
    TextInputEditText division, batchCount;
    subjectValidation validation;
    Button addSubjectBtn;
    HashMap<String, String> subjectData;


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.faculty_add_subject);

        initializeViews();
        addSubjectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (subjectName.getText().toString().isEmpty() && subjectCode.getText().toString().isEmpty() &&
                        subjectType.getText().toString().isEmpty() && subSemester.getText().toString().isEmpty()
                        && division.getText().toString().isEmpty() && batchCount.getText().toString().isEmpty()) {
//                    Toast.makeText(this, "Fields are empty!...", Toast.LENGTH_SHORT).show();
                } else {
                    subjectData = new HashMap<>();
                    subjectData.put("subject", subjectName.getText().toString());
                    subjectData.put("subjectCode", subjectName.getText().toString());
                    subjectData.put("subjectType", subjectName.getText().toString());
                    subjectData.put("subSemester", subjectName.getText().toString());
                    subjectData.put("division", subjectName.getText().toString());
                    subjectData.put("batchCount", subjectName.getText().toString());
                }
            }
        });

    }


    private void initializeViews() {
        subjectName = findViewById(R.id.subjectName);
        subjectCode = findViewById(R.id.subjectCode);
        subjectType = findViewById(R.id.subjectType);
        subSemester = findViewById(R.id.subSemester);
        division = findViewById(R.id.division);
        batchCount = findViewById(R.id.batchCount);
        addSubjectBtn = findViewById(R.id.addSubjectBtn);
    }
}