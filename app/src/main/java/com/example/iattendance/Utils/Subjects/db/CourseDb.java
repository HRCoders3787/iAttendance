package com.example.iattendance.Utils.Subjects.db;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.iattendance.Utils.Admin.Utils;
import com.example.iattendance.Utils.Faculty.FacultySessionManager;
import com.example.iattendance.Utils.Faculty.db.InsertDbCallback;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

public class CourseDb {
    Context context;
    HashMap<String, String> data;
    FirebaseFirestore db;
    FacultySessionManager facultySession;
    FirebaseOptions options;
    String year;

    public CourseDb(Context context, HashMap<String, String> data) {
        this.context = context;
        this.data = data;
        db = FirebaseFirestore.getInstance();
        facultySession = new FacultySessionManager(context);
        this.year = getCurrentYr();
    }

    public void courseInsertDb() {
        if (data.get("batchCount").toString().length() > 0) {
            String batches[] = data.get("batchCount").toString().split(",");
            for (int i = 0; i < batches.length; i++) {
                db.collection("Course").document(data.get("collegeCode").trim()).collection(data.get("course").trim())
                        .document(year).collection("sem " + data.get("subSemester").trim())
                        .document(data.get("subjectType").trim()).collection(data.get("division").trim())
                        .document(data.get("subjectCode").trim()).collection(batches[i].trim())
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
            db.collection("Course").document(data.get("collegeCode").trim()).collection(data.get("course").trim())
                    .document(year.trim()).collection("sem " + data.get("subSemester").trim())
                    .document(data.get("subjectType").trim()).collection(data.get("division").trim())
                    .document(data.get("subjectCode").trim()).collection("No Batch")
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

    public void retrieveAllCourses() {
        if (data.size() > 0) {
            String collegeCode = data.get("collegeCode").trim();
            String course = data.get("course").trim();
            String semester = data.get("semester").trim();
            String division = data.get("division").trim();

            // Print values for debugging
            System.out.println("Constructed values: " + collegeCode + " " + course + " " + semester + " " + division + " " + year);

            // Construct the path and print it for debugging
            String path = "Course/" + collegeCode + "/" + course + "/" + year + "/" + semester + "/Practical/" + division;
            System.out.println("Constructed path: " + path);

            // Now query the constructed path
            db.collection("Course").document(collegeCode)
                    .collection(course).document(year).collection(semester)
                    .document("Practical").collection(division)
                    .get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                            if (queryDocumentSnapshots.isEmpty()) {
                                Toast.makeText(context, "Empty snapshot", Toast.LENGTH_SHORT).show();
                            } else {
                                for (DocumentSnapshot snapshot : queryDocumentSnapshots.getDocuments()) {
                                    Toast.makeText(context, "Value  " + snapshot.getId(), Toast.LENGTH_SHORT).show();
                                    // Optionally, print the snapshot data to debug
                                    // System.out.println("================> " + snapshot.getData());
                                }
                            }
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(context, "Error : " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        } else {
            Toast.makeText(context, "Passed data is empty", Toast.LENGTH_SHORT).show();
        }
    }

    public void retrieveSemesters() {
        if (data.size() > 0) {
            db.collection("Course").document(data.get("collegeCode"))
                    .collection(data.get("course")).document(year)
                    .addSnapshotListener(new EventListener<DocumentSnapshot>() {
                        @Override
                        public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                            System.out.print("=======> " + value.getData());
                        }
                    });
        }
    }
}