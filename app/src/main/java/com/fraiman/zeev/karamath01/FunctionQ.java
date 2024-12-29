package com.fraiman.zeev.karamath01;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.DataPointInterface;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.OnDataPointTapListener;
import com.jjoe64.graphview.series.Series;

import java.util.ArrayList;

public class FunctionQ extends AppCompatActivity {

    Intent take;
    String stCode, stA, stB, stC,stXL, stXR, stN,
            lang, st3="My exercises";
    int a,b,c,xL,xR, n;
    TextView tvQF;
    EditText etQFxL, etQFxR, etQFN;
    ListView lvQF;
    ArrayList<String> alXY;
    ArrayAdapter<String> adap;
    double x,y,dx;
    Button bViewGraph;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_function_q);

        take=getIntent();
        stCode=take.getStringExtra("code");
        stA=take.getStringExtra("a");
        stB=take.getStringExtra("b");
        stC=take.getStringExtra("c");
        lang=take.getStringExtra("lang");
        a=Integer.parseInt(stA);
        b=Integer.parseInt(stB);
        c=Integer.parseInt(stC);

        tvQF=findViewById(R.id.tvQFu);
        String s=stA+"x<sup>2</sup>";
        if (stB.charAt(0)=='-')
            s+=stB+"x";
        else
            if (b>0)
                s+="+"+stB+"x";
            else
                s+="";
        if (stC.charAt(0)=='-')
            s+=stC;
        else
            if (c>0)
                s+="+"+stC;
            else
                s+="";
        tvQF.setText(Html.fromHtml("y="+s));

        bViewGraph=findViewById(R.id.bViewGraphQ);
        etQFxL=findViewById(R.id.etQFxL);
        etQFxR=findViewById(R.id.etQFxR);
        etQFN=findViewById(R.id.etQFN);
        lvQF=findViewById(R.id.lvQF);
        if (lang.equals("rus"))  {
            etQFN.setHint("Количество точек=");
            st3="Мои упражнения";
        }
        if (lang.equals("kara"))  {
            etQFN.setHint("Noqatlar  sanı=");
            st3="Meniń shınıǵıwlarım";
        }
    }

    public void fromto(View view) {
        stXL=etQFxL.getText().toString();
        if (stXL.equals(""))  {
            Toast.makeText(this,
                    "Empty!", Toast.LENGTH_SHORT).show();
            return;
        }
        stXR=etQFxR.getText().toString();
        if (stXR.equals(""))  {
            Toast.makeText(this,
                    "Empty!", Toast.LENGTH_SHORT).show();
            return;
        }
        stN=etQFN.getText().toString();
        if (stN.equals(""))  {
            Toast.makeText(this,
                    "Empty!", Toast.LENGTH_SHORT).show();
            return;
        }
        xL=Integer.parseInt(stXL);
        xR=Integer.parseInt(stXR);
        n=Integer.parseInt(stN);
        if (xL>=xR||n<=0)  {
            Toast.makeText(this,
                    "Wrong data!", Toast.LENGTH_SHORT).show();
            return;
        }
        alXY=new ArrayList<>();
        dx=(1.0*xR-1.0*xL)/n;
        x=1.0*xL-dx;
        for (int i = 0; i <=n; i++) {
            x+=dx;
            y=1.0*a*x*x+1.0*b*x+1.0*c;
            alXY.add("x="+((int)(x*10))/10.0+"\ny="+((int)(y*10))/10.0);
        }
        adap=new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                alXY);
        lvQF.setAdapter(adap);
        bViewGraph.setVisibility(View.VISIBLE);
        if (lang.equals("rus"))
            bViewGraph.setText("Показать график\nфункции");
        if (lang.equals("kara"))
            bViewGraph.setText("Teńlemeniń sızılmasın\nkòrsetiw");
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
            Intent
                    intent=new Intent(this, Guide.class);
            intent.putExtra("lang",lang);intent.putExtra("lang",lang);
            startActivity(intent);
        }
        if (id == R.id.credit) {
            Intent intent=new Intent(this, Credits.class);
            startActivity(intent);
        }
        if (id == R.id.buildnoti) {
            Intent intent=new Intent(this, BuildNotification.class);
            intent.putExtra("lang",lang);
            startActivity(intent);
        }
        if (id==1) {
            if (stCode.equals("free")) {
                AlertDialog.Builder adb=new AlertDialog.Builder(FunctionQ.this);
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
        if (id==R.id.restart)  {
            Intent go=new Intent(this, Entrance.class);
            startActivity(go);
        }
        if (id == R.id.back) {
            finish();
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

    public void viewGraph(View view) {
        AlertDialog.Builder adb=new AlertDialog.Builder(FunctionQ.this);
        adb.setCancelable(false);
        com.jjoe64.graphview.GraphView graphView=new com.jjoe64.graphview.GraphView(FunctionQ.this);
        dx=(1.0*xR-1.0*xL)/(n-1);
        x=1.0*xL-dx;
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>();
        for (int i = 0; i <=n; i++) {
            y=1.0*a*x*x+1.0*b*x+1.0*c;
            series.appendData(new DataPoint(x, y), false, n);
            x+=dx;
        }
        graphView.addSeries(series);
        series.setAnimated(true);
        series.setOnDataPointTapListener(new OnDataPointTapListener() {
            @Override
            public void onTap(Series series, DataPointInterface dataPoint) {
                double px=((int)(dataPoint.getX()*10))/10.0;
                double py=((int)(dataPoint.getY()*10))/10.0;
                String stP="("+px+", "+py+")";
                Toast.makeText(FunctionQ.this, stP, Toast.LENGTH_LONG).show();
            }
        });
        adb.setView(graphView);
        adb.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        adb.create().show();
    }
}
