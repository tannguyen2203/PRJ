/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fptuni.prj301.demo.dbmanager;

import com.fptuni.prj301.demo.model.User;
import com.fptuni.prj301.demo.model.UserSession;
import com.fptuni.prj301.demo.utils.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

/**
 *
 * @author ASUS
 */
public class AccessManager {
     public User login (String userID, String password){
         String sql = "select userID, name, address, phone, password, roleID from tblUser where userID=? AND password=?";
         User user = null;
         try{
           Connection conn = DBUtils.getConnection();
           PreparedStatement ps = conn.prepareStatement(sql);
           ps.setString(1, userID);
           ps.setString(2, password);
           ResultSet rs = ps.executeQuery();
             /*if (username.equals(rs.getString("userID"))&& password.equals("password")){
            UserSession us =  new UserSession();
            us.setUsername(username);
            us.setLoginDate(new Date());
            
            us.setAccessRight("Admin");
            return us;
        }*/
             if(rs.next()){
                 user = new User(rs.getString("userID"), rs.getString("name"), rs.getString("address"), rs.getString("phone"), rs.getString("password"), rs.getString("roleID"));
             }
         }catch(Exception e){
           System.out.println(e);
       }
        
        return user;
    }
}
