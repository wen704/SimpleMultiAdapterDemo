package com.gaofu.library;

import android.content.Context;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.gaofu.library.base.RViewAdapter;
import com.gaofu.library.core.RViewCreate;

import java.util.List;

/**
 * @author Gaofu
 * Time 2019-09-19 15:07
 * desc 辅助类
 */
public class RViewHelper<T> {

    // 上下文
    private Context                                 context;
    // 下拉控件
    private SwipeRefreshLayout                      swipeRefresh;
    // 下拉刷新的工具类
    private SwipeRefreshHelper                      swipeRefreshHelper;
    // RecyclerVIew
    private RecyclerView                            recyclerView;
    // 适配器
    private RViewAdapter<T>                         adapter;
    // 开始页码
    private int                                     startPageNumber = 1;
    // 是否支持加载更多
    private boolean                                 isSupportPaging;
    // 下拉刷新,加载更多监听
    private SwipeRefreshHelper.SwipeRefreshListener listener;
    // 当前页数
    private int                                     currentPageNum;

    private RViewHelper(Builder<T> builder) {
        this.swipeRefresh = builder.create.createSwipeRefreshLayout();
        this.recyclerView = builder.create.createRecyclerView();
        this.adapter = builder.create.createRecyclerViewAdapter();
        this.context = builder.create.context();
        this.isSupportPaging = builder.create.isSupportPaging();
        this.listener = builder.listener;

        this.currentPageNum = this.startPageNumber;
        if (null != swipeRefresh) {
            swipeRefreshHelper = SwipeRefreshHelper.createSwipeRefreshHelper(swipeRefresh);
        }
        init();
    }

    private void init() {
        // RecyclerView 初始化
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        // 下拉刷新的操作
        if (null != swipeRefreshHelper) {
            swipeRefreshHelper.setSwipeRefreshListener(() -> {
                // 如果正在刷新
                dismissSwipeRefresh();
                // 重置页码
                currentPageNum = startPageNumber;
                if (null != listener) {
                    listener.onRefresh();
                }
            });
        }
    }

    private void dismissSwipeRefresh() {
        if (null != swipeRefresh && swipeRefresh.isRefreshing()) {
            swipeRefresh.setRefreshing(false);
        }
    }

    public void notifyAdapterDataSetChanged(List<T> datas) {
        // 如果是首次加载,下拉刷新
        if (currentPageNum == startPageNumber) {
            adapter.updateDatas(datas);
        } else {
            adapter.addDatas(datas);
        }
        recyclerView.setAdapter(adapter);
        if (isSupportPaging) {
            // 分页加载,待开发
        }
    }

    public static class Builder<T> {

        //初始化接口
        private RViewCreate<T>                          create;
        private SwipeRefreshHelper.SwipeRefreshListener listener;

        public Builder(RViewCreate<T> create, SwipeRefreshHelper.SwipeRefreshListener listener) {
            this.create = create;
            this.listener = listener;
        }

        public RViewHelper build() {
            return new RViewHelper<>(this);
        }

    }

}
