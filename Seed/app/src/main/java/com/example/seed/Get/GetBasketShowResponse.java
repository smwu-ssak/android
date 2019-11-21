package com.example.seed.Get;

// Customized by SY

import com.example.seed.data.BasketData;

import java.util.ArrayList;

public class GetBasketShowResponse {

    public int status;
    public boolean success;
    public String message;
    public ArrayList<BasketData> data;

    public ArrayList<BasketData> getData() {
        return data;
    }

    public void setData(ArrayList<BasketData> data){
        this.data = data;
    }

}
