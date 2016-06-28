package com.zhidisoft.slefnote.activity;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.zhidisoft.slefnote.R;
import com.zhidisoft.slefnote.bean.Note;
import com.zhidisoft.slefnote.db.DBAction;
import com.zhidisoft.slefnote.fragment.NoteFragment;


import java.util.Calendar;
import java.util.List;

public class NoteEditActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView iv_save, iv_cancle, iv_time;
    private EditText et_title, et_content;
    private String title, content;
    private List<Note> listNote;
    private SetList setList;
    private DBAction dba;
//    private boolean isEdit=false;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_edit);

        initView();
    }

    void initView() {
        iv_save = (ImageView) findViewById(R.id.iv_save);
        iv_cancle = (ImageView) findViewById(R.id.iv_cancle);
        iv_time = (ImageView) findViewById(R.id.iv_time);
        et_title = (EditText) findViewById(R.id.et_title);
        et_content = (EditText) findViewById(R.id.et_content);

        iv_save.setOnClickListener(this);
        iv_cancle.setOnClickListener(this);
        iv_time.setOnClickListener(this);

        dba=new DBAction(this);
    }

    @Override
    public void onClick(View v) {
        switchImageView(v.getId());
    }

    void switchImageView(int id) {
        switch (id) {
            case R.id.iv_save:
                String title = et_title.getText().toString().trim();
                String content = et_content.getText().toString().trim();
                Note note= (Note) this.getIntent().getSerializableExtra("修改的日记");
                if (title.equals("")) {//字符串判断用equals
                    Toast.makeText(NoteEditActivity.this, "请输入标题", Toast.LENGTH_SHORT).show();
                } else if (content.isEmpty()) {
                    Toast.makeText(NoteEditActivity.this, "请输入内容", Toast.LENGTH_SHORT).show();
                } else if (note!=null){//不为空-->操作：修改笔记
                    et_content.setText(getTime()+content);

                    updateNote(note);//修改笔记
                }else{//为空-->操作类型：新建笔记
                    et_content.setText(getTime()+content);
                    insertNote();//插入数据库
                    finish();//结束当前Activity,返回
                }

                break;
            case R.id.iv_cancle:
                Toast.makeText(NoteEditActivity.this, "设置为空", Toast.LENGTH_SHORT).show();
                //设置标题文本框为空
                et_title.setText("");
                //设置内容文本框为空
                et_content.setText("");
                break;
            case R.id.iv_time:
                //获取标题
                title = et_title.getText().toString().trim();
                //获取文本
                content = et_content.getText().toString().trim();
                Toast.makeText(NoteEditActivity.this, ""+content, Toast.LENGTH_SHORT).show();
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                String timeTwo = year + "年" + month + "月" + day + "日";
                Toast.makeText(NoteEditActivity.this, ""+timeTwo, Toast.LENGTH_SHORT).show();
                //获取xxxx年-xx月-xx日 格式的时间
                content += timeTwo;
                et_content.setText(content);
                //将时间字符串拼接如内容文本框
                break;
        }
    }

    String getTime() {
        String timeOne = null;
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        timeOne = "" + year + "-" + month + "-" + day;
        return timeOne;
    }

    public interface SetList{
        void  setList(List<Note> list);
    }

    /**
     * 插入
     */
    void insertNote() {
        String title = et_title.getText().toString().trim();
        String content = et_content.getText().toString().trim();
        String time=getTime();
        Note note=new Note(title,content,time);
        DBAction dba = new DBAction(this);
        dba.insertNote(note);
        Toast.makeText(NoteEditActivity.this, "插入成功", Toast.LENGTH_SHORT).show();
    }

    /**
     * 修改笔记
     */
    void updateNote(Note note){
        String title = et_title.getText().toString().trim();
        String content = et_content.getText().toString().trim();
        String time=getTime();
        DBAction dba = new DBAction(this);
        dba.updateNote(title,content,time,note);
        Toast.makeText(NoteEditActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
    }
}
