package com.example.domainselector;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class DomainIntroduction extends YouTubeBaseActivity {

    private TextView title,description,introheading;
    private ImageView domainImage;
    private Button next1,back1;
    IntroHere IntroObj;
    public  String mpath;
    YouTubePlayerView youTubePlayerView;
    YouTubePlayer.OnInitializedListener onInitializedListener;
    String Title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_domain_introduction);

        Intent intent=getIntent();
         Title=intent.getExtras().getString("Title");

        mpath=Title+" Introduction.txt";



        title=findViewById(R.id.domainTitle1);
        description=findViewById(R.id.domainDescription);
        introheading=findViewById(R.id.intro);
        next1=findViewById(R.id.btnnext1);
        back1=findViewById(R.id.btnback1);

        title.setText(Title);
        IntroObj=new IntroHere(this);
        description.setText(IntroObj.readLine(mpath));


        next1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(DomainIntroduction.this,TechnicalSkills.class);
                intent.putExtra("Title",Title);
                startActivity(intent);


            }
        });

        back1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(DomainIntroduction.this,MainActivity.class);
                startActivity(intent);
                finish();

            }
        });

        youTubePlayerView=findViewById(R.id.domainThumbnail);
        onInitializedListener=new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {

                YoutubeLinks links=new YoutubeLinks();
                youTubePlayer.loadVideo(links.getLink(Title));
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        };
                youTubePlayerView.initialize(YouTubeConfig.getApiKey(),onInitializedListener);

    }
}
