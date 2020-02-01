package com.example.myhome;

import java.lang.reflect.Constructor;

public class MyListData
{
    private String title;
    private String img;
//    public MyListData(String title,int img)
//    {
//        this.title=title;
//        this.img=img;
//    }
    public String getTitle()
    {
        return title;
    }
    public void setTitle(String title)
    {
        this.title=title;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
