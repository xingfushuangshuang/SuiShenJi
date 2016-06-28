package com.zhidisoft.sqldemo2.dbutil;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.zhidisoft.sqldemo2.bean.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/17.
 */
public class DBAction {
    private SQLiteOpenHelper helper;
    private String sql="insert into user(username,pwd) values(?,?)";
    private List<User> list;

    public DBAction(Context context){
        helper= new SqlLiteHelper(context);
    }

    public void Insert(User user){
        SQLiteDatabase db= helper.getReadableDatabase();
        db.execSQL(sql,new Object[]{user.getUsername(),user.getPwd()});
    }

    /**
     * 定义查询所有数据的操作
     * @return
     */
    public List<User> queryAll(){
        SQLiteDatabase db=helper.getReadableDatabase();//获得数据库
        String sql="select * from user";
        //游标
        Cursor cursor=db.rawQuery(sql,null);
        return cursorList(cursor);
    }

    /**
     * 通过游标cursor获取查询数据集合list
     * @param cursor
     * @return
     */
    public List<User> cursorList(Cursor cursor){
        list=new ArrayList<>();
        while (cursor.moveToNext()){
            int id=cursor.getInt(cursor.getColumnIndex("id"));//获取ID
            String username=cursor.getString(cursor.getColumnIndex("username"));
            String pwd=cursor.getString(cursor.getColumnIndex("pwd"));
            User user=new User(id,username,pwd);
            list.add(user);
        }
        return list;
    }
    /**
     * 修改对应id的数据
     * @param user
     */
    public void update(User user){
        SQLiteDatabase db=helper.getWritableDatabase();
        String sql="update user set username=?,pwd=? where id=?";
        db.execSQL(sql,new Object[]{user.getUsername(),user.getPwd(),user.getId()});
    }

    /**
     * 查询对应名称的数据
     * @param name
     */
    public List<User> select(String name){
        SQLiteDatabase db=helper.getReadableDatabase();
        String sql="select * from user where username=?";
        //cursor游标rawQuery方法读取获取集合
        Cursor cursor=db.rawQuery(sql,new String[]{name});
        return cursorList(cursor);
    }

    /**
     * 根据id删除
     * @param id
     */
    public void deleteById(String id){
        SQLiteDatabase db=helper.getReadableDatabase();
        String sql="delete from user where id=?";
        db.execSQL(sql,new Object[]{id});
    }

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    public User selectById(String id){
        User user=new User();
        SQLiteDatabase db=helper.getReadableDatabase();
        String sql="select * from user where id=?";
        Cursor cursor=db.rawQuery(sql,new String[]{id});
        while(cursor.moveToNext()){
            int id_get=cursor.getInt(cursor.getColumnIndex("id"));
            String name=cursor.getString(cursor.getColumnIndex("username"));
            String pwd=cursor.getString(cursor.getColumnIndex("pwd"));
            user.setId(id_get);
            user.setUsername(name);
            user.setPwd(pwd);
        }
        return user;
    }


}
