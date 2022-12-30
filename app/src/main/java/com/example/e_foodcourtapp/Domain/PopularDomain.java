package com.example.e_foodcourtapp.Domain;

import java.io.Serializable;

public class PopularDomain implements Serializable {
    private String pic;
    private String title;
    private String desc;
    private Double fee;
    private int numberInCart;

    public PopularDomain(String pic, String title, String desc, Double fee) {
        this.pic = pic;
        this.title = title;
        this.desc = desc;
        this.fee = fee;
    }

    public PopularDomain(String pic, String title, String desc, Double fee, int numberInCart) {
        this.pic = pic;
        this.title = title;
        this.desc = desc;
        this.fee = fee;
        this.numberInCart = numberInCart;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Double getFee() {
        return fee;
    }

    public void setFee(Double fee) {
        this.fee = fee;
    }

    public int getNumberInCart() {
        return numberInCart;
    }

    public void setNumberInCart(int numberInCart) {
        this.numberInCart = numberInCart;
    }
}
