package com.example.seed;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.seed.DB.SharedPreferenceController;
import com.example.seed.Get.GetMypageResponse;
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

        popupToast();
    }

    public void requestMyPageDataToServer(String token){
        Call<GetMypageResponse> call = networkService.getMypageResponse("application/json", token);
        call.enqueue(new Callback<GetMypageResponse>() {
            @Override
            public void onResponse(Call<GetMypageResponse> call, Response<GetMypageResponse> response) {
                if (response.isSuccessful()){
                    String name = response.body().data.userName;
                    String imgUrl = response.body().data.userProfile;

                    Log.e("마이페이지 이름", name);
                    Log.e("마이페이지 이미지", imgUrl);

                    de.hdodenhof.circleimageview.CircleImageView targetImg = (de.hdodenhof.circleimageview.CircleImageView)findViewById(R.id.mypage_act_user_profile);
                    Glide.with(getApplicationContext())
                            .load(imgUrl)
                            .into(targetImg);
                    TextView targetName = (TextView)findViewById(R.id.mypage_act_user_name);
                    targetName.setText(name);
                }
            }

            @Override
            public void onFailure(Call<GetMypageResponse> call, Throwable t) {
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


//    Customized by MS

    public void popupToast() {
        View.OnClickListener Listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.mypage_act_FAQ_btn:
                        Toast.makeText(MypageActivity.this, "아직 지원하지 않는 서비스입니다.", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.mypage_act_contact_btn:
                        Toast.makeText(MypageActivity.this, "아직 지원하지 않는 서비스입니다.", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.mypage_act_setting_btn:
                        Toast.makeText(MypageActivity.this, "아직 지원하지 않는 서비스입니다.", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        };
        
        findViewById(R.id.mypage_act_FAQ_btn).setOnClickListener(Listener);
        findViewById(R.id.mypage_act_contact_btn).setOnClickListener(Listener);
        findViewById(R.id.mypage_act_setting_btn).setOnClickListener(Listener);
    }

}
