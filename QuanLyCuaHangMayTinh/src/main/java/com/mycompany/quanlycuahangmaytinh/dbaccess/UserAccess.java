/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quanlycuahangmaytinh.dbaccess;

import com.mycompany.quanlycuahangmaytinh.model.User;
import com.mycompany.quanlycuahangmaytinh.utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserAccess {
    //Lay thong tin user
    public List<User> getAll() {
        List<User> list = new ArrayList<>();

        String sql = "SELECT * FROM USERS";
                  
        try (
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement pre = con.prepareStatement(sql);
            ResultSet rs = pre.executeQuery()
        ) {

            while (rs.next()) {
                User user = new User();

                user.setUserId(
                        rs.getInt("user_id")
                );
                user.setUsername(
                        rs.getString("username")
                );
                user.setPassword(
                        rs.getString("password")
                );
                user.setRole(
                        rs.getString("role")
                );
                user.setStatus(
                        rs.getString("status")
                );

                list.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    //Them user
    public boolean insert(User user) {

        String sql = "INSERT INTO USERS "
                   + "(username, password, role, status) "
                   + "VALUES (?, ?, ?, ?)";

        try (
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement pre = con.prepareStatement(sql)
        ) {
            pre.setString(1, user.getUsername());
            pre.setString(2, user.getPassword());
            pre.setString(3, user.getRole());
            pre.setString(4, user.getStatus());

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
    
    //Sua user
    public boolean update(User user) throws SQLException {

        String sql = "UPDATE USERS SET "
                   + "username = ?, " + "password = ?, "
                   + "role = ?, " + "status = ? "
                   + "WHERE user_id = ?";

        try (
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement pre = con.prepareStatement(sql)
        ) {
            pre.setString(1, user.getUsername());
            pre.setString(2, user.getPassword());
            pre.setString(3, user.getRole());
            pre.setString(4, user.getStatus());
            pre.setInt(5, user.getUserId());

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
    
    //Xoa user
    public boolean delete(int userId) {

        String sql = "DELETE FROM USERS " + "WHERE user_id = ?";

        try (
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement pre = con.prepareStatement(sql)
        ) {
            pre.setInt(1, userId);

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
    
    //Tim user theo username
    public User findByUsername(String username) {

        String sql = "SELECT * FROM USERS " + "WHERE username = ?";

        try (
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement pre = con.prepareStatement(sql)
        ) {
            pre.setString(1, username);

            try (ResultSet rs = pre.executeQuery()) {
                if (rs.next()) {
                    User user = new User();

                    user.setUserId(
                            rs.getInt("user_id")
                    );
                    user.setUsername(
                            rs.getString("username")
                    );
                    user.setPassword(
                            rs.getString("password")
                    );
                    user.setRole(
                            rs.getString("role")
                    );
                    user.setStatus(
                            rs.getString("status")
                    );
                    return user;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
    
    //Login
    public User login(String username, String password) {

        String sql = "SELECT user_id, username, password, role, status "
                   + "FROM USERS "
                   + "WHERE username = ? "
                   + "AND password = ? "
                   + "AND status = 'ACTIVE'";

        try (
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement pre = con.prepareStatement(sql)
        ) {

            pre.setString(1, username);
            pre.setString(2, password);

            try (ResultSet rs = pre.executeQuery()) {

                if (rs.next()) {

                    User user = new User();

                    user.setUserId(
                            rs.getInt("user_id")
                    );
                    user.setUsername(
                            rs.getString("username")
                    );
                    user.setPassword(
                            rs.getString("password")
                    );
                    user.setRole(
                            rs.getString("role")
                    );
                    user.setStatus(
                            rs.getString("status")
                    );
                    return user;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
