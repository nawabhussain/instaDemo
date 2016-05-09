package com.nawab.develop.photogrid.utils;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by nawab.hussain on 5/6/2016.
 */
public class SpacesItemDecoration extends RecyclerView.ItemDecoration {
    private int space;

    public SpacesItemDecoration(int space) {
        this.space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view,RecyclerView parent, RecyclerView.State state) {
        outRect.set(space, space, space, space);
    }
}