package com.example.seed;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.seed.data.MainProductData;

// Customized by SY

public class DetailProductsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_products);

        moveToMainView();
        setValuesFromItems();
    }

    public void setValuesFromItems(){

        int image;
        String name;
        String place;
        int quantity;
        String gpsStore;
        int originPrice;
        int salePrice;
        int discount;

        Bundle extras = getIntent().getExtras();

        image = extras.getInt("image");
        name = extras.getString("name");
        place = extras.getString("place");
        quantity = extras.getInt("quantity");
        gpsStore = extras.getString("place");
        originPrice = extras.getInt("originPrice");
        salePrice = extras.getInt("salePrice");
        discount = extras.getInt("discount");

        ImageView image_iv = (ImageView)findViewById(R.id.detail_act_img);
        TextView name_tv = (TextView)findViewById(R.id.detail_act_name);
        TextView place_tv = (TextView)findViewById(R.id.detail_act_store_name);
        TextView quantity_tv = (TextView)findViewById(R.id.detail_act_products_quantity);
        TextView gpsStore_tv = (TextView)findViewById(R.id.detail_act_gps_store_name);
        final TextView originPrice_tv = (TextView)findViewById(R.id.detail_act_products_price_origin);
        TextView salePrice_tv = (TextView)findViewById(R.id.detail_act_products_price_sale);
        TextView discount_tv = (TextView)findViewById(R.id.detail_act_products_discount);

        final FrameLayout cancel_fl = (FrameLayout)findViewById(R.id.detail_act_products_price_origin_cancel);

//        image_iv.setImageResource(image);
        name_tv.setText(name);
        place_tv.setText(place);
        quantity_tv.setText(String.valueOf(quantity));
        gpsStore_tv.setText(String.valueOf(gpsStore));
        originPrice_tv.setText(String.valueOf(originPrice));
        salePrice_tv.setText(String.valueOf(salePrice));
        discount_tv.setText(String.valueOf(discount));

        cancel_fl.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                int len = originPrice_tv.getWidth();

                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                params.width = len+10;
                params.gravity = Gravity.CENTER_VERTICAL;
                cancel_fl.setForegroundGravity(Gravity.CENTER_VERTICAL);
                cancel_fl.setLayoutParams(params);

                originPrice_tv.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });

    }

    public void moveToMainView() {
        RelativeLayout button = findViewById(R.id.detail_act_back_mainpage);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DetailProductsActivity.class);
                finish();
            }
        });
    }
}

