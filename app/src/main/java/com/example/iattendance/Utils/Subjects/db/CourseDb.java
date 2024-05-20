package com.example.iattendance.Utils.Subjects.db;

import android.content.Context;

import com.example.iattendance.Utils.Faculty.FacultySessionManager;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;
import java.util.HashMap;

public class CourseDb {
    Context context;
    HashMap<String, String> data;
    FirebaseFirestore db;
    FacultySessionManager facultySession;
    String year;

    public CourseDb(Context context, HashMap<String, String> data) {
        this.context = context;
        this.data = data;
        db = FirebaseFirestore.getInstance();
        facultySession = new FacultySessionManager(context);
        this.year = getCurrentYr();
    }

    private String getCurrentYr() {
        Calendar calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR);
        return String.valueOf(currentYear);
    }


}