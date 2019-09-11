package com.example.domainselector;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
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
import com.google.firebase.auth.FirebaseUser;

public class LogIn extends AppCompatActivity {

    EditText etusername,etpassword;
    Button btnLogin,btnSignUp;
    FirebaseAuth firebaseAuth;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        btnLogin=findViewById(R.id.btnlogin);
        etusername=findViewById(R.id.textEmail);
        etpassword=findViewById(R.id.textPass);
        btnSignUp=findViewById(R.id.btnsignup);

        firebaseAuth=FirebaseAuth.getInstance();
        progressDialog=new ProgressDialog(this);

        FirebaseUser user=firebaseAuth.getCurrentUser();

        if(user!=null)
        {
            finish();
            startActivity(new Intent(LogIn.this,MainActivity.class));
        }

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                validate(etusername.getText().toString().trim(),etpassword.getText().toString().trim());


            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LogIn.this,SignUp.class);
                startActivity(intent);
            }
        });

    }

    private void validate(String username,String password)
    {
        progressDialog.setMessage("Finding Your Account");
        progressDialog.show();
        firebaseAuth.signInWithEmailAndPassword(username,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful())
                {
                    progressDialog.dismiss();
                    Toast.makeText(LogIn.this,"Login Successful",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LogIn.this,MainActivity.class));
                }
                else
                {
                    progressDialog.dismiss();
                    Toast.makeText(LogIn.this,"Login Failed",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
