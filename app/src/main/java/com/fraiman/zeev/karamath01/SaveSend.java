package com.fraiman.zeev.karamath01;

import android.Manifest;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class SaveSend extends AppCompatActivity {

    TextView tvExer, tvSes;
    Button bSd, bSv;
    Intent what;
    String stA, stB, stC, stType, stMess, lang;
    String st1="Enter a phone number", st2="Your exercise saved successful",
                st3="You exercise=",st4="My exercise=",st5="Send",st6="No";
    HelperDB hlp;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_send);

        tvExer=findViewById(R.id.tvExer);
        tvSes=findViewById(R.id.tvSeS);
        bSd=findViewById(R.id.bSd);
        bSv=findViewById(R.id.bSv);

        what=getIntent();
        stType=what.getStringExtra("mType");
        stA=what.getStringExtra("a");
        stB=what.getStringExtra("b");
        if (!stType.equals("Linear"))
            stC=what.getStringExtra("c");
        lang=what.getStringExtra("lang");
        if (lang.equals("rus"))  {
            st1="Укажите номер телефона";
            st2="Упражнение записано успешно";
            st3="Ваше упражнение=";
            st4="Мое упражнение=";
            st5="Отправить";
            st6="Нет";
            tvSes.setText("Сохранить и / или отправить упражнение");
            bSv.setText("Сохраните это упражнение");
            bSd.setText("Отправить это упражнение");
        }
        if (lang.equals("kara"))  {
            st1="Telefon nomerin kiritiń";
            st2="Shınıǵıw tabıslı jazıldı";
            st3="Shınıģוwlardו=";
            st4="Shınıģוwlardו=";
            st5="Shınוģıwdו jiberiw";
            st6="Yaq";
            tvSes.setText("Saqlaw hám/yamasa shınıģוwlardו jiberiw");
            bSv.setText("Shınıģוwlardו saqlań");
            bSd.setText("Shınוģוwlardו jiberiw");
        }

    }

    public void saveExer(View view) {
        Calendar calendar=Calendar.getInstance();
        int y=calendar.get(Calendar.YEAR);
        int m=calendar.get(Calendar.MONTH);
        int d=calendar.get(Calendar.DAY_OF_MONTH);
        String eDate=d+"/"+m+"/"+y;

        hlp=new HelperDB(SaveSend.this);
        db=hlp.getWritableDatabase();

        ContentValues cv=new ContentValues();
        cv.put(HelperDB.EXER_DATE, eDate);
        if (stType.equals("Linear"))
            cv.put(HelperDB.EXER_TYPE,"Linear");
        else  {
            cv.put(HelperDB.EXER_TYPE,"Quadratic");
            cv.put(HelperDB.COEF_C,stC);
        }
        cv.put(HelperDB.COEF_A,stA);
        cv.put(HelperDB.COEF_B,stB);

        db.insert(HelperDB.EXERS_TABLE,null,cv);
        db.close();
        Toast.makeText(this, st2, Toast.LENGTH_LONG).show();
    }

    public void sendExerSms(View view) {
        if(checkPermission(Manifest.permission.SEND_SMS)){
            Toast.makeText(this, "Ok with permission", Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(this, "Permission not found", Toast.LENGTH_LONG).show();
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.SEND_SMS}, 1);
        }
        AlertDialog.Builder adb=new AlertDialog.Builder(SaveSend.this);
        adb.setTitle(st1);
        if (stType.equals("Linear"))
            adb.setMessage(st3+stA+"x+"+stB+"=0");
        else
            adb.setMessage(st3+stA+"x^2+"+stB+"x+"+stC+"=0");
        final EditText editText=new EditText(SaveSend.this);
        adb.setView(editText);
        adb.setCancelable(false);
        adb.setPositiveButton(st5, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String stNum = editText.getText().toString();
                if (stType.equals("Linear"))
                    stMess = st4+stA+"x+"+stB+"=0";
                else
                    stMess=st4+stA+"x^2+"+stB+"x+"+stC+"=0";
                if(stNum == null || stNum.length() == 0 ||
                        stMess == null || stMess.length() == 0){
                    return;
                }

                if(checkPermission(Manifest.permission.SEND_SMS)){
                    SmsManager smsManager = SmsManager.getDefault();
                    Intent mySMS=new Intent(Intent.ACTION_SEND);
                    mySMS.putExtra("what",stMess);
                    smsManager.sendTextMessage(stNum, null, stMess, null, null);
                    Toast.makeText(SaveSend.this, "SMS Sent!", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(SaveSend.this, "Permission Denied", Toast.LENGTH_SHORT).show();
                }
            }
        });
        adb.setNegativeButton(st6, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        adb.create().show();
    }

    public boolean checkPermission(String permission){
        int check = ContextCompat.checkSelfPermission(this, permission);
        return (check == PackageManager.PERMISSION_GRANTED);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        if (lang.equals("eng"))
            getMenuInflater().inflate(R.menu.eng_menu, menu);
        if (lang.equals("rus"))
            getMenuInflater().inflate(R.menu.rus_menu, menu);
        if (lang.equals("kara"))
            getMenuInflater().inflate(R.menu.kara_menu, menu);
        MenuItem menuItem=menu.findItem(R.id.credit);
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
            intent.putExtra("lang",lang);
            startActivity(intent);
        }
        if (id == R.id.credit) {
            Intent intent=new Intent(this, Credits.class);
            intent.putExtra("lang",lang);
            startActivity(intent);
        }
        if (id == R.id.buildnoti) {
            Intent intent=new Intent(this, BuildNotification.class);
            intent.putExtra("lang",lang);
            startActivity(intent);
        }
        if (id == R.id.back) {
            finish();
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
        if (id==R.id.exit)  {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                finishAffinity();
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
