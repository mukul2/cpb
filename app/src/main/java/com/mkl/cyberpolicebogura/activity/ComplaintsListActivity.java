package com.mkl.cyberpolicebogura.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.mkl.cyberpolicebogura.R;
import com.mkl.cyberpolicebogura.adapter.ComplaintsAdapter;
import com.mkl.cyberpolicebogura.api.Api;
import com.mkl.cyberpolicebogura.api.ApiListener;
import com.mkl.cyberpolicebogura.model.Complaints;

import java.util.ArrayList;
import java.util.List;

public class ComplaintsListActivity extends AppCompatActivity implements ApiListener.ComplaintsDownloadListener {
    RecyclerView recyclerView;
    ComplaintsAdapter mAdapter;
    List<Complaints> notificationLists=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaints_list);
        recyclerView=(RecyclerView)findViewById(R.id.recyclerView);
        mAdapter = new ComplaintsAdapter(ComplaintsListActivity.this,notificationLists);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
       /* notificationLists.add(new Complaints());
        notificationLists.add(new Complaints());
        notificationLists.add(new Complaints());
        notificationLists.add(new Complaints());
        */
        recyclerView.setAdapter(mAdapter);

        Api.getInstance().getComplaints(this);
    }

    @Override
    public void onComplaintsDownloadSuccess(List<Complaints> list) {
        notificationLists.clear();
        notificationLists.addAll(list);
        mAdapter.notifyDataSetChanged();

    }

    @Override
    public void onComplaintsDownloadFailed(String msg) {

    }
/*
    @Override
    protected void onResume() {
        super.onResume();
        Api.getInstance().getComplaints(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Api.getInstance().getComplaints(this);

    }
    */

    public void back(View view) {
        onBackPressed();
    }
}
