package com.example.seed.Network;

import com.example.seed.Get.GetMainResponse;
import com.example.seed.Get.GetMypageRequest;
import com.example.seed.Post.PostLoginResponse;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

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
    Call<GetMypageRequest> getMypageRequest (
            @Header("token") String token
    );

    // 메인 페이지 상품 리스트
    @GET("main")
    Call<GetMainResponse> getMainResponse (
            @Header("Content-type") String content_type
    );

}
