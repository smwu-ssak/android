package com.example.seed.Get;

import com.example.seed.data.BuyCompletedData;
import java.util.ArrayList;

// Customized by SY

public class GetBuyCompletedItemResponse {

    public int status;
    public boolean success;
    public String message;
    public ArrayList<BuyCompletedData> data;

    public ArrayList<BuyCompletedData> getData() { return data; }

    public void setData(ArrayList<BuyCompletedData> data){
        this.data = data;
    }

}
