package com.example.androidcodeassigment.repositories;

import static com.example.androidcodeassigment.utils.constants.API_KEY;
import static com.example.androidcodeassigment.utils.constants.API_URL;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.androidcodeassigment.apis.ImageDataSearchService;
import com.example.androidcodeassigment.models.ImageDataResponse;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

public class ImageDataRepository {
    private ImageDataSearchService imageDataSearchService;
    private MutableLiveData<ImageDataResponse> imageDataResponseMutableLiveData;

    public ImageDataRepository() {
        imageDataResponseMutableLiveData = new MutableLiveData<>();
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.level(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        imageDataSearchService = new retrofit2.Retrofit.Builder()
                .baseUrl(API_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ImageDataSearchService.class);
    }

    public void searchImageData(String keyword, Integer page, Integer per_page){
        imageDataSearchService.searchImageData(API_KEY, keyword, page, per_page).enqueue(new Callback<ImageDataResponse>() {
            @Override
            public void onResponse(Call<ImageDataResponse> call, Response<ImageDataResponse> response) {
                if(response.body() != null){
                    imageDataResponseMutableLiveData.setValue(response.body());
                    //TODO populate data
                }
                else{
                    //TODO handle no data error
                }
            }

            @Override
            public void onFailure(Call<ImageDataResponse> call, Throwable t) {
                //TODO handle on failure error
            }
        });
    }

    public MutableLiveData<ImageDataResponse> getImageDataResponseMutableLiveData() {
        return imageDataResponseMutableLiveData;
    }
}
