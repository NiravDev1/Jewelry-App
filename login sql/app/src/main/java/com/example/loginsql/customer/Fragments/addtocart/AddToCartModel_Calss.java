package com.example.loginsql.customer.Fragments.addtocart;

public class AddToCartModel_Calss {
    private  String ProductImage,ProductName,productDiscoutPrice;

    public AddToCartModel_Calss() {
    }

    public AddToCartModel_Calss(String productImage, String productName, String productDiscoutPrice) {
        ProductImage = productImage;
        ProductName = productName;
        this.productDiscoutPrice = productDiscoutPrice;
    }

    public String getProductImage() {
        return ProductImage;
    }

    public void setProductImage(String productImage) {
        ProductImage = productImage;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public String getProductDiscoutPrice() {
        return productDiscoutPrice;
    }

    public void setProductDiscoutPrice(String productDiscoutPrice) {
        this.productDiscoutPrice = productDiscoutPrice;
    }
}
