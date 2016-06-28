package com.zhidisoft.slefnote.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zhidisoft.slefnote.R;
import com.zhidisoft.slefnote.bean.Note;

import java.util.List;

/**
 * Created by Administrator on 2016/6/20.
 */
public class NoteAdapter extends BaseAdapter {
    private List<Note> list;
    private Context context;
    private LayoutInflater inflater;

    /**
     * 构造函数
     *
     * @param list
     * @param context
     */
    public NoteAdapter(List<Note> list, Context context) {
        this.list = list;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    /**
     * 设置变量list方法
     */
    public void setList(List<Note> list) {
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
        ViewHolder holder=null;
        Note note=null;
        //如果convertView==null 即为空
        if(convertView==null){
            //加载Adapter的布局初始化convertView
            convertView=inflater.inflate(R.layout.item_note,null);
            //初始化holder
            holder=new ViewHolder();
            //设置holder的相关属性
            holder.tv_title= (TextView) convertView.findViewById(R.id.tv_title);
            holder.tv_content= (TextView) convertView.findViewById(R.id.tv_content);
            //将holder绑定给convertView
            convertView.setTag(holder);
        }else{
            //直接获取
            holder= (ViewHolder) convertView.getTag();
        }
        //获取ListView中对应条目的note对象
        note= (Note) getItem(position);
        //用note对象的属性依次初始化holder的对应属性
        holder.tv_title.setText(note.getTitle());
        holder.tv_content.setText(note.getContent());
        //返回convertView
        return convertView;
    }

    /**
     * 使用ViewHolder控制getView中的convertView
     */
    public class ViewHolder{
        public TextView tv_title;
        public TextView tv_content;
    }
}
