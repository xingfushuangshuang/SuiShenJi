package com.zhidisoft.slefnote.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhidisoft.slefnote.R;
import com.zhidisoft.slefnote.bean.Bill;

import java.util.List;

/**
 * Created by Administrator on 2016/6/21.
 */
public class BillAdapter extends BaseAdapter {
    private List<Bill> list;
    private Context context;
    private LayoutInflater inflater;
    private Bill bill;


    public void setList(List<Bill> list){
        this.list=list;
    }

    public BillAdapter(List<Bill> list, Context context) {
        this.list = list;
        this.context = context;
        inflater = LayoutInflater.from(context);
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
        ViewHolder holder = null;
        Bill bill = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_bill, null);
            holder = new ViewHolder();
            holder.iv = (ImageView) convertView.findViewById(R.id.iv_bill_item);
            holder.tv = (TextView) convertView.findViewById(R.id.tv_bill_item);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        //获取GridView中对应item的Bill对象
        bill = (Bill) getItem(position);
        //设置holder的属性
        holder.iv.setImageResource(bill.getImageId());
        holder.tv.setText(bill.getBillName());

        return convertView;
    }

    public class ViewHolder {
        public ImageView iv;
        public TextView tv;
    }
}
