package com.example.iattendance.Dashboard_Fragments.Student;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.iattendance.Login.Login_screen;
import com.example.iattendance.R;
import com.example.iattendance.Utils.Student.StudentSessionManager;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SettingsFragment_student#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SettingsFragment_student extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    TextView log_out_btn;
    StudentSessionManager studSession;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SettingsFragment_student() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Settings_Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SettingsFragment_student newInstance(String param1, String param2) {
        SettingsFragment_student fragment = new SettingsFragment_student();
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
        View view = inflater.inflate(R.layout.fragment_settings_student, container, false);
        initializeViews(view);
        return view;
    }

    private void initializeViews(View view) {
        log_out_btn = view.findViewById(R.id.log_out_btn);

        studSession = new StudentSessionManager(getContext());
        log_out_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                studSession.logoutUser();
                Intent intent = new Intent(getActivity(), Login_screen.class);
                getActivity().finish();
                startActivity(intent);
            }
        });
    }
}