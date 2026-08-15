/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quanlycuahangmaytinh.model;


public class Customer {
    private int customerId;
    private String customerName;
    private String phoneNumber;
    private String address;
    
    //Constructor
    public Customer() {
    }
    
    public Customer(int Id, String name, String phone, String address) {
        this.customerId = Id;
        this.customerName = name;
        this.phoneNumber = phone;
        this.address = address;
    }
    
    //Getter
    public int getCustomerId() {
        return this.customerId;
    }
    public String getCustomerName() {
        return this.customerName;
    }
    public String getPhoneNumber() {
        return this.phoneNumber;
    }
    public String getAddress() {
        return this.address;
    }
    
    //Setter
    public void setCustomerId(int Id) {
        this.customerId = Id;
    }
    public void setCustomerName(String name) {
        this.customerName = name;
    }
    public void setPhoneNumber(String phone) {
        this.phoneNumber = phone;
    }
    public void setAddress(String adr) {
        this.address = adr;
    }
}
