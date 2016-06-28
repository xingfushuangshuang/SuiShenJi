package com.zhidisoft.slefnote.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AbsoluteLayout;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.zhidisoft.slefnote.R;
import com.zhidisoft.slefnote.adapter.BillAdapter;
import com.zhidisoft.slefnote.bean.Bill;
import com.zhidisoft.slefnote.bean.BillStore;
import com.zhidisoft.slefnote.db.DBAction;
import com.zhidisoft.slefnote.fragment.CounterFragment;

import java.util.ArrayList;
import java.util.List;

public class BillActivity extends AppCompatActivity implements View.OnClickListener, CounterFragment.CounterFunc {
    //定义加载适配器
    private BillAdapter billAdapter;
    //定义图片数据源
    private int[] imgArrPay = {R.drawable.type_big_1, R.drawable.type_big_2, R.drawable.type_big_3, R.drawable.type_big_4, R.drawable.type_big_5, R.drawable.type_big_6, R.drawable.type_big_7, R.drawable.type_big_8, R.drawable.type_big_9, R.drawable.type_big_10, R.drawable.type_big_11, R.drawable.type_big_12,};
    private int[] imgArrEarn = {R.drawable.type_big_13, R.drawable.type_big_14, R.drawable.type_big_15, R.drawable.type_big_16, R.drawable.type_big_17, R.drawable.type_big_18, R.drawable.type_big_19,};
    //定义图片对应字符串的数据源
    private String[] strArrPay = {"一般", "用餐", "零食", "交通", "充值", "购物", "娱乐", "住房", "约会", "网购", "日用品", "香烟"};
    private String[] strArrEarn = {"工资", "外快兼职", "奖金", "借入", "零花钱", "招资投入", "礼物红包"};
    private int[] imgArr;
    private String[] strArr;
    //Note对象集合
    private List<Bill> list;
    private GridView gv_bill;
    private TextView tv_pay, tv_earn, tv_cash, tv_card, tv_credit, tv_alipay;
    //颜色
    private int colorWhite, colorBlack, colorBlue;
    //第二个LinearLayout展示GridView选中item的图片 和 名称
    private ImageView iv_item_select;
    private TextView tv_item_select;
    //支付方式控件
    private TextView tv_pay_mode;
    //点击tv_pay_mode跳转时请求码
    public static final int requestCode = 0, resultCode = 1;
    //弹出PopupWindow
    private PopupWindow win;
    //获取计算机输入以及结果的TextView
    private TextView tv_result;
    //定义收入 支出 标识常量  标记是收入还是支出
    public static final int pay = 0, earn = 1;
    //声明数据库操作类变量
    private DBAction dba;
    //声明一条数据包含的变量
    private int usecat, imgid;
    private String useway;
    private double moneynum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill);
        initView();
    }

    public void initView() {
        //初始化控件
        tv_pay = (TextView) findViewById(R.id.tv_right);
        tv_earn = (TextView) findViewById(R.id.tv_left);
        gv_bill = (GridView) findViewById(R.id.gv_bill);

        iv_item_select = (ImageView) findViewById(R.id.iv_item_select);
        tv_item_select = (TextView) findViewById(R.id.tv_item_select);
        tv_pay_mode = (TextView) findViewById(R.id.tv_pay_mode);

        //获取颜色
        colorWhite = ContextCompat.getColor(this, R.color.colorWhite);
        colorBlack = ContextCompat.getColor(this, R.color.colorBlack);
        colorBlue = ContextCompat.getColor(this, R.color.colorBlue);
        //控件监听
        tv_pay.setOnClickListener(this);
        tv_earn.setOnClickListener(this);
        //点击选择支付方式
        tv_pay_mode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(BillActivity.this,WinActivity.class);
                startActivityForResult(intent,requestCode);
            }
        });
        //默认选择右边
        switchTextView(R.id.tv_right);
        //GridView的item点击事件
        useway=list.get(0).getBillName();
        gv_bill.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                iv_item_select.setImageResource(list.get(position).getImageId());
                tv_item_select.setText(list.get(position).getBillName());
                imgid = list.get(position).getImageId();//标识一条数据的图片int值
                useway = list.get(position).getBillName();//标识一条数据的用途描述
            }
        });

        //初始化获取计算器输入及结果的TextView控件
        tv_result = (TextView) findViewById(R.id.tv_result);
        //获取加载计算机FragmentMent实例 通过其setCounterFuc方法，对计算机FragmentMent中的接口变量用this实例化
        FragmentManager fm = getSupportFragmentManager();
        CounterFragment cf = (CounterFragment) fm.findFragmentById(R.id.frag_one);
        cf.setCounterFunc(this);

    }

    /**
     * 重写onClick()
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        switchTextView(v.getId());
    }

    public void switchTextView(int id) {
        clearTextView();
        list = initlist(imgArrPay, strArrPay);
        billAdapter = new BillAdapter(list, this);
        switch (id) {
            case R.id.tv_right:
                tv_pay.setTextColor(colorWhite);
                tv_pay.setBackgroundColor(colorBlue);
                list = initlist(imgArrPay, strArrPay);
                billAdapter.setList(list);
                billAdapter.notifyDataSetChanged();
                gv_bill.setAdapter(billAdapter);
                iv_item_select.setImageResource(imgArrPay[0]);
                tv_item_select.setText(strArrPay[0]);
                usecat = 0;//标识这条数据中是支出
                break;
            case R.id.tv_left:
                tv_earn.setTextColor(colorWhite);
                tv_earn.setBackgroundColor(colorBlue);
                list = initlist(imgArrEarn, strArrEarn);
                billAdapter.setList(list);
                billAdapter.notifyDataSetChanged();
                gv_bill.setAdapter(billAdapter);
                iv_item_select.setImageResource(imgArrEarn[0]);
                tv_item_select.setText(strArrEarn[0]);
                usecat = 1;
                break;//标识这条数据中是收入
        }
    }

    /**
     * 初始化GridView列表
     */
    public List<Bill> initlist(int[] imgArr, String[] strArr) {
        //构建数据源
        List<Bill> list = new ArrayList<>();
        for (int i = 0; i < imgArr.length; i++) {
            Bill bill = new Bill();
            bill.setImageId(imgArr[i]);
            bill.setBillName(strArr[i]);
            list.add(bill);
        }
        return list;
    }

    /**
     * 清空TextView方法
     */
    public void clearTextView() {
        tv_pay.setTextColor(colorBlack);
        tv_earn.setTextColor(colorBlack);
        tv_pay.setBackgroundColor(colorWhite);
        tv_earn.setBackgroundColor(colorWhite);
    }

    public void setResult(String result) {
        tv_result.setText(result);
    }

    @Override
    public String getTv() {
        return tv_result.getText().toString();
    }

    @Override
    public void setTv(String str) {
        setResult(str);
    }

    //重写接口中添加数据到数据库的方法
    @Override
    public void insertData() {
        moneynum = Double.valueOf(tv_result.getText().toString());//一条数据中的钱数
        DBAction dba = new DBAction(this);//获取数据库操作对象
        BillStore billStore = new BillStore(usecat, imgid, useway, moneynum);//初始化BillStore对象
        dba.insertBillStore(billStore);
        Toast.makeText(BillActivity.this, "插入成功", Toast.LENGTH_SHORT).show();
    }

    /**
     * 重写方法，不用调用，只需判断requestCode == this.requestCode即可执行
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == this.requestCode) {
            switch (resultCode) {
                case 1:
                    String str=data.getStringExtra("resultInfo");
                    Log.i("info","str="+str);
                    tv_pay_mode.setText(str);
                    break;
            }
        }
    }
}
