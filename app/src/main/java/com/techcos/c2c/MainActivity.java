package com.techcos.c2c;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.util.Objects;


public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    EditText prefix,number;
    Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.main_page_toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle(Html.fromHtml("<small>C2C - Click To Chat</small>"));
        prefix = findViewById(R.id.prefix);
        number = findViewById(R.id.number);
        submit = findViewById(R.id.Btn_Submit);
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        prefix.setText("91");
        AdView adview;
        adview=findViewById(R.id.mainAd);


        AdRequest adRequest=new AdRequest.Builder().build();
        adview.loadAd(adRequest);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String link = prefix.getText().toString() + number.getText().toString();
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://wa.me/"+link));
                startActivity(intent);
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.options_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        super.onOptionsItemSelected(item);
        if(item.getItemId()==R.id.main_about_option)
        {
            Intent intent = new Intent(MainActivity.this,About.class);
            startActivity(intent);
        }
        if(item.getItemId()==R.id.main_report_option)
        {
            Intent intent = new Intent(Intent.ACTION_SEND);
            String[] rec = {"TechCosIncorporated@gmail.com"};
            intent.putExtra(Intent.EXTRA_EMAIL,rec);
            intent.putExtra(Intent.EXTRA_SUBJECT,"Bug Report");

            intent.setType("text/html");
            intent.setPackage("com.google.android.gm");
            startActivity(Intent.createChooser(intent,"Send Mail"));
        }
        if(item.getItemId()==R.id.main_rateus_option)
        {
            Toast.makeText(this,"Under Developement!",Toast.LENGTH_SHORT).show();
        }
        return true;
    }
}
