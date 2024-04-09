package com.example.iattendance.Dashboard;

import android.content.Context;
import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.iattendance.R;

import java.util.Objects;

public class Custom_Item_Decoration extends RecyclerView.ItemDecoration {

    private Context context;
    private int marginTop;
    private int marginBottom;

    public Custom_Item_Decoration(Context context, int marginTop, int marginBottom) {
        this.context = context;
        this.marginTop = marginTop;
        this.marginBottom = marginBottom;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        int position = parent.getChildAdapterPosition(view);

        // Customize margins based on the position
        if (position == 0) {
            // First item
            outRect.top = context.getResources().getDimensionPixelSize(R.dimen.first_item_margin_top);
            outRect.bottom = marginBottom;
        } else if (position == Objects.requireNonNull(parent.getAdapter()).getItemCount() - 1) {
            // Last item
            outRect.top = marginTop;
            outRect.bottom = context.getResources().getDimensionPixelSize(R.dimen.last_item_margin_bottom);
        } else {
            // Middle items
            outRect.top = marginTop;
            outRect.bottom = marginBottom;
        }
    }
}