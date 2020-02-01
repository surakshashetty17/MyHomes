package com.example.myhome;

import android.content.Context;
import android.content.Intent;
import android.icu.text.Transliterator;
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

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>
{
    private ArrayList<CategoryModal> list;
    private Context ctx;

    public CategoryAdapter(Context ctx,ArrayList<CategoryModal> list)
    {
        this.ctx=ctx;
        this.list=list;
    }
    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater lf=LayoutInflater.from(parent.getContext());
        View view=lf.inflate(R.layout.item_data,parent,false);
        CategoryAdapter.CategoryViewHolder vh=new CategoryAdapter.CategoryViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, final int position)
    {
        holder.title.setText(list.get(position).getTitle());
        Picasso.get().load(list.get(position).getImage()).into(holder.image);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(ctx,SubCategoryActivity.class);
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

    public class CategoryViewHolder extends RecyclerView.ViewHolder
    {
        TextView title;
        ImageView image;
        CardView cardView;

        public CategoryViewHolder(@NonNull View itemView)
        {
            super(itemView);
            title=(TextView)itemView.findViewById(R.id.categoryName);
            image=(ImageView)itemView.findViewById(R.id.categoryImg);
            cardView=(CardView)itemView.findViewById(R.id.cardView1);
        }
    }
}
