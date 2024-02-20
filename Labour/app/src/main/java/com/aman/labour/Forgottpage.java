package com.aman.labour;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

public class Forgottpage extends AppCompatActivity {
    ImageView call, whatsapp;

    EditText e1,e2;
    Button log;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this, Loginpage.class));
        finish();
    }


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgottpage);

        call = findViewById(R.id.callbtn);
        whatsapp = findViewById(R.id.whatsappbtn);
        log=findViewById(R.id.Login_btn);
        e1=findViewById(R.id.newpass);
        e2=findViewById(R.id.renewpass);

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCallConfirmationDialog();
            }
        });

        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = e1.getText().toString();
                String pass = e2.getText().toString();
                Toast.makeText(Forgottpage.this, "Password is :- "+id, Toast.LENGTH_SHORT).show();
                Toast.makeText(Forgottpage.this, "Password is:- "+pass, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Forgottpage.this, MainActivity.class);
                startActivity(intent);
                finish();

            }
        });





        whatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showWhatsAppConfirmationDialog();
            }
        });
    }

    private void showCallConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(Forgottpage.this);
        builder.setTitle("Confirm Call");
        builder.setMessage("Are you sure you want to call 7398869340");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                makePhoneCall();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(Forgottpage.this, "Call Permisson Denied", Toast.LENGTH_SHORT).show();
            }
        });
        builder.show();
    }

    private void makePhoneCall() {
        String phoneNumber = "tel:" + "7398869340";
        Intent dial = new Intent(Intent.ACTION_CALL, Uri.parse(phoneNumber));
        startActivity(dial);
    }

    private void showWhatsAppConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(Forgottpage.this);
        builder.setTitle("Confirm WhatsApp Message");
        builder.setMessage("Do you want to send a WhatsApp message");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                sendWhatsAppMessage();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(Forgottpage.this, "WhatsApp Permisson Denied", Toast.LENGTH_SHORT).show();
            }
        });
        builder.show();
    }

    private void sendWhatsAppMessage() {
        String phoneNumber = "7398869340";
        String message = "Hi!";

        Uri uri = Uri.parse("https://wa.me/" + phoneNumber + "?text=" + Uri.encode(message));
        Intent sendIntent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(sendIntent);
    }
}
