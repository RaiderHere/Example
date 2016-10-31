package com.example.raider.test1;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Created by RAIDER on 11.10.2016.
 */

class DBOpenHelper extends SQLiteOpenHelper implements BaseColumns {

    private static final String DATABASE_NAME = "pubinfo.db";
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_TABLE = "news";
    static final String NEWS_NAME = "name";
    static final String NEWS_URI = "uri";

    private static final String DATABASE_CREATE = "create table " + DATABASE_TABLE + " ("
            + BaseColumns._ID + " integer primary key autoincrement, " + NEWS_NAME
            + " text not null, " + NEWS_URI + " text not null " + ");";

    public DBOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DBOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    DBOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
