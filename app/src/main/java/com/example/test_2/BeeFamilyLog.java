package com.example.test_2;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.test_2.Data.BeeFamilyItems;
import com.example.test_2.Data.Category;

import java.util.ArrayList;


public class BeeFamilyLog extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    ArrayList<BeeFamilyItems> items=null;
    ListView listView;
    Toolbar toolbar;
    FrameLayout frameLayout;

    // TODO: Rename and change types of parameters

    public BeeFamilyLog() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static BeeFamilyLog newInstance() {
        BeeFamilyLog fragment = new BeeFamilyLog();
        /*Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);*/
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            //mParam1 = getArguments().getString(ARG_PARAM1);
            //mParam2 = getArguments().getString(ARG_PARAM2);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_bee_family_log, container, false);
        fillData();
        listView=(ListView)v.findViewById(R.id.beeFamilyLogList);
        BeeFamilyLogAdapter adapter=new BeeFamilyLogAdapter(v.getContext(),items);
        listView.setAdapter(adapter);
        View contenador=(View)container.getParent();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            toolbar=(Toolbar)contenador.findViewById(R.id.toolbar);
        }

        frameLayout=(FrameLayout)v.findViewById(R.id.BeeFamilyLogId);
        ViewGroup.MarginLayoutParams params=(ViewGroup.MarginLayoutParams) frameLayout.getLayoutParams();
        params.setMargins(params.leftMargin,toolbar.getHeight(),params.rightMargin,params.bottomMargin);
        frameLayout.setLayoutParams(params);
        // Inflate the layout for this fragment
        return v;
    }


    private void fillData(){
        BeeFamilyItems beeFamilyItems=new BeeFamilyItems();
        beeFamilyItems.setContext(getActivity());
        items=beeFamilyItems.getItems();
    }
}

class BeeFamilyLogAdapter extends BaseAdapter {
    Context ctx;
    LayoutInflater lInflater;
    ArrayList<BeeFamilyItems> objects;

    BeeFamilyLogAdapter(Context context, ArrayList<BeeFamilyItems> beeFamilyItems){
        ctx=context;
        objects=beeFamilyItems;
        lInflater = (LayoutInflater) ctx
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setObjects(ArrayList<BeeFamilyItems> objects) {
        this.objects = objects;
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
            v=lInflater.inflate(R.layout.bee_family_item,parent,false);
        }
        BeeFamilyItems item=getBeeFamilyItem(position);

        ((TextView)v.findViewById(R.id.textNumberOfBeeFamily)).setText(String.valueOf(item.getBeeFamilyNumber()));
        ((TextView)v.findViewById(R.id.textBeeType)).setText(item.getBeeFamilyType());
        ((TextView)v.findViewById(R.id.textBeeQuineOld)).setText(String.valueOf(item.getBeeQuineOld()));
        ((TextView)v.findViewById(R.id.textDateLastRev)).setText(String.valueOf(item.getLastDataRev()));

        return v;
    }

    BeeFamilyItems getBeeFamilyItem(int positin){
        return ((BeeFamilyItems)getItem(positin));
    }



}

