/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidevapp;

import entites.Client;
import entites.Vendeur;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import service.AdminService;
import service.ClientService;
import service.ValidationService;
import service.VendeurService;

/**
 * FXML Controller class
 *
 * @author user
 */
public class AdminEditVendeurInterfaceController implements Initializable {

    @FXML
    private PasswordField password;
    @FXML
    private Button anuller;
    @FXML
    private Label id;
    @FXML
    private PasswordField cpassword;
    @FXML
    private TextField rib;
    @FXML
    private ComboBox<String> situationfiscal;
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
    private Button editer;
    @FXML
    private Label labmsg;
    @FXML
    private TextField cemail;
    public String eemail;
    /**
     * Initializes the controller class.
     */
    public void myFunction(Vendeur v) {
        
        
       
        String dateFromDB = v.getDate_naissance();
        int year=Integer.parseInt(dateFromDB.split("-")[0]); 
        int mounth=Integer.parseInt(dateFromDB.split("-")[1]); 
        int day=Integer.parseInt(dateFromDB.split("-")[2]); 
          
                id.setText(String.valueOf(v.getId()));
                nom.setText(v.getNom());
                prenom.setText(v.getPrenom());
               datedenaissance.setValue(LocalDate.of(year, mounth, mounth));
                sexe.getSelectionModel().select(v.getSexe());
                adresse.setText(v.getAdresse());
                ville.setText(v.getVille());
                zip.setText(String.valueOf(v.getZip()));
                email.setText(v.getEmail());
                cemail.setText(v.getEmail());
                password.setText(v.getMdp());
                cpassword.setText(v.getMdp());
                numerodetelephone.setText(v.getNt());
                situationfiscal.getSelectionModel().select(v.getSexe());
                rib.setText(v.getNumero_bancaire());
                eemail=v.getEmail();
                
               
              
            
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
                || (situationfiscal.getValue().length()==0)
                ||(rib.getText().length()!=18)) 
              {
            labmsg.setText("Verfier votre champs!");
            
        } 
        AdminService as = new AdminService();
        if ((eemail.equals(email.getText())==false) && (as.recherche(email.getText())==true)){
            
              
                    labmsg.setText("Verifier votre mail!");
                }
        else if((eemail.equals(email.getText())==false) && (as.recherche(email.getText())==false))
        {
             System.out.println("********************"+numerodetelephone.getText());
            String dn = datedenaissance.getValue().toString();
            
            
            Vendeur v = new Vendeur(nom.getText(), prenom.getText(), dn, sexe.getValue(), adresse.getText(), ville.getText(), Integer.valueOf(zip.getText()), numerodetelephone.getText(), email.getText(), password.getText(), situationfiscal.getValue(),rib.getText());
           
            as.modifierVendeur(v,eemail);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("INSCRIPTION");
            alert.setHeaderText("Bienvenue");
            alert.setContentText("modifier!");
            alert.showAndWait();
                }
        
            else
            {
                String dn = datedenaissance.getValue().toString();
              Vendeur v = new Vendeur(nom.getText(), prenom.getText(), dn, sexe.getValue(), adresse.getText(), ville.getText(), Integer.valueOf(zip.getText()), numerodetelephone.getText(), email.getText(), password.getText(), situationfiscal.getValue(),rib.getText());

                
            as.modifierVendeur(v,eemail);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("INSCRIPTION");
            alert.setHeaderText("Bienvenue");
            alert.setContentText("modifier!");
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

              
    }

    private AdminService AdminService() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
