package com.example.test_2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class LiquidityAdapter extends BaseAdapter {
    Context ctx;
    LayoutInflater lInflater;
    ArrayList<Category> objects;

    LiquidityAdapter(Context context, ArrayList<Category> categories){
        ctx=context;
        objects=categories;
        lInflater = (LayoutInflater) ctx
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public Object getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v=convertView;
        if(v==null){
            v=lInflater.inflate(R.layout.liquidity_item,parent,false);
        }
        Category category=getCategory(position);

        ((TextView)v.findViewById(R.id.liquidity_list_category_text)).setText(category.getCategoryName());
        ((TextView)v.findViewById(R.id.liquidity_list_category_money_text)).setText(Integer.toString(category.getCategoryData()));
        ((ImageView)v.findViewById(R.id.liquidity_list_image)).setImageResource(category.getR());

        return v;
    }

    Category getCategory(int positin){
        return ((Category)getItem(positin));
    }

}
