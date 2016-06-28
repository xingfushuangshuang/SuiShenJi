package com.zhidisoft.sqldemo2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zhidisoft.sqldemo2.R;
import com.zhidisoft.sqldemo2.bean.User;

import java.util.List;

/**
 * Created by Administrator on 2016/6/18.
 */
public class SqlAdapter extends BaseAdapter {
    private List<User> list;//数据源
    private Context context;//上下文对象
    private LayoutInflater inflater;//布局加载器

    /**
     * 设置list
     * @param list
     */
    public void setList(List<User> list){
        this.list=list;
    }

    /**
     * 构造函数
     * @param list
     * @param context
     */
    public SqlAdapter(List<User> list, Context context) {
        this.list = list;
        this.context = context;
        inflater=LayoutInflater.from(context);
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
        ViewHolder holder=null;
        //为空--》初始化
        if (convertView==null){
            convertView=inflater.inflate(R.layout.item_row,null);//加载布局

            holder=new ViewHolder();
            holder.tv_id= (TextView) convertView.findViewById(R.id.tv_id);
            holder.tv_name= (TextView) convertView.findViewById(R.id.tv_name);
            holder.tv_pwd= (TextView) convertView.findViewById(R.id.tv_pwd);

            convertView.setTag(holder);
        }else{
            holder= (ViewHolder) convertView.getTag();
        }
        User user= (User) getItem(position);
        holder.tv_id.setText(""+user.getId());//id拼接
        holder.tv_name.setText(user.getUsername());
        holder.tv_pwd.setText(user.getPwd());

        return convertView;
    }

    public class ViewHolder{
        private TextView tv_id;
        private TextView tv_name;
        private TextView tv_pwd;
    }
}
