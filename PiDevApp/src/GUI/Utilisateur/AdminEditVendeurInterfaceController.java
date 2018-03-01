                                                                                                            /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Utilisateur;

import entites.Utilisateur.Client;
import entites.Utilisateur.Vendeur;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import service.Utilisateur.AdminService;
import service.Utilisateur.ClientService;
import service.Utilisateur.ValidationService;
import service.Utilisateur.VendeurService;

/**
 * FXML Controller class
 *
 * @author user
 */
public class AdminEditVendeurInterfaceController implements Initializable {

    private PasswordField password;
    @FXML
    private Label id;
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
    private Label labmsg;
    private TextField cemail;
    public String eemail;
    @FXML
    private Button Editer;
    @FXML
    private Button Editer1;
    @FXML
    private Button annuler;
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
       ObservableList<String> sexec = FXCollections.observableArrayList("Homme", "Femme");
        ObservableList<String> sf = FXCollections.observableArrayList("Mauvaise", "Moyenne","Bien","Tres Bien");
    @FXML
    private Button changerEmail;
    @FXML
    private Button changerIdBancaire;
    private Vendeur vendeur;
    @FXML
    private Button annuler1;
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
    public void myFunction(Vendeur v) {
        
        vendeur=v;
       
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
             
              
                numerodetelephone.setText(v.getNt());
                situationfiscal.getSelectionModel().select(v.getSutiation_fiscal());
                rib.setText(v.getNumero_bancaire());
                eemail=v.getEmail();
                
               
              
            
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         sexe.setItems(sexec);
          situationfiscal.setItems(sf);
        nom.setEditable(false);
        prenom.setEditable(false);
        datedenaissance.setEditable(false);
        sexe.setEditable(false);
        adresse.setEditable(false);
        ville.setEditable(false);
        zip.setEditable(false);
        email.setEditable(false);
        situationfiscal.setEditable(false);
        rib.setEditable(false);
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
    private void btnAnnulerAction(ActionEvent event) {
        LoadWindowParent("AdminInterface.fxml", event);
    }

    @FXML
    private void btnEditAction(ActionEvent event) {
         if (nom.getText().length() == 0)
            { labmsg.setText("verifier le Nom");}
        else if(prenom.getText().length() == 0)
            { labmsg.setText("verfier le Prenom");}
        else if (datedenaissance.getEditor().getText().length() == 0)
            { labmsg.setText("verfier le Date de naissance");}
        else if(sexe.getValue().length() == 0)
            { labmsg.setText("verfier le Sexe");}
        else if(adresse.getText().length() == 0)
            { labmsg.setText("verfier L'dresse");}
        else if (ville.getText().length() == 0)
            { labmsg.setText("verfier la Ville");}
        else if((zip.getText().length() == 0)|| !(ValidationService.numerique_Validation(zip.getText())))
            { labmsg.setText("verfier le Zip");}      
        else if ((numerodetelephone.getText().length() != 8) || !(ValidationService.numerique_Validation(numerodetelephone.getText())))
            { labmsg.setText("verfier le Numero de Telephone");}
                
        else{
        AdminService as = new AdminService();
        
            
              
                  
         System.out.println("********************"+sexe.getValue());
            String dn = datedenaissance.getValue().toString();
            
            Vendeur v = new Vendeur(nom.getText(), prenom.getText(), dn, sexe.getValue(), adresse.getText(), ville.getText(), Integer.valueOf(zip.getText()), numerodetelephone.getText());
            
             System.out.println(v);
            as.modifierVendeur(v,email.getText());

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("INSCRIPTION");
            alert.setHeaderText("Bienvenue");
            alert.setContentText("modifier!");
            alert.showAndWait();
                }
        
              LoadWindowParent("AdminInterface.fxml", event);
                    }

              
    

    private AdminService AdminService() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
          @FXML
    private void btnespClientAction(ActionEvent event) {
        if (Util.Util.connectedUser==null)
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
        if(Util.Util.connectedUserVendeur==null){
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
    private void btnChangePasswordAction(ActionEvent event) {
         
            try {
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ChangerPasswordVendeur.fxml"));
            Parent root = (Parent) loader.load();
            ChangerPasswordVendeurController contr = loader.getController();
             contr.myFunction(email.getText());
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
    private void btnChangeEmailAction(ActionEvent event) {
           try {
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ChangerEmailVendeur.fxml"));
            Parent root = (Parent) loader.load();
            ChangerEmailVendeurController contr = loader.getController();
             contr.myFunction(email.getText());
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
    private void btnChangeIdBancaireAction(ActionEvent event) {
       
        if(situationfiscal.getValue().length() == 0)
            { labmsg.setText("verfier la Situation Fiscal");}
        else if(rib.getText().length()!=18)
            { labmsg.setText("verfier le RIB");}
            else{
        AdminService as = new AdminService();
        
            
              
                  
         System.out.println("********************"+sexe.getValue());
            
            
            Vendeur v = new Vendeur(situationfiscal.getValue(),rib.getText());
            
             System.out.println(v);
            as.modifierIDBVendeur(v,email.getText());

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("INSCRIPTION");
            alert.setHeaderText("Bienvenue");
            alert.setContentText("modifier!");
            alert.showAndWait();
                }
        
           
                  LoadWindowParent("AdminInterface.fxml", event);
        
    }

    @FXML
    private void bloqueChamp(MouseEvent event) {
        // situationfiscal.setEditable(true);
        rib.setEditable(true);
        nom.setEditable(false);
        prenom.setEditable(false);
        adresse.setEditable(false);
        ville.setEditable(false);
        zip.setEditable(false);
        numerodetelephone.setEditable(false);
        sexe.setEditable(false);
        datedenaissance.setEditable(false);
        //myFunction(vendeur);
    }

    @FXML
    private void change(MouseEvent event) {
                 situationfiscal.setEditable(false);
        rib.setEditable(false);
        nom.setEditable(true);
        prenom.setEditable(true);
        adresse.setEditable(true);
        ville.setEditable(true);
        zip.setEditable(true);
        numerodetelephone.setEditable(true);
       // sexe.setEditable(true);
        datedenaissance.setEditable(true);
        //myFunction(vendeur);
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
            Logger.getLogger(AdminEditClientInterfaceController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
}
