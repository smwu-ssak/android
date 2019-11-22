package com.example.seed;

import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.seed.DB.SharedPreferenceController;
import com.example.seed.Get.GetBuyCompletedItemResponse;
import com.example.seed.Network.ApplicationController;
import com.example.seed.Network.NetworkService;
import com.example.seed.data.BuyCompletedData;
import com.example.seed.data.BuyCompletedProductData;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

// Customized by SY

public class BuyCompletedActivity extends AppCompatActivity {

    private ExpandableListView listView;
    ApplicationController applicationController = new ApplicationController();
    NetworkService networkService = applicationController.buildNetworkService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_completed);

        requestBuyListToServer();
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

    public void requestBuyListToServer() {

        Display newDisplay = getWindowManager().getDefaultDisplay();
        final int width = newDisplay.getWidth();

        final ArrayList<BuyCompletedData> DataList = new ArrayList<>();
        listView = (ExpandableListView)findViewById(R.id.mylist);

        final String token = SharedPreferenceController.getMyId(this);
        Call<GetBuyCompletedItemResponse> call = networkService.getBuyCompletedItemResponse("application/json", token);
        call.enqueue(new Callback<GetBuyCompletedItemResponse>() {
            @Override
            public void onResponse(Call<GetBuyCompletedItemResponse> call, Response<GetBuyCompletedItemResponse> response) {
                if (response.body().data.size() == 0) {
                    Toast.makeText(getApplicationContext(), "구출한 상품이 없습니다!", Toast.LENGTH_SHORT).show();
                    finish();
                }
                else if (response.isSuccessful()) {
                    int total = 0;
                    for (int i=0; i<response.body().data.size(); i++) {
                        BuyCompletedData temp = new BuyCompletedData(response.body().data.get(i).time);
                        for (int j=0; j<response.body().getData().get(i).data.size(); j++) {
                            temp.data.add(response.body().getData().get(i).data.get(j));
                            total += response.body().getData().get(i).data.get(j).sum;
                        }
                        DataList.add(temp);
                    }
                    TextView tv = findViewById(R.id.rv_item_buy_completed_price_total_bottom);
                    tv.setText(String.valueOf(total));
                    BuyCompletedExpandAdapter adapter = new BuyCompletedExpandAdapter(getApplicationContext(), R.layout.activity_buy_completed_parent, R.layout.activity_buy_completed_child, DataList);
                    listView.setIndicatorBounds(width-50, width);
                    listView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<GetBuyCompletedItemResponse> call, Throwable t) {
                Log.v("통신 실패", t.toString());
            }
        });

    }



}
