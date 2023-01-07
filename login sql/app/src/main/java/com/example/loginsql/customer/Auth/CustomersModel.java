package com.example.loginsql.customer.Auth;

public class CustomersModel {
    private  String  CustomerId,CustomerName,CustomerMobile,CustomerEmail,CustomerPassword;

    public CustomersModel(String customerId, String customerName, String customerMobile, String customerEmail, String customerPassword) {
        CustomerId = customerId;
        CustomerName = customerName;
        CustomerMobile = customerMobile;
        CustomerEmail = customerEmail;
        CustomerPassword = customerPassword;
    }

    public CustomersModel() {
    }

    public String getCustomerId() {
        return CustomerId;
    }

    public void setCustomerId(String customerId) {
        CustomerId = customerId;
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String customerName) {
        CustomerName = customerName;
    }

    public String getCustomerMobile() {
        return CustomerMobile;
    }

    public void setCustomerMobile(String customerMobile) {
        CustomerMobile = customerMobile;
    }

    public String getCustomerEmail() {
        return CustomerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        CustomerEmail = customerEmail;
    }

    public String getCustomerPassword() {
        return CustomerPassword;
    }

    public void setCustomerPassword(String customerPassword) {
        CustomerPassword = customerPassword;
    }
}
