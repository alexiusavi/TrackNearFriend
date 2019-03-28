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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText username;
    private EditText password;
    private Button login;
    private Button signup;

    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser() != null){
            finish();
            startActivity(new Intent(getApplicationContext(), MapsActivity.class));
        }

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.login);
        signup = (Button) findViewById(R.id.signup);

        progressDialog = new ProgressDialog(this);

        login.setOnClickListener(this);
        signup.setOnClickListener(this);
    }

   private void userLogin(){
       String user = username.getText().toString().trim();
       String pass = password.getText().toString().trim();

       if(TextUtils.isEmpty(user)){
           //email is empty;
           Toast.makeText(this, "Please Enter Email", Toast.LENGTH_SHORT).show();
           //stopping the function execution further
           return;
       }

       if(TextUtils.isEmpty(pass)){
           //password is empty;
           Toast.makeText(this,"Please Enter Password", Toast.LENGTH_SHORT).show();
           //stopping the function execution further
           return;
       }


       progressDialog.setMessage("Login...");
       progressDialog.show();

       firebaseAuth.signInWithEmailAndPassword(user,pass)
               .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                   @Override
                   public void onComplete(@NonNull Task<AuthResult> task) {
                      progressDialog.dismiss();

                      if(task.isSuccessful()){
                          finish();
                          startActivity(new Intent(getApplicationContext(), MapsActivity.class));
                      }
                   }
               });
   }

    @Override
    public void onClick(View view) {
        if(view == login){
            userLogin();
        }
        if(view == signup){
            finish();
            startActivity(new Intent(this, SignupActivity.class));
        }
    }
}
