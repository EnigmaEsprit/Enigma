/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.Decouverte;

import database.MyDB;
import entites.Utilisateur.Client;
import entites.Utilisateur.Vendeur;
import entites.Decouverte.Recherche;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author user
 */
public class RechercheService {
Connection con;
private ObservableList<Recherche>data;
      
    public ObservableList<Recherche>  searsh(String motch)
    { System.out.println("+++++++++++++++++"+motch);
       
         try {
             con = MyDB.getInstance().getConnection();
             data=FXCollections.observableArrayList();
             
             ResultSet res = con.createStatement().executeQuery("SELECT u.nom,u.prenom,u.adresse,u.ville,u.zip,u.numeroDuTelephone,u.email,m.nomMagasin FROM users u,magasins m WHERE u.type ='vendeur' AND (m.idUser=u.id)AND(u.nom like'%"+motch+"%' or u.prenom like'%"+motch+"%' or m.nomMagasin like'%"+motch+"%'or u.email='%"+motch+"@')");
             if(res.next())
             {
                 Recherche  recherche =new Recherche(res.getString(1), res.getString(2), res.getString(3), res.getString(4), res.getInt(5), res.getString(6),res.getString(7), res.getString(8));
                 data.add(recherche);
                 System.out.println(recherche);
             }
         } catch (SQLException ex) {
             Logger.getLogger(RechercheService.class.getName()).log(Level.SEVERE, null, ex);
         }
             
         
        return data;
        
    }
    
  
  
}
