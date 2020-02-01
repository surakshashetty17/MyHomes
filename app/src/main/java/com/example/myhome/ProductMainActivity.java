package com.example.myhome;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ProductMainActivity extends AppCompatActivity {

    ArrayList<ProductMainModal> list;
    ProductMainAdapter productMainAdapter;
    RecyclerView rv;
    int item;
    String subcategory= "http://v1.api.nanocart.in/index.php/api/category/sub_cat_pro";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_main);
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.drawable.ic_keyboard_arrow_left_black_24dp);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();

            }
        });
        Intent i=getIntent();
        item=i.getIntExtra("cat_id",0);
        rv=(RecyclerView) findViewById(R.id.recycler_view8);
        loadproduct();
    }
    public void loadproduct()
    {
        final String catid=Integer.toString(item);
        System.out.println(catid);
        final StringRequest str=new StringRequest(Request.Method.POST, subcategory, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d("strrrr",">>"+response);
                System.out.println(response);

                try
                {
                    JSONObject obj=new JSONObject(response);

                    if (obj.optInt("pro_status")==1)
                    {
                        list=new ArrayList<>();
                        JSONArray arry=obj.getJSONArray("products");
                        for (int i=0;i<arry.length();i++)
                        {
                            JSONObject objpro=arry.getJSONObject(i);
                            ProductMainModal modal=new ProductMainModal();
                            modal.setItem_id(objpro.getInt("item_id"));
                            modal.setPro_img(objpro.getString("cover_image"));
                            modal.setPro_name(objpro.getString("product_name"));
                            modal.setPro_price(objpro.getString("product_price"));
                            list.add(modal);
                        }
                        Recycler();
                    }
                }
                catch (JSONException e)
                {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map=new HashMap<>();
                map.put("cat_id",catid);
                return map;
            }
        };
        RequestQueue rq= Volley.newRequestQueue(this);
        rq.add(str);
    }
    public void Recycler()
    {
        productMainAdapter=new ProductMainAdapter(this,list);
        productMainAdapter.notifyDataSetChanged();
        rv.setAdapter(productMainAdapter);
        rv.setHasFixedSize(true);
        StaggeredGridLayoutManager staggeredGridLayoutManager=new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        rv.setLayoutManager(staggeredGridLayoutManager);

    }
}
