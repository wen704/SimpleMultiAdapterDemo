package com.gaofu.library.holder;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author Gaofu
 * Time 2019-09-19 15:14
 */
public class RViewHolder extends RecyclerView.ViewHolder {

    /**
     * 控件收集起来,作为缓存
     * View 控件的集合
     */
    private SparseArray<View> mViews;
    /**
     * 提供当前条目的 View
     */
    private View              mConvertView;

    private RViewHolder(@NonNull View itemView) {
        super(itemView);
        mConvertView = itemView;
        mViews = new SparseArray<>();
    }

    /**
     * 对外提供 api,用来创建 ViewHolder 对象
     *
     * @param context
     * @param parent
     * @param layoutId
     * @return
     */
    public static RViewHolder createViewHolder(Context context, ViewGroup parent, int layoutId) {
        View itemView = LayoutInflater.from(context).inflate(layoutId, parent, false);
        return new RViewHolder(itemView);
    }

    /**
     * 通过 viewId 获取控件
     * @param viewId
     * @param <T>
     * @return
     */
    public <T extends View> T getView(int viewId) {
        View view = mViews.get(viewId);
        // key:R.id.xxx  value:TextView
        if (null == view) {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    public View getConvertView(){
        return mConvertView;
    }

}
