package com.example.e_foodcourtapp.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.e_foodcourtapp.Activity.DetailActivity;
import com.example.e_foodcourtapp.Domain.PopularDomain;
import com.example.e_foodcourtapp.R;

import java.util.ArrayList;

public class PopularAdapter extends RecyclerView.Adapter<PopularAdapter.ViewHolder> {
    ArrayList<PopularDomain> popularDomains;

    public PopularAdapter(ArrayList<PopularDomain> popularDomains) {
        this.popularDomains = popularDomains;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_popular,parent,false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull PopularAdapter.ViewHolder holder, int position) {
        holder.title.setText(popularDomains.get(position).getTitle());
        holder.desc.setText(popularDomains.get(position).getDesc());
        holder.fee.setText(String.valueOf(popularDomains.get(position).getFee()));

        int resId = holder.itemView.getContext().getResources().getIdentifier(popularDomains.get(position).getPic(), "drawable", holder.itemView.getContext().getPackageName());

        Glide.with(holder.itemView.getContext())
                .load(resId)
                .into(holder.pic);

        holder.addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(), DetailActivity.class);
                intent.putExtra("object", popularDomains.get(position));
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return popularDomains.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, desc, fee;
        ImageView pic, addBtn;

        public ViewHolder(@NonNull @org.jetbrains.annotations.NotNull View itemView) {
            super(itemView);
            pic = itemView.findViewById(R.id.picCart);
            title = itemView.findViewById(R.id.titleText);
            desc = itemView.findViewById(R.id.description);
            fee = itemView.findViewById(R.id.feeEachItem);
            addBtn = itemView.findViewById(R.id.addBtn);

        }
    }
}
