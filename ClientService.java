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
import pidevapp.InscriptionController;

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
    public boolean ajouterClient(Client c) {
        boolean insert = false;
        try {
            ResultSet res = stm.executeQuery("SELECT email FROM users WHERE type ='client' AND email ='" + c.getEmail() + "'");

            if (res.next()) {

                insert = false;
                // ic.setLabmsg("Verfier votre email deja utiliser");
                //System.out.println("Verfier votre email deja utiliser");
            } else {
                try {
                    stm.executeUpdate("INSERT INTO users(nom,prenom,dateDeNaissance,sexe,adresse,ville,zip,numeroDuTelephone,email,password,type,numeroDeCarteBancaire,dateDeValidation,codeSecret) VALUES('" + c.getNom() + "','" + c.getPrenom() + "','" + c.getDate_naissance() + "','" + c.getSexe() + "','" + c.getAdresse() + "','" + c.getVille() + "'," + c.getZip() + ",'" + c.getNt() + "','" + c.getEmail() + "','" + c.getMdp() + "','client','" + c.getNbc() + "','" + c.getDate_validation() + "'," + c.getCode_s() + ")");
                    insert = true;
                    System.out.println("Insert");

                    // System.out.println("bravo");
                } catch (Exception e) {
                    System.out.println(e);
                }
                
            }
        } catch (SQLException ex) {
            System.out.println("Select");
        }
        return insert;

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

            stm.executeUpdate("UPDATE users SET nom='" + c.getNom() + "',prenom='" + c.getPrenom() + "',dateDeNaissance='" + c.getDate_naissance() + "',sexe='" + c.getSexe() + "',adresse='" + c.getAdresse() + "',ville='" + c.getVille() + "',zip=" + c.getZip() + ",numeroDuTelephone='"+c.getNt()+"',email='"+c.getEmail()+"',password='" + c.getMdp() + "',numeroDeCarteBancaire='" + c.getNbc() + "',dateDeValidation='" + c.getDate_validation() + "',codeSecret=" + c.getCode_s() + " WHERE email='" + mail + "'");
               System.out.println("Update Client");
        } catch (SQLException ex) {
            System.out.println(ex);
            
        }
            
      


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
    public Client rechercheParMail(String mail,String mdp) {

       Client c = null;
            
        try {
            ResultSet res = stm.executeQuery("SELECT * FROM users WHERE type ='client' AND email ='" + mail + "' AND password='"+mdp+"'");
            System.out.println("ooook"); 
            res.next();
             c = new Client(res.getString(2), res.getString(3), res.getString(4), res.getString(5), res.getString(6), res.getString(7), res.getInt(8), res.getString(9), res.getString(10), res.getString(11), res.getString(14), res.getString(15), res.getInt(16));
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
           }
            else
           {
               existe=false;
           }
        } catch (SQLException ex) {
            Logger.getLogger(ClientService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return existe;
    }

   }
