package com.zhidisoft.slefnote.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhidisoft.slefnote.R;
import com.zhidisoft.slefnote.activity.TransferAct;

/**
 * A simple {@link Fragment} subclass.
 */
public class AccountFragment extends Fragment {
    private TextView iv_transfer;

    public AccountFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_account, container, false);
        iv_transfer= (TextView) v.findViewById(R.id.iv_transfer);
        iv_transfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), TransferAct.class);
                startActivity(intent);
            }
        });
        return v;
    }

}
