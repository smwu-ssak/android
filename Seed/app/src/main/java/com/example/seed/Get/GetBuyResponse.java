package com.example.seed.Get;

import com.example.seed.data.BuyProductData;
import java.util.ArrayList;

// Customized by SY

public class GetBuyResponse {
    public int status;
    public boolean success;
    public String message;
    public ArrayList<BuyProductData> data;

    public ArrayList<BuyProductData> getData() {
        return data;
    }

    public void setData(ArrayList<BuyProductData> data){
        this.data = data;
    }
}
