package com.example.androidcodeassigment.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.androidcodeassigment.models.ImageDataResponse;
import com.example.androidcodeassigment.repositories.ImageDataRepository;

public class ImageDataViewModel extends AndroidViewModel {
    private ImageDataRepository imageDataRepository;
    private LiveData<ImageDataResponse> imageDataResponseLiveData;

    public ImageDataViewModel(@NonNull Application application) {
        super(application);
    }

    public void init(){
        imageDataRepository = new ImageDataRepository();
        imageDataResponseLiveData = imageDataRepository.getImageDataResponseMutableLiveData();
    }

    public void searchImagesData(String keyword, Integer page, Integer per_page){
        imageDataRepository.searchImageData(keyword, page, per_page);
    }

    public LiveData<ImageDataResponse> getImageDataResponseLiveData() {
        return imageDataResponseLiveData;
    }
}
