package com.example.androidcodeassigment.fragments;

import static com.example.androidcodeassigment.utils.constants.NUMBER_OF_IMAGES_PER_PAGE;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
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
    private TextView searchResultTextView;
    private ProgressBar loadingProgressBar;
    private NestedScrollView nestedScrollView;

    private int count;
    private static Integer CURRENT_PAGE = 1;
    private Integer total_result = 0;
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
                    searchResultTextView.setText(imageDataResponse.getTotal() + " results found");
                    total_result = Integer.parseInt(imageDataResponse.getTotal());
                }
                //hide progressbar on showing result
                loadingProgressBar.setVisibility(View.INVISIBLE);
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_image_search, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.image_search_results_recyclerView);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(adapter);

        loadingProgressBar = view.findViewById(R.id.more_loading_progressbar);
        searchResultTextView = view.findViewById(R.id.search_results_textview);
        searchInputEditText = view.findViewById(R.id.image_search_text_input_edittext);
        searchButton = view.findViewById(R.id.image_search_button);
        nestedScrollView = view.findViewById(R.id.nested_scrollview);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performSearch(1);
            }
        });

        nestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if(scrollY == v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight()){
                    if(count == 0 || total_result / count > 0){
                        count++;
                        performSearch(count);
                    }
                }
            }
        });

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if(!recyclerView.canScrollVertically(1)){
                    if(total_result > CURRENT_PAGE * NUMBER_OF_IMAGES_PER_PAGE){
                        CURRENT_PAGE++;
                    }
                    performSearch(CURRENT_PAGE);
                }
                else if(!recyclerView.canScrollVertically(-1)){
                    CURRENT_PAGE--;
                }
            }
        });

        return view;
    }

    public void performSearch(Integer page){
        String keyword = searchInputEditText.getEditableText().toString();
        imageDataViewModel.searchImagesData(keyword, page, NUMBER_OF_IMAGES_PER_PAGE);
        //show loading progress on fetching
        loadingProgressBar.setVisibility(View.VISIBLE);
    }

}
