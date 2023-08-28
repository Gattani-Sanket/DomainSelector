package com.example.domainselector;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import de.hdodenhof.circleimageview.CircleImageView;

public class ActivityProfile extends AppCompatActivity {

    CircleImageView profile;
    int requestCode=1;
    private Uri mainImageUri=null;
    Button savePhoto;
    TextView username,usermob,useremail;
    private StorageReference storageReference;
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebaseFirestore;

    ProgressDialog progressDialog;
    String doenloadphotolink;
    Task urlTask;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        firebaseAuth=FirebaseAuth.getInstance();

        firebaseFirestore=FirebaseFirestore.getInstance();
        storageReference= FirebaseStorage.getInstance().getReference();
        progressDialog=new ProgressDialog(ActivityProfile.this);
        profile=findViewById(R.id.profillephoto);
        savePhoto=findViewById(R.id.saveImage);

        username=findViewById(R.id.profileusername);
        useremail=findViewById(R.id.profilleemail);
        usermob=findViewById(R.id.profilemob);
       setinitialprofile();
       // StorageReference image_path=storageReference.child("profile_image").child(firebaseAuth.getCurrentUser().getUid()+".jpg");

        savePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ActivityProfile.this,"Image Url: "+mainImageUri,Toast.LENGTH_SHORT).show();
                if(mainImageUri!=null)
                {


                    progressDialog.setMessage("Setting Up Profile Photo");
                    progressDialog.show();

                    StorageReference sr= FirebaseStorage.getInstance().getReference();
                    String IMAGEPATH="profile_image/"+firebaseAuth.getCurrentUser().getUid()+".jpg";
                    final StorageReference image_path=storageReference.child("profile_image").child(firebaseAuth.getCurrentUser().getUid()+".jpg");

                    UploadTask uploadTask=image_path.putFile(mainImageUri);

                    urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                        @Override
                        public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                            if (!task.isSuccessful()) {
                                throw task.getException();
                            }
                            else
                            {
                                progressDialog.dismiss();
                            }

                            // Continue with the task to get the download URL
                            return image_path.getDownloadUrl();
                        }
                    });
                    urlTask.addOnCompleteListener(new OnCompleteListener<Uri>() {
                        @Override
                        public void onComplete(@NonNull Task<Uri> task) {
                            if (task.isSuccessful()) {
                                Uri downloadUri = task.getResult();
                                doenloadphotolink = downloadUri.toString();

                                Toast.makeText(ActivityProfile.this, "Image Uploaded Succesfully", Toast.LENGTH_SHORT).show();
                                uploadtouserdatbase();
                            } else {
                                // Handle failures
                                // ...
                                Toast.makeText(ActivityProfile.this, "Image Uploaded Failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }
                else
                {
                    Toast.makeText(ActivityProfile.this, "Error No file selected", Toast.LENGTH_SHORT).show();
                }
            }
        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(Build.VERSION.SDK_INT >=Build.VERSION_CODES.M)
                {
                    if(ContextCompat.checkSelfPermission(ActivityProfile.this, Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED)
                    {
                        Toast.makeText(ActivityProfile.this,"Permission Denied",Toast.LENGTH_SHORT).show();
                        ActivityCompat.requestPermissions(ActivityProfile.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},requestCode);
                    }
                    else
                    {
                        bringImagePicker();
                    }
                }
                else
                {
                   bringImagePicker();
                }
            }
        });
    }

    private void bringImagePicker() {

        CropImage.activity()
                .setGuidelines(CropImageView.Guidelines.ON)
                .setAspectRatio(1,1)
                .start(ActivityProfile.this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                {
                    mainImageUri = result.getUri();
                    profile.setImageURI(mainImageUri);
                }

            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
    public  void  uploadtouserdatbase()
    {
        FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
        DatabaseReference databaseReference=firebaseDatabase.getReference(firebaseAuth.getCurrentUser().getUid());
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                UserInfo info=dataSnapshot.getValue(UserInfo.class);
                info.ImageURL=doenloadphotolink;
                FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
                DatabaseReference databaseReference=firebaseDatabase.getReference(firebaseAuth.getCurrentUser().getUid());
                databaseReference.setValue(info);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        databaseReference.child("ImageUrl").setValue(doenloadphotolink);
    }
    public void setinitialprofile()
    {

        FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
        DatabaseReference databaseReference=firebaseDatabase.getReference(firebaseAuth.getCurrentUser().getUid());
       databaseReference.addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

               UserInfo info=dataSnapshot.getValue(UserInfo.class);
               username.setText(info.Uname);
               useremail.setText(info.Email);
               usermob.setText(info.Mob);
               if(info.ImageURL!=null)
               {
                   Glide.with(getApplicationContext()).load(info.ImageURL).into(profile);
               }

           }

           @Override
           public void onCancelled(@NonNull DatabaseError databaseError) {


           }
       });

    }

}
