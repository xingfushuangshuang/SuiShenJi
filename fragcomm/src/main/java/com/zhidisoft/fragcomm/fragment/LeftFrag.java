package com.zhidisoft.fragcomm.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.zhidisoft.fragcomm.R;
import com.zhidisoft.fragcomm.adapter.LeftAdapter;


/**
 * A simple {@link Fragment} subclass.
 */
public class LeftFrag extends Fragment {
    private String[] data = {"天天超值", "限时特惠", "特别推荐", "杭州吃喝", "西湖啦撒", "灵隐约会"};
    private ListView lv;
    private ClickItem clickItem;
    private LeftAdapter leftAdapter;

    public void setClickItem(ClickItem clickItem) {
        this.clickItem = clickItem;
    }

    public LeftFrag() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_left, container, false);
        //实例化ListView
        lv = (ListView) v.findViewById(R.id.lv_left);
        //加载适配器
        leftAdapter = new LeftAdapter(data, this.getActivity());
        lv.setAdapter(leftAdapter);
        //设置ListView的条目点击事件
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (clickItem != null) {
                    //设置项目标题
                    clickItem.initTitle(data[position]);
                    //将选中的Item的position赋值给leftAdapter的公共成员变量-->LeftAdapter中设置加载item_txt左边的蓝条
                    leftAdapter.index = position;
                    //通知数据设置改变
                    leftAdapter.notifyDataSetChanged();
                }
            }
        });
        return v;
    }

    /**
     * 回调接口
     */
    public interface ClickItem {
        void initTitle(String title);
    }

}
