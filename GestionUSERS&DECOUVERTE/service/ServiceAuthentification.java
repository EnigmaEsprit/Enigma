/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import database.MyDB;
import entites.authentification;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class ServiceAuthentification {
        MyDB con = MyDB.getInstance();

    public boolean authentifier(authentification a){
         boolean var = false;
        try {
           
            Statement stm = con.getConnection().createStatement();
             ResultSet res = stm.executeQuery("SELECT * FROM users WHERE type ='client' AND email ='"+a.getEmail()+"'AND password='"+a.getPassword()+"'");
             System.out.println("authentification");
             if(res.next())
             {
                 var=true;
             }
            
           
         
        } catch (SQLException ex) {
            Logger.getLogger(ServiceAuthentification.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("attention");
        }
        System.out.println(var);
            return var;
       
    }
    public boolean authentifierVendeur(authentification a){
         boolean var = false;
        try {
           
            Statement stm = con.getConnection().createStatement();
             ResultSet res = stm.executeQuery("SELECT * FROM users WHERE type ='vendeur' AND email ='"+a.getEmail()+"'AND password='"+a.getPassword()+"'");
             System.out.println("authentification");
             if(res.next())
             {
                 var=true;
             }
            
           
         
        } catch (SQLException ex) {
            Logger.getLogger(ServiceAuthentification.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("attention");
        }
        System.out.println(var);
            return var;
       
    }
     public boolean authentifierAdmin(authentification a){
         boolean var = false;
        try {
           
            Statement stm = con.getConnection().createStatement();
             ResultSet res = stm.executeQuery("SELECT * FROM users WHERE type ='admin' AND email ='"+a.getEmail()+"'AND password='"+a.getPassword()+"'");
             System.out.println("authentification");
             if(res.next())
             {
                 var=true;
             }
            
           
         
        } catch (SQLException ex) {
            Logger.getLogger(ServiceAuthentification.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("attention");
        }
        System.out.println(var);
            return var;
       
    }

    
}
