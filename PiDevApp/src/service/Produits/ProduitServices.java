/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.Produits;

import database.MyDB;
import  entites.Magasins.magasins;
import entites.Produit.produits;
import iservice.Produits.Iproduits;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class produitServices implements Iproduits {

    Connection connection = MyDB.getInstance().getConnection();
    private Statement ste;

    public produitServices() {

        try {
            ste = connection.createStatement();
        } catch (SQLException ex) {

            System.out.println(ex);
        }
    }

    @Override
    public void ajouterProduits(produits p) {
        try {
            String req = "insert into produits (referenceProduit,nomProduit,prixProduit,photoProduit,quantiteProduit,active,categorieMagasin,idMagasin) values (?,?,?,?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(req);

            ps.setString(1, p.getReferenceProduit());
            ps.setString(2, p.getNomProduit());
            ps.setDouble(3, p.getPrixProduit());
            ps.setString(4, p.getPhotoProduit());
            ps.setDouble(5, p.getQuantiteProduit());
            ps.setInt(6, p.getActive());
            ps.setString(7, p.getCategorieMagasin());
            ps.setInt(8, p.getIdMagasin());

            ps.executeUpdate();
            System.out.println("ajout produit ok");

        } catch (SQLException ex) {
            Logger.getLogger(produitServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void supprimerProduit(int idProduit) {
        String requete = "delete from produits where idProduit='" + idProduit + "'";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.executeUpdate();
            System.out.println("Suppression effectuée avec succès");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la suppression " + ex.getMessage());
        }
    }
    
     public void supprimerProduits(produits p) {
        try {
            PreparedStatement prep = connection.prepareStatement("delete from produits where nomProduit=?");
            prep.setString(1, p.getNomProduit());
            prep.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProduitServices2.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public List<produits> afficherProduis() {
       
        List<produits> prods = new ArrayList<>();
        String requete = " select *  FROM produits ";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultat = statement.executeQuery(requete);

            while (resultat.next()) {
                produits p = new produits();
                p.setIdProduit(resultat.getInt("idProduit"));
                p.setNomProduit(resultat.getString("nomProduit"));
                p.setPrixProduit(resultat.getDouble("prixProduit"));
                p.setQuantiteProduit(resultat.getInt("quantiteProduit"));
                System.out.println("la quantite totale est "+p.getQuantiteProduit());
                p.setReferenceProduit(resultat.getString("referenceProduit"));
                p.setPhotoProduit(resultat.getString("photoProduit"));
                p.setActive(resultat.getInt("active"));
                p.setCategorieMagasin(resultat.getString("categorieMagasin"));
                p.setIdMagasin(resultat.getInt("idMagasin"));

                prods.add(p);

            }

        } catch (SQLException ex) {
            System.out.println("erreur lors du chargement des comptes" + ex.getMessage());

        }
        return prods;
    }

    @Override
    public produits rechercherProduitsById(int i) {
        produits p = new produits();
        String requete = "select * from produits where idProduit LIKE '%" + i + "%'";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ResultSet resultat = ps.executeQuery();
            if (resultat.next()) {
                p.setReferenceProduit(resultat.getString("referenceProduit"));
                p.setNomProduit(resultat.getString("nomProduit"));
                p.setPrixProduit(resultat.getDouble("prixProduit"));
                p.setPhotoProduit(resultat.getString("photoProduit"));
                p.setQuantiteProduit(resultat.getInt("quantiteProduit"));
                p.setActive(resultat.getInt("active"));
                p.setCategorieMagasin(resultat.getString("categorieMagasin"));
                p.setIdMagasin(resultat.getInt("idMagasin"));
                p.setIdProduit(resultat.getInt("idProduit"));

                return p;
            }

        } catch (SQLException ex) {
            System.out.println("erreur lors de la recherche du compte " + ex.getMessage());
        }
        return p;
    }

    @Override
    public produits rechercherProduitsByName(String nom) {
        produits p = new produits();
        String requete = "select * from produits where nomProduit LIKE '%" + nom + "%'";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ResultSet resultat = ps.executeQuery();
            if (resultat.next()) {
                p.setReferenceProduit(resultat.getString("referenceProduit"));
                p.setNomProduit(resultat.getString("nomProduit"));
                p.setPrixProduit(resultat.getDouble("prixProduit"));
                p.setPhotoProduit(resultat.getString("photoProduit"));
                p.setQuantiteProduit(resultat.getInt("quantiteProduit"));
                p.setActive(resultat.getInt("active"));
                p.setCategorieMagasin(resultat.getString("categorieMagasin"));
                p.setIdMagasin(resultat.getInt("idMagasin"));

                return p;
            }

        } catch (SQLException ex) {
            System.out.println("erreur lors de la recherche du compte " + ex.getMessage());
        }
        return null;
    }

    public produits rechercherProduitsByMagasin(int idmagasin) {
        produits p = new produits();
        String requete = "select * from produits where idMagasin='" + idmagasin + "'";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ResultSet resultat = ps.executeQuery();
            if (resultat.next()) {
                p.setReferenceProduit(resultat.getString("referenceProduit"));
                p.setNomProduit(resultat.getString("nomProduit"));
                p.setPrixProduit(resultat.getDouble("prixProduit"));
                p.setPhotoProduit(resultat.getString("photoProduit"));
                p.setQuantiteProduit(resultat.getInt("quantiteProduit"));
                p.setActive(resultat.getInt("active"));
                p.setCategorieMagasin(resultat.getString("categorieMagasin"));
                p.setIdMagasin(resultat.getInt("idMagasin"));

                return p;
            }

        } catch (SQLException ex) {
            System.out.println("erreur lors de la recherche du compte " + ex.getMessage());
        }
        return null;
    }

    @Override
    public void modifierProduit(produits p) {
        String requete = "update produits set referenceProduit=? ,nomProduit=?,prixProduit=?,quantiteProduit=?,categorieMagasin=? where idProduit='" + p.getIdProduit() + "'";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setString(1, p.getReferenceProduit());
            ps.setString(2, p.getNomProduit());
            ps.setDouble(3, p.getPrixProduit());
            ps.setInt(4, p.getQuantiteProduit());
            ps.setString(5, p.getCategorieMagasin());

            ps.executeUpdate();
            System.out.println("Mise à jour effectuée avec succès");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la mise à jour " + ex.getMessage());
        }
    }
     public void modifierProduit2(produits p) {
        String requete = "update produits set quantiteProduit=? where idProduit='" + p.getIdProduit() + "'";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            
            ps.setInt(1, p.getQuantiteProduit()-p.getQuantiteProduitClient());
           

            ps.executeUpdate();
            System.out.println("Mise à jour effectuée avec succès");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la mise à jour " + ex.getMessage());
        }
    }
     public void modifierProduitQuantite(produits p) {
        String requete = "update produits set quantiteProduit=? where idProduit='" + p.getIdProduit() + "'";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            
            ps.setInt(1, p.getQuantiteProduit()-p.getQuantiteProduitClient());
           

            ps.executeUpdate();
            System.out.println("Mise à jour effectuée avec succès");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la mise à jour " + ex.getMessage());
        }
    }


    @Override
    public void supprimerProduit(produits p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<produits> rechercherProduitByMagasin(int idmagasin) {
        List<produits> produits = new ArrayList<>();
        String requete = "select * from produits where idMagasin='" + idmagasin + "'";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultat = statement.executeQuery(requete);

            while (resultat.next()) {
                produits p = new produits();

                p.setIdProduit(resultat.getInt("idProduit"));
                p.setNomProduit(resultat.getString("nomProduit"));
                p.setPrixProduit(resultat.getDouble("prixProduit"));
                p.setQuantiteProduit(resultat.getInt("quantiteProduit"));
                p.setReferenceProduit(resultat.getString("referenceProduit"));
                p.setPhotoProduit(resultat.getString("photoProduit"));
                p.setActive(resultat.getInt("active"));
                p.setCategorieMagasin(resultat.getString("categorieMagasin"));
                p.setIdMagasin(resultat.getInt("idMagasin"));

                produits.add(p);
            }

        } catch (SQLException ex) {
            System.out.println("erreur lors de la recherche du compte " + ex.getMessage());
        }
        return produits;
    }

    public List<produits> rechercherProduitsByMagasins(int idmagasin) {
        List<produits> prosuits = new ArrayList<>();
        String requete = "select * from produits where idMagasin='" + idmagasin + "'";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultat = statement.executeQuery(requete);

            while (resultat.next()) {
                produits p = new produits();
                p.setIdProduit(resultat.getInt("idProduit"));
                p.setNomProduit(resultat.getString("nomProduit"));
                p.setPrixProduit(resultat.getDouble("prixProduit"));
                p.setQuantiteProduit(resultat.getInt("quantiteProduit"));
                p.setReferenceProduit(resultat.getString("referenceProduit"));
                p.setPhotoProduit(resultat.getString("photoProduit"));
                p.setActive(resultat.getInt("active"));
                p.setCategorieMagasin(resultat.getString("categorieMagasin"));
                p.setIdMagasin(resultat.getInt("idMagasin"));

                prosuits.add(p);

            }

        } catch (SQLException ex) {
            System.out.println("erreur lors du chargement des comptes" + ex.getMessage());

        }
        return prosuits;
    }



}
