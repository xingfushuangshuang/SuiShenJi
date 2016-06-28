package com.example.girdtest;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    //支出的类型
    private String[] str_out = {"一般", "用餐", "零食", "交通", "充值", "购物", "娱乐", "住房", "约会", "网购", "日用品", "香烟"};
    private Integer[] img_out = {R.drawable.type_big_1, R.drawable.type_big_2, R.drawable.type_big_3, R.drawable.type_big_4,
            R.drawable.type_big_5, R.drawable.type_big_6, R.drawable.type_big_7, R.drawable.type_big_8,
            R.drawable.type_big_9, R.drawable.type_big_10, R.drawable.type_big_11, R.drawable.type_big_12};

    //收入的类型
    private String[] str_in = {"工资", "外快兼职", "奖金", "借入", "零花钱", "投资收入", "礼物红包"};
    private Integer[] img_in = {R.drawable.type_big_13, R.drawable.type_big_14, R.drawable.type_big_15,
            R.drawable.type_big_16, R.drawable.type_big_17, R.drawable.type_big_18, R.drawable.type_big_19};

    private List<GridBean> list_in;
    private List<GridBean> list_out;
    private MyAdapter adapter;
    private ImageView iv_selected;
    private TextView tv_selected,tv_in,tv_out;
    private int color_blue,color_white;
    //全局的bean 用来接收
    private  GridBean bean;
    private Button btn_save;
    public static final int TYPE_IN=0;
    public static final int TYPE_OUT=1;
    //当前的状态
    private int TYPE_SELECTED=TYPE_OUT;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    public void initView() {
        color_blue= ContextCompat.getColor(this,android.R.color.holo_blue_bright);
        color_white=ContextCompat.getColor(this,android.R.color.white);
        initData();
        tv_in = (TextView) findViewById(R.id.tv_in);
         tv_out = (TextView) findViewById(R.id.tv_out);
        btn_save = (Button) findViewById(R.id.btn_save);
        btn_save.setOnClickListener(this);
        tv_in.setOnClickListener(this);
        tv_out.setOnClickListener(this);
        iv_selected = (ImageView) findViewById(R.id.iv_selected);
        tv_selected = (TextView) findViewById(R.id.tv_selected);
        GridView gv = (GridView) findViewById(R.id.gv);
        adapter = new MyAdapter(list_out, this);
        gv.setAdapter(adapter);
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                bean= (GridBean) adapter.getItem(position);
                iv_selected.setImageResource(bean.getImgid());
                tv_selected.setText(bean.getType());
            }
        });
        bean=list_out.get(0);//默认值
    }

    public void initData() {
        //实例化收入
        list_in = new ArrayList<>();
        for (int i = 0; i < str_in.length; i++) {
            GridBean bean = new GridBean();
            bean.setImgid(img_in[i]);//图片id
            bean.setType(str_in[i]);
            list_in.add(bean);
        } //实例化支出
        list_out = new ArrayList<>();
        for (int i = 0; i < str_out.length; i++) {
            GridBean bean = new GridBean();
            bean.setImgid(img_out[i]);//图片id
            bean.setType(str_out[i]);
            list_out.add(bean);
        }
    }

    public void changData(List<GridBean> list) {
        bean=list.get(0);//默认值
        iv_selected.setImageResource(bean.getImgid());
        tv_selected.setText(bean.getType());
        adapter.setList(list);
        adapter.notifyDataSetChanged();
    }

    public void changeColor(int id){
        tv_in.setBackgroundColor(color_white);
        tv_out.setBackgroundColor(color_white);
        if(id==R.id.tv_in){
            tv_in.setBackgroundColor(color_blue);
        }else {
            tv_out.setBackgroundColor(color_blue);
        }
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_in:
                changData(list_in);
                changeColor(R.id.tv_in);
                TYPE_SELECTED=TYPE_IN;
                break;
            case R.id.tv_out:
                changData(list_out);
                changeColor(R.id.tv_out);
                TYPE_SELECTED=TYPE_OUT;
                break;
            case R.id.btn_save:
                //需要知道金额(直接从textview取)
                //收入支出的类型
                //图片id
                //文字
                Toast.makeText(this,bean.getType()+bean.getImgid()+"收支类型"+TYPE_SELECTED,Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
