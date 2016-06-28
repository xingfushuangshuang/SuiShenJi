package com.example.girdtest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Administrator on 2016/6/23.
 */
public class MyAdapter extends BaseAdapter {
    private List<GridBean> list;
    private Context mContext;
    private LayoutInflater inflater;

    public MyAdapter(List<GridBean> list, Context mContext) {
        this.list = list;
        this.mContext = mContext;
        inflater = LayoutInflater.from(mContext);
    }

    public void setList(List<GridBean> list) {
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

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = inflater.inflate(R.layout.item_gv, null);
        ImageView iv = (ImageView) v.findViewById(R.id.iv_gv);
        TextView tv = (TextView) v.findViewById(R.id.tv_gv);
        GridBean bean = list.get(position);
        //设置显示的图片
        iv.setImageResource(bean.getImgid());
        tv.setText(bean.getType());
        return v;
    }
}
