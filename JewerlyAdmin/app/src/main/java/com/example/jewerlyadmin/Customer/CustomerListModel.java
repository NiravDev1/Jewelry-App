package com.example.jewerlyadmin.Customer;

public class CustomerListModel {
     private  String   customerEmail,customerId,customerMobile,customerName,customerPassword;

    public CustomerListModel() {
    }

    public CustomerListModel(String customerEmail, String customerId, String customerMobile, String customerName, String customerPassword) {
        this.customerEmail = customerEmail;
        this.customerId = customerId;
        this.customerMobile = customerMobile;
        this.customerName = customerName;
        this.customerPassword = customerPassword;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerMobile() {
        return customerMobile;
    }

    public void setCustomerMobile(String customerMobile) {
        this.customerMobile = customerMobile;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerPassword() {
        return customerPassword;
    }

    public void setCustomerPassword(String customerPassword) {
        this.customerPassword = customerPassword;
    }
}
