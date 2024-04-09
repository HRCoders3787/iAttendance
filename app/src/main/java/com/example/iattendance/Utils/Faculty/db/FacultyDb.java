package com.example.iattendance.Utils.Faculty.db;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.iattendance.Sign_up_Screens.Admin_signup.ModalClass;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

public class FacultyDb {
    Context context;
    HashMap<String, String> data = new HashMap<>();
    FirebaseFirestore db;
    static boolean flag = false;

    public FacultyDb(Context context, HashMap<String, String> data) {
        this.context = context;
        this.data = data;
        db = FirebaseFirestore.getInstance();
    }

    public void insertDb(InsertDbCallback callback) {

        String FcId = getFacultyId();
        db.collection("Faculty").document(data.get("collegeCode")).collection(FcId).add(data)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        callback.onInsertComplete(true);
                    } else {
                        callback.onInsertComplete(false);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        callback.onInsertComplete(false);
                    }
                });
    }

    public ArrayList<Object> getAllFacultyDb(String collegeCode, String facultyCode) {
        ArrayList<Object> list = new ArrayList<>();
        db.collection("Faculty").document().collection(collegeCode).get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        if (!queryDocumentSnapshots.isEmpty()) {
                            for (QueryDocumentSnapshot querySnapshot : queryDocumentSnapshots) {
                                list.add(querySnapshot);
                            }
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });
        return list;
    }

    public void getLoginDetails(String phoneNo, String password, InsertDbCallback listener) {
        db.collection("Login details").document(phoneNo)
                .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            Map<String, Object> data = document.getData();
                            listener.onDataRetrieval(data);
                        } else {
                            listener.onDataRetrieval(null);
                        }
                    }
                });
    }

    private String getFacultyId() {
        String id = "FC";
        Random random = new Random();
        int code = random.nextInt(9000) + 1000;
        id = id + code;
        return id;
    }


}
