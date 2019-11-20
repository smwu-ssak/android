package com.example.seed.Get;

import com.example.seed.data.MainProductData;
import java.util.ArrayList;

// Customized by SY

public class GetMainResponse {

    int status;
    boolean success;
    String message;
    ArrayList<MainProductData> data;

    public ArrayList<MainProductData> getData() {
        return data;
    }

    public void setData(ArrayList<MainProductData> data){
        this.data = data;
    }

}
