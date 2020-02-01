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

import java.lang.reflect.Constructor;
import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder>
{
    ArrayList<ProductModal> plist;
    Context ctx;
    public ProductAdapter(Context ctx,ArrayList<ProductModal> plist)
    {
        this.ctx=ctx;
        this.plist=plist;
    }
    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater lf=LayoutInflater.from(parent.getContext());
        View view=lf.inflate(R.layout.itemproduct,parent,false);
        ProductAdapter.ProductViewHolder vh=new ProductAdapter.ProductViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {

        holder.title.setText(plist.get(position).getName());
        Picasso.get().load(plist.get(position).getImage()).into(holder.img);
    }

    @Override
    public int getItemCount() {
        return plist.size();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder
    {
        TextView title;
        ImageView img;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.item_productName);
            img=itemView.findViewById(R.id.item_product);
        }
    }
}
