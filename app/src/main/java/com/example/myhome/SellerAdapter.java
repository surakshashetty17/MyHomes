package com.example.myhome;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SellerAdapter extends RecyclerView.Adapter<SellerAdapter.ViewHolder>
{
    SellerModal[] sm;
    public SellerAdapter(SellerModal[] sm)
    {
        this.sm=sm;
    }

    @NonNull
    @Override
    public SellerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater lf=LayoutInflater.from(parent.getContext());
        View view=lf.inflate(R.layout.sales_data,parent,false);
        SellerAdapter.ViewHolder vh=new SellerAdapter.ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull SellerAdapter.ViewHolder holder, int position)
    {
        final SellerModal sellerModal = sm[position];
        holder.title.setText(sm[position].getTitle());
        holder.img.setImageResource(sm[position].getImg());
    }

    @Override
    public int getItemCount()
    {
        return sm.length;
    }
    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView title;
        ImageView img;

        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            this.title=(TextView)itemView.findViewById(R.id.categoryName1);
            this.img=(ImageView) itemView.findViewById(R.id.categoryImg1);
        }
    }

}
