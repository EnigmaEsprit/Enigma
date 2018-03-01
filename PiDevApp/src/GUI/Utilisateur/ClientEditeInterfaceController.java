/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Utilisateur;

import database.MyDB;
import entites.Utilisateur.Client;
import Util.Util;
import entites.Utilisateur.Vendeur;
import entites.Utilisateur.authentification;
import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Date;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.util.converter.LocalDateTimeStringConverter;
import service.Utilisateur.AdminService;
import service.Utilisateur.ClientService;
import service.Utilisateur.ServiceAuthentification;
import service.Utilisateur.ValidationService;

/**
 * FXML Controller class
 *
 * @author user
 */
public class ClientEditeInterfaceController implements Initializable {

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
    private TextField cemail;
    private PasswordField password;
    private PasswordField cpassword;
    @FXML
    private TextField numerodecartebancaire;
    @FXML
    private DatePicker datedevalidation;
    @FXML
    private PasswordField ccv;
    @FXML
    private Label labmsg;
    public String eemail;
    ObservableList<String> sexec = FXCollections.observableArrayList("Homme", "Femme");
    MyDB con = MyDB.getInstance();
    @FXML
    private Button annuler;
    @FXML
    private Button Editer;
    @FXML
    private Pane menu;
    private PasswordField passwordactuelle;
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
    private Button annuler1;
    @FXML
    private Button changerIdBancaire;
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
    Image image;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //String s = "1993-04-03";
        sexe.setItems(sexec);
        
             email.setEditable(false);
             numerodecartebancaire.setEditable(false);
             datedevalidation.setEditable(false);
             ccv.setEditable(false);
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
        ClientService cs = new ClientService();
        
        //Client c = cs.rechercheParMail(emaill);
        
               
    
        String dateFromDB = Util.connectedUser.getDate_naissance();
        int year=Integer.parseInt(dateFromDB.split("-")[0]); 
        int mounth=Integer.parseInt(dateFromDB.split("-")[1]); 
        int day=Integer.parseInt(dateFromDB.split("-")[2]); 
          String dateFromDV = Util.connectedUser.getDate_validation();
        int yearV=Integer.parseInt(dateFromDV.split("-")[0]); 
        int mounthV=Integer.parseInt(dateFromDV.split("-")[1]); 
        int dayV=Integer.parseInt(dateFromDV.split("-")[2]); 
        
   datedenaissance.setValue(LocalDate.of(year, mounth, day));
                nom.setText(Util.connectedUser.getNom());
                prenom.setText(Util.connectedUser.getPrenom());
          
                sexe.getSelectionModel().select(Util.connectedUser.getSexe());
                adresse.setText(Util.connectedUser.getAdresse());
                ville.setText(Util.connectedUser.getVille());
                zip.setText(String.valueOf(Util.connectedUser.getZip()));
                email.setText(Util.connectedUser.getEmail());
                
               
                numerodecartebancaire.setText(Util.connectedUser.getNbc());
                
              datedevalidation.setValue(LocalDate.of(yearV, mounthV, mounthV));
                numerodetelephone.setText(Util.connectedUser.getNt());
                eemail = email.getText();
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
    private void btnEditAction(ActionEvent event) throws ParseException, SQLException {
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
        
            else
            {ClientService cs = new ClientService();
        
                 System.out.println("********************"+numerodetelephone.getText());
                String dn = datedenaissance.getValue().toString();
            String dv = datedevalidation.getValue().toString();
            Client c = new Client(nom.getText(), prenom.getText(), dn, sexe.getValue(), adresse.getText(), ville.getText(), Integer.valueOf(zip.getText()), numerodetelephone.getText());
                System.out.println("//////////////////////"+c);
            cs.modifierClient(c,eemail);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("INSCRIPTION");
            alert.setHeaderText("Bienvenue");
            alert.setContentText("modifier!");
            alert.showAndWait();
                    
                    }
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
    }

    @FXML
    private void btnAnnulerAction(ActionEvent event) throws NoSuchAlgorithmException {
    
        ServiceAuthentification ser = new ServiceAuthentification();
        ClientService cs = new ClientService();
        Util.connectedUser=cs.rechercheParMail(email.getText());

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
     @FXML
    private void btnespAdminAction(ActionEvent event) {
         LoadWindowParent("LoginAdmin.fxml", event);
    }

    @FXML
    private void btnChangePasswordAction(ActionEvent event) {
         LoadWindowParent("changePassword.fxml", event);
        
    }

    @FXML
    private void btnChangeEmailAction(ActionEvent event) {
        LoadWindowParent("ChangeEmail.fxml", event);   
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
    private void bloqueChamp(MouseEvent event) {
          numerodecartebancaire.setEditable(true);
         datedevalidation.setEditable(true);
         ccv.setEditable(true);
        nom.setEditable(false);
        prenom.setEditable(false);
        adresse.setEditable(false);
        ville.setEditable(false);
        zip.setEditable(false);
        numerodetelephone.setEditable(false);
        //sexe.setEditable(false);
        datedenaissance.setEditable(false);
        
    }

    @FXML
    private void change(MouseEvent event) {
         numerodecartebancaire.setEditable(false);
         datedevalidation.setEditable(false);
         ccv.setEditable(false);
     
        prenom.setEditable(true);
        adresse.setEditable(true);
        ville.setEditable(true);
        zip.setEditable(true);
        numerodetelephone.setEditable(true);
        sexe.setEditable(true);
        datedenaissance.setEditable(true);
       
    }

    @FXML
    private void btnChangeIdBancaireAction(ActionEvent event) throws NoSuchAlgorithmException {
        if(!(ValidationService.numerique_Validation(numerodecartebancaire.getText()))|| (numerodecartebancaire.getText().length() != 16))
            { labmsg.setText("verfier votre Numero de card bancaire");}
        else if(datedevalidation.getEditor().getText().length() == 0)
            { labmsg.setText("verfier votre Date de validation");}
        else if(!(ValidationService.numerique_Validation(ccv.getText())) || (ccv.getText().length() == 0) ) 
            { labmsg.setText("verfier votre CCV");}
        else{
        AdminService as = new AdminService();
        
            
              
                  
         System.out.println("********************"+sexe.getValue());
            
            Client c = new Client(numerodecartebancaire.getText(), datedevalidation.getValue().toString(), ccv.getText());
           
            
             System.out.println(c);
             ClientService cs = new  ClientService();
            cs.modifierIDBClient(c,email.getText());
            
            Util.connectedUser=cs.rechercheParMail(email.getText());
            myFunction();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("INSCRIPTION");
            alert.setHeaderText("Bienvenue");
            alert.setContentText("modifier!");
            alert.showAndWait();
                }
        
           

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
            Logger.getLogger(ClientEditeInterfaceController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    @FXML
    private void btnReclamation(ActionEvent event) {
          LoadWindowParent("/GUI/Reclamation/EnvoiDesReclamations.fxml", event);
    }

    @FXML
    private void btnCatalogueAction(ActionEvent event) {
         LoadWindowParent("/GUI/Produits/AfficheListProduits.fxml", event);
    }

    @FXML
    private void btnBoutisueAction(ActionEvent event) {
         LoadWindowParent("/GUI/Magasins/AfficheMagasins.fxml", event);
    }

}
