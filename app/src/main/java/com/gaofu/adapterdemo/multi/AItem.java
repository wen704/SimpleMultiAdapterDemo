package com.gaofu.adapterdemo.multi;

import android.widget.TextView;

import com.gaofu.adapterdemo.R;
import com.gaofu.adapterdemo.bean.UserInfo;
import com.gaofu.library.holder.RViewHolder;
import com.gaofu.library.model.RViewItem;

/**
 * @author Gaofu
 * Time 2019-09-21 16:13
 */
public class AItem implements RViewItem<UserInfo> {

    @Override
    public int getItemLayout() {
        return R.layout.item_a;
    }

    @Override
    public boolean openClick() {
        return true;
    }

    @Override
    public boolean isItemView(UserInfo entity, int position) {
        return entity.getType() == 1;
    }

    @Override
    public void convert(RViewHolder holder, UserInfo entity, int position) {
        TextView tvAccount = holder.getView(R.id.tv_account);
        tvAccount.setText(entity.getAccount());
    }

}
