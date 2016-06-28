package com.example.intenttest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private String action = "com.example.intenttest.secactivity";
    private User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        user=new User();
        findViewById(R.id.btn_start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(action);
                intent.addCategory("com.example.sec");

                intent.putExtra("bean",user);

                 User user= (User) intent.getSerializableExtra("bean");
                startActivity(intent);
            }
        });
    }
}
