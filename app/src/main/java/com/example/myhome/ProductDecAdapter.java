package com.example.myhome;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.squareup.picasso.Picasso;
import com.varunest.sparkbutton.SparkButton;

import java.util.ArrayList;

public class ProductDecAdapter extends RecyclerView.Adapter<ProductDecAdapter.ViewHolder> {

    RecyclerView rv;
    ArrayList<ProductDecModal> list;
    Context ctx;
    public ProductDecAdapter(Context ctx,ArrayList<ProductDecModal> list)
    {
        this.ctx=ctx;
        this.list=list;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ProductDecAdapter.ViewHolder(LayoutInflater.from(ctx).inflate(R.layout.productview,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.name.setText(list.get(position).getPro_name());
        holder.price.setText(list.get(position).getPro_price());
        holder.desc.setText(list.get(position).getPro_dec());
        Picasso.get().load(list.get(position).getPro_image()).into(holder.image);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name,price,desc;
        ImageView image;
        Button cart,buy;
        SparkButton wish;
        ElegantNumberButton button;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name=(TextView)itemView.findViewById(R.id.proname1);
            price=(TextView)itemView.findViewById(R.id.proprice);
            desc=(TextView)itemView.findViewById(R.id.desc);
            image=(ImageView)itemView.findViewById(R.id.proimage);
            cart=(Button)itemView.findViewById(R.id.button11);
            buy=(Button)itemView.findViewById(R.id.button22);
            wish=(SparkButton)itemView.findViewById(R.id.wish);
            button=(ElegantNumberButton)itemView.findViewById(R.id.proquantity);
        }
    }
}
