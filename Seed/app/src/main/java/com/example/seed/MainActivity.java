package com.example.seed;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.ViewFlipper;

import com.example.seed.data.MainProductData;

import java.util.*;

// Customized by SY

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setScrollViewFocus();
        setflipperView();
        setProducts();
        checkMyLocation();
        moveToMypage();
        moveToBuyProducts();
//        LocationService();
    }

//    public void LocationService(){
//        ImageView button = findViewById(R.id.main_act_gps_btn);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startLocationService();
//            }
//        });
//    }
//
//    public void startLocationService(){
//        LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
//        try {
//            Location location = manager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
//            if (location != null){
//                double latitude = location.getLatitude();
//                double longitude = location.getLongitude();
//                String message = "최근 위치-> 위도: "+latitude+"\n경도: "+longitude;
//                Log.d("message: ", message);
//            }
//        } catch(SecurityException e){
//            e.printStackTrace();
//        }
//    }

    public void setScrollViewFocus(){
        final ScrollView scrollView = findViewById(R.id.main_scrollView);
        scrollView.post(new Runnable() {
            @Override
            public void run() {
                scrollView.fullScroll(ScrollView.FOCUS_UP);
            }
        });
    }

    public void moveToMypage(){
        RelativeLayout button = findViewById(R.id.main_act_topbar_mypage_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MypageActivity.class);
                startActivity(intent);
            }
        });
    }

    public void checkMyLocation(){
        ImageView button = findViewById(R.id.main_act_gps_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SearchMyLocation.class);
                startActivity(intent);
            }
        });
    }

    public void moveToBuyProducts(){
        RelativeLayout button = findViewById(R.id.main_act_buy_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), BuyProductsActivity.class);
                startActivity(intent);
            }
        });
    }

    private ViewFlipper v_flipper;

    public void setflipperView(){
        int images[] = {
                R.drawable.main_swiping_box,
//                R.drawable.abc,
                R.drawable.main_swiping_box
//                R.drawable.abc
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

    public void setProducts() {
        final RecyclerView recyclerView = findViewById(R.id.rv_main);

        final ArrayList<MainProductData> data = new ArrayList<>();

        data.add(new MainProductData(R.drawable.rv_main_apple, "사과", 5, 1000, 500, 0, "프라임마트"));
        data.add(new MainProductData(R.drawable.rv_main_broccoli, "브로콜리", 7, 1000, 800, 0, "눈송마트"));
        data.add(new MainProductData(R.drawable.rv_main_apple, "사과", 5, 1000, 500, 0, "프라임마트"));
        data.add(new MainProductData(R.drawable.rv_main_broccoli, "브로콜리", 7, 1000, 800, 0, "눈송마트"));
        data.add(new MainProductData(R.drawable.rv_main_apple, "사과", 5, 1000, 500, 0, "프라임마트"));
        data.add(new MainProductData(R.drawable.rv_main_broccoli, "브로콜리", 7, 1000, 800, 0, "눈송마트"));

        final MainProductAdapter adapter = new MainProductAdapter(data);

        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new ProductRecyclerViewDecoration(15));

        adapter.setOnItemClickListener(new OnMainProductClickListener() {
            @Override
            public void onItemClick(MainProductAdapter.MainProductViewHolder holder, View view, int position) {
                Intent intent = new Intent(getApplicationContext(), DetailProductsActivity.class);
                MainProductData item = adapter.getItem(position);
                int idx = recyclerView.getChildAdapterPosition(view);
                intent.putExtra("name", item.getName());
                intent.putExtra("quantity", item.getQuantity());
                intent.putExtra("originPrice", item.getOriginPrice());
                intent.putExtra("salePrice", item.getSalePrice());
                intent.putExtra("discount", Math.round((float)(item.getOriginPrice()-item.getSalePrice())/(float)item.getOriginPrice()*100));
                intent.putExtra("place", item.getPlace());
                startActivity(intent);
            }
        });
        //adapter.notifyDataSetChanged();
    }

}
