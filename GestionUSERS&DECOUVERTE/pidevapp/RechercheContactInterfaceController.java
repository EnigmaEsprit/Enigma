/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidevapp;

import entites.Client;
import entites.Recherche;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import service.AdminService;
import service.RechercheService;

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
    private Button chercher;
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
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
    @FXML
    private void btnChercherAvtion(ActionEvent event) {
        
        System.out.println("+++++++-------"+recherche.getText());
        RechercheService rs = new RechercheService();
        data = rs.searsh(recherche.getText());
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

        
        System.out.println("bb");
    }
    
}
