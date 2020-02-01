package com.example.myhome;

public class SellerModal
{
    private  String title;
    private  int img;

    public SellerModal(String title,int img)
    {
        this.title=title;
        this.img=img;
    }

    public int getImg()
    {
        return img;
    }

    public String getTitle()
    {
        return title;
    }

    public void setImg(int img)
    {
        this.img = img;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }
}
