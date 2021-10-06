package com.example.androidcodeassigment.apis;

import com.example.androidcodeassigment.models.ImageDataResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ImageDataSearchService {
    @GET("/api")
    Call<ImageDataResponse> searchImageData(
      @Query("key") String api_key,
      @Query("q") String query,
      @Query("page") Integer page,
      @Query("per_page") Integer per_page
    );
}
