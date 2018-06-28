package com.example.aeonz.assignment5;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;

public class databaseHelper extends SQLiteOpenHelper {


    private static final String TAG = "DatabaseHelper";
    private static final String TABLE_NAME = "User_Table";
    private static final String Colum_1 = "ID";
    private static final String Colum_2 = "username";



    public databaseHelper(Context context) {
        super(context, TABLE_NAME, null, 1 );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP IF TABLE EXISTS " + TABLE_NAME);
        onCreate(db);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT , " + Colum_2 + " TEXT)";
        db.execSQL(createTable);
    }

    public boolean addData(String item){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Colum_2, item);

        Log.d(TAG, "addData: adding " + item + "to " + TABLE_NAME);
        long result = db.insert(TABLE_NAME,null, contentValues);

        if (result == 1){
            return  false;
        }
        else {
            return true;
        }


    }

    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " +TABLE_NAME, null);
        return data;
    }

}
