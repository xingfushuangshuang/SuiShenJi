package com.example.lanuchmodetest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class StandardActivity extends Activity {
	private Button btn_self;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main2);
		TextView tv_txt = (TextView) findViewById(R.id.tv_txt);
		tv_txt.setText(this.toString());//显示activity的信息
		btn_self = (Button) findViewById(R.id.startSelf);
		btn_self.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(StandardActivity.this, StandardActivity.class);
				startActivity(intent);
			}
		});
	}
}
