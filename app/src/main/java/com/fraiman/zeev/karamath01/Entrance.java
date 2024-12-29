package com.fraiman.zeev.karamath01;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Entrance extends AppCompatActivity implements View.OnClickListener {

    ImageView bKara, bRus, bEng;
    HelperDB hlp;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrance);

        hlp=new HelperDB(this);
        db=hlp.getWritableDatabase();
        db.close();

        bKara=findViewById(R.id.bKara);
        bKara.setOnClickListener(this);
        bRus=findViewById(R.id.bRus);
        bRus.setOnClickListener(this);
        bEng=findViewById(R.id.bEng);
        bEng.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v==bKara)  {
            Intent go=new Intent(Entrance.this, Coefficients.class);
            go.putExtra("lang","kara");
            startActivity(go);
        }
        if (v==bRus)  {
            Intent go=new Intent(Entrance.this, Coefficients.class);
            go.putExtra("lang","rus");
            startActivity(go);
        }
        if (v==bEng)  {
            Intent go=new Intent(Entrance.this, Coefficients.class);
            go.putExtra("lang","eng");
            startActivity(go);
        }
    }
}
