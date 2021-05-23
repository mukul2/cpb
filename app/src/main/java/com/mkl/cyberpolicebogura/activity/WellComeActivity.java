package com.mkl.cyberpolicebogura.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.mkl.cyberpolicebogura.R;
import com.mkl.cyberpolicebogura.adapter.SlidingImage_Adapter;
import com.mkl.cyberpolicebogura.model.ImageModel;
import com.mkl.cyberpolicebogura.utils.Data;
import com.mkl.cyberpolicebogura.utils.SessionManager;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class WellComeActivity extends AppCompatActivity {
    SessionManager sessionManager;
    TextView tv_body;
    int count=0;
    ViewPager mPager;
    int currentPage = 0;
    int NUM_PAGES = 0;
    ArrayList<ImageModel> imageModelArrayList;
    Context context=this;

    int[] myImageList = new int[]{R.drawable.img};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_well_come);
        sessionManager=new SessionManager(this);
        tv_body=(TextView)findViewById(R.id.tv_1);
        if (sessionManager.getLoggedIn()){
            if (sessionManager.isSuperAdmin()){
                startActivity(new Intent(this,SuperAdminActivity.class));
            }else {
                startActivity(new Intent(this,GeneralAdminActivity.class));

            }
        }
        tv_body.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count++;
                if (count>7){
                    count=0;
                    startActivity(new Intent(WellComeActivity.this,LoginActivity.class));
                }
            }
        });
       // imageModelArrayList = new ArrayList<>();
       // imageModelArrayList = populateList();
       // init();
    }
    private ArrayList<ImageModel> populateList() {

        ArrayList<ImageModel> list = new ArrayList<>();

        for (int i = 0; i < myImageList.length; i++) {
            ImageModel imageModel = new ImageModel();
            imageModel.setImage_drawable(myImageList[i]);
            list.add(imageModel);
        }

        return list;
    }


    public void openComplaintActivity(View view) {
        startActivity(new Intent(this, SendComplaintActivity.class));
    }

    public void openAdminActivity(View view) {
        startActivity(new Intent(this,LoginActivity.class));
    }

    public void openCyberPolice(View view) {
        startActivity(new Intent(this,CyberPoliceInfoActivity.class));
    }

    public void makeCall(View view) {
        String phone = "01769693369";
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
        startActivity(intent);
    }

    public void openFacebook(View view) {
        String url = Data.FACEBOOK_LINK;
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }
}
