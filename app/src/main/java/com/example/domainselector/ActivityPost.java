package com.example.domainselector;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ActivityPost extends AppCompatActivity {

    TextView domainName;
    EditText titile,description;
    Button post;
    ProgressDialog progressDialog;
    RelativeLayout titleLayout,descriptionLayout;
    StorageReference storageReference;
    FirebaseFirestore firebaseFirestore;
    FirebaseAuth firebaseAuth;
    String current_user_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        Intent intent=getIntent();
        final String domain=intent.getExtras().getString("DomainName");

        storageReference= FirebaseStorage.getInstance().getReference();
        firebaseFirestore=FirebaseFirestore.getInstance();
        firebaseAuth=FirebaseAuth.getInstance();

        current_user_id=firebaseAuth.getCurrentUser().getUid();

        domainName=findViewById(R.id.domainHeading);
        domainName.setText(domain);
        titile=findViewById(R.id.ettitle);
        description=findViewById(R.id.etdescription);
        post=findViewById(R.id.postmypost);
        titleLayout=findViewById(R.id.titleRL);
        descriptionLayout=findViewById(R.id.descriptionRl);


        progressDialog=new ProgressDialog(this);


        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String problemTitle=titile.getText().toString();
                String problemDescription=description.getText().toString();

                if(! TextUtils.isEmpty(problemTitle) && !TextUtils.isEmpty(problemDescription))
                {
                    progressDialog.setMessage("Uploading Your post");
                    progressDialog.show();

                    PostView postView=new PostView(problemTitle,problemDescription,current_user_id,getCurrentTimeStamp());



                    firebaseFirestore.collection(domain).add(postView).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentReference> task) {

                            if(task.isSuccessful())
                            {
                                progressDialog.dismiss();
                                Intent intent=new Intent(ActivityPost.this,ActivityForum.class);
                                startActivity(intent);
                                finish();
                            }
                            else {
                                progressDialog.dismiss();
                            }
                        }
                    });

                }
                else if(TextUtils.isEmpty(problemTitle))
                {
                    progressDialog.dismiss();
                    titleLayout.setBackgroundResource(R.drawable.redborder);
                }
                else
                {
                    progressDialog.dismiss();
                    descriptionLayout.setBackgroundResource(R.drawable.redborder);
                }
            }
        });
    }
    public static String getCurrentTimeStamp(){
        try {

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String currentDateTime = dateFormat.format(new Date()); // Find todays date

            return currentDateTime;
        } catch (Exception e) {
            e.printStackTrace();

            return null;
        }
    }
}
