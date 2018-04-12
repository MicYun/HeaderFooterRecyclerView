package com.white.headfootrecyclerview;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by MicYun on 2018/4/12.
 */

public class MyItemDecoration extends RecyclerView.ItemDecoration {

    private int dividerHeight;
    private Paint dividerPaint;

    public MyItemDecoration() {
        dividerHeight = (int) DensityUtils.dp2px((float) 0.5);
        dividerPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        dividerPaint.setColor(ResourcesUtils.getColor(R.color.colorAccent));
        dividerPaint.setStrokeWidth(dividerHeight);
        dividerPaint.setStyle(Paint.Style.FILL);

    }

    /**
     * 可实现类似绘制背景的效果，内容在最上面一层（顶层）
     * @param c
     * @param parent
     * @param state
     */
    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        float starX = parent.getPaddingLeft();
        float starY;
        float stopY;

        float stopX = parent.getWidth() - parent.getPaddingRight();

        for (int i = 0; i < parent.getChildCount(); i++) {
            View view = parent.getChildAt(i);
            starY = view.getBottom() + dividerHeight;
            stopY = view.getBottom() + dividerHeight;
            c.drawLine(starX, starY, stopX, stopY, dividerPaint);
        }
    }

    /**
     * 可绘制在内容上面，覆盖内容
     * @param c
     * @param parent
     * @param state
     */
    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        /*int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View view = parent.getChildAt(i);
            if (0 == (i % 2)) {
                dividerPaint.setColor(ResourcesUtils.getColor(R.color.colorAccent));
                int left = parent.getLeft();
                int top = view.getTop();
                int right = (int) (parent.getLeft() + DensityUtils.dp2px(10));
                int bottom = view.getBottom() + dividerHeight;
                c.drawRect(new RectF(left, top, right, bottom), dividerPaint);
            } else {
                dividerPaint.setColor(ResourcesUtils.getColor(R.color.colorPrimary));
                int left = (int) (parent.getRight() - DensityUtils.dp2px(10));
                int top = view.getTop();
                int right = parent.getRight();
                int bottom = view.getBottom() + dividerHeight;
                c.drawRect(new RectF(left, top, right, bottom), dividerPaint);
            }
        }*/
    }

    /**
     * 可实现类似padding的效果
     * @param outRect
     * @param view
     * @param parent
     * @param state
     */
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.bottom = dividerHeight;
    }
}
