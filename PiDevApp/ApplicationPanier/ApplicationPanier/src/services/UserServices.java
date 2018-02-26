/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import database.MyDB;
import entites.User;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jean
 */
public class UserServices {
     Connection conn = MyDB.getInstance().getConnnexion();
    private Statement ste;

    public UserServices() {

        try {
            ste = conn.createStatement();
        } catch (SQLException ex) {

            System.out.println(ex);
        }
    }
    
    public Boolean connect(String nom,String password ){
        List<User> users = new ArrayList<>();
        Boolean result = false;
        try {
            Statement stm = conn.createStatement();
            ResultSet rest = stm.executeQuery("SELECT * from users where nom like 'nom' and password like 'password' ");
            
            while (rest.next()) {
                User u = new User();
               u.setNom(rest.getString(2));
               u.setPassword(rest.getString(9));
               return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProduitServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
}
