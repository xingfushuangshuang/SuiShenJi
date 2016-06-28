package com.zhidisoft.fragcomm.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.zhidisoft.fragcomm.R;
import com.zhidisoft.fragcomm.adapter.RightAdapter;
import com.zhidisoft.fragcomm.bean.Food;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class RightFrag extends Fragment {

    private ListView lv_right;
    private TextView tv_title;
    private List<Food> foodlist;
    private String strImg;

    public RightFrag() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_right, container, false);
        lv_right = (ListView) v.findViewById(R.id.lv_right);
        return v;
    }

    /**
     * 构建RightFrag-->listview的测试源
     *
     * @param title
     */
    public void initRightData(String title) {
        List<Food> list = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            Food food = new Food();
            food.setImgId(R.drawable.hanbao0 + i);
            food.setTitle(title);
            food.setContent("好吃的食物");
            list.add(food);
        }
        RightAdapter rightAdapter = new RightAdapter(list, this.getActivity());
        lv_right.setAdapter(rightAdapter);
    }

}
