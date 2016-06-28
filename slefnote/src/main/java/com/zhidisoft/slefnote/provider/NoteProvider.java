package com.zhidisoft.slefnote.provider;

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
public class NoteProvider extends ContentProvider {
    private SqlLiteHelper helper;
    private static final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);//谁都匹配不上的时候返回no-match
    private static final int MUIL = 0;
    private static final int SINGLE = 1;

    //执行的比较早的
    static {
        matcher.addURI("com.zhidisoft.slefnote.provider", "note", MUIL);//多条数据
        matcher.addURI("com.zhidisoft.slefnote.provider", "note/#", SINGLE);//单条
    }

    @Override
    public boolean onCreate() {
        helper = new SqlLiteHelper(getContext());
        return false;
    }


    /**
     * 参数是远程通过contentResolver传过来的
     *
     * @param uri
     * @param projection:列名
     * @param selection:查询条件
     * @param selectionArgs:查询条件参数
     * @param sortOrder:排序
     * @return:cursor 游标
     */
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        //获取数据库对象
        SQLiteDatabase db = helper.getReadableDatabase();
        //判断是单条还是多条，根据匹配项进行操作
        int code = matcher.match(uri);
        switch (code) {
            case SINGLE://根据id查询
                long id = ContentUris.parseId(uri);//解析出uri中的id
                return db.query("note", projection, "id=?", new String[]{"" + id}, null, null, sortOrder);
            case MUIL://查询所有的数据
                return db.query("note", projection, selection, selectionArgs, null, null, sortOrder);
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
    public Uri insert(Uri uri, ContentValues values) {
        SQLiteDatabase db = helper.getWritableDatabase();
        long id=db.insert("note",null,values);
        return ContentUris.withAppendedId(uri,id);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs)    {
        SQLiteDatabase db = helper.getWritableDatabase();
        int row=db.delete("note",selection,selectionArgs);
        return row;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        SQLiteDatabase db = helper.getWritableDatabase();
        int row=db.update("note",values,selection,selectionArgs);
        return row;
    }
}
