/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Reclamation;

import GUI.Utilisateur.ClientInterfaceController;
import GUI.Utilisateur.LoginController;
import GUI.Utilisateur.VendeurInterfaceController;
import Util.Util;
import com.jfoenix.controls.JFXButton;
import entites.Reclamation.Reclamations;
import entites.Utilisateur.Vendeur;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import service.Reclamation.ReclamationsServices;

/**
 * FXML Controller class
 *
 * @author user
 */
public class ReponseReclamationController implements Initializable {

    @FXML
    private Label labelIdentifiant;
    @FXML
    private Label clientConcerne;
    @FXML
    private Label emailClientConcerne;
    @FXML
    private TextArea ecranReponseReclamation;
    @FXML
    private JFXButton btnEnvoiReponse;
    @FXML
    private JFXButton btnannuler;
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
    @FXML
    private Button Reclamation;
    InterfaceReclamationsClientsController ircc = new InterfaceReclamationsClientsController();
    InterfaceEnvoiDesReclamationsController iedr = new InterfaceEnvoiDesReclamationsController();
    Reclamations rc = new Reclamations();
    ReclamationsServices rs = new ReclamationsServices();
    Vendeur vd;
    

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
     
         rc = ReclamationsClientsController.reclamationATraiter ;
        vd = ReclamationsClientsController.vendeurCte;
        clientConcerne.setText(rc.getNomClient());
        labelIdentifiant.setText("Hello, " + vd.getPrenom() + " !");
        emailClientConcerne.setText("<" + rc.getEmailClient() + ">");
    }    

    @FXML
    private void annulerReclamtion(ActionEvent event) {
    }


    @FXML
    private void afficherSuite(MouseEvent event) {
        menu.setVisible(true);
        menu2.setVisible(true);
    }

    @FXML
    private void btnespClientAction(ActionEvent event) {
                 System.out.println(Util.connectedUser);
        if (Util.connectedUser==null)
        {
            LoadWindowParent("/GUI/Utilisateur/Login.fxml", event);
        }
        else
        {
           try {
              
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/Utilisateur/ClientInterface.fxml"));
                Parent root = (Parent) loader.load();
                ClientInterfaceController ClientIn = loader.getController();
                ClientIn.myFunction();
                Stage window;
                window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(new Scene(root));
                window.setResizable(false);
                window.show();

            } catch (IOException ex) {
                System.out.println("catch : " + ex.getMessage());
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    @FXML
    private void btnespVendeurAction(ActionEvent event) {
             try {
            Util.connectedUserVendeur = null;
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/Utilisateur/LoginVendeur.fxml"));
            
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
                             LoadWindowParent("/GUI/Utilisateur/LoginAdmin.fxml", event);

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


    @FXML
    private void btnEnvoyerReponse(ActionEvent event) {
        if (!(ecranReponseReclamation.getText().trim().length() > 0 ))
            iedr.dialog(Alert.AlertType.ERROR, "Erreur", "Veuillez renseigner une réponse à envoyer");
        else {
            String message = ecranReponseReclamation.getText();
            String destinataire = emailClientConcerne.getText();
            rs.repondreAUneReclamation(destinataire, message, 35);
            iedr.dialog(Alert.AlertType.INFORMATION, "Info", "Votre réponse a été envoyé !");
            btnespVendeurAction(event);
        }
    }
     private void LoadWindowParent(String loc,ActionEvent event){
        try {
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource(loc));
            
            Parent root = (Parent) loader.load();
           
             
            Stage window;
            window = (Stage) ((Node) event.getSource()).getScene().getWindow();

            window.setScene(new Scene(root));
            window.setResizable(false);

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
    private void btnReclamationAction(ActionEvent event) {
          LoadWindowParent("/GUI/Reclamation/ReclamationsClients.fxml", event);
    }

}
