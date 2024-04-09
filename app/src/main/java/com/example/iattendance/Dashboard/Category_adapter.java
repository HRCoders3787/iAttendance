package com.example.iattendance.Dashboard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.iattendance.R;

import java.util.ArrayList;

public class Category_adapter extends RecyclerView.Adapter<Category_adapter.ViewHolder> {
    private final Context context;
    ArrayList<Category_modal> parentItemsArrList;
    ArrayList<Subject_modal> childItemsArrList;

    public Category_adapter(Context context, ArrayList<Category_modal> parentItemsArrList, ArrayList<Subject_modal> childItemsArrList) {
        this.context = context;
        this.parentItemsArrList = parentItemsArrList;
        this.childItemsArrList = childItemsArrList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.home_category_recview, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Category_modal parentItem = parentItemsArrList.get(position);

        holder.category_txt.setText(parentItem.category);
        holder.category_cnt.setText(parentItem.subj_count);

        Subject_adapter childAdapter = new Subject_adapter(context, childItemsArrList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        holder.subj_recView.setLayoutManager(linearLayoutManager);
        holder.subj_recView.setAdapter(childAdapter);

    }

    @Override
    public int getItemCount() {
        return parentItemsArrList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView category_txt, category_cnt;
        RecyclerView subj_recView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            category_txt = itemView.findViewById(R.id.category_txt);
            category_cnt = itemView.findViewById(R.id.category_cnt);
            subj_recView = itemView.findViewById(R.id.subj_recView);
        }
    }

}
