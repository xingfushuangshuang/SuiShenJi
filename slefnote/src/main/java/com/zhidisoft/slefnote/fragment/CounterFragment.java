package com.zhidisoft.slefnote.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.zhidisoft.slefnote.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CounterFragment extends Fragment implements View.OnClickListener {
    private View v;//主视图
    //控件
    private TextView tv_1, tv_2, tv_3, tv_4, tv_5, tv_6, tv_7, tv_8, tv_9, tv_0, tv_add, tv_ac, tv_del, tv_point, tv_equ;
    private double num1 = 0, num2 = 0;//声明两个double类型的参数 接收要显示的TextView前后的值
    private double result = 0;//计算结果
    int op = 0;//判断操作数
    boolean isClickEqu = false;//判断是否按下“=”
    private CounterFunc counterFunc;

    public void setCounterFunc(CounterFunc counterFunc) {
        this.counterFunc = counterFunc;
    }

    public CounterFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_counter, container, false);
        initView();

        return v;
    }

    public void initView() {
        //初始化控件
        tv_0 = (TextView) v.findViewById(R.id.tv_0);
        tv_1 = (TextView) v.findViewById(R.id.tv_1);
        tv_2 = (TextView) v.findViewById(R.id.tv_2);
        tv_3 = (TextView) v.findViewById(R.id.tv_3);
        tv_4 = (TextView) v.findViewById(R.id.tv_4);
        tv_5 = (TextView) v.findViewById(R.id.tv_5);
        tv_6 = (TextView) v.findViewById(R.id.tv_6);
        tv_7 = (TextView) v.findViewById(R.id.tv_7);
        tv_8 = (TextView) v.findViewById(R.id.tv_8);
        tv_9 = (TextView) v.findViewById(R.id.tv_9);
        tv_add = (TextView) v.findViewById(R.id.tv_add);
        tv_ac = (TextView) v.findViewById(R.id.tv_ac);
        tv_del = (TextView) v.findViewById(R.id.tv_del);
        tv_point = (TextView) v.findViewById(R.id.tv_point);
        tv_equ = (TextView) v.findViewById(R.id.tv_equ);
        //设置监听
        tv_0.setOnClickListener(this);
        tv_1.setOnClickListener(this);
        tv_2.setOnClickListener(this);
        tv_3.setOnClickListener(this);
        tv_4.setOnClickListener(this);
        tv_5.setOnClickListener(this);
        tv_6.setOnClickListener(this);
        tv_7.setOnClickListener(this);
        tv_8.setOnClickListener(this);
        tv_9.setOnClickListener(this);
        tv_point.setOnClickListener(this);
        tv_add.setOnClickListener(this);
        tv_ac.setOnClickListener(this);
        tv_del.setOnClickListener(this);
        tv_equ.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switchTv(v.getId());
    }

    public void switchTv(int id) {
        if (counterFunc != null) {
            switch (id) {
                //数字按键功能
                case R.id.tv_1:
                    isEqu();
                    String myStr1 = counterFunc.getTv();
                    myStr1 += "1";
                    counterFunc.setTv(myStr1);
                    break;
                case R.id.tv_2:
                    isEqu();
                    String myStr2 = counterFunc.getTv();
                    myStr2 += "2";
                    counterFunc.setTv(myStr2);
                    break;
                case R.id.tv_3:
                    isEqu();
                    String myStr3 = counterFunc.getTv();
                    myStr3 += "3";
                    counterFunc.setTv(myStr3);
                    break;
                case R.id.tv_4:
                    isEqu();
                    String myStr4 = counterFunc.getTv();
                    myStr4 += "4";
                    counterFunc.setTv(myStr4);
                    break;
                case R.id.tv_5:
                    isEqu();
                    String myStr5 = counterFunc.getTv();
                    myStr5 += "5";
                    counterFunc.setTv(myStr5);
                    break;
                case R.id.tv_6:
                    isEqu();
                    String myStr6 = counterFunc.getTv();
                    myStr6 += "6";
                    counterFunc.setTv(myStr6);
                    break;
                case R.id.tv_7:
                    isEqu();
                    String myStr7 = counterFunc.getTv();
                    myStr7 += "7";
                    counterFunc.setTv(myStr7);
                    break;
                case R.id.tv_8:
                    isEqu();
                    String myStr8 = counterFunc.getTv();
                    myStr8 += "8";
                    counterFunc.setTv(myStr8);
                    break;
                case R.id.tv_9:
                    isEqu();
                    String myStr9 = counterFunc.getTv();
                    myStr9 += "9";
                    counterFunc.setTv(myStr9);
                    break;
                case R.id.tv_0:
                    isEqu();
                    String myStr0 = counterFunc.getTv();
                    myStr0 += "0";
                    counterFunc.setTv(myStr0);
                    break;
                case R.id.tv_point:
                    isEqu();
                    String myStrPoint = counterFunc.getTv();
                    //判断是否有一个点
                    if (myStrPoint.contains(".")) {
                        break;
                    }
                    myStrPoint += ".";
                    counterFunc.setTv(myStrPoint);
                    break;
                //逐个删除tv_del，清空tv_ac功能
                case R.id.tv_del:
                    String strDel = counterFunc.getTv();
                    try {
                        counterFunc.setTv(strDel.substring(0, strDel.length() - 1));
                    } catch (Exception e) {
                        counterFunc.setTv("");
                    }
                    break;
                case R.id.tv_ac:
                    counterFunc.setTv(null);
                    break;
                //相加、等号功能
                case R.id.tv_add:
                    String strAdd = counterFunc.getTv();
                    //strAdd.isEmpty()和strAdd.equals("")效果相同 可以去掉其一
                    if (strAdd==null ||strAdd.isEmpty()||strAdd.equals("")) {
                        return;
                    }
                    num1 = Double.valueOf(strAdd);
                    counterFunc.setTv(null);
                    op = 1;//设置操作码为一
                    isClickEqu = false;
                    break;
                case R.id.tv_equ:
                    if (isClickEqu){
                        break;
                    }
                    String strEqu = counterFunc.getTv();
                    if (strEqu==null ||strEqu.isEmpty()) {
                        break;
                    }
                    num2 = Double.valueOf(strEqu);
                    counterFunc.setTv(null);
                    switch (op) {
                        case 0:
                            result = num2;
                            counterFunc.setTv(String.valueOf(result));
                            counterFunc.insertData();
//                            this.getActivity().finish();//结束
                            break;
                        case 1:
                            result = num1 + num2;
                            num1=0;//重新设定num1
                            num2=0;//重新设定num2
                            counterFunc.setTv(String.valueOf(result));
                            counterFunc.insertData();
                            this.getActivity().finish();//结束
                            break;
                        default:
                            result = 0;
                            break;
                    }
//                  counterFunc.setTv(String.valueOf(result));
                    isClickEqu = true;
                    break;
            }
        }
    }

    /**
     * 接口定义按键的功能
     */
    public interface CounterFunc {
        String getTv();
        void setTv(String str);//数字键功能
        void insertData();//按等号时添加数据到数据库
    }

    /**
     * 判断是否按等号键
     */
    public void isEqu() {
        if (isClickEqu) {
            counterFunc.setTv(null);
            isClickEqu = false;
        }
    }
}
