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

//    passedData {collegeCode, facultyName, semester, division, course}
    // default semester last added sem
    //default division is A contents.

    public void retrieveAllCourses() {
        if (data.size() > 0) {

            db.collection("Course").document(data.get("collegeCode")).
                    collection(data.get("course")).document(year).collection(data.get("semester"))
                    .document("Theory").collection(data.get("division"))
                    .addSnapshotListener(new EventListener<QuerySnapshot>() {
                        @Override
                        public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                            if (!value.isEmpty()) {

                            }
                        }
                    });

        } else {
            Toast.makeText(context, "passed Data is empty", Toast.LENGTH_SHORT).show();
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
