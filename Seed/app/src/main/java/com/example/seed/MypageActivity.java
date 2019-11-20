package com.example.seed;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.seed.DB.SharedPreferenceController;
import com.example.seed.Get.GetMypageRequest;
import com.example.seed.Network.ApplicationController;
import com.example.seed.Network.NetworkService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

// Customized by SY

public class MypageActivity extends AppCompatActivity {

    ApplicationController applicationController = new ApplicationController();
    NetworkService networkService = applicationController.buildNetworkService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage);

        Log.d("내 아이디", SharedPreferenceController.getMyId(this));
        String token = SharedPreferenceController.getMyId(this);
        requestMyPageDataToServer(token);

        moveToMainView();
        moveToBuyCompleted();
    }

    public void requestMyPageDataToServer(String token){
        Call<GetMypageRequest> call = networkService.getMypageRequest(token);
        call.enqueue(new Callback<GetMypageRequest>() {
            @Override
            public void onResponse(Call<GetMypageRequest> call, Response<GetMypageRequest> response) {
//                String name = response.body().getData().getUserName();
//                String imgUrl = response.body().getData().getUserProfile();
//                Log.e("마이페이지", String.valueOf(response.body().getData()));
//                Log.e("마이페이지 이름", name);
//                Log.e("마이페이지 이미지", imgUrl);
//
//                de.hdodenhof.circleimageview.CircleImageView targetImg = (de.hdodenhof.circleimageview.CircleImageView)findViewById(R.id.mypage_act_user_profile);
//                Glide.with(getApplicationContext())
//                        .load(imgUrl)
//                        .into(targetImg);
//                TextView targetName = (TextView)findViewById(R.id.mypage_act_user_name);
//                targetName.setText(name);
            }

            @Override
            public void onFailure(Call<GetMypageRequest> call, Throwable t) {
                Log.v("통신 실패", t.toString());
            }
        });
    }

    public void moveToMainView() {
        RelativeLayout button = findViewById(R.id.mypage_act_back_btn);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void moveToBuyCompleted(){
        TextView button = findViewById(R.id.mypage_act_buy_list_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), BuyCompletedActivity.class);
                startActivity(intent);
            }
        });
    }

}
