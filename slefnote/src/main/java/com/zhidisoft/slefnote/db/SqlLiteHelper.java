package com.zhidisoft.slefnote.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2016/6/23.
 */
public class SqlLiteHelper extends SQLiteOpenHelper {

    public static final String USB_NAME = "mynote1.db";
    public static final int USB_VERSION = 1;
    private String sql_bill = "create table bill(id integer primary key autoincrement,usecat int,imgid int,useway varchar(20),moneynum double)";
    private String sql_note = "create table note(id integer primary key autoincrement,title varchar(40),content varchar(40),time varchar(40))";
    public SqlLiteHelper(Context context) {
        super(context, USB_NAME, null, USB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sql_bill);
        db.execSQL(sql_note);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
