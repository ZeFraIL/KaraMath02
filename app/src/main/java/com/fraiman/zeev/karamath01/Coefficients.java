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
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class Coefficients extends AppCompatActivity {

    RadioGroup RG;
    RadioButton rbL, rbQ;
    EditText etA, etB, etC;
    Button bStart;
    String stA, stB, stC,stCode, lang;
    String st1="",st2="",st3="",st4="", st5="";
    int a,b,c;
    Intent takeit, go;
    TextView tvCoeff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coefficients);

        takeit=getIntent();
        lang=takeit.getStringExtra("lang");

        tvCoeff=findViewById(R.id.tvCoeff);
        RG=findViewById(R.id.RG);
        rbL=findViewById(R.id.rbL);
        rbQ=findViewById(R.id.rbQ);
        etA=findViewById(R.id.etA);
        etB=findViewById(R.id.etB);
        etC=findViewById(R.id.etC);
        etA.setVisibility(View.INVISIBLE);
        etB.setVisibility(View.INVISIBLE);
        etC.setVisibility(View.INVISIBLE);
        bStart=findViewById(R.id.bStart);
        if (lang.equals("kara")) {
            tvCoeff.setText("Tańlań: \nsızıqlı yamasa kvadratlו funkciya?");
            rbL.setText("Sızıqlı");
            rbQ.setText("Kvadratlı");
            etA.setHint("‘a’ koeficientin kiritiń");
            etB.setHint("‘b’ koeficientin kiritiń");
            etC.setHint("‘c’ koeficientin kiritiń");
            bStart.setText("Start");
        }
        if (lang.equals("rus")) {
            tvCoeff.setText("Сделайте выбор: \nлинейная или квадратичная функция?");
            rbL.setText("Линейная");
            rbQ.setText("Квадратичная");
            etA.setHint("Введите коэффициент 'а'");
            etB.setHint("Введите коэффициент 'b'");
            etC.setHint("Введите коэффициент 'c'");
            bStart.setText("Старт");
        }
        bStart.setVisibility(View.INVISIBLE);
        bStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stA=etA.getText().toString();
                stB=etB.getText().toString();
                stC=etC.getText().toString();
                if (stA.equals(""))  {
                    Toast.makeText(Coefficients.this, "Empty value!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (stA.equals(""))  {
                    Toast.makeText(Coefficients.this, "Empty value!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (stB.equals(""))  {
                    Toast.makeText(Coefficients.this, "Empty value!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (rbQ.isChecked()&&stC.equals(""))  {
                    Toast.makeText(Coefficients.this, "Empty A coefficient!", Toast.LENGTH_SHORT).show();
                    return;
                }
                AlertDialog.Builder adb=new AlertDialog.Builder(Coefficients.this);
                if (lang.equals("eng")) {
                    st1="Equation or function?";
                    st2="Press by your choose";
                    st3="Equation";
                    st4="Function";
                    st5="My exercises";
                }
                if (lang.equals("rus")) {
                    st1="Уравнение или функция?";
                    st2="Нажмите по вашему выбору";
                    st3="Уравнение";
                    st4="Функция";
                    st5="Мои упражнения";
                }
                if (lang.equals("kara")) {
                    st1="Teńleme yamasa funkciya?";
                    st2="Tańlap basıń";
                    st3="Teńleme";
                    st4="Funkciya";
                    st5="Meniń shınıǵıwlarım";
                }
                adb.setTitle(st1);
                adb.setMessage(st2);
                adb.setPositiveButton(st3, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (rbL.isChecked()) {
                            go = new Intent(Coefficients.this, EquationL.class);
                            go.putExtra("a", stA);
                            go.putExtra("b", stB);
                        }
                        if (rbQ.isChecked()) {
                            go = new Intent(Coefficients.this, EquationQ.class);
                            go.putExtra("a", stA);
                            go.putExtra("b", stB);
                            go.putExtra("c",stC);
                        }
                        go.putExtra("code", stCode);
                        go.putExtra("lang",lang);
                        startActivity(go);
                    }
                });
                adb.setNegativeButton(st4, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (rbL.isChecked()) {
                            go = new Intent(Coefficients.this, FunctionL.class);
                            go.putExtra("a", stA);
                            go.putExtra("b", stB);
                        }
                        if (rbQ.isChecked()) {
                            go = new Intent(Coefficients.this, FunctionQ.class);
                            go.putExtra("a", stA);
                            go.putExtra("b", stB);
                            go.putExtra("c",stC);
                        }
                        go.putExtra("code", stCode);
                        go.putExtra("lang",lang);
                        startActivity(go);
                    }
                });
                adb.create().show();
            }
        });
    }

    public void LorQ(View view) {
        if (view==rbL)  {
            etA.setVisibility(View.VISIBLE);
            etB.setVisibility(View.VISIBLE);
            etC.setVisibility(View.INVISIBLE);
            bStart.setVisibility(View.VISIBLE);
        }
        if (view==rbQ)  {
            etA.setVisibility(View.VISIBLE);
            etB.setVisibility(View.VISIBLE);
            etC.setVisibility(View.VISIBLE);
            bStart.setVisibility(View.VISIBLE);
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
        menu.add(0,1,0,st5);
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
        if (id==1) {
            Intent intent=new Intent(this, Statistics.class);
            //intent.putExtra("code",stCode);
            intent.putExtra("lang",lang);
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
