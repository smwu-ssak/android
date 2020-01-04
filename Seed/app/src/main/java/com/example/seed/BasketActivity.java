package com.example.seed;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.seed.DB.SharedPreferenceController;
import com.example.seed.Get.GetBasketShowResponse;
import com.example.seed.Network.ApplicationController;
import com.example.seed.Network.NetworkService;
import com.example.seed.Post.PostBuyNowRequest;
import com.example.seed.data.BasketData;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

// Customized by MS

public class BasketActivity extends AppCompatActivity {

    ApplicationController applicationController = new ApplicationController();
    NetworkService networkService = applicationController.buildNetworkService();
    static ArrayList<BasketData> data;
    static BasketAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket);

        responseFromServer();
        moveToMainView();
        moveToBuyProduct();

    }

    @Override
    public void onBackPressed() {
        // super.onBackPressed();
    }

    public void moveToMainView() {
        RelativeLayout button = findViewById(R.id.basket_act_back_mainpage_btn);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }
        });
    }

    public void moveToBuyProduct() {
        final String token = SharedPreferenceController.getMyId(this);
        RelativeLayout button = findViewById(R.id.basket_act_bottom_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (data.size() == 0){
                    Toast.makeText(getApplicationContext(), "장바구니가 비어있습니다!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    finish();
                }

                ArrayList<JsonObject> jsonObject = new ArrayList<>();
                for(int i=0; i<data.size(); i++) {
                    if (data.get(i).getBuyNum() == 0)
                        continue;
                    JSONObject object = new JSONObject();
                    try {
                        object.put("quantity", data.get(i).getBuyNum());
                        object.put("packing", data.get(i).getPacking());
                        object.put("timePickup", data.get(i).getTimePickup());
                        Log.d("시간녀석", data.get(i).getTimePickup());
                        object.put("idBasket", data.get(i).getIdBasket());
                        data.get(i).setBuyNum(0);
                        JsonObject gsonObject = (JsonObject) new JsonParser().parse(object.toString());
                        jsonObject.add(gsonObject);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                Call<PostBuyNowRequest> call = networkService.postBuyNowRequest("application/json", token, jsonObject);
                call.enqueue(new Callback<PostBuyNowRequest>() {
                    @Override
                    public void onResponse(Call<PostBuyNowRequest> call, Response<PostBuyNowRequest> response) {
                        if (response.isSuccessful()){
                            if (response.isSuccessful()){
                                Log.v("통신 성공", "통신 성공");
                                Intent intent = new Intent(getApplicationContext(), BuyProductActivity.class);
                                startActivity(intent);
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<PostBuyNowRequest> call, Throwable t) {
                        Log.v("통신 실패", t.toString());
                    }
                });

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

    }

}
