package com.aman.labour;




import android.content.Intent;

import android.os.Bundle;

import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class Kycprocess extends AppCompatActivity {

    CardView btn1, pic, adharfront, adharback;


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this, Registrationform.class));
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kycprocess);

        adharfront = findViewById(R.id.Adharfront);
        adharback = findViewById(R.id.Adharback);
        btn1 = findViewById(R.id.docsubmit);
        pic = findViewById(R.id.imagebtn);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Kycprocess.this, Dashboard.class));
                finish();
            }
        });
    }
}
