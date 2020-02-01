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

public class SubCategoryAdapter extends RecyclerView.Adapter<SubCategoryAdapter.SubViewHoder>
{
    ArrayList<SubCategoryModal> list;
    Context ctx;
    public SubCategoryAdapter(Context ctx,ArrayList<SubCategoryModal> list)
    {
        this.ctx=ctx;
        this.list=list;
    }

    @NonNull
    @Override
    public SubViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater lf=LayoutInflater.from(parent.getContext());
        View view=lf.inflate(R.layout.subitem,parent,false);
        SubCategoryAdapter.SubViewHoder vh=new SubCategoryAdapter.SubViewHoder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull SubViewHoder holder, final int position)
    {
        holder.title.setText(list.get(position).getTitle());
        Picasso.get().load(list.get(position).getImage()).into(holder.image);
        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(ctx,ProductMainActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                i.putExtra("cat_id",list.get(position).getId());
                ctx.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class SubViewHoder extends RecyclerView.ViewHolder
    {
        ImageView image;
        TextView title;
        CardView card;
        public SubViewHoder(@NonNull View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.item_subimg);
            title=itemView.findViewById(R.id.item_subtitle);
            card=itemView.findViewById(R.id.cardview3);
        }
    }
}
