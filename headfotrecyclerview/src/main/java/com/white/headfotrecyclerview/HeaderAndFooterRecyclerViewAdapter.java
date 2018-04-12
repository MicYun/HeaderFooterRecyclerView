package com.white.headfotrecyclerview;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by MicYun on 2018/4/11.
 */

public class HeaderAndFooterRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_FOOTER = 0xCCCC;
    private static final List<Integer> TYPE_HEADER =
            Arrays.asList(0xAAAA, 0xAAAB, 0xAAAC, 0xAAAD, 0xAAAE, 0xAAAF, 0xAAB0, 0xAAB1);
    private static final int TYPE_EMPTY = 0xBBBB;

    public static final int STATE_NORMAL = 0xFFFF;
    public static final int STATE_EMPTY = 0xEEEE;

    private ArrayList<View> mHeaderList = new ArrayList<>();
    private View mFooterView;

    public BaseRecyclerViewAdapter mInnerAdapter;
    public BaseRecyclerViewAdapter mEmptyAdapter;

    private int mState = STATE_NORMAL;

    public HeaderAndFooterRecyclerViewAdapter(BaseRecyclerViewAdapter adapter) {
        mInnerAdapter = adapter;
    }

    public BaseRecyclerViewAdapter getInnerAdapter() {
        return mInnerAdapter;
    }

    public BaseRecyclerViewAdapter getEmptyAdapter() {
        return mEmptyAdapter;
    }

    public void showEmptyState() {
        if (mEmptyAdapter == null) {
            return;
        }
        mState = STATE_EMPTY;
        notifyDataSetChanged();
    }

    public void showNormalState() {
        if (mInnerAdapter == null) {
            return;
        }
        mState = STATE_NORMAL;
        notifyDataSetChanged();
    }

    public int getState() {
        return mState;
    }

    public void addHeader(View v) {
        if (v == null) {
            return;
        }
        if (mHeaderList.size() == TYPE_HEADER.size()) {
            throw new IllegalArgumentException("Maximum support " + TYPE_HEADER.size() + " headers");
        }
        mHeaderList.add(v);
    }

    public void addHeader(int index, View v) {
        if (v == null) {
            return;
        }
        if (mHeaderList.size() == TYPE_HEADER.size()) {
            throw new IllegalArgumentException("Maximum support " + TYPE_HEADER.size() + " headers");
        }
        if (index < 0 || index > mHeaderList.size()) {
            return;
        }
        mHeaderList.add(index, v);
    }

    public View getHeaderView(int index) {
        return mHeaderList.get(index);
    }

    public int getHeaderIndex(View v) {
        if (!mHeaderList.isEmpty()) {
            for (int i = 0; i < mHeaderList.size(); i++) {
                if (mHeaderList.get(i) == v) {
                    return i;
                }
            }
        }
        return -1;
    }

    public ArrayList<View> getHeaderView() {
        return mHeaderList;
    }

    public void addFooter(View v) {
        boolean hasFooter = (mFooterView != null);
        mFooterView = v;
        if (!hasFooter) {
            notifyItemRangeChanged(getItemCount(), 1);
        } else {
            notifyItemChanged(getItemCount() - 1, 1);
        }
    }

    public View getFooterView() {
        return mFooterView;
    }

    public void removeFooterView() {
        mFooterView = null;
        notifyItemRemoved(getItemCount() - 1);
    }

    @Override
    public int getItemViewType(int position) {
        if (mHeaderList != null && position < mHeaderList.size()) {
            return TYPE_HEADER.get(position);
        }

        if (position == getItemCount() - 1 && mFooterView != null) {
            return TYPE_FOOTER;
        }

        if (mState == STATE_NORMAL) {
            return mInnerAdapter.getItemViewType(position);
        } else if (mState == STATE_EMPTY) {
            return TYPE_EMPTY;
        }
        throw new IllegalArgumentException("Illegal view type.");
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (TYPE_HEADER.contains(viewType)) {
            View v = mHeaderList.get(getIndex(viewType));
            if (v == null) {
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_error_header, parent,
                        false);
            }
            return new BaseViewHolder<>(v);
        }

        if (viewType == TYPE_FOOTER) {
            return new BaseViewHolder<>(mFooterView);
        }

        if (viewType == TYPE_EMPTY) {
            return mEmptyAdapter.onCreateViewHolder(parent, viewType);
        }
        return mInnerAdapter.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        int viewType = getItemViewType(position);
        if (TYPE_HEADER.contains(viewType) || viewType == TYPE_FOOTER || viewType == TYPE_EMPTY) {
            return;
        }

        if (viewType == mInnerAdapter.getItemViewType(position)) {
            if (hasHeader()) {
                mInnerAdapter.onBindViewHolder((BaseViewHolder) viewHolder, position - mHeaderList.size());
            } else {
                mInnerAdapter.onBindViewHolder((BaseViewHolder) viewHolder, position);
            }
        }
    }

    @Override
    public int getItemCount() {
        int count = 0;
        if (mHeaderList != null) {
            count += mHeaderList.size();
        }

        if (mFooterView != null) {
            count += 1;
        }

        if (mState == STATE_EMPTY) {
            if (mEmptyAdapter != null) {
                count += mEmptyAdapter.getItemCount();
            }
            return count;
        } else if (mState == STATE_NORMAL) {
            return count + mInnerAdapter.getItemCount();
        }
        throw new IllegalArgumentException("Illegal state.");
    }

    @Override
    public void registerAdapterDataObserver(RecyclerView.AdapterDataObserver observer) {
        if (mInnerAdapter != null) {
            mInnerAdapter.registerAdapterDataObserver(observer);
        }
        super.registerAdapterDataObserver(observer);
    }

    @Override
    public void unregisterAdapterDataObserver(RecyclerView.AdapterDataObserver observer) {
        if (mInnerAdapter != null) {
            mInnerAdapter.unregisterAdapterDataObserver(observer);
        }
        super.unregisterAdapterDataObserver(observer);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        if (mInnerAdapter != null) {
            mInnerAdapter.onAttachedToRecyclerView(recyclerView);
        }

        // fix GridLayoutManager,header and footer span count
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            final GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
            final GridLayoutManager.SpanSizeLookup oldSpanSizeLookup = gridLayoutManager.getSpanSizeLookup();
            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    int itemType = getItemViewType(position);
                    if (itemType == TYPE_FOOTER || TYPE_HEADER.contains(itemType) || itemType == TYPE_EMPTY) {
                        return gridLayoutManager.getSpanCount();
                    }
                    if (oldSpanSizeLookup != null) {
                        return oldSpanSizeLookup.getSpanSize(position);
                    }
                    return 1;
                }
            });
        }
    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        mHeaderList.clear();
        if (mInnerAdapter != null) {
            mInnerAdapter.onDetachedFromRecyclerView(recyclerView);
        }
    }

    @Override
    public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
        if (mInnerAdapter != null) {
            mInnerAdapter.onViewAttachedToWindow(holder);
        }
        int itemType = getItemViewType(holder.getLayoutPosition());
        if (itemType == TYPE_FOOTER || TYPE_HEADER.contains(itemType) || itemType == TYPE_EMPTY) {
            View view = holder.itemView;
            ViewGroup.LayoutParams params = view.getLayoutParams();
            if (params != null && params instanceof StaggeredGridLayoutManager.LayoutParams) {
                StaggeredGridLayoutManager.LayoutParams lp = (StaggeredGridLayoutManager.LayoutParams) params;
                lp.setFullSpan(true);
                view.setLayoutParams(lp);
            }
        }
        super.onViewAttachedToWindow(holder);
    }

    private boolean hasHeader() {
        return !mHeaderList.isEmpty();
    }

    private int getIndex(int viewType) {
        int size = TYPE_HEADER.size();
        for (int i = 0; i < size; i++) {
            if (viewType == TYPE_HEADER.get(i)) {
                return i;
            }
        }
        throw new IllegalArgumentException("invalid header type.");
    }
}
