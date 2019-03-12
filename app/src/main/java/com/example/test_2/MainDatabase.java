package com.example.test_2;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.util.Log;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainDatabase extends SQLiteOpenHelper {
    ContentValues contentValues=new ContentValues();
    Resources res;

    static final String categoryTableName="Category";
    static final String categoryTypeRow="type";
    static final String categoryNameRow="name";
    static final int thisExpence=0;
    static final int thisIncomne=1;

    static final String incomeTableName="Income";
    static final String incomeTypeRow="type";
    static final String incomeCategoryRow="category";
    static final String incomeCommentsRow="comments";
    static final String incomeDateRow="date";



    String[] categoryNames;


    public MainDatabase(Context context) {
        super(context, "ApiDatabase", null, 1);
        res=context.getResources();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table Income (" +
                "id integer primary key autoincrement," +
                "type integer ," +
                "category integer," +
                "comments text," +
                "date numeric)");
        db.execSQL("create table Category (" +
                "id integer primary key autoincrement," +
                "type integer ," +
                "name text)");

        categoryNames=res.getStringArray(R.array.expenses_category);

        for (String temp:categoryNames) {
            contentValues.put(MainDatabase.categoryTypeRow,MainDatabase.thisExpence);
            contentValues.put(MainDatabase.categoryNameRow, temp);
            db.insert(MainDatabase.categoryTableName,null,contentValues);
            contentValues.clear();
        }




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

}
