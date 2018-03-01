/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.Utilisateur;

import database.MyDB;
import entites.Utilisateur.Client;
import Util.Util;
import iservice.Utilisateur.IClient;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import GUI.Utilisateur.InscriptionController;

/**
 *
 * @author user
 */
public class ClientService implements IClient {

    private Statement stm;
    Connection con;
    InscriptionController ic = new InscriptionController();

    public ClientService() {
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
                    String MDPHASH = ServiceHash.hashPassword(c.getMdp());
                    String CVVHASH = ServiceHash.hashPassword(c.getCode_s());
                    stm.executeUpdate("INSERT INTO users(nom,prenom,dateDeNaissance,sexe,adresse,ville,zip,numeroDuTelephone,email,password,type,imageUser,numeroDeCarteBancaire,dateDeValidation,codeSecret) VALUES('" + c.getNom() + "','" + c.getPrenom() + "','" + c.getDate_naissance() + "','" + c.getSexe() + "','" + c.getAdresse() + "','" + c.getVille() + "'," + c.getZip() + ",'" + c.getNt() + "','" + c.getEmail() + "','" + MDPHASH + "','client','"+c.getImg()+"','" + c.getNbc() + "','" + c.getDate_validation() + "','" + CVVHASH+ "')");
                    
                    System.out.println("Insert");

                    // System.out.println("bravo");
                } catch (SQLException e) {
                    System.out.println(e);
                } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(ClientService.class.getName()).log(Level.SEVERE, null, ex);
        }
                
            }


    

    @Override
    public void supprimerClient(String email) {
        try {
            stm.executeUpdate("DELETE FROM users WHERE email='" + email + "'AND type='client'");

        } catch (SQLException ex) {
            System.out.println("Supprimer");
            System.out.println(ex);
        }
       
    }

    @Override
    public void modifierClient(Client c,String mail) {
        
          
            
        
        try {
            
            
            stm.executeUpdate("UPDATE users SET nom='" + c.getNom() + "',prenom='" + c.getPrenom() + "',dateDeNaissance='" + c.getDate_naissance() + "',sexe='" + c.getSexe() + "',adresse='" + c.getAdresse() + "',ville='" + c.getVille() + "',zip=" + c.getZip() + ",numeroDuTelephone='"+c.getNt()+"'WHERE email='" + mail + "' and type='client'");
               System.out.println("Update Client");
        } catch (SQLException ex) {
            System.out.println(ex);
            
           


    }
    }
          
    public boolean modifierPasswordClient(String mdp) {
        
          
            boolean insert = false;
        
        try {
            
            
         
            stm.executeUpdate("UPDATE users SET password='" + mdp + "'WHERE email='" +Util.connectedUser.getEmail() + "' and type = 'client'");
               System.out.println("Update Password Client");
               insert = true;
        } catch (SQLException ex) {
            System.out.println(ex);
            
       
            
      


    }
        return insert;
    }
     public boolean modifierEmailClient(String mail) {
        
          
            boolean insert = false;
        
        try {
            
            
         
            stm.executeUpdate("UPDATE users SET email='" + mail+ "'WHERE email='" +Util.connectedUser.getEmail() + "' and type = 'client'");
               System.out.println("Update Email Client");
               insert = true;
        } catch (SQLException ex) {
            System.out.println(ex);
            
       
            
      


    }
        return insert;
    }
    @Override
    public void modifierClientImg(Client c, String img) {
        
        try {
            stm.executeUpdate("UPDATE users SET imageUser='" + img + "'WHERE email='" + c.getEmail() + "'");
        } catch (SQLException ex) {
            System.out.println(ex);
            System.out.println("Update IMG");
        }
    }

    @Override
    public void afficherClient(Client c) {
        try {
            ResultSet res = stm.executeQuery("SELECT * FROM users WHERE email='" + c.getEmail() + "'");
            while (res.next()) {
                System.out.println("Image: " + res.getString(12) + "\nNom: " + res.getString(2) + "\nPrenom: " + res.getString(3) + "\nDate de naissance: " + res.getDate(4) + "\nSexe " + res.getString(5) + "\nAdresse: " + res.getString(6) + "\nVille: " + res.getString(7) + "\nZIP: " + res.getInt(8) + "\nE-mail: " + res.getString(9) + "\nPassword: " + res.getString(10) + "\nNumero de carte bancaire: " + res.getString(13) + "\nDate de validation: " + res.getDate(14) + "\nCode secraite: " + res.getInt(15));
            }
        } catch (SQLException ex) {
            System.out.println("Consulter");
            System.out.println(ex);
        }

    }

    @Override
    public Client rechercheParMail(String mail) {

       Client c = null;
            
        try {
           
            ResultSet res = stm.executeQuery("SELECT * FROM users WHERE type ='client' AND email ='" + mail+"'");
            System.out.println("ooook"); 
            res.next();
             c = new Client(res.getInt(1),res.getString(2), res.getString(3), res.getString(4), res.getString(5), res.getString(6), res.getString(7), res.getInt(8), res.getString(9), res.getString(10), res.getString(11),res.getString(13), res.getString(14), res.getString(15), res.getString(16));
             System.out.println(c);
        } catch (SQLException ex) {
            Logger.getLogger(ClientService.class.getName()).log(Level.SEVERE, null, ex);
        
        }
        return c;
            

}

    @Override
    public boolean recherche(String mail) {
        boolean existe = false;
        try {
            ResultSet res = stm.executeQuery("SELECT * FROM users WHERE type ='client' AND email ='" + mail + "'");
            System.out.println("ooook"); 
           if(res.next())
           {
               
               existe = true;
               System.out.println(existe);
           }
            else
           {
               existe=false;
               System.out.println(existe);
           }
        } catch (SQLException ex) {
            Logger.getLogger(ClientService.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(existe);
        return existe;
        
    }
       public void modifierIDBClient(Client c,String email) throws NoSuchAlgorithmException {
        try {
           String codes = ServiceHash.hashPassword(c.getCode_s());
            
            stm.executeUpdate("UPDATE users SET numeroDeCarteBancaire='"+c.getNbc()+"',dateDeValidation='"+c.getDate_validation()+"',codeSecret='"+codes+"'WHERE email='"+email+"'and type='client'");
       
        } catch (SQLException ex) {
            System.out.println("IDB Client modifier");
            System.out.println(ex);
        }
      }
 
   }
