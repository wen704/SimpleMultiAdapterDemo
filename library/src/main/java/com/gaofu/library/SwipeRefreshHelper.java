package com.gaofu.library;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

/**
 * @author Gaofu
 * Time 2019-09-19 17:08
 * desc 下拉刷新的帮助类
 */
public class SwipeRefreshHelper {

    private SwipeRefreshLayout   swipeRefresh;
    private SwipeRefreshListener swipeRefreshListener;

    static SwipeRefreshHelper createSwipeRefreshHelper(SwipeRefreshLayout swipeRefresh) {
        return new SwipeRefreshHelper(swipeRefresh);
    }

    private SwipeRefreshHelper(SwipeRefreshLayout swipeRefresh) {
        this.swipeRefresh = swipeRefresh;
        init();
    }

    private void init() {
        swipeRefresh.setColorSchemeResources(android.R.color.holo_orange_dark, android.R.color.holo_green_dark, android.R.color.holo_blue_dark);
        swipeRefresh.setOnRefreshListener(() -> {
            if (null != swipeRefreshListener) {
                swipeRefreshListener.onRefresh();
            }
        });
    }

    public interface SwipeRefreshListener {
        void onRefresh();
    }

    public void setSwipeRefreshListener(SwipeRefreshListener swipeRefreshListener) {
        this.swipeRefreshListener = swipeRefreshListener;
    }
}
