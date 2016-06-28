package com.zhidisoft.slefnote.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import com.zhidisoft.slefnote.R;
import com.zhidisoft.slefnote.activity.BillActivity;
import com.zhidisoft.slefnote.adapter.BillRecAdapter;
import com.zhidisoft.slefnote.adapter.NoteAdapter;
import com.zhidisoft.slefnote.bean.Bill;
import com.zhidisoft.slefnote.bean.BillRec;
import com.zhidisoft.slefnote.bean.BillStore;
import com.zhidisoft.slefnote.db.DBAction;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class BillFragment extends Fragment implements View.OnClickListener{
    private ListView lv_bill_rec;
    private List<BillStore> listBillStore;
    private ImageView iv_add;
    private DBAction dba;
    private BillRecAdapter billRecAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_bill, container, false);
        //获取控件--》设置监听
        iv_add= (ImageView) v.findViewById(R.id.iv_add);
        iv_add.setOnClickListener(this);
        //实例化支出收入ListView控件
        lv_bill_rec= (ListView) v.findViewById(R.id.lv_bill_rec);

        //先设置ListView加载billRecAdapter
        dba=new DBAction(getActivity());//获得数据操作类
        listBillStore=new ArrayList<>();//初始化集合
        billRecAdapter=new BillRecAdapter(listBillStore,getActivity());
        lv_bill_rec.setAdapter(billRecAdapter);
        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_add:
                //初始化intent
                Intent intent=new Intent(getActivity(), BillActivity.class);
                //跳转
                startActivity(intent);
                break;
        }
    }

    /**
     * 重写onStart方法，每次都显示note表中所有记录
     */
    @Override
    public void onStart() {
        super.onStart();
        queryAllBill();
        lv_bill_rec.setAdapter(billRecAdapter);
    }

    /**
     * 查询所有
     */
    public void queryAllBill(){
        listBillStore=dba.queryBillStore();//获取集合数据
        billRecAdapter.setList(listBillStore);//适配器调用setList方法设置集合
        billRecAdapter.notifyDataSetChanged();//通知更新
    }

}
