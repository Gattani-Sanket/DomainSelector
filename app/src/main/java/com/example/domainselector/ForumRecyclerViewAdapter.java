package com.example.domainselector;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ForumRecyclerViewAdapter extends RecyclerView.Adapter<ForumRecyclerViewAdapter.MyViewHolder> {

    private Context context;
    List<Domain> data;
    public ForumRecyclerViewAdapter( Context context,List<Domain> data)
    {
        this.context=context;
        this.data=data;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        view=layoutInflater.inflate(R.layout.forum_item,parent,false);
        return new MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ForumRecyclerViewAdapter.MyViewHolder holder, final int position) {

        holder.domainName.setText(data.get(position).getTitle());
        holder.domainImage.setImageResource(data.get(position).getThumbnail() );
        holder.rlforum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(context, ActivityForum.class);
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
        RelativeLayout rlforum;

        public MyViewHolder( View itemView) {
            super(itemView);
            domainName=itemView.findViewById(R.id.ForumDomainName);
            domainImage=itemView.findViewById(R.id.ForumDomainLogo);
            rlforum=itemView.findViewById(R.id.forumRL);

        }
    }
}
