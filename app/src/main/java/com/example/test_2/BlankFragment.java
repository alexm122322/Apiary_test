package com.example.test_2;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import com.example.test_2.Data.Income;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link BlankFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link BlankFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BlankFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    AppBarLayout appBarLayout;
    TabLayout tabs;
    ViewPager viewPager;
    SectionsPagerAdapter1 mSectionsPagerAdapter1;
    RelativeLayout frameLayout;
    Toolbar toolbar;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public BlankFragment() {
        // Required empty public constructor
    }

    private void setupViewPaiger(){


        Bundle bundle=new Bundle();
        bundle.putInt("type", Income.thisExpence);
        bundle.putIntegerArrayList("icons",getIconArray(Income.thisExpence));
        ExpensesFragment expensesFragment=new ExpensesFragment();
        expensesFragment.setArguments(bundle);
        mSectionsPagerAdapter1.addFragmant(expensesFragment,getString(R.string.expenses_title));

        Bundle bundle1=new Bundle();
        bundle1.putInt("type", Income.thisIncome);
        bundle1.putIntegerArrayList("icons",getIconArray(Income.thisIncome));
        IncomeFragment incomeFragment=new IncomeFragment();
        incomeFragment.setArguments(bundle1);
        mSectionsPagerAdapter1.addFragmant(incomeFragment,getString(R.string.income_title));



        mSectionsPagerAdapter1.addFragmant(new ReportFragment(),getString(R.string.report_title));


    }

    // TODO: Rename and change types and number of parameters
    public static BlankFragment newInstance() {
        BlankFragment fragment = new BlankFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_blank, container, false);
        View contenador=(View)container.getParent();
        appBarLayout=(AppBarLayout)contenador.findViewById(R.id.appbar);
        tabs=new TabLayout(getActivity());
        tabs.setTabTextColors(Color.parseColor("#FFFFFF"),Color.parseColor("#FFFFFF"));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            toolbar=(Toolbar)contenador.findViewById(R.id.toolbar);
        }
        appBarLayout.addView(tabs);
        viewPager=(ViewPager)v.findViewById(R.id.pager);
        mSectionsPagerAdapter1=new SectionsPagerAdapter1(getFragmentManager());
        setupViewPaiger();
        viewPager.setAdapter(mSectionsPagerAdapter1);
        tabs.setupWithViewPager(viewPager);
        Toast.makeText(v.getContext(),String.valueOf(appBarLayout.getHeight())+"  "+String.valueOf(toolbar.getHeight()) , Toast.LENGTH_SHORT).show();
        frameLayout=(RelativeLayout)v.findViewById(R.id.frameLayoutId);
        ViewGroup.MarginLayoutParams params=(ViewGroup.MarginLayoutParams) frameLayout.getLayoutParams();
        params.setMargins(params.leftMargin,toolbar.getHeight()+toolbar.getHeight(),params.rightMargin,params.bottomMargin);
        frameLayout.setLayoutParams(params);
        return v;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        appBarLayout.removeView(tabs);
    }



    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
    public ArrayList<Integer> getIconArray(int fragment_type){
        ArrayList<Integer> icons=new ArrayList<>();
        switch (fragment_type){
            case Income.thisExpence:
                icons.add(R.drawable.big_bee);
                icons.add(R.drawable.res);
                icons.add(R.drawable.res);
                icons.add(R.drawable.wax);
                icons.add(R.drawable.beehive);
                icons.add(R.drawable.bee);
                icons.add(R.drawable.truck);
                icons.add(R.drawable.drived);
                break;
            case Income.thisIncome:
                icons.add(R.drawable.honey);
                icons.add(R.drawable.wax);
                icons.add(R.drawable.big_bee);
                icons.add(R.drawable.drived);
                break;
            default:
        }
        return icons;
    }
}

class SectionsPagerAdapter1 extends FragmentStatePagerAdapter {
    public ArrayList<Fragment> listOfFragment=new ArrayList<>();
    public ArrayList<String> listOfFragmentTitle=new ArrayList<>();

    public SectionsPagerAdapter1(FragmentManager fm) {
        super(fm);
    }

    public void addFragmant(Fragment fragment, String fragmentTitle){
        listOfFragment.add(fragment);
        listOfFragmentTitle.add(fragmentTitle);

    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return listOfFragmentTitle.get(position);
    }

    @Override
    public Fragment getItem(int position) {

        return listOfFragment.get(position);
    }

    @Override
    public int getCount() {

        return listOfFragment.size();
    }
}