/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.quanlycuahangmaytinh;

import com.mycompany.quanlycuahangmaytinh.utils.DatabaseConnection;
import java.sql.Connection;

public class TestConnection {

    public static void main(String[] args) {

        Connection con = DatabaseConnection.getConnection();

        if (con != null) {
            System.out.println("Ket noi MySQL thanh cong!");
        } else {
            System.out.println("Ket noi that bai!");
        }
    }
}
