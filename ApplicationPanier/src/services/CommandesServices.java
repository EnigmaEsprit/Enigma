/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import database.MyDB;
import entites.Commande;
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
public class CommandesServices {

    Connection conn = MyDB.getInstance().getConnnexion();
    private Statement ste;

    public CommandesServices() {

        try {
            ste = conn.createStatement();
        } catch (SQLException ex) {

            System.out.println(ex);
        }
    }

    public void ajouterCommande(Commande c) {
        try {
            /* Statement stm= myDB.getConnnexion().createStatement();*/

            String query = "INSERT INTO commandes ( prixTotal, idUser, etat,dateDeCommande,idTransaction) VALUES (?, ?,?, ?,?)";

            PreparedStatement pre = conn.prepareStatement(query);

            pre.setDouble(1, c.getPrixTotal());
            pre.setInt(2, c.getIdUser());
            pre.setInt(3, c.getEtat());
            pre.setDate(4, c.getDateDeCommande());
            pre.setInt(5, c.getIdTransaction());
            // ste.executeUpdate(query);
            pre.executeUpdate();

            System.out.println("commande ajout ok");
        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }

    public void supprimerCommande(Commande c) {
        try {
            PreparedStatement prep = conn.prepareStatement("delete from commandes where idTransaction=?");
            prep.setInt(1, c.getIdTransaction());
            prep.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProduitServices.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void modifierEtat(int idCommande) throws SQLException {

        String query = "update commandes set etat=1 where idCommande= ?";
        PreparedStatement pre = conn.prepareStatement(query);

        pre.setInt(1, idCommande);
        pre.executeUpdate();
    }

    public List<Commande> rechercherParDate(Date date) {
        List<Commande> commandes = new ArrayList<>();
        try {
            Statement stm = conn.createStatement();
            ResultSet rest = stm.executeQuery("SELECT * from commandes where dateDeCommande like 'date%' ");
            while (rest.next()) {
                Commande c = new Commande();
                c.setIdCommande(rest.getInt(1));
                c.setPrixTotal(rest.getDouble(2));
                c.setIdUser(rest.getInt(3));
                c.setEtat(rest.getInt(4));
                c.setDateDeCommande(rest.getDate(5));
                c.setIdTransaction(rest.getInt(6));
                commandes.add(c);

            }

        } catch (SQLException ex) {
            Logger.getLogger(ProduitServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return commandes;
    }

    public int rechercherParIdTransaction(int idTransaction) {
       
        int id=0;
        try {
           
            PreparedStatement statement = conn.prepareStatement("SELECT * from commandes where idTransaction =? ");    
            statement.setInt(1, idTransaction);    
            ResultSet rest = statement.executeQuery();
            while (rest.next()) {
                Commande c = new Commande();
                c.setIdCommande(rest.getInt(1));
                c.setPrixTotal(rest.getDouble(2));
                c.setIdUser(rest.getInt(3));
                c.setEtat(rest.getInt(4));
                c.setDateDeCommande(rest.getDate(5));
                c.setIdTransaction(rest.getInt(6));
               
                id=c.getIdCommande();
                return id;

            }

        } catch (SQLException ex) {
            Logger.getLogger(ProduitServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }

    public List<Commande> selectCommandes() {

        List<Commande> commandes = new ArrayList<>();

        try {
            Statement stm = conn.createStatement();
            ResultSet rest
                    = stm.executeQuery("select commandes.*,users.nom from commandes join users on commandes.idUser=users.id");
            /* ResultSet rest
                    = stm.executeQuery("select commandes.*,users.id from commandes"
                            + "INNER JOIN users on commandes.idUser = users.id");*/
            while (rest.next()) {
                Commande c = new Commande();
                c.setIdCommande(rest.getInt(1));
                c.setPrixTotal(rest.getDouble(2));
                c.setIdUser(rest.getInt(3));
                c.setEtat(rest.getInt(4));
                c.setDateDeCommande(rest.getDate(5));
                c.setIdTransaction(rest.getInt(6));
                c.setNom(rest.getString(7));
                commandes.add(c);

            }

        } catch (SQLException ex) {
            Logger.getLogger(ProduitServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return commandes;
    }
}
