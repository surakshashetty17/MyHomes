package com.example.myhome;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ViewPageAdapter extends RecyclerView.Adapter<ViewPageAdapter.PageViewHolder>
{

    private ArrayList<ViewpageModal> list;
    Context ctx;
    public ViewPageAdapter(Context ctx,ArrayList<ViewpageModal> list)
    {
        this.list=list;
        this.ctx=ctx;
    }
    @NonNull
    @Override
    public PageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater lf=LayoutInflater.from(parent.getContext());
        View view=lf.inflate(R.layout.itemoffer,parent,false);
        ViewPageAdapter.PageViewHolder vh=new ViewPageAdapter.PageViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull PageViewHolder holder, int position)
    {
        Picasso.get().load(list.get(position).getImage()).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class PageViewHolder extends RecyclerView.ViewHolder
    {
        ImageView image;

        public PageViewHolder(@NonNull View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.offer);
        }
    }
}
