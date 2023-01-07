package com.example.loginsql.SellerDashbord;

public class SellerProductDataholder {
    private  String PSname,PSdetails,PSprice,PSdiscountPrice,PSdiscout,PSImage;


    public SellerProductDataholder(String PSname, String PSdetails, String PSprice, String PSdiscountPrice, String PSdiscout, String PSImage) {
        this.PSname = PSname;
        this.PSdetails = PSdetails;
        this.PSprice = PSprice;
        this.PSdiscountPrice = PSdiscountPrice;
        this.PSdiscout = PSdiscout;
        this.PSImage = PSImage;
    }

    public SellerProductDataholder() {
    }

    public String getPSname() {
        return PSname;
    }

    public void setPSname(String PSname) {
        this.PSname = PSname;
    }

    public String getPSdetails() {
        return PSdetails;
    }

    public void setPSdetails(String PSdetails) {
        this.PSdetails = PSdetails;
    }

    public String getPSprice() {
        return PSprice;
    }

    public void setPSprice(String PSprice) {
        this.PSprice = PSprice;
    }

    public String getPSdiscountPrice() {
        return PSdiscountPrice;
    }

    public void setPSdiscountPrice(String PSdiscountPrice) {
        this.PSdiscountPrice = PSdiscountPrice;
    }

    public String getPSdiscout() {
        return PSdiscout;
    }

    public void setPSdiscout(String PSdiscout) {
        this.PSdiscout = PSdiscout;
    }

    public String getPSImage() {
        return PSImage;
    }

    public void setPSImage(String PSImage) {
        this.PSImage = PSImage;
    }
}
