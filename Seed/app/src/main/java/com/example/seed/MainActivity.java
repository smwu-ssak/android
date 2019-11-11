package com.example.seed;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
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
        moveToDetailView();
    }

    public void setScrollViewFocus(){
        final ScrollView scrollView = findViewById(R.id.main_scrollView);
        scrollView.post(new Runnable() {
            @Override
            public void run() {
                scrollView.fullScroll(ScrollView.FOCUS_UP);
            }
        });
    }

    public void moveToDetailView(){
        RelativeLayout button = findViewById(R.id.main_act_buy_btn);
        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                //Intent intent = new Intent(getApplicationContext(), DetailProductsActivity.class);
                //startActivity(intent);
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
        RecyclerView recyclerView = findViewById(R.id.rv_main);

        final ArrayList<MainProductData> data = new ArrayList<>();

        data.add(new MainProductData(R.drawable.rv_main_apple, "사과", 5, 1000, 500, 50, "프라임마트"));
        data.add(new MainProductData(R.drawable.rv_main_broccoli, "브로콜리", 7, 1000, 800, 20, "눈송마트"));
        data.add(new MainProductData(R.drawable.rv_main_apple, "사과", 5, 1000, 500, 50, "프라임마트"));
        data.add(new MainProductData(R.drawable.rv_main_broccoli, "브로콜리", 7, 1000, 800, 20, "눈송마트"));
        data.add(new MainProductData(R.drawable.rv_main_apple, "사과", 5, 1000, 500, 50, "프라임마트"));
        data.add(new MainProductData(R.drawable.rv_main_broccoli, "브로콜리", 7, 1000, 800, 20, "눈송마트"));

        final MainProductAdapter adapter = new MainProductAdapter(data);

        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new ProductRecyclerViewDecoration(15));

        adapter.setOnItemClickListener(new OnMainProductClickListener() {
            @Override
            public void onItemClick(MainProductAdapter.MainProductViewHolder holder, View view, int position) {
                MainProductData item = adapter.getItem(position);
                Intent intent = new Intent(getApplicationContext(), DetailProductsActivity.class);
                startActivity(intent);
            }
        });
        //adapter.notifyDataSetChanged();
    }

}
