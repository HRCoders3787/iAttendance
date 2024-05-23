package com.example.iattendance.Dashboard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.iattendance.Dashboard_Fragments.Student.Student_SubjectModal;
import com.example.iattendance.R;
import com.example.iattendance.Utils.Attendance.Modals.StudAttendanceModal;
import com.example.iattendance.Utils.Subjects.db.SubjectsModel;

import java.util.ArrayList;

public class Subject_adapter extends RecyclerView.Adapter<Subject_adapter.ViewHolder> {
    private final Context context;
    ArrayList<StudAttendanceModal> subjectItemLists;

    public Subject_adapter(Context context, ArrayList<StudAttendanceModal> subjectItemLists) {
        this.context = context;

        this.subjectItemLists = subjectItemLists;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.subjects_recview, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        StudAttendanceModal parentItem = subjectItemLists.get(position);
        String abbr[] = parentItem.getSubjectName().toString().split(" ");
        String subAbbr = "";
        for (String word : abbr) {
            if (!word.equalsIgnoreCase("and")
                    && !word.equalsIgnoreCase("of") && !word.equalsIgnoreCase("the")) {
                subAbbr += word.toString().substring(0, 1).toUpperCase();
            }
        }
        abbr = parentItem.getAttendance().toString().split(" ");
        int currentAtt = Integer.parseInt(abbr[0]);
        int totalAtt = Integer.parseInt(abbr[3]);
        int perAtt = totalAtt == 0 ? 0 : currentAtt / totalAtt * 100;

        holder.subj_abbr.setText(subAbbr);
        holder.prof_name.setText(parentItem.getFacultyName());
        holder.subj_name.setText(parentItem.getSubjectName());
        holder.first_letter.setText(parentItem.getSubjectName().substring(0, 1));
        holder.present_count.setText(String.valueOf(currentAtt));
        holder.total_count.setText(String.valueOf(totalAtt));
        holder.present_percent.setText(String.valueOf(perAtt) + "%");

    }

    @Override
    public int getItemCount() {
        return subjectItemLists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView subj_abbr, subj_name, prof_name, first_letter, present_count, total_count, present_percent;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            subj_abbr = itemView.findViewById(R.id.subj_abbr);
            prof_name = itemView.findViewById(R.id.prof_name);
            subj_name = itemView.findViewById(R.id.subj_name);
            first_letter = itemView.findViewById(R.id.first_letter);
            present_count = itemView.findViewById(R.id.present_count);
            total_count = itemView.findViewById(R.id.total_count);
            present_percent = itemView.findViewById(R.id.present_percent);
        }
    }

}
