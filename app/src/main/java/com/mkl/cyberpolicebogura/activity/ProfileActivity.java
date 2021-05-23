package com.mkl.cyberpolicebogura.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mkl.cyberpolicebogura.R;
import com.mkl.cyberpolicebogura.model.Police;
import com.mkl.cyberpolicebogura.utils.Data;
import com.mkl.cyberpolicebogura.utils.HexagonMaskView;

import static com.mkl.cyberpolicebogura.utils.Data.PHOTO_BASE;
import static com.mkl.cyberpolicebogura.utils.Initials.getUserType;

public class ProfileActivity extends AppCompatActivity {
    Context context=this;
    HexagonMaskView hexagonMaskView;
    ImageView cover_image;
    TextView tv_name,tv_designation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        hexagonMaskView=(HexagonMaskView)findViewById(R.id.hexa_image);
        cover_image=(ImageView)findViewById(R.id.cover_image);
        tv_name=(TextView)findViewById(R.id.tv_name);
        tv_designation=(TextView)findViewById(R.id.tv_designation);
        Police police= Data.tempPolice;
        Glide.with(context).load(PHOTO_BASE+police.getPhoto()).into(hexagonMaskView);
        Glide.with(context).load(PHOTO_BASE+police.getPhoto()).into(cover_image);
        tv_name.setText(police.getName());
        tv_designation.setText(getUserType(police.getType()));

    }

    public void back(View view) {
        onBackPressed();
    }
}
