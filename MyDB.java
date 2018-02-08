/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author user
 */
public class MyDB {
    private static MyDB data;
    String url="jdbc:mysql://localhost:3306/pidev";
    String login="root";
    String pwd="";
    private Connection con;
    private MyDB()
    {
        try {
            con = DriverManager.getConnection(url,login,pwd);
            System.out.println("Connexion OK");
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    public Connection getConnection(){
        return con;
    }

    public static MyDB getInstance(){
        if(data == null)
        {
          data = new MyDB();
        }
        return data;
    }
    
}
