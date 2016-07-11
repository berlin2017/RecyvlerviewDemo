package com.berlin.recyvlerviewdemo;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

/**
 * Created by berlin on 2016/7/5 0005.
 */
public class MyRefreshRecyclerView extends RecyclerView {

    private  MyOnLoadMoreListener listener;
    private Context context;
    float mTotalUnconsumed = 0;

    public MyRefreshRecyclerView(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public MyRefreshRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    public MyRefreshRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;
        init();
    }
//
//    @Override
//    public boolean onTouchEvent(MotionEvent e) {
//        if (e.getAction()==MotionEvent.ACTION_MOVE&&getScrollY()==0&&listener!=null){
//            listener.onLoadMore();
//        }
//        return super.onTouchEvent(e);
//    }

    public void init() {
        addOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (listener!=null&&(!canScrollVertically(-1))) {
                    listener.onLoadMore();
                }
            }
        });
    }

    public boolean isTop() {
        if (getFirstVisiblePosition() == 0) {
            return true;
        }
        return false;
    }

    /**
     * 获取最后一条展示的位置
     *
     * @return
     */
    private int getFirstVisiblePosition() {
        int position;
        if (getLayoutManager() instanceof LinearLayoutManager) {
            position = ((LinearLayoutManager) getLayoutManager()).findFirstVisibleItemPosition();
        } else if (getLayoutManager() instanceof GridLayoutManager) {
            position = ((GridLayoutManager) getLayoutManager()).findFirstVisibleItemPosition();
        } else if (getLayoutManager() instanceof StaggeredGridLayoutManager) {
            StaggeredGridLayoutManager layoutManager = (StaggeredGridLayoutManager) getLayoutManager();
            int[] lastPositions = layoutManager.findFirstVisibleItemPositions(new int[layoutManager.getSpanCount()]);
            position = getMinPosition(lastPositions);
        } else {
            position = 0;
        }
        return position;
    }

    /**
     * 获得最小的位置
     *
     * @param positions
     * @return
     */
    private int getMinPosition(int[] positions) {
        int size = positions.length;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < size; i++) {
            min = Math.min(min, positions[i]);
        }
        return min;
    }

    public void setListener(MyOnLoadMoreListener myOnLoadMoreListener){
        listener = myOnLoadMoreListener;
    }

    public interface MyOnLoadMoreListener {
        void onLoadMore();
    }
}
