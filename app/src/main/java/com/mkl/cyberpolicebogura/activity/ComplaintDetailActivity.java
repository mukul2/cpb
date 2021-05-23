package com.mkl.cyberpolicebogura.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.mkl.cyberpolicebogura.R;
import com.mkl.cyberpolicebogura.adapter.GallaryAdapter;
import com.mkl.cyberpolicebogura.adapter.GallaryAdapterOnline;
import com.mkl.cyberpolicebogura.api.Api;
import com.mkl.cyberpolicebogura.api.ApiListener;
import com.mkl.cyberpolicebogura.model.Complaints;
import com.mkl.cyberpolicebogura.model.Status;
import com.mkl.cyberpolicebogura.utils.Data;
import com.mkl.cyberpolicebogura.utils.Initials;
import com.mkl.cyberpolicebogura.utils.SessionManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.mkl.cyberpolicebogura.utils.Data.USER_ID;
import static com.mkl.cyberpolicebogura.utils.Data.USER_NAME;
import static com.mkl.cyberpolicebogura.utils.Initials.changeDateformate;

public class ComplaintDetailActivity extends AppCompatActivity implements ApiListener.SeenInfoChangeListener{
    GallaryAdapterOnline mAdapter;
    List<String> image_list = new ArrayList<>();
    TextView tv_department,tv_time,tv_detail,tv_sender_location,tv_seen_status,tv_type;
    String SEEN="1";
    String UNSEEN="0";
    ProgressDialog progressDialog;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint_detail);
        setProgresDialog(this);
        sessionManager=new SessionManager(this);
        USER_ID=sessionManager.getUserId();
        USER_NAME=sessionManager.getUserName();
        tv_department=(TextView)findViewById(R.id.tv_department);
        tv_type=(TextView)findViewById(R.id.tv_type);
        tv_sender_location=(TextView)findViewById(R.id.tv_sender_location);
        tv_seen_status=(TextView)findViewById(R.id.tv_seen_status);
        tv_time=(TextView)findViewById(R.id.tv_time);
        tv_detail=(TextView)findViewById(R.id.tv_detail);
        final Complaints complaints = Data.tempComplain;
        tv_department.setText(complaints.getComplaintSub());
        tv_sender_location.setText(complaints.getSenderLocation());
        tv_time.setText(changeDateformate(complaints.getTime()));
        tv_detail.setText(complaints.getBody());
        if (complaints.getType()!=null)tv_type.setText(complaints.getType());
        if (complaints.getSeen().equals(SEEN)){
            String value=complaints.getSeen_by();
            value = value.substring(1, value.length()-1);           //remove curly brackets
            String[] keyValuePairs = value.split(",");              //split the string to creat key-value pairs
            Map<String,String> map = new HashMap<>();

            for(String pair : keyValuePairs)                        //iterate over the pairs
            {
                String[] entry = pair.split("=");                   //split the pairs to get key and value
                map.put(entry[0].trim(), entry[1].trim());          //add them to the hashmap and trim whitespaces
            }
            if (map.get("id").equals(USER_ID)){
                tv_seen_status.setText("Mark as unseen");
                tv_seen_status.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        progressDialog.show();
                        Api.getInstance().updateSeenInfo("",complaints.getId(),"0",ComplaintDetailActivity.this);

                    }
                });
            }else {
                tv_seen_status.setVisibility(View.GONE);

            }
        }else if (complaints.getSeen().equals(UNSEEN)){
            tv_seen_status.setVisibility(View.VISIBLE);
            tv_seen_status.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Map<String,String>datamap=new HashMap<>();
                    datamap.put("id",USER_ID);
                    datamap.put("name",USER_NAME);
                   // tv_seen_status.setText(datamap.toString());
                    progressDialog.show();

                    Api.getInstance().updateSeenInfo(datamap.toString(),complaints.getId(),"1",ComplaintDetailActivity.this);
                }
            });
        }
        try {
            JSONArray array = new JSONArray(complaints.getAttachment());
            for (int p = 0; p < array.length(); p++) {
                image_list.add(array.getString(p));
            }
            mAdapter = new GallaryAdapterOnline(this, image_list);
            Initials.setGallaryADapterOnline(mAdapter, (RecyclerView) findViewById(R.id.recyclerView), this, image_list);

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    private void setProgresDialog(ComplaintDetailActivity complaintDetailActivity) {
        progressDialog=new ProgressDialog(complaintDetailActivity);
        progressDialog.setMessage("Please wait");
    }

    public void back(View view) {
        onBackPressed();
    }

    @Override
    public void onSeenInfoChangeSuccess(Status status) {
        progressDialog.dismiss();
        startActivity(new Intent(this,ComplaintsListActivity.class));
        finish();

    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this,ComplaintsListActivity.class));
        finish();
    }

    @Override
    public void onSeenInfoChangeFailed(String msg) {
        progressDialog.dismiss();
        startActivity(new Intent(this,ComplaintsListActivity.class));
        finish();

    }
}
