package com.mkl.cyberpolicebogura.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StatusPath {

@SerializedName("status")
@Expose
private Boolean status;
@SerializedName("path")
@Expose
private String path;

public Boolean getStatus() {
return status;
}

public void setStatus(Boolean status) {
this.status = status;
}

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}