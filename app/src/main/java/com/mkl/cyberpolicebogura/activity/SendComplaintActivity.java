package com.mkl.cyberpolicebogura.activity;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.fxn.pix.Pix;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.mkl.cyberpolicebogura.R;
import com.mkl.cyberpolicebogura.adapter.GallaryAdapter;
import com.mkl.cyberpolicebogura.api.Api;
import com.mkl.cyberpolicebogura.api.ApiInterface;
import com.mkl.cyberpolicebogura.api.ApiListener;
import com.mkl.cyberpolicebogura.api.ServiceGenerator;
import com.mkl.cyberpolicebogura.model.Notification;
import com.mkl.cyberpolicebogura.model.NotificationData;
import com.mkl.cyberpolicebogura.model.Status;
import com.mkl.cyberpolicebogura.utils.Data;
import com.mkl.cyberpolicebogura.utils.Initials;
import com.mkl.cyberpolicebogura.utils.MyDialog;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SendComplaintActivity extends AppCompatActivity implements ApiListener.SubjectSpinnerListener,
        ApiListener.DistrictSpinnerListener,
        ApiListener.ComplaintsSendListener,
        ApiListener.TypeSpinnerListener {
    ImageView imageView;
    RecyclerView recyclerView;
    GallaryAdapter mAdapter;
    List<String> image_list = new ArrayList<>();
    String selectedDistrict, selectedSubject, senderLocation = "No location found", selectedType;
    EditText ed_body, ed_reporter, ed_reporter1;
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_complaint);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait");
        imageView = (ImageView) findViewById(R.id.imageView);
        ed_body = (EditText) findViewById(R.id.ed_body);
        ed_reporter = (EditText) findViewById(R.id.ed_reporter);
        ed_reporter1 = (EditText) findViewById(R.id.ed_reporter1);
        mAdapter = new GallaryAdapter(this, image_list);
        Initials.setGallaryADapter(mAdapter, (RecyclerView) findViewById(R.id.recyclerView), this, image_list);
        Initials.setSubjectsSpinner((Spinner) findViewById(R.id.spinnerSubject), this, Data.getSubjects(), this);
        Initials.setDistrictSpinner((Spinner) findViewById(R.id.spinnerDistrict), this, Data.getDistricts(), this);
        Initials.setTypeSpinner((Spinner) findViewById(R.id.spinnerType), this, Data.getTypes(), this);

        //getLocationInfo();


    }


    private void getLocationInfo() {
        Dexter.withActivity(this)
                .withPermissions(
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_FINE_LOCATION)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        // check if all permissions are granted
                        if (report.areAllPermissionsGranted()) {
                            // do you work now

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

    private String getLocationAddress(double latitude, double longitude) {
        String addres = "";
        Geocoder geocoder;
        List<Address> addresses;
        geocoder = new Geocoder(this, Locale.getDefault());

        try {
            addresses = geocoder.getFromLocation(latitude, longitude, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
            String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
            String city = addresses.get(0).getLocality();
            String state = addresses.get(0).getAdminArea();
            String country = addresses.get(0).getCountryName();
            String postalCode = addresses.get(0).getPostalCode();
            String knownName = addresses.get(0).getFeatureName();
            addres = address;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return addres;
    }

    public void addAttachment(View view) {
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
                            Pix.start(SendComplaintActivity.this,                    //Activity or Fragment Instance
                                    001,                //Request code for activity results
                                    10);

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
            for (int i = 0; i < returnValue.size(); i++) {
                image_list.add(returnValue.get(i));
                mAdapter.notifyDataSetChanged();
            }

        }
    }

    @Override
    public void onDistrictClicked(int position) {
        selectedDistrict = Data.getDistricts().get(position);
        //Toast.makeText(this, selectedDistrict, Toast.LENGTH_SHORT).show();


    }

    @Override
    public void onSubjectClicked(int position) {
        selectedSubject = Data.getSubjects().get(position);
        // Toast.makeText(this, selectedSubject, Toast.LENGTH_SHORT).show();

    }

    public void sendComplaint(View view) {
        if (!selectedDistrict.equals("Select")) {
            if (!selectedType.equals("Select")) {
                if (!selectedSubject.equals("Select")) {
                    if (ed_body.getText().toString().trim().length() > 0) {
                        //send
                        if (reporterInfoGiven()) {
                            String userGivenInfo = ed_reporter.getText().toString().trim() + "  " + ed_reporter1.getText().toString().trim();

                            progressDialog.show();
                            Api.getInstance().complain(selectedType, selectedSubject, ed_body.getText().toString().trim(),
                                    userGivenInfo, selectedDistrict, image_list, this);
                        } else {
                            // Toast.makeText(this, "Reporter info issue", Toast.LENGTH_SHORT).show();


                        }


                    } else {
                        MyDialog.getInstance().with(SendComplaintActivity.this)
                                .message("বর্ণনা লিখুন")
                                .autoBack(false)
                                .autoDismiss(false)
                                .show();
                    }
                } else {
                    MyDialog.getInstance().with(SendComplaintActivity.this)
                            .message("বিষয় নির্ধারণ করুন")
                            .autoBack(false)
                            .autoDismiss(false)
                            .show();
                }
            } else {
                MyDialog.getInstance().with(SendComplaintActivity.this)
                        .message("ধরণ নির্ধারণ করুন")
                        .autoBack(false)
                        .autoDismiss(false)
                        .show();
            }
        } else {
            MyDialog.getInstance().with(SendComplaintActivity.this)
                    .message("জেলা নির্ধারণ করুন")
                    .autoBack(false)
                    .autoDismiss(false)
                    .show();
        }
    }

    private boolean reporterInfoGiven() {
        String msg_numberEror = "সঠিক মোবাইল নাম্বার লিখুন";
        String numberOrEmail = ed_reporter1.getText().toString().trim();
        boolean status = false;
        if (ed_reporter.getText().toString().trim().length() > 0) {
            if (numberOrEmail.length() > 0) {
                if (numberOrEmail.length() == 13) {
                    if (numberOrEmail.charAt(0) == '8' && numberOrEmail.charAt(1) == '8' && numberOrEmail.charAt(2) == '0'
                            && numberOrEmail.charAt(3) == '1') {
                        if (Integer.parseInt(String.valueOf(numberOrEmail.charAt(4))) > 2) {
                            status = true;
                        } else {
                            MyDialog.getInstance().with(SendComplaintActivity.this)
                                    .message(msg_numberEror)
                                    .autoBack(false)
                                    .autoDismiss(false)
                                    .show();
                        }
                    } else {
                        MyDialog.getInstance().with(SendComplaintActivity.this)
                                .message(msg_numberEror)
                                .autoBack(false)
                                .autoDismiss(false)
                                .show();
                    }
                }  else if (numberOrEmail.length() == 11) {
                    if (numberOrEmail.charAt(0) == '0'
                            && numberOrEmail.charAt(1) == '1') {
                        if (Integer.parseInt(String.valueOf(numberOrEmail.charAt(2))) > 2) {
                            status = true;
                        } else {
                            MyDialog.getInstance().with(SendComplaintActivity.this)
                                    .message(msg_numberEror)
                                    .autoBack(false)
                                    .autoDismiss(false)
                                    .show();
                        }
                    }  else {
                        MyDialog.getInstance().with(SendComplaintActivity.this)
                                .message(msg_numberEror)
                                .autoBack(false)
                                .autoDismiss(false)
                                .show();
                    }
                }else {
                    MyDialog.getInstance().with(SendComplaintActivity.this)
                            .message(msg_numberEror)
                            .autoBack(false)
                            .autoDismiss(false)
                            .show();
                }

            } else {
                MyDialog.getInstance().with(SendComplaintActivity.this)
                        .message(msg_numberEror)
                        .autoBack(false)
                        .autoDismiss(false)
                        .show();
            }
        } else {
            MyDialog.getInstance().with(SendComplaintActivity.this)
                    .message("অভিযোগকারীর নাম প্রদান করুন")
                    .autoBack(false)
                    .autoDismiss(false)
                    .show();
        }
        return status;
    }

    @Override
    public void onComplaintsSendSuccess(Status status) {
        progressDialog.dismiss();
        Toast.makeText(this, "Your complain has been submitted successfully", Toast.LENGTH_SHORT).show();
        sendNotification(selectedSubject, ed_body.getText().toString().trim());
        onBackPressed();
    }

    private void sendNotification(String trim, String trim1) {
        Notification notification = new Notification();
        notification.setBody(trim1);
        notification.setTitle(trim);
        notification.setClick_action("OPEN_ACTIVITY_1");
        NotificationData notificationData = new NotificationData();
        notificationData.setTo("/topics/cyberPolice");
        notificationData.setNotification(notification);

        ApiInterface jsonPostService = ServiceGenerator.createService(ApiInterface.class, "https://fcm.googleapis.com/fcm/");
        Call<String> call = jsonPostService.postRawJSON("key=AIzaSyAEHsEXsOjz7Xs7NKzXF-f7cWjYpLuZziI", notificationData);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                //Toast.makeText(JokesAddActivity.this, response.body(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                //  Toast.makeText(JokesAddActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }

    @Override
    public void onComplaintsSendFailed(String msg) {
        progressDialog.dismiss();
        Toast.makeText(this, "Try Again", Toast.LENGTH_SHORT).show();
        onBackPressed();

    }

    public void back(View view) {
        onBackPressed();
    }

    @Override
    public void onTypeClicked(int position) {
        selectedType = Data.getTypes().get(position);
        //Toast.makeText(this, selectedType, Toast.LENGTH_SHORT).show();
    }


}
