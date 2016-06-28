package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    private EditText et_content;
    private Button btn_setname;
    private TextView tv_name;
    //定义一个请求码
    private int req_name = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_content = (EditText) findViewById(R.id.et_content);
        btn_setname = (Button) findViewById(R.id.btn_setname);
        tv_name = (TextView) findViewById(R.id.tv_name);
        findViewById(R.id.btn_start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取用户输入的内容
                String content = et_content.getText().toString();
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                //将数据放入intent keyvalue的形式
                intent.putExtra("content", content);
                startActivity(intent);
            }
        });
        //tag,标签，内容
        Log.i("info", "A-- onCreate");

        btn_setname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                startActivityForResult(intent, req_name);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("info", "A-- onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("info", "A--onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("info", "A--onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("info", "A--onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("info", "A--onDestory");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("info", "A--onRestart");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == req_name && resultCode == RESULT_OK) {//设置的是姓名  设置成功
            String name = data.getStringExtra("name");
            tv_name.setText(name);
        }
    }
}
