package com.example.androidcodeassigment.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ImageData {
    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("tags")
    @Expose
    private String title;

    //Low resolution images with a maximum width or height of 150 px
    @SerializedName("previewURL")
    @Expose
    private String previewUrl;

    //Scaled image with a maximum width/height of 1280px.
    @SerializedName("largeImageURL")
    @Expose
    private String largeImageURL;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPreviewUrl() {
        return previewUrl;
    }

    public void setPreviewUrl(String previewUrl) {
        this.previewUrl = previewUrl;
    }

    public String getLargeImageURL() {
        return largeImageURL;
    }

    public void setLargeImageURL(String largeImageURL) {
        this.largeImageURL = largeImageURL;
    }
}
