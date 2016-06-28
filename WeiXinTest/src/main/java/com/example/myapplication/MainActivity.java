package com.example.myapplication;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adapter.MyAdapter;
import com.example.fragment.FXFragmen;
import com.example.fragment.MFragment;
import com.example.fragment.TXLFragment;
import com.example.fragment.WeiXinFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity implements View.OnClickListener {
    private ViewPager pager;
    private LinearLayout ll_wx, ll_txl, ll_fx, ll_me;
    private ImageView iv_wx, iv_txl, iv_fx, iv_me;
    private TextView tv_wx;
    private TextView tv_txl;
    private TextView tv_fx;
    private TextView tv_me;
    private int textcolornor;
    private int textcolorselected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }

    public void initView() {
        pager = (ViewPager) findViewById(R.id.vp_content);
        initPager();
        textcolornor = ContextCompat.getColor(MainActivity.this, R.color.colortextgray);
        textcolorselected = ContextCompat.getColor(this, R.color.colortextgreen);
        //实例化控件
        ll_wx = (LinearLayout) findViewById(R.id.ll_wx);
        ll_txl = (LinearLayout) findViewById(R.id.ll_txl);
        ll_fx = (LinearLayout) findViewById(R.id.ll_fx);
        ll_me = (LinearLayout) findViewById(R.id.ll_me);
        ll_wx.setOnClickListener(this);
        ll_txl.setOnClickListener(this);
        ll_me.setOnClickListener(this);
        ll_fx.setOnClickListener(this);
        //实例化imageview
        iv_wx = (ImageView) findViewById(R.id.iv_wx);
        iv_fx = (ImageView) findViewById(R.id.iv_fx);
        iv_txl = (ImageView) findViewById(R.id.iv_txl);
        iv_me = (ImageView) findViewById(R.id.iv_me);
        //textview
        tv_wx = (TextView) findViewById(R.id.tv_wx);
        tv_txl = (TextView) findViewById(R.id.tv_txl);
        tv_me = (TextView) findViewById(R.id.tv_me);
        tv_fx = (TextView) findViewById(R.id.tv_fx);
        //设置微信为默认选项
        switchTag(0);
    }

    public void initPager() {
        List<Fragment> list = new ArrayList<>();
        WeiXinFragment fg1 = new WeiXinFragment();
        TXLFragment fg2 = new TXLFragment();
        FXFragmen fg3 = new FXFragmen();
        MFragment fg4 = new MFragment();
        list.add(fg1);
        list.add(fg2);
        list.add(fg3);
        list.add(fg4);
        MyAdapter adapter = new MyAdapter(getSupportFragmentManager(), list);
        pager.setAdapter(adapter);
        pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            //选项被选中的时候
            @Override
            public void onPageSelected(int position) {
                switchTag(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    @Override
    public void onClick(View v) {
        switchTag(v.getId());
    }

    /**
     * 清空状态为未选中
     */
    public void clearFocus() {
        tv_wx.setTextColor(textcolornor);
        tv_txl.setTextColor(textcolornor);
        tv_me.setTextColor(textcolornor);
        tv_fx.setTextColor(textcolornor);
        iv_wx.setImageResource(R.drawable.ahk);
        iv_txl.setImageResource(R.drawable.ahi);
        iv_fx.setImageResource(R.drawable.ahm);
        iv_me.setImageResource(R.drawable.aho);
    }

    public void switchTag(int position) {
        //清空选择的状态
        clearFocus();
        switch (position) {
            case R.id.ll_me:
            case 3:
                tv_me.setTextColor(textcolorselected);
                iv_me.setImageResource(R.drawable.ahn);
                //当前选项是第几个
                pager.setCurrentItem(3);
                break;
            case R.id.ll_wx:
            case 0:
                tv_wx.setTextColor(textcolorselected);
                iv_wx.setImageResource(R.drawable.ahj);
                pager.setCurrentItem(0);
                break;
            case R.id.ll_txl:
            case 1:
                tv_txl.setTextColor(textcolorselected);
                iv_txl.setImageResource(R.drawable.ahh);
                pager.setCurrentItem(1);
                break;
            case R.id.ll_fx:
            case 2:
                tv_fx.setTextColor(textcolorselected);
                iv_fx.setImageResource(R.drawable.ahl);
                pager.setCurrentItem(2);
                break;
        }
    }

}
