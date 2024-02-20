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

public class MainActivity extends AppCompatActivity {
    ImageView call, whatsap;
    Button btn;
    TextView btn1, btn2,register;
    EditText no;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        no=findViewById(R.id.contactNum);
        call = findViewById(R.id.calldailer);
        whatsap = findViewById(R.id.whatsaplaunch);
        btn2 = findViewById(R.id.websiteurl);
        btn1 = findViewById(R.id.loginBypass);
        btn = findViewById(R.id.btn_continue);
        register = findViewById(R.id.Registration);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Select_service.class));
            }
        });


        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCallConfirmationDialog();
            }
        });
        whatsap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showWhatsAppConfirmationDialog();
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Webview.class));
                finish();
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Loginpage.class));
                finish();
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String otpNumber=no.getText().toString();
                Toast.makeText(MainActivity.this, "Number is:- "+otpNumber, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, OtpVerification.class);
                startActivity(intent);
                finish();

            }
        });
    }

    private void showCallConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
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
                Toast.makeText(MainActivity.this, "Call Permisson Denied", Toast.LENGTH_SHORT).show();
            }
        });
        builder.show();
    }
    private void makePhoneCall() {
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            String phoneNumber = "tel:" + "7398869340";
            Intent dial = new Intent(Intent.ACTION_CALL, Uri.parse(phoneNumber));
            startActivity(dial);
        } else {

            requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, 1);
        }
    }
    private void showWhatsAppConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
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
                Toast.makeText(MainActivity.this, "WhatsApp Permission Denied", Toast.LENGTH_SHORT).show();
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
