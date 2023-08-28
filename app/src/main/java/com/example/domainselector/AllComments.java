package com.example.domainselector;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;


public class AllComments extends AppCompatActivity {

    FloatingActionButton btncomment;
    RecyclerView comment_list_view;
    List<AddAnswer> comment_list;
    FirebaseFirestore firebaseFirestore;
    CommentRecyclerviewAdapter commentRecyclerViewAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_comments);

        final Intent intent=getIntent();
        final String domain=intent.getExtras().getString("domain");
        final String documentid=intent.getExtras().getString("DocID");


        btncomment=findViewById(R.id.addComment);
        comment_list_view=findViewById(R.id.commentRV);

        comment_list=new ArrayList<>();
        commentRecyclerViewAdapter=new CommentRecyclerviewAdapter(comment_list);
        comment_list_view.setLayoutManager(new LinearLayoutManager(this));
        comment_list_view.setAdapter(commentRecyclerViewAdapter);
        firebaseFirestore=FirebaseFirestore.getInstance();

        firebaseFirestore.collection(domain).document(documentid).collection("Comments").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {

                for(DocumentChange doc:queryDocumentSnapshots.getDocumentChanges())
                {
                    if(doc.getType()==DocumentChange.Type.ADDED)
                    {

                        AddAnswer comment=doc.getDocument().toObject(AddAnswer.class);
                        comment_list.add(comment);
                        commentRecyclerViewAdapter.notifyDataSetChanged();
                    }
                }
            }
        });

        btncomment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1=new Intent(AllComments.this,ActivityAnswer.class);
                intent1.putExtra("domain",domain);
                intent1.putExtra("DocID",documentid);
                startActivity(intent1);
            }
        });
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
