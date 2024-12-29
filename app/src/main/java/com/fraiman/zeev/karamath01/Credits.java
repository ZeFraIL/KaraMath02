package com.fraiman.zeev.karamath01;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Credits extends AppCompatActivity {

    String lang, stZeev="", stZamira="";
    Intent take;
    TextView tvZeev, tvZamira;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credits);

        tvZeev=findViewById(R.id.tvZeev);
        tvZamira=findViewById(R.id.tvZamira);
        take=getIntent();
        lang=take.getStringExtra("lang");
        if (lang.equals("rus"))  {
            stZeev="Зэев Фрайман\nпреподаватель курса 'Компьютерные науки'\nи курса 'Разработка приложений под ОС Андроид'\nИзраиль, Беэр-Шева";
            tvZeev.setText(stZeev);
            stZamira="Жанибекова Замира Казакбаевна\nметодист по предмету математики Республиканского учебно-методического  центра  при Министерстве Народного образования Республики Каракалпакстан";
            tvZamira.setText(stZamira);
        }
        if (lang.equals("eng"))  {
            stZeev="Ze'ev Fraiman\neacher of the course 'Computer Science'\nand the course 'Application Development for Android OS'\nIsrael, Beer Sheva";
            tvZeev.setText(stZeev);
            stZamira="Janibekova Zamira Kazakbaevna\nmethodologist on the subject of mathematics of the Republican Educational and Methodological Center under the Ministry of Public Education of the Republic of Karakalpakstan";
            tvZamira.setText(stZamira);
        }
        if (lang.equals("kara"))  {
            stZeev="Зэев Фрайман\nпреподаватель курса 'Компьютерные науки'\nи курса 'Разработка приложений под ОС Андроид'\nИзраиль, Беэр-Шева";
            tvZeev.setText(stZeev);
            stZamira="Жанибекова Замира Казакбаевна\nҚарақалпақстан Республикасы Халық билимлендириў министрлиги жанындағы Республика оқыў методика орайының математика пәни методисти(ққ.тил)";
            tvZamira.setText(stZamira);
        }
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
