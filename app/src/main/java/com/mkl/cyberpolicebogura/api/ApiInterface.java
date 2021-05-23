package com.mkl.cyberpolicebogura.api;


import com.mkl.cyberpolicebogura.model.ComplainBody;
import com.mkl.cyberpolicebogura.model.Complaints;
import com.mkl.cyberpolicebogura.model.LoginResponse;
import com.mkl.cyberpolicebogura.model.NotificationData;
import com.mkl.cyberpolicebogura.model.Police;
import com.mkl.cyberpolicebogura.model.Status;
import com.mkl.cyberpolicebogura.model.StatusPath;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface ApiInterface {


    @POST("get_complaints.php")
    Call<List<Complaints>> getComplaints();

    @FormUrlEncoded
    @POST("get_all_users.php")
    Call<List<Police>> getAllPolice(@Field("id") String id);


    @POST("push_complaint.php")
    Call<Status> sendComplain(@Query("type") String type,
                              @Query("complaint_sub") String selectedSubject,
                              @Query("body") String body,
                              @Query("sender_location") String senderLocation,
                              @Query("district") String selectedDistrict,
                              @Body MultipartBody requestBody);

    @POST("updateProfilePhoto.php")
    Call<StatusPath> updateProfilePhoto(@Query("id") String id,
                                        @Body MultipartBody requestBody);

    @POST("push_complaint.php")
    Call<Status> sendComplainNoAttach(@Query("type") String type,
                                      @Query("complaint_sub") String selectedSubject,
                                      @Query("body") String body,
                                      @Query("sender_location") String senderLocation,
                                      @Query("district") String selectedDistrict);

    @POST("Cell.Search.php")
    Call<String> getTrack(@Query("mcc") String mcc,
                          @Query("mnc") String mnc,
                          @Query("lac") String lac,
                          @Query("cid") String cid);

    @FormUrlEncoded
    @POST("check_mobile.php")
    Call<Status> checkMobile(@Field("mobile") String mobile);

    @FormUrlEncoded
    @POST("seen_by.php")
    Call<Status> update_seenBy(@Field("seen_by") String seen_by,
                               @Field("id") String id,
                               @Field("seen") String seen);

    @FormUrlEncoded
    @POST("check_imei.php")
    Call<Status> checkimei(@Field("imei") String mobile);

    @FormUrlEncoded
    @POST("create_user.php")
    Call<Status> createUser(@Field("name") String mobile,
                            @Field("mobile") String imei,
                            @Field("imei") String name,
                            @Field("password") String password);

    @FormUrlEncoded
    @POST("login.php")
    Call<LoginResponse> login(@Field("mobile") String imei,
                              @Field("password") String password);

    @Headers("Content-Type: application/json")
    @POST("send")
    Call<String> postRawJSON(@Header("Authorization") String s, @Body NotificationData locationPost);

/*
    @FormUrlEncoded
    @POST("SavecasePosting")
    Call<StatusMessage> casePost(@Header("Authorization") String token,
                                 @Field("district_id") String district_id,
                                 @Field("thana_id") String thana_id,
                                 @Field("case_category") String case_category,
                                 @Field("all_lawyer") String all_lawyer,
                                 @Field("selected_lawrules_id[]") String s,
                                 @Field("status") String status,
                                 @Field("case_no") String case_no,
                                 @Field("project_details") String project_details);

    @FormUrlEncoded
    @POST("SavecasePosting")
    Call<StatusMessage> updatePost(@Header("Authorization") String token,
                                   @Field("district_id") String district_id,
                                   @Field("thana_id") String thana_id,
                                   @Field("case_category") String case_category,
                                   @Field("all_lawyer") String all_lawyer,
                                   @Field("selected_lawrules_id[]") String s,
                                   @Field("status") String status,
                                   @Field("case_no") String case_no,
                                   @Field("project_details") String project_details,
                                   @Field("id") String id);

    @POST("SavecasePosting")
    Call<DraftModel> draftList(@Header("Authorization") String token);

    @POST("send")
    Call<NotificationResponse> notification(@Header("Authorization") String s, @Body NotificationModel model);

    @POST("send")
    Call<NotificationResponse> newmsg(@Header("Authorization") String s, @Body NotificationNewMsg model);

    @POST("notificationList")
    Call<RecentNotification> notificationtList(@Header("Authorization") String token);

    @FormUrlEncoded
    @POST("replyMessage")
    Call<MessageResponse> downloadOldMsg(@Header("Authorization") String token, @Field("id") String id);

    @FormUrlEncoded
    @POST("replyMessage")
    Call<MessageResponse> sendNewMsg(@Header("Authorization") String token, @Field("id") String id, @Field("project_details") String message);

    @FormUrlEncoded
    @POST("replyMessage")
    Call<MessageResponse> setLawyerResponse(@Header("Authorization") String auth, @Field("id") String id, @Field("IsLawyerInterested") String s, @Field("lawyerDetails") String project_details);

    @FormUrlEncoded
    @POST("replyMessage")
    Call<MessageResponse> setClientResponse(@Header("Authorization") String auth, @Field("id") String id, @Field("IsClientInterested") String s);

    @FormUrlEncoded
    @POST("ProfileUpdate")
    Call<Success> update(
            @Header("Authorization") String token,
            @Field("nid") String nid,
            @Field("phone") String phone,
            @Field("address") String address,
            @Field("district_id") String district_id,
            @Field("thana_id") String thana_id);

    @Multipart
    @POST("ProfileUpdate")
    Call<Success> updatePhoto(@Header("Authorization") String token, @Part MultipartBody.Part image);


    @POST("ProfileUpdate")
    Call<ProfileResponse> downloadProfile(@Header("Authorization") String token);

    @POST("invoiceCreate")
    Call<SercviceCustomerModel> getInvoiceCreateData(@Header("Authorization") String token);

    @POST("invoiceCreate")
    Call<Success> sendInvoice(@Header("Authorization") String s, @Body InvoicePostModel model);

    @FormUrlEncoded
    @POST("replyMessage")
    Call<Success> lawyersendAcceptionDicission(@Header("Authorization") String token, @Field("id") String id, @Field("IsLawyerIAccept") String message);

    @FormUrlEncoded
    @POST("replyMessage")
    Call<Success> clientsendrequest(@Header("Authorization") String token, @Field("id") String id, @Field("IsClienIAccept") String message);

    @POST("projectHistory")
    Call<ProjectHistoryModel> getMyCaseList(@Header("Authorization") String token);

    @POST("invoiceList")
    Call<InResponse> downloadInvoiceList(@Header("Authorization") String token);

    @FormUrlEncoded
    @POST("invoiceList")
    Call<InvoiceDetailModel> downloadInvoiceDetail(@Header("Authorization") String auth, @Field("token") String token);

    @FormUrlEncoded
    @POST("savePayment")
    Call<Success> addPayment(@Header("Authorization") String auth, @Field("token") String token, @Field("client_id") String client_id, @Field("payment_method") String payment_method, @Field("payer") String payer, @Field("amount") String amount);


    @POST("lawyerProfileUpdate")
    Call<LawyerinfoResponse> getLawyerProfile(@Header("Authorization") String auth);

    @FormUrlEncoded
    @POST("lawyerProfileUpdate")
    Call<LawyerinfoResponse> updatelawyerProfile(@Header("Authorization") String auth, @Field("regNumber") String regNumber,
                                                 @Field("llbPassingInstitute") String llbPassingInstitute,
                                                 @Field("passingYear") String passingYear,
                                                 @Field("chamberAddress") String chamberAddress,
                                                 @Field("chamberContact") String chamberContact,
                                                 @Field("optionalChamberAddressOrContact") String optionalChamberAddressOrContact,
                                                 @Field("associatesInfo") String associatesInfo);

    @FormUrlEncoded
    @POST("lawyerProfileUpdate")
    Call<LawyerinfoResponse> updatelawyerProffesionalProfile(@Header("Authorization") String auth,
                                                             @Field("llbPassingInstitute") String llbPassingInstitute,
                                                             @Field("passingYear") String passingYear,
                                                             @Field("llmPassingInstitute") String llmPassingInstitute,
                                                             @Field("llmPassingYear") String llmPassingYear,
                                                             @Field("barOfLawInstitute") String barOfLawInstitute,
                                                             @Field("barLawPassingYear") String barLawPassingYear,
                                                             @Field("phdFrom") String phdFrom,
                                                             @Field("phdPassingYear") String phdPassingYear);


    @FormUrlEncoded
    @POST("feedback")
    Call<FeedbackStatusResponse> checkProjectStatus(@Header("Authorization") String auth, @Field("project_id") String token);

    @FormUrlEncoded
    @POST("feedback")
    Call<FeedbackStatusResponse> confirmProjectStatus(@Header("Authorization") String auth, @Field("project_id") String token, @Field("confirm") String confirm);

    @FormUrlEncoded
    @POST("feedBackReplay")
    Call<FeedBackResponse> projectFeedBackResponse(@Header("Authorization") String auth, @Field("project_id") String token);

    @FormUrlEncoded
    @POST("feedBackReplay")
    Call<Success> sendFeedBack(@Header("Authorization") String auth, @Field("project_id") String token, @Field("review_rate") String review_rate,
                               @Field("experience") String experience, @Field("comment") String comment, @Field("suggest") String suggest);

    @FormUrlEncoded
    @POST("projectActivity")
    Call<Success> caseInfoEdit(@Header("Authorization") String auth, @Field("project_id") String token, @Field("court_name") String court_name,
                               @Field("court_no") String court_no, @Field("next_date") String next_date, @Field("last_order") String last_order, @Field("district") String district);


    @FormUrlEncoded
    @POST("projectActivity")
    Call<Success> caseNoEdit(@Header("Authorization") String auth, @Field("project_id") String token, @Field("caseNoEdit") String caseNoEdit);

    @FormUrlEncoded
    @POST("projectActivity")
    Call<C> getCaseDates(@Header("Authorization") String auth, @Field("project_id") String token);

    @POST("caseInfo")
    Call<CaseRoutine> getMyDates(@Header("Authorization") String auth);

    @POST("earningProfile")
    Call<EarningResponse> myearningProgile(@Header("Authorization") String auth);

    @POST("fileOrDocuments")
    Call<Success> sendFileToServer(@Header("Authorization") String auth, @Query("project_id") String token, @Body RequestBody multipartFile);

    @FormUrlEncoded
    @POST("fileOrDocuments")
    Call<FileDownloadResponse> downloadFileFromServer(@Header("Authorization") String auth, @Field("project_id") String token);

    @FormUrlEncoded
    @POST("clientFeedBackList")
    Call<FeedBackVIewResponse> reviewList(@Header("Authorization") String auth, @Field("lawyer_id") String token);


    @FormUrlEncoded
    @POST("lawyerProfileUpdate")
    Call<LawyerinfoResponse> lawyerServiceSelect(@Header("Authorization") String auth, @Field("serviceSelect[]") String regNumber);

    @POST("lawyerServiceRate")
    Call<LawyerServiceRateList> getserviceRateList(@Header("Authorization") String auth);

    @FormUrlEncoded
    @POST("lawyerServiceRate")
    Call<Success> setlawyerservicerate(@Header("Authorization") String auth, @Field("service_id") String service_id, @Field("service_rate") String service_rate);

    @FormUrlEncoded
    @POST("lawyerServiceRate")
    Call<Success> editlawyerservicerate(@Header("Authorization") String auth, @Field("lawyerRateId") String service_id, @Field("service_rate") String lawyerRateId);

    @POST("lawRules")
    Call<LawRulesResponse> getlawRule(@Header("Authorization") String auth);

    @Multipart
    @POST("lawyerProfileUpdate")
    Call<Success> cirtificateUpdate(@Header("Authorization") String token, @Part MultipartBody.Part image);


    @FormUrlEncoded
    @POST("phoneOrEmailUnique")
    Call<Success> checkPhone(@Field("phone") String phone);

    @FormUrlEncoded
    @POST("phoneOrEmailUnique")
    Call<Success> checkEmail(@Field("email") String phone);

    @FormUrlEncoded
    @POST("forgotPassword")
    Call<AccountFindResponse> findAccount(@Field("emailOrPhone") String emailorphone);

    @FormUrlEncoded
    @POST("resetPassword")
    Call<Success> changePassword(@Field("id") String id, @Field("password") String password);

*/
}


