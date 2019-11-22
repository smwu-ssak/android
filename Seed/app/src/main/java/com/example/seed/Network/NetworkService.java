package com.example.seed.Network;

import com.example.seed.Get.GetBasketAddRequest;
import com.example.seed.Get.GetBasketShowResponse;
import com.example.seed.Get.GetBuyCompletedItemResponse;
import com.example.seed.Get.GetBuyResponse;
import com.example.seed.Get.GetDetailResponse;
import com.example.seed.Get.GetMainResponse;
import com.example.seed.Get.GetMypageResponse;
import com.example.seed.Post.PostBuyCompleteResponse;
import com.example.seed.Post.PostBuyNowRequest;
import com.example.seed.Post.PostLoginResponse;
import com.google.gson.JsonObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface NetworkService {

    // Customized by SY

    // 카카오톡 로그인
    @POST("user")
    Call<PostLoginResponse> postLoginResponse (
            @Header("Content-type") String content_type,
            @Body() JsonObject body
    );

    // 마이페이지 프로필 내역 확인
    @GET("mypage")
    Call<GetMypageResponse> getMypageResponse (
            @Header("Content-type") String content_type,
            @Header("token") String token
    );

    // 메인 페이지 상품 리스트
    @GET("main")
    Call<GetMainResponse> getMainResponse (
            @Header("Content-type") String content_type
    );

    // 상품 상세보기
    @GET("main/detail/{idProduct}")
    Call<GetDetailResponse> getDetailResponse (
            @Header("Content-type") String content_type,
            @Path("idProduct") int idProduct
    );

    // 상품 장바구니에 추가
    @GET("basket/{idProduct}")
    Call<GetBasketAddRequest> getBasketAddRequest (
            @Header("Content-type") String content_type,
            @Header("token") String token,
            @Path("idProduct") int idProduct
    );

    // 장바구니 조회
    @GET("basket")
    Call<GetBasketShowResponse> getBasketShowRespose (
            @Header("Content-type") String content_type,
            @Header("token") String token
    );

    // 구출하러 가기
    @POST("buy/now")
    Call<PostBuyNowRequest> postBuyNowRequest (
            @Header("Content-type") String content_type,
            @Header("token") String token,
            @Body() ArrayList<JsonObject> body
    );

    // 구출할 내역 조회
    @GET("buy")
    Call<GetBuyResponse> getBuyResponse (
            @Header("Content-type") String content_type,
            @Header("token") String token
    );

    // 구출 확정
    @POST("buy")
    Call<PostBuyCompleteResponse> postBuyCompleteResponse (
            @Header("Content-type") String content_type,
            @Header("token") String token
    );

    // 구출했던 내역 조회
    @GET("mypage/buy_list")
    Call<GetBuyCompletedItemResponse> getBuyCompletedItemResponse (
            @Header("Content-type") String content_type,
            @Header("token") String token
    );

}
