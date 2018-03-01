/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Utilisateur;

import Util.Util;
import entites.Utilisateur.authentification;
import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
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
import javafx.scene.control.PasswordField;
import javafx.scene.input.DragEvent;
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
 * @author user
 */
public class ChangerPasswordVendeurController implements Initializable {

    @FXML
    private Button annuler;
    @FXML
    private Button Editer;
    private PasswordField passwordactuelle;
    @FXML
    private PasswordField password;
    @FXML
    private PasswordField cpassword;
    @FXML
    private Label labmsg;
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
    private String email;
    @FXML
    private Pane menu3;
    @FXML
    private Button recherche;
    @FXML
    private Button Maps;
    @FXML
    private Button Contacts;

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj); //To change body of generated methods, choose Tools | Templates.
    }
  

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
  
        email=mail;

        }

    
    @FXML
    private void btnAnnulerAction(ActionEvent event) throws IOException {
           try {
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminEditVendeurInterface.fxml"));
            Parent root = (Parent) loader.load();
            AdminEditVendeurInterfaceController contr = loader.getController();
            VendeurService vs = new VendeurService();
             contr.myFunction(vs.rechercheParMail(email));
            Stage window;
            window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(new Scene(root));
            window.setResizable(false);
            window.show();
        } catch (IOException ex) {
            Logger.getLogger(AdminEditVendeurInterfaceController.class.getName()).log(Level.SEVERE, null, ex);
        }

           
    }

    @FXML
    private void btnEditAction(ActionEvent event) throws NoSuchAlgorithmException {
         
         String NMDPHASH =ServiceHash.hashPassword(password.getText());
         String CNMDPHASH =ServiceHash.hashPassword(cpassword.getText());
        
         
         System.out.println(NMDPHASH);
         System.out.println(CNMDPHASH);
        if (NMDPHASH.equals(CNMDPHASH)&&password.getText().length()!=0&&cpassword.getText().length()!=0 )
        {
            VendeurService vs = new VendeurService();
            if(vs.modifierPasswordVendeur(email,NMDPHASH))
            {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("INSCRIPTION");
            alert.setHeaderText("Bienvenue");
            alert.setContentText("Password modifier!");
            alert.showAndWait();
            }
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
            Logger.getLogger(ChangerPasswordVendeurController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }


}
