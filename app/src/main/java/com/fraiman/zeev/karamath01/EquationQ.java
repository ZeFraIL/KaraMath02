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
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.DataPointInterface;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.OnDataPointTapListener;
import com.jjoe64.graphview.series.Series;

public class EquationQ extends AppCompatActivity {

    TextView tvQE, tvSolQ;
    Intent takeit;
    String stA, stB, stC, lang,
            st1="An endless amount of solutions",st2="Not found solution",
            st3="My exercises",st4="It's not a quadratic equation";
    int a,b, c;
    double d, x, x1, x2, xmin, dx, xL, xR,y;
    GraphView graph;
    Button bSeSe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equation_q);

        tvSolQ=findViewById(R.id.tvSolQ);
        tvQE=findViewById(R.id.tvQE);
        bSeSe=findViewById(R.id.bSeSe);
        takeit=getIntent();
        stA=takeit.getStringExtra("a");
        stB=takeit.getStringExtra("b");
        stC=takeit.getStringExtra("c");
        lang=takeit.getStringExtra("lang");
        if (lang.equals("rus"))  {
            tvQE.setText("Квадратное уравнение");
            bSeSe.setText("Сохранить / отправить это упражнение");
            st1="Бесконечное множество решений";
            st2="Нет решения";
            st3="Мои упражнения";
            st4="Это не квадратное уравнение";
        }
        if (lang.equals("kara"))  {
            tvQE.setText("Kvadrat teńleme");
            bSeSe.setText("Saqlaw/shוnוģוwdו jiberiw");
            st1="Sheksiz kóp sheshimler";
            st2="Sheshimi joq";
            st3="Meniń shınıǵıwlarım";
            st4="Bul kvadrat teńleme emes";
        }
        String s=stA+"x<sup>2</sup>";
        if (stB.charAt(0)=='-')
            s+=stB;
        else
            s+="+"+stB+"x";
        if (stC.charAt(0)=='-')
            s+=stC;
        else
            s+="+"+stC;
        tvQE.setText(Html.fromHtml(s+" = 0\n"));
        a=Integer.parseInt(stA);
        b=Integer.parseInt(stB);
        c=Integer.parseInt(stC);

        if (a==0&&b==0&&c==0) {
            tvSolQ.setText(st1);
            return;
        }
        if (a==0&&b!=0) {
            tvSolQ.setText(st4);
            return;
        }
        if (a!=0) {
            d=b*b-4*a*c;
            if (d<0)  {
                tvSolQ.setText(st2);
            }
            if (d==0)  {
                tvSolQ.setText("x="+(-b/(2.0*a)));
            }
            if (d>0)  {
                x1=(-b-Math.sqrt(d))/(2.0*a);
                x2=(-b+Math.sqrt(d))/(2.0*a);
                tvSolQ.setText("x1="+x1+"\nx2="+x2);
            }
            int numPoints=100;
            graph=findViewById(R.id.graphQ);
            LineGraphSeries<DataPoint> series=new LineGraphSeries<>();
            if (d<=0)  {
                xmin=-1.0*b/(2*a);
                xL=xmin-5.0;
                xR=xmin+5.0;
                dx=(xR-xL)/(1.0*numPoints);
            }

            if (d>0&&a>0)  {
                xL=x1-5;
                xR=x2+5;
                dx=(xR-xL)/(1.0*numPoints);
            }
            if (d>0&&a<0)  {
                xL=x2-5;
                xR=x1+5;
                dx=(xR-xL)/(1.0*numPoints);
            }
            x=xL;
            for (int i = 1; i <=(numPoints+1) ; i++) {
                y=a*x*x+b*x+c;
                series.appendData(new DataPoint(x,y), false, numPoints+1);
                x+=dx;
            }
            series.setAnimated(true);
            series.setOnDataPointTapListener(new OnDataPointTapListener() {
                @Override
                public void onTap(Series series, DataPointInterface dataPoint) {
                    double px=((int)(dataPoint.getX()*10))/10.0;
                    double py=((int)(dataPoint.getY()*10))/10.0;
                    String stP="("+px+", "+py+")";
                    Toast.makeText(EquationQ.this, stP, Toast.LENGTH_LONG).show();
                }
            });
            graph.addSeries(series);
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
            Intent intent = new Intent(this, Statistics.class);
            intent.putExtra("lang", lang);
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
        if (id==R.id.exit)  {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                finishAffinity();
            }
        }
        return super.onOptionsItemSelected(item);
    }

    public void saveExer(View view) {
        Intent go=new Intent(this, SaveSend.class);
        go.putExtra("mType","Quadratic");
        go.putExtra("a",stA);
        go.putExtra("b",stB);
        go.putExtra("c",stC);
        go.putExtra("lang", lang);
        startActivity(go);
    }
}
