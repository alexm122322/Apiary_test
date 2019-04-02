package com.example.test_2;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.example.test_2.Data.Category;
import com.example.test_2.Data.Income;

import java.util.ArrayList;


public class ExpensesFragment extends Fragment {

    ArrayList<Category> categories;
    LiquidityAdapter liquidityAdapter;
    Button expencesAddButton;
    String LOGS="LOGS";
    LinearLayout linearLayout;
    int type;
    Toolbar toolbar;
    TextView sum;
    Resources res;
    ArrayList<Integer> mIcons;
    Income income;
    ListView liquidityList;

    public ExpensesFragment() {
    }



    void getTypeIcons(){
        Bundle bundle=getArguments();
        this.type=bundle.getInt("type");
        this.mIcons=bundle.getIntegerArrayList("icons");
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
        income=new Income(getActivity());

        expencesAddButton =view.findViewById(R.id.expences_add_button);
        expencesAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment expensesDialog=new ExpensesDialog();
                ((ExpensesDialog) expensesDialog).setType(Income.thisExpence);
                ((ExpensesDialog) expensesDialog).setCurrentFragment(ExpensesFragment.this);
                expensesDialog.show(getFragmentManager(),"qwer");
            }
        });
        res=view.getResources();
        categories=new ArrayList<>();
        fillData();
        liquidityAdapter=new LiquidityAdapter(view.getContext(),categories);

        liquidityList=(ListView)view.findViewById(R.id.liquidity_list);
        liquidityList.setAdapter(liquidityAdapter);
        linearLayout =(LinearLayout) view.findViewById(R.id.listLinearLayout);
        ViewGroup.MarginLayoutParams params=(ViewGroup.MarginLayoutParams)linearLayout.getLayoutParams();
        params.setMargins(params.leftMargin,params.topMargin,params.rightMargin,expencesAddButton.getHeight()+150);
        linearLayout.setLayoutParams(params);
        //--------------set_sum_of_expence--------------------------------
        sum=(TextView) view.findViewById(R.id.text_sum);
        sum.setText(String.valueOf(income.readDate(ReadType.SumOfExpence))+" грн");
        //----------------------------------------------------------------
        return view;
    }

    void updateList(){
        fillData();
        liquidityAdapter.setObjects(categories);
        liquidityAdapter.notifyDataSetChanged();
        sum.setText(String.valueOf(income.readDate(ReadType.SumOfExpence))+" грн");
    }


    void fillData(){
        getTypeIcons();
        categories=new ArrayList<>();
        String[] category;

        if (type==Income.thisExpence)
            category=res.getStringArray(R.array.expenses_category);
        else
            category=res.getStringArray(R.array.income_category);
        Category c=new Category();
        ArrayList<ArrayList<Integer>> list=(income.readDate(Income.thisExpence));
        Log.e("SIZE_EXP",String.valueOf(list.get(0).size()));
        int i=0;
        for (String cat:category){
            c.setCategoryName(cat);
            c.setCategoryData(list.get(i).get(1));
            Log.e(LOGS,String.valueOf(mIcons.size()));
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
