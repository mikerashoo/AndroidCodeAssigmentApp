package com.example.androidcodeassigment.repositories;

import static com.example.androidcodeassigment.utils.constants.API_KEY;
import static com.example.androidcodeassigment.utils.constants.API_URL;

import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.example.androidcodeassigment.MainActivity;
import com.example.androidcodeassigment.apis.ImageDataSearchService;
import com.example.androidcodeassigment.models.ImageDataResponse;
import com.example.androidcodeassigment.utils.NetworkConnectionInterceptor;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

public class ImageDataRepository {
    private final ImageDataSearchService imageDataSearchService;
    private final MutableLiveData<ImageDataResponse> imageDataResponseMutableLiveData;

    public ImageDataRepository() {
        imageDataResponseMutableLiveData = new MutableLiveData<>();
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.level(HttpLoggingInterceptor.Level.BODY);

        NetworkConnectionInterceptor networkConnectionInterceptor = new NetworkConnectionInterceptor(MainActivity.context);

        // Adding NetworkConnectionInterceptor with okHttpClientBuilder.
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).addInterceptor(networkConnectionInterceptor).build();
        imageDataSearchService = new retrofit2.Retrofit.Builder()
                .baseUrl(API_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ImageDataSearchService.class);
    }

    public void searchImageData(String keyword, Integer page, Integer per_page) {
        imageDataSearchService.searchImageData(API_KEY, keyword, page, per_page).enqueue(new Callback<ImageDataResponse>() {
            @Override
            public void onResponse(Call<ImageDataResponse> call, Response<ImageDataResponse> response) {
                if (response.isSuccessful()) {
                    imageDataResponseMutableLiveData.setValue(response.body());
                } else {
                    Toast.makeText(MainActivity.context, "Something went wrong. please try again!", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ImageDataResponse> call, Throwable t) {
                ImageDataResponse _imageDataResponse = new ImageDataResponse();
                _imageDataResponse.setError(t);
                imageDataResponseMutableLiveData.setValue(_imageDataResponse);
            }
        });
    }

    public MutableLiveData<ImageDataResponse> getImageDataResponseMutableLiveData() {
        return imageDataResponseMutableLiveData;
    }
}
