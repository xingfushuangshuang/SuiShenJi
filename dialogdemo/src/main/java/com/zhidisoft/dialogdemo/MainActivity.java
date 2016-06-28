package com.zhidisoft.dialogdemo;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    //定义全局变量
    private Button btn;
    private Button btn_mui;
    private Button btn_self;
    private Button btn_popup;
    private PopupWindow win;
    private Spinner spin, spin_static;
    private String[] strCities = {"北京", "上海", "郑州"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //创建主界面时初始化 btn
        initBtn();

    }

    /**
     * 点击时 switch控件id
     * --> 展示对应Dialog
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_dialog:
                initDialog();
                break;
            case R.id.btn_muiDialog:
                initMuilDialog();
                break;
            case R.id.btn_self:
                initSelfDialog();
                break;
            case R.id.btn_pop:
                initPopupWin();
                break;
            case R.id.spin:
                initSpinner();
                break;
        }

    }

    /**
     * 初始化按钮控件
     */
    protected void initBtn() {
        //初始化控件
        btn = (Button) findViewById(R.id.btn_dialog);
        btn_mui = (Button) findViewById(R.id.btn_muiDialog);
        btn_self = (Button) findViewById(R.id.btn_self);
        btn_popup = (Button) findViewById(R.id.btn_pop);
        //初始化Spinner
        initSpinner();
        staticSpinner();
        //设置监听事件
        btn.setOnClickListener(this);
        btn_mui.setOnClickListener(this);
        btn_self.setOnClickListener(this);
        btn_popup.setOnClickListener(this);
//        spin.setOnClickListener(this);
    }

    /**
     * 初始化简单Dialog
     */
    protected void initDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("简单对话框");
        builder.setMessage("你好，这是提示信息");
        builder.setIcon(R.drawable.ic_launcher);
        builder.create().show();
    }

    /**
     * 初始化复杂Dialog
     * 声明并实例化对话框建造器builder-->设置标题-->设置信息-->设置积极、消极点击事件相关属性
     * -->展示
     */
    protected void initMuilDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("简单对话框");
        builder.setMessage("你好，这是提示信息");
        builder.setIcon(R.drawable.ic_launcher);
        builder.setPositiveButton("删除", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showToast("你点击了删除");
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showToast("你点击了取消");
            }
        });
        builder.setNeutralButton("查看", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showToast("你点几了查看");
            }
        });
        builder.create().show();

    }

    /**
     * 初始化自定义Dialog
     * 获取布局加载器-->加载布局 -->布局获取 EditText控件并实例化-->实例化对话框建造器builder
     * -->builder设置需要加载的view-->设置积极、消极对应的点击属性
     * -->点击时获取加载的view的EditText的输入
     * -->展示
     */
    protected void initSelfDialog() {
        LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
        View view = inflater.inflate(R.layout.dialog_login, null);
        /*   控件实例为何final修饰
        1.局部内部类对象生命周期和局部变量不同，有可能局部方法结束后（局部变量也销毁后）局部内部类对象还存在，为防止此情况发生，局部内部类对象创建时会拷贝一份局部变量。
        2.由于是在局部内部类对象创建时拷贝局部变量，如何保持局部变量和拷贝数据一致性，需要使用final限定，拷贝后不变，保持一致性。
         */
        final EditText et_user = (EditText) view.findViewById(R.id.et_username);
        final EditText et_pwd = (EditText) view.findViewById(R.id.et_pwd);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(view);
        builder.setTitle("登陆");
        builder.setPositiveButton("登录", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showToast("用户名：" + et_user.getText() + " 密码：" + et_pwd.getText());
            }
        });
        builder.setNegativeButton("取消", null);
        builder.create().show();
    }

    /**
     * 初始化阻塞式弹出对话框
     * 加载布局设置背景-->实例化PopupWindow变量-->获取焦点为true
     * -->设置点击外部消失-->实例化布局的控件-->设置展示的控件位置
     * -->布局中btn_login的监听事件-->弹出消息(同时关闭PopupWindow实例win)
     */
    protected void initPopupWin() {
        LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
         View popView = inflater.inflate(R.layout.popup_login, null);
        popView.setBackgroundColor(Color.GREEN);

        win = new PopupWindow(popView, ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT, true);
        win.setFocusable(true);

        win.setBackgroundDrawable(new BitmapDrawable());

//       win.setTouchable(true);
        win.setOutsideTouchable(false);


        final EditText et_user = (EditText) popView.findViewById(R.id.et_username);
        final EditText et_pwd = (EditText) popView.findViewById(R.id.et_pwd);
        Button btn_login = (Button) popView.findViewById(R.id.btn_login);
        win.showAtLocation(btn_popup, Gravity.CENTER, 0, 0);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("用户名:" + et_user.getText() + " 密码:" + et_pwd.getText());
                win.dismiss();
            }
        });
    }

    /**
     * 初始化Spinner控件
     * 初始化Spinner变量  -->设置适配器 --> 设置适配器下拉视图源 -->设置标题
     * --> 监听事件:setOnItemSelectedListener
     */
    protected void initSpinner() {
        spin = (Spinner) findViewById(R.id.spin);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, strCities);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spin.setAdapter(adapter);
        spin.setPrompt("请选择城市");
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                showToast("你选择的城市是：" + spin.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    /**
     * 静态方式定义Spinner
     */
    protected void staticSpinner() {
        spin_static = (Spinner) findViewById(R.id.spin_static);
        spin_static.setPrompt("请选择城市");
        spin_static.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                showToast("你选择的城市是：" + spin_static.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    /**
     * 定义点击按钮时弹出消息方法
     *
     * @param msg
     */
    protected void showToast(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * 重写系统方法 点击返回键时退出 如果win不展示  退出主页
     */
    @Override
    public void onBackPressed() {
        if (win != null && win.isShowing()) {
            win.dismiss();
        }
        super.onBackPressed();
    }

//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        return super.onKeyDown(keyCode, event);
//    }
}
