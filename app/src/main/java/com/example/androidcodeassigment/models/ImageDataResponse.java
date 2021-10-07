package com.example.androidcodeassigment.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ImageDataResponse {
    //Total number of hits.
    @SerializedName("total")
    @Expose
    private String total;

    //Number of images accessible through the API.
    @SerializedName("totalHits")
    @Expose
    private String totalHits;

    @SerializedName("hits")
    @Expose
    private List<ImageData> imageDataList = null;

    private Throwable error;

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getTotalHits() {
        return totalHits;
    }

    public void setTotalHits(String totalHits) {
        this.totalHits = totalHits;
    }

    public List<ImageData> getImageDataList() {
        return imageDataList;
    }

    public void setImageDataList(List<ImageData> imageDataList) {
        this.imageDataList = imageDataList;
    }

    public Throwable getError() {
        return error;
    }

    public void setError(Throwable error) {
        this.error = error;
    }
}
