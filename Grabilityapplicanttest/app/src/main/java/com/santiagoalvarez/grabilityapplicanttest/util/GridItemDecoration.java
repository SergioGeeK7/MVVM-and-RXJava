package com.santiagoalvarez.grabilityapplicanttest.util;

import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by santiagoalvarezmonsalve on 3/22/16.
 */
public class GridItemDecoration extends RecyclerView.ItemDecoration {

    private int space;

    public GridItemDecoration(int space) {
        this.space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.top =
                parent.getChildLayoutPosition(view) <= ((GridLayoutManager) parent.getLayoutManager()).getSpanCount() ?
                        space * 2 : space;
        outRect.left = space;
        outRect.right = space;
    }
}
