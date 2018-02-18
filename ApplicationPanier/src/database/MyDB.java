/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nader
 */
public class MyDB {
    String url = "jdbc:mysql://localhost:3306/pidev";
            String login = "root";
            String pwd ="";
            
            Connection con ;
            
            public static MyDB data;

    private MyDB() {
        try {
            con = DriverManager.getConnection(url, login, pwd);
            System.out.println("connexion ok");
        } catch (SQLException ex) {
            Logger.getLogger(MyDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public Connection getConnnexion() {
        return con;
    }
    
    public static MyDB getInstance(){
        if(data == null){
            data = new MyDB();
        }
        return data;
    }
    
            
            
                       
}
