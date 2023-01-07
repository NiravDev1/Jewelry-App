package com.example.loginsql.SellerDashbord;

import java.util.ArrayList;

public class SellerProductModel {
    private String ProductName, ProductPrice, ProductDiscout, ProductDetails, ProductImage, ProductQuantity,ProductDiscoutPrice;

    public SellerProductModel() {
    }

    public SellerProductModel(String productName, String productPrice, String productDiscout, String productDetails, String productImage, String productQuantity, String productDiscoutPrice) {
        ProductName = productName;
        ProductPrice = productPrice;
        ProductDiscout = productDiscout;
        ProductDetails = productDetails;
        ProductImage = productImage;
        ProductQuantity = productQuantity;
        ProductDiscoutPrice = productDiscoutPrice;

    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public String getProductPrice() {
        return ProductPrice;
    }

    public void setProductPrice(String productPrice) {
        ProductPrice = productPrice;
    }

    public String getProductDiscout() {
        return ProductDiscout;
    }

    public void setProductDiscout(String productDiscout) {
        ProductDiscout = productDiscout;
    }

    public String getProductDetails() {
        return ProductDetails;
    }

    public void setProductDetails(String productDetails) {
        ProductDetails = productDetails;
    }

    public String getProductImage() {
        return ProductImage;
    }

    public void setProductImage(String productImage) {
        ProductImage = productImage;
    }

    public String getProductQuantity() {
        return ProductQuantity;
    }

    public void setProductQuantity(String productQuantity) {
        ProductQuantity = productQuantity;
    }

    public String getProductDiscoutPrice() {
        return ProductDiscoutPrice;
    }

    public void setProductDiscoutPrice(String productDiscoutPrice) {
        ProductDiscoutPrice = productDiscoutPrice;
    }
}
