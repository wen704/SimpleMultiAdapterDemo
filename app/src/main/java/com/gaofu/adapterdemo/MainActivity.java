package com.gaofu.adapterdemo;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.gaofu.library.core.RViewCreate;

public class MainActivity extends AppCompatActivity implements RViewCreate {

    private SwipeRefreshLayout srl;
    private RecyclerView       rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        srl = findViewById(R.id.swipeRefreshLayout);
        rv = findViewById(R.id.recyclerView);
    }
}
