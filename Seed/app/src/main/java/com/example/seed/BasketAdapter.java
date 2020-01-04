package com.example.seed;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.seed.DB.SharedPreferenceController;
import com.example.seed.Delete.DeleteBasketItemResponse;
import com.example.seed.Network.ApplicationController;
import com.example.seed.Network.NetworkService;
import com.example.seed.data.BasketData;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.seed.BasketActivity.adapter;

// Customized by SY

public class BasketAdapter extends RecyclerView.Adapter<BasketAdapter.BasketViewHolder> {

    ApplicationController applicationController = new ApplicationController();
    NetworkService networkService = applicationController.buildNetworkService();

    ArrayList<BasketData> items;
    public static Context context;

    Handler handler = new Handler();

    final Runnable r = new Runnable() {
        public void run() {
            adapter.notifyDataSetChanged();
        }
    };

    static String sendTime;

    public BasketAdapter(ArrayList<BasketData> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @Override
    public BasketViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = inflater.inflate(R.layout.rv_item_basket_products, viewGroup, false);
        return new BasketViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final BasketViewHolder viewHolder, final int position) {
        final BasketData item = items.get(position);
        Glide.with(viewHolder.itemView.getContext())
                .load(item.getImage())
                .into(viewHolder.basketImage);
        viewHolder.basketName.setText(item.getName());
        item.setBuyNum(0);
        viewHolder.basketBuyNum.setText(String.valueOf(item.getBuyNum()));
        viewHolder.basketTimePickup.setText(" 00");
        viewHolder.basketDatePickup.setText("00 :");
        viewHolder.basketSalePrice.setText(String.valueOf(item.getSalePrice()));
        item.setPacking(0);
        item.setSumPrice(0);
        viewHolder.basketSumPrice.setText(String.valueOf(item.getSumPrice()));

        viewHolder.basketDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(view.getContext(), BasketActivity.class);
//                intent.putExtra("idProduct", item.getIdBasket());
                int idProduct = item.getIdBasket();
                Call<DeleteBasketItemResponse> call = networkService.deleteBasketItemResponse("application/json", SharedPreferenceController.getMyId(view.getRootView().getContext()), idProduct);
                call.enqueue(new Callback<DeleteBasketItemResponse>() {
                    @Override
                    public void onResponse(Call<DeleteBasketItemResponse> call, Response<DeleteBasketItemResponse> response) {
                        if (response.isSuccessful()) {
                            int status = response.body().status;
                            Log.d("통신", String.valueOf(status));
                            if (status == 200) {
                                Log.d("통신", "성공");
                                Log.d("통신", String.valueOf(items.size()));
                                handler.post(r);
//                                notifyItemRemoved(position);
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<DeleteBasketItemResponse> call, Throwable t) {
                        Log.d("통신", "실패");
                    }
                });
            }
        });

        // Customized by MS
        final Calendar calendar = Calendar.getInstance();
        viewHolder.basketDatePickup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, final int month, int dayOf) {
                        calendar.set(Calendar.YEAR, year);
                        calendar.set(Calendar.MONTH, month);
                        calendar.set(Calendar.DAY_OF_MONTH, dayOf);

                        String dateM = String.valueOf(calendar.getTime()).substring(4,7);
                        String monthM = new String();

                        switch(dateM) {
                            case "Jan" : monthM = "01";  break;
                            case "Feb" : monthM = "02";  break;
                            case "Mar" : monthM = "03";  break;
                            case "Apr" : monthM = "04";  break;
                            case "May" : monthM = "05";  break;
                            case "Jun" : monthM = "06";  break;
                            case "Jul" : monthM = "07";  break;
                            case "Aug" : monthM = "08";  break;
                            case "Sep" : monthM = "09";  break;
                            case "Oct" : monthM = "10";  break;
                            case "Nov" : monthM = "11";  break;
                            case "Dec" : monthM = "12";  break;
                        }

                        String string = String.valueOf(calendar.getTime()).substring(30,34)+"/"+monthM+"/"+String.valueOf(calendar.getTime()).substring(8,10);
                        viewHolder.basketDatePickup.setText(string);
                        Log.d("day날짜-일", String.valueOf(calendar.getTime()).substring(8,10));
                        Log.d("day날짜-월", String.valueOf(calendar.getTime()).substring(4,7));
                        Log.d("day날짜-년", String.valueOf(calendar.getTime()).substring(30,34));
                        viewHolder.basketDatePickup.setTextColor(Color.parseColor("#00ff0000"));
                        sendTime = String.valueOf(calendar.getTime()).substring(30,34)+"-"+monthM+"-"+String.valueOf(calendar.getTime()).substring(8,10)+"T";
                    }
                };
                new DatePickerDialog(view.getRootView().getContext(), onDateSetListener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
                Log.d("day날짜-total", String.valueOf(calendar.getTime()));
            }
        });

        viewHolder.basketTimePickup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOf, int minOf) {
                        calendar.set(Calendar.HOUR_OF_DAY, hourOf);
                        calendar.set(Calendar.MINUTE, minOf);

                        viewHolder.basketTimePickup.setText(String.valueOf(calendar.getTime()).substring(11, 16));
                        Log.d("day시간", String.valueOf(calendar.getTime()).substring(11, 16)+":00");


                        sendTime = sendTime + String.valueOf(calendar.getTime()).substring(11, 16) + ":00.000Z";
                        //yyyy-MM-dd(T)HH:mm:ss.SSS(Z) 형식
                        item.setTimePickup(sendTime);
                        Log.d("day시간-total", item.getTimePickup());
                        Log.d("day시간분",item.getTimePickup().substring(11, 13));

                    }
                };
                TimePickerDialog timePickerDialog = new TimePickerDialog(view.getRootView().getContext(), onTimeSetListener, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true);
                timePickerDialog.show();
            }
        });
        // Customized by SY

        viewHolder.basketRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int id) {
                switch (id) {
                    case R.id.rv_item_basket_vinyl: {
                        int check = Integer.parseInt(String.valueOf(item.getPacking()));
                        int sumPrice = Integer.parseInt(String.valueOf(item.getSumPrice()));
                        sumPrice = sumPrice - check;
                        item.setPacking(0);
                        check = 0;
                        sumPrice = sumPrice + check;
                        item.setSumPrice(sumPrice);
                        viewHolder.basketSumPrice.setText(String.valueOf(sumPrice));
                        break;
                    }
                    case R.id.rv_item_basket_paper: {
                        int check = Integer.parseInt(String.valueOf(item.getPacking()));
                        item.setPacking(100);
                        int sumPrice = Integer.parseInt(String.valueOf(item.getSumPrice()));
                        sumPrice = sumPrice - check;
                        check = 100;
                        sumPrice = sumPrice + check;
                        item.setSumPrice(sumPrice);
                        viewHolder.basketSumPrice.setText(String.valueOf(sumPrice));
                        break;
                    }
                    case R.id.rv_item_basket_box: {
                        int check = Integer.parseInt(String.valueOf(item.getPacking()));
                        item.setPacking(1000);
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
        protected TextView basketDatePickup;
        protected TextView basketSalePrice;
        protected RadioGroup basketRadioGroup;
        protected TextView basketSumPrice;
        protected ImageView plus;
        protected ImageView minus;
        protected RelativeLayout basketDelete;

        public BasketViewHolder(final View itemView) {
            super(itemView);

            basketImage = itemView.findViewById(R.id.rv_item_basket_image);
            basketName = itemView.findViewById(R.id.rv_item_basket_products_name);
            basketBuyNum = itemView.findViewById(R.id.rv_item_basket_products_buynum);
            basketTimePickup = itemView.findViewById(R.id.rv_item_basket_time_pickup_btn);
            basketDatePickup = itemView.findViewById(R.id.rv_item_basket_date_pickup_btn);
            basketSalePrice = itemView.findViewById(R.id.rv_item_basket_price);
            basketRadioGroup = itemView.findViewById(R.id.rv_item_basket_optionGroup);
            basketSumPrice = itemView.findViewById(R.id.basket_sumPrice);
            plus = itemView.findViewById(R.id.rv_item_basket_quantity_plus_btn);
            minus = itemView.findViewById(R.id.rv_item_basket_quantity_minus_btn);
            basketDelete = itemView.findViewById(R.id.rv_item_basket_delete);
        }
    }

}
