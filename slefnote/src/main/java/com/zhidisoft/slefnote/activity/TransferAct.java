package com.zhidisoft.slefnote.activity;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhidisoft.slefnote.R;

public class TransferAct extends AppCompatActivity implements View.OnClickListener {
    private ImageView iv_return;
    private RelativeLayout lay_out, lay_in;
    private PopupWindow win;
    private TextView tv_out, tv_in, tv_cash, tv_card, tv_credit, tv_alipay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer);

        initView();
    }

    /**
     * 初始化界面
     */
    void initView() {
        iv_return = (ImageView) findViewById(R.id.iv_return);
        lay_out = (RelativeLayout) findViewById(R.id.lay_out);
        lay_in = (RelativeLayout) findViewById(R.id.lay_in);
        tv_out = (TextView) findViewById(R.id.tv_out);
        tv_in = (TextView) findViewById(R.id.tv_in);

        lay_out.setOnClickListener(this);
        lay_in.setOnClickListener(this);

        iv_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    /**
     * 初始化阻塞式弹出对话框
     * 实例化PopupWindow变量-->获取焦点为true
     * -->设置点击外部消失-->实例化布局的控件-->设置展示的控件位置
     * -->布局中TextView的监听事件
     */
    protected void initPopupWin() {
        LayoutInflater inflater = LayoutInflater.from(TransferAct.this);
        View popView = inflater.inflate(R.layout.pay_mode_win, null);
        win = new PopupWindow(popView, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT, true);
        win.setFocusable(false);
        win.setBackgroundDrawable(new BitmapDrawable());
        win.setTouchable(true);
        win.setOutsideTouchable(false);
        //初始化控件
        tv_cash = (TextView) popView.findViewById(R.id.tv_cash);
        tv_card = (TextView) popView.findViewById(R.id.tv_card);
        tv_credit = (TextView) popView.findViewById(R.id.tv_credit);
        tv_alipay = (TextView) popView.findViewById(R.id.tv_alipay);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.lay_out:
                initPopupWin();
                //设置弹出位置
                win.showAtLocation(lay_out, Gravity.BOTTOM | Gravity.CENTER, 0, 0);
                //设置监听
                tv_cash.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        tv_out.setText(tv_cash.getText());
                        win.dismiss();
                    }
                });
                tv_card.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        tv_out.setText(tv_card.getText());
                        win.dismiss();
                    }
                });
                tv_credit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        tv_out.setText(tv_credit.getText());
                        win.dismiss();
                    }
                });
                tv_alipay.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        tv_out.setText(tv_alipay.getText());
                        win.dismiss();
                    }
                });
                break;
            case R.id.lay_in:
                initPopupWin();
                //设置弹出位置
                win.showAtLocation(lay_in, Gravity.BOTTOM | Gravity.CENTER, 0, 0);
                //设置监听
                tv_cash.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        tv_in.setText(tv_cash.getText());
                        win.dismiss();
                    }
                });
                tv_card.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        tv_in.setText(tv_card.getText());
                        win.dismiss();
                    }
                });
                tv_credit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        tv_in.setText(tv_credit.getText());
                        win.dismiss();
                    }
                });
                tv_alipay.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        tv_in.setText(tv_alipay.getText());
                        win.dismiss();
                    }
                });
                break;
        }
    }
}
