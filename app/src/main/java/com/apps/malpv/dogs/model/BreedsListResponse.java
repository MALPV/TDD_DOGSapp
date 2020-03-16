package com.apps.malpv.dogs.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BreedsListResponse {

    @SerializedName("message")
    private List<String> breedList;
    private String status;

    public BreedsListResponse(List<String> breedList, String status) {
        this.breedList = breedList;
        this.status = status;
    }

    public List<String> getBreedList() {
        return breedList;
    }

    public void setBreedList(List<String> breedList) {
        this.breedList = breedList;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
