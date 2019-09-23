package com.gaofu.library.base;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gaofu.library.holder.RViewHolder;
import com.gaofu.library.listener.ItemListener;
import com.gaofu.library.manager.RViewItemManager;
import com.gaofu.library.model.RViewItem;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Gaofu
 * Time 2019-09-19 15:26
 */
public class RViewAdapter<T> extends RecyclerView.Adapter<RViewHolder> {

    /**
     * item 类型管理器
     */
    private RViewItemManager<T> itemStyle;
    /**
     * item 点击事件监听
     */
    private ItemListener<T>     itemListener;
    /**
     * 数据源
     */
    private List<T>             datas;

    /**
     * 添加数据集合
     *
     * @param datas
     */
    public void addDatas(List<T> datas) {
        if (null == datas) {
            return;
        }
        this.datas.addAll(datas);
        notifyDataSetChanged();
    }

    /**
     * 修改整个数据集合
     *
     * @param datas
     */
    public void updateDatas(List<T> datas) {
        if (null == datas) {
            return;
        }
        this.datas = datas;
        notifyDataSetChanged();
    }

    /**
     * 单一布局
     *
     * @param datas
     */
    public RViewAdapter(List<T> datas) {
        this.datas = datas;
        if (null == datas) {
            this.datas = new ArrayList<>();
        }
        itemStyle = new RViewItemManager<>();
    }

    /**
     * 嵌套(多样式布局)
     *
     * @param datas
     */
    public RViewAdapter(List<T> datas, RViewItem<T> item) {
        this.datas = datas;
        if (null == datas) {
            this.datas = new ArrayList<>();
        }
        itemStyle = new RViewItemManager<>();
        // 将一种新的 item 类型加入到多样式集合中
        addItemStyles(item);
    }

    public void addItemStyles(RViewItem<T> item) {
        itemStyle.addStyles(item);
    }

    /**
     * 是否有多样式 RViewItem
     *
     * @return
     */
    private boolean hasMultiStyle() {
        return null != itemStyle && itemStyle.getItemViewStylesCount() > 0;
    }

    @Override
    public int getItemViewType(int position) {
        // 如果有多样式,需要判断分开加载
        if (hasMultiStyle()) {
            return itemStyle.getItemViewType(datas.get(position), position);
        }
        return super.getItemViewType(position);
    }

    @NonNull
    @Override
    public RViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // 根据返回的 viewType,从集合中得到 RViewItem
        RViewItem item = itemStyle.getRViewItem(viewType);
        // 获取对象的属性
        int layoutId = item.getItemLayout();
        // 创建 ViewHolder 对象
        RViewHolder holder = RViewHolder.createViewHolder(parent.getContext(), parent, layoutId);
        // 拦截点击事件
        if (item.openClick()) {
            setListener(holder);
        }
        return holder;
    }

    private void setListener(final RViewHolder holder) {
        holder.getConvertView().setOnClickListener(v -> {
            if (null != itemListener) {
                int position = holder.getAdapterPosition();
                itemListener.onItemClick(v, datas.get(position), position);
            }
        });
        holder.getConvertView().setOnLongClickListener(v -> {
            if (null != itemListener) {
                int position = holder.getAdapterPosition();
                itemListener.onItemLongClick(v, datas.get(position), position);
            }
            return true;
        });
    }

    @Override
    public void onBindViewHolder(@NonNull RViewHolder holder, int position) {
        convert(holder, datas.get(position));
    }

    private void convert(RViewHolder holder, T entity) {
        itemStyle.convert(holder, entity, holder.getAdapterPosition());
    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }

    public void setItemListener(ItemListener<T> itemListener) {
        this.itemListener = itemListener;
    }

}
