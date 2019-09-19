package com.gaofu.library.manager;

import androidx.collection.SparseArrayCompat;

import com.gaofu.library.holder.RViewHolder;
import com.gaofu.library.model.RViewItem;

/**
 * @author Gaofu
 * Time 2019-09-19 15:25
 * desc 条目管理类,配合 Adapter 工作
 */
public class RViewItemManager<T> {

    /**
     * key: int viewType  value: RViewItem
     */
    private SparseArrayCompat<RViewItem<T>> styles = new SparseArrayCompat<>();

    public void addStyles(RViewItem<T> item) {
        if (null != item) {
            styles.put(styles.size(), item);
        }
    }

    /**
     * 获取所有 item 样式的数量
     *
     * @return
     */
    public int getItemViewStylesCount() {
        return styles.size();
    }

    /**
     * 获取 styles 集合中的 key
     *
     * @param entity
     * @param position
     * @return
     */
    public int getItemViewType(T entity, int position) {
        // 避免开发者增删集合抛出异常
        for (int i = styles.size() - 1; i >= 0; i--) {
            RViewItem<T> item = styles.valueAt(i);
            // 校验完成,才返回 viewType 多样式
            if (item.isItemView(entity, position)) {
                return styles.keyAt(i);
            }
        }
        throw new RuntimeException("");
    }

    public RViewItem getRViewItem(int viewType) {
        return styles.get(viewType);
    }

    public void convert(RViewHolder holder, T entity, int position) {
        for (int i = 0; i < styles.size(); i++) {
            RViewItem<T> item = styles.valueAt(i);
            // 校验完成,才做数据绑定
            if (item.isItemView(entity, position)) {
                item.convert(holder, entity, position);
                return;
            }
        }
        throw new RuntimeException("");
    }

}
