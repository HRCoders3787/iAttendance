package com.example.iattendance.Dashboard;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.iattendance.R;
import com.example.iattendance.Student_Attendance_Screen.StudentAttendance;

import java.util.ArrayList;

public class Subject_adapter extends RecyclerView.Adapter<Subject_adapter.ViewHolder> {
    private Context context;

    public Subject_adapter(Context context, ArrayList<Subject_modal> childItemArrList) {
        this.context = context;
        this.childItemArrList = childItemArrList;
    }

    ArrayList<Subject_modal> childItemArrList;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.subjects_recview, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Subject_modal childItem = childItemArrList.get(position);

        holder.subj_abbr.setText(childItem.abbr);
        holder.first_letter.setText(childItem.subj_first_Letter);
        holder.subj_name.setText(childItem.subj_name);
        holder.prof_name.setText(childItem.prof_name);
        holder.present_count.setText(childItem.present_cnt);
        holder.total_count.setText(childItem.total_cnt);
        holder.present_percent.setText(childItem.attendance_percent);

        int totalCnt = Integer.parseInt(childItem.total_cnt);
        int attendanceCnt = Integer.parseInt(childItem.present_cnt);

        // Use floating-point arithmetic to get a percentage
        float percent = (float) attendanceCnt / totalCnt;

        // Calculate the dynamic width based on the percentage and set it
        holder.percent_bar.post(() -> {
            int viewWidth = holder.percent_bar.getWidth();
            int desiredWidth = (int) (viewWidth * percent);

            ViewGroup.LayoutParams layoutParams = holder.percent_bar.getLayoutParams();
            layoutParams.width = desiredWidth;

            // Change the color based on the percentage range
            int color = getColorForPercentage(percent * 100);
            holder.percent_bar.setBackgroundColor(color);

            holder.percent_bar.setLayoutParams(layoutParams);
        });

        holder.viewSub.setOnClickListener(v -> {
            Intent intent = new Intent(context, StudentAttendance.class);
            context.startActivity(intent);
        });
    }

    // Method to get color based on percentage range
    private int getColorForPercentage(float percentage) {
        if (percentage >= 90) {
            return ContextCompat.getColor(context, R.color.green);
        } else if (percentage >= 80) {
            return ContextCompat.getColor(context, R.color.yellow);
        } else if (percentage >= 70) {
            return ContextCompat.getColor(context, R.color.orange);
        } else {
            return ContextCompat.getColor(context, R.color.red);
        }
    }

    @Override
    public int getItemCount() {
        return childItemArrList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView subj_abbr, first_letter, subj_name, prof_name, present_count, total_count, present_percent;
        LinearLayout viewSub;
        View percent_bar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            subj_abbr = itemView.findViewById(R.id.subj_abbr);
            first_letter = itemView.findViewById(R.id.first_letter);
            subj_name = itemView.findViewById(R.id.subj_name);
            prof_name = itemView.findViewById(R.id.prof_name);
            present_count = itemView.findViewById(R.id.present_count);
            total_count = itemView.findViewById(R.id.total_count);
            present_percent = itemView.findViewById(R.id.present_percent);
            viewSub = itemView.findViewById(R.id.viewSub);
            percent_bar = itemView.findViewById(R.id.percent_bar);
        }
    }
}
