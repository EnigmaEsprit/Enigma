/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidevapp;

import entites.Client;
import entites.Vendeur;
import entites.authentification;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.stage.Stage;
import service.ClientService;
import service.ServiceAuthentification;
import service.ValidationService;
import service.VendeurService;

/**
 * FXML Controller class
 *
 * @author user
 */
public class AjouterVendeurByAdminInterfaceController implements Initializable {

    @FXML
    private PasswordField cpassword;
    @FXML
    private Button inscrit;
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
    private TextField cemail;
    @FXML
    private PasswordField password;
    @FXML
    private ComboBox<String> situationfiscal;
    @FXML
    private Label labmsg;
    @FXML
    private Label im;
    ObservableList<String> sexec = FXCollections.observableArrayList("Homme", "Femme");
     ObservableList<String> sf = FXCollections.observableArrayList("Mauvaise", "Moyenne","Bien","Tres Bien");
    @FXML
    private TextField rib;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
                sexe.setItems(sexec);
                situationfiscal.setItems(sf);

    }    

    @FXML
    private void btnInscritAction(ActionEvent event) {
    
      if ((nom.getText().length() == 0)
                || (prenom.getText().length() == 0)
                || (datedenaissance.getEditor().getText().length() == 0)
                || (sexe.getValue().length() == 0)
                || (adresse.getText().length() == 0)
                || (ville.getText().length() == 0)
                || (zip.getText().length() == 0)
                || !(ValidationService.numerique_Validation(zip.getText()))
                || (numerodetelephone.getText().length() != 8)
                || !(ValidationService.numerique_Validation(numerodetelephone.getText()))
                || !(ValidationService.email_Validation(email.getText()))
                || !(email.getText().equals(cemail.getText()))
                || (password.getText().length() == 0)
                || (cpassword.getText().length() == 0)
                || !(password.getText().equals(cpassword.getText()))
                ||(situationfiscal.getValue().length() == 0)
                ||(rib.getText().length()!=18)
                ) {
            labmsg.setText("Verfier votre champs!");
      }else{
       String dn = datedenaissance.getValue().toString();
         
            Vendeur v = new Vendeur(nom.getText(), prenom.getText(), dn, sexe.getValue(), adresse.getText(), ville.getText(), Integer.valueOf(zip.getText()), numerodetelephone.getText(), email.getText(), password.getText(), situationfiscal.getValue(), rib.getText());
           System.out.println("-------------------------"+v);
            VendeurService vs = new VendeurService();
           
            if (vs.ajouterVendeur(v) == false) {
                labmsg.setText("Verfier votre email deja utiliser!");
            } else {
                 labmsg.setText("Bienvenue!");
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("INSCRIPTION");
                alert.setHeaderText("Bienvenue");
                alert.setContentText("Vous etes inscript!");
                alert.showAndWait();
        
                 try {
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminInterface.fxml"));
            
            Parent root = (Parent) loader.load();
          

             
            Stage window;
            window = (Stage) ((Node) event.getSource()).getScene().getWindow();

            window.setScene(new Scene(root));

            window.show();

           
        } catch (IOException ex) {
            Logger.getLogger(AdminInterfaceController.class.getName()).log(Level.SEVERE, null, ex);
        }
            }
            System.out.println(vs.ajouterVendeur(v));
            // labmsg.setText("bienvenue!");
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

    @FXML
    private void btnAnnulerAction(ActionEvent event) {
    }
}
