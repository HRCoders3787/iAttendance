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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.iattendance.Dashboard.Subject_adapter;
import com.example.iattendance.Dashboard.Subject_modal;
import com.example.iattendance.Dashboard_Fragments.Faculty.SubjectAdapter;
import com.example.iattendance.R;
import com.example.iattendance.Student_Attendance_Screen.StudentAttendance;
import com.example.iattendance.Utils.Faculty.FacultySessionManager;
import com.example.iattendance.Utils.Student.StudentSessionManager;
import com.example.iattendance.Utils.Subjects.db.CourseDb;
import com.example.iattendance.Utils.Subjects.db.SubjectsModel;
import com.example.iattendance.Utils.Subjects.db.subjectInterface;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HomeFragment_student extends Fragment {
    Subject_adapter categoryAdapter;

    TextView id, studentName, first_letter;
    ArrayList<Student_SubjectModal> subjectModalArrayList;
    RecyclerView studSubjectCardView;
    RelativeLayout animatedComponent;
    Spinner spinner_semesters;
    StudentSessionManager studentSession;
    HashMap<String, String> studentMember;
    ImageView empty_icon;

    private TextView activeText;


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private CourseDb courseDb;

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
        initializeViews(view);


        id.setText(studentMember.get(StudentSessionManager.KEY_ST_ID));
        studentName.setText(studentMember.get("studentName"));
        first_letter.setText(studentMember.get("studentName").toString().substring(0, 1));
//        Hooks

        ArrayList<String> semestersList = new ArrayList<>();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireActivity(), android.R.layout.simple_spinner_item, semestersList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_semesters.setAdapter(adapter);

        HashMap<String, String> data = new HashMap<>();
        data.put("collegeCode", studentMember.get(StudentSessionManager.KEY_ST_COLLEGE));
        data.put("facultyId", "FC8601");
        data.put("courseName", studentMember.get(StudentSessionManager.KEY_ST_COURSE));
        data.put("division", studentMember.get(StudentSessionManager.KEY_ST_DIV));

        courseDb = new CourseDb(getContext(), data);
        courseDb.getSemesters(new subjectInterface() {
            @Override
            public void getSemesterList(ArrayList<String> semesterList) {
                if (semesterList.size() > 0) {
                    for (String str : semesterList) {
                        if (!semestersList.contains(str)) {
                            semestersList.add(str);
                        }
                    }
                    adapter.notifyDataSetChanged();
                    // Setting default selection to the latest semester and year
                    spinner_semesters.setSelection(semestersList.size() - 1);
                } else {
                    Toast.makeText(getContext(), "Empty Semester lists received", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void getStudentSemesterList(ArrayList<Student_SubjectModal> semesterList) {

            }

            @Override
            public void getFacultyCodes(ArrayList<String> facultyCodeList) {

            }

            @Override
            public void isConfirmed(boolean val) {

            }
        });

        spinner_semesters.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedSemester = semestersList.get(position);
                String[] parts = selectedSemester.split(", ");
                String selectedYear = parts[1].trim();
                String selectedSemesterNumber = parts[0].split(" ")[1].trim();
                data.put("year", selectedYear);
                data.put("semester", selectedSemesterNumber);
                data.put("subjectType", "Practical");

                courseDb = new CourseDb(getContext(), data);
                courseDb.getStudentSubjects(new subjectInterface() {
                    @Override
                    public void getSemesterList(ArrayList<String> semesterList) {

                    }

                    @Override
                    public void getStudentSemesterList(ArrayList<Student_SubjectModal> semesterList) {
                        if (semesterList.size() > 0) {
                            updateRecyclerView(semesterList);
                        } else {
                            empty_icon.setVisibility(View.VISIBLE);
                            studSubjectCardView.setVisibility(View.GONE);
                            spinner_semesters.setVisibility(View.VISIBLE);
                        }
                    }

                    @Override
                    public void getFacultyCodes(ArrayList<String> facultyCodeList) {

                    }

                    @Override
                    public void isConfirmed(boolean val) {

                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });

        subjectModalArrayList = new ArrayList<>();

        studSubjectCardView.setLayoutManager(new LinearLayoutManager(getActivity()));
        categoryAdapter = new Subject_adapter(getActivity(), subjectModalArrayList);
        studSubjectCardView.setAdapter(categoryAdapter);

        // Fetch data for each category and update the adapter

        return view;
    }

    private void initializeViews(View view) {
        id = view.findViewById(R.id.id);
        studentName = view.findViewById(R.id.studentName);
        first_letter = view.findViewById(R.id.first_letter);
        spinner_semesters = view.findViewById(R.id.spinner_semesters);
        studSubjectCardView = view.findViewById(R.id.category_recView);
        activeText = view.findViewById(R.id.activeText);
        empty_icon = view.findViewById(R.id.empty_icon);

        studentSession = new StudentSessionManager(getContext());
        studentMember = studentSession.getUserDetails();
    }

    private void updateRecyclerView(ArrayList<Student_SubjectModal> subjectsList) {
        if (subjectsList.size() > 0) {

            categoryAdapter = new Subject_adapter(getContext(), subjectsList);
            studSubjectCardView.setAdapter(categoryAdapter);
            empty_icon.setVisibility(View.GONE);

        } else {
            empty_icon.setVisibility(View.VISIBLE);
            studSubjectCardView.setVisibility(View.GONE);
            spinner_semesters.setVisibility(View.VISIBLE);
        }
    }

//    @SuppressLint("NotifyDataSetChanged")
//    private void fetchDataForCategories() {
//        // Initialization
//        FirebaseFirestore db = FirebaseFirestore.getInstance();
//
//        db.collection("Teaching schemes")
//                .document("400607")
//                .collection("MCA")
//                .document("Semester 1")
//                .collection("Schemes")
//                .get()
//                .addOnSuccessListener(queryDocumentSnapshots -> {
//
//                    List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
//
//                    for (DocumentSnapshot d : list) {
//                        // Extract scheme category data
//                        String schemeName = d.getString("Scheme name");
//                        String schemeCount = d.getString("Scheme count");
//
//                        // Create a Category_modal object and add it to the list
//                        Subject_modal categoryModal = new Subject_modal();
//                        categoryModalsArrList.add(categoryModal);
//
//                        // Fetch data for each subject within the category
//                        fetchSubjectsForCategory(schemeName, categoryModal);
//                    }
//
//                    // Update adapter
//                    categoryAdapter.notifyDataSetChanged();
//                });
//    }
//
//    @SuppressLint("NotifyDataSetChanged")
//    private void fetchSubjectsForCategory(String category, Subject_modal categoryModal) {
////        Toast.makeText(getActivity(), category, Toast.LENGTH_SHORT).show();
//        FirebaseFirestore db = FirebaseFirestore.getInstance();
//
//        // Replace "Teaching schemes" with your actual collection name
//        db.collection("All subjects")
//                .document("400607")
//                .collection("MCA")
//                .document("Semester 1")
//                .collection(category)
//                .get()
//                .addOnCompleteListener(task -> {
//                    if (task.isSuccessful() && task.getResult() != null) {
//                        List<DocumentSnapshot> subjectDocuments = task.getResult().getDocuments();
//                        Toast.makeText((Context) getActivity(), (CharSequence) subjectDocuments, Toast.LENGTH_SHORT).show();
//
//                        // Clear the subject list for the current category
////                        subjectModalArrayList.clear();
//                        // Clear the subject list for the current category
//                        subjectModalArrayList.clear();
//
//                        for (DocumentSnapshot subjectDocument : subjectDocuments) {
//                            // Assuming you have a Subject_modal constructor, adjust fields accordingly
//                            Subject_modal subjectModal = new Subject_modal(
//                                    subjectDocument.getString("Sub name"),
//                                    subjectDocument.getString("Professor name"),
//                                    subjectDocument.getString("Sub abbr"),
//                                    subjectDocument.getString("Sub first letter"),
//                                    subjectDocument.getString("Total attendance"),
//                                    subjectDocument.getString("Attendance present"),
//                                    subjectDocument.getString("Attendance percentage")
//                            );
////                            Toast.makeText(getActivity(), subjectDocument.getString("Sub name"), Toast.LENGTH_SHORT).show();
//                            // Add the data to the category and notify the adapter
//                            subjectModalArrayList.add(subjectModal);
//                        }
//                        categoryAdapter.notifyDataSetChanged();
//                    }
//                });
//    }
//
//    private void toggleAnimation() {
//        if (activeText.getVisibility() == View.VISIBLE) {
//            hideText();
//        } else {
//            showComponents();
//        }
//    }
//
//    private void hideText() {
//        ViewPropertyAnimator animator = activeText.animate()
//                .setInterpolator(new AccelerateDecelerateInterpolator())
//                .setDuration(300)
//                .setListener(new AnimatorListenerAdapter() {
//                    @Override
//                    public void onAnimationEnd(Animator animation) {
//                        activeText.setAlpha(0.0f); // Reset alpha for the next animation
////                        activeText.setTranslationX(0); // Reset translation for the next animation
//                        activeText.setVisibility(View.GONE);
//                    }
//                });
//
//        animator.alpha(0.0f);
//        animator.translationX(50);
//    }
//
//    private void showComponents() {
//
//        ViewPropertyAnimator animator = activeText.animate()
//                .setInterpolator(new AccelerateDecelerateInterpolator())
//                .setDuration(300)
//                .setListener(null);
//
//        activeText.setVisibility(View.VISIBLE);
//        animator.alpha(1.0f);
//        animator.translationX(0);
//    }
}