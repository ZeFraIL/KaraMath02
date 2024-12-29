package com.fraiman.zeev.karamath01;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Guide extends AppCompatActivity {

    TextView tvGuide, tvGuide0;
    Intent take;
    String lang;
    int fileID=R.raw.guide_en;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);


        take=getIntent();
        lang=take.getStringExtra("lang");
        tvGuide0=findViewById(R.id.tvGuide0);
        if (lang.equals("rus"))  {
            tvGuide0.setText("Руководство для пользователей");
            fileID=R.raw.guide_ru;
        }
        if (lang.equals("kara"))  {
            tvGuide0.setText("Paydalanıwshı ushın qollanba");
            fileID=R.raw.guide_kara;
        }
        tvGuide=findViewById(R.id.tvGuide);


        InputStream is=getResources().openRawResource(fileID);
        InputStreamReader isr=new InputStreamReader(is);
        BufferedReader br=new BufferedReader(isr);
        String t, guidetext="";
        try {
            while ((t = br.readLine()) != null) {
                guidetext+=t+"\n";
            }
            br.close();
            tvGuide.setText(guidetext);
        } catch (IOException e) {
            Toast.makeText(this, e.toString(),
                    Toast.LENGTH_LONG).show();
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
        MenuItem menuItem=menu.findItem(R.id.guide);
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
            startActivity(intent);
        }
        if (id == R.id.back) {
            finish();
        }
        if (id==R.id.restart)  {
            Intent go=new Intent(this, Entrance.class);
            startActivity(go);
        }
        if (id==R.id.inform)  {
            Intent go=new Intent(this, OnlyInfo.class);
            go.putExtra("lang",lang);
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
