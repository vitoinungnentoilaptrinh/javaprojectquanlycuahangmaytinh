/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quanlycuahangmaytinh.dbaccess;

import com.mycompany.quanlycuahangmaytinh.model.Category;
import com.mycompany.quanlycuahangmaytinh.utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryAccess {
    //Xem danh sach cac danh muc
    public List<Category> getAll() {

        List<Category> list = new ArrayList<>();

        String sql = "SELECT category_id, category_name " + "FROM CATEGORIES";

        try (
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery() )
        {
            while (rs.next()) {
                //Tao doi tuong category
                Category category = new Category();
                //Lay du lieu tu bang va them vao class category
                category.setCategoryId(
                        rs.getInt("category_id")
                );
                category.setCategoryName(
                        rs.getString("category_name")
                );
                list.add(category);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    //Them danh muc
    public boolean insert(Category category) {
        //Tao cau lenh SQL
        String sql = "INSERT INTO CATEGORIES(category_name) "
                   + "VALUES (?)";

        try (
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement pre = con.prepareStatement(sql) )
        {
            //Truyen cac gia tri cho prepared statement
            pre.setString(1, category.getCategoryName() );
            
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
    
    //Sua danh muc
    public boolean update(Category category) {
        //Cau lenh SQl
        String sql = "UPDATE CATEGORIES " + "SET category_name = ? "
                + "WHERE category_id = ?";

        try (
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement pre = con.prepareStatement(sql)
        ) {
            //Truyen cac gia tri cho prepare statement
            pre.setString(1, category.getCategoryName() );
            pre.setInt(2,category.getCategoryId() );
        
            //Kiem tra
            int records = pre.executeUpdate();
            if (records > 0) {
                System.out.println("Sua danh muc thanh cong");
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
    //Xoa danh muc
    public boolean delete(int categoryId) {
        //Cau lenh SQL
        String sql = "DELETE FROM CATEGORIES " + "WHERE category_id = ?";

        try (
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement pre = con.prepareStatement(sql)
        ) {
            //Truyen gia tri cho prepared statement
            pre.setInt(1, categoryId);

            //Kiem tra
            int records = pre.executeUpdate();
            if (records > 0) {
                System.out.println("Xoa danh muc thanh cong");
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
    
    //Tim kiem danh muc theo ten
    public List<Category> search(String keyword) {

        List<Category> list = new ArrayList<>();
        //Cau lenh SQL
        String sql = "SELECT category_id, category_name " + "FROM CATEGORIES "
               + "WHERE category_name LIKE ?";

        try (
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {
            //Truyen gia tri cho prepared statement
            ps.setString(1,"%" + keyword + "%");

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Category category = new Category();

                    category.setCategoryId(
                        rs.getInt("category_id")
                    );
                    category.setCategoryName(
                        rs.getString("category_name")
                    );
                    
                    list.add(category);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
