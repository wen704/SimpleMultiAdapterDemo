package com.gaofu.library.model;

import com.gaofu.library.holder.RViewHolder;

/**
 * @author Gaofu
 * Time 2019-09-19 15:08
 * desc 可以理解为 JavaBean,记录了各式各样的条目
 */
public interface RViewItem<T> {

    /**
     * 布局
     *
     * @return
     */
    int getItemLayout();

    /**
     * 是否开启点击
     *
     * @return
     */
    boolean openClick();

    /**
     * 是否为当前需要的 item 布局
     *
     * @param entity
     * @param position
     * @return
     */
    boolean isItemView(T entity, int position);

    /**
     * 将 item 的控件与数据绑定
     *
     * @param holder
     * @param entity
     * @param position
     */
    void convert(RViewHolder holder, T entity, int position);

}
