package com.example.domainselector;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

public class ActivityForum extends AppCompatActivity {

    FloatingActionButton btnadd;
    TextView tvDomainname;
    RecyclerView discussion_list_view;
    List<PostView>post_list;
    List<String>doc_id;
    FirebaseFirestore firebaseFirestore;
    PostRecyclerViewAdapter postRecyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum);

        Intent intent=getIntent();
        final String domain=intent.getExtras().getString("Title");

        tvDomainname=findViewById(R.id.tvDomainNameforum);
        btnadd=findViewById(R.id.addpost);

        discussion_list_view=findViewById(R.id.forumpostRV);

        tvDomainname.setText(domain);

        post_list=new ArrayList<>();
        doc_id=new ArrayList<>();

        postRecyclerViewAdapter=new PostRecyclerViewAdapter(post_list,doc_id,domain);
        discussion_list_view.setLayoutManager(new LinearLayoutManager(this));
        discussion_list_view.setAdapter(postRecyclerViewAdapter);
        firebaseFirestore=FirebaseFirestore.getInstance();

       firebaseFirestore.collection(domain).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {

                for(DocumentChange doc:queryDocumentSnapshots.getDocumentChanges())
                {
                    if(doc.getType()==DocumentChange.Type.ADDED)
                    {
                        PostView post=doc.getDocument().toObject(PostView.class);
                        post_list.add(post);
                        doc_id.add(doc.getDocument().getId());
                        postRecyclerViewAdapter.notifyDataSetChanged();
                    }
                }
            }
        });



        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(ActivityForum.this,ActivityPost.class);
                intent.putExtra("DomainName",domain);
                startActivity(intent);
            }
        });
    }
}
