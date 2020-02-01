package com.example.myhome;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
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

public class ProductDecActivity extends AppCompatActivity {

    private RecyclerView rv;
    private ProductDecAdapter productDecAdapter;
    ArrayList<ProductDecModal> list;
    String subcategory= "http://v1.api.nanocart.in/index.php/api/product/product_details";
    String addtocart="http://v1.api.nanocart.in/index.php/api/product/add_to_cart";
    int pro;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_dec);
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
        pro=i.getIntExtra("item_id",0);
        rv=(RecyclerView)findViewById(R.id.recyclerview9);
        loadproductdec();
    }

    public void loadproductdec()
    {
        final String item=Integer.toString(pro);
        System.out.println(item);
        final StringRequest str=new StringRequest(Request.Method.POST, subcategory, new Response.Listener<String>() {
            @Override
            public void onResponse(String response)
            {
                try
                {
                    JSONObject obj=new JSONObject(response);
                    if (obj.optInt("status")==1)
                    {
                        list=new ArrayList<>();
                        JSONObject obj1=obj.getJSONObject("details");
                        ProductDecModal modal=new ProductDecModal(
                                obj1.getInt("item_id"),
                                obj1.getString("product_name"),
                                obj1.getString("product_price"),
                                obj1.getString("product_desc"),
                                obj1.getString("cover_image")
                        );
                        modal.setItem_id(obj1.getInt("item_id"));
                        modal.setPro_dec(obj1.getString("product_desc"));
                        modal.setPro_image(obj1.getString("cover_image"));
                        modal.setPro_name(obj1.getString("product_name"));
                        modal.setPro_price(obj1.getString("product_price"));
                        list.add(modal);
                        JSONArray arry=obj1.getJSONArray("display_attributes");
                        for (int i=0;i<arry.length();i++)
                        {
                            JSONObject obj2=arry.getJSONObject(i);
                            modal.setDisplay(obj2.getString("attribute_name"));
                            modal.setDisplayatt(obj2.getString("option_name"));
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
            public void onErrorResponse(VolleyError error)
            {
                Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map=new HashMap<>();
                map.put("item_id",item);
                return map;
            }
        };
        RequestQueue rq= Volley.newRequestQueue(this);
        rq.add(str);
    }
    public void Recycler()
    {
        productDecAdapter=new ProductDecAdapter(this,list);
        productDecAdapter.notifyDataSetChanged();
        rv.setAdapter(productDecAdapter);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
    }

}
