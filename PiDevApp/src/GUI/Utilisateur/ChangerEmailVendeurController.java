/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Utilisateur;

import Util.Util;
import entites.Utilisateur.Vendeur;
import entites.Utilisateur.authentification;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
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
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import service.Utilisateur.ClientService;
import service.Utilisateur.SendEmail;
import service.Utilisateur.ValidationService;
import service.Utilisateur.VendeurService;

/**
 * FXML Controller class
 *
 * @author user
 */
public class ChangerEmailVendeurController implements Initializable {

    @FXML
    private Button annuler;
    @FXML
    private Button Editer;
    @FXML
    private Label labmsg;
    @FXML
    private TextField email;
    @FXML
    private TextField cemail;
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
    private String emaill;
    @FXML
    private Pane menu3;
    @FXML
    private Button recherche;
    @FXML
    private Button Maps;
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
     menu3.setVisible(false);
    }    
  public void myFunction(String mail) {
  
        emaill=mail;

        }
    @FXML
    private void btnAnnulerAction(ActionEvent event) throws IOException {
           FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminEditVendeurInterface.fxml"));
                Parent root = (Parent) loader.load();
                AdminEditVendeurInterfaceController ClientIn = loader.getController();
               VendeurService vs = new VendeurService();
                ClientIn.myFunction(vs.rechercheParMail(emaill));
                Stage window;
                window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(new Scene(root));
                window.show();

    }

    @FXML
    private void btnEditAction(ActionEvent event) {
        VendeurService vs = new VendeurService();
       System.out.println(vs.recherche(email.getText()));
       
        boolean mailExist=false;
        mailExist=vs.recherche(email.getText().toString());
        System.out.println("exist:  "+ mailExist);
        if((email.getText().equals(cemail.getText()))&&(!email.getText().equals(emaill))&&(mailExist==false)&&(email.getText().length()!=0)&&(cemail.getText().length()!=0)&&ValidationService.email_Validation(email.getText())&&ValidationService.email_Validation(cemail.getText()))
        {
            
                if(vs.modifierEmailVendeur(email.getText(),emaill)==true)
                {
                   Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Email Change");
            alert.setHeaderText("Bienvenue");
            alert.setContentText("Mail modifier!");
            alert.showAndWait();  
                    Vendeur v = vs.rechercheParMail(email.getText());
                    try {
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminEditVendeurInterface.fxml"));
            Parent root = (Parent) loader.load();
            AdminEditVendeurInterfaceController contr = loader.getController();
           
             contr.myFunction(v);
            Stage window;
            window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(new Scene(root));
            window.show();
           
        } catch (IOException ex) {
            Logger.getLogger(ClientInterfaceController.class.getName()).log(Level.SEVERE, null, ex);
        }
                }
                else{
                       Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Email Change");
            alert.setHeaderText("Bienvenue");
            alert.setContentText("Erreur de modifier d'un E-mail!");
            alert.showAndWait();
                }
           
          
            
            
        }
        else
        {
            labmsg.setText("Verfier votre Email!!");
        }
    }
             @FXML
    private void btnespClientAction(ActionEvent event) {
              System.out.println(Util.connectedUser);
        if (Util.connectedUser==null)
        {
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
        try {
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/Utilisateur/LoginVendeur.fxml"));
            
            Parent root = (Parent) loader.load();
           
             
            Stage window;
            window = (Stage) ((Node) event.getSource()).getScene().getWindow();

            window.setScene(new Scene(root));

            window.show();

           
        } catch (IOException ex) {
            Logger.getLogger(ChangeEmailController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ChangeEmailController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/Decouverte/RechercheContactInterface.fxml"));
            
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
    private void btnMapsAction(ActionEvent event) {
        try {
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/Decouverte/Maps.fxml"));
            
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

            window.show();

           
        } catch (IOException ex) {
            Logger.getLogger(ChangerEmailVendeurController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
}
