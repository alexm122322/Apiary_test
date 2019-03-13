package com.example.test_2;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

public class ExpensesDialog extends DialogFragment {
    EditText mExpenceET, mCommentsET;
    MainDatabase md;
    SQLiteDatabase db;
    ContentValues cv;
    Date currentTime= Calendar.getInstance().getTime();
    int type;


    public void setType(int type) {
        this.type = type;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        LayoutInflater inflater=getActivity().getLayoutInflater();
        Log.i("RRR","Dialog runned");
        final View v=inflater.inflate(R.layout.expenses_dialog,null);
        String[] data;
        if(type==MainDatabase.thisExpence)
            data=getResources().getStringArray(R.array.expenses_category);
        else
            data=getResources().getStringArray(R.array.income_category);

        ArrayAdapter<String> adapter=new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item, data);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        final Spinner spinner=(Spinner)v.findViewById(R.id.spinner);
        spinner.setAdapter(adapter);

        mExpenceET=(EditText) v.findViewById(R.id.expence_dialog_edit_text);
        mCommentsET=(EditText) v.findViewById(R.id.comments_dialog_edit_text);

        final int[] mPosition = {0};
        md=new MainDatabase(v.getContext());
        db=md.getWritableDatabase();
        cv=new ContentValues();


        builder.setView(v)
                .setPositiveButton("Добавить", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                cv.put(MainDatabase.incomeTypeRow,MainDatabase.thisExpence);
                cv.put(MainDatabase.incomeCategoryRow,spinner.getSelectedItemId());
                cv.put(MainDatabase.incomeCommentsRow,mCommentsET.getText().toString());
                cv.put(MainDatabase.incomeDateRow,currentTime.getTime());
                cv.put(MainDatabase.incomeSumRow,Integer.parseInt(mExpenceET.getText().toString()));
                db.insert(MainDatabase.incomeTableName,null,cv);
                Toast.makeText(v.getContext(), mExpenceET.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        })
        .setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        return builder.create();
    }
}
