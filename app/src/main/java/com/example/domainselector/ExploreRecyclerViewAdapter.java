package com.example.domainselector;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ExploreRecyclerViewAdapter extends RecyclerView.Adapter<ExploreRecyclerViewAdapter.MyViewHolder>{

    private Context context;
    List<Domain>data;

    public ExploreRecyclerViewAdapter(Context context, List<Domain> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        view=layoutInflater.inflate(R.layout.explore_cardviewitem,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        holder.domainName.setText(data.get(position).getTitle());
        holder.domainImage.setImageResource(data.get(position).getThumbnail() );
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(context, DomainIntroduction.class);
                intent.putExtra("Title",data.get(position).getTitle());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView domainName;
        ImageView domainImage;
        CardView cardView;

        public MyViewHolder( View itemView) {
            super(itemView);
            domainName=itemView.findViewById(R.id.tvDomainName);
            domainImage=itemView.findViewById(R.id.ivDomain);
            cardView=itemView.findViewById(R.id.cardView);

        }
    }
}
