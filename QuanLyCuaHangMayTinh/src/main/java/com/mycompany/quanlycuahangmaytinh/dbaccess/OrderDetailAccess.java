/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quanlycuahangmaytinh.dbaccess;

import com.mycompany.quanlycuahangmaytinh.model.OrderDetail;
import com.mycompany.quanlycuahangmaytinh.utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailAccess {
    //Lay chi tiet don hang
    public List<OrderDetail> getAll() {

        List<OrderDetail> list = new ArrayList<>();

        String sql = "SELECT * FROM ORDER_DETAILS";

        try (
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement pre = con.prepareStatement(sql);
            ResultSet rs = pre.executeQuery()
        ) {
            while (rs.next()) {
                OrderDetail detail = new OrderDetail();
                
                detail.setOrderDetailId(
                        rs.getInt("order_detail_id")
                );
                detail.setOrderId(
                        rs.getInt("order_id")
                );
                detail.setProductId(
                        rs.getInt("product_id")
                );
                detail.setQuantity(
                        rs.getInt("quantity")
                );
                detail.setUnitPrice(
                        rs.getInt("unit_price")
                );
                detail.setSubtotal(
                        rs.getInt("subtotal")
                );
                list.add(detail);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    //Lay chi tiet theo Id
    public List<OrderDetail> getByOrderId(int orderId) {

        List<OrderDetail> list = new ArrayList<>();

        String sql = "SELECT order_detail_id, order_id, "
                   + "product_id, quantity, unit_price, subtotal "
                   + "FROM ORDER_DETAILS "
                   + "WHERE order_id = ?";

        try (
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement pre = con.prepareStatement(sql)
        ) {

            pre.setInt(1, orderId);

            try (ResultSet rs = pre.executeQuery()) {
                while (rs.next()) {
                    OrderDetail detail = new OrderDetail();
                    
                    detail.setOrderDetailId(
                            rs.getInt("order_detail_id")
                    );
                    detail.setOrderId(
                            rs.getInt("order_id")
                    );
                    detail.setProductId(
                            rs.getInt("product_id")
                    );
                    detail.setQuantity(
                            rs.getInt("quantity")
                    );
                    detail.setUnitPrice(
                            rs.getInt("unit_price")
                    );

                    detail.setSubtotal(
                            rs.getInt("subtotal")
                    );

                    list.add(detail);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    //Them don hang
    public boolean insert(OrderDetail detail) {

        String sql = "INSERT INTO ORDER_DETAILS "
                   + "(order_id, product_id, quantity, "
                   + "unit_price, subtotal) "
                   + "VALUES (?, ?, ?, ?, ?)";

        try (
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement pre = con.prepareStatement(sql)
        ) {
            pre.setInt(1, detail.getOrderId());
            pre.setInt(2, detail.getProductId());
            pre.setInt(3, detail.getQuantity());
            pre.setInt(4, detail.getUnitPrice());
            pre.setInt(5, detail.getSubtotal());

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
    
    //Sua don hang
    public boolean update(OrderDetail detail) {

        String sql = "UPDATE ORDER_DETAILS SET "
                   + "product_id = ?, " + "quantity = ?, "
                   + "unit_price = ?, " + "subtotal = ? "
                   + "WHERE order_detail_id = ?";

        try (
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement pre = con.prepareStatement(sql)
        ) {

            pre.setInt(1, detail.getProductId());
            pre.setInt(2, detail.getQuantity());
            pre.setInt(3, detail.getUnitPrice());
            pre.setInt(4, detail.getSubtotal());
            pre.setInt(5, detail.getOrderDetailId());

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
     public boolean delete(int orderDetailId) {

        String sql = "DELETE FROM ORDER_DETAILS "
              + "WHERE order_detail_id = ?";

        try (
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement pre = con.prepareStatement(sql)
        ) {

            pre.setInt(1, orderDetailId);

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
}
