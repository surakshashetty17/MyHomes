package com.example.myhome;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
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

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private MyAdapter myAdapter;
    RecyclerView rv;
    ViewPager vp;
    ImageAdapter ad;
    MyListData[] myListData;
    String request_url ="http://v1.api.nanocart.in/index.php/api/home/screen";
    ArrayList<MyListData> lst;

    //view page dots
    private LinearLayout dotpoint;
    private int current_position = 0;//used inside createSildeshow function
    private int Custom_page = 0;
    private Timer timer;//used inside createSildeshow function

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.drawable.ic_keyboard_arrow_left_black_24dp);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

//        myListData=new MyListData[]
//        {
//        new MyListData("Cart", R.drawable.cart),
//        new MyListData("Chatting", R.drawable.chatting),
//        new MyListData("support", R.drawable.support),
//        new MyListData("voucher", R.drawable.voucher),
//                new MyListData("Cart", R.drawable.cart),
//                new MyListData("Chatting", R.drawable.chatting),
//                new MyListData("support", R.drawable.support),
//                new MyListData("voucher", R.drawable.voucher)
//        };

        rv=(RecyclerView) findViewById(R.id.recyclerview);
        loadShop();
//        myAdapter=new MyAdapter(this.myListData);
//        rv.setHasFixedSize(true);
////        rv.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
//        StaggeredGridLayoutManager staggeredGridVertical=new
//                StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL);
//        rv.setLayoutManager(staggeredGridVertical);
//        rv.setAdapter(myAdapter);
//
        vp=(ViewPager) findViewById(R.id.viewpage);
        ad=new ImageAdapter(this);
        vp.setAdapter(ad);

        //view page dots
        dotpoint=(LinearLayout) findViewById(R.id.dot);
        ad=new ImageAdapter(this);
        vp.setAdapter(ad);
        createSlideShow();
        dots(Custom_page++);
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                if (Custom_page > 2)
                    Custom_page = 0;
                dots(Custom_page++);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });


    }
    public void loadShop()
    {
        final StringRequest str=new StringRequest(Request.Method.GET, request_url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("strrrrr",">>"+response);
                        System.out.println(response);

                        try {
                            JSONObject obj=new JSONObject(response);
                            System.out.println(obj);
                            if (obj.optInt("category_status")==1)
                            {
                                lst=new ArrayList<>();
                                JSONArray ja=obj.getJSONArray("categories");
                                for (int i=0;i<ja.length();i++)
                                {
                                    MyListData ld=new MyListData();
                                    JSONObject objs=ja.getJSONObject(i);
                                    ld.setTitle(objs.getString("cat_name"));
                                    ld.setImg(objs.getString("cat_icon"));
                                    lst.add(ld);
                                }
                                Recycler();
                            }

                        } catch (JSONException e) {
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
    public void Recycler()
    {
        myAdapter=new MyAdapter(this.lst);
        rv.setHasFixedSize(true);
        StaggeredGridLayoutManager staggeredGridVertical=new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL);
        rv.setLayoutManager(staggeredGridVertical);
        rv.setAdapter(myAdapter);
    }
    private void createSlideShow() {
        final Handler handler = new Handler();
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (current_position == 3)
                    current_position = 0;
                vp.setCurrentItem(current_position++, true);
            }
        };
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(runnable);
            }
        }, 250, 3000);
    }
    private void dots(int currentSlidePosition) {
        if (dotpoint.getChildCount() > 0)
            dotpoint.removeAllViews();

        ImageView dotsImage[] = new ImageView[3];
        for (int i = 0; i < 3; i++) {
            dotsImage[i] = new ImageView(this);
            if (i == currentSlidePosition)

                dotsImage[i].setImageDrawable(ContextCompat.getDrawable(this, R.drawable.dots));
            else
                dotsImage[i].setImageDrawable(ContextCompat.getDrawable(this, R.drawable.inactivedots));

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(4, 0, 4, 0);
            dotpoint.addView(dotsImage[i], layoutParams);

        }
    }

    public void click(View view) {
        Intent i=new Intent(MainActivity.this,Sellerinfo.class);
        startActivity(i);
    }
}
