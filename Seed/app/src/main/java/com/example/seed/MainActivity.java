package com.example.seed;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.seed.Get.GetMainResponse;
import com.example.seed.Network.ApplicationController;
import com.example.seed.Network.NetworkService;
import com.example.seed.data.MainProductData;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

// Customized by SY

public class MainActivity extends AppCompatActivity {

    ApplicationController applicationController = new ApplicationController();
    NetworkService networkService = applicationController.buildNetworkService();
    static ArrayList<MainProductData> data = new ArrayList();
    static MainProductAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setflipperView();
        responseFromServer();
        checkMyLocation();
        moveToMypage();
        moveToBasket();
//        LocationService();
    }

//    public void LocationService(){
//        ImageView button = findViewById(R.id.main_act_gps_btn);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startLocationService();
//            }
//        });
//    }
//
//    public void startLocationService(){
//        LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
//        try {
//            Location location = manager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
//            if (location != null){
//                double latitude = location.getLatitude();
//                double longitude = location.getLongitude();
//                String message = "최근 위치-> 위도: "+latitude+"\n경도: "+longitude;
//                Log.d("message: ", message);
//            }
//        } catch(SecurityException e){
//            e.printStackTrace();
//        }
//    }

    public void setScrollViewFocus(){
        final ScrollView scrollView = findViewById(R.id.main_scrollView);
        scrollView.post(new Runnable() {
            @Override
            public void run() {
                scrollView.fullScroll(ScrollView.FOCUS_UP);
            }
        });
    }

    public void moveToMypage(){
        RelativeLayout button = findViewById(R.id.main_act_topbar_mypage_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MypageActivity.class);
                startActivity(intent);
            }
        });
    }

    public void checkMyLocation(){
        ImageView button = findViewById(R.id.main_act_gps_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SearchMyLocation.class);
                startActivity(intent);
            }
        });
    }

    public void moveToBasket(){
        RelativeLayout button = findViewById(R.id.main_act_buy_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), BasketActivity.class);
                startActivity(intent);
            }
        });
    }

    private ViewFlipper v_flipper;

    public void setflipperView(){
        int images[] = {
                R.drawable.mainview_slide1,
                R.drawable.mainview_slide2
        };
        v_flipper = findViewById(R.id.main_act_img_slide);
        for(int image: images){
            flipperImages(image);
        }
    }

    public void flipperImages(int image){
        ImageView imageView = new ImageView(this);
        imageView.setBackgroundResource(image);

        v_flipper.addView(imageView);
        v_flipper.setFlipInterval(3500);
        v_flipper.setAutoStart(true);

//        v_flipper.setInAnimation(this, android.R.anim.slide_out_right);
//        v_flipper.setOutAnimation(this, android.R.anim.slide_in_left);
    }

    public void responseFromServer() {
        final RecyclerView recyclerView = findViewById(R.id.rv_main);

        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        Call<GetMainResponse> getMainResponse;
        getMainResponse = networkService.getMainResponse("application/json");
        getMainResponse.enqueue(new Callback<GetMainResponse>() {
            @Override
            public void onResponse(Call<GetMainResponse> call, Response<GetMainResponse> response) {
                if (response.isSuccessful()) {
                    data = response.body().getData();
                    // int i = response.body().getData().get(0).getIdProduct();
                    adapter = new MainProductAdapter(data);
                    recyclerView.setAdapter(adapter);
                    recyclerView.addItemDecoration(new ProductRecyclerViewDecoration(15));
                    adapter.notifyDataSetChanged();

                    adapter.setOnItemClickListener(new OnMainProductClickListener() {
                        @Override
                        public void onItemClick(MainProductAdapter.MainProductViewHolder holder, View view, int position) {
                            Intent intent = new Intent(getApplicationContext(), DetailProductsActivity.class);
                            MainProductData item = adapter.getItem(position);
                            int idx = recyclerView.getChildAdapterPosition(view);
                            intent.putExtra("idProduct", item.getIdProduct());
//                            intent.putExtra("name", item.getName());
//                            intent.putExtra("quantity", item.getQuantity());
//                            intent.putExtra("originPrice", item.getOriginPrice());
//                            intent.putExtra("salePrice", item.getSalePrice());
//                            intent.putExtra("discount", Math.round((float)(item.getOriginPrice()-item.getSalePrice())/(float)item.getOriginPrice()*100));
//                            intent.putExtra("place", item.getPlace());
                            startActivity(intent);
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<GetMainResponse> call, Throwable t) {
                Log.v("통신 실패", t.toString());
            }
        });

//        data.add(new MainProductData(R.drawable.rv_main_apple, "사과", 5, 10000, 6000, 0, "프라임마트"));
//        data.add(new MainProductData(R.drawable.rv_main_broccoli, "브로콜리", 7, 100, 50, 0, "눈송마트"));
//        data.add(new MainProductData(R.drawable.rv_main_apple, "사과", 5, 1000, 800, 0, "프라임마트"));
//        data.add(new MainProductData(R.drawable.rv_main_broccoli, "브로콜리", 7, 1000, 850, 0, "눈송마트"));
//        data.add(new MainProductData(R.drawable.rv_main_apple, "사과", 5, 1000, 500, 0, "프라임마트"));
//        data.add(new MainProductData(R.drawable.rv_main_broccoli, "브로콜리", 7, 1000, 800, 0, "눈송마트"));

    }

    // Customized by MS
    // 뒤로 가기 버튼 두 번 눌러야 종료되게
    private long time = 0;
    @Override
    public void onBackPressed() {
        //super.onBackPressed();

        if (System.currentTimeMillis() >= 2000 + time) {
            time = System.currentTimeMillis();
            Toast.makeText(getApplicationContext(), "뒤로 가기 버튼을 한 번 더 누르면 종료합니다.", Toast.LENGTH_SHORT).show();
        } else if (System.currentTimeMillis() < 2000 + time) {
            finish();
        }
    }


}
