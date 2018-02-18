/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidevapp;

import entites.Client;
import entites.authentification;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.time.LocalDate;
import java.util.Observable;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import service.ClientService;
import service.ServiceAuthentification;
import service.ValidationService;

/**
 * FXML Controller class
 *
 * @author user
 */
public class InscriptionController implements Initializable {

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
    private PasswordField cpassword;
    @FXML
    private TextField numerodecartebancaire;
    @FXML
    private DatePicker datedevalidation;
    @FXML
    private PasswordField ccv;
    @FXML
    private Button inscrit;
    @FXML
    public ImageView imgsouq;
    @FXML
    private Label labmsg;
    @FXML
    public Label im;
    @FXML
    private File f;
    ObservableList<String> sexec = FXCollections.observableArrayList("Homme", "Femme");

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        sexe.setItems(sexec);
    }

    @FXML
    private void btnInscritAction(ActionEvent event) throws ParseException {
        int var;
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
                || !(ValidationService.numerique_Validation(numerodecartebancaire.getText()))
                || (numerodecartebancaire.getText().length() != 14)
                || (datedevalidation.getEditor().getText().length() == 0)
                || !(ValidationService.numerique_Validation(ccv.getText()))
                || (ccv.getText().length() == 0) //((String.valueOf(Integer.valueOf(numerodecartebancaire.getText())).length()!=14)||datedevalidation.getEditor().getText().length()
                ) {
            labmsg.setText("Verfier votre champs!");
        } else {

            String dn = datedenaissance.getValue().toString();
            String dv = datedevalidation.getValue().toString();
            
         /*   SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
            Date daten = formater.parse(dn);
            Date datev = formater.parse(dv);*/
            Client c = new Client(nom.getText(), prenom.getText(), dn, sexe.getValue(), adresse.getText(), ville.getText(), Integer.valueOf(zip.getText()), numerodetelephone.getText(), email.getText(), password.getText(), numerodecartebancaire.getText(), dv, Integer.valueOf(ccv.getText()));
            ClientService cs = new ClientService();
            if (cs.ajouterClient(c) == false) {
                labmsg.setText("Verfier votre email deja utiliser!");
            } else {
                labmsg.setText("Bienvenue!");
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("INSCRIPTION");
                alert.setHeaderText("Bienvenue");
                alert.setContentText("Vous etes inscript!");
                alert.showAndWait();
                authentification aut = new authentification(email.getText(), password.getText());
        ServiceAuthentification ser = new ServiceAuthentification();
                 try {
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ClientInterface.fxml"));
            
            Parent root = (Parent) loader.load();
           ClientInterfaceController ClientIn = loader.getController();
                ClientIn.myFunction(aut);

             
            Stage window;
            window = (Stage) ((Node) event.getSource()).getScene().getWindow();

            window.setScene(new Scene(root));

            window.show();

           
        } catch (IOException ex) {
            Logger.getLogger(ClientInterfaceController.class.getName()).log(Level.SEVERE, null, ex);
        }
            }
            System.out.println(cs.ajouterClient(c));
            // labmsg.setText("bienvenue!");
        }
        // labmsg.setText(datedenaissance.getEditor().getText());
        //labmsg.setText(String.valueOf(datedenaissance.getEditor().getText().length()));
        //labmsg.setText(String.valueOf((Integer.valueOf(zip.getText()))));
        // email.getText().length()==0||
        //String.valueOf((Integer.valueOf(zip.getText()))).length()==0
        //String.valueOf((Integer.valueOf(numerodetelephone.getText()))).length()==0||String.valueOf((Integer.valueOf(zip.getText()))).length()>6
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
