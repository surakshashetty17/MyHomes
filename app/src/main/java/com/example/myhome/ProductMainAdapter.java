package com.example.myhome;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;
import com.varunest.sparkbutton.SparkButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ProductMainAdapter extends RecyclerView.Adapter<ProductMainAdapter.ViewHolder>
{
    ArrayList<ProductMainModal> list;
    String addtoWish = "http://v1.api.nanocart.in/index.php/api/wishlist/add_to_wishlist";
    String removecart= "http://v1.api.nanocart.in/index.php/api/wishlist/remove_product";
    Context ctx;
    public ProductMainAdapter(Context ctx,ArrayList<ProductMainModal> list)
    {
        this.ctx=ctx;
        this.list=list;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view;
//        LayoutInflater mInflater = LayoutInflater.from(ctx);
//        view = mInflater.inflate(R.layout.product_item, parent, false);
//        return new ProductMainAdapter.ViewHolder(view);
        return new ProductMainAdapter.ViewHolder(LayoutInflater.from(ctx).inflate(R.layout.product_item,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        holder.name.setText(list.get(position).getPro_name());
        holder.price.setText(list.get(position).getPro_price());
        Picasso.get().load(list.get(position).getPro_img()).into(holder.image);
        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(ctx,ProductDecActivity.class);
                i.putExtra("item_id",list.get(position).getItem_id());
                ctx.startActivity(i);
            }
        });
        holder.wish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.wish.isChecked()==false)
                {
                    holder.wish.setChecked(false);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            holder.wish.setChecked(true);
                            holder.wish.playAnimation();
                        }
                    },3000);
                    addtowishlist(list.get(position).getItem_id());
                }
                else
                {
                    holder.wish.setChecked(true);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            holder.wish.setChecked(false);
                            holder.wish.playAnimation();
                        }
                    },3000);
                    removefromwishlist(list.get(position).getItem_id());
                }
            }
        });
    }
    public void addtowishlist(int item)
    {
        final String catid=Integer.toString(item);
        System.out.println(catid);
        final StringRequest str=new StringRequest(Request.Method.POST, addtoWish, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject obj=new JSONObject(response);
                    System.out.println(response);

                    if (obj.optInt("status")==1)
                    {
                        Toast.makeText(ctx,"message",Toast.LENGTH_SHORT).show();
                    }
                }
                catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ctx,error.getMessage(),Toast.LENGTH_LONG);
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map=new HashMap<>();
                map.put("item",catid);
                return map;
            }
        };
        RequestQueue rq= Volley.newRequestQueue(ctx);
        rq.add(str);
    }
    public void removefromwishlist(int item)
    {
        final String catid=Integer.toString(item);
        final StringRequest str=new StringRequest(Request.Method.POST, removecart, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject obj=new JSONObject(response);
                    System.out.println(response);

                    if (obj.optInt("status")==1)
                    {
                        Toast.makeText(ctx,"message",Toast.LENGTH_SHORT).show();
                    }
                }
                catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ctx, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map=new HashMap<>();
                map.put("item",catid);
                System.out.println(map);
                return map;
            }
        };
        RequestQueue rq=Volley.newRequestQueue(ctx);
        rq.add(str);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView name,price;
        ImageView image;
        CardView card;
        SparkButton wish;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name=(TextView)itemView.findViewById(R.id.proname);
            price=(TextView)itemView.findViewById(R.id.rupee);
            image=(ImageView)itemView.findViewById(R.id.proimage);
            card=(CardView)itemView.findViewById(R.id.procard);
            wish=(SparkButton)itemView.findViewById(R.id.imagewhish);


        }
    }
}
