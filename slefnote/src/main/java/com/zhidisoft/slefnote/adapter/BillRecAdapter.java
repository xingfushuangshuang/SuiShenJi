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
import com.zhidisoft.slefnote.bean.BillRec;
import com.zhidisoft.slefnote.bean.BillStore;

import java.util.List;

/**
 * Created by Administrator on 2016/6/22.
 */
public class BillRecAdapter extends BaseAdapter {
    private List<BillStore> listBillStore;
    private Context context;
    private LayoutInflater inflater;
    private BillStore billStore;

    public void setList(List<BillStore> listBillStore) {
        this.listBillStore = listBillStore;
    }

    public BillRecAdapter(List<BillStore> listBillStore ,Context context) {
        this.listBillStore = listBillStore;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return listBillStore.size();
    }

    @Override
    public Object getItem(int position) {
        return listBillStore.get(position);
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
            convertView = inflater.inflate(R.layout.lv_pay_item, null);
            holder = new ViewHolder();
            holder.iv = (ImageView) convertView.findViewById(R.id.iv_img);
            holder.tv_left = (TextView) convertView.findViewById(R.id.tv_left);
            holder.tv_right = (TextView) convertView.findViewById(R.id.tv_right);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        //获取GridView中对应item的Bill对象
        billStore = (BillStore) getItem(position);
        //如果是支出-->设置holder的属性 右边显示
        if (billStore.getUseCat()==0){
            holder.iv.setImageResource(billStore.getImgId());
            holder.tv_right.setText(billStore.getUseWay()+" "+billStore.getMoneyNum());
            holder.tv_left.setText("");
        }
        //如果是收入-->设置holder的属性 左边显示
        if (billStore.getUseCat()==1){
            holder.iv.setImageResource(billStore.getImgId());
            holder.tv_left.setText(billStore.getUseWay()+" "+billStore.getMoneyNum());
            holder.tv_right.setText("");
        }
        return convertView;
    }

    public class ViewHolder {
        public ImageView iv;
        public TextView tv_right, tv_left;
    }
}
