package com.aman.labour;

import androidx.appcompat.app.AppCompatActivity;
import android.Manifest;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;

public class Loginpage extends AppCompatActivity {
    Button log;
    EditText e1,e2;
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @SuppressLint({"MissingInflatedId", "CutPasteId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginpage);


        e1 = findViewById(R.id.contactNum);
        e2 = findViewById(R.id.password);
        log = findViewById(R.id.btn_login);


        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = e1.getText().toString();
                String pass = e2.getText().toString();
                Toast.makeText(Loginpage.this, "Number is:- " + id, Toast.LENGTH_SHORT).show();
                Toast.makeText(Loginpage.this, "Password is:- " + pass, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Loginpage.this, Dashboard.class);
                startActivity(intent);
                finish();

            }
        });

    }

}
