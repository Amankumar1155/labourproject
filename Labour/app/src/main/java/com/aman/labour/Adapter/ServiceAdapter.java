package com.aman.labour.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.aman.labour.Model.Category;
import com.aman.labour.R;
import com.aman.labour.Registrationform;

public class ServiceAdapter extends RecyclerView.Adapter<ServiceAdapter.MyViewHolder> {

    Category[] categories;
    Context context;

    public ServiceAdapter(Category[] categories, Context context) {
        this.categories = categories;
        this.context = context;
    }

    @NonNull
    @Override
    public ServiceAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View myLayout = LayoutInflater.from(context).inflate(R.layout.service_custom_layout, parent, false);
        ServiceAdapter.MyViewHolder holder = new ServiceAdapter.MyViewHolder(myLayout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ServiceAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.textView.setText(categories[position].getName());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String categoryId = categories[position].getId();
                String categoryName = categories[position].getName();

                Intent i = new Intent(context, Registrationform.class);
                i.putExtra("category_id", categoryId);
                i.putExtra("category_name", categoryName);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return categories.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView cardView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView);
            cardView = itemView.findViewById(R.id.imagess);
        }
    }
}
