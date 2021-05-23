package com.mkl.cyberpolicebogura.api;

import android.support.annotation.NonNull;


import com.mkl.cyberpolicebogura.model.ComplainBody;
import com.mkl.cyberpolicebogura.model.Complaints;
import com.mkl.cyberpolicebogura.model.LoginResponse;
import com.mkl.cyberpolicebogura.model.Police;
import com.mkl.cyberpolicebogura.model.Status;
import com.mkl.cyberpolicebogura.model.StatusPath;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created By TAOHID on 9/6/18.
 */
public class Api {
    String error_msg = "Could not connect to server.Please check internet connection and try again";

    private static Api dataManager = null;

    public static Api getInstance() {

        if (dataManager == null) {
            dataManager = new Api();
        }

        return dataManager;
    }

    public void getComplaints(final ApiListener.ComplaintsDownloadListener complaintsDownloadListener) {

        ApiClient.getApiInterface().getComplaints().enqueue(new Callback<List<Complaints>>() {
            @Override
            public void onResponse(@NonNull Call<List<Complaints>> call, @NonNull Response<List<Complaints>> response) {
                if (response != null) {
                    complaintsDownloadListener.onComplaintsDownloadSuccess(response.body());

                }

            }

            @Override
            public void onFailure(@NonNull Call<List<Complaints>> call, @NonNull Throwable t) {
                complaintsDownloadListener.onComplaintsDownloadFailed(error_msg);
            }
        });
    }

    public void getAllPolice(String id,final ApiListener.PoliceListDownloadListener policeListDownloadListener) {

        ApiClient.getApiInterface().getAllPolice(id).enqueue(new Callback<List<Police>>() {
            @Override
            public void onResponse(@NonNull Call<List<Police>> call, @NonNull Response<List<Police>> response) {
                if (response != null) {
                    policeListDownloadListener.onPoliceListDownloadSuccess(response.body());

                }

            }

            @Override
            public void onFailure(@NonNull Call<List<Police>> call, @NonNull Throwable t) {
                policeListDownloadListener.onPoliceListDownloadFailed(error_msg);
            }
        });
    }
    public void complain(String type,String selectedSubject, String trim, String senderLocation, String selectedDistrict, List<String> image_list, final ApiListener.ComplaintsSendListener complaintsSendListener) {
        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.setType(MultipartBody.FORM);
      if (image_list.size()>0) {
          for (String filePath : image_list) {
              File file = new File(filePath);
              RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
              builder.addFormDataPart("file_array[]", file.getName(), requestFile);
          }

          MultipartBody requestBody = builder.build();
          ApiClient.getApiInterface().sendComplain(type,selectedSubject, trim, senderLocation, selectedDistrict, requestBody).enqueue(new Callback<Status>() {
              @Override
              public void onResponse(Call<Status> call, Response<Status> response) {
                  complaintsSendListener.onComplaintsSendSuccess(response.body());
              }

              @Override
              public void onFailure(Call<Status> call, Throwable t) {
                  complaintsSendListener.onComplaintsSendFailed(t.getMessage());

              }
          });
      }else {
          ApiClient.getApiInterface().sendComplainNoAttach(type,selectedSubject, trim, senderLocation, selectedDistrict).enqueue(new Callback<Status>() {
              @Override
              public void onResponse(Call<Status> call, Response<Status> response) {
                  complaintsSendListener.onComplaintsSendSuccess(response.body());
              }

              @Override
              public void onFailure(Call<Status> call, Throwable t) {
                  complaintsSendListener.onComplaintsSendFailed(t.getMessage());

              }
          });
      }

    }

    public void PhotoUpdate(String id,String uri,final ApiListener.photoUpdateListener photoUpdateListener){
        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.setType(MultipartBody.FORM);
        File file = new File(uri);
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        builder.addFormDataPart("file", file.getName(), requestFile);
        MultipartBody requestBody = builder.build();
        ApiClient.getApiInterface().updateProfilePhoto(id,requestBody).enqueue(new Callback<StatusPath>() {
            @Override
            public void onResponse(Call<StatusPath> call, Response<StatusPath> response) {
                photoUpdateListener.onPhotoUpdateSuccess(response.body());
            }

            @Override
            public void onFailure(Call<StatusPath> call, Throwable t) {
                photoUpdateListener.onPhotoUpdateFailed(t.getLocalizedMessage());

            }
        });

    }
    public RequestBody create_form_data(String s){
      return  RequestBody.create(
                okhttp3.MultipartBody.FORM, s);

    }

    public void checkMobile(String mobile,final ApiListener.CheckMobileListener checkMobileListener) {

        ApiClient.getApiInterface().checkMobile(mobile).enqueue(new Callback<Status>() {
            @Override
            public void onResponse(@NonNull Call<Status> call, @NonNull Response<Status> response) {
                if (response != null) {
                    checkMobileListener.onMobileCheckSuccess(response.body());

                }

            }

            @Override
            public void onFailure(@NonNull Call<Status> call, @NonNull Throwable t) {
                checkMobileListener.onMobileCheckFailed(error_msg);
            }
        });
    }

    public void checkIMEI(String imei,final ApiListener.CheckIMEIListener checkIMEIListener) {

        ApiClient.getApiInterface().checkimei(imei).enqueue(new Callback<Status>() {
            @Override
            public void onResponse(@NonNull Call<Status> call, @NonNull Response<Status> response) {
                if (response != null) {
                    checkIMEIListener.onIMEICheckSuccess(response.body());

                }

            }

            @Override
            public void onFailure(@NonNull Call<Status> call, @NonNull Throwable t) {
                checkIMEIListener.onIMEICheckFailed(error_msg);
            }
        });
    }

    public void createUser(String name,String mobile,String imei,String password,final ApiListener.UserCreateListener userCreateListener) {

        ApiClient.getApiInterface().createUser(name,mobile,imei,password).enqueue(new Callback<Status>() {
            @Override
            public void onResponse(@NonNull Call<Status> call, @NonNull Response<Status> response) {
                if (response != null) {
                    userCreateListener.onUserCreateSuccess(response.body());

                }

            }

            @Override
            public void onFailure(@NonNull Call<Status> call, @NonNull Throwable t) {
                userCreateListener.onUserCreateFailed(t.getLocalizedMessage());
            }
        });
    }

    public void loginUser(String mobile,String password,final ApiListener.LoginUserListener loginUserListener) {

        ApiClient.getApiInterface().login(mobile,password).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(@NonNull Call<LoginResponse> call, @NonNull Response<LoginResponse> response) {
                if (response != null) {
                    loginUserListener.onUserLoginSuccess(response.body());

                }

            }

            @Override
            public void onFailure(@NonNull Call<LoginResponse> call, @NonNull Throwable t) {
                loginUserListener.onUserLoginFailed(t.getLocalizedMessage());
            }
        });
    }
    public void updateSeenInfo(String seen_by,String id,String seen,final ApiListener.SeenInfoChangeListener seenInfoChangeListener) {

        ApiClient.getApiInterface().update_seenBy(seen_by,id,seen).enqueue(new Callback<Status>() {
            @Override
            public void onResponse(@NonNull Call<Status> call, @NonNull Response<Status> response) {
                if (response != null) {
                    seenInfoChangeListener.onSeenInfoChangeSuccess(response.body());

                }

            }

            @Override
            public void onFailure(@NonNull Call<Status> call, @NonNull Throwable t) {
                seenInfoChangeListener.onSeenInfoChangeFailed(t.getLocalizedMessage());
            }
        });
    }
}


