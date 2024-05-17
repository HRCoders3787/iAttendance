package com.example.iattendance.Utils.Subjects.db;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.iattendance.Utils.Admin.Utils;
import com.example.iattendance.Utils.Faculty.FacultySessionManager;
import com.example.iattendance.Utils.Faculty.db.InsertDbCallback;
import com.example.iattendance.Utils.Subjects.CourseAdapter;
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

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
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

    public CourseDb() {
        db = FirebaseFirestore.getInstance();
    }

    public void courseInsertDb() {
        /*String batchCount = data.get("batchCount");
        if (batchCount != null && batchCount.length() > 0) {
            String[] batches = batchCount.split(",");
            for (String batch : batches) {
                CollectionReference collectionRef = db.collection("Course")
                        .document(Objects.requireNonNull(data.get("collegeCode")).trim())
                        .collection(Objects.requireNonNull(data.get("course")).trim())
                        .document(year).collection("sem " + Objects.requireNonNull(data.get("subSemester")).trim())
                        .document(Objects.requireNonNull(data.get("subjectType")).trim())
                        .collection(Objects.requireNonNull(data.get("division")).trim())
                        .document(Objects.requireNonNull(data.get("subjectCode")).trim()).set(new HashMap<>());
                        .collection(batch.trim());

                collectionRef.add(data)
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
            CollectionReference collectionRef = db.collection("Course")
                    .document(Objects.requireNonNull(data.get("collegeCode")).trim())
                    .collection(Objects.requireNonNull(data.get("course")).trim())
                    .document(year.trim()).collection("sem " + Objects.requireNonNull(data.get("subSemester")).trim())
                    .document(Objects.requireNonNull(data.get("subjectType")).trim())
                    .collection(Objects.requireNonNull(data.get("division")).trim())
                    .document(Objects.requireNonNull(data.get("subjectCode")).trim())
                    .collection("No Batch");

            collectionRef.add(data)
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
        }*/
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
        DocumentReference subjectDocRef = db.collection("Course")
                .document(collegeCode.trim())
                .collection(course.trim())
                .document(year.trim())
                .collection("sem " + subSemester.trim())
                .document(subjectType.trim())
                .collection(division.trim())
                .document(subjectCode.trim());

        subjectDocRef.set(new HashMap<>()) // Create the subjectCode document
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        insertBatches(subjectDocRef, batchCount);
                    } else {
                        Toast.makeText(context, "Failed to create subject document!...", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(context, "Failed to create subject document! " + e.getMessage(), Toast.LENGTH_SHORT).show();
                });
//        String collegeCode = data.get("collegeCode");
//        String course = data.get("course");
//        String subjectType = data.get("subjectType");
//        String subSemester = data.get("subSemester");
//        String division = data.get("division");
//        String subjectCode = data.get("subjectCode");
//        String batchCount = data.get("batchCount");
//
//        if (collegeCode == null || course == null || subjectType == null || subSemester == null || division == null || subjectCode == null) {
//            Toast.makeText(context, "Missing required fields", Toast.LENGTH_SHORT).show();
//            return;
//        }
//
//        // Ensure all intermediate documents are created
//        db.collection("Course").document(collegeCode.trim()).set(new HashMap<>());
//        db.collection("Course").document(collegeCode.trim()).collection(course.trim()).document(year.trim()).set(new HashMap<>());
//        db.collection("Course").document(collegeCode.trim()).collection(course.trim()).document(year.trim())
//                .collection("sem " + subSemester.trim()).document(subjectType.trim()).set(new HashMap<>());
//        db.collection("Course").document(collegeCode.trim()).collection(course.trim()).document(year.trim())
//                .collection("sem " + subSemester.trim()).document(subjectType.trim()).collection(division.trim()).document(subjectCode.trim()).set(new HashMap<>());
//
//        // Insert the actual data
//        CollectionReference batchCollection = db.collection("Course")
//                .document(collegeCode.trim())
//                .collection(course.trim())
//                .document(year.trim())
//                .collection("sem " + subSemester.trim())
//                .document(subjectType.trim())
//                .collection(division.trim())
//                .document(subjectCode.trim())
//                .collection(batchCount != null && !batchCount.isEmpty() ? batchCount.trim() : "No Batch");
//
//        batchCollection.add(data)
//                .addOnCompleteListener(task -> {
//                    if (task.isSuccessful()) {
//                        Toast.makeText(context, "Successfully Inserted Subject!...", Toast.LENGTH_SHORT).show();
//                    } else {
//                        Toast.makeText(context, "Failed to insert subject!...", Toast.LENGTH_SHORT).show();
//                    }
//                })
//                .addOnFailureListener(e -> {
//                    Toast.makeText(context, "Failed on Inserting Subject, try again! " + e.getMessage(), Toast.LENGTH_SHORT).show();
//                });

//        if (Objects.requireNonNull(data.get("batchCount")).length() > 0) {
//            String[] batches = Objects.requireNonNull(data.get("batchCount")).split(",");
//            for (String batch : batches) {
//                db.collection("Course").document(Objects.requireNonNull(data.get("collegeCode")).trim())
//                        .collection(Objects.requireNonNull(data.get("course")).trim())
//                        .document(year).collection("sem " + Objects.requireNonNull(data.get("subSemester")).trim())
//                        .document(Objects.requireNonNull(data.get("subjectType")).trim())
//                        .collection(Objects.requireNonNull(data.get("division")).trim())
//                        .document(Objects.requireNonNull(data.get("subjectCode")).trim()).collection(batch.trim())
//                        .add(data)
//                        .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
//                            @Override
//                            public void onComplete(@NonNull Task<DocumentReference> task) {
//                                if (task.isSuccessful()) {
//                                    Toast.makeText(context, "Successfully Inserted Subject!...", Toast.LENGTH_SHORT).show();
//                                }
//                            }
//                        })
//                        .addOnFailureListener(new OnFailureListener() {
//                            @Override
//                            public void onFailure(@NonNull Exception e) {
//                                Toast.makeText(context, "Failed on Inserting Subject try again!...", Toast.LENGTH_SHORT).show();
//                            }
//                        });
//            }
//        } else {
//            db.collection("Course").document(Objects.requireNonNull(data.get("collegeCode")).trim())
//                    .collection(Objects.requireNonNull(data.get("course")).trim())
//                    .document(year.trim()).collection("sem " + Objects.requireNonNull(data.get("subSemester")).trim())
//                    .document(Objects.requireNonNull(data.get("subjectType")).trim())
//                    .collection(Objects.requireNonNull(data.get("division")).trim())
//                    .document(Objects.requireNonNull(data.get("subjectCode")).trim()).collection("No Batch")
//                    .add(data)
//                    .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
//                        @Override
//                        public void onComplete(@NonNull Task<DocumentReference> task) {
//                            if (task.isSuccessful()) {
//                                Toast.makeText(context, "Successfully Inserted Subject!...", Toast.LENGTH_SHORT).show();
//                            }
//                        }
//                    })
//                    .addOnFailureListener(new OnFailureListener() {
//                        @Override
//                        public void onFailure(@NonNull Exception e) {
//                            Toast.makeText(context, "Failed on Inserting Subject try again!...", Toast.LENGTH_SHORT).show();
//                        }
//                    });
//        }
    }

    private void insertBatches(DocumentReference subjectDocRef, String batchCount) {
        if (batchCount != null && !batchCount.isEmpty()) {
            String[] batches = batchCount.split(",");
            for (String batch : batches) {
                CollectionReference batchRef = subjectDocRef.collection(batch.trim());
                batchRef.add(data)
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                Toast.makeText(context, "Successfully inserted subject for batch " + batch + "!", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(e -> {
                            Toast.makeText(context, "Failed to insert subject for batch " + batch + ", try again!...", Toast.LENGTH_SHORT).show();
                        });
            }
        } else {
            Toast.makeText(context, "No batches provided", Toast.LENGTH_SHORT).show();
        }
    }

    private String getCurrentYr() {
        Calendar calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR);
        return String.valueOf(currentYear);
    }

    public void retrieveAllCourses() {
        if (data.size() > 0) {
            String collegeCode = Objects.requireNonNull(data.get("collegeCode")).trim();
            String course = Objects.requireNonNull(data.get("course")).trim();
            String semester = Objects.requireNonNull(data.get("semester")).trim();
            String division = Objects.requireNonNull(data.get("division")).trim();

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

    public void retrieveSubjects(String collegeCode, String course, String year, String semester, String subjectType, String division, RecyclerView recyclerView, Context context) {
        CollectionReference divisionRef = db.collection("Course").document(collegeCode)
                .collection(course).document(year)
                .collection("sem " + semester).document(subjectType)
                .collection(division);

        divisionRef.get().addOnSuccessListener(queryDocumentSnapshots -> {
            List<Map<String, Object>> subjectList = new ArrayList<>();
            for (DocumentSnapshot snapshot : queryDocumentSnapshots) {
                if (snapshot.exists()) {
                    Map<String, Object> subjectData = new HashMap<>();
                    subjectData.put("subjectCode", snapshot.getId());
                    subjectData.putAll(Objects.requireNonNull(snapshot.getData()));
                    subjectList.add(subjectData);
                }
            }
            CourseAdapter adapter = new CourseAdapter(context, subjectList);
            recyclerView.setAdapter(adapter);
        }).addOnFailureListener(e -> {
            Toast.makeText(context, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        });
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