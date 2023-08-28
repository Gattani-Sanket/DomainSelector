package com.example.domainselector;


import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentAnalyse extends Fragment {


    public FragmentAnalyse() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment_analyse, container, false);
    }
    DatabaseHelper dbh;
    SQLiteDatabase db;
    ContentValues values;

    CheckBox cb1,cb2,cb3,cb4,cb5,cb6,cb7,cb8,cb9,cb10,cb11,cb12;
    Button btnNext;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        dbh= new DatabaseHelper(getActivity());
        db=dbh.getWritableDatabase();
        values=new ContentValues();
        cb1=view.findViewById(R.id.cboption1);
        cb2=view.findViewById(R.id.cboption2);
        cb3=view.findViewById(R.id.cboption3);
        cb4=view.findViewById(R.id.cboption4);
        cb5=view.findViewById(R.id.cboption5);
        cb6=view.findViewById(R.id.cboption6);
        cb7=view.findViewById(R.id.cboption7);
        cb8=view.findViewById(R.id.cboption8);
        cb9=view.findViewById(R.id.cboption9);
        cb10=view.findViewById(R.id.cboption10);
        cb11=view.findViewById(R.id.cboption11);
        cb12=view.findViewById(R.id.cboption12);
        btnNext=view.findViewById(R.id.interestnext);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.execSQL("Delete From Prerequisites");
                values.clear();
                if(cb1.isChecked())
                {
                        values.put("Category",cb1.getText().toString());
                    db.insert("Prerequisites", null, values);
                }
                if(cb2.isChecked())
                {
                    values.put("Category", cb2.getText().toString());
                    db.insert("Prerequisites", null, values);
                }
                if(cb3.isChecked())
                {
                    values.put("Category",cb3.getText().toString());
                    db.insert("Prerequisites", null, values);
                }
                if(cb4.isChecked())
                {
                    values.put("Category",cb4.getText().toString());
                    db.insert("Prerequisites", null, values);
                }
                if(cb5.isChecked())
                {
                    values.put("Category",cb5.getText().toString());
                    db.insert("Prerequisites", null, values);
                }
                if(cb6.isChecked())
                {
                    values.put("Category",cb6.getText().toString());
                    db.insert("Prerequisites", null, values);
                }
                if(cb7.isChecked())
                {
                    values.put("Category",cb7.getText().toString());
                    db.insert("Prerequisites", null, values);
                }
                if(cb8.isChecked())
                {
                    values.put("Category",cb8.getText().toString());
                    db.insert("Prerequisites", null, values);
                }
                if(cb9.isChecked())
                {
                    values.put("Category",cb9.getText().toString());
                    db.insert("Prerequisites", null, values);
                }
                if(cb10.isChecked())
                {
                    values.put("Category",cb10.getText().toString());
                    db.insert("Prerequisites", null, values);
                }
                if(cb11.isChecked())
                {
                    values.put("Category",cb11.getText().toString());
                    db.insert("Prerequisites", null, values);
                }
                if(cb12.isChecked())
                {
                    values.put("Category",cb12.getText().toString());
                    db.insert("Prerequisites", null, values);
                }

                System.out.println("OUT");
                Intent intent=new Intent(getActivity(),ActivityInterestQuestion.class);
                startActivity(intent);
            }

        });
        super.onViewCreated(view, savedInstanceState);
    }
}
