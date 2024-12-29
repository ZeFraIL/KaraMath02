package com.fraiman.zeev.karamath01;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class OnlyInfo extends AppCompatActivity {

    ListView lvSite, lvVideo;
    ArrayAdapter adapSite, adapVideo;
    String[] sitenames={"Quadratic equal (wiki)","Linear equations"};
    String[] sites={"https://he.wikibooks.org/wiki/%D7%9E%D7%AA%D7%9E%D7%98%D7%99%D7%A7%D7%94_%D7%AA%D7%99%D7%9B%D7%95%D7%A0%D7%99%D7%AA/%D7%90%D7%9C%D7%92%D7%91%D7%A8%D7%94_%D7%AA%D7%99%D7%9B%D7%95%D7%A0%D7%99%D7%AA/%D7%9E%D7%A9%D7%95%D7%95%D7%90%D7%95%D7%AA/%D7%9E%D7%A9%D7%95%D7%95%D7%90%D7%95%D7%AA_%D7%A8%D7%99%D7%91%D7%95%D7%A2%D7%99%D7%95%D7%AA",
            "https://www.mathsisfun.com/algebra/linear-equations.html"};
    String[] videonames={"Geometriya","Video #2"};
    String[] ytvideo={"xz9GMXYMINw","IfQs833CJlE"};
    WebView wv;
    Intent take;
    String lang="";
    TextView tvInfo1, tvInfo2, tvInfo3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_only_info);

        take=getIntent();
        lang=take.getStringExtra("lang");
        tvInfo1=findViewById(R.id.tvInfo1);
        tvInfo2=findViewById(R.id.tvInfo2);
        tvInfo3=findViewById(R.id.tvInfo3);
        if (lang.equals("kara"))   {
            tvInfo1.setText("Sayt yamasa videonı tańlań");
            tvInfo2.setText("Sayt");
            tvInfo3.setText("Video");

        }
        if (lang.equals("rus"))   {
            tvInfo1.setText("Выберите сайт или видео");
            tvInfo2.setText("Сайт");
            tvInfo3.setText("Видео");

        }

        wv=findViewById(R.id.wv);
        WebSettings webSettings = wv.getSettings();
        webSettings.setJavaScriptEnabled(true);

        lvSite=findViewById(R.id.lvSite);
        adapSite=new ArrayAdapter(this,
                android.R.layout.simple_list_item_1,sitenames);
        lvSite.setAdapter(adapSite);
        lvSite.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String goURL=sites[position];
                wv.setWebViewClient(new myWebViewClient());
                wv.loadUrl(goURL);

            }
        });

        lvVideo=findViewById(R.id.lvVideo);
        adapVideo=new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, videonames);
        lvVideo.setAdapter(adapVideo);
        lvVideo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String t1=ytvideo[position];
                String frameVideo = "<html><body>Video From YouTube<br>" +
                        "<iframe width=\"427\" height=\"240\" " +
                        "src=\"https://www.youtube.com/embed/"+t1+"\" " +
                        "frameborder=\"0\" allow=\"autoplay; encrypted-media\" allowfullscreen>" +
                        "</iframe></body></html>";
                wv.setWebViewClient(new myWebViewClient());
                wv.loadData(frameVideo, "text/html", "utf-8");
            }
        });
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

    private class myWebViewClient extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url)
        {
            view.loadUrl(url);
            return true;
        }

        @TargetApi(Build.VERSION_CODES.LOLLIPOP)
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            view.loadUrl(request.getUrl().toString());
            return true;
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            Toast.makeText(OnlyInfo.this, "Page started", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            Toast.makeText(OnlyInfo.this, "Page finished", Toast.LENGTH_SHORT).show();
        }
    }

}
