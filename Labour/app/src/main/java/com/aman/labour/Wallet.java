package com.aman.labour;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import com.aman.labour.Adapter.MyViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;
public class Wallet extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this, Dashboard.class));
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet);






     tabLayout=findViewById(R.id.myTab);
     viewPager=findViewById(R.id.myViewPager);

        MyViewPagerAdapter adapter=new MyViewPagerAdapter(getSupportFragmentManager());

       viewPager.setAdapter(adapter);

        tabLayout.setupWithViewPager(viewPager);
    }
}