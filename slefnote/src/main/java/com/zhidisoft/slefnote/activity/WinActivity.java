package com.zhidisoft.slefnote.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.zhidisoft.slefnote.R;

public class WinActivity extends Activity implements View.OnClickListener {
    private TextView tv_cash, tv_credit, tv_card, tv_alipay;
    private Intent intent;
    private String str;
    private int resultCode=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_win);
        initView();
    }

    void initView() {
        //初始化控件
        tv_cash = (TextView) findViewById(R.id.tv_cash);
        tv_card = (TextView) findViewById(R.id.tv_card);
        tv_credit = (TextView) findViewById(R.id.tv_credit);
        tv_alipay = (TextView) findViewById(R.id.tv_alipay);
        //设置监听
        tv_cash.setOnClickListener(this);
        tv_card.setOnClickListener(this);
        tv_credit.setOnClickListener(this);
        tv_alipay.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_cash:
                str="现金";
                setData(str);
                break;
            case R.id.tv_card:
                str="储蓄卡";
                setData(str);
                break;
            case R.id.tv_credit:
                str="信用卡";
                setData(str);
                break;
            case R.id.tv_alipay:
                str="支付宝";
                setData(str);
                break;
        }
    }

    /**
     * 设置信息 回送给BillActivity
     * @param str
     */
    void setData(String str){
        intent=this.getIntent();
        intent.putExtra("resultInfo",str);
        this.setResult(resultCode,intent);
        finish();
    }
}
