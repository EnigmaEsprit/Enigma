/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 * https://www.google.com/settings/security/lesssecureapps
 */
package service.Utilisateur;

/**
 *
 * @author user
 */
    
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.internet.MimeMessage;

public class InscriptionEmailService {
private double random;
  public int random()
  {
     random= Math.random() * ( 100000 - 999999 );
     
    return (int)random;

  }
  public void sendMail(String email,String nbr){
        try{
             String host ="smtp.gmail.com" ;
            String user = "boumaiazaoussama@gmail.com";
            String pass = "PIDEV2018";
            String to = email;
            String from = "boumaiazaoussama@gmail.com";
            String subject = "Code de verification";
            String messageText = "This is confirmation number for your activation account Souk Medina * :"+nbr+"";
            boolean sessionDebug = false;

            Properties props = System.getProperties();

            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.required", "true");

            java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
            Session mailSession = Session.getDefaultInstance(props, null);
            mailSession.setDebug(sessionDebug);
            Message msg = new MimeMessage(mailSession);
            msg.setFrom(new InternetAddress(from));
            InternetAddress[] address = {new InternetAddress(to)};
            msg.setRecipients(Message.RecipientType.TO, address);
            msg.setSubject(subject); msg.setSentDate(new Date());
            msg.setText(messageText);

           Transport transport=mailSession.getTransport("smtp");
           transport.connect(host, user, pass);
           transport.sendMessage(msg, msg.getAllRecipients());
           transport.close();
           System.out.println("message send successfully");
        }catch(Exception ex)
        {
            System.out.println(ex);
        }

    }


    
      

    
}
