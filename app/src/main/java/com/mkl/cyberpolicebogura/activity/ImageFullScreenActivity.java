package com.mkl.cyberpolicebogura.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.jsibbold.zoomage.ZoomageView;
import com.mkl.cyberpolicebogura.R;
import com.mkl.cyberpolicebogura.utils.Data;

import static com.mkl.cyberpolicebogura.utils.Data.PHOTO_BASE;

public class ImageFullScreenActivity extends AppCompatActivity {
    ZoomageView myZoomageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_full_screen);
        myZoomageView=(ZoomageView)findViewById(R.id.myZoomageView);
        Glide.with(this).load(PHOTO_BASE+Data.TEMP_LINK).into(myZoomageView);
    }
}
