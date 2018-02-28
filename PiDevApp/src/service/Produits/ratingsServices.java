/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.Produits;

import database.MyDB;
import Entities.Ratings;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author jean
 */
public class ratingsServices {
    Connection connection = MyDB.getInstance().getConnection();
    private Statement ste;

    public ratingsServices() {

        try {
            ste = connection.createStatement();
        } catch (SQLException ex) {

            System.out.println(ex);
        }
    }
    
    
    public void ajouterRatings(Ratings r, int idProduit) {
        try {
            /* Statement stm= myDB.getConnnexion().createStatement();*/

            String query = "INSERT INTO produits (sommeEvaluation, nombreEvaluation, moyenneEvaluation, idProduit) VALUES (?, ?,?, ?)";

            PreparedStatement pre = connection.prepareStatement(query);
            pre.setDouble(1, r.getSommeEvaluation());
            pre.setInt(2, r.getNombreEvaluation());
            pre.setDouble(3, r.getMoyenneEvaluation());
            pre.setInt(4, idProduit);
            // ste.executeUpdate(query);
            pre.executeUpdate();

            System.out.println("ratings ok");
        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }
    
    public void modifierRatings(Ratings r, int idProduit){
        
        String requete = "update ratings set ";
        
    }
    
}
