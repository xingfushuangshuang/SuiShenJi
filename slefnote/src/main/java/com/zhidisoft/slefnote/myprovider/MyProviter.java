package com.zhidisoft.slefnote.myprovider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;

import com.zhidisoft.slefnote.db.SqlLiteHelper;

/**
 * Created by Administrator on 2016/6/28.
 */
public class MyProviter extends ContentProvider {
    private SqlLiteHelper sqlLiteHelper;
    private static final UriMatcher matcher=new UriMatcher(UriMatcher.NO_MATCH);//谁都匹配不上时返回NO-MATCH
    private static final int MULL=0;
    private static final int SINGLE=1;
    //此静态代码块首先执行
    static {
        matcher.addURI("com.zhidisoft.slefnote.myproviter", "note",MULL);
        matcher.addURI("com.zhidisoft.slefnote.myproviter", "note/#",SINGLE);
    }

    @Override
    public boolean onCreate() {
        sqlLiteHelper=new SqlLiteHelper(getContext());//创建实例
        return false;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteDatabase sqLiteDatabase=sqlLiteHelper.getReadableDatabase();
        int code=matcher.match(uri);
        switch (code){
            case SINGLE:
                long id=ContentUris.parseId(uri);
                return sqLiteDatabase.query("note", projection, "id=?", new String[]{"" + id}, null, null, sortOrder);
            case MULL:
                return sqLiteDatabase.query("note", projection, selection, selectionArgs, null, null, sortOrder);
        }

        return null;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        return null;
    }

    @Override
    public int delete(Uri uri, String s, String[] strings) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String s, String[] strings) {
        return 0;
    }
}
