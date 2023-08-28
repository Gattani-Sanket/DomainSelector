package com.example.domainselector;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class QuestionsFileHandler {
    private Context mContext;
    ArrayList list;
    public QuestionsFileHandler(Context context)
    {
        this.mContext=context;
    }
    public ArrayList<String> readLine(String path)
    {

         list =new ArrayList();
        AssetManager am = mContext.getAssets();
        try {

            InputStream is = am.open(path);
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String line;

            while ((line = reader.readLine()) != null)
            list.add(line);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return(list);
    }
}
