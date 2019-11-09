package com.example.seed;

import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

// Customized by SY

public class ProductRecyclerViewDecoration extends RecyclerView.ItemDecoration{
    public final int divHeight;

    public ProductRecyclerViewDecoration(int divHeight){
        this.divHeight=divHeight;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state){
        super.getItemOffsets(outRect, view, parent, state);
        outRect.top = divHeight;
    }
}
