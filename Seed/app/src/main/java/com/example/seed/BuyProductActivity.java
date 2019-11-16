package com.example.seed;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.seed.data.BuyProductData;

import java.util.ArrayList;

// Customized by MS

public class BuyProductActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_products);

        setProducts();
        moveToBasket();

    }

    public void moveToBasket(){
        RelativeLayout button = findViewById(R.id.buy_act_bottom_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), BasketActivity.class);
                startActivity(intent);
            }
        });
    }

    public void setProducts() {
        final RecyclerView recyclerView = findViewById(R.id.rv_buy_products);

        final ArrayList<BuyProductData> data = new ArrayList<>();

        data.add(new BuyProductData(R.drawable.purchaseview_applephoto, "사과", 2, "싹 종이 봉투에 넣어", 1100));
        data.add(new BuyProductData(R.drawable.purchaseview_broccoliphoto, "브로콜리", 2, "싹 용기에 넣어", 2100));
        data.add(new BuyProductData(R.drawable.purchaseview_applephoto, "사과", 2, "봉투 없이", 1100));
        data.add(new BuyProductData(R.drawable.purchaseview_broccoliphoto, "브로콜리", 2, "싹 용기에 넣어", 2100));

        final BuyProductAdapter adapter = new BuyProductAdapter(data);

        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new ProductRecyclerViewDecoration(15));

    }

}
