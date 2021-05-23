package com.mkl.cyberpolicebogura.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.mkl.cyberpolicebogura.R;

public class CyberPoliceInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cyber_police_info);
    }

    public void back(View view) {
        onBackPressed();
    }
}
