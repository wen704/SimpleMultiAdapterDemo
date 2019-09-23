package com.gaofu.adapterdemo;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.gaofu.adapterdemo.bean.UserInfo;
import com.gaofu.adapterdemo.multi.MultiAdapter;
import com.gaofu.library.RViewHelper;
import com.gaofu.library.SwipeRefreshHelper;
import com.gaofu.library.base.RViewAdapter;
import com.gaofu.library.core.RViewCreate;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Gaofu
 * Time 2019-09-21 16:00
 */
public class MultiActivity extends AppCompatActivity implements RViewCreate, SwipeRefreshHelper.SwipeRefreshListener {

    private RViewHelper<UserInfo>  helper;
    private RViewAdapter<UserInfo> adapter;
    private List<UserInfo>         datas = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        helper = new RViewHelper.Builder(this, this).build();
        initDatas();
    }

    private void initDatas() {
        new Thread(() -> {
            if (datas.isEmpty()) {
                for (int i = 0; i < 15; i++) {
                    for (int j = 0; j < 15; j++) {
                        UserInfo user = new UserInfo();
                        if (j % 15 == 1) {
                            user.setType(1);
                            user.setAccount("A" + i + "-" + j);
                        } else if (j % 15 == 2 || j % 15 == 3) {
                            user.setType(2);
                            user.setAccount("B" + i + "-" + j);
                        } else if (j % 15 == 4 || j % 15 == 5 || j % 15 == 6) {
                            user.setType(3);
                            user.setAccount("C" + i + "-" + j);
                        } else if (j % 15 == 7 || j % 15 == 8 || j % 15 == 9 || j % 15 == 10) {
                            user.setType(4);
                            user.setAccount("D" + i + "-" + j);
                        } else {
                            user.setType(5);
                            user.setAccount("E" + i + "-" + j);
                        }
                        datas.add(user);
                    }
                }
            }
            helper.notifyAdapterDataSetChanged(datas);
        }).start();
    }

    @Override
    public void onRefresh() {
        initDatas();
    }

    @Override
    public Context context() {
        return this;
    }

    @Override
    public SwipeRefreshLayout createSwipeRefreshLayout() {
        return findViewById(R.id.swipeRefreshLayout);
    }

    @Override
    public RecyclerView createRecyclerView() {
        return findViewById(R.id.recyclerView);
    }

    @Override
    public RViewAdapter createRecyclerViewAdapter() {
        adapter = new MultiAdapter(null);
        return adapter;
    }

    @Override
    public boolean isSupportPaging() {
        return true;
    }

}
