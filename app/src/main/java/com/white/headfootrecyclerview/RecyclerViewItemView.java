package com.white.headfootrecyclerview;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by MicYun on 2018/4/11.
 */

public class RecyclerViewItemView extends ConstraintLayout {

    private TextView mTextView;

    public RecyclerViewItemView(Context context) {
        super(context);
    }

    public RecyclerViewItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RecyclerViewItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public static RecyclerViewItemView newInstance(Context context) {
        RecyclerViewItemView itemView = (RecyclerViewItemView) LayoutInflater.from(context).inflate(R.layout.layout_recyclerview_item, null);
        return itemView;
    }

    public static RecyclerViewItemView newInstance(ViewGroup parent) {
        RecyclerViewItemView itemView = (RecyclerViewItemView) LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_recyclerview_item, null);
        return itemView;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mTextView = (TextView) findViewById(R.id.tv);
    }

    public TextView getTextView() {
        return mTextView;
    }
}
