/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidevapp;

import database.MyDB;
import entites.Client;
import entites.Vendeur;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import service.AdminService;

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
    private TableColumn<Client, Integer> id;
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
    private TableColumn<Client, Integer> code_s;
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
    private TableColumn<Vendeur, Integer> id1;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
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
         } 
    }

    @FXML
    private void btnAfficherAction(ActionEvent event) {
       AdminService as = new AdminService();
       data=as.SelectClient();
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
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
        AdminService as =  new AdminService();
        dat=as.SelectVendeur();
        id1.setCellValueFactory(new PropertyValueFactory<>("id"));
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
    

