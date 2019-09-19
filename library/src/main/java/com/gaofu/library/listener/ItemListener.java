package com.gaofu.library.listener;

import android.view.View;

/**
 * @author Gaofu
 * Time 2019-09-19 15:23
 * desc item 点击、长按监听接口
 */
public interface ItemListener<T> {

    /**
     * item 点击事件监听
     *
     * @param view
     * @param entity
     * @param position
     */
    void onItemClick(View view, T entity, int position);

    /**
     * item 长按事件监听
     *
     * @param view
     * @param entity
     * @param position
     * @return
     */
    boolean onItemLongClick(View view, T entity, int position);

}
