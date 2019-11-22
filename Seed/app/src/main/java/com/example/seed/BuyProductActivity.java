package com.example.seed;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.seed.DB.SharedPreferenceController;
import com.example.seed.Get.GetBuyResponse;
import com.example.seed.Network.ApplicationController;
import com.example.seed.Network.NetworkService;
import com.example.seed.Post.PostBuyCompleteResponse;
import com.example.seed.data.BuyProductData;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

// Customized by MS

public class BuyProductActivity extends AppCompatActivity {

    ApplicationController applicationController = new ApplicationController();
    NetworkService networkService = applicationController.buildNetworkService();
    static ArrayList<BuyProductData> data;
    static BuyProductAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_products);

        setProducts();
        moveToBasketView();

        popupDialog();
    }

    @Override
    public void onBackPressed() {
        // super.onBackPressed();
    }

    public void moveToBasketView() {
        RelativeLayout button = findViewById(R.id.buy_act_back_basket_btn);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), BasketActivity.class));
                finish();
            }
        });
    }

    public void popupDialog() {
        RelativeLayout button = findViewById(R.id.buy_act_bottom_btn);

        final String token = SharedPreferenceController.getMyId(this);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BuyProductDialog dialog = new BuyProductDialog(BuyProductActivity.this);

                Call<PostBuyCompleteResponse> call = networkService.postBuyCompleteResponse("application/json", token);
                call.enqueue(new Callback<PostBuyCompleteResponse>() {
                    @Override
                    public void onResponse(Call<PostBuyCompleteResponse> call, Response<PostBuyCompleteResponse> response) {
                        if (response.isSuccessful()){
                            Log.v("통신 성공", "통신 성공");
                        }
                    }

                    @Override
                    public void onFailure(Call<PostBuyCompleteResponse> call, Throwable t) {
                        Log.v("통신 실패", t.toString());
                    }
                });
                dialog.show();
                
            }
        });
    }

    public void setProducts() {
        final RecyclerView recyclerView = findViewById(R.id.rv_buy_products);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        final String token = SharedPreferenceController.getMyId(this);
        Call<GetBuyResponse> call = networkService.getBuyResponse("application/json", token);
        call.enqueue(new Callback<GetBuyResponse>() {

            @Override
            public void onResponse(Call<GetBuyResponse> call, Response<GetBuyResponse> response) {
                data = response.body().getData();
                adapter = new BuyProductAdapter(data);
                recyclerView.setAdapter(adapter);
                recyclerView.addItemDecoration(new ProductRecyclerViewDecoration(15));

                int sum = 0;
                for (int i=0; i<data.size(); i++){
                    int price = data.get(i).getTotalPrice();
                    sum+=price;
                }
                TextView total_tv = (TextView)findViewById(R.id.buy_act_price_total);
                total_tv.setText(String.valueOf(sum));

            }

            @Override
            public void onFailure(Call<GetBuyResponse> call, Throwable t) {
                Log.v("통신 실패", t.toString());
            }
        });

    }

}
