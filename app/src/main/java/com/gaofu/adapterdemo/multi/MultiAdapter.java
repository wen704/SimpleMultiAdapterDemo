package com.gaofu.adapterdemo.multi;

import com.gaofu.adapterdemo.bean.UserInfo;
import com.gaofu.library.base.RViewAdapter;

import java.util.List;

/**
 * @author Gaofu
 * Time 2019-09-21 16:20
 */
public class MultiAdapter extends RViewAdapter<UserInfo> {

    public MultiAdapter(List<UserInfo> datas) {
        super(datas);
        addItemStyles(new AItem());
        addItemStyles(new BItem());
        addItemStyles(new CItem());
        addItemStyles(new DItem());
        addItemStyles(new EItem());
    }

}
