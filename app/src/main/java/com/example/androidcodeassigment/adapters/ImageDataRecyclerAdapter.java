package com.example.androidcodeassigment.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.androidcodeassigment.R;
import com.example.androidcodeassigment.models.ImageData;

import java.util.ArrayList;
import java.util.List;

public class ImageDataRecyclerAdapter extends RecyclerView.Adapter<ImageDataRecyclerAdapter.ImageDataSearchResultHolder>{

    private List<ImageData> imageDataList = new ArrayList<>();

    @NonNull
    @Override
    public ImageDataSearchResultHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.images_recyclerview_item, parent, false);
        return new ImageDataSearchResultHolder(view);
    }

    /**
     *
     * get image preview url from image data
     * Display with help of Glide library
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull ImageDataSearchResultHolder holder, int position) {
        ImageData imageData = imageDataList.get(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO show image on new activity
            }
        });
        if(imageData.getPreviewUrl() != null){
            String imageUrl = imageData.getPreviewUrl();
            Glide.with(holder.itemView).load(imageUrl).into(holder.imageDataView);
        }

        if(imageData.getTitle() != null){
            holder.imageTitleTextView.setText(imageData.getTitle());
        }
    }

    @Override
    public int getItemCount() {
        return imageDataList.size();
    }

    class ImageDataSearchResultHolder extends RecyclerView.ViewHolder{

        private ImageView imageDataView;
        private TextView imageTitleTextView;
        public ImageDataSearchResultHolder(@NonNull View itemView) {
            super(itemView);

            imageDataView = itemView.findViewById(R.id.recyclerview_item_imageview);
            imageTitleTextView = itemView.findViewById(R.id.recyclerview_item_title_textview);
        }
    }

    public void setImageDataList(List<ImageData> imageDataList) {
        this.imageDataList = imageDataList;
        notifyDataSetChanged();
    }
}
