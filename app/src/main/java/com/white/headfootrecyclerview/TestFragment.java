package com.white.headfootrecyclerview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.white.headfotrecyclerview.BaseRecyclerViewAdapter;
import com.white.headfotrecyclerview.HeaderAndFooterRecyclerViewAdapter;

import java.util.Arrays;
import java.util.List;

/**
 * Created by MicYun on 2018/4/11.
 */

public class TestFragment extends Fragment {

    private RecyclerView mRecyclerView;
    List<String> data = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8");

    public static TestFragment newInstance() {
        TestFragment fragment = new TestFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_test, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        RecyclerView.LayoutManager layoutManager =
                new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);

        BaseRecyclerViewAdapter innerAdapter = new MyRecyclerViewAdapter(data);
        HeaderAndFooterRecyclerViewAdapter viewAdapter = new HeaderAndFooterRecyclerViewAdapter(innerAdapter);

        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(viewAdapter);

        mRecyclerView.addItemDecoration(new MyItemDecoration());

        viewAdapter.addHeader(initHeadView());
        viewAdapter.addFooter(initFootView());
    }

    private View initFootView() {
        TextView footTv = new TextView(getContext());
        RecyclerView.LayoutParams params = new RecyclerView.LayoutParams(-1, 100);
        footTv.setLayoutParams(params);
        footTv.setBackgroundResource(R.color.color_345678);
        footTv.setText("这是一个尾部");
        footTv.setGravity(Gravity.CENTER);
        return footTv;
    }

    private View initHeadView() {
        TextView headTv = new TextView(getContext());
        RecyclerView.LayoutParams params = new RecyclerView.LayoutParams(-1, 80);
        headTv.setLayoutParams(params);
        headTv.setBackgroundResource(R.color.color_345678);
        headTv.setText("这是一个头部");
        headTv.setGravity(Gravity.CENTER);

        return headTv;
    }
}
