package com.example.seed;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.kakao.auth.Session;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.MeResponseCallback;
import com.kakao.usermgmt.callback.MeV2ResponseCallback;
import com.kakao.usermgmt.response.MeV2Response;

import java.util.ArrayList;
import java.util.List;

// Customized by SY

public class KakaoSignupActivity extends Activity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestMe();
    }

    protected void requestMe() { //유저의 정보를 받아오는

        List<String> keys = new ArrayList<>();
        keys.add("properties.id");
        keys.add("properties.profile_image");
        keys.add("kakao_account.email");

        UserManagement.getInstance().me(new MeV2ResponseCallback() {
            @Override
            public void onSessionClosed(ErrorResult errorResult) {
                Log.d("카카오 로그인", "실패");
                redirectLoginActivity();
            }

            @Override
            public void onSuccess(MeV2Response result) {
                Log.d("카카오 로그인", "성공");
                Log.d("카카오 user id", String.valueOf(result.getId()));
                Log.d("카카오 profile image", String.valueOf(result.getKakaoAccount().getProfile()));
                Log.d("카카오 email", String.valueOf(result.getKakaoAccount().getEmail()));
                Log.e("토큰", Session.getCurrentSession().getTokenInfo().getAccessToken());
                redirectMainActivity();
            }
        });

    }

    private void redirectMainActivity() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    protected void redirectLoginActivity() {
        final Intent intent = new Intent(this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
        finish();
    }

}
