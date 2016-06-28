package com.zhidisoft.fragcomm.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhidisoft.fragcomm.R;
import com.zhidisoft.fragcomm.bean.Food;

import java.util.List;

/**
 * Created by Administrator on 2016/6/7.
 */
public class RightAdapter extends BaseAdapter {
    private List<Food> list;//Food类型的集合
    private Context activity;//上下文对象
    private LayoutInflater inflater;//布局加载器

    public void setList(List<Food> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public RightAdapter(List<Food> list, Context activity) {
        this.list = list;
        this.activity = activity;
        inflater = LayoutInflater.from(activity);
    }

    /*
    加载布局文件-->获取实体bean-->实例化ImageView
    -->实例化TextView
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        Food food = null;
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
            convertView = inflater.inflate(R.layout.item_rgiht, null);
            holder = new ViewHolder();

            holder.iv = (ImageView) convertView.findViewById(R.id.iv_food);
            holder.title = (TextView) convertView.findViewById(R.id.tv_title);
            holder.content = (TextView) convertView.findViewById(R.id.tv_desc);


            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        food = (Food) getItem(position);
        holder.iv.setImageResource(food.getImgId());
        holder.title.setText(food.getTitle());
        holder.content.setText(food.getContent());

        return convertView;
    }

    //ViewHolder类
    public class ViewHolder {
        public ImageView iv;
        public TextView title;
        public TextView content;
    }
}
