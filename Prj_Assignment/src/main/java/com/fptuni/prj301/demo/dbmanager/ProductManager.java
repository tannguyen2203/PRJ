/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fptuni.prj301.demo.dbmanager;

import com.fptuni.prj301.demo.model.Product;
import com.fptuni.prj301.demo.utils.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class ProductManager {
   private static ArrayList<Product> list;
   
   static {
        list = new ArrayList<Product>();
       String sql = "select productID, productName,image, price, categoryID, description, sizeID from tblProduct";
       try{
           Connection conn = DBUtils.getConnection();
           PreparedStatement ps = conn.prepareStatement(sql);
           ResultSet rs = ps.executeQuery();
           while(rs.next()){
               list.add(new Product(rs.getString("productID"), 
                       rs.getString("productName"), 
                       rs.getString("image"), 
                       rs.getFloat("price"),
                       rs.getString("categoryID"), 
                       rs.getString("description"),
                       rs.getString("sizeID")));
           }
       }catch(Exception e){
           System.out.println(e);
       }
   }
    public List<Product> list(){
        return list;
    }
    
}
