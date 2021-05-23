package com.mkl.cyberpolicebogura.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Police {

@SerializedName("id")
@Expose
private String id;
@SerializedName("type")
@Expose
private String type;
@SerializedName("mobile")
@Expose
private String mobile;
@SerializedName("imei")
@Expose
private String imei;
@SerializedName("name")
@Expose
private String name;
@SerializedName("photo")
@Expose
private String photo;

public String getId() {
return id;
}

public void setId(String id) {
this.id = id;
}

public String getType() {
return type;
}

public void setType(String type) {
this.type = type;
}

public String getMobile() {
return mobile;
}

public void setMobile(String mobile) {
this.mobile = mobile;
}

public String getImei() {
return imei;
}

public void setImei(String imei) {
this.imei = imei;
}

public String getName() {
return name;
}

public void setName(String name) {
this.name = name;
}

public String getPhoto() {
return photo;
}

public void setPhoto(String photo) {
this.photo = photo;
}

}