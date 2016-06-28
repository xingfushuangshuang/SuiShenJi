package com.zhidisoft.slefnote.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.zhidisoft.slefnote.bean.BillStore;
import com.zhidisoft.slefnote.bean.Note;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/24.
 */
public class DBAction {
    private SQLiteOpenHelper helper;
    private List<Note> listNote;
    private List<BillStore> listBillStore;

    public DBAction(Context context) {
        helper = new SqlLiteHelper(context);
    }

    public void insertNote(Note note) {
        //获得数据库
        SQLiteDatabase db = helper.getReadableDatabase();
        //定义语句
        String sql = "insert into note(title,content,time) values(?,?,?)";
        //执行语句
        db.execSQL(sql, new Object[]{note.getTitle(), note.getContent(), note.getTime()});
    }

    public void updateNote(String title,String content,String time,Note note) {
        //获得数据库
        SQLiteDatabase db = helper.getReadableDatabase();
        //定义语句
        String sql = "update note set title=?,content=?,time=? where title=? and content=?";
        //执行语句
        db.execSQL(sql, new Object[]{title, content, time,note.getTitle(),note.getContent()});
    }

    public void delNote(Note note) {
        //获得数据库
        SQLiteDatabase db = helper.getReadableDatabase();
        //定义语句
        String sql = "delete from note where title=? and content=? ";
        //执行语句
        db.execSQL(sql, new Object[]{note.getTitle(), note.getContent()});
        queryNoteAll();
    }

    /**
     * 查询note表中记录，返回Note对象集合
     *
     * @return
     */
    public List<Note> queryNoteAll() {
        //获取数据库
        SQLiteDatabase db = helper.getReadableDatabase();
        String sql = "select * from note order by id desc";
        Cursor cursor = db.rawQuery(sql, null);
        return cursorNoteList(cursor);
    }

    /**
     * 通过游标cursor获取查询数据集合listNote
     *
     * @param cursor
     * @return
     */
    public List<Note> cursorNoteList(Cursor cursor) {
        listNote = new ArrayList<>();
        while (cursor.moveToNext()) {
            String title = cursor.getString(cursor.getColumnIndex("title"));//获取ID
            String content = cursor.getString(cursor.getColumnIndex("content"));
            String time = cursor.getString(cursor.getColumnIndex("time"));
            Note note = new Note(title, content, time);
            listNote.add(note);
        }
        return listNote;
    }

    public void insertBillStore(BillStore billStore) {
        //获得数据库
        SQLiteDatabase db = helper.getReadableDatabase();
        //定义语句
        String sql = "insert into bill(usecat,imgid,useway,moneynum) values(?,?,?,?)";
        //执行语句
        db.execSQL(sql, new Object[]{billStore.getUseCat(), billStore.getImgId(), billStore.getUseWay(), billStore.getMoneyNum()});
    }

    /**
     * 查询bill表中的所有记录，返回BillStore集合
     *
     * @return
     */
    public List<BillStore> queryBillStore() {
        //获取数据库
        SQLiteDatabase db = helper.getReadableDatabase();
        String sql = "select * from bill order by id desc";
        Cursor cursor = db.rawQuery(sql, null);
        return cursorBillList(cursor);
    }

    /**
     * 通过游标cursor获取查询数据集合listBillStore
     *
     * @param cursor
     * @return
     */
    public List<BillStore> cursorBillList(Cursor cursor) {
        listBillStore = new ArrayList<>();
        while (cursor.moveToNext()) {
            int usecat = cursor.getInt(cursor.getColumnIndex("usecat"));//获取ID
            int imgid = cursor.getInt(cursor.getColumnIndex("imgid"));
            String useway = cursor.getString(cursor.getColumnIndex("useway"));
            double moneynum = cursor.getDouble(cursor.getColumnIndex("moneynum"));
            BillStore billStore = new BillStore(usecat, imgid, useway, moneynum);
            listBillStore.add(billStore);
        }
        return listBillStore;
    }
}
