package com.aman.labour;

import android.annotation.SuppressLint;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class Dashboard extends AppCompatActivity {
    CardView card1;
    Button btn1,btn2;

    ImageView imageView ;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView navigationView, wallet;
    private ActionBarDrawerToggle drawerToggle;


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this, Kycprocess.class));
        finish();
    }

    @SuppressLint({"WrongViewCast", "MissingInflatedId", "CutPasteId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);




        card1=findViewById(R.id.workorder);
        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationViews = findViewById(R.id.nav_view);
        View headerView = navigationViews.getHeaderView(0);



        drawerLayout = findViewById(R.id.drawer_layout);
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
        );
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(item -> {
            int itemId = item.getItemId();

            if (itemId == R.id.nav_home)
            {
                startActivity(new Intent(Dashboard.this,Dashboard.class));
                finish();

            } else if (itemId==R.id.nav_profile)

            {
                startActivity(new Intent(Dashboard.this,EditProfile.class));
                finish();
            }
            else if (itemId==R.id.wallet)
            {
                startActivity(new Intent(Dashboard.this,Wallet.class));
                finish();
            }
            else if (itemId==R.id.rateapp)
            {
                String url = "https://alltradsolutions.com/";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
            }
            else if (itemId == R.id.refferfriend) {
                String referralLink = "Your referral link here"; // Replace with your actual referral link
                String shareMessage = "Check out this app: " + referralLink;

                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Check out this app!");
                shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);

                startActivity(Intent.createChooser(shareIntent, "Share via"));

                } else if (itemId == R.id.email) {
                String email = "amanchaursiya1234@gmail.com";
                String subject = "Hi!";
                String body = "Hi!\nPlease assist me.";
                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.setType("text/plain");
                emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{email});
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
                emailIntent.putExtra(Intent.EXTRA_TEXT, body);
                emailIntent.setPackage("com.google.android.gm");

                startActivity(emailIntent);
            }
            else if (itemId == R.id.logout) {
                Toast.makeText(this, "You are logged out.📴📴", Toast.LENGTH_SHORT).show();
                finish();

                Intent intent = new Intent(this, Loginpage.class);
                startActivity(intent);
            }
            else if (itemId==R.id.Chatai)

            {
//                startActivity(new Intent(Dashboard.this,EditProfile.class));
//                finish();
            }

            drawerLayout.closeDrawer(navigationView);

            return true;
        });


            imageView=findViewById(R.id.tv_drawer_nav);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!drawerLayout.isDrawerOpen(navigationView)) {
                    drawerLayout.openDrawer(navigationView);
                }
            }
        });

        card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Dashboard.this, Workordercard.class));
                finish();

            }
        });


    }
}
