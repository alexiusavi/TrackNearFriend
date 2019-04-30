package com.example.tracknearfriend;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnRegister;
    private EditText reg_fullname;
    private EditText reg_email;
    private EditText reg_password;
    private TextView link_to_login;

    private static final String TAG = "EmailPassword";

    private ProgressDialog progressDialog;

    private FirebaseAuth firebaseAuth;

    FirebaseFirestore db = FirebaseFirestore.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        firebaseAuth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(this);

        btnRegister = findViewById(R.id.btnRegister);

        reg_fullname = findViewById(R.id.reg_fullname);
        reg_email = findViewById(R.id.reg_email);
        reg_password = findViewById(R.id.reg_password);
        link_to_login = findViewById(R.id.link_to_login);

        btnRegister.setOnClickListener(this);
        link_to_login.setOnClickListener(this);
    }


    private void registerUser(){
        final String fullname = reg_fullname.getText().toString().trim();
        final String email = reg_email.getText().toString().trim();
        final String password = reg_password.getText().toString().trim();


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

        progressDialog.show();
        progressDialog.setMessage("Registering...");

        firebaseAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if(task.isSuccessful()){
                            Toast.makeText(SignupActivity.this, "Registered Successfully...", Toast.LENGTH_LONG).show();
                            LoginIntent();
                            dataBase(fullname, email, password); //calling dataBase() method;

                        }
                        else{
                            Toast.makeText(SignupActivity.this, "Already Registered/Invalid Email...", Toast.LENGTH_LONG).show();
                            SignupIntent();
                        }
                    }
                });

    }

    //FireStore DataBase;
    private void dataBase(String fullname, String email, String password) {
        /// Create a new user with a first and last name
        Map<String, Object> user = new HashMap<>();
        user.put("fullName", fullname);
        user.put("email", email);
        user.put("password", password);

        // Add a new document with a generated ID
        db.collection("location")
                .add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
                    }
                });
    }

    public void LoginIntent() {
        startActivity(new Intent(this, LoginActivity.class));
    }

    public void SignupIntent() {
        startActivity(new Intent(this, SignupActivity.class));
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
