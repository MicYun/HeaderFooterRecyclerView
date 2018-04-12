package com.white.headfootrecyclerview;

import android.view.ViewGroup;

import com.white.headfotrecyclerview.BaseRecyclerViewAdapter;
import com.white.headfotrecyclerview.BaseViewHolder;

import java.util.List;

/**
 * Created by MicYun on 2018/4/11.
 */

public class MyRecyclerViewAdapter extends BaseRecyclerViewAdapter<String, RecyclerViewItemView> {

    public MyRecyclerViewAdapter(List<String> list) {
        super(list);
    }

    @Override
    public BaseViewHolder<RecyclerViewItemView> onCreateViewHolder(ViewGroup parent, int type) {
        return new BaseViewHolder<>(RecyclerViewItemView.newInstance(parent));
    }

    @Override
    protected void upDateItem(BaseViewHolder<RecyclerViewItemView> holder, String s) {
        holder.view.getTextView().setText(s);
    }
}
