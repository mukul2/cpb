package com.mkl.cyberpolicebogura.activity;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.mkl.cyberpolicebogura.R;
import com.mkl.cyberpolicebogura.api.Api;
import com.mkl.cyberpolicebogura.api.ApiListener;
import com.mkl.cyberpolicebogura.model.Status;
import com.mkl.cyberpolicebogura.utils.MyDialog;

public class AddPoliceActivity extends AppCompatActivity implements ApiListener.CheckMobileListener,
    ApiListener.CheckIMEIListener,
        ApiListener.UserCreateListener {
    EditText ed_name,ed_mobile,ed_imei,ed_password;
    String name,imei,mobile,password;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_police);
        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Please wait");
        init_fields();
    }

    private void init_fields() {
        ed_name=(EditText)findViewById(R.id.ed_name);
        ed_mobile=(EditText)findViewById(R.id.ed_mobile);
        ed_imei=(EditText)findViewById(R.id.ed_imei);
        ed_password=(EditText)findViewById(R.id.ed_password);
    }

    public void createPolice(View view) {
         name=ed_name.getText().toString().trim();
         mobile=ed_mobile.getText().toString().trim();
         imei=ed_imei.getText().toString().trim();
         password=ed_password.getText().toString().trim();
        if (name.length()>0){
            if (mobile.length()>0){
                if (imei.length()>0){
                    if (password.length()>0){
                        progressDialog.show();
                        Api.getInstance().checkMobile(mobile,this);

                    }
                }
            }
        }
    }

    @Override
    public void onMobileCheckSuccess(Status status) {
        if (status.getStatus()){
            //Toast.makeText(this, "This Mobile Exists", Toast.LENGTH_SHORT).show();
            progressDialog.dismiss();

            MyDialog.getInstance().with(AddPoliceActivity.this)
                    .message("This Mobile Exists")
                    .autoBack(false)
                    .autoDismiss(false)
                    .show();
        }else {
            Api.getInstance().checkIMEI(imei,this);
        }
    }

    @Override
    public void onMobileCheckFailed(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onIMEICheckSuccess(Status status) {
        if (status.getStatus()){
           // Toast.makeText(this, "This IMEI exists", Toast.LENGTH_SHORT).show();
            progressDialog.dismiss();

            MyDialog.getInstance().with(AddPoliceActivity.this)
                    .message("This IMEI exists")
                    .autoBack(false)
                    .autoDismiss(false)
                    .show();
        }else {
            Api.getInstance().createUser(name,mobile,imei,password,this);

        }
    }

    @Override
    public void onIMEICheckFailed(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onUserCreateSuccess(Status status) {
        progressDialog.dismiss();
        if (status.getStatus()){
          //  Toast.makeText(this, "User Created Successfully", Toast.LENGTH_SHORT).show();
            MyDialog.getInstance().with(AddPoliceActivity.this)
                    .message("User Created Successfully")
                    .autoBack(true)
                    .autoDismiss(false)
                    .show();
        }else {
           // Toast.makeText(this, "Error ocured.Try again later", Toast.LENGTH_SHORT).show();
            MyDialog.getInstance().with(AddPoliceActivity.this)
                    .message("Error ocured.Try again later")
                    .autoBack(true)
                    .autoDismiss(false)
                    .show();
        }
       // onBackPressed();
    }

    @Override
    public void onUserCreateFailed(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        onBackPressed();

    }

    public void back(View view) {
        onBackPressed();
    }
}
