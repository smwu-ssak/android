package com.example.seed;

// Customized by MS

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.seed.data.BuyCompletedProductData;

import java.util.ArrayList;

public class BuyCompletedProductAdapter extends RecyclerView.Adapter<BuyCompletedProductAdapter.BuyCompletedProductViewHolder> {

    ArrayList<BuyCompletedProductData> items;

    public BuyCompletedProductAdapter(ArrayList<BuyCompletedProductData> items) {
        this.items = items;
    }

    @Override
    public BuyCompletedProductViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = inflater.inflate(R.layout.rv_item_buy_completed, viewGroup, false);
        return new BuyCompletedProductViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(BuyCompletedProductViewHolder viewHolder, int position) {
        BuyCompletedProductData item = items.get(position);
        viewHolder.completeName.setText(item.getName());
        viewHolder.completeTotalPrice.setText(item.getTotalPrice());
    }

    @Override
    public int getItemCount() {
        if (items == null)
            return 0;
        return items.size();
    }

    public void addItem(BuyCompletedProductData item){
        items.add(item);
    }

    public void setItems(ArrayList<BuyCompletedProductData> items){
        this.items = items;
    }

    public BuyCompletedProductData getItem(int position){
        return items.get(position);
    }

    public void setItem(int position, BuyCompletedProductData item){
        items.set(position, item);
    }


    static class BuyCompletedProductViewHolder extends RecyclerView.ViewHolder {
        protected TextView completeName;
        protected TextView completeTotalPrice;
        protected TextView completeTimePickup;

        public BuyCompletedProductViewHolder(final View itemView) {
            super(itemView);

            completeName = itemView.findViewById(R.id.rv_item_buy_completed_products_name);
            completeTotalPrice = itemView.findViewById(R.id.rv_item_buy_completed_price_total_bottom);
            completeTimePickup = itemView.findViewById(R.id.rv_item_buy_completed_date);

        }
    }

}
