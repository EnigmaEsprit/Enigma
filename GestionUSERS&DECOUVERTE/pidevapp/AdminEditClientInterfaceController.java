/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidevapp;

import entites.Client;
import entites.authentification;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import service.AdminService;
import service.ClientService;
import service.ValidationService;

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
    @FXML
    private Label labmsg;
    @FXML
    private Label id;
        ObservableList<String> sexec = FXCollections.observableArrayList("Homme", "Femme");
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
          try {
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminInterface.fxml"));
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
    private void btnEditAction(ActionEvent event) {
          if (password.getText().length() == 0)
                
                {
            labmsg.setText("Verfier Votre saisir");
           
           
        } 
        else
          {
             if(!password.getText().equals(cpassword.getText())||password.getText().length()==0)
             {
                AdminService as = new AdminService();
            as.UpdateClient(id.getText(),password.getText());

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("INSCRIPTION");
            alert.setHeaderText("modifier!");
            
            alert.showAndWait();
             try {
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminInterface.fxml"));
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
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Attention");
            alert.setHeaderText("verifier password");
             }
              
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
