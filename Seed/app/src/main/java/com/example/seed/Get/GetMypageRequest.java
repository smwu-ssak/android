package com.example.seed.Get;

import com.example.seed.data.MypageData;
import java.util.ArrayList;

// Customized by SY

public class GetMypageRequest {

    int status;
    boolean success;
    String message;
    MypageData data;

    public MypageData getData() {
        return data;
    }

    public void setData(MypageData data){
        this.data = data;
    }

}
