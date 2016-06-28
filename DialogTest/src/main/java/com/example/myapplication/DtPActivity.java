package com.example.myapplication;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;

import java.util.Calendar;

public class DtPActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btn_date, btn_time;
    private int year, montu, day;
    private Calendar calender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dt_p);
        btn_date = (Button) findViewById(R.id.btn_date);
        btn_date.setOnClickListener(this);
        btn_time = (Button) findViewById(R.id.btn_time);
        btn_time.setOnClickListener(this);
        calender = Calendar.getInstance();
        year = calender.get(Calendar.YEAR);
        montu = calender.get(Calendar.MONTH);
        day = calender.get(Calendar.DAY_OF_MONTH);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_date:
                showDateDialog();
                break;
            case R.id.btn_time:
                showTimeDialog();
                break;
        }
    }

    public void showDateDialog() {
        DatePickerDialog dialog = new DatePickerDialog(DtPActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                String date = year + "年" + (monthOfYear + 1) + "月" + dayOfMonth + "日";
                btn_date.setText(date);
            }
        }, year, montu, day);
        dialog.show();
    }

    public void showTimeDialog() {
        int hour = calender.get(Calendar.HOUR_OF_DAY);
        int min = calender.get(Calendar.MINUTE);
        TimePickerDialog dialog = new TimePickerDialog(DtPActivity.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                String time = hourOfDay + "时" + minute + "分";
                btn_time.setText(time);
            }
        }, hour, min, true);
        dialog.show();
    }
}
