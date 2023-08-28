package com.example.domainselector;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentExplore extends Fragment {


    public FragmentExplore() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment_explore, container, false);
    }

    List<Domain> domainList;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        domainList=new ArrayList<>();
        domainList.add(new Domain("Machine Learning",R.drawable.ml));
        domainList.add(new Domain("Internet of Things",R.drawable.iot));
        domainList.add(new Domain("Cyber Security",R.drawable.cyber));
        domainList.add(new Domain("Artificial Intelligence",R.drawable.ai));
        domainList.add(new Domain("Robotics",R.drawable.robotics));
        domainList.add(new Domain("Data Analysis",R.drawable.dataanalysis));
        domainList.add(new Domain("Cloud Computing",R.drawable.cloud));
        domainList.add(new Domain("Blockchain Technology",R.drawable.blockchain));
        domainList.add(new Domain("Big Data",R.drawable.bigdata));
        domainList.add(new Domain("Web Development",R.drawable.web));
        domainList.add(new Domain("Data Mining",R.drawable.mining));

        RecyclerView recyclerView=view.findViewById(R.id.recyclerView);
        ExploreRecyclerViewAdapter recyclerViewAdapter=new ExploreRecyclerViewAdapter(getContext(),domainList);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        recyclerView.setAdapter(recyclerViewAdapter);
        super.onViewCreated(view, savedInstanceState);
    }
}
