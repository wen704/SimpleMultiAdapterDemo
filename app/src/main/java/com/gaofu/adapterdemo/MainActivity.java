package com.gaofu.adapterdemo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.gaofu.adapterdemo.bean.UserInfo;
import com.gaofu.library.RViewHelper;
import com.gaofu.library.SwipeRefreshHelper;
import com.gaofu.library.base.RViewAdapter;
import com.gaofu.library.core.RViewCreate;
import com.gaofu.library.holder.RViewHolder;
import com.gaofu.library.listener.ItemListener;
import com.gaofu.library.model.RViewItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RViewCreate, SwipeRefreshHelper.SwipeRefreshListener {

    private SwipeRefreshLayout srl;
    private RecyclerView       rv;

    private RViewHelper helper;

    private List<UserInfo> datas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        srl = findViewById(R.id.swipeRefreshLayout);
        rv = findViewById(R.id.recyclerView);

        helper = new RViewHelper.Builder(this, this).build();

        initDatas();
    }

    private void initDatas() {
        if (datas.isEmpty()) {
            for (int i = 0; i < 100; i++) {
                datas.add(new UserInfo("Test", "123456"));
            }
        }
        helper.notifyAdapterDataSetChanged(datas);
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public Context context() {
        return this;
    }

    @Override
    public SwipeRefreshLayout createSwipeRefreshLayout() {
        return srl;
    }

    @Override
    public RecyclerView createRecyclerView() {
        return rv;
    }

    @Override
    public RViewAdapter createRecyclerViewAdapter() {
        RViewAdapter<UserInfo> adapter = new RViewAdapter(datas, new RViewItem<UserInfo>() {
            @Override
            public int getItemLayout() {
                return R.layout.item_account;
            }

            @Override
            public boolean openClick() {
                return true;
            }

            @Override
            public boolean isItemView(UserInfo entity, int position) {
                return true;
            }

            @Override
            public void convert(RViewHolder holder, UserInfo entity, int position) {
                TextView tvAccount = holder.getView(R.id.tv_account);
                tvAccount.setText(entity.getAccount());
                TextView tvPwd = holder.getView(R.id.tv_pwd);
                tvPwd.setText(entity.getPassword());
            }
        });
        adapter.setItemListener(new ItemListener<UserInfo>() {
            @Override
            public void onItemClick(View view, UserInfo entity, int position) {
                Toast.makeText(MainActivity.this, entity.getAccount() + " : " + position, Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this, MultiActivity.class));
            }

            @Override
            public boolean onItemLongClick(View view, UserInfo entity, int position) {
                return false;
            }
        });
        return adapter;
    }

    @Override
    public boolean isSupportPaging() {
        return false;
    }

    //    public void notifyAdapterDataSetChanged(List datas) {
    //        helper.notifyAdapterDataSetChanged();
    //    }

}
