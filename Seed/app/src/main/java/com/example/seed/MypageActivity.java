package com.example.seed;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MypageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage);

        moveToMainView();
        moveToBuyCompleted();
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
