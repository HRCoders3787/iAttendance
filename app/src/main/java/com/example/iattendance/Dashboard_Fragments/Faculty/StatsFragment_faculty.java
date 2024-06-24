package com.example.iattendance.Dashboard_Fragments.Faculty;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.iattendance.R;
import com.example.iattendance.Utils.Faculty.FacultySessionManager;
import com.example.iattendance.Utils.FacultySubjectInterface;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StatsFragment_faculty#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StatsFragment_faculty extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    Spinner spinnerSubject;
    FirebaseFirestore db;
    FacultySessionManager facultySession;
    HashMap<String, String> facultyMember;
    String coll_code, faculty_id, faculty_courseName;
    ArrayList<String> subjectsList;

    public StatsFragment_faculty() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment StatsFragment_faculty.
     */
    // TODO: Rename and change types and number of parameters
    public static StatsFragment_faculty newInstance(String param1, String param2) {
        StatsFragment_faculty fragment = new StatsFragment_faculty();
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
        View view = inflater.inflate(R.layout.fragment_stats_faculty, container, false);
        initializeViews(view);
        return view;
    }

    public void initializeViews(View view) {
        spinnerSubject = view.findViewById(R.id.spinnerSubject);
        facultySession = new FacultySessionManager(requireActivity());
        facultyMember = facultySession.getUserDetails();

        coll_code = facultyMember.get(FacultySessionManager.KEY_FC_COLLEGE);
        faculty_id = facultyMember.get(FacultySessionManager.KEY_FC_ID);
        faculty_courseName = facultyMember.get(FacultySessionManager.KEY_FC_COURSE);
        db = FirebaseFirestore.getInstance();
        subjectsList = new ArrayList<>();

        getSubjectsDetails(new FacultySubjectInterface() {
            @Override
            public void getSubjectCodes(ArrayList<String> subjects) {
                if (subjects.size() > 0) {
                    subjectsList = subjects;
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(requireActivity(), android.R.layout.simple_spinner_item, subjectsList);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerSubject.setAdapter(adapter);
                }
            }
        }, coll_code, faculty_id, faculty_courseName);

        spinnerSubject.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedSubject = subjectsList.get(position);
                Toast.makeText(getContext(), "Selected list " + selectedSubject, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void getSubjectsDetails(FacultySubjectInterface SubjectInterface, String coll_code, String faculty_id, String faculty_courseName) {

        db.collection("Faculty Subjects").document(coll_code).collection("Course")
                .document(faculty_courseName).collection("Year").document("2024")
                .collection("Semester").document("Semester 1").collection("Faculty code")
                .document(faculty_id).collection("Details")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        if (!queryDocumentSnapshots.isEmpty()) {
                            ArrayList<String> subjectCodes = new ArrayList<>();
                            for (DocumentSnapshot documentSnapshot : queryDocumentSnapshots.getDocuments()) {
                                subjectCodes.add(documentSnapshot.getString("subject_code"));
                            }
                            SubjectInterface.getSubjectCodes(subjectCodes);
                        } else {
                            SubjectInterface.getSubjectCodes(new ArrayList<>());
                            Toast.makeText(getContext(), "Empty query snapshot", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });
    }
}