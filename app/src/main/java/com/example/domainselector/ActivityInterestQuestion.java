package com.example.domainselector;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ActivityInterestQuestion extends AppCompatActivity {
DatabaseHelper dbh;
SQLiteDatabase db,db1;
Cursor cur;
String mpath;
    Button submit;
    RadioGroup Rg;
    RadioButton rb1,rb2,rb3,rb4;
TextView question;
QuestionsFileHandler QFH=new QuestionsFileHandler(this);
ArrayList<String>AL;
    int position,curpos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interest_question);
       question=findViewById(R.id.tvquestion);
        rb1=findViewById(R.id.option1rb);
        rb2=findViewById(R.id.option2rb);
        rb3=findViewById(R.id.option3rb);
        rb4=findViewById(R.id.option4rb);
        submit=findViewById(R.id.check);
        Rg=findViewById(R.id.optionRG);

        dbh=new DatabaseHelper(this);

       db=dbh.getReadableDatabase();
       db1=dbh.getWritableDatabase();
       String projection[] = {"Category","Questions_Solved","Correct"};
      cur = db.query("Prerequisites",projection, null, null, null, null, null);
      cur.moveToFirst();
        AL=QFH.readLine(cur.getString(0)+".txt");


       position=setQuestionAnswer(0);

      submit.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              if(Rg.getCheckedRadioButtonId()==-1)
              {
                  Toast.makeText(ActivityInterestQuestion.this,"Select your answer ",Toast.LENGTH_SHORT).show();
              }
              else
              {
                  //Retrieving Cursor Again Every time
                  String projection[] = {"Category","Questions_Solved","Correct"};
                  cur = db.query("Prerequisites",projection, null, null, null, null, null);
                  cur.moveToPosition(curpos);

                  int ans=getAnswer();
                  ContentValues value=new ContentValues();
                  int answered=cur.getInt(1);
                  answered++;

                        //incrementing if answer is correct
                        if(ans==Integer.parseInt(AL.get(position)))
                      {
                          int correct;
                          correct=cur.getInt(2);
                          correct++;
                          value.put("Correct",correct);
                            System.out.println("Correct:-"+correct);
                  }
                  value.put("Questions_Solved",answered);

                  //Query To Increment Attempt and Correct answer
                  db1.update("Prerequisites",value,cur.getColumnName(0)+"=?",new String[]{cur.getString(0)});

                  position++;

                if(position!=36)
                {
                    position=setQuestionAnswer(position);
                }
                else
                {

                    position=0;
                    if(cur.moveToNext())
                    {

                        AL=QFH.readLine(cur.getString(0)+".txt");
                        System.out.println(AL);
                        position=setQuestionAnswer(0);
                        curpos++;
                    }
                    else
                    {
                        //IntentToResult

                        Intent intent=new Intent(ActivityInterestQuestion.this,ActivityFinalResult.class);
                        startActivity(intent);
                    }
                }
              }
          }
      });


    }
public int setQuestionAnswer(int pos)
{
    question.setText(AL.get(pos));
    pos++;
    rb1.setText(AL.get(pos));
    pos++;
    rb2.setText(AL.get(pos));
    pos++;
    rb3.setText(AL.get(pos));
    pos++;
    rb4.setText(AL.get(pos));
    pos++;
    return pos;
}
public int getAnswer()
{

    if(rb1.isChecked()) {
        Rg.clearCheck();
        return 1;
    }
    else if(rb2.isChecked()) {
        Rg.clearCheck();
        return 2;
    }
        else if(rb3.isChecked()) {
        Rg.clearCheck();
        return 3;
    }
        else {
        Rg.clearCheck();
        return 4;
    }
 }
}

