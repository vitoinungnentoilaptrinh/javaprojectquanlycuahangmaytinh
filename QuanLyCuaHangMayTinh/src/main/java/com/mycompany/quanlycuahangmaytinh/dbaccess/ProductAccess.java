/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quanlycuahangmaytinh.dbaccess;

import com.mycompany.quanlycuahangmaytinh.model.Product;
import com.mycompany.quanlycuahangmaytinh.utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductAccess {
    //lay danh sach san pham
    public List<Product> getAll() {

        List<Product> list = new ArrayList<>();
        //Cau lenh SQL
        String sql = "SELECT * FROM PRODUCTS";

        try (
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement pre = con.prepareStatement(sql);
            ResultSet rs = pre.executeQuery()
        ) {

            while (rs.next()) {

                Product product = new Product();
                
                product.setProductId(
                        rs.getInt("product_id")
                );
                product.setProductName(
                        rs.getString("product_name")
                );
                product.setCategoryId(
                        rs.getInt("category_id")
                );
                product.setBrand(
                        rs.getString("brand")
                );

                product.setState(
                        rs.getString("state")
                );
                product.setPurchasePrice(
                        rs.getInt("purchase_price")
                );

                product.setSellingPrice(
                        rs.getInt("selling_price")
                );
                product.setQuantity(
                        rs.getInt("quantity")
                );
                product.setDescription(
                        rs.getString("description")
                );
                product.setStatus(
                        rs.getString("status")
                );
                //Them vao list
                list.add(product);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
    
    //Them san pham
    public boolean insert(Product product) {
        //Cau lenh SQL
        String sql = "INSERT INTO PRODUCTS "
                   + "(product_name, category_id, brand, "
                   + "state, purchase_price, selling_price, "
                   + "quantity, description, status) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement pre = con.prepareStatement(sql)
        ) {
            int index = 0;
            
            //Truyen du lieu cho prepared statement
            pre.setString(++index, product.getProductName());
            pre.setInt(++index, product.getCategoryId());
            pre.setString(++index, product.getBrand());
            pre.setString(++index, product.getState());
            pre.setInt(++index, product.getPurchasePrice());
            pre.setInt(++index, product.getSellingPrice());
            pre.setInt(++index, product.getQuantity());
            pre.setString(++index, product.getDescription());
            pre.setString(++index,product.getStatus());

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
    
    //Sua san pham
    public boolean update(Product product) {
        //Tao cau lenh SQL
        String sql = "UPDATE PRODUCTS SET "
                   + "product_name = ?," + "category_id = ?, " + "brand = ?, " 
                   + "state = ?, " + "purchase_price = ?, " + "selling_price = ?, "
                   + "quantity = ?, " + "description = ?, " + "status = ? "
                   + "WHERE product_id = ?";

        try (
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement pre = con.prepareStatement(sql)
        ) {
            //Truyen du lieu cho pre
            pre.setString(1, product.getProductName());
            pre.setInt(2, product.getCategoryId());
            pre.setString(3, product.getBrand());
            pre.setString(4, product.getState());
            pre.setInt(5, product.getPurchasePrice());
            pre.setInt(6, product.getSellingPrice());
            pre.setInt(7, product.getQuantity());
            pre.setString(8, product.getDescription());
            pre.setString(9,product.getStatus());
            pre.setInt(10, product.getProductId());

            //Kiem tra
            int records = pre.executeUpdate();
            if (records > 0) {
                System.out.println("Da sua thanh cong");
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
    
    //Xoa san pham
    public boolean delete(int productId) {
        //Cau lenh SQL
        String sql = "DELETE FROM PRODUCTS " + "WHERE product_id = ?";

        try (
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement pre = con.prepareStatement(sql)
        ) {
            //Truyen du lieu cho pre
            pre.setInt(1, productId);

            //Kiem tra
            int records = pre.executeUpdate();
            if (records > 0) {
                System.out.println("Da xoa thanh cong");
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
    
    //Tim kiem san pham
    public List<Product> search(String keyword) {

        List<Product> list = new ArrayList<>();
        //Canh lenh SQL
        String sql = "SELECT product_id, product_name, "
                   + "category_id, brand, state, "
                   + "purchase_price, selling_price, quantity, "
                   + "description, status "
                   + "FROM PRODUCTS "
                   + "WHERE product_id LIKE ? "
                   + "OR product_name LIKE ? "
                   + "OR brand LIKE ? "
                   + "OR category_id IN ("
                   + "SELECT category_id FROM CATEGORIES "
                   + "WHERE category_name LIKE ?)";

        try (
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement pre = con.prepareStatement(sql)
        ) {

            String key = "%" + keyword + "%";
            //Truyen cau lenh cho pre
            pre.setString(1, key);
            pre.setString(2, key);
            pre.setString(3, key);
            pre.setString(4, key);

            try (ResultSet rs = pre.executeQuery()) {
                while (rs.next()) {

                    Product product = new Product();

                    product.setProductId(
                            rs.getInt("product_id")
                    );
                    product.setProductName(
                            rs.getString("product_name")
                    );
                    product.setCategoryId(
                            rs.getInt("category_id")
                    );
                    product.setBrand(
                            rs.getString("brand")
                    );
                    product.setState(
                            rs.getString("state")
                    );
                    product.setPurchasePrice(
                            rs.getInt("purchase_price")
                    );
                    product.setSellingPrice(
                            rs.getInt("selling_price")
                    );
                    product.setQuantity(
                            rs.getInt("quantity")
                    );
                    product.setDescription(
                            rs.getString("description")
                    );
                    product.setStatus(
                            rs.getString("status")
                    );

                    list.add(product);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
    
    //Xem chi tiet san pham
    public Product findById(int productId) {
        //Tao cau lenh SQL
        String sql = "SELECT product_id, product_name, "
                   + "category_id, brand, state, "
                   + "purchase_price, selling_price, quantity, "
                   + "description, status "
                   + "FROM PRODUCTS "
                   + "WHERE product_id = ?";

        try (
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement pre = con.prepareStatement(sql)
        ) {
            //Truyen du lieu cho pre
            pre.setInt(1, productId);

            try (ResultSet rs = pre.executeQuery()) {

                if (rs.next()) {
                    Product product = new Product();

                    product.setProductId(
                            rs.getInt("product_id")
                    );
                    product.setProductName(
                            rs.getString("product_name")
                    );
                    product.setCategoryId(
                            rs.getInt("category_id")
                    );
                    product.setBrand(
                            rs.getString("brand")
                    );
                    product.setState(
                            rs.getString("state")
                    );
                    product.setPurchasePrice(
                            rs.getInt("purchase_price")
                    );
                    product.setSellingPrice(
                            rs.getInt("selling_price")
                    );
                    product.setQuantity(
                            rs.getInt("quantity")
                    );
                    product.setDescription(
                            rs.getString("description")
                    );
                    product.setStatus(
                            rs.getString("status")
                    );

                    return product;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    //Kiem tra ton kho
    public int checkStock(int productId) {

        String sql =
                "SELECT quantity, status "
              + "FROM PRODUCTS "
              + "WHERE product_id = ?";

        try (
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement pre = con.prepareStatement(sql)
        ) {

            pre.setInt(1, productId);
            
            ResultSet rs = pre.executeQuery();
            int stock = rs.getInt("quantity");
            return stock;
            
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    //Cap nhat ton kho sau khi ban
    public boolean decreaseStock(int productId, int quantity) {

        String sql = "UPDATE PRODUCTS "
              + "SET quantity = quantity - ? "
              + "WHERE product_id = ? "
              + "AND quantity >= ?";

        try (
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement pre = con.prepareStatement(sql)
        ) {

            pre.setInt(1, quantity);
            pre.setInt(2, productId);
            pre.setInt(3, quantity);

            return pre.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
