package com.example.myhome;

import android.content.Context;
import android.icu.text.Transliterator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class BannerAdapter extends RecyclerView.Adapter<BannerAdapter.BannerViewHolder>
{
    private ArrayList<BannerModal> list;
    private Context ctx;
    public BannerAdapter(Context ctx, ArrayList<BannerModal> list)
    {
        this.ctx=ctx;
        this.list=list;
    }

    @NonNull
    @Override
    public BannerAdapter.BannerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater lf=LayoutInflater.from(parent.getContext());
        View view=lf.inflate(R.layout.itemdata,parent,false);
        BannerAdapter.BannerViewHolder vh=new BannerAdapter.BannerViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull BannerViewHolder holder, int position) {

        Picasso.get().load(list.get(position).getImg()).into(holder.img);    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class BannerViewHolder extends RecyclerView.ViewHolder
    {
        private ImageView img;

        public BannerViewHolder(@NonNull View itemView)
        {
            super(itemView);
            img=(ImageView)itemView.findViewById(R.id.item_image);
        }
    }
}
