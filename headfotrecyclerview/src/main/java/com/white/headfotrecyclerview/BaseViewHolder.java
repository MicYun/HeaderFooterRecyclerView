package com.white.headfotrecyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by MicYun on 2018/4/11.
 */

public class BaseViewHolder<V extends View> extends RecyclerView.ViewHolder {

    public V view;

    public BaseViewHolder(V itemView) {
        super(itemView);
        view = itemView;
    }
}
