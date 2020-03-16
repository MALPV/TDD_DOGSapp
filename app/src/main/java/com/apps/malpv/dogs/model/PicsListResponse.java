package com.apps.malpv.dogs.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PicsListResponse {

    @SerializedName("message")
    private List<String> picList;
    private String status;

    public PicsListResponse(List<String> picList, String status) {
        this.picList = picList;
        this.status = status;
    }

    public List<String> getPicList() {
        return picList;
    }

    public void setPicList(List<String> picList) {
        this.picList = picList;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
