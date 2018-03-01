/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Datasource.MyDB;
import Entities.Produits;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ivan Landry ONANA
 */
public class ProduitsServices {

    Connection conn = MyDB.getInstance().getConnection();
    private Statement ste;

    public ProduitsServices() {

        try {
            ste = conn.createStatement();
        } catch (SQLException ex) {

            System.out.println(ex);
        }
    }

    public List<Produits> affichageProduits() {
        List<Produits> produits = new ArrayList<>();

        try {
            Statement stm = conn.createStatement();
            ResultSet rest
                    = stm.executeQuery("select * from produits");
            while (rest.next()) {
                Produits p = new Produits();
                p.setIdProduit(rest.getInt(1));
                p.setNomProduit(rest.getString(3));
                p.setPrixProduit(rest.getDouble(4));
                p.setQuantiteProduit(rest.getInt(6));
                p.setUrlPhoto(rest.getString(5));
                p.setReferenceProduit(rest.getInt(2));
                produits.add(p);

            }

        } catch (SQLException ex) {
            Logger.getLogger(ProduitsServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return produits;
    }

}
