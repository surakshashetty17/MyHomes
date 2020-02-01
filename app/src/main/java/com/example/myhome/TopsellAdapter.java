package com.example.myhome;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class TopsellAdapter extends RecyclerView.Adapter<TopsellAdapter.TopViewHolder>
{
     ArrayList<TopsellModal> tlist;
     private Context ctx;

    public TopsellAdapter(Context ctx,ArrayList<TopsellModal> tlist)
    {
        this.ctx = ctx;
        this.tlist = tlist;
    }

    @NonNull
    @Override
    public TopViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater lf=LayoutInflater.from(parent.getContext());
        View view=lf.inflate(R.layout.itemseller,parent,false);
        TopsellAdapter.TopViewHolder vh=new TopsellAdapter.TopViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull TopViewHolder holder, int position) {

        holder.title.setText(tlist.get(position).getTitle());
        Picasso.get().load(tlist.get(position).getImg()).into(holder.img);    }

    @Override
    public int getItemCount() {
        return tlist.size();
    }

    public class TopViewHolder extends RecyclerView.ViewHolder
    {
        TextView title;
        ImageView img;

        public TopViewHolder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.item_sellershop);
            img=itemView.findViewById(R.id.item_seller);
        }
    }
}
