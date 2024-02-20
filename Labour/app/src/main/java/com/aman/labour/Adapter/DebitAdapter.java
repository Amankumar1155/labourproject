package com.aman.labour.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aman.labour.DebitFragmnt;
import com.aman.labour.R;
import com.aman.labour.RecyclerAdapter;

public class DebitAdapter extends RecyclerView.Adapter<DebitAdapter.MyViewHolder>
{

    String id[];
    Context context;

    public DebitAdapter(String[] id, Context context) {
        this.id = id;
        this.context = context;
    }


    @NonNull
    @Override
    public DebitAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View myLayout = LayoutInflater.from(context).inflate(R.layout.custom_debit_layout, parent, false);

        return new MyViewHolder(myLayout);
    }

    @Override
    public void onBindViewHolder(@NonNull DebitAdapter.MyViewHolder holder, int position)
    {

        holder.textView.setText("Transaction Id :  " + id[position]);
    }

    @Override
    public int getItemCount() {
        return id.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textView;
        public MyViewHolder(@NonNull View itemView)
        {
            super(itemView);

            textView=itemView.findViewById(R.id.transactionText);

        }
    }
}