package com.example.seed;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.seed.data.BuyProductData;

import java.util.ArrayList;

// Customized by MS

public class BuyProductAdapter extends RecyclerView.Adapter<BuyProductAdapter.BuyProductViewHolder> implements OnBuyProductClickListener {

    ArrayList<BuyProductData> items;
    OnBuyProductClickListener listener;

    public BuyProductAdapter(ArrayList<BuyProductData> items) {
        this.items = items;
    }

    @Override
    public BuyProductViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = inflater.inflate(R.layout.rv_item_buy_products, viewGroup, false);
        return new BuyProductViewHolder(itemView, this);
    }

    @Override
    public void onBindViewHolder(BuyProductViewHolder viewHolder, int position) {
        BuyProductData item = items.get(position);
        viewHolder.buyImage.setImageResource(item.getImage());
        viewHolder.buyName.setText(item.getName());
        viewHolder.buyQuantity.setText(String.valueOf(item.getQuantity()));
//        viewHolder.buyTimePickup.setText(String.);
        viewHolder.buyTotalPrice.setText(String.valueOf(item.getTotalPrice()));
        viewHolder.buyOptionSelected.setText(String.valueOf(item.getOptionSelected()));

    }

    public void setOnItemClickListener(OnBuyProductClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onItemClick(BuyProductViewHolder holder, View view, int position) {
        if (listener != null)
            listener.onItemClick(holder, view, position);
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
//        protected TextView buyTimePickup;
        protected TextView buyTotalPrice;
        protected TextView buyOptionSelected;

        public BuyProductViewHolder(final View itemView, final OnBuyProductClickListener listener) {
            super(itemView);

            buyImage = itemView.findViewById(R.id.rv_item_buy_image);
            buyName = itemView.findViewById(R.id.rv_item_buy_products_name);
            buyQuantity = itemView.findViewById(R.id.rv_item_buy_products_quantity);
//            buyTimePickup = itemView.findViewById(R.id.rv_item_buy_price);
            buyTotalPrice = itemView.findViewById(R.id.rv_item_buy_price);
            buyOptionSelected = itemView.findViewById(R.id.rv_item_buy_option);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (listener != null)
                        listener.onItemClick(BuyProductViewHolder.this, view, position);
                }
            });
        }
    }
}
