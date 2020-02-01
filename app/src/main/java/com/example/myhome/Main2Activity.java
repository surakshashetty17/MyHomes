package com.example.myhome;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Main2Activity extends AppCompatActivity {

    String request_url ="http://v1.api.nanocart.in/index.php/api/home/screen";

    ArrayList<BannerModal> list;
    private BannerAdapter bannerAdapter;
    private RecyclerView rv;

    ArrayList<CategoryModal> clist;
    private CategoryAdapter categoryAdapter;
    private RecyclerView rv1;

    ArrayList<TopsellModal> tlist;
    private TopsellAdapter topsellAdapter;
    private RecyclerView rv2;

    ArrayList<ProductModal> plist;
    private ProductAdapter productAdapter;
    private RecyclerView rv3;

    ArrayList<ViewpageModal> vlist;
    private ViewPageAdapter viewPageAdapter;
    private RecyclerView rv4;
    //dot point

//    private  LinearLayout dotpoint;
//    private int current_position=0;
//    private  int Custom_page=0;
//    private Timer timer;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.drawable.ic_keyboard_arrow_left_black_24dp);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });



        //banner
        rv=(RecyclerView) findViewById(R.id.recyclerview1);
        loadbanner();

        //category
        rv1=(RecyclerView) findViewById(R.id.recyclerview2);
        loadCategory();

        //top seller
        rv2=(RecyclerView) findViewById(R.id.recycleriew3);
        loadTopseller();

        //top product
        rv3=(RecyclerView) findViewById(R.id.recyclerview4);
        loadproduct();

        //offers
        rv4=(RecyclerView) findViewById(R.id.recyclerview5);
        loadoffers();

    }
    //banner
    public  void loadbanner()
    {
        final StringRequest str=new StringRequest(Request.Method.GET, request_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response)
            {
                Log.d("strrrrr", ">>" + response);
                System.out.println(response);
                try {
                    JSONObject obj=new JSONObject(response);
                    System.out.println(obj);

                    if (obj.optInt("main_banner_status")==1)
                    {
                        list=new ArrayList<>();
                        JSONArray arry=obj.getJSONArray("main_banners");
                        for (int i=0;i<arry.length();i++)
                        {
                            BannerModal modal=new BannerModal();
                            JSONObject obj1=arry.getJSONObject(i);

                            modal.setImg(obj1.getString("icon"));
                            System.out.println(modal);
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
            public void onErrorResponse(VolleyError error)
            {
                Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue rq= Volley.newRequestQueue(this);

        int socketTimeout = 30000;
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        str.setRetryPolicy(policy);
        rq.add(str);
    }
    //banner
    public void Recycler()
    {
        bannerAdapter=new BannerAdapter(this,list);
        rv.setAdapter(bannerAdapter);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

    }

    //category
    public void loadCategory()
    {
        final StringRequest str=new StringRequest(Request.Method.GET, request_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("strrrrr",">>"+response);
                System.out.println(response);

                try
                {
                    JSONObject obj=new JSONObject(response);
                    System.out.println(obj);

                    if(obj.optInt("category_status")==1)
                    {
                        clist=new ArrayList<>();
                        JSONArray arry=obj.getJSONArray("categories");
                        for (int i=0;i<arry.length();i++)
                        {
                            CategoryModal mod=new CategoryModal();
                            JSONObject objs=arry.getJSONObject(i);
                            mod.setId(objs.getInt("cat_id"));
                            mod.setTitle(objs.getString("cat_name"));
                            mod.setImage(objs.getString("cat_icon"));
                            clist.add(mod);
                        }
                        Crecycler();
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
        });
        RequestQueue rq= Volley.newRequestQueue(this);

        int socketTimeout = 30000;
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        str.setRetryPolicy(policy);
        rq.add(str);
    }
    public void Crecycler()
    {
        categoryAdapter=new CategoryAdapter(this,clist);
        rv1.setAdapter(categoryAdapter);
        rv1.setHasFixedSize(true);
        StaggeredGridLayoutManager staggeredGridVertical=new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL);
        rv1.setLayoutManager(staggeredGridVertical);
    }

    //Top Sellers
    public void loadTopseller()
    {
        final StringRequest str=new StringRequest(Request.Method.GET, request_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d("strrrrr",">>"+response);
                System.out.println(response);

                try {
                    JSONObject obj = new JSONObject(response);
                    if (obj.optInt("top_sellers_status")==1)
                    {
                        tlist=new ArrayList<>();
                        JSONArray arry=obj.getJSONArray("top_sellers");
                        for (int i=0;i<arry.length();i++)
                        {
                            TopsellModal top=new TopsellModal();
                            JSONObject objs=arry.getJSONObject(i);
                            top.setTitle(objs.getString("business_name"));
                            top.setImg(objs.getString("logo"));
                            tlist.add(top);
                        }
                        Trecycler();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue rq= Volley.newRequestQueue(this);

        int socketTimeout = 30000;
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        str.setRetryPolicy(policy);
        rq.add(str);
    }
    public void Trecycler()
    {
        topsellAdapter=new TopsellAdapter(this,tlist);
        rv2.setAdapter(topsellAdapter);
        rv2.setHasFixedSize(true);
        rv2.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
    }


    //top product
    public void loadproduct()
    {
        final StringRequest str=new StringRequest(Request.Method.GET, request_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d("strrrrr",">>"+response);
                System.out.println(response);

                try
                {
                    JSONObject obj=new JSONObject(response);
                    System.out.println(obj);
                    if (obj.optInt("products_status")==1)
                    {
                        plist=new ArrayList<>();
                        JSONArray arry=obj.getJSONArray("products");
                        for (int i=0;i<arry.length();i++)
                        {
                            ProductModal product=new ProductModal();
                            JSONObject objs=arry.getJSONObject(i);
                            product.setId(objs.getInt("item_id"));
                            product.setName(objs.getString("product_name"));
                            product.setImage(objs.getString("cover_image"));
                            plist.add(product);
                        }
                        Precycler();
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

            }
        });
        RequestQueue rq= Volley.newRequestQueue(this);

        int socketTimeout = 30000;
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        str.setRetryPolicy(policy);
        rq.add(str);
    }
    public void Precycler()
    {
        productAdapter=new ProductAdapter(this,plist);
        rv3.setAdapter(productAdapter);
        rv3.setHasFixedSize(true);
        rv3.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
    }

    public void loadoffers()
    {
        final StringRequest str=new StringRequest(Request.Method.GET, request_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response)
            {
                Log.d("strrrrr",">>");
                System.out.println(response);

                try
                {
                    JSONObject obj=new JSONObject(response);
                    System.out.println(obj);
                    if (obj.optInt("offer_banner_status")==1)
                    {
                        vlist=new ArrayList<>();
                        JSONArray arry=obj.getJSONArray("offer_banners");
                        for (int i=0;i<arry.length();i++)
                        {
                            ViewpageModal modal=new ViewpageModal();
                            JSONObject objs=arry.getJSONObject(i);
                            modal.setImage(objs.getString("icon"));
                            vlist.add(modal);
                        }
                        orecycler();
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

            }
        });
        RequestQueue rq=Volley.newRequestQueue(this);

        int socketTimeout = 30000;
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        str.setRetryPolicy(policy);
        rq.add(str);
    }
    public void orecycler()
    {
        viewPageAdapter=new ViewPageAdapter(this,vlist);
        rv4.setAdapter(viewPageAdapter);
        rv4.setHasFixedSize(true);
        rv4.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        rv4.addItemDecoration(new CirclePagerIndicatorDecoration());
    }

//    private void createSlideShow() {
//        final Handler handler = new Handler();
//        final Runnable runnable = new Runnable() {
//            @Override
//            public void run() {
//                if (current_position == 3)
//                    current_position = 0;
//                vp.setCurrentItem(current_position++, true);
//            }
//        };
//        timer = new Timer();
//        timer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                handler.post(runnable);
//            }
//        }, 250, 3000);
//    }
//
//    private void dots(int currentSlidePosition) {
//        if (dotpoint.getChildCount() > 0)
//            dotpoint.removeAllViews();
//
//        ImageView dotsImage[] = new ImageView[3];
//        for (int i = 0; i < 3; i++) {
//            dotsImage[i] = new ImageView(this);
//            if (i == currentSlidePosition)
//
//                dotsImage[i].setImageDrawable(ContextCompat.getDrawable(this, R.drawable.dots));
//            else
//                dotsImage[i].setImageDrawable(ContextCompat.getDrawable(this, R.drawable.inactivedots));
//
//            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
//                    ViewGroup.LayoutParams.WRAP_CONTENT);
//            layoutParams.setMargins(4, 0, 4, 0);
//            dotpoint.addView(dotsImage[i], layoutParams);
//
//        }
//    }


}
