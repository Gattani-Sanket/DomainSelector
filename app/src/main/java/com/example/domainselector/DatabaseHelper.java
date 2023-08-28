package com.example.domainselector;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {



    public DatabaseHelper(Context context) {
        super(context, "DomainSelection.db", null, 1);

    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("drop table if exists Prerequisites");
        db.execSQL("create table Prerequisites(Category text primary key, Questions_Solved int default 0,Correct int default 0)");

        //Inserting into Prerequisites table
        //db.execSQL("insert into Que_Ans(Category) select Category from Prerequisites" );
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop table Prerequisites");
        onCreate(db);

    }

}