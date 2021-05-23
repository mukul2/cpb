package com.mkl.cyberpolicebogura.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Complaints {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("seen")
    @Expose
    private String seen;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("seen_by")
    @Expose
    private String seen_by;
    @SerializedName("complaint_sub")
    @Expose
    private String complaintSub;
    @SerializedName("body")
    @Expose
    private String body;
    @SerializedName("attachment")
    @Expose
    private String attachment;
    @SerializedName("time")
    @Expose
    private String time;
    @SerializedName("sender_location")
    @Expose
    private String senderLocation;
    @SerializedName("thana")
    @Expose
    private String thana;
    @SerializedName("district")
    @Expose
    private String district;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getComplaintSub() {
        return complaintSub;
    }

    public void setComplaintSub(String complaintSub) {
        this.complaintSub = complaintSub;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSenderLocation() {
        return senderLocation;
    }

    public void setSenderLocation(String senderLocation) {
        this.senderLocation = senderLocation;
    }

    public String getThana() {
        return thana;
    }

    public void setThana(String thana) {
        this.thana = thana;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getSeen() {
        return seen;
    }

    public void setSeen(String seen) {
        this.seen = seen;
    }

    public String getSeen_by() {
        return seen_by;
    }

    public void setSeen_by(String seen_by) {
        this.seen_by = seen_by;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}