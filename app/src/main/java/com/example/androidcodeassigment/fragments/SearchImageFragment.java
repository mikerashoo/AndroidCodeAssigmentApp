package com.example.androidcodeassigment.fragments;

import static com.example.androidcodeassigment.utils.constants.NUMBER_OF_IMAGES_PER_PAGE;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidcodeassigment.R;
import com.example.androidcodeassigment.adapters.ImageDataRecyclerAdapter;
import com.example.androidcodeassigment.models.ImageDataResponse;
import com.example.androidcodeassigment.viewmodels.ImageDataViewModel;
import com.google.android.material.textfield.TextInputEditText;

public class SearchImageFragment extends Fragment {
    private ImageDataViewModel imageDataViewModel;
    private ImageDataRecyclerAdapter adapter;
    private TextInputEditText searchInputEditText;
    private Button searchButton;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new ImageDataRecyclerAdapter();
        imageDataViewModel = ViewModelProviders.of(this).get(ImageDataViewModel.class);
        imageDataViewModel.init();
        imageDataViewModel.getImageDataResponseLiveData().observe(this, new Observer<ImageDataResponse>() {
            @Override
            public void onChanged(ImageDataResponse imageDataResponse) {
                if(imageDataResponse != null){
                    adapter.setImageDataList(imageDataResponse.getImageDataList());
                }
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_image_search, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.image_search_results_recyclerView);
        //TODO change layout to grid so that it look like gallery
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        searchInputEditText = view.findViewById(R.id.image_search_text_input_edittext);
        searchButton = view.findViewById(R.id.image_search_button);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performSearch();
            }
        });
        return view;
    }

    public void performSearch(){
        String keyword = searchInputEditText.getEditableText().toString();
        imageDataViewModel.searchImagesData(keyword, 1, NUMBER_OF_IMAGES_PER_PAGE);
    }
}
