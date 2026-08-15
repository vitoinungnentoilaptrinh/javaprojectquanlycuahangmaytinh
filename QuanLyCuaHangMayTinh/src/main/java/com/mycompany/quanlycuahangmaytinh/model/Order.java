/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quanlycuahangmaytinh.model;

/**
 *
 * @author nanik
 */
public class Order {

    private int orderId;
    private int customerId;
    private int userId;
    private String orderDate;
    private int totalAmount;

    public Order() {
    }

    public Order(int orderId, int customerId, int userId, String orderDate,
            int totalAmount) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.userId = userId;
        this.orderDate = orderDate;
        this.totalAmount = totalAmount;
    }
    //Getter and Setter
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }
}
