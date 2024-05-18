package com.example.iattendance.Adapters.Subjects.subjectAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.iattendance.R;
import com.example.iattendance.Utils.Subjects.db.SubjectModal;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder> {

    ArrayList<SubjectModal> allSubjectsData;
    Context context;

    public RecyclerAdapter(ArrayList<SubjectModal> allSubjectsData, Context context) {
        this.allSubjectsData = allSubjectsData;
        this.context = context;
    }


    @NonNull
    @Override
    public RecyclerAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RecyclerViewHolder
                (LayoutInflater.from(parent.getContext()).inflate(R.layout.parent_rv_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.RecyclerViewHolder holder, int position) {
        holder.setRecyclerData(allSubjectsData.get(position));
    }

    @Override
    public int getItemCount() {
        return allSubjectsData.size();
    }

    class RecyclerViewHolder extends RecyclerView.ViewHolder {
        TextView subjectFirst_letter, subj_name, subj_type;
        TextView div_txt, batch_txt, sem_txt, year_txt;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);

            subjectFirst_letter = itemView.findViewById(R.id.subjectFirst_letter);
            subj_name = itemView.findViewById(R.id.subj_name);
            subj_type = itemView.findViewById(R.id.subj_type);
            div_txt = itemView.findViewById(R.id.div_txt);
            batch_txt = itemView.findViewById(R.id.batch_txt);
            sem_txt = itemView.findViewById(R.id.sem_txt);
            year_txt = itemView.findViewById(R.id.year_txt);
        }

        void setRecyclerData(SubjectModal subjectModal) {

            subjectFirst_letter.setText(subjectModal.getSubject().toString().substring(0, 1));
            subj_name.setText(subjectModal.getSubject());
            div_txt.setText("Div - "+subjectModal.getDivision());
            batch_txt.setText("Batch - "+subjectModal.getBatches());
            sem_txt.setText("Sem - "+subjectModal.getSubSemester());
            year_txt.setText("2024");
            subj_type.setText(subjectModal.getSubjectType());
        }

    }
}
