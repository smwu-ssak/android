package com.example.seed;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.seed.DB.SharedPreferenceController;
import com.example.seed.Get.GetBasketAddResponse;
import com.example.seed.Get.GetDetailResponse;
import com.example.seed.Network.ApplicationController;
import com.example.seed.Network.NetworkService;

import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

// Customized by SY

public class DetailProductsActivity extends AppCompatActivity {

    ApplicationController applicationController = new ApplicationController();
    NetworkService networkService = applicationController.buildNetworkService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_products);

        moveToMainView();
        setValuesFromItems();

        popupDialog();
    }

    // Customized by MS
    public void popupDialog() {

        Bundle extras = getIntent().getExtras();
        final int idProduct = extras.getInt("idProduct");
        final String token = SharedPreferenceController.getMyId(this);

        RelativeLayout button = findViewById(R.id.detail_act_bottom_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DetailProductDialog dialog = new DetailProductDialog(DetailProductsActivity.this);

                Call<GetBasketAddResponse> call = networkService.getBasketAddResponse("application/json", token, idProduct);
                call.enqueue(new Callback<GetBasketAddResponse>() {
                    @Override
                    public void onResponse(Call<GetBasketAddResponse> call, Response<GetBasketAddResponse> response) {
                        if (response.isSuccessful()){
                            Log.v("통신 성공", "통신 성공");
                        }
                    }
                    @Override
                    public void onFailure(Call<GetBasketAddResponse> call, Throwable t) {
                        Log.v("통신 실패", t.toString());
                    }
                });

                dialog.show();
            }
        });
    }
    // Customized by MS

    public void setValuesFromItems() {

        Bundle extras = getIntent().getExtras();
        int idProduct = extras.getInt("idProduct");

        final TextView name_tv = (TextView)findViewById(R.id.detail_act_name);
        final TextView quantity_tv = (TextView)findViewById(R.id.detail_act_products_quantity);
        final TextView comment_tv = (TextView)findViewById(R.id.detail_act_comments);
        final ImageView image_iv = (ImageView)findViewById(R.id.detail_act_img);
        final TextView originPrice_tv = (TextView)findViewById(R.id.detail_act_products_price_origin);
        final TextView salePrice_tv = (TextView)findViewById(R.id.detail_act_products_price_sale);
        final TextView discount_tv = (TextView)findViewById(R.id.detail_act_products_discount);
        TextView expDate_tv = (TextView)findViewById(R.id.detail_act_products_timeleft);
        final TextView stoName_tv = (TextView)findViewById(R.id.detail_act_store_name);
        final TextView gpsStoName_tv = (TextView)findViewById(R.id.detail_act_gps_store_name);
        final TextView address_tv = (TextView)findViewById(R.id.detail_act_store_location);
        final TextView gpsAddress_tv = (TextView)findViewById(R.id.detail_act_gps_store_location);
        final TextView tel_tv = (TextView)findViewById(R.id.detail_act_store_num1);
        final de.hdodenhof.circleimageview.CircleImageView userProfile_iv = (de.hdodenhof.circleimageview.CircleImageView)findViewById(R.id.detail_act_img_store);

        final FrameLayout cancel_fl = (FrameLayout)findViewById(R.id.detail_act_products_price_origin_cancel);

        // quantity_tv.setText(String.valueOf(quantity));

        Call<GetDetailResponse> call = networkService.getDetailResponse("application/json", idProduct);
        call.enqueue(new Callback<GetDetailResponse>() {
            @Override
            public void onResponse(Call<GetDetailResponse> call, Response<GetDetailResponse> response) {
                if (response.isSuccessful()){
                    String name = response.body().data.proName;
                    int quantity = response.body().data.quantity;
                    String comment = response.body().data.comment;
                    String image = response.body().data.image;
                    int originPrice = response.body().data.originPrice;
                    int salePrice = response.body().data.salePrice;
                    Date expDate = response.body().data.expDate;
                    String stoName = response.body().data.stoName;
                    String address = response.body().data.address;
                    double lat = response.body().data.lat;
                    double log = response.body().data.log;
                    String tel = response.body().data.tel;
                    String userProfile = response.body().data.userProfile;

                    name_tv.setText(name);
                    quantity_tv.setText(String.valueOf(quantity));
                    comment_tv.setText(comment);
                    Glide.with(getApplicationContext())
                            .load(image)
                            .into(image_iv);
                    originPrice_tv.setText(String.valueOf(originPrice));
                    salePrice_tv.setText(String.valueOf(salePrice));
                    discount_tv.setText(String.valueOf(Math.round((float)(originPrice-salePrice)/(float)originPrice*100)));

                    stoName_tv.setText(stoName);
                    gpsStoName_tv.setText(stoName);
                    address_tv.setText(address);
                    gpsAddress_tv.setText(address);
                    tel_tv.setText(tel);
                    Glide.with(getApplicationContext())
                            .load(userProfile)
                            .into(userProfile_iv);

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
            }

            @Override
            public void onFailure(Call<GetDetailResponse> call, Throwable t) {
                Log.v("통신 실패", t.toString());
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

