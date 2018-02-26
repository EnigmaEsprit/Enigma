/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Decouverte;

import GUI.Utilisateur.ClientInterfaceController;
import GUI.Utilisateur.LoginController;
import GUI.Utilisateur.VendeurInterfaceController;
import Util.Util;
import entites.Utilisateur.Client;
import entites.Decouverte.Recherche;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.TranslateTransition;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import service.Utilisateur.AdminService;
import service.Decouverte.RechercheService;

/**
 * FXML Controller class
 *
 * @author user
 */
public class RechercheContactInterfaceController implements Initializable {

    @FXML
    private Label labmsg;
    @FXML
    private TextField recherche;
    @FXML
    private TableColumn<Recherche, String> nom;
    @FXML
    private TableColumn<Recherche, String> prenom;
    @FXML
    private TableColumn<Recherche, String> adresse;
    @FXML
    private TableColumn<Recherche, String> ville;
    @FXML
    private TableColumn<Recherche, Integer> zip;
    @FXML
    private TableColumn<Recherche, String> ntelephone;
    @FXML
    private TableColumn<Recherche, String> mail;
    @FXML
    private TableColumn<Recherche, String> nommagazin;
    @FXML
    private TableView<Recherche> tablerecherche;
 private ObservableList<Recherche>data;
    @FXML
    private Button Editer;
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
    @FXML
    private Pane menu3;
    @FXML
    private Button recherche1;
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
    private void btnespClientAction(ActionEvent event) {
              System.out.println(Util.connectedUser);
        if (Util.connectedUser==null)
        {
           try {
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/Utilisateur/Login.fxml"));
            
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
              
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/Utlisateur/ClientInterface.fxml"));
                Parent root = (Parent) loader.load();
                ClientInterfaceController ClientIn = loader.getController();
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
    private void btnespVendeurAction(ActionEvent event) {
        if(Util.connectedUserVendeur==null){
        try {
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/Utilisateur/LoginVendeur.fxml"));
            
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
                
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/Utilisateur/VendeurInterface.fxml"));
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
    private void btnespAdminAction(ActionEvent event) {
         try {
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/Utilisateur/LoginAdmin.fxml"));
            
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
    private void btnChercherAction(ActionEvent event) {
         System.out.println("+++++++-------"+recherche.getText());
        RechercheService rs = new RechercheService();
        if(recherche.getText().length()==0)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Email Change");
            alert.setHeaderText("Bienvenue");
            alert.setContentText("Verifier votre saisie");
            alert.showAndWait();
        }
        else{
       data=rs.searsh(recherche.getText());
        
           
        if(data.isEmpty()==true)
        {
        
            
               Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Email Change");
            alert.setHeaderText("Bienvenue");
            alert.setContentText("introvable");
            alert.showAndWait();
             
        
        }
        else
        {
            data=rs.searsh(recherche.getText());
            System.out.println("aa");
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        
        mail.setCellValueFactory(new PropertyValueFactory<>("email"));
        adresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        ville.setCellValueFactory(new PropertyValueFactory<>("ville"));
        zip.setCellValueFactory(new PropertyValueFactory<>("zip"));
        nommagazin.setCellValueFactory(new PropertyValueFactory<>("nom_magazin"));
        ntelephone.setCellValueFactory(new PropertyValueFactory<>("nt"));
        tablerecherche.setItems(null);
        tablerecherche.setItems(data);
        
        }
       
        
        System.out.println("bb");
    }
    
}
  @FXML
    private void recherche(KeyEvent event) {/*
        System.out.println("+++++++-------"+recherche.getText());
        RechercheService rs = new RechercheService();
        data = rs.searsh(recherche.getText());
        if(data !=  null)
        {
             tablerecherche.setItems(null);
               tablerecherche.setItems(data);
        System.out.println("aa");
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        
        mail.setCellValueFactory(new PropertyValueFactory<>("email"));
        adresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        ville.setCellValueFactory(new PropertyValueFactory<>("ville"));
        zip.setCellValueFactory(new PropertyValueFactory<>("zip"));
        nommagazin.setCellValueFactory(new PropertyValueFactory<>("nom_magazin"));
        ntelephone.setCellValueFactory(new PropertyValueFactory<>("nt"));
        }
        else
        {
            
               Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Email Change");
            alert.setHeaderText("Bienvenue");
            alert.setContentText("introvable");
            alert.showAndWait();
        }
       

        
        System.out.println("bb");
    */}

  
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
    private void afficherSuitemenu3(MouseEvent event) {
         menu.setVisible(true);
        menu3.setVisible(true);
 
    }
    @FXML
    private void exitemenu3(MouseEvent event) {
        menu3.setOnMouseEntered(evt -> {menu3.setVisible(true);});
         menu3.setOnMouseExited(evt -> {menu3.setVisible(false);});
       
        menu3.setVisible(false);
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
    
}
