package com.example.iattendance.Dashboard_Fragments.Student;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.iattendance.Dashboard.Subject_adapter;
import com.example.iattendance.Dashboard.Subject_modal;
import com.example.iattendance.R;
import com.example.iattendance.Student_Attendance_Screen.StudentAttendance;
import com.example.iattendance.Utils.Student.StudentSessionManager;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HomeFragment_student extends Fragment {
    Subject_adapter categoryAdapter;
    ArrayList<Subject_modal> categoryModalsArrList;
    TextView id, studentName;
    ArrayList<Subject_modal> subjectModalArrayList;
    RecyclerView category_recView;
    RelativeLayout animatedComponent;

    StudentSessionManager studentSession;

    private TextView activeText;
    Button btn;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public HomeFragment_student() {
        // Required empty public constructor
    }

    public static HomeFragment_student newInstance(String param1, String param2) {
        HomeFragment_student fragment = new HomeFragment_student();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home_student, container, false);

        id = view.findViewById(R.id.id);
        studentName = view.findViewById(R.id.studentName);
        btn = view.findViewById(R.id.btn);

        studentSession = new StudentSessionManager(getContext());
        HashMap<String, String> data = studentSession.getUserDetails();
//        Toast.makeText(getContext(), "Name : " + data.get("facultyName"), Toast.LENGTH_SHORT).show();
        id.setText(data.get("studentId"));
        studentName.setText(data.get("studentName"));
//        Hooks
        category_recView = view.findViewById(R.id.category_recView);
        activeText = view.findViewById(R.id.activeText);

//        Animation
        animatedComponent = view.findViewById(R.id.animatedComponent);
        animatedComponent.setOnClickListener(v -> toggleAnimation());

        categoryModalsArrList = new ArrayList<>();
        subjectModalArrayList = new ArrayList<>();

        category_recView.setLayoutManager(new LinearLayoutManager(getActivity()));
        categoryAdapter = new Subject_adapter(getActivity(), categoryModalsArrList, subjectModalArrayList);
        category_recView.setAdapter(categoryAdapter);

        // Fetch data for each category and update the adapter
        fetchDataForCategories();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), StudentAttendance.class);
                startActivity(intent);
            }
        });

        return view;
    }

    @SuppressLint("NotifyDataSetChanged")
    private void fetchDataForCategories() {
        // Initialization
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("Teaching schemes")
                .document("400607")
                .collection("MCA")
                .document("Semester 1")
                .collection("Schemes")
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {

                    List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();

                    for (DocumentSnapshot d : list) {
                        // Extract scheme category data
                        String schemeName = d.getString("Scheme name");
                        String schemeCount = d.getString("Scheme count");

                        // Create a Category_modal object and add it to the list
                        Subject_modal categoryModal = new Subject_modal();
                        categoryModalsArrList.add(categoryModal);

                        // Fetch data for each subject within the category
                        fetchSubjectsForCategory(schemeName, categoryModal);
                    }

                    // Update adapter
                    categoryAdapter.notifyDataSetChanged();
                });
    }

    @SuppressLint("NotifyDataSetChanged")
    private void fetchSubjectsForCategory(String category, Subject_modal categoryModal) {
//        Toast.makeText(getActivity(), category, Toast.LENGTH_SHORT).show();
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        // Replace "Teaching schemes" with your actual collection name
        db.collection("All subjects")
                .document("400607")
                .collection("MCA")
                .document("Semester 1")
                .collection(category)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful() && task.getResult() != null) {
                        List<DocumentSnapshot> subjectDocuments = task.getResult().getDocuments();
                        Toast.makeText((Context) getActivity(), (CharSequence) subjectDocuments, Toast.LENGTH_SHORT).show();

                        // Clear the subject list for the current category
//                        subjectModalArrayList.clear();
                        // Clear the subject list for the current category
                        subjectModalArrayList.clear();

                        for (DocumentSnapshot subjectDocument : subjectDocuments) {
                            // Assuming you have a Subject_modal constructor, adjust fields accordingly
                            Subject_modal subjectModal = new Subject_modal(
                                    subjectDocument.getString("Sub name"),
                                    subjectDocument.getString("Professor name"),
                                    subjectDocument.getString("Sub abbr"),
                                    subjectDocument.getString("Sub first letter"),
                                    subjectDocument.getString("Total attendance"),
                                    subjectDocument.getString("Attendance present"),
                                    subjectDocument.getString("Attendance percentage")
                            );
//                            Toast.makeText(getActivity(), subjectDocument.getString("Sub name"), Toast.LENGTH_SHORT).show();
                            // Add the data to the category and notify the adapter
                            subjectModalArrayList.add(subjectModal);
                        }
                        categoryAdapter.notifyDataSetChanged();
                    }
                });
    }

    private void toggleAnimation() {
        if (activeText.getVisibility() == View.VISIBLE) {
            hideText();
        } else {
            showComponents();
        }
    }

    private void hideText() {
        ViewPropertyAnimator animator = activeText.animate()
                .setInterpolator(new AccelerateDecelerateInterpolator())
                .setDuration(300)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        activeText.setAlpha(0.0f); // Reset alpha for the next animation
//                        activeText.setTranslationX(0); // Reset translation for the next animation
                        activeText.setVisibility(View.GONE);
                    }
                });

        animator.alpha(0.0f);
        animator.translationX(50);
    }

    private void showComponents() {

        ViewPropertyAnimator animator = activeText.animate()
                .setInterpolator(new AccelerateDecelerateInterpolator())
                .setDuration(300)
                .setListener(null);

        activeText.setVisibility(View.VISIBLE);
        animator.alpha(1.0f);
        animator.translationX(0);
    }
}