package com.example.myhome;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Sellerinfo extends AppCompatActivity {


    Button help,Report;
    RecyclerView rv;
    SellerAdapter sa;
    SellerModal[] sm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sellerinfo);
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.drawable.ic_keyboard_arrow_left_black_24dp);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        sm=new SellerModal[]
                {
                    new SellerModal("Jeans",R.drawable.pant),
                        new SellerModal("Shirts",R.drawable.tshirt),
                        new SellerModal("T-Shirts",R.drawable.shirt),
                        new SellerModal("Sportswear",R.drawable.sports),
                        new SellerModal("Shoes",R.drawable.shoe),
                        new SellerModal("Watches",R.drawable.watch),
                };
        sa=new SellerAdapter(this.sm);
        rv=(RecyclerView) findViewById(R.id.recycle);
        rv.setHasFixedSize(true);
        StaggeredGridLayoutManager sgm=new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);
        rv.setLayoutManager(sgm);
        rv.setAdapter(sa);

        help=(Button) findViewById(R.id.button2);
        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent i=new Intent(Sellerinfo.this,Main2Activity.class);
                startActivity(i);
            }
        });
        Report=(Button) findViewById(R.id.button3);
        Report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent i=new Intent(Sellerinfo.this,Main2Activity.class);
                startActivity(i);
            }
        });
    }
}
