package com.example.androidcodeassigment.adapters;

import static com.example.androidcodeassigment.utils.constants.IMAGE_DATA_INTENT;
import static com.example.androidcodeassigment.utils.constants.IMAGE_TITLE_INTENT;

import android.app.Activity;
import android.content.Intent;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.BackgroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityOptionsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.androidcodeassigment.ImageDetailActivity;
import com.example.androidcodeassigment.R;
import com.example.androidcodeassigment.models.ImageData;

import java.util.ArrayList;
import java.util.List;

public class ImageDataRecyclerAdapter extends RecyclerView.Adapter<ImageDataRecyclerAdapter.ImageDataSearchResultHolder>{

    private List<ImageData> imageDataList = new ArrayList<>();

    //To access search keyword from fragment
    private String search_keyword = "";

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
                //Passing large image url to fullscreen image activity
                Intent intent = new Intent(v.getContext(), ImageDetailActivity.class);
                intent.putExtra(IMAGE_DATA_INTENT, imageData.getLargeImageURL());

                //Passing image tags to fullscreen activity
                intent.putExtra(IMAGE_TITLE_INTENT, imageData.getTitle());
                ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) v.getContext(), holder.imageDataView, "image_transition");
                v.getContext().startActivity(intent, optionsCompat.toBundle());
            }
        });
        if(imageData.getPreviewUrl() != null){
            String imageUrl = imageData.getPreviewUrl();
            Glide.with(holder.itemView).load(imageUrl).into(holder.imageDataView);
        }

        if(imageData.getTitle() != null){
            holder.imageTitleTextView.setText(imageData.getTitle());
            if(search_keyword != ""){
                setHighLightedText(holder.imageTitleTextView, search_keyword);
            }
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
            imageDataView = itemView.findViewById(R.id.id_image_view);
            imageTitleTextView = itemView.findViewById(R.id.recyclerview_item_title_textview);
        }
    }

    public void setImageDataList(List<ImageData> imageDataList) {
        this.imageDataList = imageDataList;
        notifyDataSetChanged();
    }

    public void setSearchKeyword(String search_keyword) {
        this.search_keyword = search_keyword;
    }

    /**
     * use this method to highlight a text in TextView
     *
     * @param tv              TextView or Edittext or Button (or derived from TextView)
     * @param textToHighlight Text to highlight
     */
    public void setHighLightedText(TextView tv, String textToHighlight) {
        String tvt = tv.getText().toString();
        int ofe = tvt.indexOf(textToHighlight, 0);
        Spannable wordToSpan = new SpannableString(tv.getText());
        for (int ofs = 0; ofs < tvt.length() && ofe != -1; ofs = ofe + 1) {
            ofe = tvt.indexOf(textToHighlight, ofs);
            if (ofe == -1)
                break;
            else {
                // set color here
                wordToSpan.setSpan(new BackgroundColorSpan(0xFFFFFF00), ofe, ofe + textToHighlight.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                tv.setText(wordToSpan, TextView.BufferType.SPANNABLE);
            }
        }
    }
}
