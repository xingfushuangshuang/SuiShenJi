package com.example.lanuchmodetest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener {
	private Button btn_standard, singleTop;
	private Button singletask;
	private TextView tv_id;
	private Button singleInstance;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
	}

	public void initView() {
		btn_standard = (Button) findViewById(R.id.standard);
		btn_standard.setOnClickListener(this);
		singleTop = (Button) findViewById(R.id.singtop);
		singleTop.setOnClickListener(this);
		singletask = (Button) findViewById(R.id.singletask);
		singletask.setOnClickListener(this);
		singleInstance = (Button) findViewById(R.id.singleInstance);
		singleInstance.setOnClickListener(this);
		tv_id = (TextView) findViewById(R.id.tv_id);
		tv_id.setText("" + getTaskId());
	}

	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
			// 每次启动Activity时(startActivity)，都创建Activity实例，并放入任务栈；
			case R.id.standard:
				startActivity(StandardActivity.class);
				break;
			// 如果任务栈栈顶就是该Activity，则不需要创建，其余情况都要创建Activity实例；
			case R.id.singtop:
				startActivity(SingleTop.class);
				break;
			// 如果要激活的那个Activity在任务栈中存在该实例，则不需要创建，只需要把此Activity放入栈顶，并把该Activity以上的Activity实例都清除；
			case R.id.singletask:
				startActivity(SingleTask.class);
				break;
			// 另起一个任务栈，只存放该activity的实例
			case R.id.singleInstance:
				startActivity(SingleInstance.class);
				break;
			default:
				break;
		}
	}

	public void startActivity(Class<?> cls) {
		Intent intent = new Intent(this, cls);
		startActivity(intent);
	}
}
