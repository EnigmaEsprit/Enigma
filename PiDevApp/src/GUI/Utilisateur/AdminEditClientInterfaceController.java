/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Utilisateur;


import Util.Util;
import entites.Utilisateur.Client;
import entites.Utilisateur.authentification;
import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import service.Utilisateur.AdminService;
import service.Utilisateur.ClientService;
import service.Utilisateur.ServiceHash;
import service.Utilisateur.ValidationService;
import service.Utilisateur.VendeurService;

/**
 * FXML Controller class
 *
 * @author user
 */
public class AdminEditClientInterfaceController implements Initializable {

    @FXML
    private PasswordField password;
    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private DatePicker datedenaissance;
    @FXML
    private ComboBox<String> sexe;
    @FXML
    private TextField adresse;
    @FXML
    private TextField ville;
    @FXML
    private TextField zip;
    @FXML
    private TextField numerodetelephone;
    @FXML
    private TextField email;
    private Label labmsg;
    @FXML
    private Label id;
        ObservableList<String> sexec = FXCollections.observableArrayList("Homme", "Femme");
        ObservableList<String> sf = FXCollections.observableArrayList("Mauvaise", "Moyenne","Bien","Tres Bien");
    @FXML
    private PasswordField cpassword;
    @FXML
    private Button annuler;
    @FXML
    private Button Editer;
    @FXML
    private Label labmsg1;
    @FXML
    private Pane menu;
    @FXML
    private Pane menu2;
    @FXML
    private Pane menu3;
    @FXML
    private Button Maps;
    @FXML
    private Button recherche;
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
        
        sexe.setItems(sexec);
        nom.setEditable(false);
        prenom.setEditable(false);
        datedenaissance.setEditable(false);
        sexe.setEditable(false);
        adresse.setEditable(false);
        ville.setEditable(false);
        zip.setEditable(false);
        email.setEditable(false);
        numerodetelephone.setEditable(false);
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

    public void myFunction(Client c) {
        
        
       String dateFromDB = c.getDate_naissance();
        int year=Integer.parseInt(dateFromDB.split("-")[0]); 
        int mounth=Integer.parseInt(dateFromDB.split("-")[1]); 
        int day=Integer.parseInt(dateFromDB.split("-")[2]); 

          
                id.setText(String.valueOf(c.getId()));
                nom.setText(c.getNom());
                prenom.setText(c.getPrenom());
           datedenaissance.setValue(LocalDate.of(year, mounth, mounth));
                sexe.getSelectionModel().select(c.getSexe());
                adresse.setText(c.getAdresse());
                ville.setText(c.getVille());
                zip.setText(String.valueOf(c.getZip()));
                email.setText(c.getEmail());
              
                
               
                numerodetelephone.setText(c.getNt());
               
              
            
    }
    @FXML
    private void btnAnnulerAction(ActionEvent event) {
          LoadWindowParent("AdminInterface.fxml", event);
    }

    @FXML
    private void btnEditAction(ActionEvent event) throws NoSuchAlgorithmException {
          if (password.getText().length() == 0 || cpassword.getText().length()==0)
                
                {
            labmsg.setText("Verfier Votre saisir");
           
           
        } 
        else
          {
             if(password.getText().equals(cpassword.getText()))
             {
                AdminService as = new AdminService();
            if(as.UpdateClient(email.getText(),ServiceHash.hashPassword(password.getText())))
            {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("INSCRIPTION");
            alert.setHeaderText("modifier!");
            
            alert.showAndWait();
             try {
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminInterface.fxml"));
            Parent root = (Parent) loader.load();
           
            Stage window;
            window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(new Scene(root));  window.setResizable(false);
            window.show();
        } catch (IOException ex) {
            Logger.getLogger(ClientInterfaceController.class.getName()).log(Level.SEVERE, null, ex);
        } 
             }
             else
             {
                  Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Attention");
            alert.setHeaderText("verifier stp"); 
             }
          }
             else
             {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Attention");
            alert.setHeaderText("verifier password");
             }
              
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
                window.setScene(new Scene(root));  window.setResizable(false);
                window.show();

            } catch (IOException ex) {
                System.out.println("catch : " + ex.getMessage());
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
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
                window.setScene(new Scene(root));  window.setResizable(false);
                window.show();

            } catch (IOException ex) {
                System.out.println("catch : " + ex.getMessage());
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }

    }
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
        
        LoadWindowParent("/GUI/Panier/FXMLPanierInterface.fxml", event);
    }

    
    private void LoadWindow(String loc, String title) {
        try {
            Parent homePageParent = FXMLLoader.load(getClass().getResource(loc));
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle(title);
            stage.setScene(new Scene(homePageParent));stage.setResizable(false);
            stage.show();

        } catch (IOException ex) {
            Logger.getLogger(AdminEditClientInterfaceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void LoadWindowParent(String loc,ActionEvent event){
        try {
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource(loc));
            
            Parent root = (Parent) loader.load();
           
             
            Stage window;
            window = (Stage) ((Node) event.getSource()).getScene().getWindow();

            window.setScene(new Scene(root));  window.setResizable(false);

            window.show();

           
        } catch (IOException ex) {
            Logger.getLogger(ClientInterfaceController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    @FXML
    private void btnEventAction(ActionEvent event) {
    }
   }
