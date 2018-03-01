/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Utilisateur;

import GUI.Utilisateur.ClientEditeInterfaceController;
import GUI.Utilisateur.ClientInterfaceController;
import GUI.Utilisateur.LoginController;
import GUI.Utilisateur.VendeurInterfaceController;
import Util.Util;
import entites.Utilisateur.authentification;
import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
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
import javafx.scene.control.PasswordField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import service.Utilisateur.ClientService;
import service.Utilisateur.ServiceHash;
import service.Utilisateur.VendeurService;

/**
 * FXML Controller class
 *
 * @author jean
 */
public class ChangerPassowrdVendeurVContoller implements Initializable {

    @FXML
    private Button annuler;
    @FXML
    private Button Editer;
    @FXML
    private PasswordField passwordactuelle;
    @FXML
    private PasswordField password;
    @FXML
    private PasswordField cpassword;
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
    private Button Contacts;

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

    @FXML
    private void btnAnnulerAction(ActionEvent event) throws IOException {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("VendeurInterface.fxml"));
                Parent root = (Parent) loader.load();
                VendeurInterfaceController VendIn = loader.getController();
                authentification ath = new authentification(Util.connectedUserVendeur.getEmail(), Util.connectedUserVendeur.getMdp());
                VendIn.myFunction();
                Stage window;
                window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(new Scene(root));
                window.setResizable(false);
                window.show();
    }

    @FXML
    private void btnEditAction(ActionEvent event) throws NoSuchAlgorithmException, IOException {
          String MDPHASH =ServiceHash.hashPassword(passwordactuelle.getText());
         String NMDPHASH =ServiceHash.hashPassword(password.getText());
         String CNMDPHASH =ServiceHash.hashPassword(cpassword.getText());
         System.out.println(Util.connectedUserVendeur.getMdp());
         System.out.println(MDPHASH);
         System.out.println(NMDPHASH);
         System.out.println(CNMDPHASH);
        if (MDPHASH.equals(Util.connectedUserVendeur.getMdp())&&NMDPHASH.equals(CNMDPHASH)&& passwordactuelle.getText().length()!=0&&password.getText().length()!=0&&cpassword.getText().length()!=0 )
        {
            VendeurService vs = new VendeurService();
            if(vs.modifierPasswordVendeur(NMDPHASH))
            {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("INSCRIPTION");
            alert.setHeaderText("Bienvenue");
            alert.setContentText("Password modifier!");
            alert.showAndWait();
            }
             FXMLLoader loader = new FXMLLoader(getClass().getResource("VendeurInterface.fxml"));
                Parent root = (Parent) loader.load();
                VendeurInterfaceController VendeurIn = loader.getController();
                authentification ath = new authentification(Util.connectedUserVendeur.getEmail(), Util.connectedUserVendeur.getMdp());
                VendeurIn.myFunction();
                Stage window;
                window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(new Scene(root));
                window.setResizable(false);
                window.show();

        }
        else
        {
               Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("INSCRIPTION");
            alert.setHeaderText("Bienvenue");
            alert.setContentText("Erreur de modifier Password!");
            alert.showAndWait();
        }
    
    }

    @FXML
    private void afficherSuite(MouseEvent event) {
         menu.setVisible(true);
        menu2.setVisible(true);
    }

    @FXML
    private void btnespClientAction(ActionEvent event) {
        if(Util.connectedUser==null){
            LoadWindowParent("Login.fxml", event);
        }
        else 
        {
              try {
                
                FXMLLoader loader = new FXMLLoader(getClass().getResource("ClientInterface.fxml"));
                Parent root = (Parent) loader.load();
                VendeurInterfaceController ClientIn = loader.getController();
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
        Util.connectedUserVendeur=null;
         LoadWindowParent("LoginVendeur.fxml", event);
    }

    @FXML
    private void btnespAdminAction(ActionEvent event) {
        LoadWindowParent("LoginAdmin.fxml", event);
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
    private void exite(MouseEvent event) {
         menu2.setOnMouseEntered(evt -> {menu2.setVisible(true);});
         menu2.setOnMouseExited(evt -> {menu2.setVisible(false);});
       
        menu2.setVisible(false);
    }

    @FXML
    private void btnEventAction(ActionEvent event) {
    }

    @FXML
    private void exitemenu3(MouseEvent event) {
    }

    @FXML
    private void afficherSuitemenu3(MouseEvent event) {
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
    private void ReclamationAction(ActionEvent event) {
          LoadWindowParent("/GUI/Reclamation/ReclamationsClients.fxml", event);
    }

    @FXML
    private void btnCataloguesAction(ActionEvent event) {
         LoadWindowParent("/GUI/Produits/AjoutArticle.fxml", event);
    }
}
