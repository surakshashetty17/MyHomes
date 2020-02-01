package com.example.myhome;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>
{
   // private MyListData[] ld;
    private ArrayList<MyListData> ld;
    public MyAdapter(ArrayList<MyListData> ld)
    {
        this.ld=ld;
    }

    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater lf=LayoutInflater.from(parent.getContext());
        View view=lf.inflate(R.layout.item_data,parent,false);
        ViewHolder vh=new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.ViewHolder holder, int position)
    {
       // final MyListData listData=ld[position];
        holder.title.setText(ld.get(position).getTitle());
        Picasso.get().load(ld.get(position).getImg()).into(holder.imgid);


    }

    @Override
    public int getItemCount() {
        return ld.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
    {

        TextView title;
        ImageView imgid;
        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            this.title=(TextView)itemView.findViewById(R.id.categoryName);
            this.imgid=(ImageView)itemView.findViewById(R.id.categoryImg);

        }
    }

}
