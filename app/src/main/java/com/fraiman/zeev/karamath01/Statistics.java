package com.fraiman.zeev.karamath01;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class Statistics extends AppCompatActivity {

    Intent what;
    String stCode, stA, stB, stC, stType, stDate, lang;
    HelperDB hlp;
    SQLiteDatabase db;
    ArrayList<MyExercise>  alExers;
    ListView lvMyExers;
    ArrayList<String> alInfo;
    ArrayAdapter<String> adap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        lvMyExers=findViewById(R.id.lvMyExers);
        what=getIntent();
        stCode=what.getStringExtra("code");
        lang=what.getStringExtra("lang");
        goReadFromDB(stCode);
    }

    private void goReadFromDB(String stCode) {
        alExers=new ArrayList<>();
        hlp=new HelperDB(this);
        db=hlp.getReadableDatabase();
        Cursor c=db.query(HelperDB.EXERS_TABLE,
                null, null,
                null, null,
                null, null);
        c.moveToFirst();
        while (!c.isAfterLast()) {
            stDate=c.getString(c.getColumnIndex(HelperDB.EXER_DATE));
            stType=c.getString(c.getColumnIndex(HelperDB.EXER_TYPE));
            stA=c.getString(c.getColumnIndex(HelperDB.COEF_A));
            stB=c.getString(c.getColumnIndex(HelperDB.COEF_B));
            stC=c.getString(c.getColumnIndex(HelperDB.COEF_C));
            MyExercise myExercise=new MyExercise(stDate, stType, stA, stB, stC);
            alExers.add(myExercise);
            c.moveToNext();
        }
        db.close();
        goView(alExers);
    }

    private void goView(ArrayList<MyExercise> alExers) {
        alInfo=new ArrayList<>();
        for (int i = 0; i < alExers.size(); i++) {
            alInfo.add(alExers.get(i).toString());
        }
        adap=new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, alInfo);
        lvMyExers.setAdapter(adap);
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
