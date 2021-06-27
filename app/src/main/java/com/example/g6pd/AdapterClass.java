package com.example.g6pd;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AdapterClass extends RecyclerView.Adapter<AdapterClass.MyViewHolder>{
    List<Items> itemsList;
    public AdapterClass(List<Items> itemsList){
        this.itemsList = itemsList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tv.setText(itemsList.get(position).name);
        holder.tvLabel.setText(itemsList.get(position).company);
        if(itemsList.get(position).type == "food"){
            holder.ivIcon.setImageResource(R.drawable.food);
        }else {
            holder.ivIcon.setImageResource(R.drawable.pharm);
        }
    }

    @Override
    public int getItemCount() {
        return itemsList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tv, tvLabel;
        ImageView ivIcon;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.product);
            ivIcon = itemView.findViewById(R.id.icon);
            tvLabel = itemView.findViewById(R.id.label);
        }
    }
}
