/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Services.ReclamationsServices;
import Entities.Reclamations;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Ivan Landry ONANA
 */
public class InterfaceReclamationsClientsController implements Initializable {

    @FXML
    private TableView<Reclamations> tableReclamations;
    @FXML
    private TableColumn<Reclamations, String> colIdReclamation;
    @FXML
    private TableColumn<Reclamations, String> colObjetReclamation;
    @FXML
    private TableColumn<Reclamations, String> colTypeReclamation;
    @FXML
    private TableColumn<Reclamations, String> colNomlUser;
    @FXML
    private TableColumn<Reclamations, String> colEmail;
    @FXML
    private TableColumn<Reclamations, String> colDate;
    @FXML
    private TextArea ecranAffichageReclamation;
    @FXML
    private TextArea ecranReponseReclamation;
    @FXML
    private Button btnReponseReclamation;
    @FXML
    private Button btnSuppressionReclamation;
    ReclamationsServices rs = new ReclamationsServices();
    ObservableList<Reclamations> listReclamations;
    @FXML
    private AnchorPane container;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        loadData();
        tableReclamations.setOnMouseClicked((MouseEvent event) -> {
            if (event.getClickCount() >=1 ) {
                getReclamationContent();
            }
        });
    }    


    @FXML
    private void supprimerReclamation(MouseEvent event) {
        if (tableReclamations.getSelectionModel().getSelectedItem() != null) {
            Reclamations selectedReclamation = tableReclamations.getSelectionModel().getSelectedItem();
            Boolean res = dialog(Alert.AlertType.CONFIRMATION, "Voulez-vous vraiment supprimer cette reclamation ?", "Confirmation");
            if(res){
                rs.supprimerUneReclamation(selectedReclamation);
                dialog(Alert.AlertType.INFORMATION, "Une réclamation a été supprimée !", "Info");
                refresh();
            }
        }
        else{
            dialog(Alert.AlertType.INFORMATION, "Veuilez selectionner une réclamation !", "Info");
        }
    }

    public void getReclamationContent() {
        if (tableReclamations.getSelectionModel().getSelectedItem() != null) {
            Reclamations selectedReclamation = tableReclamations.getSelectionModel().getSelectedItem();
            ecranAffichageReclamation.setText(selectedReclamation.getContenuReclamation());
        }
    }

    @FXML
    private void repondreReclamation(MouseEvent event) {
        try {
            if (tableReclamations.getSelectionModel().getSelectedItem() != null) {
                Reclamations selectedReclamation = tableReclamations.getSelectionModel().getSelectedItem();
                String message = ecranReponseReclamation.getText();
                String destinataire = selectedReclamation.getEmailClient();
                rs.repondreAUneReclamation(destinataire, message);
                dialog(Alert.AlertType.INFORMATION, "Votre message a été envoyé !", "Info");
            }
            
        } 
        catch (Exception e) {
            dialog(Alert.AlertType.ERROR, "Echec d'envoi de message !", "Erreur");
            System.out.println(e.getMessage());
        }
        
    }
    
    private Boolean dialog(Alert.AlertType alertType,String s, String i){
        Alert alert = new Alert(alertType,s);
        alert.initStyle(StageStyle.UTILITY);
        alert.setTitle(i);
        final Optional<ButtonType> result = alert.showAndWait();
        return result.get() == ButtonType.OK;
    }
    
    private void refresh(){
        listReclamations.clear();
        listReclamations.removeAll(listReclamations);
        loadData();
    }

    private void loadData() {
        listReclamations = rs.afficherReclamations();
        colIdReclamation.setCellValueFactory(new PropertyValueFactory<>("idReclamation"));        
        colTypeReclamation.setCellValueFactory(new PropertyValueFactory<>("typeReclamation"));        
        colObjetReclamation.setCellValueFactory(new PropertyValueFactory<>("ObjetReclamation")); 
        colNomlUser.setCellValueFactory(new PropertyValueFactory<>("nomClient"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("emailClient"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("dateEnvoiReclamation"));    
        tableReclamations.setItems(null);
        tableReclamations.setItems(listReclamations);
        
    }

    private void openAccueil(MouseEvent event) throws IOException {
        Parent homePageParent = FXMLLoader.load(getClass().getResource("/Views/InterfaceAccueil.fxml"));
        Scene homePageScene = new Scene(homePageParent);
        Stage appStage =(Stage)((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(homePageScene);
        appStage.show();
    }

    @FXML
    private void openPageAccueil(MouseEvent event) throws IOException {
        Parent homePageParent = FXMLLoader.load(getClass().getResource("/Views/InterfaceAccueil.fxml"));
        Scene homePageScene = new Scene(homePageParent);
        Stage appStage =(Stage)((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(homePageScene);
        appStage.setTitle("Souk El Medina");
        appStage.show();
    }

    @FXML
    private void openClientPage(MouseEvent event) throws IOException {
        Parent homePageParent = FXMLLoader.load(getClass().getResource("/Views/InterfaceClients.fxml"));
        Scene homePageScene = new Scene(homePageParent);
        Stage appStage =(Stage)((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(homePageScene);
        appStage.show();
        appStage.setTitle("Espace Client - Souk El Medina");
    }

    @FXML
    private void openVendeurPage(MouseEvent event) throws IOException {
        Parent homePageParent = FXMLLoader.load(getClass().getResource("/Views/InterfaceVendeur.fxml"));
        Scene homePageScene = new Scene(homePageParent);
        Stage appStage =(Stage)((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(homePageScene);
        appStage.show();
        appStage.setTitle("Espace Vendeur - Souk El Medina");
    }

    @FXML
    private void openFAQPage(MouseEvent event) {
    }

    
}