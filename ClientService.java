/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import database.MyDB;
import entites.Client;
import iservice.IClient;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class ClientService implements IClient{
    private Statement stm;
    Connection con;
    public ClientService(){
        try {
            con = MyDB.getInstance().getConnection();
            stm = con.createStatement();
           
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void ajouterClient(Client c) {
        try {
            ResultSet res = stm.executeQuery("SELECT email FROM users WHERE type ='client' AND email ='"+c.getEmail()+"'");
            if(res.next()){
                System.out.println("Verfier votre email deja utiliser");
            }
            else
            {
                try {
                    stm.executeUpdate("INSERT INTO users(nom,prenom,dateDeNaissance,adresse,ville,zip,email,password,type,imageUser,numeroDeCarteBancaire,dateDeValidation,codeSecret) VALUES('"+c.getNom()+"','"+c.getPrenom()+"','"+c.getDate_naissance()+"','"+c.getAdresse()+"','"+c.getVille()+"',"+c.getZip()+",'"+c.getEmail()+"','"+c.getMdp()+"','client','"+c.getImg()+"','"+c.getNbc()+"','"+c.getDate_validation()+"',"+c.getCode_s()+")");
                } catch (Exception e) {
                    System.out.println("Insert");
                    System.out.println(e);
                }
               
            }
        } catch (SQLException ex) {
            System.out.println("Select");
        }
         
    }

    @Override
    public void supprimerClient(Client c) {
        try {
            stm.executeUpdate("DELETE FROM users WHERE email='"+c.getEmail()+"'");
            
        } catch (SQLException ex) {
            System.out.println("Supprimer");
            System.out.println(ex);
        }
    }

    @Override
    public void modifierClient(Client c) {
        try {
            ResultSet res = stm.executeQuery("SELECT * FROM users WHERE email='"+c.getEmail()+"'");
            
            stm.executeUpdate("UPDATE users SET nom='"+c.getNom()+"',prenom='"+c.getPrenom()+"',dateDeNaissance='"+c.getDate_naissance()+"',adresse='"+c.getAdresse()+"',ville='"+c.getVille()+"',zip="+c.getZip()+",password='"+c.getMdp()+"',numeroDeCarteBancaire='"+c.getNbc()+"',dateDeValidation='"+c.getDate_validation()+"',codeSecret="+c.getCode_s()+" WHERE email='"+c.getEmail()+"'");
       
        } catch (SQLException ex) {
            System.out.println(ex);
            System.out.println("Update Client");
        }

    }
     @Override
    public void modifierClientImg(Client c, String img) {
        try {
            stm.executeUpdate("UPDATE users SET imageUser='"+img+"'WHERE email='"+c.getEmail()+"'");
        } catch (SQLException ex) {
            System.out.println(ex);
            System.out.println("Update IMG");
        }
    }
 

    @Override
    public void afficherClient(Client c) {
        try {
           ResultSet res = stm.executeQuery("SELECT * FROM users WHERE email='"+c.getEmail()+"'");
            while (res.next()) {                
                System.out.println("Image: "+res.getString(11)+"\nNom: "+res.getString(2)+"\nPrenom: "+res.getString(3)+"\nDate de naissance: "+res.getDate(4)+"\nAdresse: "+res.getString(5)+"\nVille: "+res.getString(6)+"\nZIP: "+res.getInt(7)+"\nE-mail: "+res.getString(8)+"\nPassword: "+res.getString(9)+"\nNumero de carte bancaire: "+res.getString(12)+"\nDate de validation: "+res.getDate(13)+"\nCode secraite: "+res.getInt(14));
            }
        } catch (SQLException ex) {
            System.out.println("Consulter");
            System.out.println(ex);
        }

        
    }

   
}
