package com.example.domainselector;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class TechnicalSkills extends AppCompatActivity {

    public String mpath;
    IntroHere IntroObj;
    private TextView title2,description,techHeading;
    private Button next2,back2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_technical_skills);
        IntroObj=new IntroHere(this);
        Intent intent=getIntent();
        String Title=intent.getExtras().getString("Title");
        mpath=Title+" Technical Skills.txt";
        String str=IntroObj.readLine(mpath);
        title2=findViewById(R.id.domainTitle2);
        title2.setText(Title);
        description=findViewById(R.id.techDescription);
        techHeading=findViewById(R.id.tech);
        next2=findViewById(R.id.btnnext2);
        back2=findViewById(R.id.btnback2);
        description.setText(str);
        next2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });

        back2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(TechnicalSkills.this,DomainIntroduction.class);
                startActivity(intent);
                finish();

            }
        });
    }
}
