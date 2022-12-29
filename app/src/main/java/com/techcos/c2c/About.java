package com.techcos.c2c;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.TextView;

public class About extends AppCompatActivity {
    TextView yt,twitter,mail,insta;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        yt=findViewById(R.id.youtubeText);
        yt.setMovementMethod(LinkMovementMethod.getInstance());
        twitter=findViewById(R.id.twitterText);
        twitter.setMovementMethod(LinkMovementMethod.getInstance());
        mail=findViewById(R.id.mailText);
        mail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                String[] rec = {"TechCosIncorporated@gmail.com"};
                intent.putExtra(Intent.EXTRA_EMAIL,rec);
                intent.putExtra(Intent.EXTRA_SUBJECT,"FeedBack");

                intent.setType("text/html");
                intent.setPackage("com.google.android.gm");
                startActivity(Intent.createChooser(intent,"Send Mail"));
            }
        });
        insta=findViewById(R.id.instaText);
        insta.setMovementMethod(LinkMovementMethod.getInstance());
    }
}
