package com.example.tracknearfriend;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SidebrActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sidebr);
    }

    @Override
    protected void onStart() {
        super.onStart();
        startActivity(new Intent(this, MapsActivity.class));
    }
}
