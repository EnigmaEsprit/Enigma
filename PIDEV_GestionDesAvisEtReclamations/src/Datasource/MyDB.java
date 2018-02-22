/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Datasource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ivan Landry ONANA
 * 
 * Patron de conception connexion à la base de données (DESIGN PATTERN)
 * 
 */
public class MyDB {
    private static MyDB data;  //Création du singleton
    private static final String url = "jdbc:mysql://localhost:3306/pidev";
    private static final String user = "root";
    private static final String pass = "";
    private static Connection connexion;

    private MyDB() {
        try{
            connexion = DriverManager.getConnection(url, user, pass);
            System.out.println("Connexion à la base de données réussie !");
        }
        catch(SQLException ex){
            Logger.getLogger(MyDB.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Echec de connexion !");
        }
    }
    
    
    public Connection getConnection(){
        return connexion;
    }
    
    public static MyDB getInstance(){
        if (data == null){
            data = new MyDB();
        }
        return data;
    }

}
