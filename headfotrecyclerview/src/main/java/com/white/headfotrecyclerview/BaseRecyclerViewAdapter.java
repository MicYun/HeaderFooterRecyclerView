package com.white.headfotrecyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MicYun on 2018/4/11.
 */

public abstract class BaseRecyclerViewAdapter<M, V extends View>
        extends RecyclerView.Adapter<BaseViewHolder<V>> {

    protected List<M> mList = new ArrayList<>();

    public BaseRecyclerViewAdapter(List<M> list) {
        if (list == null) return;
        mList.clear();
        mList.addAll(list);
    }

    public M getDataAtPosition(int position) {
        return mList.get(position);
    }

    public void setData(List<M> data) {
        if (data == null) {
            return;
        }

        mList.clear();
        mList.addAll(data);
    }

    public List<M> getData() {
        return mList;
    }

    public void addData(List<M> data) {
        if (data == null) {
            return;
        }
        mList.addAll(data);
    }

    public void addData(M m) {
        if (m == null) {
            return;
        }
        mList.add(m);
    }

    @Override
    public abstract BaseViewHolder<V> onCreateViewHolder(ViewGroup parent, int type);

    @Override
    public void onBindViewHolder(BaseViewHolder<V> holder, int position) {
        upDateItem(holder, mList.get(position));
    }

    protected abstract void upDateItem(BaseViewHolder<V> holder, M m);

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }
}
