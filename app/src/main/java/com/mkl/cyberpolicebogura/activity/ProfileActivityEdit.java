package com.mkl.cyberpolicebogura.activity;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.fxn.pix.Pix;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.mkl.cyberpolicebogura.R;
import com.mkl.cyberpolicebogura.api.Api;
import com.mkl.cyberpolicebogura.api.ApiListener;
import com.mkl.cyberpolicebogura.model.Police;
import com.mkl.cyberpolicebogura.model.StatusPath;
import com.mkl.cyberpolicebogura.utils.Data;
import com.mkl.cyberpolicebogura.utils.HexagonMaskView;
import com.mkl.cyberpolicebogura.utils.MyDialog;
import com.mkl.cyberpolicebogura.utils.SessionManager;

import java.util.ArrayList;
import java.util.List;

import static com.mkl.cyberpolicebogura.utils.Data.PHOTO_BASE;
import static com.mkl.cyberpolicebogura.utils.Initials.getUserType;

public class ProfileActivityEdit extends AppCompatActivity implements ApiListener.photoUpdateListener{
    Context context=this;
    HexagonMaskView hexagonMaskView;
    ImageView cover_image;
    TextView tv_name,tv_designation;
    SessionManager sessionManager;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_edit);
        sessionManager=new SessionManager(this);
        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Please wait");
        hexagonMaskView=(HexagonMaskView)findViewById(R.id.hexa_image);
        cover_image=(ImageView)findViewById(R.id.cover_image);
        tv_name=(TextView)findViewById(R.id.tv_name);
        tv_designation=(TextView)findViewById(R.id.tv_designation);

        Glide.with(context).load(PHOTO_BASE+sessionManager.get_userPhoto()).into(hexagonMaskView);
        Glide.with(context).load(PHOTO_BASE+sessionManager.get_userPhoto()).into(cover_image);

        tv_name.setText(sessionManager.getUserName());
        if (sessionManager.isSuperAdmin()){
            tv_designation.setText("Administrator");
        }else {
            tv_designation.setText("Admin");
        }


    }
    public void changePhoto(View view) {
        Dexter.withActivity(this)
                .withPermissions(
                        Manifest.permission.CAMERA,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        // check if all permissions are granted
                        if (report.areAllPermissionsGranted()) {
                            // do you work now
                            Pix.start(ProfileActivityEdit.this,                    //Activity or Fragment Instance
                                    001,                //Request code for activity results
                                    1);

                        }

                        // check for permanent denial of any permission
                        if (report.isAnyPermissionPermanentlyDenied()) {
                            // permission is denied permenantly, navigate user to app settings
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                })
                .onSameThread()
                .check();
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == 001) {
            ArrayList<String> returnValue = data.getStringArrayListExtra(Pix.IMAGE_RESULTS);
            //  imageView.setImageURI(Uri.parse(returnValue.get(0)));
            Glide.with(context).load(returnValue.get(0)).into(hexagonMaskView);
            Glide.with(context).load(returnValue.get(0)).into(cover_image);
            progressDialog.show();
            Api.getInstance().PhotoUpdate(sessionManager.getUserId(),returnValue.get(0),this);


        }
    }
    public void back(View view) {
        onBackPressed();
    }

    @Override
    public void onPhotoUpdateSuccess(StatusPath status) {
        progressDialog.dismiss();
        if (status.getStatus()){
            sessionManager.set_userPhoto(status.getPath());
        }
    }

    @Override
    public void onPhotoUpdateFailed(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();

    }
}
