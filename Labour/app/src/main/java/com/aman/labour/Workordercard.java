package com.aman.labour;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class Workordercard extends AppCompatActivity {


    @Override
    public void onBackPressed() {
        super.onBackPressed();

        startActivity(new Intent(this, Dashboard.class));
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workordercard);
    }
}