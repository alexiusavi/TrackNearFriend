package com.example.tracknearfriend;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void mapsActivity(View view) {

        Intent getMapsActivityPage = new Intent(this, MapsActivity.class);

        final int result = 1;

        getMapsActivityPage.putExtra("callingActivity", "LoginActivity");

        startActivityForResult(getMapsActivityPage, result);
    }

    public void signupActivity(View view) {
        Intent getSignupPage = new Intent(this, SignupActivity.class);

        final int res = 1;

        getSignupPage.putExtra("call","SignupActivity");

        startActivityForResult(getSignupPage, res);
    }
}
