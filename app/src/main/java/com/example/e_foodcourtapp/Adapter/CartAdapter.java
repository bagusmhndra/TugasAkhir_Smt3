package com.example.e_foodcourtapp.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.e_foodcourtapp.Domain.PopularDomain;
import com.example.e_foodcourtapp.Helper.ManagementCart;
import com.example.e_foodcourtapp.Interface.ChangeNumberItem;
import com.example.e_foodcourtapp.R;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {
    private ArrayList<PopularDomain> popularDomains;
    private ManagementCart managementCart;
    private ChangeNumberItem changeNumberItem;

    public CartAdapter(ArrayList<PopularDomain> popularDomains, Context context, ChangeNumberItem changeNumberItem) {
        this.popularDomains = popularDomains;
        this.managementCart = new ManagementCart(context);
        this.changeNumberItem = changeNumberItem;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_cart, parent, false);

        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.ViewHolder holder, int position) {
        holder.title.setText(popularDomains.get(position).getTitle());
        holder.feeEachItem.setText(String.valueOf(popularDomains.get(position).getFee()));
        holder.num.setText(String.valueOf(popularDomains.get(position).getNumberInCart()));

        int resId = holder.itemView.getContext().getResources().getIdentifier(popularDomains.get(position).getPic(), "drawable", holder.itemView.getContext().getPackageName());

        Glide.with(holder.itemView.getContext())
                .load(resId)
                .into(holder.pic);

        holder.plusItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                managementCart.plusNumberFood(popularDomains, position, new ChangeNumberItem() {
                    @Override
                    public void changed() {
                        notifyDataSetChanged();
                        changeNumberItem.changed();
                    }
                });
            }
        });

        holder.minusItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                managementCart.minusNumberFood(popularDomains, position, new ChangeNumberItem() {
                    @SuppressLint("NotifyDataSetChanged")
                    @Override
                    public void changed() {
                        notifyDataSetChanged();
                        changeNumberItem.changed();
                    }
                });
            }
        });

    }

    @Override
    public int getItemCount() {
        return popularDomains.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView title, feeEachItem;
        ImageView pic, plusItem, minusItem;
        TextView num;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.titleText);
            feeEachItem = itemView.findViewById(R.id.feeEachItem);
            pic = itemView.findViewById(R.id.picCart);
            num = itemView.findViewById(R.id.numberOrder2);
            plusItem = itemView.findViewById(R.id.plusBtn2);
            minusItem = itemView.findViewById(R.id.minusBtn2);
        }
    }

}
