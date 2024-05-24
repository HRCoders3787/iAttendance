package com.example.iattendance.Utils.Attendance.DB;

import android.content.Context;
import android.widget.Toast;

import com.example.iattendance.Utils.Attendance.Modals.StudAttendanceModal;
import com.example.iattendance.Utils.Attendance.attendanceInterface;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;

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
                                        String batch = document.getString("Batch");
                                        String facultyName = document.getString("Faculty name");
                                        String subjectName = document.getString("Subject name");
                                        String subjectType = document.getString("Subject type");
                                        String rollNo = document.getString("Roll no");

                                        StudAttendanceModal attendanceModal = new StudAttendanceModal(attendance, facultyName, rollNo, subjectName, subjectType, batch);
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