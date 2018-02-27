/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.Reclamation;

import entites.Reclamation.Reclamations;
import iservice.Reclamation.IReclamations;
import database.MyDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author Ivan Landry ONANA
 */
public class ReclamationsServices implements IReclamations{
    Connection con = MyDB.getInstance().getConnection();
    private Statement ste;
    private int idMagasin;

    public ReclamationsServices() {
        try{
            ste = con.createStatement();
        }
        catch (SQLException ex){
            System.out.println(ex);
        }
    }    

    @Override
    public void envoyerUneReclamation(Reclamations c, String emailUser, String nomMagasin) {
        int idUser = 0;
        String req = "INSERT INTO reclamations(typeReclamation, objetReclamation, contenuReclamation, dateEnvoiReclamation, idUser, idMagasin) VALUES (?,?,?,CURDATE(),?,?)";        
        String req2 = "SELECT id FROM users WHERE email = '" + emailUser + "'"; 
        String req3 = "SELECT idMagasin FROM magasins WHERE nomMagasin = '" + nomMagasin + "'"; 
       
        PreparedStatement prep;
        try {
            prep = con.prepareStatement(req2);
            ResultSet rs = prep.executeQuery();
            while (rs.next()) {   
                idUser = rs.getInt(1);
            }
            prep = con.prepareStatement(req3);
            ResultSet rs2 = prep.executeQuery();
            while (rs2.next()) {   
                idMagasin = rs2.getInt(1);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationsServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        int us = idUser;
        int mg = idMagasin;
        
        try {
            PreparedStatement pre = con.prepareStatement(req);
            
            pre.setString(1, c.getTypeReclamation());
            pre.setString(2, c.getObjetReclamation());
            pre.setString(3, c.getContenuReclamation());
            pre.setInt(4, us);
            pre.setInt(5, mg);
            
            pre.executeUpdate();
            System.out.println("Votre réclamation a bien été envoyée !");
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationsServices.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void repondreAUneReclamation(String destinataire, String message, int id) {
        envoyerMail(destinataire, message);
        modifierDateReponseReclamation(id);
    }
    
    @Override
    public ObservableList<Reclamations> afficherReclamations(String emailVendeur) {
        ObservableList<Reclamations> listeReclamations;
        String sql = "select r.idReclamation, r.typeReclamation, r.objetReclamation, r.contenuReclamation, r.dateEnvoiReclamation, "
                    + "concat(u.nom,' ',u.prenom) as nom, u.email "
                    + "from reclamations r join magasins m on (m.idMagasin = r.idMagasin) join users u on (r.idUser = u.id) "
                    + "where ((r.idMagasin = " + idMagasin + ") and (r.dateReponseReclamation IS null)) "
                    + "order by r.dateEnvoiReclamation desc";
        listeReclamations = affichageReclamations(sql, emailVendeur);
        return listeReclamations;
    }
    
    @Override
    public void supprimerUneReclamation(Reclamations c) {
        String query = "delete from reclamations where idReclamation = '" + c.getIdReclamation() + "'";
        try {
            PreparedStatement pre = con.prepareStatement(query);
            pre.executeUpdate(query);
            System.out.println("Une réclamation supprimé OK !");
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationsServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    private void modifierDateReponseReclamation(int id) {
        try {
            String sql = "update reclamations set dateReponseReclamation = CURDATE() where idReclamation = "+ id +"";
            PreparedStatement pre = con.prepareStatement(sql);
            pre.executeUpdate();
            System.out.println("Date de réponse affect@");
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationsServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void envoyerMail(String receiver, String mesg) {
        // Recipient's email ID needs to be mentioned.
          String to = receiver;
          // Sender's email ID needs to be mentioned
          String from = "ivan.onana2013@gmail.com";
          final String username = "ivan.onana2013@gmail.com"; // "obil2014@yahoo.fr"; change accordingly
          final String password = "@Barcelone2017"; // "Nadal@2015";change accordingly
          // Assuming you are sending email through relay.gmail.com
          String host = "smtp.gmail.com"; // "smtp.mail.yahoo.fr";
          String port = "587";

          Properties props = new Properties();
          props.put("mail.smtp.auth", "true");
          props.put("mail.smtp.starttls.enable", "true");
          props.put("mail.smtp.host", host);
          props.put("mail.smtp.port", port);

          // Get the Session object.
          Session session = Session.getInstance(props,
             new javax.mail.Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                   return new PasswordAuthentication(username, password);
               }
             });

          try {
               // Create a default MimeMessage object.
               Message message = new MimeMessage(session);

               // Set From: header field of the header.
               message.setFrom(new InternetAddress(from));

               // Set To: header field of the header.
               message.setRecipients(Message.RecipientType.TO,
                   InternetAddress.parse(to));

               // Set Subject: header field
               message.setSubject("Reponse à votre réclamation.");

               // Now set the actual message
               message.setText(mesg);
               
               
               // Attach enclosure
               Multipart multipart = new MimeMultipart();        
               MimeBodyPart messageBodyPart = new MimeBodyPart();   
               DataSource source = new FileDataSource("C:\\Users\\user\\Documents\\NetBeansProjects\\Enigma\\PiDevApp\\Recapitulatif_reclamation.pdf");      
               messageBodyPart.setDataHandler(new DataHandler(source));
               messageBodyPart.setFileName("Recapitulatif_reclamation.pdf");
               multipart.addBodyPart(messageBodyPart);
               message.setContent(multipart);


               // Send message
               Transport.send(message);

               System.out.println("Sent message successfully....");

          } catch (MessagingException e) {
             throw new RuntimeException(e);
            }    
    }

    public List<String> getListeMagasins() {
        String sql = "select nomMagasin from magasins";
        List<String> listeMagasins =  new ArrayList<>();
        
        try {
            PreparedStatement pre = con.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) { 
                String magasin = rs.getString("nomMagasin");
                listeMagasins.add(magasin);                
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationsServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listeMagasins;
    } 
    
    public ObservableList<Reclamations> afficherReclamationsDes3DerniersJours(String emailVendeur) {
        ObservableList<Reclamations> listeReclamations;
        String sql = "select r.idReclamation, r.typeReclamation, r.objetReclamation, r.contenuReclamation, r.dateEnvoiReclamation, "
                    + "concat(u.nom,' ',u.prenom) as nom, u.email "
                    + "from reclamations r join magasins m on (m.idMagasin = r.idMagasin) join users u on (r.idUser = u.id) "
                    + "where ((r.idMagasin = " + idMagasin + ") and (r.dateReponseReclamation IS null) and (CURDATE() - r.dateEnvoiReclamation <= 3)) "
                    + "order by r.dateEnvoiReclamation desc";
        listeReclamations = affichageReclamations(sql, emailVendeur);
        return listeReclamations;
    }
    
    public ObservableList<Reclamations> afficherReclamationsDeLaSemaine(String emailVendeur) {
        ObservableList<Reclamations> listeReclamations;
        String sql = "select r.idReclamation, r.typeReclamation, r.objetReclamation, r.contenuReclamation, r.dateEnvoiReclamation, "
                    + "concat(u.nom,' ',u.prenom) as nom, u.email "
                    + "from reclamations r join magasins m on (m.idMagasin = r.idMagasin) join users u on (r.idUser = u.id) "
                    + "where ((r.idMagasin = " + idMagasin + ") and (r.dateReponseReclamation IS null) and (CURDATE() - r.dateEnvoiReclamation <= 7)) "
                    + "order by r.dateEnvoiReclamation desc";
        listeReclamations = affichageReclamations(sql, emailVendeur);
        return listeReclamations;
    }
    
    public ObservableList<Reclamations> afficherReclamationsDuMois(String emailVendeur) {
        ObservableList<Reclamations> listeReclamations;
        String sql = "select r.idReclamation, r.typeReclamation, r.objetReclamation, r.contenuReclamation, r.dateEnvoiReclamation, "
                    + "concat(u.nom,' ',u.prenom) as nom, u.email "
                    + "from reclamations r join magasins m on (m.idMagasin = r.idMagasin) join users u on (r.idUser = u.id) "
                    + "where ((r.idMagasin = " + idMagasin + ") and (r.dateReponseReclamation IS null) and (CURDATE() - r.dateEnvoiReclamation <= 30)) "
                    + "order by r.dateEnvoiReclamation desc";
        listeReclamations = affichageReclamations(sql, emailVendeur);
        return listeReclamations;
    }
    
        
    public ObservableList<Reclamations> affichageReclamations(String query, String emailVendeur) {
        int idM = 0;
        ObservableList<Reclamations> listeReclamations = FXCollections.observableArrayList();
        try {
            String sql = "select m.idMagasin from magasins m join users u on (u.id = m.idUser) where u.email = '"+ emailVendeur +"'";
            PreparedStatement prep = con.prepareStatement(sql);
            ResultSet rs2 = prep.executeQuery();
            while (rs2.next()) { 
                idM = rs2.getInt(1);
            }
            idMagasin = idM;

            PreparedStatement pre = con.prepareStatement(query);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {  
                Reclamations r = new Reclamations();
                r.setIdReclamation(rs.getInt(1));
                r.setTypeReclamation(rs.getString(2));
                r.setObjetReclamation(rs.getString(3));
                r.setContenuReclamation(rs.getString(4));
                r.setNomClient(rs.getString(6));
                r.setEmailClient(rs.getString(7));
                r.setDateEnvoiReclamation(rs.getDate(5));
                listeReclamations.add(r);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationsServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listeReclamations;
    }
    
    
}
