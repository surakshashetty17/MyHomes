package com.example.myhome;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class ImageAdapter extends PagerAdapter
{
    Context ctx;
    public ImageAdapter(Context ctx)
    {
        this.ctx=ctx;
    }

    private  int sliderImageID[]=new int[]
            {
                R.drawable.cart,R.drawable.chatting,R.drawable.voucher
            };


    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==((ImageView)object);
    }

    @Override

    public Object instantiateItem(ViewGroup container, int position)
    {
        ImageView iv=new ImageView(ctx);
        iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
        iv.setImageResource(sliderImageID[position]);
        ((ViewPager)container).addView(iv,0);
        return iv;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        ((ViewPager)container).removeView((ImageView)object);
    }
    @Override
    public int getCount() {
        return sliderImageID.length;
    }
}
