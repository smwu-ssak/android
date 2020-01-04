package com.example.seed;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.seed.data.BuyProductData;

import java.util.ArrayList;

// Customized by MS

public class BuyProductAdapter extends RecyclerView.Adapter<BuyProductAdapter.BuyProductViewHolder> {

    ArrayList<BuyProductData> items;


    public BuyProductAdapter(ArrayList<BuyProductData> items) {
        this.items = items;
    }

    @Override
    public BuyProductViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = inflater.inflate(R.layout.rv_item_buy_products, viewGroup, false);
        return new BuyProductViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(BuyProductViewHolder viewHolder, int position) {
        BuyProductData item = items.get(position);
        Glide.with(viewHolder.itemView.getContext())
                .load(item.getImage())
                .into(viewHolder.buyImage);
        viewHolder.buyName.setText(item.getName());
        viewHolder.buyQuantity.setText(String.valueOf(item.getQuantity()));
        viewHolder.buyTotalPrice.setText(String.valueOf(item.getTotalPrice()));

        Integer pickHour = Integer.valueOf(item.getTimePickup().substring(11, 13));
        pickHour = pickHour+9;
        String pickHourT = String.valueOf(pickHour);

        viewHolder.buyTimePickupHour.setText(pickHourT);
        Log.d("시간아", item.getTimePickup().substring(11, 13));
        Log.d("시간아", item.getTimePickup());
        viewHolder.buyTimePickupMin.setText(item.getTimePickup().substring(14, 16));

        if (item.getPacking() == 0)
            viewHolder.buyOptionSelected.setText("봉투 없이");
        if (item.getPacking() == 100)
            viewHolder.buyOptionSelected.setText("싹 종이 봉투에 넣어");
        if (item.getPacking() == 1000)
            viewHolder.buyOptionSelected.setText("싹 용기에 넣어");

    }

    @Override
    public int getItemCount() {
        if (items == null)
            return 0;
        return items.size();
    }

//날짜 String to Date    public void

    public void addItem(BuyProductData item){
        items.add(item);
    }

    public void setItems(ArrayList<BuyProductData> items){
        this.items = items;
    }

    public BuyProductData getItem(int position){
        return items.get(position);
    }

    public void setItem(int position, BuyProductData item){
        items.set(position, item);
    }


    static class BuyProductViewHolder extends RecyclerView.ViewHolder {
        protected ImageView buyImage;
        protected TextView buyName;
        protected TextView buyQuantity;
        protected TextView buyTimePickupHour;
        protected TextView buyTimePickupMin;
        protected TextView buyTotalPrice;
        protected TextView buyOptionSelected;

        public BuyProductViewHolder(final View itemView) {
            super(itemView);

            buyImage = itemView.findViewById(R.id.rv_item_buy_image);
            buyName = itemView.findViewById(R.id.rv_item_buy_products_name);
            buyQuantity = itemView.findViewById(R.id.rv_item_buy_products_quantity);
            buyTimePickupHour = itemView.findViewById(R.id.rv_item_buy_time_pickup_hour);
            buyTimePickupMin = itemView.findViewById(R.id.rv_item_buy_time_pickup_min);
            buyTotalPrice = itemView.findViewById(R.id.rv_item_buy_price);
            buyOptionSelected = itemView.findViewById(R.id.rv_item_buy_option);

        }
    }
}
