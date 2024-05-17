package com.example.iattendance.Dashboard_Fragments.Faculty;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.iattendance.R;
import com.example.iattendance.Utils.Admin.SessionManager;
import com.example.iattendance.Utils.Faculty.FacultySessionManager;
import com.example.iattendance.Utils.Subjects.db.CourseDb;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment_faculty#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment_faculty extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    FloatingActionButton add_subject_fab;
    TextView faculty_name, faculty_coll_code, first_letter;
    FacultySessionManager facultySession;
    CourseDb courseDb;
    HashMap<String, String> passedValue;

    RecyclerView rv_parent;


    public HomeFragment_faculty() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static HomeFragment_faculty newInstance(String param1, String param2) {
        HomeFragment_faculty fragment = new HomeFragment_faculty();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home_faculty, container, false);

        initializeViews(view);

        add_subject_fab.setOnClickListener(v -> {
//                startActivity(new Intent(getContext(), FacultyAddSubject.class));
            courseDb.retrieveAllCourses();
        });

        return view;
    }

    private void initializeViews(View view) {
        add_subject_fab = view.findViewById(R.id.add_subject_fab);
        faculty_name = view.findViewById(R.id.faculty_name);
        faculty_coll_code = view.findViewById(R.id.faculty_coll_code);
        first_letter = view.findViewById(R.id.first_letter);
        rv_parent = view.findViewById(R.id.rv_parent);

        facultySession = new FacultySessionManager(requireContext());

        HashMap<String, String> facultyMember = facultySession.getUserDetails();
        faculty_name.setText(facultyMember.get(FacultySessionManager.KEY_FC_NAME));
        faculty_coll_code.setText(facultyMember.get(FacultySessionManager.KEY_FC_COLLEGE));
        first_letter.setText(Objects.requireNonNull(facultyMember.get(FacultySessionManager.KEY_FC_NAME)).substring(0, 1));

//        /Course/BVIMIT-5623/MCA/2024/sem 1/Practical/B/Java-78/B2/olIouHMEADcbsOBpGiS6
        passedValue = new HashMap<>();
        passedValue.put("facultyName", facultyMember.get(FacultySessionManager.KEY_FC_NAME));
//        passedValue.put("facultyName", "Nidhi");
        passedValue.put("division", "B");
        passedValue.put("semester", "sem 2");
        passedValue.put("course", "MCA");
//        passedValue.put("collegeCode", "RUIACSIT-7358");
//        passedValue.put("collegeCode", "BVIMIT-5623");
        passedValue.put("collegeCode", facultyMember.get(FacultySessionManager.KEY_FC_ID));

        courseDb = new CourseDb(getContext(), passedValue);

    }
}