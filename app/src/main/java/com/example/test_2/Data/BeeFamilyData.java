package com.example.test_2.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.test_2.MainDatabase;

public class BeeFamilyData {
    public static String tableName="BeeFamilyData";
    public static String row_number="number";
    public static String row_breed="breed";
    public static String row_beeQuineOld="bee_quine_old";
    public static String row_labled="labled";

    private int number;
    private String breed;
    private int beeQuineOld;
    private int labled;
    Context c;
    ContentValues cv;
    SQLiteDatabase db;

    public void setNumber(int number) {
        this.number = number;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public void setBeeQuineOld(int beeQuineOld) {
        this.beeQuineOld = beeQuineOld;
    }

    public void setLabled(int labled) {
        this.labled = labled;
    }

    public void setC(Context c) {
        this.c = c;
    }
    public boolean putToDataBase(){
        cv=new ContentValues();
        db=(new MainDatabase(c)).getWritableDatabase();
        cv.put(row_number,this.row_number);
        cv.put(row_breed, this.breed);
        cv.put(row_beeQuineOld,this.beeQuineOld);
        cv.put(row_labled, this.labled);

        if(db.insert(tableName,null,cv)==-1)
            return false;
        return true;
    }
}
