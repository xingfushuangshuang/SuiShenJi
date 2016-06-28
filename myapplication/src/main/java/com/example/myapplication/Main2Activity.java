package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {
    private TextView tv_content;
    private Button btn_save;
    private EditText et_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        tv_content = (TextView) findViewById(R.id.tv);
        btn_save = (Button) findViewById(R.id.btn_save);
        et_content = (EditText) findViewById(R.id.et_content);
        Log.i("info", "B--onCreate");
        //接受数据
        Intent intnet = getIntent();
        String content = intnet.getStringExtra("content");
        if (!TextUtils.isEmpty(content)) {
            tv_content.setText(content);//如果content为null会报空指针
        }
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取用户输入的内容
                String name = et_content.getText().toString();
                //设置结果
                Intent intent = new Intent();
                intent.putExtra("name", name);
                setResult(RESULT_OK, intent);
                //结束掉
                Main2Activity.this.finish();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("info", "B--onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("info", "B--onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("info", "B--onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("info", "B--onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("info", "B--onDestroy");
    }
}
