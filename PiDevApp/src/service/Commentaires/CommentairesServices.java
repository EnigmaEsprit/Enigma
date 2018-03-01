/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.Commentaires;

import database.MyDB;
import entites.Commentaires.Commentaires;
import iservice.Commentaires.ICommentaires;
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
 * @author Ivan Landry ONANA
 */
public class CommentairesServices implements ICommentaires{
    Connection con = MyDB.getInstance().getConnection();
    private Statement ste;

    public CommentairesServices() {
        try{
            ste = con.createStatement();
        }
        catch (SQLException ex){
            System.out.println(ex);
        }
    }    
    

    @Override
    public void ajouterCommentaires(Commentaires c, int idUser, int idProduit) {
            String req = "INSERT INTO commentaires(contenuCommentaire,dateAjoutCommentaire,idUser,idProduit) VALUES (?,CURDATE(),?,?)";   
            try {
                PreparedStatement pre = con.prepareStatement(req);
                //pre.setInt(1, c.getIdCommentaire());
                pre.setString(1, c.getContenuCommentaire());
                pre.setInt(2, idUser);
                pre.setInt(3, idProduit);
                //pre.setDate(3, c.getDateAjoutCommentaire());
                //pre.setDate(4, c.getDateModificationCommentaire());
                pre.executeUpdate();
                System.out.println("Commentaire ajout@");
            } catch (SQLException ex) {
                Logger.getLogger(CommentairesServices.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    @Override
    public void supprimerCommentaires(Commentaires c) {
        String query = "delete from commentaires where idCommentaire = " + c.getIdCommentaire() + "";
        Statement state;
        try {
            PreparedStatement pre = con.prepareStatement(query);
            pre.executeUpdate(query);
            System.out.println("Un commentaire supprimé OK !");
        } catch (SQLException ex) {
            Logger.getLogger(CommentairesServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Commentaires> afficherCommentaires(int idProduit) {
        String query = "select c.*, u.imageUser "
                + "from commentaires c join users u on c.idUser = u.id "
                + "where c.idProduit = " + idProduit + " "
                + "order by c.dateAjoutCommentaire desc";
        PreparedStatement pre;
        List<Commentaires> ListCommentaires= new ArrayList<>();
        try {
            pre = con.prepareStatement(query);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                //On crée un Commentaire
                Commentaires c = new Commentaires();

                c.setIdCommentaire(rs.getInt("c.idCommentaire"));
                c.setContenuCommentaire(rs.getString("c.contenuCommentaire"));
                c.setDateAjoutCommentaire(rs.getDate("c.dateAjoutCommentaire")); 
                c.setUrlUserPhoto(rs.getString("u.imageUser"));

                ListCommentaires.add(c);
            }

            //System.out.println("");
        } catch (SQLException ex) {
            Logger.getLogger(CommentairesServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ListCommentaires;
    }

    @Override
    public void modifierCommentaires(Commentaires c) {
            String req = "UPDATE commentaires SET contenuCommentaire = ? WHERE idCommentaire = ?";   
            System.out.println(""+c.getIdCommentaire());
                System.out.println(c.getContenuCommentaire());
            try {
                PreparedStatement pre = con.prepareStatement(req);
                pre.setString(1, c.getContenuCommentaire());
                pre.setInt(2, c.getIdCommentaire());
                //pre.setDate(3, c.getDateAjoutCommentaire());
                //pre.setDate(4, c.getDateModificationCommentaire());
                pre.executeUpdate();             
                System.out.println("Commentaire modifi@");
            } catch (SQLException ex) {
                System.out.println("Erreur !\n" + ex.getMessage());
            }
    }
    
    public int selectID(String emailUser) {
        int idUser = 0;
        String req2 = "SELECT id FROM users WHERE email = '" + emailUser + "'"; 
        PreparedStatement prep;
        try {
            prep = con.prepareStatement(req2);
            ResultSet rs = prep.executeQuery();
            while (rs.next()) {   
               idUser = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CommentairesServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return idUser;
    }
    
        public String getPhotoUser(String emailUser){
            String photo = "";
            String sql = "select imageUser from users where email = '"+emailUser+"'";
            try {
                PreparedStatement pre = con.prepareStatement(sql);
                ResultSet rs = pre.executeQuery();
                while (rs.next()) { 
                    photo = rs.getString(1);
                }
            } catch (SQLException ex) {
                Logger.getLogger(CommentairesServices.class.getName()).log(Level.SEVERE, null, ex);
            }
            return photo;
        }
    
    
}
