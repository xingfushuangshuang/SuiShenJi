package com.zhidisoft.sqldemo2.dbutil;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2016/6/17.
 */
public class SqlLiteHelper extends SQLiteOpenHelper {
    public static final String USB_NAME = "sqltest.db";
    public static final int USB_VERSION = 1;
    private String sql = "create table user(id integer primary key autoincrement,username varchar(20),pwd varchar(20))";

    public SqlLiteHelper(Context context) {
        super(context, USB_NAME, null, USB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
