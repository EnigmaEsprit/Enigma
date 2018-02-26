/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Utilisateur;

import Util.Util;
import database.MyDB;
import entites.Utilisateur.Client;
import entites.Utilisateur.Vendeur;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import service.Utilisateur.AdminService;

/**
 * FXML Controller class
 *
 * @author user
 */
public class AdminInterfaceController implements Initializable {

    @FXML
    private Tab client;
    @FXML
    private TableView<Client> tableclients;
    @FXML
    private Tab vendeur;
    @FXML
    private TableColumn<Client, String> nom;
    @FXML
    private TableColumn<Client, String> prenom;
    @FXML
    private TableColumn<Client, String> date_naissance;
    @FXML
    private TableColumn<Client, String> sexe;
    @FXML
    private TableColumn<Client, String> adresse;
    @FXML
    private TableColumn<Client, String> ville;
    @FXML
    private TableColumn<Client, Integer> zip;
    @FXML
    private TableColumn<Client, String> nTelephone;
    @FXML
    private TableColumn<Client, String> email;
    @FXML
    private TableColumn<Client, String> password;
    @FXML
    private TableColumn<Client, String> nCarteBancaire;
    @FXML
    private TableColumn<Client, String> date_validation;
    @FXML
    private TableColumn<Client, String> code_s;
    @FXML
    private Button modifier;
    @FXML
    private Button supprimer;
    @FXML
    private Button loder_D;
    private ObservableList<Client>data;
    private ObservableList<Vendeur>dat;
   Connection con;
    @FXML
    private TableView<Vendeur> tablevendeur;
    @FXML
    private TableColumn<Vendeur, String> nom1;
    @FXML
    private TableColumn<Vendeur, String> prenom1;
    @FXML
    private TableColumn<Vendeur, String> date_naissance1;
    @FXML
    private TableColumn<Vendeur, String> sexe1;
    @FXML
    private TableColumn<Vendeur, String> adresse1;
    @FXML
    private TableColumn<Vendeur, String> ville1;
    @FXML
    private TableColumn<Vendeur, Integer> zip1;
    @FXML
    private TableColumn<Vendeur, String> nTelephone1;
    @FXML
    private TableColumn<Vendeur, String> email1;
    @FXML
    private TableColumn<Vendeur, String> password1;
    @FXML
    private TableColumn<Vendeur, String> situation_fiscal;
    @FXML
    private TableColumn<Vendeur, String> rib;
    @FXML
    private Button modifier1;
    @FXML
    private Button supprimer1;
    @FXML
    private Button loder_D1;
    @FXML
    private Button ajouter;
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
    refrachTable();
    refrachTableVendeur();
    }   
    public  void refrachTableVendeur(){
          AdminService as =  new AdminService();
        dat=as.SelectVendeur();
        
        nom1.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenom1.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        date_naissance1.setCellValueFactory(new PropertyValueFactory<>("date_naissance"));
        sexe1.setCellValueFactory(new PropertyValueFactory<>("sexe"));
        adresse1.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        ville1.setCellValueFactory(new PropertyValueFactory<>("ville"));
        zip1.setCellValueFactory(new PropertyValueFactory<>("zip"));
        nTelephone1.setCellValueFactory(new PropertyValueFactory<>("nt"));
        email1.setCellValueFactory(new PropertyValueFactory<>("email"));
        password1.setCellValueFactory(new PropertyValueFactory<>("mdp"));
        
        situation_fiscal.setCellValueFactory(new PropertyValueFactory<>("sutiation_fiscal"));
        rib.setCellValueFactory(new PropertyValueFactory<>("numero_bancaire"));
        tablevendeur.setItems(null);
        tablevendeur.setItems(dat);
    }
    public  void refrachTable()
    {
         AdminService as = new AdminService();
       data=as.SelectClient();
        
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        date_naissance.setCellValueFactory(new PropertyValueFactory<>("date_naissance"));
        sexe.setCellValueFactory(new PropertyValueFactory<>("sexe"));
        adresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        ville.setCellValueFactory(new PropertyValueFactory<>("ville"));
        zip.setCellValueFactory(new PropertyValueFactory<>("zip"));
        nTelephone.setCellValueFactory(new PropertyValueFactory<>("nt"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        password.setCellValueFactory(new PropertyValueFactory<>("mdp"));
        nCarteBancaire.setCellValueFactory(new PropertyValueFactory<>("nbc"));
        date_validation.setCellValueFactory(new PropertyValueFactory<>("date_validation"));
        code_s.setCellValueFactory(new PropertyValueFactory<>("code_s"));
        tableclients.setItems(null);
        tableclients.setItems(data);
        
    }
    @FXML
    private void btnModifierClientAction(ActionEvent event) {
         Client c = tableclients.getSelectionModel().getSelectedItem();
         if(c==null)
         {
             Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ATTENTION");
           
            alert.setContentText("Verifier votre choix!");
            alert.showAndWait();
         }
         else
         {
             try {
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminEditClientInterface.fxml"));
            Parent root = (Parent) loader.load();
            AdminEditClientInterfaceController contr = loader.getController();
           
             contr.myFunction(c);
            Stage window;
            window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(new Scene(root));
            window.show();
            refrachTable();
        } catch (IOException ex) {
            Logger.getLogger(ClientInterfaceController.class.getName()).log(Level.SEVERE, null, ex);
        }
         }
        

    }

    @FXML
    private void btnSupprimerClientAction(ActionEvent event) {
       Client c = tableclients.getSelectionModel().getSelectedItem();
         if(c==null)
         {
             Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ATTENTION");
           
            alert.setContentText("Verifier votre choix!");
            alert.showAndWait();
         }
         else
         {
             AdminService as =new AdminService();
             as.SupprimerClient(c);
             refrachTable();
         } 
    }

    @FXML
    private void btnAfficherAction(ActionEvent event) {
        refrachTable();
       
    }

    @FXML
    private void btnModifierVendeurAction(ActionEvent event) {
        Vendeur v = tablevendeur.getSelectionModel().getSelectedItem();
         if(v==null)
         {
             Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ATTENTION");
           
            alert.setContentText("Verifier votre choix!");
            alert.showAndWait();
         }
         else
         {
             try {
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminEditVendeurInterface.fxml"));
            Parent root = (Parent) loader.load();
            AdminEditVendeurInterfaceController contr = loader.getController();
           
             contr.myFunction(v);
            Stage window;
            window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(new Scene(root));
            window.show();
            refrachTableVendeur();
        } catch (IOException ex) {
            Logger.getLogger(ClientInterfaceController.class.getName()).log(Level.SEVERE, null, ex);
        }
         }

    }

    @FXML
    private void btnSupprimerVendeurAction(ActionEvent event) {
         Vendeur v = tablevendeur.getSelectionModel().getSelectedItem();
         if(v==null)
         {
             Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ATTENTION");
           
            alert.setContentText("Verifier votre choix!");
            alert.showAndWait();
         }
         else
         {
             AdminService as =new AdminService();
             as.SupprimerVendeur(v);
             refrachTableVendeur();
         } 
    }

    @FXML
    private void btnAjouterVendeurAction(ActionEvent event) {
        try {
                
                FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterVendeurByAdminInterface.fxml"));
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
    private void btnAfficherVendeurAction(ActionEvent event) {
        refrachTableVendeur();
      
    }
        
          @FXML
    private void btnespClientAction(ActionEvent event) {
              System.out.println(Util.connectedUser);
        if (Util.connectedUser==null)
        {
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
        else
        {
           try {
              
                FXMLLoader loader = new FXMLLoader(getClass().getResource("ClientInterface.fxml"));
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
            Logger.getLogger(AdminInterfaceController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    }
    

