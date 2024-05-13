package com.example.iattendance.Utils.Subjects.db;

import android.content.Context;

import com.example.iattendance.Utils.Admin.Utils;
import com.example.iattendance.Utils.Faculty.FacultySessionManager;
import com.example.iattendance.Utils.Faculty.db.InsertDbCallback;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Random;

public class CourseDb {
    Context context;
    HashMap<String, String> data = new HashMap<>();
    FirebaseFirestore db;
    FacultySessionManager facultySession;

    public CourseDb(Context context, HashMap<String, String> data) {
        this.context = context;
        this.data = data;
        db = FirebaseFirestore.getInstance();
        facultySession = new FacultySessionManager(context);
    }

    public void courseInsertDb(InsertDbCallback callback) {
        String subjectCode = getSubjectId();

    }

    private String getSubjectId() {
        String id = data.get("subject");
        Random random = new Random();
        int code = random.nextInt(9000) + 1000;
        id = id + code;
        return id;
    }
}
