package com.techcos.c2c;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    EditText prefix,number;
    Button submit;
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


        toolbar = findViewById(R.id.main_page_toolbar);
        setSupportActionBar(toolbar);
//        Objects.requireNonNull(getSupportActionBar()).setTitle(Html.fromHtml("<small>C2C - Click To Chat</small>"));
        prefix = findViewById(R.id.prefix);
        number = findViewById(R.id.number);
        submit = findViewById(R.id.Btn_Submit);


        prefix.setText("91");

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
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id="+getPackageName())));
        }
        return true;
    }

}

