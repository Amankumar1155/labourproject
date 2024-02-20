package com.aman.labour.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aman.labour.R;

public class RechargeAdapter extends RecyclerView.Adapter<RechargeAdapter.MyViewHolder>
{

    String id[];
    Context context;

    public RechargeAdapter(String[] id, Context context) {
        this.id = id;
        this.context = context;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View myLayout = LayoutInflater.from(context).inflate(R.layout.custom_debit_layout, parent, false);

        return new MyViewHolder(myLayout);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position)
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
