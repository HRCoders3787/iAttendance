package com.example.iattendance.Dashboard_Fragments.Faculty;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.iattendance.R;
import com.example.iattendance.Utils.Subjects.db.SubjectsModel;

import java.util.ArrayList;

public class SubjectAdapter extends RecyclerView.Adapter<SubjectAdapter.ViewHolder> {
    private final ArrayList<SubjectsModel> subjectsList;
    Context context;

    public SubjectAdapter(ArrayList<SubjectsModel> subjectsList, Context context) {
        this.subjectsList = subjectsList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.parent_rv_layout, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        SubjectsModel subject = subjectsList.get(position);

        holder.subjectFirst_letter.setText(subject.getSubject().substring(0, 1));
        holder.subj_type.setText(subject.getSubject_type());
        holder.subj_name.setText(subject.getSubject());
        holder.class_in_no.setText(String.valueOf(subject.getClass_completed()));
        holder.div_txt.setText("Division: " + subject.getDivision());

        if (subject.getSubject().equals("null")) {
            holder.batch_txt.setText("");
        } else {
            holder.batch_txt.setText("Batch: " + subject.getBatch());
        }

        holder.sem_txt.setText("Semester: " + subject.getSemester());
        holder.year_txt.setText(subject.getYear());

    }

    @Override
    public int getItemCount() {
        return subjectsList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView subjectFirst_letter, subj_type, subj_name, class_in_no, div_txt, batch_txt, sem_txt, year_txt;

        public ViewHolder(View itemView) {
            super(itemView);
            subjectFirst_letter = itemView.findViewById(R.id.subjectFirst_letter);
            subj_type = itemView.findViewById(R.id.subj_type);
            subj_name = itemView.findViewById(R.id.subj_name);
            class_in_no = itemView.findViewById(R.id.class_in_no);
            div_txt = itemView.findViewById(R.id.div_txt);
            batch_txt = itemView.findViewById(R.id.batch_txt);
            sem_txt = itemView.findViewById(R.id.sem_txt);
            year_txt = itemView.findViewById(R.id.year_txt);
        }
    }
}

