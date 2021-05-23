package com.mkl.cyberpolicebogura.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Notification {

    @SerializedName("body")
    @Expose
    private String body;
    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("click_action")
    @Expose
    private String click_action;

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }



    public String getClick_action() {
        return click_action;
    }

    public void setClick_action(String click_action) {
        this.click_action = click_action;
    }
}
