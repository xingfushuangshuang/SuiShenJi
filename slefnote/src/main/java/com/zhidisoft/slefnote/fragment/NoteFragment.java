package com.zhidisoft.slefnote.fragment;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.zhidisoft.slefnote.R;
import com.zhidisoft.slefnote.activity.NoteActivity;
import com.zhidisoft.slefnote.activity.NoteDetailAct;
import com.zhidisoft.slefnote.activity.NoteEditActivity;
import com.zhidisoft.slefnote.adapter.NoteAdapter;
import com.zhidisoft.slefnote.bean.Note;
import com.zhidisoft.slefnote.db.DBAction;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * 实现接口 是用来使用回调接口的方式来获取NoteEditActivity的日记对象  暂时未用
 * A simple {@link Fragment} subclass.
 */
public class NoteFragment extends Fragment implements NoteEditActivity.SetList {
    private ImageView iv_add;
    private View v;
    private List<Note> list;//集合很多地方会用到 要定义为全局变量
    private ListView lv_note;
    private DBAction dba;
    private NoteAdapter noteAdapter;
    private int requestCode = 0;

    public NoteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_note, container, false);
        lv_note = (ListView) v.findViewById(R.id.lv_note);
        iv_add = (ImageView) v.findViewById(R.id.iv_add);

        iv_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), NoteEditActivity.class);
                startActivity(intent);
            }
        });

        lv_note.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), NoteDetailAct.class);
                //获得被点击item对应的对象note
                Note note = list.get(position);
                //将对象note属性放入intent
                intent.putExtra("Note类bean", note);
                //跳转
                startActivity(intent);
            }
        });
        lv_note.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                initMuilDialog(position);
                return true;
            }
        });

        //先设置ListView加载noteAdapter
        dba = new DBAction(getActivity());//获得数据操作类
        list = new ArrayList<>();//初始化集合
        noteAdapter = new NoteAdapter(list, getActivity());
        lv_note.setAdapter(noteAdapter);

        return v;
    }

    /**
     * ListView 的条目长按事件Dialog 实现修改、删除
     */
    protected void initMuilDialog(final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("请选择操作");
        builder.setPositiveButton("删除", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                DBAction dba = new DBAction(getActivity());
                Note note = list.get(position);//获取note对象
                dba.delNote(note);
                queryAll();//在点击事件中实现实时刷新
                Toast.makeText(getActivity(), "删除成功", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getActivity(), "你点击了取消", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNeutralButton("修改", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(getActivity(), NoteEditActivity.class);
                Note note = list.get(position);//获取note对象
                intent.putExtra("修改的日记", note);
                startActivity(intent);
                Toast.makeText(getActivity(), "你点击了修改", Toast.LENGTH_SHORT).show();
            }
        });
        builder.create().show();

    }

    /**
     * 获取时间方法
     *
     * @return
     */
    public String getTime() {
        String timeOne = null;
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        timeOne = year + "年" + month + "月" + day + "日";
        return timeOne;
    }

    /**
     * 实现方法
     *
     * @param list
     */
    @Override
    public void setList(List<Note> list) {
        this.list = list;
    }

    /**
     * 重写onStart方法，每次都显示note表中所有记录
     */
    @Override
    public void onStart() {
        super.onStart();
        queryAll();
    }

    /**
     * 查询所有数据
     */
    void queryAll() {
        //获取list数据
        list = dba.queryNoteAll();
        noteAdapter.setList(list);
        //更新
        noteAdapter.notifyDataSetChanged();
        lv_note.setAdapter(noteAdapter);
    }


}
