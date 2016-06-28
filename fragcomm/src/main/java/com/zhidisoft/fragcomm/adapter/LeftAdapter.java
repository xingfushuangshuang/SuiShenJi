package com.zhidisoft.fragcomm.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhidisoft.fragcomm.R;

/**
 * Created by Administrator on 2016/6/7.
 */
public class LeftAdapter extends BaseAdapter {
    private String[] data;
    private Context activity;
    private LayoutInflater inflater;
    public int index;
    private int colorgray;
    private int colorwhite;

    public LeftAdapter() {

    }

    /**
     * 构造函数初始化
     *
     * @param data
     * @param activity
     */
    public LeftAdapter(String[] data, Context activity) {
        this.data = data;
        this.activity = activity;
        //实例化inflater--LayoutInflater类的from(activity)方法
        inflater = LayoutInflater.from(activity);
        colorgray = ContextCompat.getColor(activity, R.color.colorNor);
        colorwhite = ContextCompat.getColor(activity, R.color.colorWhite);
    }

    @Override
    public int getCount() {
        return data.length;
    }

    @Override
    public Object getItem(int position) {
        return data[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * 获取ListView被点击的Item
     *
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        /*
        如果convertView为空  加载布局初始化convertView
        -->初始化holder
        -->  初始化 holder的三个属性（控件）
        --> 绑定holder到view
        --> 如果convertView不为空，getTag获取holder对象
        -->  通过getItem方法获得一个实体bean的实例
        -->  分别为holder的三个控件赋值
         */
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_txt, null);
            //初始化布局持有器
            holder = new ViewHolder();
            holder.iv = (ImageView) convertView.findViewById(R.id.iv);
            holder.tv = (TextView) convertView.findViewById(R.id.tv);
            //调用转换视图的setTag(视图持有器) 来初始化convertView
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        //获得标题--》设置视图持有器中的TextView的文本
        String title = data[position];
        holder.tv.setText(title);
        //设置加载item蓝条 背景色
        if (index == position) {
            convertView.setBackgroundColor(colorwhite);
            holder.iv.setVisibility(View.VISIBLE);
        } else {
            convertView.setBackgroundColor(colorgray);
            holder.iv.setVisibility(View.INVISIBLE);
        }
        //返回转换视图
        return convertView;
    }

    //定义布局持有器
    public class ViewHolder {
        private ImageView iv;
        private TextView tv;
    }
}
