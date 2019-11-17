package com.example.seed;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.seed.data.BuyCompletedData;
import com.example.seed.data.BuyCompletedProductData;

import java.util.ArrayList;

// Customized by MS

public class BuyCompletedAdapter extends RecyclerView.Adapter<BuyCompletedAdapter.BuyCompletedViewHolder> {

    ArrayList<BuyCompletedData> items;

    public BuyCompletedAdapter(ArrayList<BuyCompletedData> items) {
        this.items = items;
    }

    @Override
    public BuyCompletedViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = inflater.inflate(R.layout.rv_item_buy_completed, viewGroup, false);
        return new BuyCompletedViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(BuyCompletedViewHolder viewHolder, int position) {
        BuyCompletedData item = items.get(position);

        viewHolder.completeTimePickup.setText(item.getTimePickup());

//        ArrayList sectionItems = itemlist.get(position).getName();
//        BuyCompletedProductAdapter buycompletedproductAdapter = new BuyCompletedProductAdapter(allItemsInSection);
//        BuyCompletedProductData itemin = itemlist.get(position);

    }

    @Override
    public int getItemCount() {
        if (items == null)
            return 0;
        return items.size();
    }

    static class BuyCompletedViewHolder extends RecyclerView.ViewHolder {
        protected TextView completeTimePickup;

        protected RecyclerView recycler_productList;

        public BuyCompletedViewHolder(final View itemView) {
            super(itemView);

            completeTimePickup = itemView.findViewById(R.id.rv_item_buy_completed_date);
            this.recycler_productList = (RecyclerView) itemView.findViewById(R.id.rv_buy_completed_products);

        }

    }

    public ArrayList<BuyCompletedProductData> allItemsInSection;

    public ArrayList<BuyCompletedProductData> getAllItemsInSection() {
        return allItemsInSection;
    }


    public void addItem(BuyCompletedData item){
        items.add(item);
    }

    public void setItems(ArrayList<BuyCompletedData> items){
        this.items = items;
    }

    public BuyCompletedData getItem(int position){
        return items.get(position);
    }

    public void setItem(int position, BuyCompletedData item){
        items.set(position, item);
    }


}
