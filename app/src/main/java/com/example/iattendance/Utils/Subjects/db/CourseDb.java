package com.example.iattendance.Utils.Subjects.db;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.iattendance.Utils.Admin.Utils;
import com.example.iattendance.Utils.Faculty.FacultySessionManager;
import com.example.iattendance.Utils.Faculty.db.InsertDbCallback;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Random;

public class CourseDb {
    Context context;
    HashMap<String, String> data;
    FirebaseFirestore db;
    FacultySessionManager facultySession;

    public CourseDb(Context context, HashMap<String, String> data) {
        this.context = context;
        this.data = data;
        db = FirebaseFirestore.getInstance();
        facultySession = new FacultySessionManager(context);
    }

    public void courseInsertDb() {
        String year = getCurrentYr();
        if (data.get("batchCount").toString().length() > 0) {
            String batches[] = data.get("batchCount").toString().split(",");
            data.remove("batchCount");
            for (int i = 0; i < batches.length; i++) {
                db.collection("Course").document(data.get("collegeCode")).collection(data.get("course"))
                        .document(year).collection("sem " + data.get("subSemester"))
                        .document(data.get("subjectType")).collection(data.get("division"))
                        .document(data.get("subjectCode")).collection(batches[i])
                        .add(data)
                        .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentReference> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(context, "Successfully Inserted Subject!...", Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(context, "Failed on Inserting Subject try again!...", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        } else {
            data.remove("batchCount");
            db.collection("Course").document(data.get("collegeCode")).collection(data.get("course"))
                    .document(year).collection("sem " + data.get("subSemester"))
                    .document(data.get("subjectType")).collection(data.get("division"))
                    .document(data.get("subjectCode")).collection("No Batch")
                    .add(data)
                    .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentReference> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(context, "Successfully Inserted Subject!...", Toast.LENGTH_SHORT).show();
                            }
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(context, "Failed on Inserting Subject try again!...", Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }

    private String getCurrentYr() {
        Calendar calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR);
        return String.valueOf(currentYear);
    }

    private String getSubjectId() {
        String id = data.get("subject");
        Random random = new Random();
        int code = random.nextInt(9000) + 1000;
        id = id + code;
        return id;
    }
}
