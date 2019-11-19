package com.example.seed.Network;

import com.example.seed.Post.PostLoginResponse;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface NetworkService {

    // Customized by SY

    // 카카오톡 로그인
    @FormUrlEncoded
    @POST("user")
    Call<PostLoginResponse> postLoginResponse (
            @Header("Content-Type") String content_type,
            @Body() JsonObject body
    );

//    마이페이지 프로필 내역 확인
//    @GET("mypage")
//    Call<MypageRequest> mypageRequest {
//
//    }


    // Customized by MS

}
