package com.example.myhome;

public class ProductDecModal {
    public int item_id;
    public String pro_name;
    public String pro_image;
    public String pro_dec;
    public String pro_price;
    public String proquqntity;
    public String display;
    public String displayatt;

    public ProductDecModal(int item_id, String product_name, String product_price, String product_desc, String pro_image)
    {
        this.item_id=item_id;
        this.pro_dec=product_desc;
        this.pro_name=product_name;
        this.pro_price=product_price;
        this.pro_image=pro_image;
    }

    public int getItem_id() {
        return item_id;
    }

    public void setItem_id(int item_id) {
        this.item_id = item_id;
    }

    public String getPro_name() {
        return pro_name;
    }

    public void setPro_name(String pro_name) {
        this.pro_name = pro_name;
    }

    public String getPro_image() {
        return pro_image;
    }

    public void setPro_image(String pro_image) {
        this.pro_image = pro_image;
    }

    public String getPro_dec() {
        return pro_dec;
    }

    public void setPro_dec(String pro_dec) {
        this.pro_dec = pro_dec;
    }

    public String getPro_price() {
        return pro_price;
    }

    public void setPro_price(String pro_price) {
        this.pro_price = pro_price;
    }

    public String getProquqntity() {
        return proquqntity;
    }

    public void setProquqntity(String proquqntity) {
        this.proquqntity = proquqntity;
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public String getDisplayatt() {
        return displayatt;
    }

    public void setDisplayatt(String displayatt) {
        this.displayatt = displayatt;
    }
}
