package com.example.test_2;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.test_2.Data.BeeFamilyData;
import com.example.test_2.Data.BeeFamilyItems;

import java.util.ArrayList;

public class MainDatabase extends SQLiteOpenHelper {
    ContentValues contentValues=new ContentValues();


    static final String categoryTableName="Category";
    static final String categoryTypeRow="type";
    static final String categoryNameRow="name";

    String[] expanceCategory, incomeCategory;
    Context context=null;


    public MainDatabase(Context context) {
        super(context, "ApiDatabase", null, 2);
        this.context=context;

    }

    @Override
    public void onCreate(SQLiteDatabase db) {


        db.execSQL("create table Income (" +
                "id integer primary key autoincrement," +
                "type integer ," +
                "category integer," +
                "comments text," +
                "sum integer," +
                "date numeric)");
        db.execSQL("create table Category (" +
                "id integer primary key autoincrement," +
                "type integer ," +
                "name text)");

        db.execSQL("create table BeeFamilyData (" +
                "id integer primary key autoincrement," +
                "number integer ," +
                "breed text," +
                "bee_quine_old integer," +
                "labled integer," +
                "last_rev integer)");
        db.execSQL("create table BeeFamilyLog (" +
                "id integer primary key autoincrement," +
                "id_bee_family_data integer ," +
                "count_of_brood integer," +
                "bee_quine integer," +
                "comments text," +
                "date intager)");


        addSomeFamilis(40,db);

    }




    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }



    public ArrayList<Cursor> getData(String Query){
        //get writable database
        SQLiteDatabase sqlDB = this.getWritableDatabase();
        String[] columns = new String[] { "message" };
        //an array list of cursor to save two cursors one has results from the query
        //other cursor stores error message if any errors are triggered
        ArrayList<Cursor> alc = new ArrayList<Cursor>(2);
        MatrixCursor Cursor2= new MatrixCursor(columns);
        alc.add(null);
        alc.add(null);

        try{
            String maxQuery = Query ;
            //execute the query results will be save in Cursor c
            Cursor c = sqlDB.rawQuery(maxQuery, null);

            //add value to cursor2
            Cursor2.addRow(new Object[] { "Success" });

            alc.set(1,Cursor2);
            if (null != c && c.getCount() > 0) {

                alc.set(0,c);
                c.moveToFirst();

                return alc ;
            }
            return alc;
        } catch(SQLException sqlEx){
            Log.d("printing exception", sqlEx.getMessage());
            //if any exceptions are triggered save the error message to cursor an return the arraylist
            Cursor2.addRow(new Object[] { ""+sqlEx.getMessage() });
            alc.set(1,Cursor2);
            return alc;
        } catch(Exception ex){
            Log.d("printing exception", ex.getMessage());

            //if any exceptions are triggered save the error message to cursor an return the arraylist
            Cursor2.addRow(new Object[] { ""+ex.getMessage() });
            alc.set(1,Cursor2);
            return alc;
        }
    }
    public void addSomeFamilis(int n,SQLiteDatabase db){
        ContentValues cv = new ContentValues();
        for(int i=0; i<n; i++) {
            cv.put(BeeFamilyData.labled, 1);
            cv.put(BeeFamilyData.beeQuineOld, 2018);
            cv.put(BeeFamilyData.number, i+1);
            cv.put(BeeFamilyData.breed,"UKR");
            db.insert(BeeFamilyData.tableName, null, cv);
        }
    }

}

