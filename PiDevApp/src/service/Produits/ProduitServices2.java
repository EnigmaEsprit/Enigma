/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.Produits;

import database.MyDB;
import entites.Produit.Produit;
import java.sql.Connection;
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
 * @author jean
 */
public class ProduitServices2 {

    Connection conn = MyDB.getInstance().getConnection();
    private Statement ste;

    public ProduitServices2() {

        try {
            ste = conn.createStatement();
        } catch (SQLException ex) {

            System.out.println(ex);
        }
    }

    public void ajouterProduit(Produit p) {
        try {
            /* Statement stm= myDB.getConnnexion().createStatement();*/

            String query = "INSERT INTO produits (nomProduit, prixProduit, quantiteProduit, idMagasin) VALUES (?, ?,?, ?)";

            PreparedStatement pre = conn.prepareStatement(query);
            pre.setString(1, p.getNomProduit());
            pre.setDouble(2, p.getPrixProduit());
            pre.setInt(3, p.getQuantiteProduit());
            pre.setInt(4, 1);
            // ste.executeUpdate(query);
            pre.executeUpdate();

            System.out.println("produit ajout ok");
        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }

    public List<Produit> rechercherProduitParNom(String nomProduit) {
        List<Produit> produits = new ArrayList<>();
        try {
            Statement stm = conn.createStatement();
            ResultSet rest = stm.executeQuery("SELECT * from produits where nomProduit like 'nomProduit%' ");
            while (rest.next()) {
                Produit p = new Produit();
                p.setNomProduit(rest.getString(3));
                p.setPrixProduit(rest.getDouble(4));
                p.setQuantiteProduit(rest.getInt(6));
                produits.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProduitServices2.class.getName()).log(Level.SEVERE, null, ex);
        }
        return produits;
    }

    public void supprimerProduits(Produit p) {
        try {
            PreparedStatement prep = conn.prepareStatement("delete from produits where nomProduit=?");
            prep.setString(1, p.getNomProduit());
            prep.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProduitServices2.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public List<Produit> selectProduits() {
        List<Produit> produits = new ArrayList<>();

        try {
            Statement stm = conn.createStatement();
            ResultSet rest
                    = stm.executeQuery("select * from produits");
            while (rest.next()) {
                Produit p = new Produit();
                p.setIdProduit(rest.getInt(1));
                p.setNomProduit(rest.getString(3));
                p.setPrixProduit(rest.getDouble(4));
                p.setQuantiteProduit(rest.getInt(6));
                produits.add(p);

            }

        } catch (SQLException ex) {
            Logger.getLogger(ProduitServices2.class.getName()).log(Level.SEVERE, null, ex);
        }
        return produits;
    }
    
     public void modifierProduit(Produit p) {
        String requete = "update produits set quantiteProduit=? where idProduit='" + p.getIdProduit() + "'";
        try {
            PreparedStatement ps = conn.prepareStatement(requete);
            
            ps.setInt(1, p.getQuantiteProduit()-p.getQuantiteProduitClient());
           

            ps.executeUpdate();
            System.out.println("Mise à jour effectuée avec succès");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la mise à jour " + ex.getMessage());
        }
    }

}
