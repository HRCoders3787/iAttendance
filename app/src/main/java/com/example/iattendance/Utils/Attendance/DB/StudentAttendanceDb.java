package com.example.iattendance.Utils.Attendance.DB;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.iattendance.Utils.Attendance.Modals.StudAttendanceModal;
import com.example.iattendance.Utils.Attendance.attendanceInterface;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.HashMap;

public class StudentAttendanceDb {

    private final FirebaseFirestore db;
    private final Context context;
    ArrayList<StudAttendanceModal> attendanceArrayList;


//    private boolean rollNumberFound = false;

    public StudentAttendanceDb(Context context) {
        this.context = context;
        this.db = FirebaseFirestore.getInstance();
        this.attendanceArrayList = new ArrayList<>();
    }


    public void addWifiAttendanceData(attendanceInterface attInterface, String collegeCode, String semester, String courseName,
                                      String division, String batchRange, String attDate, HashMap<String, String> mapData) {
        DocumentReference attenRef = db.collection("Wifi Attendance")
                .document(collegeCode)
                .collection("Course")
                .document(courseName)
                .collection("Semester, Year")
                .document(semester)
                .collection("Division")
                .document(division)
                .collection("Batch range")
                .document(batchRange);
        attenRef.set(new HashMap<>())
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            insertBatches(attenRef, batchRange, mapData, attDate, mapData.get("Roll no"));
                            attInterface.isCheckingAttendance(true);
                        } else {
                            Toast.makeText(context, "Failed to insert attendance", Toast.LENGTH_SHORT).show();
                            attInterface.isCheckingAttendance(false);
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                    }
                });
    }

    private void insertBatches(DocumentReference attendRef, String batchRange, HashMap<String, String> fieldData, String attDate, String roll) {
        if (!batchRange.isEmpty()) {
            String[] batches = batchRange.split("-");

            attendRef.collection("Date")
                    .document(attDate)
                    .collection("Students")
                    .document(roll)
                    .collection("Details")
                    .add(fieldData)
                    .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentReference> task) {
                            if (task.isSuccessful()) {

                            } else {
                                Toast.makeText(context, "Failed to insert batches", Toast.LENGTH_SHORT).show();
                            }
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                        }
                    });

        } else {
            Toast.makeText(context, "Empty batch range", Toast.LENGTH_SHORT).show();
        }
    }

    public void fetchAttendanceData(final String collegeCode, final String semesterYear,
                                    final String division, final int userRollNo, attendanceInterface attendanceData) {

        // Collection path
        String basePath = "Students Attendance/" + collegeCode + "/Semester, Year/" + semesterYear
                + "/Division/" + division + "/Batch range/";

        CollectionReference batchRangeCollection = db.collection(basePath);

        batchRangeCollection.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                if (task.getResult().isEmpty()) {
                    Toast.makeText(context, "No batch ranges found", Toast.LENGTH_SHORT).show();
                    return;
                }
                boolean rollNumberFound = false;
                for (QueryDocumentSnapshot batchRangeDoc : task.getResult()) {
                    String batchRange = batchRangeDoc.getId();

                    int startRoll = Integer.parseInt(batchRange.split("-")[0]);
                    int endRoll = Integer.parseInt(batchRange.split("-")[1]);

                    // Checking if the user's roll number falls within the batch range
                    if (userRollNo >= startRoll && userRollNo <= endRoll) {
                        Toast.makeText(context, "User roll number " + userRollNo + " found in range " + batchRange, Toast.LENGTH_SHORT).show();
                        rollNumberFound = true;

                        // Getting the collection reference for the user's roll number
                        CollectionReference detailsCollection = batchRangeCollection.document(batchRange)
                                .collection("Students")
                                .document(String.valueOf(userRollNo))
                                .collection("Details");

                        detailsCollection.get().addOnCompleteListener(detailsTask -> {
                            if (detailsTask.isSuccessful()) {

                                for (QueryDocumentSnapshot document : detailsTask.getResult()) {
                                    if (document.exists()) {
                                        // Retrieving the data fields from the document
                                        String attendance = document.getString("Attendance");
                                        String batch = document.getString("batch");
                                        String facultyName = document.getString("Faculty name");
                                        String subjectName = document.getString("Subject name");
                                        String subjectType = document.getString("Subject type");
                                        String rollNo = document.getString("Roll no");
                                        String subCode = document.getString("subjectCode");


                                        StudAttendanceModal attendanceModal = new StudAttendanceModal(attendance, facultyName, rollNo, subjectName, subjectType, batch, subCode);
                                        attendanceArrayList.add(attendanceModal);
                                        // Handle the retrieved data
                                        Toast.makeText(context, "Subject: " + subjectName + ", Attendance: " + attendance, Toast.LENGTH_SHORT).show();
                                    }
                                }
                                attendanceData.getStudentAttendance(attendanceArrayList);

                            } else {
                                Toast.makeText(context, "Error getting details documents: " + detailsTask.getException(), Toast.LENGTH_SHORT).show();
                                attendanceData.getStudentAttendance(new ArrayList<>());
                            }
                        });

                        // Exiting loop since the roll number was found in a batch range
                    }
                }


                if (!rollNumberFound) {
                    Toast.makeText(context, "User's roll number is not within any batch range.", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(context, "Error getting batch ranges: " + task.getException(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}