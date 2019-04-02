package com.example.test_2.Data;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.test_2.MainDatabase;
import com.example.test_2.R;
import com.example.test_2.ReadType;


import java.util.ArrayList;

public class Income {
    public static final String tableName="Income";
    public static final String typeRow="type";
    public static final String categoryRow="category";
    public static final String commentsRow="comments";
    public static final String sumRow="sum";
    public static final String dateRow="date";
    public static final int thisExpence=0;
    public static final int thisIncome=1;
    Context context;
    ContentValues contentValues=new ContentValues();
    Resources res;
    SQLiteDatabase db;

    
    public Income(Context context) {
        this.context = context;
        res=context.getResources();
        db=new MainDatabase(context).getWritableDatabase();
    }




    public int readDate(ReadType type){
        //ArrayList<String> list=new ArrayList<>();

        int sum=0;
        Cursor c=null;
        switch (type){
            case SumOfIncome:
                c=db.query(tableName,
                        new String[]{sumRow},
                        typeRow+"="+String.valueOf(thisIncome),
                        null,
                        null,
                        null,
                        null);
                break;
            case SumOfExpence:
                c=db.query(tableName,
                        new String[]{sumRow},
                        typeRow+" = "+String.valueOf(thisExpence),
                        null,
                        null,
                        null,
                        null);
                break;
        }
        if(c!=null){
            if(c.moveToFirst()){
                sum=c.getInt(0);
                while (c.moveToNext())
                    sum+=c.getInt(0);
            }
        }
        return sum;
    }

    public ArrayList<ArrayList<Integer>> readDate(int type) {
        Cursor c=null;
        String[] catData=null;
        ArrayList<Integer> row=new ArrayList<>();
        ArrayList<ArrayList<Integer>> list=new ArrayList<>();

        if(type==thisExpence)
            catData=res.getStringArray(R.array.expenses_category);
        else if(type==thisIncome)
            catData=res.getStringArray(R.array.income_category);
        for (int i=0;i<catData.length;i++) {
            row.add(i);
            row.add(0);
            list.add(row);
            row=new ArrayList<>();
            Log.e("TAG","0");
        }
        c=db.query(tableName,
                new String[]{categoryRow,sumRow},
                typeRow+"="+String.valueOf(type),
                null,
                null,
                null,
                null );
        if(c!=null) {
            if(c.moveToFirst()){
                do{
                    row.add(c.getInt(0));
                    row.add(list.get(c.getInt(0)).get(1)+c.getInt(1));
                    list.set(c.getInt(0),row);
                    row=new ArrayList<>();
                    Log.e("TAG","1");
                }while (c.moveToNext());
            }
        }
        Log.e("SIZE",String.valueOf(list.size()));
        return list;
    }
}
