package com.example.seed;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import java.util.*;

public class MainActivity extends AppCompatActivity {

    // Customized by SY

    private ViewFlipper v_flipper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int images[] = {
                R.drawable.main_swiping_box,
                R.drawable.main_swiping_box,
                R.drawable.main_swiping_box
        };
        v_flipper = findViewById(R.id.main_act_img_slide);
        for(int image: images){
            flipperImages(image);
        }

    }

    public void flipperImages(int image){
        ImageView imageView = new ImageView(this);
        imageView.setBackgroundResource(image);

        v_flipper.addView(imageView);
        v_flipper.setFlipInterval(2000);
        v_flipper.setAutoStart(true);

//        v_flipper.setInAnimation(this, android.R.anim.slide_out_right);
//        v_flipper.setOutAnimation(this, android.R.anim.slide_in_left);
    }

}
