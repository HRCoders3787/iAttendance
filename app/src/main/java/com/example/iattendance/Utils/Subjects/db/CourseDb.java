package com.example.iattendance.Utils.Subjects.db;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.iattendance.Utils.Faculty.FacultySessionManager;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class CourseDb {
    Context context;
    HashMap<String, String> data;
    FirebaseFirestore db;
    FacultySessionManager facultySession;
    FirebaseOptions options;
    HashMap<String, SubjectModal> subjectDetails;
    ArrayList<SubjectModal> subjects;
    String year;

    public CourseDb(Context context, HashMap<String, String> data) {
        this.context = context;
        this.data = data;
        db = FirebaseFirestore.getInstance();
        facultySession = new FacultySessionManager(context);
        this.year = getCurrentYr();
    }

    public CourseDb() {
        db = FirebaseFirestore.getInstance();
    }

    public void courseInsertDb() {
        String collegeCode = data.get("collegeCode");
        String course = data.get("course");
        String subjectType = data.get("subjectType");
        String subSemester = data.get("subSemester");
        String division = data.get("division");
        String subjectCode = data.get("subjectCode");
        String batchCount = data.get("batchCount");

        if (collegeCode == null || course == null || subjectType == null || subSemester == null || division == null || subjectCode == null) {
            Toast.makeText(context, "Missing required fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Ensure all intermediate documents are created
        //Creating COURSE table
        DocumentReference subjectDocRef = db.collection("Course")
                .document(collegeCode.trim())
                .collection("Course Name")
                .document(course.trim())
                .collection("Year")
                .document(year.trim())
                .collection("Semester")
                .document("sem " + subSemester.trim())
                .collection("Subject type")
                .document(subjectType.trim())
                .collection("Divisions")
                .document(division.trim())
                .collection("Subject Code")
                .document(subjectCode.trim());

        subjectDocRef.set(new HashMap<>()) // Create the subjectCode document
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        insertBatches(subjectDocRef, batchCount);
                        //Creating SUBJECT table
                        Toast.makeText(context, "Successfully Created Subject!...", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(context, "Failed to create subject document!...", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(context, "Failed to create subject document! " + e.getMessage(), Toast.LENGTH_SHORT).show();
                });

    }

    private void insertBatches(DocumentReference subjectDocRef, String batchCount) {
        if (!batchCount.trim().equalsIgnoreCase("No Batch") && !batchCount.isEmpty()) {
            String[] batches = batchCount.split(",");
            data.remove("batchesCount");
            for (String batch : batches) {
                data.put("batch", batch);
                DocumentReference batchRef = subjectDocRef.collection("Batches").document(batch.trim());
                batchRef.set(data)
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
//                                Toast.makeText(context, "Successfully inserted subject for batch " + batch + "!", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(e -> {
                            Toast.makeText(context, "Failed to insert subject for batch " + batch + ", try again!...", Toast.LENGTH_SHORT).show();
                        });
            }
        } else {
            data.remove("batchesCount");
            data.put("batch", "No Batch");
            DocumentReference batchRef = subjectDocRef.collection("Batches").document("No Batch");
            batchRef.set(data)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
//                                Toast.makeText(context, "Inserted No batch data", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(context, "Not able to add no batch data", Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }

    public void courseAllDetails(subjectInterface callback) {
        ArrayList<String> subjectCodes = new ArrayList<>();
        ArrayList<String> batches = new ArrayList<>();
        subjects = new ArrayList<>();
        subjectDetails = new HashMap<>();

        String collegeCode = data.get("collegeCode");
        String course = data.get("course");
        String subjectType = data.get("subjectType");
        String subSemester = data.get("subSemester");
        String division = data.get("division");

        CollectionReference subjectDocRef = db.collection("Course")
                .document(collegeCode.trim())
                .collection("Course Name")
                .document(course.trim())
                .collection("Year")
                .document(year.trim())
                .collection("Semester")
                .document(subSemester.trim())
                .collection("Subject type")
                .document(subjectType.trim())
                .collection("Divisions").document(division)
                .collection("Subject Code");
        subjectDocRef.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        for (DocumentSnapshot snapshot : queryDocumentSnapshots) {
                            if (snapshot.exists()) {
                                subjectCodes.add(snapshot.getId());
                                CollectionReference batchesRef = subjectDocRef.document(snapshot.getId())
                                        .collection("Batches");
                                batchesRef.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                            @Override
                                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                                for (DocumentSnapshot snapshot1 : queryDocumentSnapshots) {
                                                    if (snapshot1.exists()) {
                                                        batchesRef.document(snapshot1.getId())
                                                                .get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                                                    @Override
                                                                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                                                                        if (documentSnapshot.exists()) {
                                                                            if (documentSnapshot.getString("facultyName").equals(data.get("facultyName"))) {
                                                                                String collegeCode = documentSnapshot.getString("collegeCode");
                                                                                String course = documentSnapshot.getString("course");
                                                                                String subjectType = documentSnapshot.getString("subjectType");
                                                                                String subSemester = documentSnapshot.getString("subSemester");
                                                                                String division = documentSnapshot.getString("division");
                                                                                String facultyName = documentSnapshot.getString("facultyName");
                                                                                String subject = documentSnapshot.getString("subject");
                                                                                String subjectCode = documentSnapshot.getString("subjectCode");
                                                                                String batches = documentSnapshot.getString("batch");
                                                                                SubjectModal modal = new SubjectModal(batches, collegeCode, course, division, facultyName, subSemester, subject, subjectCode, subjectType);
                                                                                subjects.add(modal);
                                                                                callback.getSubjectDetails(subjects);
                                                                                subjectDetails.put(snapshot.getId(), modal);
//                                                                                Toast.makeText(context, "Batches " + modal.getBatches(), Toast.LENGTH_SHORT).show();
                                                                            }
                                                                        } else {
                                                                            Toast.makeText(context, "Empty fields", Toast.LENGTH_SHORT).show();
                                                                        }
                                                                    }
                                                                });
                                                    } else {
                                                        Toast.makeText(context, "Empty Snapshot1", Toast.LENGTH_SHORT).show();
                                                    }
                                                }
                                            }
                                        })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {

                                            }
                                        });
                            } else {
                                Toast.makeText(context, "Snapshot empty", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(context, "Failed to extract the value", Toast.LENGTH_SHORT).show();
                    }
                });

    }

    private String getCurrentYr() {
        Calendar calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR);
        return String.valueOf(currentYear);
    }


}