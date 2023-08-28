package com.example.domainselector;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class PostRecyclerViewAdapter extends RecyclerView.Adapter<PostRecyclerViewAdapter.ViewHolder> {

    String domain;
    List<PostView>post_list;
    List<String>doc_list;
    public  PostRecyclerViewAdapter(List<PostView>post_list,List<String>doc_id,String domain)
    {
        this.doc_list=doc_id;
        this.post_list=post_list;
        this.domain=domain;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_discussion_forum,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {

        String title_data=post_list.get(position).getTitle();
        String desc_data=post_list.get(position).getDescription();
        final String docid=doc_list.get(position);
        String userid=post_list.get(position).getUser_id();
        String time=post_list.get(position).getPosttime();
        holder.setTitleText(title_data);
        holder.setDescText(desc_data);
        holder.setUserName(userid);
        holder.settime(time);
        holder.queAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               Intent intent=new Intent(view.getContext(),AllComments.class);
               intent.putExtra("domain",domain);
               intent.putExtra("DocID",docid);
               view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return post_list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private View view;
        TextView descView;
        TextView titleView;
        TextView username;
        TextView posttime;
        CircleImageView profile;
        CardView queAnswer;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            view=itemView;
            queAnswer=view.findViewById(R.id.cardque);

        }
        public void setDescText(String descText)
        {
            descView=view.findViewById(R.id.postDescription);
            descView.setText(descText);
        }
        public void setTitleText(String titleText)
        {
            titleView=view.findViewById(R.id.posttitle);
            titleView.setText(titleText);
        }
        public void  setUserName(String userid)
        {
            username=view.findViewById(R.id.username);
            profile=view.findViewById(R.id.userphoto);
            FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
            DatabaseReference databaseReference=firebaseDatabase.getReference(userid);
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    UserInfo info=dataSnapshot.getValue(UserInfo.class);
                    username.setText((info.Uname));
                    Glide.with(view.getContext()).load(info.ImageURL).into(profile);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

        }
        public void settime(String time)
        {
            posttime=view.findViewById(R.id.posttime);
            posttime.setText(time);
        }
    }
}
