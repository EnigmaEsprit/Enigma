/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Utilisateur;

import database.MyDB;
import entites.Utilisateur.Client;
import Util.Util;
import entites.Utilisateur.authentification;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import service.Utilisateur.ClientService;

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
    private Button espClient1;
    @FXML
    private Button espVendeur1;
    @FXML
    private Button espAdmin1;
    @FXML
    private Pane menu2;
    @FXML
    private Button Log;
    @FXML
    private Button Event;
    @FXML
    private Pane menu3;
    @FXML
    private Button recherche;
    @FXML
    private Button Maps;
    @FXML
    private Button Contacts;
    @FXML
    private Button Reclamation;
    @FXML
    private ImageView profil;
    private Image image;

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
     menu3.setVisible(false);
    }

    public void myFunction() {
        
             nom.setText(Util.connectedUser.getNom());
                prenom.setText(Util.connectedUser.getPrenom());
                datedenaissance.setText(Util.connectedUser.getDate_naissance());
                sexe.setText(Util.connectedUser.getSexe());
                adresse.setText(Util.connectedUser.getAdresse());
                ville.setText(Util.connectedUser.getVille());
                zip.setText(String.valueOf(Util.connectedUser.getZip()));
                email.setText(Util.connectedUser.getEmail());
                numerodecardbancaire.setText(Util.connectedUser.getNbc());
                datedevalidation.setText(Util.connectedUser.getDate_validation());
                nt.setText(Util.connectedUser.getNt());
                if(Util.connectedUser.getImg()==null)
                {
                    image= new Image("http://localhost/uimg/user.jpg");
                profil.setImage(image);
                }
                else
                {
                image= new Image("http://localhost/uimg/"+Util.connectedUser.getImg());
                profil.setImage(image);
                }
              
        }
     

    @FXML
    private void btnSupprimerCompteAction(ActionEvent event) {
        ClientService cs = new ClientService();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Voulez vous supprimer votre compte?");
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                cs.supprimerClient(Util.connectedUser.getEmail());

                 try {
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
            
            Parent root = (Parent) loader.load();
           
             
            Stage window;
            window = (Stage) ((Node) event.getSource()).getScene().getWindow();

            window.setScene(new Scene(root));
            window.setResizable(false);

            window.show();
            Util.connectedUser=null;
           
        } catch (IOException ex) {
            Logger.getLogger(ClientInterfaceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
           
                
            }    
        });
        
                 } 

    @FXML
    private void btnEditerCompteAvtion(ActionEvent event) {
        try {
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ClientEditeInterface.fxml"));
            Parent root = (Parent) loader.load();
            ClientEditeInterfaceController contr = loader.getController();
             contr.myFunction();
            Stage window;
            window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(new Scene(root));
            window.setResizable(false);
            window.show();
        } catch (IOException ex) {
            Logger.getLogger(ClientInterfaceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void btnLogoutAction(ActionEvent event) {
        Util.connectedUser=null;
         LoadWindowParent("Login.fxml", event);
    }

      @FXML
    private void btnespClientAction(ActionEvent event) {
        Util.connectedUser=null;
          LoadWindowParent("Login.fxml", event);
    }

    @FXML
    private void btnespVendeurAction(ActionEvent event) {
        if(Util.connectedUserVendeur==null){
            LoadWindowParent("LoginVendeur.fxml", event);
        }
        else 
        {
              try {
                
                FXMLLoader loader = new FXMLLoader(getClass().getResource("VendeurInterface.fxml"));
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
    } @FXML
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


    @FXML
    private void exitemenu3(MouseEvent event) {
        menu3.setOnMouseEntered(evt -> {menu3.setVisible(true);});
         menu3.setOnMouseExited(evt -> {menu3.setVisible(false);});
       
        menu3.setVisible(false);
    }

    @FXML
    private void afficherSuitemenu3(MouseEvent event) {
         menu.setVisible(true);
        menu3.setVisible(true);
 
    }

    @FXML
    private void btnRechercheAction(ActionEvent event) {
        LoadWindowParent("/GUI/Decouverte/RechercheContactInterface.fxml", event);
        
    }

    @FXML
    private void btnMapsAction(ActionEvent event) {
        LoadWindowParent("/GUI/Decouverte/Maps.fxml", event);
    }

       @FXML
    private void PanierFenetre(ActionEvent event) {
        LoadWindowParent("/GUI/Panier/FXMLPanierInterface.fxml",event);
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
            Logger.getLogger(ClientInterfaceController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    @FXML
    private void btnReclamation(ActionEvent event) throws IOException {
           LoadWindowParent("/GUI/Reclamation/EnvoiDesReclamations.fxml", event);
    }

    @FXML
    private void btnCataloguesAction(ActionEvent event) {
         LoadWindowParent("/GUI/Produits/AfficheListProduits.fxml", event);
    }

    @FXML
    private void btnBoutique(ActionEvent event) {
        LoadWindowParent("/GUI/Magasins/AfficheMagasins.fxml", event);
    }

}
