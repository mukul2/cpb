package com.mkl.cyberpolicebogura.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import okhttp3.MultipartBody;

/**
 * Created by mkl on 2/14/2019.
 */

public class ComplainBody {
    String complaint_sub,body,sender_location,district;


    public ComplainBody(String complaint_sub, String body, String sender_location, String district) {
        this.complaint_sub = complaint_sub;
        this.body = body;
        this.sender_location = sender_location;
        this.district = district;
    }



    public ComplainBody() {
    }

    public String getComplaint_sub() {
        return complaint_sub;
    }

    public void setComplaint_sub(String complaint_sub) {
        this.complaint_sub = complaint_sub;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getSender_location() {
        return sender_location;
    }

    public void setSender_location(String sender_location) {
        this.sender_location = sender_location;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }
}
