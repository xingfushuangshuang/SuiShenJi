package com.zhidisoft.fragcomm.entity;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.zhidisoft.fragcomm.R;
import com.zhidisoft.fragcomm.fragment.LeftFrag;
import com.zhidisoft.fragcomm.fragment.RightFrag;

public class MainActivity extends AppCompatActivity implements LeftFrag.ClickItem {
    private RightFrag frag_right;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //获取实例
        FragmentManager fm = getSupportFragmentManager();
        LeftFrag frag_left = (LeftFrag) fm.findFragmentById(R.id.frag_left);
        frag_left.setClickItem(MainActivity.this);
        frag_right = (RightFrag) fm.findFragmentById(R.id.frag_right);
    }

    /**
     * 实现接口的方法
     * @param title
     */
    @Override
    public void initTitle(String title) {
        frag_right.initRightData(title);
    }
}
