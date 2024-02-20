package com.aman.labour;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aman.labour.Adapter.DebitAdapter;

public class DebitFragmnt extends Fragment {

    RecyclerView recyclerView;

 String id[]={"02446768","25577790",};


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_debit_fragmnt, container, false);

        recyclerView=view.findViewById(R.id.debitRecyclerview);

       DebitAdapter adapter=new DebitAdapter(id,getActivity());

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL,true));

        recyclerView.setAdapter(adapter);

        return view;
    }


}