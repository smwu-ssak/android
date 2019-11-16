package com.example.seed;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.seed.data.BasketData;

import java.util.ArrayList;

// Customized by MS

public class BasketAdapter extends RecyclerView.Adapter<BasketAdapter.BasketViewHolder> implements OnBasketClickListener {

    ArrayList<BasketData> items;
    OnBasketClickListener listener;

    public BasketAdapter(ArrayList<BasketData> items) {
        this.items = items;
    }

    @Override
    public BasketViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = inflater.inflate(R.layout.rv_item_basket_products, viewGroup, false);
        return new BasketViewHolder(itemView, this);
    }

    @Override
    public void onBindViewHolder(BasketViewHolder viewHolder, int position) {
        BasketData item = items.get(position);
        viewHolder.basketImage.setImageResource(item.getImage());
        viewHolder.basketName.setText(item.getName());
        viewHolder.basketQuantity.setText(String.valueOf(item.getQuantity()));
        viewHolder.basketTimePickup.setText(item.getTimePickup());
        viewHolder.basketSalePrice.setText(String.valueOf(item.getSalePrice()));
//        viewHolder.basketVinyl.setText(item.isVinyl());
//        viewHolder.basketPaper.
//        viewHolder.basketBox.
    }

    public void setOnItemClickListener(OnBasketClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onItemClick(BasketViewHolder holder, View view, int position) {
        if (listener != null)
            listener.onItemClick(holder, view, position);
    }

    @Override
    public int getItemCount() {
        if (items == null)
            return 0;
        return items.size();
    }

    public void addItem(BasketData item){
        items.add(item);
    }

    public void setItems(ArrayList<BasketData> items){
        this.items = items;
    }

    public BasketData getItem(int position){
        return items.get(position);
    }

    public void setItem(int position, BasketData item){
        items.set(position, item);
    }


    static class BasketViewHolder extends RecyclerView.ViewHolder {
        protected ImageView basketImage;
        protected TextView basketName;
        protected TextView basketQuantity;
        protected TextView basketTimePickup;
        protected TextView basketSalePrice;
        protected RadioButton basketVinyl;
        protected RadioButton basketPaper;
        protected RadioButton basketBox;

        public BasketViewHolder(final View itemView, final OnBasketClickListener listener) {
            super(itemView);

            basketImage = itemView.findViewById(R.id.rv_item_basket_image);
            basketName = itemView.findViewById(R.id.rv_item_basket_products_name);
            basketQuantity = itemView.findViewById(R.id.rv_item_basket_products_quantity);
            basketTimePickup = itemView.findViewById(R.id.rv_item_basket_time_pickup_btn);
            basketSalePrice = itemView.findViewById(R.id.rv_item_basket_price);
            basketVinyl = itemView.findViewById(R.id.rv_item_basket_vinyl);
            basketPaper = itemView.findViewById(R.id.rv_item_basket_paper);
            basketBox = itemView.findViewById(R.id.rv_item_basket_box);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (listener != null)
                        listener.onItemClick(BasketViewHolder.this, view, position);
                }
            });
        }
    }


}
