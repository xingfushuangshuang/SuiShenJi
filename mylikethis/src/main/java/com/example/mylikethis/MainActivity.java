package com.example.mylikethis;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
private Button btn;
    private EditText et;
    private ListView lv;
    private String strUir="content://com.zhidisoft.slefnote.myproviter/note";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initV();
    }
    public void initV(){
        et= (EditText) findViewById(R.id.et);
        btn= (Button) findViewById(R.id.btn);
        lv= (ListView) findViewById(R.id.lv);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn:
                querryById();
                break;

        }
    }
    public void querryById(){
        ContentResolver resolver=getContentResolver();
        Uri uri=Uri.parse(strUir);
        long id=Long.parseLong(et.getText().toString());
        uri=ContentUris.withAppendedId(uri,id);
        Cursor cursor=resolver.query(uri,null,null,null,null);
        while (cursor.moveToNext()) {
            int idd = cursor.getInt(cursor.getColumnIndex("id"));
            String content = cursor.getString(cursor.getColumnIndex("content"));
            String title = cursor.getString(cursor.getColumnIndex("title"));
            Toast.makeText(this, "id=" + idd + "content=" + content + "title" + title, Toast.LENGTH_SHORT).show();
        }

    }
    public void querryAll(){
        ContentResolver resolver=getContentResolver();
        Uri uri=Uri.parse(strUir);
    }
}
