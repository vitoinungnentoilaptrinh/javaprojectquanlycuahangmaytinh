/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quanlycuahangmaytinh;

import com.mycompany.quanlycuahangmaytinh.dbaccess.CategoryAccess;
import com.mycompany.quanlycuahangmaytinh.model.Category;

import java.util.List;

public class TestCategory {
    public static void main(String[] args) {

        CategoryAccess dao = new CategoryAccess();

        List<Category> list = dao.getAll();

        for (Category category : list) {

            System.out.println(
                    category.getCategoryId()
                    + " - "
                    + category.getCategoryName()
            );
        }
    }
}
