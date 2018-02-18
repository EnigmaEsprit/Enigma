/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidevapp;

import database.MyDB;
import entites.Client;
import entites.Util;
import entites.authentification;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import service.ClientService;

/**
 * FXML Controller class
 *
 * @author user
 */
public class ClientInterfaceController implements Initializable {

    @FXML
    private Label labmsg;
    @FXML
    private Label datedenaissance;
    @FXML
    private Label sexe;
    @FXML
    private Label adresse;
    @FXML
    private Label ville;
    @FXML
    private Label zip;
    @FXML
    private Label email;
    @FXML
    private Label numerodecardbancaire;
    @FXML
    private Label datedevalidation;
    @FXML
    private Label nom;
    @FXML
    private Label prenom;

    MyDB con = MyDB.getInstance();
    @FXML
    private Label nt;
    @FXML
    private Button SupprimerCompte;
    @FXML
    private Button Editer;
    public authentification auth;
    @FXML
    private Pane menu;
    @FXML
    private Button espClient;
    @FXML
    private Button espVendeur;
    @FXML
    private Button espAdmin;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
/*
        LoginController lc = new LoginController();
        String username = lc.name.getText();
        //String password = lc.getPas();

        System.out.println("test \n ------------------");
        System.out.println("username = " + username);
        //System.out.println("password = " + password);

        Statement stm;
        try {
            stm = con.getConnection().createStatement();
            System.out.println("");
            ResultSet res = stm.executeQuery("SELECT * FROM users WHERE type ='client' AND email ='" + username + "'AND password='" + "aa" + "'");
            System.out.println("ok");
            /* if(res.next())
             {
                 nom.setText(res.getString(2));
             }
             
        } catch (SQLException ex) {
            Logger.getLogger(ClientInterfaceController.class.getName()).log(Level.SEVERE, null, ex);
        }
         */
                         menu.setTranslateX(-190);
    TranslateTransition menuTranslation = new TranslateTransition(Duration.millis(500), menu);

    menuTranslation.setFromX(-190);
    menuTranslation.setToX(0);

    menu.setOnMouseEntered(evt -> {
        menuTranslation.setRate(1);
        menuTranslation.play();
    });
    menu.setOnMouseExited(evt -> {
        menuTranslation.setRate(-1);
        menuTranslation.play();
    });
    }

    public void myFunction(authentification o) {
        Statement stm;
        auth=o;
        ClientService cs = new ClientService();
        Client c = cs.rechercheParMail(o.getEmail(),o.getPassword());
        Util.connectedUser= c;
            if(c != null)
            {
                nom.setText(c.getNom());
                prenom.setText(c.getPrenom());
                datedenaissance.setText(c.getDate_naissance());
                sexe.setText(c.getSexe());
                adresse.setText(c.getAdresse());
                ville.setText(c.getVille());
                zip.setText(String.valueOf(c.getZip()));
                email.setText(c.getEmail());
                numerodecardbancaire.setText(c.getNbc());
                datedevalidation.setText(c.getDate_validation());
                nt.setText(c.getNt());
              
            }

                
// String nomg = res.getString(2);

                //System.out.println("Image: "+res.getString(12)+"\nNom: "+res.getString(2)+"\nPrenom: "+res.getString(3)+"\nDate de naissance: "+res.getDate(4)+"\nSexe "+res.getString(5)+"\nAdresse: "+res.getString(6)+"\nVille: "+res.getString(7)+"\nZIP: "+res.getInt(8)+"\nE-mail: "+res.getString(9)+"\nPassword: "+res.getString(10)+"\nNumero de carte bancaire: "+res.getString(13)+"\nDate de validation: "+res.getDate(14)+"\nCode secraite: "+res.getInt(15));
            else {
                System.out.println("non)");
            }
}

     

    @FXML
    private void btnSupprimerCompteAction(ActionEvent event) {
        ClientService cs = new ClientService();
        cs.supprimerClient(email.getText());
        try {
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
            
            Parent root = (Parent) loader.load();
           
             
            Stage window;
            window = (Stage) ((Node) event.getSource()).getScene().getWindow();

            window.setScene(new Scene(root));

            window.show();

           
        } catch (IOException ex) {
            Logger.getLogger(ClientInterfaceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btnEditerCompteAvtion(ActionEvent event) {
        try {
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ClientEditeInterface.fxml"));
            Parent root = (Parent) loader.load();
            ClientEditeInterfaceController contr = loader.getController();
             contr.myFunction(auth);
            Stage window;
            window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(new Scene(root));
            window.show();
        } catch (IOException ex) {
            Logger.getLogger(ClientInterfaceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void btnLogoutAction(ActionEvent event) {
         try {
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
            
            Parent root = (Parent) loader.load();
           
             
            Stage window;
            window = (Stage) ((Node) event.getSource()).getScene().getWindow();

            window.setScene(new Scene(root));

            window.show();

           
        } catch (IOException ex) {
            Logger.getLogger(ClientInterfaceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

      @FXML
    private void btnespClientAction(ActionEvent event) {
try {
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
            
            Parent root = (Parent) loader.load();
           
             
            Stage window;
            window = (Stage) ((Node) event.getSource()).getScene().getWindow();

            window.setScene(new Scene(root));

            window.show();

           
        } catch (IOException ex) {
            Logger.getLogger(ClientInterfaceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btnespVendeurAction(ActionEvent event) {
        try {
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginVendeur.fxml"));
            
            Parent root = (Parent) loader.load();
           
             
            Stage window;
            window = (Stage) ((Node) event.getSource()).getScene().getWindow();

            window.setScene(new Scene(root));

            window.show();

           
        } catch (IOException ex) {
            Logger.getLogger(ClientInterfaceController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
 @FXML
    private void btnespAdminAction(ActionEvent event) {
         try {
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginAdmin.fxml"));
            
            Parent root = (Parent) loader.load();
           
             
            Stage window;
            window = (Stage) ((Node) event.getSource()).getScene().getWindow();

            window.setScene(new Scene(root));

            window.show();

           
        } catch (IOException ex) {
            Logger.getLogger(ClientInterfaceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
