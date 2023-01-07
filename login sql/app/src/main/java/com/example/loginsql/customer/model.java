package com.example.loginsql.customer;

public class model{

    public String g_tag;
    public  int  g_img;
    public  int p_imag;

    public model() {
    }

    public model(String g_tag, int g_img, int p_imag) {
        this.g_tag = g_tag;
        this.g_img = g_img;
        this.p_imag = p_imag;
    }

    public String getG_tag() {
        return g_tag;
    }

    public void setG_tag(String g_tag) {
        this.g_tag = g_tag;
    }

    public int getG_img() {
        return g_img;
    }

    public void setG_img(int g_img) {
        this.g_img = g_img;
    }

    public int getP_imag() {
        return p_imag;
    }

    public void setP_imag(int p_imag) {
        this.p_imag = p_imag;
    }
}
