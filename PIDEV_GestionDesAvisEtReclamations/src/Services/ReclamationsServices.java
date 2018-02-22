/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Reclamations;
import IServices.IReclamations;
import Datasource.MyDB;
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
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Ivan Landry ONANA
 */
public class ReclamationsServices implements IReclamations{
    Connection con = MyDB.getInstance().getConnection();
    private Statement ste;

    public ReclamationsServices() {
        try{
            ste = con.createStatement();
        }
        catch (SQLException ex){
            System.out.println(ex);
        }
    }    

    @Override
    public void envoyerUneReclamation(Reclamations c, String emailUser) {
        int idUser = 0;
        String req = "INSERT INTO reclamations(typeReclamation, objetReclamation, contenuReclamation, dateEnvoiReclamation, idUser) VALUES (?,?,?,CURDATE(),?)";        
        String req2 = "SELECT id FROM users WHERE email = '" + emailUser + "'";        
        PreparedStatement prep;
        try {
            prep = con.prepareStatement(req2);
            ResultSet rs = prep.executeQuery();
            while (rs.next()) {   
                idUser = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationsServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        int us = idUser;
        
        try {
            PreparedStatement pre = con.prepareStatement(req);
            
            pre.setString(1, c.getTypeReclamation());
            pre.setString(2, c.getObjetReclamation());
            pre.setString(3, c.getContenuReclamation());
            pre.setInt(4, us);
            
            pre.executeUpdate();
            System.out.println("Votre réclamation a bien été envoyée !");
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationsServices.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void repondreAUneReclamation(String destinataire, String message) {
        envoyerMail(destinataire, message);
    }

    @Override
    public ObservableList<Reclamations> afficherReclamations() {
        String sql = "select r.idReclamation, r.typeReclamation, r.objetReclamation, r.contenuReclamation, r.dateEnvoiReclamation, "
                + "concat(u.nom,' ',u.prenom) as nom, u.email "
                + "from reclamations r join users u on (r.idUser = u.id) "
                + "order by r.dateEnvoiReclamation desc";
        ObservableList<Reclamations> listeReclamations = FXCollections.observableArrayList();
        
        try {
            PreparedStatement pre = con.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {   
                Reclamations m = new Reclamations(
                (rs.getInt(1)),
                (rs.getString(2)),
                (rs.getString(3)),
                (rs.getString(4)),
                (rs.getString(6)),
                (rs.getString(7)),
                (rs.getDate(5)));
                listeReclamations.add(m);                
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationsServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listeReclamations;
    }
    
    @Override
    public void supprimerUneReclamation(Reclamations c) {
        String query = "delete from reclamations where idReclamation = '" + c.getIdReclamation() + "'";
        Statement state;
        try {
            PreparedStatement pre = con.prepareStatement(query);
            pre.executeUpdate(query);
            System.out.println("Une réclamation supprimé OK !");
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

               // Send message
               Transport.send(message);

               System.out.println("Sent message successfully....");

          } catch (MessagingException e) {
             throw new RuntimeException(e);
            }    
    }
    
    public Boolean compareEmails(String emailInsere) {
        String sql = "select email from users";
        List<String> listeEmails = new ArrayList<> ();
        
        try {
            PreparedStatement pre = con.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {   
                listeEmails.add(rs.getString("email"));                
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationsServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        for(String emails : listeEmails){
            return emails.equals(emailInsere);
        }                
        return false;
        
    }
}
