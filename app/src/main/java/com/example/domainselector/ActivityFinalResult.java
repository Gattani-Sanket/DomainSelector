package com.example.domainselector;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.razerdp.widget.animatedpieview.AnimatedPieView;
import com.razerdp.widget.animatedpieview.AnimatedPieViewConfig;
import com.razerdp.widget.animatedpieview.callback.OnPieSelectListener;
import com.razerdp.widget.animatedpieview.data.IPieInfo;
import com.razerdp.widget.animatedpieview.data.SimplePieInfo;

public class ActivityFinalResult extends AppCompatActivity {

    TextView resultview;
    DomainCount[] DC;
    DomainCount dc;
    String result="";
    Cursor cur;
    ContentValues values;
    SQLiteDatabase db,db1;
    DatabaseHelper dbh;
    AnimatedPieView animatedPieView;
    AnimatedPieViewConfig animatedPieViewConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_result);


            animatedPieView=findViewById(R.id.pie);
        resultview=findViewById(R.id.result);
            animatedPieViewConfig=new AnimatedPieViewConfig();
            dbh=new DatabaseHelper(this);
            db=dbh.getReadableDatabase();
            db1=dbh.getWritableDatabase();
            String projection[] = {"Category","Questions_Solved","Correct"};
            cur = db.query("Prerequisites",projection, null, null, null, null, null);
            cur.moveToFirst();
            DC=new DomainCount[11];
            for(int i=0;i<11;i++)
                DC[i]=new DomainCount();

            DC[0].name="Machine Learning";
            DC[1].name="Internet of Things";
            DC[2].name="Cyber Security";
            DC[3].name="Artificial Intelligence";
            DC[4].name="Robotics";
            DC[5].name="Data Analysis";
            DC[6].name="Cloud Computing";
            DC[7].name="BlockChain Technology";
            DC[8].name="Big Data";
            DC[9].name="Web Development";
            DC[10].name="Data Mining";

        analysedata();
        sortDC();
        for (int i=0;i<11;i++)
            System.out.println(DC[i].name+":"+DC[i].count);
            if(DC[0].count==0)
            {
                result=result+"Please Improve your Knowledge And Come Back Again You Will See Information Regarding domains in Explore Section!";
            }
            else
            {

        addpieelement();
        animatedPieViewConfig.duration(1000);
        animatedPieViewConfig.drawText(true);
        animatedPieViewConfig.strokeMode(false);
        animatedPieViewConfig.textSize(30);

        animatedPieViewConfig.selectListener(new OnPieSelectListener<IPieInfo>() {
            @Override
            public void onSelectPie(@NonNull IPieInfo pieInfo, boolean isFloatUp) {

                Toast.makeText(ActivityFinalResult.this,pieInfo.getDesc()+"-"+pieInfo.getValue(),Toast.LENGTH_SHORT).show();
            }
        });
        animatedPieViewConfig.startAngle(-180);
        animatedPieView.applyConfig(animatedPieViewConfig);
        animatedPieView.start();

            }
            resultview.setText(result);




        }

        public void sortDC()
        {
            dc=new DomainCount();
            for(int i=0;i<11;i++)
            {
                for(int j=0;j<10-i;j++)
                {
                    if(DC[j].count<DC[j+1].count)
                    {
                        dc=DC[j];
                        DC[j]=DC[j+1];
                        DC[j+1]=dc;
                    }
                }
            }

        }
        public void analysedata()
        {
            for(int i=0;i<11;i++)
                DC[i].count=0;

            values=new ContentValues();
            cur.moveToFirst();
            System.out.println(cur.getString(0));
            do {
                if (cur.getString(0).equals("Mathematics")) {
                    if(cur.getInt(2)>=3)
                    {
                        DC[0].count++;
                        DC[3].count++;
                        DC[4].count++;
                    }
                }
                else if (cur.getString(0).equals("Algorithms")) {
                    if(cur.getInt(2)>=3)
                    {
                        DC[0].count++;
                        DC[1].count++;
                        DC[2].count++;
                        DC[3].count++;
                        DC[4].count++;
                        DC[7].count++;
                        DC[10].count++;
                    }
                }
                else if (cur.getString(0).equals("Hardware")) {
                    if(cur.getInt(2)>=3)
                    {
                        DC[1].count++;
                        DC[4].count++;

                    }
                }
                else if (cur.getString(0).equals("Software")) {
                    if(cur.getInt(2)>=3)
                    {
                        DC[0].count++;
                        DC[1].count++;
                        DC[2].count++;
                        DC[3].count++;
                        DC[4].count++;
                        DC[5].count++;
                        DC[6].count++;
                        DC[7].count++;
                        DC[8].count++;
                        DC[9].count++;
                        DC[10].count++;

                    }
                }
                else if (cur.getString(0).equals("Electronics")) {
                    if(cur.getInt(2)>=3)
                    {

                        DC[1].count++;
                        DC[4].count++;

                    }
                }
                else if (cur.getString(0).equals("Electrical")) {
                    if(cur.getInt(2)>=3)
                    {
                        DC[1].count++;
                        DC[4].count++;

                    }
                }
                else if (cur.getString(0).equals("Cryptocurrency")) {
                    if(cur.getInt(2)>=3)
                    {

                        DC[7].count++;

                    }
                }
                else if (cur.getString(0).equals("Programming")) {
                    if(cur.getInt(2)>=3)
                    {
                        DC[0].count++;
                        DC[1].count++;
                        DC[2].count++;
                        DC[3].count++;
                        DC[4].count++;
                        DC[5].count++;
                        DC[6].count++;
                        DC[7].count++;
                        DC[8].count++;
                        DC[9].count++;
                        DC[10].count++;
                    }
                }
                else if (cur.getString(0).equals("Database")) {
                    if(cur.getInt(2)>=3)
                    {
                        DC[0].count++;
                        DC[3].count++;
                        DC[5].count++;
                        DC[8].count++;
                        DC[9].count++;
                        DC[10].count++;
                    }
                }
                else if (cur.getString(0).equals("Cloud Storage")) {
                    if(cur.getInt(2)>=3)
                    {
                        DC[5].count++;
                        DC[6].count++;
                        DC[8].count++;
                        DC[10].count++;
                    }
                }
                else if (cur.getString(0).equals("Sensor Study")) {
                    if(cur.getInt(2)>=3)
                    {
                        DC[1].count++;
                        DC[3].count++;
                        DC[4].count++;
                    }
                }
                else if (cur.getString(0).equals("Data Computing")) {
                    if(cur.getInt(2)>=3)
                    {
                        DC[0].count++;
                        DC[3].count++;
                        DC[4].count++;
                        DC[5].count++;
                        DC[7].count++;
                        DC[8].count++;
                        DC[10].count++;
                    }
                }

            }
            while(cur.moveToNext());
        }
        public void addpieelement()
        {
            if(DC[0].count!=0)
            animatedPieViewConfig.addData(new SimplePieInfo(DC[0].count, Color.parseColor("#AAFF0000"),DC[0].name));
            if(DC[1].count!=0)
            animatedPieViewConfig.addData(new SimplePieInfo(DC[1].count, Color.parseColor("#AA00FF00"),DC[1].name));
            if(DC[2].count!=0)
            animatedPieViewConfig.addData(new SimplePieInfo(DC[2].count, Color.parseColor("#f39c12"),DC[2].name));
            if(DC[3].count!=0)
            animatedPieViewConfig.addData(new SimplePieInfo(DC[3].count, Color.parseColor("#9b018a"),DC[3].name));
            if(DC[4].count!=0)
            animatedPieViewConfig.addData(new SimplePieInfo(DC[4].count, Color.parseColor("#DC143C"),DC[4].name));
            if(DC[5].count!=0)
            animatedPieViewConfig.addData(new SimplePieInfo(DC[5].count, Color.parseColor("#4bf320"),DC[5].name));
            if(DC[6].count!=0)
            animatedPieViewConfig.addData(new SimplePieInfo(DC[6].count, Color.parseColor("#00CD00"),DC[6].name));
            if(DC[7].count!=0)
            animatedPieViewConfig.addData(new SimplePieInfo(DC[7].count, Color.parseColor("#FFD700"),DC[7].name));
            if(DC[8].count!=0)
            animatedPieViewConfig.addData(new SimplePieInfo(DC[8].count, Color.parseColor("#94faff"),DC[8].name));
            if(DC[9].count!=0)
            animatedPieViewConfig.addData(new SimplePieInfo(DC[9].count, Color.parseColor("#00f4e"),DC[9].name));
            if(DC[10].count!=0)
            animatedPieViewConfig.addData(new SimplePieInfo(DC[10].count, Color.parseColor("#808000"),DC[10].name));

        }


}


