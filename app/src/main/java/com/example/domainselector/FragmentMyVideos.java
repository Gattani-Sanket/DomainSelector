package com.example.domainselector;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.speech.RecognizerIntent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentMyVideos extends Fragment {


    public FragmentMyVideos() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment_my_videos, container, false);
    }

    ImageView voiceSerch,coursera,edx,udemy,udacity;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        voiceSerch=view.findViewById(R.id.ivvoice);
        coursera=view.findViewById(R.id.ivcoursera);
        edx=view.findViewById(R.id.ivedx);
        udemy=view.findViewById(R.id.ivudemy);
        udacity=view.findViewById(R.id.ivudacity);
        voiceSerch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sp=new Intent(RecognizerIntent.ACTION_WEB_SEARCH);
                sp.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                sp.putExtra(RecognizerIntent.EXTRA_PROMPT,"Speak Please");
                startActivity(sp);
            }
        });
        coursera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.coursera.org/"));
                startActivity(intent);
            }
        });
        edx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.edx.org/"));
                startActivity(intent);
            }
        });
        udemy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.udemy.com/"));
                startActivity(intent);
            }
        });
        udacity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.udacity.com/"));
                startActivity(intent);
            }
        });
        super.onViewCreated(view, savedInstanceState);
    }
}
