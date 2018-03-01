/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.Magasins;

import database.MyDB;
import entites.Magasins.magasins;
import iservice.Magasins.Imagasins;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author besmelah
 */
public class magasinsServices implements Imagasins{
   /* Connection conn=MyDB.getInstance().getConnection();
    private Statement ste;
    
public magasinsServices(){
    try {
        ste =conn.createStatement();
    } catch (SQLException ex) {
        System.out.println(ex);
    }
}*/
     private final Connection connection;
                
    public magasinsServices(){
                connection = MyDB.getInstance().getConnection();
    }

    
    @Override
    public void ajouterMagasins(magasins m) {
   
        try {
            String req = "insert into magasins (nomMagasin,photoMagasin,descriptionMagasin,dateCreationMagasin,contactMagasin,idUser,adresseMagasin,numeroMagasin) values (?,?,?,?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(req);
           
            ps.setString(1,m.getNomMagasin());
            ps.setString(2, m.getPhotoMagasin());
            ps.setString(3,m.getDescriptionMagasin());
            ps.setDate(4,m.getDateCreationMagasin());
            ps.setString(5,m.getContactMagasin());
            ps.setInt(6,m.getIdUser());
            ps.setString(7,m.getAdresseMagasin());
            ps.setString(8,m.getNumeroMagasin());

            ps.executeUpdate();
            System.out.println("ajout avec succé");
       
        } catch (SQLException ex) {
            Logger.getLogger(magasinsServices.class.getName()).log(Level.SEVERE, null, ex);
    } 
    }


    @Override
    public void supprimerMagasins(magasins m) {
String requete = "delete from magasins where idMagasin='"+m.getIdMagasin()+"'";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.executeUpdate();
            System.out.println("Suppression effectuée avec succès");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la suppression " + ex.getMessage());
        }          }
    
    
public void supprimerMagasins(int id) {
String requete = "delete from magasins where idMagasin='"+id+"'";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.executeUpdate();
            System.out.println("Suppression effectuée avec succès");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la suppression " + ex.getMessage());
        }          }

    @Override
    public List<magasins> afficherMagasinc() {
           List<magasins> magasins = new ArrayList<>();
        String requete = "select * from magasins ORDER BY idMagasin DESC";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultat = statement.executeQuery(requete);

            while (resultat.next()) {
                magasins m = new magasins();
                m.setNomMagasin(resultat.getString("nomMagasin"));
                m.setPhotoMagasin(resultat.getString("photoMagasin"));
                m.setDescriptionMagasin(resultat.getString("descriptionMagasin"));
                m.setDateCreationMagasin(resultat.getDate("dateCreationMagasin"));
                m.setContactMagasin(resultat.getString("contactMagasin"));
                m.setIdUser(resultat.getInt("idUser"));
                m.setAdresseMagasin(resultat.getString("adresseMagasin"));
                m.setNumeroMagasin(resultat.getString("numeroMagasin"));
                m.setIdMagasin(resultat.getInt("idMagasin"));

             //   u.setDateDeNaissance(resultat.getDate("dateDeNaissance"));
                
                magasins.add(m);
           
           }
            
        } catch (SQLException ex) {
            System.out.println("erreur lors du chargement des comptes" + ex.getMessage());
           
        }   
          return magasins; 
    }
    
     public List<magasins> afficherMagasin() {
           List<magasins> magasins = new ArrayList<>();
        String requete = "select * from magasins ORDER BY idMagasin DESC";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultat = statement.executeQuery(requete);

            while (resultat.next()) {
                magasins m = new magasins();
                
                m.setNomMagasin(resultat.getString("nomMagasin"));
                m.setIdMagasin(resultat.getInt("idMagasin"));

                magasins.add(m);
           
           }
            
        } catch (SQLException ex) {
            System.out.println("erreur lors du chargement des comptes" + ex.getMessage());
           
        }   
          return magasins; 
    }

     @Override   
    public magasins rechercherMagasinsById(int i) {
        
            magasins m = new magasins();
        String requete = "select * from magasins where idMagasin= ? ORDER BY idMagasin DESC";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1, i);
            ResultSet resultat = ps.executeQuery();
            if (resultat.next()) {
                 m.setNomMagasin(resultat.getString("nomMagasin"));
                m.setPhotoMagasin(resultat.getString("photoMagasin"));
                m.setDescriptionMagasin(resultat.getString("descriptionMagasin"));
                m.setDateCreationMagasin(resultat.getDate("dateCreationMagasin"));
                m.setContactMagasin(resultat.getString("contactMagasin"));
                m.setIdUser(resultat.getInt("idUser"));
                m.setAdresseMagasin(resultat.getString("adresseMagasin"));
                m.setNumeroMagasin(resultat.getString("numeroMagasin"));
                m.setIdMagasin(resultat.getInt("idMagasin"));

                return m;
            }

        } catch (SQLException ex) {
            System.out.println("erreur lors de la recherche du compte " + ex.getMessage());
        }   
    return m;    }

    @Override
    public magasins rechercherMagasinsByName(String nom) {
 magasins m = new magasins();
        String requete = "select * from magasins where nomMagasin LIKE '%"+nom+"%'";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ResultSet resultat = ps.executeQuery();
            if (resultat.next()) {
                 m.setNomMagasin(resultat.getString("nomMagasin"));
                m.setPhotoMagasin(resultat.getString("photoMagasin"));
                m.setDescriptionMagasin(resultat.getString("descriptionMagasin"));
                m.setDateCreationMagasin(resultat.getDate("dateCreationMagasin"));
                m.setContactMagasin(resultat.getString("contactMagasin"));
                m.setIdUser(resultat.getInt("idUser"));
                m.setAdresseMagasin(resultat.getString("adresseMagasin"));
                m.setNumeroMagasin(resultat.getString("numeroMagasin"));
                m.setIdMagasin(resultat.getInt("idMagasin"));

                return m;
            }

        } catch (SQLException ex) {
            System.out.println("erreur lors de la recherche du compte " + ex.getMessage());
        }   
    return null;       }
    public List<magasins> rechercherMagasinsByNamee(String nom) {
                   List<magasins> magasins = new ArrayList<>();

        String requete = "select * from magasins where nomMagasin LIKE '%"+nom+"%'ORDER BY idMagasin DESC";
       try {
            Statement statement = connection.createStatement();
            ResultSet resultat = statement.executeQuery(requete);

            while (resultat.next()) {
                magasins m = new magasins();
                m.setNomMagasin(resultat.getString("nomMagasin"));
                m.setPhotoMagasin(resultat.getString("photoMagasin"));
                m.setDescriptionMagasin(resultat.getString("descriptionMagasin"));
                m.setDateCreationMagasin(resultat.getDate("dateCreationMagasin"));
                m.setContactMagasin(resultat.getString("contactMagasin"));
                m.setIdUser(resultat.getInt("idUser"));
                m.setAdresseMagasin(resultat.getString("adresseMagasin"));
                m.setNumeroMagasin(resultat.getString("numeroMagasin"));
             //   u.setDateDeNaissance(resultat.getDate("dateDeNaissance"));
                m.setIdMagasin(resultat.getInt("idMagasin"));
  
                magasins.add(m);
           
           }
            
        } catch (SQLException ex) {
            System.out.println("erreur lors du chargement des comptes" + ex.getMessage());
           
        }   
          return magasins; }

    @Override
    public void modifierMagasin(magasins m) {
 String requete = "update magasins set nomMagasin=? ,photoMagasin=?,descriptionMagasin=?,dateCreationMagasin=?,contactMagasin=?,idUser=?,adresseMagasin=?,numeroMagasin=? where idMagasin='"+m.getIdMagasin()+"'";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setString(1,m.getNomMagasin());
            ps.setString(2, m.getPhotoMagasin());
            ps.setString(3,m.getDescriptionMagasin());
            ps.setDate(4,m.getDateCreationMagasin());
            ps.setString(5,m.getContactMagasin());
            ps.setInt(6,m.getIdUser());
            ps.setString(7,m.getAdresseMagasin());
            ps.setString(8,m.getNumeroMagasin());

            ps.executeUpdate();
            System.out.println("Mise à jour effectuée avec succès");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la mise à jour " + ex.getMessage());
        }               }

      public magasins rechercherMagasinsByVendeur(int idVendeur) {
 magasins m = new magasins();
        String requete = "select * from magasins where idUser ='"+idVendeur+"' ";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ResultSet resultat = ps.executeQuery();
            if (resultat.next()) {
                 m.setNomMagasin(resultat.getString("nomMagasin"));
                m.setPhotoMagasin(resultat.getString("photoMagasin"));
                m.setDescriptionMagasin(resultat.getString("descriptionMagasin"));
                m.setDateCreationMagasin(resultat.getDate("dateCreationMagasin"));
                m.setContactMagasin(resultat.getString("contactMagasin"));
                m.setIdUser(resultat.getInt("idUser"));
                m.setAdresseMagasin(resultat.getString("adresseMagasin"));
                m.setNumeroMagasin(resultat.getString("numeroMagasin"));
                m.setIdMagasin(resultat.getInt("idMagasin"));

                return m;
            }

        } catch (SQLException ex) {
            System.out.println("erreur lors de la recherche du compte " + ex.getMessage());
        }   
    return m;       }
   
    
}
