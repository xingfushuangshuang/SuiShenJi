package com.example.resolcertest;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.v4.content.ContentResolverCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText et_id, et_content, et_title;
    private Button btn_query, btn_insert,btn_delete,btn_update;
    private ListView lv_content;
    private String struri = "content://com.zhidisoft.slefnote.provider/note";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    public void initView() {
        et_id = (EditText) findViewById(R.id.et_id);
        et_title = (EditText) findViewById(R.id.et_title);
        et_content = (EditText) findViewById(R.id.et_content);
        btn_query = (Button) findViewById(R.id.btn_query);
        btn_insert = (Button) findViewById(R.id.btn_insert);
        btn_delete= (Button) findViewById(R.id.btn_delete);
        btn_update= (Button) findViewById(R.id.btn_update);
        lv_content = (ListView) findViewById(R.id.lv_content);
        btn_delete.setOnClickListener(this);
        btn_query.setOnClickListener(this);
        btn_insert.setOnClickListener(this);
        btn_update.setOnClickListener(this);
        queryAll();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_query:
                queryById();
                break;
            case R.id.btn_insert:
                insert();
                break;
            case R.id.btn_delete:
                delete();
                break;
            case R.id.btn_update:
                update();
                break;
        }
    }
//根据ID查询
    public void queryById() {
        ContentResolver resolver = getContentResolver();//获取内容接受者
        Uri uri = Uri.parse(struri);
        long id = Long.parseLong(et_id.getText().toString());
        //拼接一个id
        uri = ContentUris.withAppendedId(uri, id);
        Cursor cursor = resolver.query(uri, null, null, null, null);
        parseCursor(cursor);
    }
//全部查询
    public void queryAll() {
        ContentResolver resolver = getContentResolver();
        Uri uri = Uri.parse(struri);
        Cursor cursor = resolver.query(uri, null, null, null, null);
        parseCursor(cursor);
    }
//查询的方法归纳
    public void parseCursor(Cursor cursor) {
        List<Map<String, Object>> list = new ArrayList<>();
        while (cursor.moveToNext()) {
            Map<String, Object> map = new HashMap<>();
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            String content = cursor.getString(cursor.getColumnIndex("content"));
            String title = cursor.getString(cursor.getColumnIndex("title"));
            map.put("id", id);
            map.put("title", title);
            map.put("content", content);
            list.add(map);
        }
        SimpleAdapter adapter = new SimpleAdapter(this, list, R.layout.item_layout, new String[]{"id", "title", "content"}, new int[]{R.id.tv_id, R.id.tv_title, R.id.tv_content});
        lv_content.setAdapter(adapter);
    }

    public void insert() {
        Uri uri = Uri.parse(struri);
        ContentResolver resolver = getContentResolver();
        ContentValues values = new ContentValues();
        values.put("title", et_title.getText().toString());
        values.put("content", et_content.getText().toString());
        Uri newuri = resolver.insert(uri, values);
        long newid = ContentUris.parseId(newuri);
        Toast.makeText(this,"新id="+newid,Toast.LENGTH_SHORT).show();
        queryAll();
    }
    public void delete(){
        ContentResolver resolver = getContentResolver();//获取内容接受者
        Uri uri = Uri.parse(struri);
        int row=resolver.delete(uri,"id=?",new String[]{et_id.getText().toString()});
        if(row>0){
            queryAll();
        }
    }
    public void update(){
        ContentResolver resolver = getContentResolver();//获取内容接受者
        Uri uri = Uri.parse(struri);
        ContentValues values=new ContentValues();
        values.put("title",et_title.getText().toString());
        values.put("content",et_content.getText().toString());
        int row=resolver.update(uri,values,"id=?",new String[]{et_id.getText().toString()});
        if(row>0){
            queryAll();
        }

    }

}
