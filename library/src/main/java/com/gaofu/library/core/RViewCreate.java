package com.gaofu.library.core;

import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.gaofu.library.base.RViewAdapter;

/**
 * @author Gaofu
 * Time 2019-09-19 16:59
 * desc 创建 RViewHelper 所需要的数据,它的实现类很方便创建 RViewHelper 对象
 */
public interface RViewCreate<T> {

    Context context();

    /**
     * 创建 SwipeRefresh 下拉
     *
     * @return
     */
    SwipeRefreshLayout createSwipeRefreshLayout();

    /**
     * 创建 RecyclerView
     *
     * @return
     */
    RecyclerView createRecyclerView();

    /**
     * 创建 RecyclerView.Adapter
     *
     * @return
     */
    RViewAdapter<T> createRecyclerViewAdapter();

    /**
     * 是否支持分页
     *
     * @return
     */
    boolean isSupportPaging();

}
