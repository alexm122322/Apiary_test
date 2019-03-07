package com.example.test_2;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.List;



public class ExpensesFragment extends Fragment {

    ArrayList<Category> categories;
    LiquidityAdapter liquidityAdapter;
    Button expencesAddButton;

    public ExpensesFragment() {
    }

    Resources res;

    ArrayList<Integer> mIcons=new ArrayList<>();

    @SuppressLint("ValidFragment")
    public ExpensesFragment(ArrayList icons){
        this.mIcons=icons;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_expenses, container, false);
       /* PieChart chart = (PieChart) view.findViewById(R.id.chart);
        List<PieEntry> entries = new ArrayList<>();

        entries.add(new PieEntry(18.5f, "Green"));
        entries.add(new PieEntry(26.7f, "Yellow"));
        entries.add(new PieEntry(24.0f, "Red"));
        entries.add(new PieEntry(30.8f, "Blue"));

        PieDataSet set = new PieDataSet(entries, "Election Results");
        set.setColors(new int[]{R.color.color1,R.color.color2,R.color.color3,R.color.color4},view.getContext());
        PieData data = new PieData(set);
        chart.setData(data);
        chart.invalidate();*/

        expencesAddButton =view.findViewById(R.id.expences_add_button);
        expencesAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment expensesDialog=new ExpensesDialog();
                expensesDialog.show(getFragmentManager(),"qwer");
            }
        });
        res=view.getResources();
        categories=new ArrayList<>();
        fillData();
        liquidityAdapter=new LiquidityAdapter(view.getContext(),categories);

        ListView liquidityList=(ListView)view.findViewById(R.id.liquidity_list);
        liquidityList.setAdapter(liquidityAdapter);

        return view;

    }

    void fillData(){
        String[] expensesCategory=res.getStringArray(R.array.expenses_category);
        Category c=new Category();
        int i=0;
        for (String cat:expensesCategory){
            c.setCategoryName(cat);
            c.setCategoryData(1000);
            c.setR(mIcons.get(i));
            categories.add(c);
            c=new Category();
            i++;
        }

    }

    public static ExpensesFragment newInstance() {
        
        Bundle args = new Bundle();
        
        ExpensesFragment fragment = new ExpensesFragment();
        fragment.setArguments(args);
        return fragment;
    }



}
