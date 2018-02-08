/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev;

import entites.Client;
import entites.Utilisateur;
import entites.Vendeur;
import java.sql.Date;
import service.ClientService;
import service.VendeurService;

/**
 *
 * @author user
 */
public class PiDev {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        ClientService cs = new ClientService();
        
       Client c;
        c = new Client("Boumaiza","Oussama",new Date(1993,04,03),"rue du grand maghreb Dar Chaabane Fehrie","nabeul", 8011,"boumaizaoussama@gmail.com","starac","C:/img","54788969852014",new java.sql.Date(20180402), 125);
       // Client c1 = new Client("boumaiza", "oussama", new Date(19930403), "rue du grand maghreb", "nabeul", 8011, "oussama@gmail.com", "147852", "C:\image\oussama", "12345678901234", new Date(20180502), 854);
       // System.out.println(c);
       // c1 = new Client("Boumaiza", "Saif", new Date(19890103), "rue du grand maghreb", "Nabeul", 8011, "saif@yahoo.fr", "123", "C:\img1", "12345678901234", new Date(20180901),158);
        
        //cs.ajouterClient(c);
      //  cs.supprimerClient(c);
      //  cs.afficherClient(c); 
       // cs.modifierClient(c);
        //cs.modifierClientImg(c, "C:/oussama/img/1");
       // cs.afficherClient(c);
        VendeurService vs= new VendeurService();
       Vendeur v = new Vendeur("salwa", "laibi", new Date(19690112), "rue du grand maghreb", "nabeul", 8011, "laribisalwa@yahoo.fr", "salwa","D:/img", "exellent", "152465547852314");
     //  vs.ajouterVendeur(v);
      //vs.modifierVendeur(v);
      //vs.modifierVendeurImg(v, "C/Salwa/img");
      vs.supprimerVendeur(v);
      vs.afficherVendeur(v);
        
    }
    
}
