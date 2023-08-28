package com.example.domainselector;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.auth.User;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class CommentRecyclerviewAdapter extends RecyclerView.Adapter<CommentRecyclerviewAdapter.ViewHolder> {

    List<AddAnswer>comment_list;


    public CommentRecyclerviewAdapter(List<AddAnswer> comment_list) {
        this.comment_list = comment_list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comment,parent,false);
        return new CommentRecyclerviewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        String ans_data=comment_list.get(position).getAnswer();
        String userid=comment_list.get(position).getUser_id();
        String time=comment_list.get(position).getCommenttime();
        holder.setAnswerText(ans_data);
        holder.setusername(userid);
        holder.setcommenttime(time);
    }

    @Override
    public int getItemCount() {
        return comment_list.size();
    }

    public  class  ViewHolder extends RecyclerView.ViewHolder {
        private  View view;
        TextView answer;
        TextView username;
        TextView comtime;
        CircleImageView profile;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            view=itemView;
        }
        public void setAnswerText(String answerText)
        {
            answer=view.findViewById(R.id.comAnswer);
            answer.setText(answerText);
        }
        public void setusername(String userid)
        {
            System.out.println("............................................."+userid+".............................");
            username=view.findViewById(R.id.comusername);
            profile=view.findViewById(R.id.comuserphoto);
            FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
            DatabaseReference databaseReference=firebaseDatabase.getReference(userid);
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    UserInfo info=dataSnapshot.getValue(UserInfo.class);
                    System.out.println("..........................................."+info.Uname+"..........................");
                    username.setText((info.Uname));
                    Glide.with(view.getContext()).load(info.ImageURL).into(profile);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });


        }
        public void setcommenttime(String time)
        {
            comtime=view.findViewById(R.id.comtime);
            comtime.setText(time);
        }
    }
}
