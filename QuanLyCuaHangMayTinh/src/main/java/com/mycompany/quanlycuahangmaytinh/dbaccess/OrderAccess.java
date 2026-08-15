/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quanlycuahangmaytinh.dbaccess;

import com.mycompany.quanlycuahangmaytinh.model.Order;
import com.mycompany.quanlycuahangmaytinh.utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderAccess {
    //Lay don hang
    public List<Order> getAll() {
        List<Order> list = new ArrayList<>();

        String sql = "SELECT * FROM ORDERS";

        try (
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement pre = con.prepareStatement(sql);
            ResultSet rs = pre.executeQuery()
        ) {

            while (rs.next()) {
                Order order = new Order();

                order.setOrderId(
                        rs.getInt("order_id")
                );
                order.setCustomerId(
                        rs.getInt("customer_id")
                );
                order.setUserId(
                        rs.getInt("user_id")
                );
                order.setOrderDate(
                        rs.getString("order_date")
                );
                order.setTotalAmount(
                    rs.getInt("total_amount")
                );
                list.add(order);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    //Them don hang
    public int insert(Order order) {

        String sql = "INSERT INTO ORDERS "
                   + "(customer_id, user_id, order_date, total_amount) "
                   + "VALUES (?, ?, ?, ?)";

        try (
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement pre = con.prepareStatement(sql);
        ) {

            pre.setInt(1, order.getCustomerId());
            pre.setInt(2, order.getUserId());
            pre.setString(3, order.getOrderDate());
            pre.setInt(4,order.getTotalAmount());

            //Kiem tra
            int records = pre.executeUpdate();
            if (records > 0) {
                System.out.println("Da them moi thanh cong");
                return order.getOrderId();     
            } else {
                System.out.println("That bai");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
        return -1;
    }
    
    //Cap nhat don hang
    public boolean update(Order order) {

        String sql = "UPDATE ORDERS SET "
                   + "customer_id = ?, " + "user_id = ?, "
                   + "order_date = ?, " + "total_amount = ? "
                   + "WHERE order_id = ?";

        try (
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement pre = con.prepareStatement(sql)
        ) {

            pre.setInt(1, order.getCustomerId());
            pre.setInt(2, order.getUserId());
            pre.setString(3, order.getOrderDate());
            pre.setInt(4, order.getTotalAmount());
            pre.setInt(5, order.getOrderId());

            //Kiem tra
            int records = pre.executeUpdate();
            if (records > 0) {
                System.out.println("Da them moi thanh cong");
                return true;     
            } else {
                System.out.println("That bai");
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    //Xoa don hang
    public boolean delete(int orderId) {

        String sql = "DELETE FROM ORDERS " + "WHERE order_id = ?";

        try (
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement pre = con.prepareStatement(sql)
        ) {
            pre.setInt(1, orderId);

            //Kiem tra
            int records = pre.executeUpdate();
            if (records > 0) {
                System.out.println("Da them moi thanh cong");
                return true;     
            } else {
                System.out.println("That bai");
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    //Tim don hang theo id
    public Order findById(int orderId) {

        String sql = "SELECT order_id, customer_id, user_id, "
                   + "order_date, total_amount "
                   + "FROM ORDERS "
                   + "WHERE order_id = ?";

        try (
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setInt(1, orderId);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    Order order = new Order();

                    order.setOrderId(
                            rs.getInt("order_id")
                    );
                    order.setCustomerId(
                            rs.getInt("customer_id")
                    );
                    order.setUserId(
                            rs.getInt("user_id")
                    );
                    order.setOrderDate(
                        rs.getString("order_date")
                    );
                    order.setTotalAmount(
                            rs.getInt("total_amount")
                    );
                    return order;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
