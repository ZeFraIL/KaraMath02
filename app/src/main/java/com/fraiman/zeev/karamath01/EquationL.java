package com.fraiman.zeev.karamath01;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.DataPointInterface;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.OnDataPointTapListener;
import com.jjoe64.graphview.series.Series;

public class EquationL extends AppCompatActivity {

    TextView tvLE, tvSolL;
    Intent takeit;
    String stA, stB, stCode, lang, st1="An endless amount of solutions",st2="Not found solution",st3="My exercises";
    int a,b;
    GraphView graphL;
    private double x, xL, xR, y;
    Button bSendSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equation_l);


        tvSolL = findViewById(R.id.tvSolL);
        tvLE = findViewById(R.id.tvLE);
        bSendSave=findViewById(R.id.bSendSave);
        takeit = getIntent();
        stA = takeit.getStringExtra("a");
        stB = takeit.getStringExtra("b");
        stCode = takeit.getStringExtra("code");
        lang=takeit.getStringExtra("lang");
        if (lang.equals("rus"))  {
            tvLE.setText("Линейное уравнение");
            bSendSave.setText("Сохранить / отправить это упражнение");
            st1="Бесконечное множество решений";
            st2="Нет решения";
            st3="Мои упражнения";
        }
        if (lang.equals("kara"))  {
            tvLE.setText("Sızıqlו teńleme");
            bSendSave.setText("Saqlaw/shוnוģוwdו jiberiw");
            st1="Sheksiz kóp sheshimler";
            st2="Sheshimi joq";
            st3="Meniń shınıǵıwlarım";
        }
        String s=stA+"x";
        if (stB.charAt(0)=='-')
            s+=stB;
        else
            s+="+"+stB;
        tvLE.setText(s + " = 0");
        a = Integer.parseInt(stA);
        b = Integer.parseInt(stB);
        Toast.makeText(this, "" + a + "," + b, Toast.LENGTH_SHORT).show();
        if (a == 0 && b == 0) {
            tvSolL.setText(st1);
            return;
        }
        if (a == 0 && b != 0) {
            tvSolL.setText(st2);
            return;
        }
        tvSolL.setText("x=" + (-b * 1.0 / a));
        graphL = findViewById(R.id.graphL);
        x = (-b * 1.0) / a;
        int numPoints = 10;
        xL = x - 5;
        xR = x + 5;
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>();
        y = a * xL + b;
        series.appendData(new DataPoint(xL, y), false, 2);
        y=a*xR+b;
        series.appendData(new DataPoint(xR, y), false, 2);
        series.setAnimated(true);
        series.setOnDataPointTapListener(new OnDataPointTapListener() {
            @Override
            public void onTap(Series series, DataPointInterface dataPoint) {
                double px=((int)(dataPoint.getX()*10))/10.0;
                double py=((int)(dataPoint.getY()*10))/10.0;
                String stP="("+px+", "+py+")";
                Toast.makeText(EquationL.this, stP, Toast.LENGTH_LONG).show();
            }
        });
        graphL.addSeries(series);
    }

    public void saveExer(View view) {
        Intent go=new Intent(this, SaveSend.class);
        go.putExtra("code", stCode);
        go.putExtra("mType","Linear");
        go.putExtra("a",stA);
        go.putExtra("b",stB);
        go.putExtra("lang",lang);
        startActivity(go);
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
        menu.add(0,1,0,st3);
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
        if (id==R.id.restart)  {
            Intent go=new Intent(this, Entrance.class);
            startActivity(go);
        }
        if (id==1) {
            if (stCode.equals("free")) {
                AlertDialog.Builder adb=new AlertDialog.Builder(EquationL.this);
                String stTitle="Only for registered users";
                if (lang.equals("kara"))
                    stTitle="?????";
                if (lang.equals("rus"))
                    stTitle="Только для зарегистрированных пользователей";
                adb.setTitle(stTitle);
                adb.setCancelable(false);
                adb.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                adb.create().show();
            }
            else {
                Intent intent = new Intent(this, Statistics.class);
                intent.putExtra("code", stCode);
                intent.putExtra("lang", lang);
                startActivity(intent);
            }
        }
        if (id==R.id.inform)  {
            Intent go=new Intent(this, OnlyInfo.class);
            go.putExtra("lang",lang);
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
