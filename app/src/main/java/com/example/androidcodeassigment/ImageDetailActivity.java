package com.example.androidcodeassigment;

import static com.example.androidcodeassigment.utils.constants.IMAGE_DATA_INTENT;
import static com.example.androidcodeassigment.utils.constants.IMAGE_TITLE_INTENT;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

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

        CircularProgressDrawable circularProgressDrawable = new CircularProgressDrawable(this);
        circularProgressDrawable.setStrokeWidth(5f);
        circularProgressDrawable.setCenterRadius(30f);
        circularProgressDrawable.start();
        fullScreenImageView = findViewById(R.id.id_image_view);
        Intent intent = getIntent();
        String imgUrl = intent.getStringExtra(IMAGE_DATA_INTENT);
        String title = intent.getStringExtra(IMAGE_TITLE_INTENT);
        if(!imgUrl.isEmpty()){
            Glide.with(this).load(imgUrl).placeholder(circularProgressDrawable).into(fullScreenImageView);
        }

        // Add back button
        assert getSupportActionBar() != null;   //null check
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle(title);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}