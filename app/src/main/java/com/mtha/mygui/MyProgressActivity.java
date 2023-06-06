package com.mtha.mygui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.*;

import java.util.Calendar;

public class MyProgressActivity extends AppCompatActivity {

    //khai bao cac lop view
    ImageButton btnShow;
    ProgressDialog myProgress;
    Handler myHandler ; //cap nhat lai giao dien progressdialog sau moi lan lap
    int myPercent =0;

    //khai bao cac doi tuong lam viec voi autoCompletTextView
    AutoCompleteTextView autoCity;
    //data source: danh sach ten cac thanh pho
    final String[] cities ={"Ha Noi", "Hai Phong", "Ha Nam","Ho Chi Minh",
    "Da Nang", "Nha Trang", "Da lat"};
    //dinh nghia mot adapter de chua datasource va view hien thi item cua datasource
    ArrayAdapter<String> myAdapter;

    //timePickerDialog, DatePickerDialog
    TextView txtTime, txtDate;
    //tao doi tuong calendar va lay ra ngay gio hien tai
    final Calendar calendar = Calendar.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_progress);
        btnShow = findViewById(R.id.btnShow);
        //khoi tao doi tuong handler
        myHandler = new  Handler();
        //khoi tao va thiet lap cac thuoc tinh cho myProgress
        myProgress = new ProgressDialog(MyProgressActivity.this);
        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myProgress.setCancelable(true);
                myProgress.setMessage("Dowloading.....!!!!");
                myProgress.setProgress(0);
                myProgress.setMax(100);
                myProgress.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                myProgress.show();

                //xu ly cap nhat tao mot thread
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        //todo
                        for(int i=0;i<100;i++){
                            try {
                                Thread.sleep(1000);
                                //cap nhat gia tri tren progressDialog
                                myHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        myProgress.setProgress(myPercent++);
                                    }
                                });
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        //dong dialog
                        myProgress.dismiss();
                    }
                }).start();

            }
        });
        //hien thi va xu ly autocompleteTextView
        autoCity= findViewById(R.id.autoCity);
        //khoi tao doi tuong adapter
        myAdapter = new ArrayAdapter(MyProgressActivity.this,
                android.R.layout.simple_dropdown_item_1line,cities);
        autoCity.setAdapter(myAdapter);
        autoCity.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MyProgressActivity.this,
                        "vi tri: "+ position, Toast.LENGTH_LONG).show();
            }
        });
        //xu ly time va date
        txtTime =findViewById(R.id.txtTime);
        txtDate = findViewById(R.id.txtDate);


    }

    public void onSeleteDate(View view) {
        int date, month, year;
        date = calendar.get(Calendar.DAY_OF_MONTH);
        month = calendar.get(Calendar.MONTH);
        year = calendar.get(Calendar.YEAR);

        DatePickerDialog dpd = new DatePickerDialog(MyProgressActivity.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int y, int m, int d) {
                        txtDate.setText(d+"/"+ (m+1)+"/"+y);
                    }
                },year,month,date);

        dpd.show();
    }

    public void onSelectTime(View view) {
        int mHour, mMinute;
        mHour = calendar.get(Calendar.HOUR);
        mMinute = calendar.get(Calendar.MINUTE);
        //Khai bao va khoi tao TimePickerDialog
        TimePickerDialog tdp;
        tdp = new TimePickerDialog(MyProgressActivity.this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        //thiet lap thoi gian len txtTime
                        txtTime.setText(hourOfDay+":"+ minute);
                    }
                },mHour,mMinute, false);
        //hien dialog
        tdp.show();
    }
}