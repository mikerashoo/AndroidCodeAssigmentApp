package com.example.androidcodeassigment;

import static com.example.androidcodeassigment.utils.constants.IMAGE_DATA_INTENT;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class ImageDetailActivity extends AppCompatActivity {
    private ImageView fullScreenImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_detail);

        fullScreenImageView = findViewById(R.id.fullscreen_imageview);
        Intent intent = getIntent();
        String imgUrl = intent.getStringExtra(IMAGE_DATA_INTENT);
        if(!imgUrl.isEmpty()){
            Glide.with(this).load(imgUrl).into(fullScreenImageView);
        }

        // Add back button
        assert getSupportActionBar() != null;   //null check
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}