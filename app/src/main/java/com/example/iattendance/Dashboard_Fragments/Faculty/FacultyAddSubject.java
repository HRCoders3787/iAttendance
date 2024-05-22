package com.example.iattendance.Dashboard_Fragments.Faculty;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.iattendance.Dashboard_Fragments.Student.Student_SubjectModal;
import com.example.iattendance.R;
import com.example.iattendance.Utils.Faculty.FacultySessionManager;
import com.example.iattendance.Utils.Subjects.Validation.subjectValidation;
import com.example.iattendance.Utils.Subjects.db.CourseDb;
import com.example.iattendance.Utils.Subjects.db.SubjectsModel;
import com.example.iattendance.Utils.Subjects.db.subjectInterface;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Objects;

public class FacultyAddSubject extends AppCompatActivity {

    TextInputLayout sub_type, sem, div, batch;
    TextInputEditText subjectName, subjectCode, subjectType, subSemester;
    TextInputEditText division, batchCount, courseName, batchRange;
    HashMap<String, String> facultyMember;
    subjectValidation validation;
    CourseDb courseDb;
    Button addSubjectBtn;
    HashMap<String, String> subjectData;
    FacultySessionManager facultySession;
    String collegeCode, courseId, year, semester, facultyCode, batch_, div_, subCode, subName, subType, facultyName, bRange;

    private FirebaseFirestore db;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.faculty_add_subject);

        initializeViews();

        db = FirebaseFirestore.getInstance();


        addSubjectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateInputValues(Objects.requireNonNull(courseName.getText()).toString(),
                        Objects.requireNonNull(subjectName.getText()).toString(),
                        Objects.requireNonNull(subjectCode.getText()).toString(),
                        Objects.requireNonNull(subjectType.getText()).toString(),
                        Objects.requireNonNull(subSemester.getText()).toString(),
                        Objects.requireNonNull(division.getText()).toString())
                ) {

                    Calendar calendar = Calendar.getInstance();
                    int currentYear = calendar.get(Calendar.YEAR);
                    String year = String.valueOf(currentYear);
                    batch_ = Objects.requireNonNull(batch.getEditText()).getText().toString();
                    div_ = Objects.requireNonNull(div.getEditText()).getText().toString();
                    semester = Objects.requireNonNull(sem.getEditText()).getText().toString();
                    subCode = subjectCode.getText().toString();
                    subName = subjectName.getText().toString();
                    subType = subjectType.getText().toString();
                    bRange = batchRange.getText().toString();

                    collegeCode = facultyMember.get(FacultySessionManager.KEY_FC_COLLEGE);
                    courseId = courseName.getText().toString();
                    facultyCode = facultyMember.get(FacultySessionManager.KEY_FC_ID);
                    facultyName = facultyMember.get(FacultySessionManager.KEY_FC_NAME);


                    SubjectsModel subjectModel = new SubjectsModel(batch_, div_, semester, subCode, subName, subType, year, 0, bRange);
                    addSubjectData(collegeCode, courseId, year, semester, facultyCode, subType, subName, facultyName, subjectModel);

                    addSemesterData(collegeCode, facultyCode, semester, year);

                    updateCourse(courseName.getText().toString(), collegeCode, facultyCode, facultyMember.get(FacultySessionManager.KEY_FC_PHONE));
                    facultySession.updateSession(courseId);

                } else {
                    Toast.makeText(FacultyAddSubject.this, "Fields are empty!...", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void updateCourse(String courseName, String collCode, String facCode, String contact) {

        db.collection("Faculty")
                .document(collCode)
                .collection(facCode)
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        for (DocumentSnapshot document : queryDocumentSnapshots.getDocuments()) {
                            if (document.getString("contact").equals(contact)) {
                                document.getReference().update("course", courseName)
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()) {
                                                    Toast.makeText(FacultyAddSubject.this, "Success update course", Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Toast.makeText(FacultyAddSubject.this, "Exception in updating!...", Toast.LENGTH_SHORT).show();
                                            }
                                        });
                            }
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(FacultyAddSubject.this, "Error getting in updating faculty", Toast.LENGTH_SHORT).show();
                    }
                });


    }

    private void addSemesterData(String collegeCode, String facultyCode, String semester, String year) {

        db.collection("Semesters")
                .document(collegeCode)
                .collection(facultyCode)
                .whereEqualTo("Semester", semester)
                .whereEqualTo("Year", year)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        QuerySnapshot querySnapshot = task.getResult();
                        if (querySnapshot != null && !querySnapshot.isEmpty()) {
//                            Toast.makeText(context, "Semester data already exists", Toast.LENGTH_SHORT).show();
                        } else {
                            HashMap<String, String> semesterData = new HashMap<>();
                            semesterData.put("Semester", semester);
                            semesterData.put("Year", year);

                            db.collection("Semesters")
                                    .document(collegeCode)
                                    .collection(facultyCode)
                                    .add(semesterData)
                                    .addOnSuccessListener(documentReference -> {
                                        String generatedId = documentReference.getId();
                                        Toast.makeText(getApplicationContext(), "Semester data added with ID: " + generatedId, Toast.LENGTH_SHORT).show();
                                    })
                                    .addOnFailureListener(e -> {
                                        Toast.makeText(getApplicationContext(), "Error adding semester data: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                    });
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Error checking semester data: " + Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

    }

    private void addSubjectData(String collegeCode, String courseId, String year, String semester, String facultyCode, String subType, String subName, String facultyName, SubjectsModel subjectModel) {

        db.collection("Faculty Subjects")
                .document(collegeCode)
                .collection("Course")
                .document(courseId)
                .collection("Year")
                .document(year)
                .collection("Semester")
                .document("Semester " + semester)
                .collection("Faculty code")
                .document(facultyCode)
                .collection("Details")
                .add(subjectModel)
                .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        if (task.isSuccessful()) {
                            HashMap<String, String> map = new HashMap<>();
                            map.put("collegeCode", collegeCode);
                            map.put("courseName", courseId);
                            map.put("year", year);
                            map.put("semester", semester);
                            map.put("facultyName", facultyName);
                            map.put("subjectType", subType);
                            map.put("Division", subjectModel.getDivision());
                            map.put("subjectName", subjectName.getText().toString());
                            map.put("batch", subjectModel.getBatch());
                            map.put("batchRange", subjectModel.getBatchRange());
                            courseDb = new CourseDb(getApplicationContext(), map);
                            courseDb.addStudentSubCard(new subjectInterface() {
                                @Override
                                public void isConfirmed(boolean val) {
                                    if (val) {
                                        Toast.makeText(FacultyAddSubject.this, "Successfully added subject Card", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(FacultyAddSubject.this, "Failed to add subject card!...", Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void getSemesterList(ArrayList<String> semesterList) {

                                }

                                @Override
                                public void getStudentSemesterList(ArrayList<Student_SubjectModal> semesterList) {

                                }

                                @Override
                                public void getFacultyCodes(ArrayList<String> facultyCodeList) {

                                }

                            });
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(FacultyAddSubject.this, "Failed to add subject data", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void initializeViews() {
        sub_type = findViewById(R.id.sub_type);
        sem = findViewById(R.id.sem);
        div = findViewById(R.id.div);
        batch = findViewById(R.id.batch);

        subjectName = findViewById(R.id.subjectName);
        subjectCode = findViewById(R.id.subjectCode);
        subjectType = findViewById(R.id.subjectType);
        subSemester = findViewById(R.id.subSemester);
        division = findViewById(R.id.division);
        batchCount = findViewById(R.id.batchCount);
        addSubjectBtn = findViewById(R.id.addSubjectBtn);
        courseName = findViewById(R.id.courseName);
        batchRange = findViewById(R.id.batchRange);


        sub_type.setHintAnimationEnabled(false);
        sem.setHintAnimationEnabled(false);
        div.setHintAnimationEnabled(false);
        batch.setHintAnimationEnabled(false);
/*        sub_type.setHintEnabled(false);
        sem.setHintEnabled(false);
        div.setHintEnabled(false);
        batch.setHintEnabled(false);
*/
        subjectType.setOnFocusChangeListener((view, focused) -> {
            if (focused) {
                // When TextInputEditText is focused, show the hint
                sub_type.setHintEnabled(true);
            } else {
                // When TextInputEditText loses focus, show EditText text if available
                CharSequence text = subjectType.getText();
                if (!TextUtils.isEmpty(text)) {
                    sub_type.setHintEnabled(false);
                }
            }
        });
        subSemester.setOnFocusChangeListener((view, focused) -> {
            if (focused) {
                // When TextInputEditText is focused, show the hint
                sem.setHintEnabled(true);
            } else {
                // When TextInputEditText loses focus, show EditText text if available
                CharSequence text = subSemester.getText();
                if (!TextUtils.isEmpty(text)) {
                    sem.setHintEnabled(false);
                }
            }
        });
        division.setOnFocusChangeListener((view, focused) -> {
            if (focused) {
                // When TextInputEditText is focused, show the hint
                div.setHintEnabled(true);
            } else {
                // When TextInputEditText loses focus, show EditText text if available
                CharSequence text = division.getText();
                if (!TextUtils.isEmpty(text)) {
                    div.setHintEnabled(false);
                }
            }
        });
        batchCount.setOnFocusChangeListener((view, focused) -> {
            if (focused) {
                // When TextInputEditText is focused, show the hint
                batch.setHintEnabled(true);
            } else {
                // When TextInputEditText loses focus, show EditText text if available
                CharSequence text = batchCount.getText();
                if (!TextUtils.isEmpty(text)) {
                    batch.setHintEnabled(false);
                }
            }
        });

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