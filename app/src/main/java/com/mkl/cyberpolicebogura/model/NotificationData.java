package com.mkl.cyberpolicebogura.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NotificationData {

@SerializedName("to")
@Expose
private String to;
@SerializedName("notification")
@Expose
private Notification notification;

public String getTo() {
return to;
}

public void setTo(String to) {
this.to = to;
}

public Notification getNotification() {
return notification;
}

public void setNotification(Notification notification) {
this.notification = notification;
}

}