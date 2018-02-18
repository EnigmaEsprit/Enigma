/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import database.MyDB;
import entites.LigneCommande;
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
 * @author jean
 */
public class LigneCommandesServices {
   Connection conn = MyDB.getInstance().getConnnexion();
    private Statement ste;

    public LigneCommandesServices() {

        try {
            ste = conn.createStatement();
        } catch (SQLException ex) {

            System.out.println(ex);
        }
    }  
    
     public void ajouterProduit(LigneCommande l) {
        try {
            /* Statement stm= myDB.getConnnexion().createStatement();*/

            String query = "INSERT INTO lignecommandes (idCommande, idProduit, prixTotal, quantite,idMagasin,prixUnitaire) VALUES (?,?, ?,?, ?,?)";

            PreparedStatement pre = conn.prepareStatement(query);
            pre.setInt(1, l.getIdCommande());
            pre.setDouble(3,l.getPrixTotal());
            pre.setInt(2, l.getIdProduit());
            pre.setInt(4, l.getQuantite());
            pre.setInt(5, 1);
            pre.setDouble(6, l.getPrixUnitaire());
            // ste.executeUpdate(query);
            pre.executeUpdate();

            System.out.println("ligne de commande ajout ok");
        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }
     
     public List<LigneCommande> rechercherProduitEtQuantite(Date date){
         List<LigneCommande> lignecommandes = new ArrayList<>();
        try {
            Statement stm = conn.createStatement();
            ResultSet rest = stm.executeQuery("SELECT SUM(L.quantite),P.nomProduit  from lignecommandes L JOIN produits P on L.idProduit = P.idProduit Join commandes C on L.idCommande= C.idCommande where C.dateDeCommande like 'date%' group by P.nomProduit ");
            while (rest.next()) {
                LigneCommande l = new LigneCommande();
               
                l.setQuantiteTotale(rest.getInt(1));
                 l.setLibelle(rest.getString(2));
                lignecommandes.add(l);

            }

        } catch (SQLException ex) {
            Logger.getLogger(ProduitServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lignecommandes;
     }
     
      public List<LigneCommande> rechercherProduitEtQuantiteDefaut(){
         List<LigneCommande> lignecommandes = new ArrayList<>();
        try {
            Statement stm = conn.createStatement();
            ResultSet rest = stm.executeQuery("SELECT SUM(L.quantite),P.nomProduit  from lignecommandes L JOIN produits P on L.idProduit = P.idProduit group by P.nomProduit ");
            while (rest.next()) {
                LigneCommande l = new LigneCommande();
               
                l.setQuantiteTotale(rest.getInt(1));
                 l.setLibelle(rest.getString(2));
                
                lignecommandes.add(l);

            }

        } catch (SQLException ex) {
            Logger.getLogger(ProduitServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lignecommandes;
     }
     
}
