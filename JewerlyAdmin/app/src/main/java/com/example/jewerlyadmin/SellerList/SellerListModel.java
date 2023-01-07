package com.example.jewerlyadmin.SellerList;

public class SellerListModel {
    private String SellerAddDate, SellerAddress, SellerBusinessName, SellerEmail, SellerGender, SellerMobileNo, SellerName, SellerId;

    public SellerListModel(String sellerAddDate, String sellerAddress, String sellerBusinessName, String sellerEmail, String sellerGender, String sellerMobileNo, String sellerName, String sellerId) {
        SellerAddDate = sellerAddDate;
        SellerAddress = sellerAddress;
        SellerBusinessName = sellerBusinessName;
        SellerEmail = sellerEmail;
        SellerGender = sellerGender;
        SellerMobileNo = sellerMobileNo;
        SellerName = sellerName;
        SellerId = sellerId;
    }

    public SellerListModel() {
    }

    public String getSellerAddDate() {
        return SellerAddDate;
    }

    public void setSellerAddDate(String sellerAddDate) {
        SellerAddDate = sellerAddDate;
    }

    public String getSellerAddress() {
        return SellerAddress;
    }

    public void setSellerAddress(String sellerAddress) {
        SellerAddress = sellerAddress;
    }

    public String getSellerBusinessName() {
        return SellerBusinessName;
    }

    public void setSellerBusinessName(String sellerBusinessName) {
        SellerBusinessName = sellerBusinessName;
    }

    public String getSellerEmail() {
        return SellerEmail;
    }

    public void setSellerEmail(String sellerEmail) {
        SellerEmail = sellerEmail;
    }

    public String getSellerGender() {
        return SellerGender;
    }

    public void setSellerGender(String sellerGender) {
        SellerGender = sellerGender;
    }

    public String getSellerMobileNo() {
        return SellerMobileNo;
    }

    public void setSellerMobileNo(String sellerMobileNo) {
        SellerMobileNo = sellerMobileNo;
    }

    public String getSellerName() {
        return SellerName;
    }

    public void setSellerName(String sellerName) {
        SellerName = sellerName;
    }

    public String getSellerId() {
        return SellerId;
    }

    public void setSellerId(String sellerId) {
        SellerId = sellerId;
    }
}

