/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quanlycuahangmaytinh.dbaccess;

import com.mycompany.quanlycuahangmaytinh.model.Customer;
import com.mycompany.quanlycuahangmaytinh.utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerAccess {
    
    //Lay danh sach cac khach hang
    public List<Customer> getAll() {

        List<Customer> list = new ArrayList<>();
        
        //Cau lenh SQL
        String sql = "SELECT * FROM CUSTOMERS"; 

        try (
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement pre = con.prepareStatement(sql);
            ResultSet rs = pre.executeQuery()
        ) {

            while (rs.next()) {

                Customer customer = new Customer();

                customer.setCustomerId(
                        rs.getInt("customer_id")
                );
                customer.setCustomerName(
                        rs.getString("customer_name")
                );
                customer.setPhoneNumber(
                        rs.getString("phone")
                );
                customer.setAddress(
                        rs.getString("address")
                );
                list.add(customer);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
    
    //Them khach hang
    public boolean insert(Customer customer) {

        String sql = "INSERT INTO CUSTOMERS "
                +"(customer_name, phone, address) " 
                + "VALUES (?, ?, ?)";

        try (
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement pre = con.prepareStatement(sql)
        ) {

            pre.setString(1, customer.getCustomerName());
            pre.setString(2, customer.getPhoneNumber());
            pre.setString(3, customer.getAddress());

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
    
    //Sua khach hang
    public boolean update(Customer customer) {

        String sql = "UPDATE CUSTOMERS SET "
                   + "customer_name = ?, " + "phone = ?, "
                   + "address = ? " + "WHERE customer_id = ?";

        try (
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement pre = con.prepareStatement(sql)
        ) {

            pre.setString(1, customer.getCustomerName() );
            pre.setString(2, customer.getPhoneNumber());
            pre.setString(3, customer.getAddress());
            pre.setInt(4, customer.getCustomerId());

            //Kiem tra
            int records = pre.executeUpdate();
            if (records > 0) {
                System.out.println("thanh cong");
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
    
    //Xoa khach hang
    public boolean delete(int customerId) {

        String sql = "DELETE FROM CUSTOMERS " + "WHERE customer_id = ?";

        try (
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement pre = con.prepareStatement(sql)
        ) {

            pre.setInt(1, customerId);

            //Kiem tra
            int records = pre.executeUpdate();
            if (records > 0) {
                System.out.println("thanh cong");
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
    
    //Tim kiem khach hang
    public List<Customer> search(String keyword) {

        List<Customer> list = new ArrayList<>();

        String sql = "SELECT customer_id, customer_name, " + "phone, address "
                   + "FROM CUSTOMERS "
                   + "WHERE customer_name LIKE ? "
                   + "OR phone LIKE ?";

        try (
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement pre = con.prepareStatement(sql)
        ) {

            pre.setString(1, "%" + keyword + "%");
            pre.setString(2, "%" + keyword + "%");

            try (ResultSet rs = pre.executeQuery()) {

                while (rs.next()) {
                    Customer customer = new Customer();

                    customer.setCustomerId(
                            rs.getInt("customer_id")
                    );
                    customer.setCustomerName(
                            rs.getString("customer_name")
                    );
                    customer.setPhoneNumber(
                            rs.getString("phone")
                    );
                    customer.setAddress(
                            rs.getString("address")
                    );

                    list.add(customer);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}
