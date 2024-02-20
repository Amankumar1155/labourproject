package com.aman.labour;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.aman.labour.Adapter.ServiceAdapter;

public class Sub_category extends AppCompatActivity {

    RecyclerView subRecycler;

    String namesz[]={"chachi","chacha"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_category);

//        subRecycler=findViewById(R.id.subRecyclerView);
//
//        ServiceAdapter adapter =new ServiceAdapter(namesz,this);
//
//        subRecycler.setLayoutManager(new GridLayoutManager(this,2));
//        subRecycler.setAdapter(adapter);
    }
}