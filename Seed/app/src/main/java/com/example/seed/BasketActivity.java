package com.example.seed;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.seed.DB.SharedPreferenceController;
import com.example.seed.Get.GetBasketShowResponse;
import com.example.seed.Network.ApplicationController;
import com.example.seed.Network.NetworkService;
import com.example.seed.data.BasketData;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

// Customized by MS

public class BasketActivity extends AppCompatActivity {

    ApplicationController applicationController = new ApplicationController();
    NetworkService networkService = applicationController.buildNetworkService();
    static ArrayList<BasketData> data = new ArrayList();
    static BasketAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket);

        responseFromServer();
        moveToMainView();
        moveToBuyProduct();

    }


    public void moveToMainView() {
        RelativeLayout button = findViewById(R.id.basket_act_back_mainpage_btn);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void moveToBuyProduct() {
        RelativeLayout button = findViewById(R.id.basket_act_bottom_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), BuyProductActivity.class);
                startActivity(intent);
            }
        });
    }


    public void responseFromServer() {
        final RecyclerView recyclerView = findViewById(R.id.rv_basket);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        final String token = SharedPreferenceController.getMyId(this);
        Call<GetBasketShowResponse> call = networkService.getBasketShowRespose("application/json", token);
        call.enqueue(new Callback<GetBasketShowResponse>() {
            @Override
            public void onResponse(Call<GetBasketShowResponse> call, Response<GetBasketShowResponse> response) {
                if (response.isSuccessful()){
                    data = response.body().getData();
                    adapter = new BasketAdapter(data, getApplicationContext());
                    recyclerView.setAdapter(adapter);
                    recyclerView.addItemDecoration(new ProductRecyclerViewDecoration(15));
                }
            }

            @Override
            public void onFailure(Call<GetBasketShowResponse> call, Throwable t) {
                Log.v("통신 실패", t.toString());
            }
        });

//        data.add(new BasketData(R.drawable.purchaseview_applephoto, "사과", "00시 00분", 0, 5, 1000, 0, 0));
//        data.add(new BasketData(R.drawable.purchaseview_broccoliphoto, "브로콜리", "00시 00분", 0, 4, 1500, 0, 0));
//        data.add(new BasketData(R.drawable.purchaseview_applephoto, "사과", "00시 00분", 0, 7, 800, 0, 0));
//        data.add(new BasketData(R.drawable.purchaseview_broccoliphoto, "브로콜리", "00시 00분", 0, 3, 700, 0, 0));

    }

}
