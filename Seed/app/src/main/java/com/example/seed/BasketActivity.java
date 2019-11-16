package com.example.seed;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.seed.data.BasketData;

import java.util.ArrayList;

// Customized by MS

public class BasketActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket);

        setProducts();
        moveToMainView();
        moveToBuyProduct();

    }

    public void setOptionSelected() {
        RadioGroup basketOptionGroup = (RadioGroup)findViewById(R.id.rv_item_basket_optionGroup);

        RadioButton basketOption1 = (RadioButton)findViewById(R.id.rv_item_basket_option_1);
        RadioButton basketOption2 = (RadioButton)findViewById(R.id.rv_item_basket_option_2);
        RadioButton basketOption3 = (RadioButton)findViewById(R.id.rv_item_basket_option_3);

        basketOptionGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int id) {
                RadioButton select = (RadioButton)findViewById(id);
            }
        });

    }

    public void moveToMainView() {
        RelativeLayout button = findViewById(R.id.basket_act_back_mainpage_btn);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void moveToBuyProduct(){
        RelativeLayout button = findViewById(R.id.basket_act_bottom_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), BuyProductActivity.class);
                startActivity(intent);
            }
        });
    }

    public void setProducts() {
        final RecyclerView recyclerView = findViewById(R.id.rv_basket);

        final ArrayList<BasketData> data = new ArrayList<>();

        data.add(new BasketData(R.drawable.purchaseview_applephoto, "사과", 2, 1000, false, false, false));
        data.add(new BasketData(R.drawable.purchaseview_broccoliphoto, "브로콜리", 2, 2000, false, false, false));
        data.add(new BasketData(R.drawable.purchaseview_applephoto, "사과", 2, 1000, false, false, false));
        data.add(new BasketData(R.drawable.purchaseview_broccoliphoto, "브로콜리", 2, 2000, false, false, false));

        final BasketAdapter adapter = new BasketAdapter(data);

        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new ProductRecyclerViewDecoration(15));


        //adapter.notifyDataSetChanged();
    }

}
