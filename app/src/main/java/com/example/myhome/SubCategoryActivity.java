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

public class SubCategoryActivity extends AppCompatActivity {


    ArrayList<SubCategoryModal> list;
    private RecyclerView rv;
    private SubCategoryAdapter subCategoryAdapter;

    String category="http://v1.api.nanocart.in/index.php/api/category/sub_cat_pro";

    int j;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_category);
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.drawable.ic_keyboard_arrow_left_black_24dp);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        Intent intent = getIntent();
        j = intent.getIntExtra("cat_id",0);
        rv=(RecyclerView) findViewById(R.id.recyclerview10);
        loadsubcategory();
    }
    public void loadsubcategory()
    {
        final String subcategory=Integer.toString(j);
        System.out.println(subcategory);

        final StringRequest str=new StringRequest(Request.Method.POST, category, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d("strrrrr",">>"+response);
                System.out.println(response);

                try
                {
                    JSONObject obj=new JSONObject(response);
                    System.out.println(obj);

                    if (obj.optInt("cat_status")==1)
                    {
                        list=new ArrayList<>();
                        JSONArray array=obj.getJSONArray("categories");
                        for (int i=0;i<array.length();i++)
                        {
                            SubCategoryModal modal=new SubCategoryModal();
                            JSONObject obj1=array.getJSONObject(i);
                            modal.setId(obj1.getInt("cat_id"));
                            modal.setTitle(obj1.getString("cat_name"));
                            modal.setImage(obj1.getString("cat_icon"));
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
                Map<String, String> params = new HashMap<>();
                params.put("cat_id", subcategory);
                //               params.put("user_id",userid);
                return params;
            }
        };;

        RequestQueue rq= Volley.newRequestQueue(this);
        rq.add(str);

    }
    public  void Recycler()
    {
        subCategoryAdapter=new SubCategoryAdapter(this,list);
        rv.setAdapter(subCategoryAdapter);
        rv.setHasFixedSize(true);
        StaggeredGridLayoutManager sgd=new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);
        rv.setLayoutManager(sgd);
    }
}
