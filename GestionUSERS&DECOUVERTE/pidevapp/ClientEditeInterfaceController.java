/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidevapp;

import database.MyDB;
import entites.Client;
import entites.authentification;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.util.converter.LocalDateTimeStringConverter;
import service.ClientService;
import service.ServiceAuthentification;
import service.ValidationService;

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
        //String s = "1993-04-03";
        sexe.setItems(sexec);
        
                /*  nom.setText("oussama");
                prenom.setText("boumaiza");
              //  datedenaissance.setValue();
                sexe.getSelectionModel().select("Homme");
                adresse.setText("rue");
                ville.setText("nab");
                zip.setText(String.valueOf(2222));
                email.setText("bb@yaho.fr");
                numerodecartebancaire.setText("111111");
               // datedevalidation.setValue(res.getDate(15).toLocalDate());
                numerodetelephone.setText("2200220022");*/
               
             // myFunction(env_email);
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

    public void myFunction(authentification o) {
        ClientService cs = new ClientService();
        System.out.println("++++---- "+o);
        System.out.println("*****"+cs);
        Client c = cs.rechercheParMail(o.getEmail(),o.getPassword());
        
        System.out.println(c+"+++++");
               
        /*try {
        Date date = new  SimpleDateFormat("yyyy-MM-dd").parse(c.getDate_naissance());
        LocalDate daten = LocalDate.of(, 0, 0)
        } catch (ParseException ex) {
        Logger.getLogger(ClientEditeInterfaceController.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        String dateFromDB = c.getDate_naissance();
        int year=Integer.parseInt(dateFromDB.split("-")[0]); 
        int mounth=Integer.parseInt(dateFromDB.split("-")[1]); 
        int day=Integer.parseInt(dateFromDB.split("-")[2]); 
          String dateFromDV = c.getDate_validation();
        int yearV=Integer.parseInt(dateFromDV.split("-")[0]); 
        int mounthV=Integer.parseInt(dateFromDV.split("-")[1]); 
        int dayV=Integer.parseInt(dateFromDV.split("-")[2]); 
        
   datedenaissance.setValue(LocalDate.of(year, mounth, day));
                nom.setText(c.getNom());
                prenom.setText(c.getPrenom());
          
                sexe.getSelectionModel().select(c.getSexe());
                adresse.setText(c.getAdresse());
                ville.setText(c.getVille());
                zip.setText(String.valueOf(c.getZip()));
                email.setText(c.getEmail());
                cemail.setText(c.getEmail());
                password.setText(c.getMdp());
                cpassword.setText(c.getMdp());
                numerodecartebancaire.setText(c.getNbc());
                ccv.setText(Integer.toString(c.getCode_s()));
              datedevalidation.setValue(LocalDate.of(yearV, mounthV, mounthV));
                numerodetelephone.setText(c.getNt());
                eemail = o.getEmail();
              

                //System.out.println("Image: "+res.getString(12)+"\nNom: "+res.getString(2)+"\nPrenom: "+res.getString(3)+"\nDate de naissance: "+res.getDate(4)+"\nSexe "+res.getString(5)+"\nAdresse: "+res.getString(6)+"\nVille: "+res.getString(7)+"\nZIP: "+res.getInt(8)+"\nE-mail: "+res.getString(9)+"\nPassword: "+res.getString(10)+"\nNumero de carte bancaire: "+res.getString(13)+"\nDate de validation: "+res.getDate(14)+"\nCode secraite: "+res.getInt(15));
            

        }

    

    @FXML
    private void btnEditAction(ActionEvent event) throws ParseException, SQLException {
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
            System.out.println(eemail);
            System.out.println(email.getText());
            System.out.println("********************"+numerodetelephone.getText());
        } ClientService cs = new ClientService();
        if ((eemail.equals(email.getText())==false) && (cs.recherche(email.getText())==true)){
            
              
                    labmsg.setText("Verifier votre mail!");
                }
        else if((eemail.equals(email.getText())==false) && (cs.recherche(email.getText())==false))
        {
             System.out.println("********************"+numerodetelephone.getText());
            String dn = datedenaissance.getValue().toString();
            String dv = datedevalidation.getValue().toString();
            //System.out.println(dn);
            //System.out.println(dv);
         /*   SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
            Date daten = formater.parse(dn);
            Date datev = formater.parse(dv);*/
            Client c = new Client(nom.getText(), prenom.getText(), dn, sexe.getValue(), adresse.getText(), ville.getText(), Integer.valueOf(zip.getText()), numerodetelephone.getText(), email.getText(), password.getText(), numerodecartebancaire.getText(), dv, Integer.valueOf(ccv.getText()));
            System.out.println("-----------------------"+c);
            cs.modifierClient(c,eemail);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("INSCRIPTION");
            alert.setHeaderText("Bienvenue");
            alert.setContentText("modifier!");
            alert.showAndWait();
                }
        
            else
            {
                 System.out.println("********************"+numerodetelephone.getText());
                String dn = datedenaissance.getValue().toString();
            String dv = datedevalidation.getValue().toString();
            //System.out.println(dn);
            //System.out.println(dv);
         /*   SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
            Date daten = formater.parse(dn);
            Date datev = formater.parse(dv);*/
            Client c = new Client(nom.getText(), prenom.getText(), dn, sexe.getValue(), adresse.getText(), ville.getText(), Integer.valueOf(zip.getText()), numerodetelephone.getText(), email.getText(), password.getText(), numerodecartebancaire.getText(), dv, Integer.valueOf(ccv.getText()));
                System.out.println("//////////////////////"+c);
            cs.modifierClient(c,eemail);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("INSCRIPTION");
            alert.setHeaderText("Bienvenue");
            alert.setContentText("modifier!");
            alert.showAndWait();
                    
                    }

        }
        /*  else
        {
            
            // labmsg.setText("bienvenue!");
        }
        // labmsg.setText(datedenaissance.getEditor().getText());
        //labmsg.setText(String.valueOf(datedenaissance.getEditor().getText().length()));
        //labmsg.setText(String.valueOf((Integer.valueOf(zip.getText()))));
        // email.getText().length()==0||
        //String.valueOf((Integer.valueOf(zip.getText()))).length()==0
        //String.valueOf((Integer.valueOf(numerodetelephone.getText()))).length()==0||String.valueOf((Integer.valueOf(zip.getText()))).length()>6
         */
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
    private void btnAnnulerAction(ActionEvent event) {
    authentification aut = new authentification(email.getText(),password.getText());
        ServiceAuthentification ser = new ServiceAuthentification();
        //ClientInterfaceController cl = new ClientInterfaceController();

        boolean var = ser.authentifier(aut);

                    try {
                // cl.affiche(aut);
                /* nom = name.getText();
                System.out.println("name = " + name.getText());
                System.out.println("nom = " + nom);
                pas = txtPassword.getText();*/
                FXMLLoader loader = new FXMLLoader(getClass().getResource("ClientInterface.fxml"));
                Parent root = (Parent) loader.load();
                ClientInterfaceController ClientIn = loader.getController();
                ClientIn.myFunction(aut);
                Stage window;
                window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(new Scene(root));
                window.show();

            } catch (IOException ex) {
                System.out.println("catch : " + ex.getMessage());
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
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


