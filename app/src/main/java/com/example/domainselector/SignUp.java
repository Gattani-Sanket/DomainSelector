package com.example.domainselector;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUp extends AppCompatActivity {

    EditText username,email,password1,password2;
    Button register;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        setupUIViews();

        firebaseAuth=FirebaseAuth.getInstance();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(validate())
                {
                    //upload data to the database
                    String User_Email=email.getText().toString().trim();
                    String User_Pass=password1.getText().toString().trim();

                    firebaseAuth.createUserWithEmailAndPassword(User_Email,User_Pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if(task.isSuccessful())
                            {
                                Toast.makeText(SignUp.this,"registration successful",Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(SignUp.this,MainActivity.class));
                            }
                            else
                            {
                                Toast.makeText(SignUp.this,"registration failed",Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                }
            }
        });



    }

    private void setupUIViews()
    {
        email=findViewById(R.id.semail);
        password1=findViewById(R.id.pwd1);
        password2=findViewById(R.id.pwd2);
        username=findViewById(R.id.susername);
        register=findViewById(R.id.btnRegister);


    }
    private Boolean validate()
    {
        Boolean result=false;

        String name= username.getText().toString();
        String pass1=password1.getText().toString();
        String pass2=password2.getText().toString();
        String mail=email.getText().toString();

        if(name.isEmpty() && pass1.isEmpty() && pass2.isEmpty() && mail.isEmpty())
        {
            Toast.makeText(this,"please enter all details",Toast.LENGTH_SHORT).show();
        }
        else if(!pass1.equals(pass2))
        {
            Toast.makeText(this,"please check your password",Toast.LENGTH_SHORT).show();
        }
        else
        {
            result=true;
        }
        return result;
    }
}
