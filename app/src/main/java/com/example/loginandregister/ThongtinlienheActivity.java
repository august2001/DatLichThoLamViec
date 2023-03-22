package com.example.loginandregister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class ThongtinlienheActivity extends AppCompatActivity {
    ImageView trove;
    ImageView fb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thongtinlienhe);
        trove= findViewById(R.id.id_trove);
        fb=findViewById(R.id.imgfb);

        trove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ThongtinlienheActivity.this, MainActivity.class));
                finish();
            }
        });
        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String applink="https://www.facebook.com/profile.php?id=100015849918524";
                openLink(applink);
            }
        });

    }
    private  void openLink(String applink){
        try{
            Uri uri= Uri.parse(applink);
            Intent intent= new Intent(Intent.ACTION_VIEW);
            intent.setData(uri);
            startActivity(intent);
        }catch (ActivityNotFoundException activityNotFoundException){
             activityNotFoundException.printStackTrace();
        }
    }
}