package com.example.seed;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.seed.data.BuyCompletedData;

import java.util.ArrayList;

// Customized by SY

public class BuyCompletedExpandAdapter extends BaseExpandableListAdapter {

    private Context context;
    private int groupLayout = 0;
    private int chlidLayout = 0;
    private ArrayList<BuyCompletedData> DataList;
    private LayoutInflater myinf = null;

    public BuyCompletedExpandAdapter(Context context,int groupLay,int chlidLay,ArrayList<BuyCompletedData> DataList){
        this.DataList = DataList;
        this.groupLayout = groupLay;
        this.chlidLayout = chlidLay;
        this.context = context;
        this.myinf = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getGroupCount() {
        return DataList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return DataList.get(groupPosition).data.size();
    }

    @Override
    public BuyCompletedData getGroup(int groupPosition) {
        return DataList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return DataList.get(groupPosition).data.get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = myinf.inflate(this.groupLayout, parent, false);
        }
        TextView groupName = (TextView)convertView.findViewById(R.id.groupName);
        groupName.setText(DataList.get(groupPosition).timePickup);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if(convertView == null) {
            convertView = myinf.inflate(this.chlidLayout, parent, false);
        }
        TextView childName = (TextView) convertView.findViewById(R.id.rv_item_buy_completed_products_name);
        childName.setText(DataList.get(groupPosition).data.get(childPosition).name);
        TextView childPrice = (TextView)convertView.findViewById(R.id.rv_item_buy_completed_products_price);
        childPrice.setText(String.valueOf(DataList.get(groupPosition).data.get(childPosition).totalPrice));
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
