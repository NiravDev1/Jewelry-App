package com.example.jewerlyadmin.ProductsList;

public class ProductModel {

private String  productDetails,productDiscout,productDiscoutPrice,productImage,productName,productPrice,productQuantity,sellerName,productAddDatetime;

    public ProductModel(String productDetails, String productDiscout, String productDiscoutPrice, String productImage, String productName, String productPrice, String productQuantity, String sellerName, String productAddDatetime) {
        this.productDetails = productDetails;
        this.productDiscout = productDiscout;
        this.productDiscoutPrice = productDiscoutPrice;
        this.productImage = productImage;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productQuantity = productQuantity;
        this.sellerName = sellerName;
        this.productAddDatetime = productAddDatetime;
    }

    public ProductModel() {
    }

    public String getProductDetails() {
        return productDetails;
    }

    public void setProductDetails(String productDetails) {
        this.productDetails = productDetails;
    }

    public String getProductDiscout() {
        return productDiscout;
    }

    public void setProductDiscout(String productDiscout) {
        this.productDiscout = productDiscout;
    }

    public String getProductDiscoutPrice() {
        return productDiscoutPrice;
    }

    public void setProductDiscoutPrice(String productDiscoutPrice) {
        this.productDiscoutPrice = productDiscoutPrice;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(String productQuantity) {
        this.productQuantity = productQuantity;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public String getProductAddDatetime() {
        return productAddDatetime;
    }

    public void setProductAddDatetime(String productAddDatetime) {
        this.productAddDatetime = productAddDatetime;
    }
}
