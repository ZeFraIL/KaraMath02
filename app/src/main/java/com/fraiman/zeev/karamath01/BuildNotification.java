package com.fraiman.zeev.karamath01;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class BuildNotification extends AppCompatActivity {

    Spinner spHour;
    Calendar calendar;
    AlertDialog.Builder adb;
    int myear, mmonth, mdayOfMonth,y,m,d,mhour,mminute,hour,minute;
    Intent take;
    String lang, stData="", stTime="";
    ImageButton ibData, ibTime;
    TextView tvNoti;
    Button bNoti, bAgain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_build_notification);

        ibData=findViewById(R.id.ibData);
        ibTime=findViewById(R.id.ibTime);
        tvNoti=findViewById(R.id.tvNoti);
        bNoti=findViewById(R.id.bNoti);
        bAgain=findViewById(R.id.bAgain);

        tvNoti.setText(stData+"  "+stTime);
        take=getIntent();
        lang=take.getStringExtra("lang");
        if (lang.equals("rus")) {
            bNoti.setText("Создать напоминание\nдля следующего раза");
            bAgain.setText("Вернуться к работе");
        }
        if (lang.equals("kar")) {
            bNoti.setText("Keyingi paydalanıw\nushın bildiriw jaratıw?");
            bAgain.setText("Jumısqa qaytıw");
        }


    }

    public void chooseDay(View view) {
        calendar=Calendar.getInstance();
        y=calendar.get(Calendar.YEAR);
        m=calendar.get(Calendar.MONTH);
        d=calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dpd1=new DatePickerDialog(BuildNotification.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        myear=year;
                        mmonth=month;
                        mdayOfMonth=dayOfMonth;
                        stData=""+mdayOfMonth+"."+mmonth+"."+myear;
                        tvNoti.setText(stData+"  "+stTime);
                    }
                }, y,m,d);
        dpd1.show();
    }

    public void chooseTime(View view) {
        calendar = Calendar.getInstance();
        hour = calendar.get(Calendar.HOUR_OF_DAY);
        minute = calendar.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay,
                                          int minute) {
                        mhour=hourOfDay;
                        mminute=minute;
                        stTime=""+mhour+":"+mminute;
                        tvNoti.setText(stData+"  "+stTime);
                    }
                }, hour, minute, true);
        timePickerDialog.show();
    }

    public void goBuildNotification(View view) {
        if (stTime.equals("") || stData.equals(""))  {
            Toast.makeText(this, "Empty!", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this, "Ok", Toast.LENGTH_SHORT).show();
            calendar.setTimeInMillis(System.currentTimeMillis());
            calendar.set(myear, mmonth, mdayOfMonth, 8, 00);
            Intent goservice = new Intent(BuildNotification.this, MyService.class);
            PendingIntent pIntent = PendingIntent.getService(BuildNotification.this,
                    0, goservice, 0);
            AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pIntent);
        }
    }

    public void backToWork(View view) {
        Intent goagain=new Intent(BuildNotification.this, Coefficients.class);
        goagain.putExtra("lang",lang);
        startActivity(goagain);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        if (lang.equals("eng"))
            getMenuInflater().inflate(R.menu.eng_menu, menu);
        if (lang.equals("rus"))
            getMenuInflater().inflate(R.menu.rus_menu, menu);
        if (lang.equals("kara"))
            getMenuInflater().inflate(R.menu.kara_menu, menu);
        MenuItem menuItem=menu.findItem(R.id.buildnoti);
        menuItem.setVisible(false);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.guide) {
            Intent intent=new Intent(this, Guide.class);
            startActivity(intent);
        }
        if (id == R.id.credit) {
            Intent intent=new Intent(this, Credits.class);
            startActivity(intent);
        }
        if (id == R.id.buildnoti) {
            Intent intent=new Intent(this, BuildNotification.class);
            startActivity(intent);
        }
        if (id==R.id.inform)  {
            Intent go=new Intent(this, OnlyInfo.class);
            go.putExtra("lang",lang);
            startActivity(go);
        }
        if (id==R.id.restart)  {
            Intent go=new Intent(this, Entrance.class);
            startActivity(go);
        }
        if (id == R.id.back) {
            finish();
        }
        if (id==R.id.exit)  {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                finishAffinity();
            }
        }

        return super.onOptionsItemSelected(item);
    }
}
