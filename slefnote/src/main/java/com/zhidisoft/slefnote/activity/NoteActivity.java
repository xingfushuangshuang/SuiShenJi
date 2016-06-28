package com.zhidisoft.slefnote.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhidisoft.slefnote.R;
import com.zhidisoft.slefnote.adapter.FragAdapter;
import com.zhidisoft.slefnote.fragment.AccountFragment;
import com.zhidisoft.slefnote.fragment.BillFragment;
import com.zhidisoft.slefnote.fragment.NoteFragment;

import java.util.ArrayList;
import java.util.List;

public class NoteActivity extends FragmentActivity implements View.OnClickListener {
    //声明全局变量
    private List<Fragment> list;
    private ViewPager viewPager;
    private LinearLayout lay_note, lay_bill, lay_acc;
    private ImageView iv_note_blue, iv_bill_blue, iv_acc_blue, iv_note, iv_bill, iv_acc;
    private TextView tv_note, tv_bill, tv_acc;
    //初始化颜色全局变量
    private int colorBlue;
    private int colorNor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        initBottom();
    }

    /**
     * 初始化Frament
     */
    void initFragment() {
        //获得viewPager
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        //三个Fragment初始化
        AccountFragment accFrag = new AccountFragment();
        BillFragment billFrag = new BillFragment();
        NoteFragment noteFrag = new NoteFragment();
        //初始化list-->添加Fragment
        list = new ArrayList<>();
        list.add(noteFrag);
        list.add(billFrag);
        list.add(accFrag);
        //声明适配器FragAdapter变量并初始化
        FragAdapter fragAdapter = new FragAdapter(this.getSupportFragmentManager(), list);
        //给视图翻页viewPager加载适配器
        viewPager.setAdapter(fragAdapter);
        //适配器的监听事件
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switchLinear(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /**
     * 初始化底部控件
     */
    void initBottom() {
        initFragment();
        //初始化LinearLayout
        lay_note = (LinearLayout) findViewById(R.id.lay_note);
        lay_bill = (LinearLayout) findViewById(R.id.lay_bill);
        lay_acc = (LinearLayout) findViewById(R.id.lay_acc);
        //初始化ImageView
        iv_note_blue = (ImageView) findViewById(R.id.iv_note_blue);
        iv_bill_blue = (ImageView) findViewById(R.id.iv_bill_blue);
        iv_acc_blue = (ImageView) findViewById(R.id.iv_acc_blue);
        iv_note = (ImageView) findViewById(R.id.iv_note);
        iv_bill = (ImageView) findViewById(R.id.iv_bill);
        iv_acc = (ImageView) findViewById(R.id.iv_acc);
        //初始化TextView
        tv_note = (TextView) findViewById(R.id.tv_note);
        tv_bill = (TextView) findViewById(R.id.tv_bill);
        tv_acc = (TextView) findViewById(R.id.tv_acc);
        //三个LinearLayout设置监听事件
        lay_note.setOnClickListener(this);
        lay_bill.setOnClickListener(this);
        lay_acc.setOnClickListener(this);
        switchLinear(0);
    }

    @Override
    public void onClick(View v) {
        //调用switchLinear(int id)方法
        switchLinear(v.getId());
    }

    /**
     * switch被点击的LinearLayout方法
     *
     * @param id
     */
    void switchLinear(int id) {
        clearBottom();
        switch (id) {
            case R.id.lay_note:
            case 0:
                iv_note_blue.setVisibility(View.VISIBLE);
                tv_note.setTextColor(colorBlue);
                viewPager.setCurrentItem(0);
                break;
            case R.id.lay_bill:
            case 1:
                iv_bill_blue.setVisibility(View.VISIBLE);
                tv_bill.setTextColor(colorBlue);
                viewPager.setCurrentItem(1);
                break;
            case R.id.lay_acc:
            case 2:
                iv_acc_blue.setVisibility(View.VISIBLE);
                tv_acc.setTextColor(colorBlue);
                viewPager.setCurrentItem(2);
                break;
        }
    }

    /**
     * 初始化未进行任何操作的底部状态
     */
    void clearBottom() {
        //获取颜色
        colorBlue=ContextCompat.getColor(NoteActivity.this,R.color.colorBlue);
        colorNor=ContextCompat.getColor(NoteActivity.this,R.color.colorNor);
        //蓝条默认不显示
        iv_note_blue.setVisibility(View.INVISIBLE);
        iv_bill_blue.setVisibility(View.INVISIBLE);
        iv_acc_blue.setVisibility(View.INVISIBLE);

        //字体默认正常颜色
        tv_note.setTextColor(colorNor);
        tv_bill.setTextColor(colorNor);
        tv_acc.setTextColor(colorNor);
    }
}
