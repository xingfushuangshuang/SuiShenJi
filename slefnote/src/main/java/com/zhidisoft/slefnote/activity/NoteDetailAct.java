package com.zhidisoft.slefnote.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.zhidisoft.slefnote.R;
import com.zhidisoft.slefnote.bean.Note;

public class NoteDetailAct extends AppCompatActivity {
    private TextView tv_title;
    private TextView tv_content;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_detail);
        initView();

    }

    void initView(){
        tv_title= (TextView) findViewById(R.id.tv_title);
        tv_content= (TextView) findViewById(R.id.tv_content);

        //获取intent
        Intent intent=super.getIntent();
        Note note= (Note) intent.getSerializableExtra("Note类bean");

        //设置控件文本
        tv_title.setText(note.getTitle());
        tv_content.setText(note.getContent());
    }
}
