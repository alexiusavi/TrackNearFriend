package com.example.tracknearfriend;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnRegister;
    private EditText reg_fullname;
    private EditText reg_email;
    private EditText reg_password;
    private TextView link_to_login;

    private ProgressDialog progressDialog;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        firebaseAuth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(this);

        btnRegister = (Button) findViewById(R.id.btnRegister);

        reg_fullname = (EditText) findViewById(R.id.reg_fullname);
        reg_email = (EditText) findViewById(R.id.reg_email);
        reg_password = (EditText) findViewById(R.id.reg_password);
        link_to_login = (TextView) findViewById(R.id.link_to_login);

        btnRegister.setOnClickListener(this);
        link_to_login.setOnClickListener(this);
    }

    private void registerUser(){
        String email = reg_email.getText().toString().trim();
        String password = reg_password.getText().toString().trim();
        String fullname = reg_fullname.getText().toString().trim();

        if(TextUtils.isEmpty(fullname)){
            Toast.makeText(this, "Please Enter Full Name", Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(email)){
            //email is empty;
            Toast.makeText(this, "Please Enter Email", Toast.LENGTH_SHORT).show();
            //stopping the function execution further
            return;
        }

        if(TextUtils.isEmpty(password)){
            //password is empty;
            Toast.makeText(this,"Please Enter Password", Toast.LENGTH_SHORT).show();
            //stopping the function execution further
            return;
        }

        progressDialog.setMessage("Registering User...");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            finish();
                            Toast.makeText(SignupActivity.this,"Registered Successfully...", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(SignupActivity.this, "Could not register...",Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }
                });

    }

    @Override
    public void onClick(View view){
        if(view == btnRegister){
            registerUser();
        }
        if(view == link_to_login){
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }
    }

}
