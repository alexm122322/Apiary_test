package com.example.test_2.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.test_2.MainDatabase;

import java.util.ArrayList;

public class BeeFamilyItems {
    private int beeFamilyNumber;
    private String beeFamilyType;
    private int beeQuineOld;
    private int lastDataRev;
    private int last_rev;
    Context context;
    SQLiteDatabase db;
    ContentValues cv = new ContentValues();

    public BeeFamilyItems(){

    }

    public BeeFamilyItems(int beeFamilyNumber, String beeFamilyType, int beeQuineOld, int lastDataRev) {
        this.beeFamilyNumber = beeFamilyNumber;
        this.beeFamilyType = beeFamilyType;
        this.beeQuineOld = beeQuineOld;
        this.lastDataRev = lastDataRev;
    }

    public void setContext(Context context) {
        this.context = context;
        db = new MainDatabase(context).getWritableDatabase();
    }

    public int getBeeFamilyNumber() {
        return beeFamilyNumber;
    }

    public void setBeeFamilyNumber(int beeFamilyNumber) {
        this.beeFamilyNumber = beeFamilyNumber;
    }

    public String getBeeFamilyType() {
        return beeFamilyType;
    }

    public void setBeeFamilyType(String beeFamilyType) {
        this.beeFamilyType = beeFamilyType;
    }

    public int getBeeQuineOld() {
        return beeQuineOld;
    }

    public void setBeeQuineOld(int beeQuineOld) {
        this.beeQuineOld = beeQuineOld;
    }

    public int getLastDataRev() {
        return lastDataRev;
    }

    public void setLastDataRev(int lastDataRev) {
        this.lastDataRev = lastDataRev;
    }

    public ArrayList<BeeFamilyItems> getItems() {
        ArrayList<BeeFamilyItems> items = new ArrayList<>();
        Cursor cursor = null;
        cursor = db.query(BeeFamilyData.tableName,
                null,
                null,
                null,
                null,
                null,
                null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    BeeFamilyItems beeFamilyItems = new BeeFamilyItems(cursor.getInt(1),
                            cursor.getString(2),
                            cursor.getInt(3),
                            cursor.getInt(5));
                    items.add(beeFamilyItems);
                } while (cursor.moveToNext());
            }
        }
        return items;
    }


}
