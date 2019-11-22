package com.example.seed;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.seed.DB.SharedPreferenceController;
import com.example.seed.Get.GetBasketAddRequest;
import com.example.seed.Get.GetDetailResponse;
import com.example.seed.Network.ApplicationController;
import com.example.seed.Network.NetworkService;

import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapReverseGeoCoder;
import net.daum.mf.map.api.MapView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

// Customized by SY

public class DetailProductsActivity extends AppCompatActivity implements MapView.MapViewEventListener, MapView.POIItemEventListener, MapReverseGeoCoder.ReverseGeoCodingResultListener {

    ApplicationController applicationController = new ApplicationController();
    NetworkService networkService = applicationController.buildNetworkService();

    private static final String LOG_TAG = "DetailProductActivity";

    MapView mapView;
    private MapPoint stLocation;
    private MapPoint.GeoCoordinate mapPointGeo;
    private MapPOIItem mDefaultMarker;
    ViewGroup mapViewContainer;
    public double latitude;
    public double longitude;


    private static final int GPS_ENABLE_REQUEST_CODE = 2001;
    private static final int PERMISSIONS_REQUEST_CODE = 100;
    String[] REQUIRED_PERMISSIONS = {Manifest.permission.ACCESS_FINE_LOCATION};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_products);

        createSmallMapView();

        moveToMainView();
        setValuesFromItems();

        popupDialog();
    }


    // Customized by MS
    public void popupDialog() {

        Bundle extras = getIntent().getExtras();
        final int idProduct = extras.getInt("idProduct");
        final String token = SharedPreferenceController.getMyId(this);

        RelativeLayout button = findViewById(R.id.detail_act_bottom_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DetailProductDialog dialog = new DetailProductDialog(DetailProductsActivity.this);

                Call<GetBasketAddRequest> call = networkService.getBasketAddRequest("application/json", token, idProduct);
                call.enqueue(new Callback<GetBasketAddRequest>() {
                    @Override
                    public void onResponse(Call<GetBasketAddRequest> call, Response<GetBasketAddRequest> response) {
                        if (response.isSuccessful()) {
                            Log.v("통신 성공", "통신 성공");
                        }
                    }

                    @Override
                    public void onFailure(Call<GetBasketAddRequest> call, Throwable t) {
                        Log.v("통신 실패", t.toString());
                    }
                });

                dialog.show();
            }
        });
    }
    // Customized by MS

    public void setValuesFromItems() {

        Bundle extras = getIntent().getExtras();
        int idProduct = extras.getInt("idProduct");

        final TextView name_tv = (TextView) findViewById(R.id.detail_act_name);
        final TextView quantity_tv = (TextView) findViewById(R.id.detail_act_products_quantity);
        final TextView comment_tv = (TextView) findViewById(R.id.detail_act_comments);
        final ImageView image_iv = (ImageView) findViewById(R.id.detail_act_img);
        final TextView originPrice_tv = (TextView) findViewById(R.id.detail_act_products_price_origin);
        final TextView salePrice_tv = (TextView) findViewById(R.id.detail_act_products_price_sale);
        final TextView discount_tv = (TextView) findViewById(R.id.detail_act_products_discount);
        final TextView expDate_tv = (TextView) findViewById(R.id.detail_act_products_timeleft);
        final TextView stoName_tv = (TextView) findViewById(R.id.detail_act_store_name);
        final TextView gpsStoName_tv = (TextView) findViewById(R.id.detail_act_gps_store_name);
        final TextView address_tv = (TextView) findViewById(R.id.detail_act_store_location);
        final TextView gpsAddress_tv = (TextView) findViewById(R.id.detail_act_gps_store_location);
        final TextView tel_tv = (TextView) findViewById(R.id.detail_act_store_num1);
        final de.hdodenhof.circleimageview.CircleImageView userProfile_iv = (de.hdodenhof.circleimageview.CircleImageView) findViewById(R.id.detail_act_img_store);

        final FrameLayout cancel_fl = (FrameLayout) findViewById(R.id.detail_act_products_price_origin_cancel);

        // quantity_tv.setText(String.valueOf(quantity));

        Call<GetDetailResponse> call = networkService.getDetailResponse("application/json", idProduct);
        call.enqueue(new Callback<GetDetailResponse>() {
            @Override
            public void onResponse(Call<GetDetailResponse> call, Response<GetDetailResponse> response) {
                if (response.isSuccessful()) {
                    String name = response.body().data.proName;
                    int quantity = response.body().data.quantity;
                    String comment = response.body().data.comment;
                    String image = response.body().data.image;
                    int originPrice = response.body().data.originPrice;
                    int salePrice = response.body().data.salePrice;
                    int expDate = response.body().data.expDate;
                    String stoName = response.body().data.stoName;
                    String address = response.body().data.address;
                    double lat = response.body().data.lat;
                    latitude = lat;
                    double log = response.body().data.log;
                    longitude = log;
                    String tel = response.body().data.tel;
                    String userProfile = response.body().data.userProfile;

                    name_tv.setText(name);
                    quantity_tv.setText(String.valueOf(quantity));
                    comment_tv.setText(comment);
                    Glide.with(getApplicationContext())
                            .load(image)
                            .into(image_iv);
                    originPrice_tv.setText(String.valueOf(originPrice));
                    salePrice_tv.setText(String.valueOf(salePrice));
                    discount_tv.setText(String.valueOf(Math.round((float) (originPrice - salePrice) / (float) originPrice * 100)));

                    if (expDate == 0)
                        expDate_tv.setText("DAY");
                    else
                        expDate_tv.setText(String.valueOf(expDate));

                    stoName_tv.setText(stoName);
                    gpsStoName_tv.setText(stoName);
                    address_tv.setText(address);
                    gpsAddress_tv.setText(address);
                    tel_tv.setText(tel);
                    Glide.with(getApplicationContext())
                            .load(userProfile)
                            .into(userProfile_iv);

                    cancel_fl.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                        @Override
                        public void onGlobalLayout() {
                            int len = originPrice_tv.getWidth();

                            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                            params.width = len + 10;
                            params.gravity = Gravity.CENTER_VERTICAL;
                            cancel_fl.setForegroundGravity(Gravity.CENTER_VERTICAL);
                            cancel_fl.setLayoutParams(params);

                            originPrice_tv.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<GetDetailResponse> call, Throwable t) {
                Log.v("통신 실패", t.toString());
            }
        });

    }

    public void createSmallMapView() {
        mapView = new MapView(this);
        mapViewContainer = (ViewGroup) findViewById(R.id.map_view);
        mapViewContainer.addView(mapView);

        mapView.setMapViewEventListener(this);
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        mapView.setMapViewEventListener(this);
        mapView.setShowCurrentLocationMarker(false);
    }

    @Override
    public void onMapViewInitialized(MapView mapView) {
        mapView.setMapCenterPoint(MapPoint.mapPointWithGeoCoord(latitude, longitude), true);
        Log.d("위도", String.valueOf(latitude));
        Log.d("경도", String.valueOf(longitude));

        MapPOIItem marker = new MapPOIItem();
        marker.setMapPoint(MapPoint.mapPointWithCONGCoord(latitude, longitude));
        //Log.d("위치", String.valueOf(MapPoint.mapPointWithCONGCoord(latitude, longitude)));
        marker.setMarkerType(MapPOIItem.MarkerType.BluePin);
        mapView.addPOIItem(marker);
        // mapView.setMapCenterPoint(stLocation, true);
        mapView.setPOIItemEventListener(new MapView.POIItemEventListener() {
            @Override
            public void onPOIItemSelected(MapView mapView, MapPOIItem mapPOIItem) {
                String name = mapPOIItem.getItemName();
            }

            @Override
            public void onCalloutBalloonOfPOIItemTouched(MapView mapView, MapPOIItem mapPOIItem) {

            }

            @Override
            public void onCalloutBalloonOfPOIItemTouched(MapView mapView, MapPOIItem mapPOIItem, MapPOIItem.CalloutBalloonButtonType calloutBalloonButtonType) {

            }

            @Override
            public void onDraggablePOIItemMoved(MapView mapView, MapPOIItem mapPOIItem, MapPoint mapPoint) {

            }
        });

    }

    @Override
    public void onMapViewCenterPointMoved(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onMapViewZoomLevelChanged(MapView mapView, int i) {

    }

    @Override
    public void onMapViewSingleTapped(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onMapViewDoubleTapped(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onMapViewLongPressed(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onMapViewDragStarted(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onMapViewDragEnded(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onMapViewMoveFinished(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onPOIItemSelected(MapView mapView, MapPOIItem mapPOIItem) {

    }

    @Override
    public void onCalloutBalloonOfPOIItemTouched(MapView mapView, MapPOIItem mapPOIItem) {

    }

    @Override
    public void onCalloutBalloonOfPOIItemTouched(MapView mapView, MapPOIItem mapPOIItem, MapPOIItem.CalloutBalloonButtonType calloutBalloonButtonType) {

    }

    @Override
    public void onDraggablePOIItemMoved(MapView mapView, MapPOIItem mapPOIItem, MapPoint mapPoint) {

    }

    @Override
    public void onReverseGeoCoderFoundAddress(MapReverseGeoCoder mapReverseGeoCoder, String s) {
        mapReverseGeoCoder.toString();
        // onFinishReverseGeoCoding(s);
    }

    @Override
    public void onReverseGeoCoderFailedToFindAddress(MapReverseGeoCoder mapReverseGeoCoder) {

    }





    public void moveToMainView() {
        RelativeLayout button = findViewById(R.id.detail_act_back_mainpage);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DetailProductsActivity.class);
                finish();
            }
        });
    }


}

