package com.example.iattendance.Dashboard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.iattendance.R;

import java.util.ArrayList;

public class Subject_adapter extends RecyclerView.Adapter<Subject_adapter.ViewHolder> {
    private final Context context;
    ArrayList<Subject_modal> parentItemsArrList;
    ArrayList<Subject_modal> childItemsArrList;

    public Subject_adapter(Context context, ArrayList<Subject_modal> parentItemsArrList, ArrayList<Subject_modal> childItemsArrList) {
        this.context = context;
        this.parentItemsArrList = parentItemsArrList;
        this.childItemsArrList = childItemsArrList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.parent_rv_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Subject_modal parentItem = parentItemsArrList.get(position);

        holder.subj_type.setText(parentItem.subj_type);
        holder.subj_name.setText(parentItem.subj_name);
        holder.class_in_no.setText(parentItem.class_in_no);
        holder.div_txt.setText(parentItem.div_txt);
        holder.batch_txt.setText(parentItem.batch_txt);
        holder.sem_txt.setText(parentItem.sem_txt);
        holder.year_txt.setText(parentItem.year_txt);
//
//        Subject_adapter childAdapter = new Subject_adapter(context, childItemsArrList);
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
//        holder.subj_recView.setLayoutManager(linearLayoutManager);
//        holder.subj_recView.setAdapter(childAdapter);

    }

    @Override
    public int getItemCount() {
        return parentItemsArrList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView subj_type, subj_name, class_in_no, div_txt, batch_txt, sem_txt, year_txt;
        RecyclerView subj_recView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

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
