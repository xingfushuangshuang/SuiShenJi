package com.zhidisoft.slefnote.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.zhidisoft.slefnote.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btn_login,btn_nose;
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    void initView(){
        btn_login= (Button) findViewById(R.id.btn_login);
        btn_nose= (Button) findViewById(R.id.btn_nose);
        btn_login.setOnClickListener(this);
        btn_nose.setOnClickListener(this);
        intent=new Intent(MainActivity.this,NoteActivity.class);
    }

    @Override
    public void onClick(View v) {
        switchBtn(v.getId());
    }

    void switchBtn(int id){
        switch (id){
            case R.id.btn_login:
                startActivity(intent);
                this.finish();
                break;
            case R.id.btn_nose:
                startActivity(intent);
                this.finish();
                break;
        }
    }
}
