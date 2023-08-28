package com.example.domainselector;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class ActivitySplash extends AppCompatActivity {

    TextView name;
    ImageView photo;
    Animation frombottom,fromtop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        name=findViewById(R.id.proname);
          photo=findViewById(R.id.splashphoto);
        frombottom= AnimationUtils.loadAnimation(this,R.anim.frombottom);
        fromtop=AnimationUtils.loadAnimation(this,R.anim.fromtop);
        name.setAnimation(frombottom);
        photo.setAnimation(fromtop);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(ActivitySplash.this,LogIn.class);
     startActivity(intent);
        finish();
            }
        },2100);

    }
}
