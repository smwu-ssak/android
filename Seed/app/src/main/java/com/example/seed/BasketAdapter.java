package com.example.seed;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.recyclerview.widget.RecyclerView;

import com.example.seed.data.BasketData;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

// Customized by MS

public class BasketAdapter extends RecyclerView.Adapter<BasketAdapter.BasketViewHolder> implements OnBasketClickListener {

    ArrayList<BasketData> items;
    OnBasketClickListener listener;
    private Context context;


    public BasketAdapter(ArrayList<BasketData> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @Override
    public BasketViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = inflater.inflate(R.layout.rv_item_basket_products, viewGroup, false);
        return new BasketViewHolder(itemView, this);
    }

    @Override
    public void onBindViewHolder(final BasketViewHolder viewHolder, final int position) {
        final BasketData item = items.get(position);
        viewHolder.basketImage.setImageResource(item.getImage());
        viewHolder.basketName.setText(item.getName());
        viewHolder.basketBuyNum.setText(String.valueOf(item.getBuyNum()));
        viewHolder.basketTimePickup.setText(item.getTimePickup());
        viewHolder.basketSalePrice.setText(String.valueOf(item.getSalePrice()));
        viewHolder.basketSumPrice.setText(String.valueOf(item.getSumPrice()));

        final Calendar calendar = Calendar.getInstance();
        viewHolder.basketTimePickup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, final int month, int dayOf) {
                        calendar.set(Calendar.YEAR, year);
                        calendar.set(Calendar.MONTH, month);
                        calendar.set(Calendar.DAY_OF_MONTH, dayOf);


                        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker timePicker, int hourOf, int minOf) {
                                calendar.set(Calendar.HOUR_OF_DAY, hourOf);
                                calendar.set(Calendar.MINUTE, minOf);

                                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH시 MM분");

                                TextView timePickerTextView = viewHolder.basketTimePickup;
                                timePickerTextView.setText(simpleDateFormat.format(calendar.getTime()));
                            }
                        };

                        new TimePickerDialog(context, onTimeSetListener, calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE),false).show();
                    }
                };

                new DatePickerDialog(context, onDateSetListener, calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });



        viewHolder.basketRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int id) {
                switch (id) {
                    case R.id.rv_item_basket_vinyl: {
                        int check = Integer.parseInt(String.valueOf(item.getCheck()));
                        int sumPrice = Integer.parseInt(String.valueOf(item.getSumPrice()));
                        sumPrice = sumPrice - check;
                        item.setCheck(0);
                        check = 0;
                        sumPrice = sumPrice + check;
                        item.setSumPrice(sumPrice);
                        viewHolder.basketSumPrice.setText(String.valueOf(sumPrice));
                        break;
                    }
                    case R.id.rv_item_basket_paper: {
                        int check = Integer.parseInt(String.valueOf(item.getCheck()));
                        item.setCheck(100);
                        int sumPrice = Integer.parseInt(String.valueOf(item.getSumPrice()));
                        sumPrice = sumPrice - check;
                        check = 100;
                        sumPrice = sumPrice + check;
                        item.setSumPrice(sumPrice);
                        viewHolder.basketSumPrice.setText(String.valueOf(sumPrice));
                        break;
                    }
                    case R.id.rv_item_basket_box: {
                        int check = Integer.parseInt(String.valueOf(item.getCheck()));
                        item.setCheck(1000);
                        int sumPrice = Integer.parseInt(String.valueOf(item.getSumPrice()));
                        sumPrice = sumPrice - check;
                        check = 1000;
                        sumPrice = sumPrice + check;
                        item.setSumPrice(sumPrice);
                        viewHolder.basketSumPrice.setText(String.valueOf(sumPrice));
                        break;
                    }
                }
            }
        });

        viewHolder.plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int sumPrice = Integer.parseInt(String.valueOf(item.getSumPrice()));
                int maxNum = Integer.parseInt(String.valueOf(item.getQuantity()));
                int price = Integer.parseInt(String.valueOf(item.getSalePrice()));
                int buy = Integer.parseInt(String.valueOf(item.getBuyNum()));
                if (buy < maxNum) {
                    buy++;
                    item.setBuyNum(buy);
                    viewHolder.basketBuyNum.setText(String.valueOf(buy));
                    sumPrice = sumPrice + price;
                    item.setSumPrice(sumPrice);
                    viewHolder.basketSumPrice.setText(String.valueOf(sumPrice));
                }
            }
        });

        viewHolder.minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int sumPrice = Integer.parseInt(String.valueOf(item.getSumPrice()));
                int price = Integer.parseInt(String.valueOf(item.getSalePrice()));
                int buy = Integer.parseInt(String.valueOf(item.getBuyNum()));
                if (buy > 0) {
                    buy--;
                    item.setBuyNum(buy);
                    viewHolder.basketBuyNum.setText(String.valueOf(buy));
                    sumPrice = sumPrice - price;
                    item.setSumPrice(sumPrice);
                    viewHolder.basketSumPrice.setText(String.valueOf(sumPrice));
                }
            }
        });

    }

    public void setOnItemClickListener(OnBasketClickListener listener) {
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

    public void addItem(BasketData item) {
        items.add(item);
    }

    public void setItems(ArrayList<BasketData> items) {
        this.items = items;
    }

    public BasketData getItem(int position) {
        return items.get(position);
    }

    public void setItem(int position, BasketData item) {
        items.set(position, item);
    }

    static class BasketViewHolder extends RecyclerView.ViewHolder {
        protected ImageView basketImage;
        protected TextView basketName;
        protected TextView basketBuyNum;
        protected TextView basketTimePickup;
        protected TextView basketSalePrice;
        protected RadioGroup basketRadioGroup;
        protected TextView basketSumPrice;
        protected ImageView plus;
        protected ImageView minus;

        public BasketViewHolder(final View itemView, final OnBasketClickListener listener) {
            super(itemView);

            basketImage = itemView.findViewById(R.id.rv_item_basket_image);
            basketName = itemView.findViewById(R.id.rv_item_basket_products_name);
            basketBuyNum = itemView.findViewById(R.id.rv_item_basket_products_buynum);
            basketTimePickup = itemView.findViewById(R.id.rv_item_basket_time_pickup_btn);
            basketSalePrice = itemView.findViewById(R.id.rv_item_basket_price);
            basketRadioGroup = itemView.findViewById(R.id.rv_item_basket_optionGroup);
            basketSumPrice = itemView.findViewById(R.id.basket_sumPrice);
            plus = itemView.findViewById(R.id.rv_item_basket_quantity_plus_btn);
            minus = itemView.findViewById(R.id.rv_item_basket_quantity_minus_btn);
        }
    }

}
