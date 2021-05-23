package com.mkl.cyberpolicebogura.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.mkl.cyberpolicebogura.R;
import com.mkl.cyberpolicebogura.adapter.CyberPoliceListAdapter;
import com.mkl.cyberpolicebogura.adapter.GallaryAdapter;
import com.mkl.cyberpolicebogura.api.Api;
import com.mkl.cyberpolicebogura.api.ApiListener;
import com.mkl.cyberpolicebogura.model.Police;
import com.mkl.cyberpolicebogura.utils.Initials;
import com.mkl.cyberpolicebogura.utils.SessionManager;

import java.util.ArrayList;
import java.util.List;

public class PoliceListActivity extends AppCompatActivity implements ApiListener.PoliceListDownloadListener{
    List<Police> policeList=new ArrayList<>();
    CyberPoliceListAdapter mAdapter;
    String USER_ID;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_police_list);
        sessionManager=new SessionManager(this);
        USER_ID=sessionManager.getUserId();
        mAdapter=new CyberPoliceListAdapter(this,policeList);
        policeList.clear();
        Initials.setCyberPoliceListADapter(mAdapter, (RecyclerView) findViewById(R.id.recyclerView),this);
        Api.getInstance().getAllPolice(USER_ID,this);


    }

    @Override
    public void onPoliceListDownloadSuccess(List<Police> list) {
        policeList.clear();
        policeList.addAll(list);
        mAdapter.notifyDataSetChanged();

    }

    @Override
    public void onPoliceListDownloadFailed(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();

    }

    public void openPoliceCreate(View view) {
        startActivity(new Intent(this,AddPoliceActivity.class));
    }

    @Override
    protected void onResume() {
        super.onResume();
        policeList.clear();
        Api.getInstance().getAllPolice(USER_ID,this);


    }

    @Override
    protected void onRestart() {
        super.onRestart();
        policeList.clear();
        Api.getInstance().getAllPolice(USER_ID,this);
    }

    public void back(View view) {
        onBackPressed();
    }
}
