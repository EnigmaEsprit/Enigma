/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.Utilisateur;

import database.MyDB;
import entites.Utilisateur.Client;
import Util.Util;
import entites.Utilisateur.Vendeur;
import iservice.Utilisateur.IVendeur;
import java.security.NoSuchAlgorithmException;
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
public class VendeurService implements IVendeur{
    private Statement stm;
    Connection con;
    public VendeurService(){
        try {
            con = MyDB.getInstance().getConnection();
            stm = con.createStatement();
           
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void ajouterVendeur(Vendeur v) {
       
                try {
                    String MDPHASH = ServiceHash.hashPassword(v.getMdp());
                    stm.executeUpdate("INSERT INTO users(nom,prenom,dateDeNaissance,sexe,adresse,ville,zip,numeroDuTelephone,email,password,type,situaitionFiscal,ribBancaire) VALUES('" + v.getNom() + "','" + v.getPrenom() + "','" + v.getDate_naissance() + "','" + v.getSexe() + "','" + v.getAdresse() + "','" + v.getVille() + "'," + v.getZip() + ",'" + v.getNt() + "','" + v.getEmail() + "','" + MDPHASH + "','vendeur','" +v.getSutiation_fiscal()+ "','" + v.getNumero_bancaire()+"')");
                   
                    System.out.println("Insert");

                    // System.out.println("bravo");
                } catch (SQLException e) {
                    System.out.println(e);
                } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(VendeurService.class.getName()).log(Level.SEVERE, null, ex);
        }
                
        
      
     
    }

    @Override
    public void supprimerVendeur(Vendeur v) {
        try {
            stm.executeUpdate("DELETE FROM users WHERE email='"+v.getEmail()+"'");
            
        } catch (SQLException ex) {
            System.out.println("SupprimerV");
            System.out.println(ex);
        }    }

    @Override
    public void modifierVendeur(Vendeur v) {
try {
          
            
            stm.executeUpdate("UPDATE users SET nom='"+v.getNom()+"',prenom='"+v.getPrenom()+"',dateDeNaissance='"+v.getDate_naissance()+"',sexe='"+v.getSexe()+"',adresse='"+v.getAdresse()+"',ville='"+v.getVille()+"',zip="+v.getZip()+" WHERE email='"+v.getEmail()+"'");
       
        } catch (SQLException ex) {
            System.out.println("modifier");
            System.out.println(ex);
        }    }
    @Override
        public void modifierVendeurImg(Vendeur v, String img) {
  try {
            stm.executeUpdate("UPDATE users SET imageUser='"+img+"'WHERE email='"+v.getEmail()+"'");
        } catch (SQLException ex) {
            System.out.println(ex);
            System.out.println("Update IMG V");
        }    }
    @Override
    public void afficherVendeur(Vendeur v) {
try {
           ResultSet res = stm.executeQuery("SELECT * FROM users WHERE email='"+v.getEmail()+"'");
            while (res.next()) {                
                System.out.println("Image: "+res.getString(12)+"\nNom: "+res.getString(2)+"\nPrenom: "+res.getString(3)+"\nDate de naissance: "+res.getDate(4)+"\nSexe: "+res.getString(5)+"\nAdresse: "+res.getString(6)+"\nVille: "+res.getString(7)+"\nZIP: "+res.getInt(8)+"\nE-mail: "+res.getString(9)+"\nPassword: "+res.getString(10)+"\nSituaition fiscal: "+res.getString(16)+"\nNumero du compte bancaire: "+res.getString(17));
            }
        } catch (SQLException ex) {
            System.out.println("ConsulterV");
            System.out.println(ex);
        }

        
    }
 public Vendeur rechercheParMail(String mail) {

       Vendeur v = null;
            
        try {
            ResultSet res = stm.executeQuery("SELECT * FROM users WHERE type ='vendeur' AND email ='" + mail + "' ");
            System.out.println("ooook"); 
            res.next();
                v = new Vendeur(res.getString(2), res.getString(3),res.getString(4) , res.getString(5), res.getString(6), res.getString(7), res.getInt(8), res.getString(9), res.getString(10), res.getString(11), res.getString(17), res.getString(18));
             System.out.println(v);
        } catch (SQLException ex) {
            Logger.getLogger(ClientService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return v;
            

}

    public boolean recherche(String mail) {
        boolean existe = false;
        try {
            ResultSet res = stm.executeQuery("SELECT * FROM users WHERE type ='vendeur' AND email ='" + mail + "'");
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
     public boolean modifierPasswordVendeur(String email,String mdp) {
        
          
            boolean insert = false;
        
        try {
            
            
         
            stm.executeUpdate("UPDATE users SET password='" + mdp + "'WHERE email='" +email+ "' and type = 'vendeur'");
               System.out.println("Update Password Client");
               insert = true;
        } catch (SQLException ex) {
            System.out.println(ex);
            
       
            
      


    }
        return insert;
    }

     public boolean modifierEmailVendeur(String mail,String emaill) {
        
          
            boolean insert = false;
        
        try {
            
            
         
            stm.executeUpdate("UPDATE users SET email='" + mail+ "'WHERE email='" +emaill + "' and type = 'vendeur'");
               System.out.println("Update Email Vendeur");
               insert = true;
        } catch (SQLException ex) {
            System.out.println(ex);
            
       
            
      


    }
        return insert;
    }

      public boolean modifierPasswordVendeur(String mdp) {
        
          
            boolean insert = false;
        
        try {
            
            
         
            stm.executeUpdate("UPDATE users SET password='" + mdp + "'WHERE email='" +Util.connectedUserVendeur.getEmail() + "' and type = 'vendeur'");
               System.out.println("Update Password vendeur");
               insert = true;
        } catch (SQLException ex) {
            System.out.println(ex);
            
       
            
      


    }
        return insert;
    }
}

   