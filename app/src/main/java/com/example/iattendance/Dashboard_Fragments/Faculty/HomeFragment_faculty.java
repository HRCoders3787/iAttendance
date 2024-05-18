package com.example.iattendance.Dashboard_Fragments.Faculty;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.iattendance.Adapters.Subjects.subjectAdapter.RecyclerAdapter;
import com.example.iattendance.R;
import com.example.iattendance.Utils.Admin.SessionManager;
import com.example.iattendance.Utils.Faculty.FacultySessionManager;
import com.example.iattendance.Utils.Subjects.db.CourseDb;
import com.example.iattendance.Utils.Subjects.db.SubjectModal;
import com.example.iattendance.Utils.Subjects.db.subjectInterface;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
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
    public TextView faculty_name, faculty_coll_code, first_letter;
    FacultySessionManager facultySession;
    CourseDb courseDb;
    HashMap<String, String> passedValue;
    RecyclerAdapter subjectAdapter;
    RecyclerView rv_parent;
    String divisionItem[] = {"A", "B"};
    String semItem[] = {"sem 1", "sen 2"};
    String subTypeItem[] = {"Theory", "Practical"};
    ArrayAdapter<String> divAdapterItem;
    ArrayAdapter<String> semAdapterItem;
    ArrayAdapter<String> subjectTypeAdapterItem;

    HashMap<String, String> facultyMember;
    AutoCompleteTextView selectDivision, selectSubType;
    String selectedDiv = "A", selectedSubType = "Practical";
//    ArrayList<SubjectModal> subjectData;


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


        add_subject_fab.setOnClickListener(v ->
        {
            startActivity(new Intent(getContext(), FacultyAddSubject.class));
//            courseDb.courseAllDetails();
        });

        selectDivision.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedDiv = parent.getItemAtPosition(position).toString();
//                Toast.makeText(getContext(), "Selected value " + selectedValue, Toast.LENGTH_SHORT).show();
                passedValue = new HashMap<>();
                passedValue.put("facultyName", facultyMember.get(FacultySessionManager.KEY_FC_NAME));
                passedValue.put("division", selectedDiv);
                passedValue.put("subSemester", "sem 1");
                passedValue.put("course", "MCA");
                passedValue.put("subjectType", selectedSubType);
                passedValue.put("collegeCode", facultyMember.get(FacultySessionManager.KEY_FC_ID));

                courseDb = new CourseDb(getContext(), passedValue);
                courseDb.courseAllDetails(new subjectInterface() {
                    @Override
                    public void getSubjectDetails(ArrayList<SubjectModal> subjectData) {
                        if (subjectData.size() > 0) {
                            subjectAdapter = new RecyclerAdapter(subjectData, getContext());
                            rv_parent.setAdapter(subjectAdapter);
                            subjectAdapter.notifyDataSetChanged();
                        } else {
                            Toast.makeText(getContext(), "Empty subject data", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
            }
        });

        selectSubType.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedSubType = parent.getItemAtPosition(position).toString();
//                Toast.makeText(getContext(), "Selected value " + selectedValue, Toast.LENGTH_SHORT).show();
                passedValue = new HashMap<>();
                passedValue.put("facultyName", facultyMember.get(FacultySessionManager.KEY_FC_NAME));
                passedValue.put("division", selectedDiv);
                passedValue.put("subSemester", "sem 1");
                passedValue.put("course", "MCA");
                passedValue.put("subjectType", selectedSubType);
                passedValue.put("collegeCode", facultyMember.get(FacultySessionManager.KEY_FC_ID));

                courseDb = new CourseDb(getContext(), passedValue);
                courseDb.courseAllDetails(new subjectInterface() {
                    @Override
                    public void getSubjectDetails(ArrayList<SubjectModal> subjectData) {
                        if (subjectData.size() > 0) {
                            subjectAdapter = new RecyclerAdapter(subjectData, getContext());
                            rv_parent.setAdapter(subjectAdapter);
                            subjectAdapter.notifyDataSetChanged();
                        } else {
                            Toast.makeText(getContext(), "Empty subject data", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
            }
        });


        return view;
    }

    private void initializeViews(View view) {
        add_subject_fab = view.findViewById(R.id.add_subject_fab);
        faculty_name = view.findViewById(R.id.faculty_name);
        faculty_coll_code = view.findViewById(R.id.faculty_coll_code);
        first_letter = view.findViewById(R.id.first_letter);
        rv_parent = view.findViewById(R.id.rv_parent);
        selectDivision = view.findViewById(R.id.selectDivision);
        selectSubType = view.findViewById(R.id.selectSubType);


        divAdapterItem = new ArrayAdapter<>(getContext(), R.layout.list_division, divisionItem);
        selectDivision.setAdapter(divAdapterItem);

        subjectTypeAdapterItem = new ArrayAdapter<>(getContext(), R.layout.list_subject_type, subTypeItem);
        selectSubType.setAdapter(subjectTypeAdapterItem);


        facultySession = new FacultySessionManager(requireContext());
        facultyMember = facultySession.getUserDetails();
        faculty_name.setText(facultyMember.get(FacultySessionManager.KEY_FC_NAME));
        faculty_coll_code.setText(facultyMember.get(FacultySessionManager.KEY_FC_COLLEGE));
        first_letter.setText(Objects.requireNonNull(facultyMember.get(FacultySessionManager.KEY_FC_NAME)).substring(0, 1));


        passedValue = new HashMap<>();
        passedValue.put("facultyName", facultyMember.get(FacultySessionManager.KEY_FC_NAME));
        passedValue.put("division", "A");
        passedValue.put("subSemester", "sem 1");
        passedValue.put("course", "MCA");
        passedValue.put("subjectType", "Theory");
        passedValue.put("collegeCode", facultyMember.get(FacultySessionManager.KEY_FC_ID));

//        subjectData = new ArrayList<>();

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rv_parent.setLayoutManager(layoutManager);
        courseDb = new CourseDb(getContext(), passedValue);
        courseDb.courseAllDetails(new subjectInterface() {
            @Override
            public void getSubjectDetails(ArrayList<SubjectModal> subjectData) {
                if (subjectData.size() > 0) {
                    subjectAdapter = new RecyclerAdapter(subjectData, getContext());
                    rv_parent.setAdapter(subjectAdapter);
                    subjectAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(getContext(), "Empty subject data", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }
}