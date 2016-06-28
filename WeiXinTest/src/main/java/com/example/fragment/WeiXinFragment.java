package com.example.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myapplication.Main2Activity;
import com.example.myapplication.R;

import org.w3c.dom.Text;

/**
 * A simple {@link Fragment} subclass.
 */
public class WeiXinFragment extends Fragment {
    private TextView tv;

    public WeiXinFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_wei_xin, container, false);
        // Inflate the layout for this fragment
        tv= (TextView) v.findViewById(R.id.tv);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), Main2Activity.class);
                WeiXinFragment.this.startActivityForResult(intent,0);
            }
        });
        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==getActivity().RESULT_OK){
            String name=data.getStringExtra("name");
            tv.setText(name);
        }

    }
}
