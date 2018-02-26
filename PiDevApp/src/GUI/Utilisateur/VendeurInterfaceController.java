/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Utilisateur;

import entites.Utilisateur.Client;
import Util.Util;
import entites.Utilisateur.Vendeur;
import entites.Utilisateur.authentification;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import service.Utilisateur.ClientService;
import service.Utilisateur.VendeurService;

/**
 * FXML Controller class
 *
 * @author user
 */
public class VendeurInterfaceController implements Initializable {

    @FXML
    private Label labmsg;
    @FXML
    private Label im;
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
    private Label nom;
    @FXML
    private Label prenom;
    @FXML
    private Label testLabel;
    @FXML
    private Label sf;
    @FXML
    private Label rib;
    @FXML
    private Label nt;
    @FXML
    private Pane menu;
    @FXML
    private Pane menu2;
    @FXML
    private Button espClient1;
    @FXML
    private Button espVendeur1;
    @FXML
    private Button espAdmin1;
    @FXML
    private Button Log;
    @FXML
    private Button Event;
    private Pane menu3;
    @FXML
    private Button Editer;
    @FXML
    private Button Reclamation;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
      
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
     menu2.setVisible(false);
  
    }    
    public void myFunction() {
       
           
                nom.setText(Util.connectedUserVendeur.getNom());
                prenom.setText(Util.connectedUserVendeur.getPrenom());
                datedenaissance.setText(Util.connectedUserVendeur.getDate_naissance());
                sexe.setText(Util.connectedUserVendeur.getSexe());
                adresse.setText(Util.connectedUserVendeur.getAdresse());
                ville.setText(Util.connectedUserVendeur.getVille());
                zip.setText(String.valueOf(Util.connectedUserVendeur.getZip()));
                email.setText(Util.connectedUserVendeur.getEmail());
                 sf.setText(Util.connectedUserVendeur.getSutiation_fiscal());
                 rib.setText(Util.connectedUserVendeur.getNumero_bancaire());
                nt.setText(Util.connectedUserVendeur.getNt());
              
          
    }
    private void btnLogoutAction(ActionEvent event) {
        try {
            Util.connectedUserVendeur = null;
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
    private void btnespClientAction(ActionEvent event) {
              System.out.println(Util.connectedUser);
        if (Util.connectedUser==null)
        {
            LoadWindowParent("Login.fxml", event);
        }
        else
        {
           try {
              
                FXMLLoader loader = new FXMLLoader(getClass().getResource("ClientInterface.fxml"));
                Parent root = (Parent) loader.load();
                ClientInterfaceController ClientIn = loader.getController();
                ClientIn.myFunction();
                Stage window;
                window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(new Scene(root));
                window.show();

            } catch (IOException ex) {
                System.out.println("catch : " + ex.getMessage());
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        }


    @FXML
    private void btnespVendeurAction(ActionEvent event) {
        Util.connectedUserVendeur=null;
        LoadWindowParent("LoginVendeur.fxml", event);
        
    }
 @FXML
    private void btnespAdminAction(ActionEvent event) {
     LoadWindowParent("LoginAdmin.fxml", event);
    }

      @FXML
    private void afficherSuite(MouseEvent event) {
        menu.setVisible(true);
        menu2.setVisible(true);
 
    }

    @FXML
    private void exite(MouseEvent event) {
         menu2.setOnMouseEntered(evt -> {menu2.setVisible(true);});
         menu2.setOnMouseExited(evt -> {menu2.setVisible(false);});
       
        menu2.setVisible(false);
    }

    @FXML
    private void btnEventAction(ActionEvent event) {
    }

    private void exitemenu3(MouseEvent event) {
        menu3.setOnMouseEntered(evt -> {menu3.setVisible(true);});
         menu3.setOnMouseExited(evt -> {menu3.setVisible(false);});
       
        menu3.setVisible(false);
    }

    private void afficherSuitemenu3(MouseEvent event) {
         menu.setVisible(true);
        menu3.setVisible(true);
 
    }

    private void btnRechercheAction(ActionEvent event) {
        LoadWindowParent("/GUI/Decouverte/RechercheContactInterface.fxml", event);
        
    }

    private void btnMapsAction(ActionEvent event) {
        LoadWindowParent("/GUI/Decouverte/Maps.fxml", event);
    }

    
    
    private void LoadWindowParent(String loc,ActionEvent event){
        try {
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource(loc));
            
            Parent root = (Parent) loader.load();
           
             
            Stage window;
            window = (Stage) ((Node) event.getSource()).getScene().getWindow();

            window.setScene(new Scene(root));

            window.show();

           
        } catch (IOException ex) {
            Logger.getLogger(VendeurInterfaceController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

 

    @FXML
    private void statswindow(ActionEvent event) {
          LoadWindowParent("/GUI/Panier/FXMLStatistiques.fxml", event);
    }

    @FXML
    private void CommandesFenetre(ActionEvent event) {
         LoadWindowParent("/GUI/Panier/FXMLCommandesInterface.fxml", event);
    }

    @FXML
    private void btnChangePasswordAction(ActionEvent event) {
        LoadWindowParent("ChangerPassowrdVendeurV.fxml", event);
        
    }

    @FXML
    private void btnReclamationAction(ActionEvent event) {
    }
}
