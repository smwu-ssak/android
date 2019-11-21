package com.example.seed;

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
        moveToBasketView();

        popupDialog();


    }

    public void moveToBasketView() {
        RelativeLayout button = findViewById(R.id.buy_act_back_basket_btn);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });
    }


    public void popupDialog() {
        RelativeLayout button = findViewById(R.id.buy_act_bottom_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BuyProductDialog dialog = new BuyProductDialog(BuyProductActivity.this);
                dialog.show();
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
