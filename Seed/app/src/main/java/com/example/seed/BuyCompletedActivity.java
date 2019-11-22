package com.example.seed;

import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.seed.data.BuyCompletedData;
import com.example.seed.data.BuyCompletedProductData;

import java.util.ArrayList;

// Customized by SY

public class BuyCompletedActivity extends AppCompatActivity {

    private ExpandableListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_completed);

        setProducts();
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

    public void setProducts() {

        Display newDisplay = getWindowManager().getDefaultDisplay();
        int width = newDisplay.getWidth();

        ArrayList<BuyCompletedData> DataList = new ArrayList<>();
        listView = (ExpandableListView)findViewById(R.id.mylist);

        BuyCompletedData temp = new BuyCompletedData("2019-11-14");
        temp.data.add(new BuyCompletedProductData("싱그러운 영동 사과", 2600, "2019-11-14"));
        temp.data.add(new BuyCompletedProductData("파릇한 브로콜리", 5700, "2019-11-14"));
        DataList.add(temp);

        temp = new BuyCompletedData("2019-11-15");
        temp.data.add(new BuyCompletedProductData("싱그러운 영동사과", 2700, "2019-11-15"));
        temp.data.add(new BuyCompletedProductData("파릇한 브로콜리", 1700, "2019-11-15"));
        DataList.add(temp);

        temp = new BuyCompletedData("2019-11-16");
        temp.data.add(new BuyCompletedProductData("영동 사과", 26050, "2019-11-16"));
        temp.data.add(new BuyCompletedProductData("브로콜리", 500, "2019-11-16"));
        DataList.add(temp);

        BuyCompletedExpandAdapter adapter = new BuyCompletedExpandAdapter(getApplicationContext(), R.layout.activity_buy_completed_parent, R.layout.activity_buy_completed_child, DataList);
        listView.setIndicatorBounds(width-50, width);
        listView.setAdapter(adapter);
    }



}
