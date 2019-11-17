package com.example.seed;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.seed.data.BuyCompletedData;

import java.util.ArrayList;

// Customized by MS

public class BuyCompletedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_completed);

        setProducts();
        moveToMypageView();

    }

    public void moveToMypageView() {
        RelativeLayout button = findViewById(R.id.buy_completed_back_mainpage_btn);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });
    }


    public void setProducts() {
        final RecyclerView recyclerView = findViewById(R.id.rv_buy_completed);

        final ArrayList<BuyCompletedData> data = new ArrayList<>();

        data.add(new BuyCompletedData("2019년 11월 14일"));
        data.add(new BuyCompletedData("2019년 11월 15일"));
        data.add(new BuyCompletedData("2019년 11월 16일"));


        final BuyCompletedAdapter adapter = new BuyCompletedAdapter(data);

        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new ProductRecyclerViewDecoration(15));


        //adapter.notifyDataSetChanged();
    }



}
