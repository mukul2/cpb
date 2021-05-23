package com.mkl.cyberpolicebogura.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.messaging.FirebaseMessaging;
import com.mkl.cyberpolicebogura.R;
import com.mkl.cyberpolicebogura.api.Api;
import com.mkl.cyberpolicebogura.api.ApiListener;
import com.mkl.cyberpolicebogura.model.LoginResponse;
import com.mkl.cyberpolicebogura.utils.Constraints;
import com.mkl.cyberpolicebogura.utils.MyDialog;
import com.mkl.cyberpolicebogura.utils.SessionManager;

import static com.mkl.cyberpolicebogura.utils.Data.USER_ID;
import static com.mkl.cyberpolicebogura.utils.Data.USER_NAME;

public class LoginActivity extends AppCompatActivity implements ApiListener.LoginUserListener{
    EditText ed_mobile,ed_password;
    String mobile,password;
    SessionManager sessionManager;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sessionManager=new SessionManager(this);
        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Please wait");
        init_fields();
    }

    private void init_fields() {
        ed_mobile=(EditText)findViewById(R.id.ed_mobile);
        ed_password=(EditText)findViewById(R.id.ed_password);
    }

    public void login(View view) {
        mobile=ed_mobile.getText().toString().trim();
        password=ed_password.getText().toString().trim();
        if (isMobileVarified(mobile)){
            if (password.length()>0){
                progressDialog.show();
                Api.getInstance().loginUser(mobile,password,this);

            }

        }
    }

    private boolean isMobileVarified(String mobile) {
        return true;
    }

    @Override
    public void onUserLoginSuccess(LoginResponse status) {
        progressDialog.dismiss();
        if (status.getStatus()){

            sessionManager.setuserId(status.getId());
            sessionManager.setuserName(status.getName());
            sessionManager.setLoggedIn(true);
            sessionManager.set_userPhoto(status.getPhoto());
            USER_ID=status.getId();
            USER_NAME=status.getName();
            FirebaseMessaging.getInstance().subscribeToTopic("cyberPolice");
           // Toast.makeText(this, sessionManager.ge, Toast.LENGTH_SHORT).show();
            if (status.getType().equals(Constraints.SUPER_ADMIN)) {
                sessionManager.setSuperAdmin(true);
                startActivity(new Intent(this, SuperAdminActivity.class));
                finish();
            }
            else if (status.getType().equals(Constraints.ADMIN)) {
                sessionManager.setSuperAdmin(false);
                startActivity(new Intent(this, GeneralAdminActivity.class));
                finish();
            }




        }else {
          //  Toast.makeText(this, "Wrong username/password", Toast.LENGTH_SHORT).show();
            MyDialog.getInstance().with(LoginActivity.this)
                    .message("Wrong username/password")
                    .autoBack(false)
                    .autoDismiss(false)
                    .show();
        }

    }

    @Override
    public void onUserLoginFailed(String msg) {
      //  Toast.makeText(this, "Wrong username/password", Toast.LENGTH_SHORT).show();
        progressDialog.dismiss();
        MyDialog.getInstance().with(LoginActivity.this)
                .message("Network error.Please check internet")
                .autoBack(false)
                .autoDismiss(false)
                .show();

    }

    public void back(View view) {
        onBackPressed();
    }
}
