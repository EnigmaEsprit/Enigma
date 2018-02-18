/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidevapp;

import entites.authentification;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import service.ServiceAuthentification;
import service.ValidationService;

/**
 * FXML Controller class
 *
 * @author user
 */
public class LoginController implements Initializable {

    public TextField name;
    @FXML
    private PasswordField txtPassword;
    private Label lblmsg;

    String nom;
    String pas;
    @FXML
    private Label labmsg;
    @FXML
    private Text singup;
    @FXML
    private Pane paneimage2;
    @FXML
    private Pane menu;
    @FXML
    private Button btnLogin;
    @FXML
    private Button espClient;
    @FXML
    private Button espVendeur;
    @FXML
    private Button espAdmin;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
        // TODO
    }

    @FXML
    private void btnLoginAction(ActionEvent event) {
       
        //ClientInterfaceController cl = new ClientInterfaceController();

        
       /* if (name.getText().length() == 0 || !(ValidationService.email_Validation(name.getText())) || txtPassword.getText().length()==0)
        {
            labmsg.setText("verifier vos champs!");
        }*/
         authentification aut = new authentification(name.getText(), txtPassword.getText());
        ServiceAuthentification ser = new ServiceAuthentification();
        boolean var = ser.authentifier(aut);
        if (var == false) {
            labmsg.setText("Username or Password invalid!");
        } else {
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
        
    }

    @FXML
    private void singupAction(MouseEvent event) {
         try {
                
                FXMLLoader loader = new FXMLLoader(getClass().getResource("inscription.fxml"));
                Parent root = (Parent) loader.load();
                
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
