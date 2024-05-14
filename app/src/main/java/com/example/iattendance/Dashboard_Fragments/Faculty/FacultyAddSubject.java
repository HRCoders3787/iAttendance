package com.example.iattendance.Dashboard_Fragments.Faculty;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.example.iattendance.R;
import com.example.iattendance.Utils.Faculty.FacultySessionManager;
import com.example.iattendance.Utils.Subjects.Validation.subjectValidation;
import com.example.iattendance.Utils.Subjects.db.CourseDb;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.HashMap;

public class FacultyAddSubject extends AppCompatActivity {

    TextInputEditText subjectName, subjectCode, subjectType, subSemester;
    TextInputEditText division, batchCount, courseName;
    HashMap<String, String> facultyMember;
    subjectValidation validation;
    CourseDb courseDb;
    Button addSubjectBtn;
    HashMap<String, String> subjectData;
    FacultySessionManager facultySession;


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.faculty_add_subject);

        initializeViews();
        addSubjectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateInputValues(courseName.getText().toString(),
                        subjectName.getText().toString(),
                        subjectCode.getText().toString(),
                        subjectType.getText().toString(),
                        subSemester.getText().toString(),
                        division.getText().toString())
                ) {

                    subjectData = new HashMap<>();
                    subjectData.put("facultyName", facultyMember.get(facultySession.KEY_FC_NAME));
                    subjectData.put("collegeCode", facultyMember.get(facultySession.KEY_FC_ID));
                    subjectData.put("course", courseName.getText().toString());
                    subjectData.put("subject", subjectName.getText().toString());
                    subjectData.put("subjectCode", subjectCode.getText().toString());
                    subjectData.put("subjectType", subjectType.getText().toString());
                    subjectData.put("subSemester", subSemester.getText().toString());
                    subjectData.put("division", division.getText().toString());
                    subjectData.put("batchCount", batchCount.getText().toString());

                    courseDb = new CourseDb(getApplicationContext(), subjectData);
                    courseDb.courseInsertDb();

                } else {
                    Toast.makeText(FacultyAddSubject.this, "Fields are empty!...", Toast.LENGTH_SHORT).show();
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
        courseName = findViewById(R.id.courseName);

        validation = new subjectValidation(getApplicationContext());
        facultySession = new FacultySessionManager(getApplicationContext());
        facultyMember = facultySession.getUserDetails();
    }


    public boolean validateInputValues(String course, String subjectName, String subjectCode,
                                       String subjectType, String subSemester,
                                       String division) {
        return validation.isEmptyField(course) &&
                validation.isEmptyField(subjectName) &&
                validation.isEmptyField(subjectCode) &&
                validation.isEmptyField(subjectType) &&
                validation.isEmptyField(subSemester) &&
                validation.isEmptyField(subSemester) &&
                validation.isEmptyField(division);
    }


}