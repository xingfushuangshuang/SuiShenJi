package com.example.myapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btn_simple, btn_muibtn, btn_logindialog, btn_popupwindow;
    private PopupWindow window;
    private String[] cities = new String[]{"北京", "上海", "广州"};
    private Spinner sp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    public void initView() {
        btn_simple = (Button) findViewById(R.id.btn_simple);
        btn_simple.setOnClickListener(this);
        btn_muibtn = (Button) findViewById(R.id.btn_muibtn);
        btn_muibtn.setOnClickListener(this);
        btn_logindialog = (Button) findViewById(R.id.btn_logindialog);
        btn_logindialog.setOnClickListener(this);
        btn_popupwindow = (Button) findViewById(R.id.btn_popupwindow);
        btn_popupwindow.setOnClickListener(this);
        //实例化spinner
        sp = (Spinner) findViewById(R.id.sp);
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, cities);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        sp.setAdapter(adapter);
//        sp.setPrompt("请选择城市");//标题
//        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                String city = cities[position];
//                showToast(city);
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_simple:
                showSimpleDialog();
                break;
            case R.id.btn_muibtn:
                showMuilBtnDialog();
                break;
            case R.id.btn_logindialog:
                showViewDialog();
                break;
            case R.id.btn_popupwindow:
//                showPopupWindow();
                String str = sp.getSelectedItem().toString();
                showToast(str);
                break;
        }
    }

    /**
     * 展示简单的dialog
     */
    public void showSimpleDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("标题");
        builder.setMessage("显示的信息");
        builder.setIcon(android.R.drawable.ic_dialog_alert);
        builder.create().show();
    }

    public void showMuilBtnDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("标题");
        builder.setMessage("显示的信息");
        builder.setIcon(android.R.drawable.ic_dialog_info);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showToast("您点击了确定");
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showToast("您点击了取消");
            }
        });
        builder.setNeutralButton("查看", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showToast("您点击了查看");
            }
        });
        builder.create().show();
    }

    public void showViewDialog() {
        LayoutInflater inflater = LayoutInflater.from(this);
        View v = inflater.inflate(R.layout.dialog_login, null);
        //实例化了控件
        final EditText et_usernmae = (EditText) v.findViewById(R.id.et_username);
        final EditText et_pwd = (EditText) v.findViewById(R.id.et_pwd);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(v);//设置显示的view
        builder.setTitle("登录");
        builder.setPositiveButton("登录", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //获取用户输入的用户名和密码
                String username = et_usernmae.getText().toString();
                String pwd = et_pwd.getText().toString();
                showToast("用户名:" + username + "密码" + pwd);
            }
        });
        builder.setNeutralButton("取消", null);
        builder.create().show();
    }

    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    public void showPopupWindow() {
        //加载布局
        LayoutInflater inflater = LayoutInflater.from(this);
        View v = inflater.inflate(R.layout.popupwindow_login, null);
        v.setBackgroundColor(Color.GRAY);
        //实例化了控件
        final EditText et_usernmae = (EditText) v.findViewById(R.id.et_username);
        final EditText et_pwd = (EditText) v.findViewById(R.id.et_pwd);
        Button btn_login = (Button) v.findViewById(R.id.btn_login);
        //新建一个popupwindow
        window = new PopupWindow(v, 800, ViewGroup.LayoutParams.WRAP_CONTENT);
        window.setFocusable(true);//获取焦点才可以输入内容
        window.setBackgroundDrawable(new BitmapDrawable());//给popupwindow一个背景
        window.setOutsideTouchable(true);//show之前

        window.showAtLocation(btn_popupwindow, Gravity.CENTER, 0, 0);//展示在某一个控件位置上

//        window.showAsDropDown();//展示在某一个控件下面
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取用户输入的内容
                String username = et_usernmae.getText().toString();
                String pwd = et_pwd.getText().toString();
                showToast("用户名：" + username + "密码" + pwd);
                window.dismiss();//消失
            }
        });
    }


//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (keyCode == KeyEvent.KEYCODE_BACK && window != null && window.isShowing()) {//点击了返回键
//            window.dismiss();
//            return true;//截断事件
//        }
//        return super.onKeyDown(keyCode, event);
//    }

    @Override
    public void onBackPressed() {
        if (window != null && window.isShowing()) {//点击了返回键
            window.dismiss();
        } else {
            super.onBackPressed();
        }
    }
}
