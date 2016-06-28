package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

public class WeightActivity extends AppCompatActivity {
    private DatePicker dp;
    private TimePicker tp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight);
        Calendar calender = Calendar.getInstance();
        int year = calender.get(Calendar.YEAR);
        int montu = calender.get(Calendar.MONTH);
        int day = calender.get(Calendar.DAY_OF_MONTH);
        dp = (DatePicker) findViewById(R.id.dp);
        dp.init(year, montu, day, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                String str = year + "年" + (monthOfYear + 1) + "月" + dayOfMonth + "日";
                Toast.makeText(WeightActivity.this, str, Toast.LENGTH_LONG).show();
            }
        });
        tp = (TimePicker) findViewById(R.id.tp);
        tp.setIs24HourView(true);
//        tp.setHour(day);
        tp.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                String time = hourOfDay + "时" + minute + "分";
                Toast.makeText(WeightActivity.this, time, Toast.LENGTH_LONG).show();
            }
        });
    }
}
