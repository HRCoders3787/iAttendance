package com.example.iattendance.Utils.Subjects;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.iattendance.R;

import java.util.List;
import java.util.Map;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder> {
    private Context context;
    private List<Map<String, Object>> courseList;

    public CourseAdapter(Context context, List<Map<String, Object>> courseList) {
        this.context = context;
        this.courseList = courseList;
    }

    @NonNull
    @Override
    public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.parent_rv_layout, parent, false);
        return new CourseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseViewHolder holder, int position) {
        Map<String, Object> course = courseList.get(position);

        holder.subjType.setText((String) course.get("subjectType"));
        holder.subjName.setText((String) course.get("subjectName"));
        holder.classInNo.setText(String.valueOf(course.get("classCount")));
        holder.divTxt.setText((String) course.get("division"));
        holder.batchTxt.setText((String) course.get("batch"));
        holder.semTxt.setText((String) course.get("semester"));
        holder.yearTxt.setText((String) course.get("year"));
    }

    @Override
    public int getItemCount() {
        return courseList.size();
    }

    public static class CourseViewHolder extends RecyclerView.ViewHolder {
        TextView subjType, subjName, classInNo, divTxt, batchTxt, semTxt, yearTxt;
        ImageButton backBtn;

        public CourseViewHolder(@NonNull View itemView) {
            super(itemView);

            subjType = itemView.findViewById(R.id.subj_type);
            subjName = itemView.findViewById(R.id.subj_name);
            classInNo = itemView.findViewById(R.id.class_in_no);
            divTxt = itemView.findViewById(R.id.div_txt);
            batchTxt = itemView.findViewById(R.id.batch_txt);
            semTxt = itemView.findViewById(R.id.sem_txt);
            yearTxt = itemView.findViewById(R.id.year_txt);
            backBtn = itemView.findViewById(R.id.backBtn);
        }
    }
}
