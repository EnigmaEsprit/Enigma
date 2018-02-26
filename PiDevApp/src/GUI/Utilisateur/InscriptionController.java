/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Utilisateur;

import Util.Util;
import entites.Utilisateur.Client;
import entites.Utilisateur.authentification;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.time.LocalDate;
import java.util.Observable;
import java.util.Optional;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import service.Utilisateur.ClientService;
import service.Utilisateur.SendEmail;
import service.Utilisateur.ServiceAuthentification;
import service.Utilisateur.ValidationService;

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
    public ImageView imgsouq;
    @FXML
    private Label labmsg;
    @FXML
    public Label im;
    ObservableList<String> sexec = FXCollections.observableArrayList("Homme", "Femme");
    @FXML
    private Button annuler;
    @FXML
    private Pane menu;
    @FXML
    private Button Inscrit;
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
        sexe.setItems(sexec);
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

    @FXML
    private void btnInscritAction(ActionEvent event) throws ParseException {
        int var;
        SendEmail IES = new SendEmail();
        if (nom.getText().length() == 0)
            { labmsg.setText("verifier votre Nom");}
        else if(prenom.getText().length() == 0)
            { labmsg.setText("verfier votre Prenom");}
        else if (datedenaissance.getEditor().getText().length() == 0)
            { labmsg.setText("verfier votre Date de naissance");}
        else if(sexe.getValue().length() == 0)
            { labmsg.setText("verfier votre Sexe");}
        else if(adresse.getText().length() == 0)
            { labmsg.setText("verfier votre Adresse");}
        else if (ville.getText().length() == 0)
            { labmsg.setText("verfier votre Ville");}
        else if((zip.getText().length() == 0)|| !(ValidationService.numerique_Validation(zip.getText())))
            { labmsg.setText("verfier votre Zip");}      
        else if ((numerodetelephone.getText().length() != 8) || !(ValidationService.numerique_Validation(numerodetelephone.getText())))
            { labmsg.setText("verfier votre Numero de Telephone");}
        else if(!(ValidationService.email_Validation(email.getText()))|| !(email.getText().equals(cemail.getText())))
            { labmsg.setText("verfier votre Mail");}
        else if ((password.getText().length() == 0)|| (cpassword.getText().length() == 0)|| !(password.getText().equals(cpassword.getText())))
            { labmsg.setText("verfier votre Password");}
        else if(!(ValidationService.numerique_Validation(numerodecartebancaire.getText()))|| (numerodecartebancaire.getText().length() != 16))
            { labmsg.setText("verfier votre Numero de card bancaire");}
        else if(datedevalidation.getEditor().getText().length() == 0)
            { labmsg.setText("verfier votre Date de validation");}
        else if(!(ValidationService.numerique_Validation(ccv.getText())) || (ccv.getText().length() == 0) ) 
            { labmsg.setText("verfier votre CCV");}
            
         else {
                String dn = datedenaissance.getValue().toString();
                       String dv = datedevalidation.getValue().toString();
                Client c = new Client(nom.getText(), prenom.getText(), dn, sexe.getValue(), adresse.getText(), ville.getText(), Integer.valueOf(zip.getText()), numerodetelephone.getText(), email.getText(), password.getText(), numerodecartebancaire.getText(), dv, ccv.getText());
                 ClientService cs = new ClientService();
                 if (cs.recherche(email.getText()) == true) {
                 labmsg.setText("Verfier votre email deja utiliser!");
                     System.out.println(cs.recherche(email.getText()));
                 } else  {
                     System.out.println("--------------"+cs.recherche(email.getText()));
                String rand = String.valueOf(Math.abs(IES.random()));
                IES.sendMail(c.getEmail(), rand);
                TextInputDialog dialog = new TextInputDialog();
                dialog.setTitle("Code de Verification");
                dialog.setHeaderText("Code de verification");
                dialog.setContentText("Please enter your Verification code");
                Optional<String> result = dialog.showAndWait();
                       if (result.isPresent() && result.get().equals(rand)){
                           System.out.println("/////////////"+c);
                         cs.ajouterClient(c);
                       
                        labmsg.setText("Bienvenue!");
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("INSCRIPTION");
                        alert.setHeaderText("Bienvenue");
                        alert.setContentText("Vous etes inscript!");
                        alert.showAndWait();
                        authentification aut = new authentification(email.getText(), password.getText());
                        ServiceAuthentification ser = new ServiceAuthentification();
                           System.out.println("***********"+ser);
                        
                 try {
                        Util.connectedUser=cs.rechercheParMail(email.getText());
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("ClientInterface.fxml"));

                        Parent root = (Parent) loader.load();
                       ClientInterfaceController ClientIn = loader.getController();
                            ClientIn.myFunction();


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
                        alert.setTitle("Code verification");
                        alert.setHeaderText("Erreur");
                        alert.setContentText("Verifier votre code!");
                        alert.showAndWait();
                        
                       }
           
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
        if(Util.connectedUserVendeur==null){
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
                window.show();

            } catch (IOException ex) {
                System.out.println("catch : " + ex.getMessage());
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }

    }
    }
      @FXML
    private void btnAnnulerAction(ActionEvent event) {
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
            Logger.getLogger(InscriptionController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
}
