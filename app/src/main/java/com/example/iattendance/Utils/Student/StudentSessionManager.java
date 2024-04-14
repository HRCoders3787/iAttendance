package com.example.iattendance.Utils.Student;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;

public class StudentSessionManager {
    public static final String PREF_NAME = "StudentSession";
    public static final String KEY_ST_ID = "studentId";
    public static final String KEY_ST_PASS = "studentPass";
    public static final String KEY_ST_PHONE = "studentPhone";

    public static final String KEY_ST_COLLEGE = "studentCollegeCode";
    public static final String KEY_ST_LOGGED_IN = "isLoggedIn";
    public static final String KEY_ST_NAME = "studentName";
    public SharedPreferences pref;
    public SharedPreferences.Editor editor;
    public Context context;

    public StudentSessionManager(Context context) {
        this.context = context;
        pref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = pref.edit();
    }

    public void createSession(String facultyId, String facultyPass, String facultyPhone, String collegeCode, String facultyName) {
//        Toast.makeText(context, "NAME : " + facultyName, Toast.LENGTH_SHORT).show();
        editor.putString(KEY_ST_ID, facultyId);
        editor.putString(KEY_ST_PASS, facultyPass);
        editor.putString(KEY_ST_PHONE, facultyPhone);
        editor.putString(KEY_ST_NAME, facultyName);
        editor.putString(KEY_ST_COLLEGE, collegeCode);

        editor.commit();
    }

    public void createLoginSession(String userId, String username) {
        editor.putBoolean(KEY_ST_LOGGED_IN, true);
        editor.putString("userId", userId);
        editor.putString("username", username);
        editor.apply();
    }

    public boolean isLoggedIn() {
        return pref.getBoolean(KEY_ST_LOGGED_IN, false);
    }

    public HashMap<String, String> getUserDetails() {
        HashMap<String, String> user = new HashMap<>();
        user.put(KEY_ST_ID, pref.getString(KEY_ST_ID, null));
        user.put(KEY_ST_PASS, pref.getString(KEY_ST_PASS, null));
        user.put(KEY_ST_PHONE, pref.getString(KEY_ST_PHONE, null));
        user.put(KEY_ST_COLLEGE, pref.getString(KEY_ST_COLLEGE, null));
        user.put(KEY_ST_NAME, pref.getString(KEY_ST_NAME, null));
        return user;
    }

    public void logoutUser() {
        editor.clear();
        editor.commit();
    }
}
