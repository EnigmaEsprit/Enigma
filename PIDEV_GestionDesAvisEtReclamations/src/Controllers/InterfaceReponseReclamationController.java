/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import Entities.Reclamations;
import Entities.Vendeur;
import Services.ReclamationsServices;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;

/**
 * FXML Controller class
 *
 * @author Ivan Landry ONANA
 */
public class InterfaceReponseReclamationController implements Initializable {

    @FXML
    private AnchorPane container;
    @FXML
    private Label labelIdentifiant;
    @FXML
    private TextArea ecranReponseReclamation;
    @FXML
    private Button btnEnvoiReponse;
    @FXML
    private Label clientConcerne;
    @FXML
    private Label emailClientConcerne;
    InterfaceReclamationsClientsController ircc = new InterfaceReclamationsClientsController();
    InterfaceEnvoiDesReclamationsController iedr = new InterfaceEnvoiDesReclamationsController();
    Reclamations rc = new Reclamations();
    ReclamationsServices rs = new ReclamationsServices();
    Vendeur vd;
    @FXML
    private Button btnRetour;
    

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        rc = InterfaceReclamationsClientsController.reclamationATraiter ;
        vd = InterfaceReclamationsClientsController.vendeurCte;
        clientConcerne.setText(rc.getNomClient());
        labelIdentifiant.setText("Hello, " + vd.getUsername() + " !");
        emailClientConcerne.setText("<" + rc.getEmailClient() + ">");
    }    

    @FXML
    private void openPageAccueil(ActionEvent event) throws IOException {
        ircc.loadWindows(event, "/Views/InterfaceAccueil.fxml", "Souk El Medina");
    }

    @FXML
    private void openClientPage(ActionEvent event) throws IOException {
        ircc.loadWindows(event, "/Views/InterfaceEnvoiDesReclamations.fxml", "Envoi des réclamations - Souk El Medina");
    }

    @FXML
    private void openVendeurPage(ActionEvent event) throws IOException {
        ircc.loadWindows(event, "/Views/InterfaceReclamationsClients.fxml", "Gestion des Réclamations - Souk El Medina");
    }



    @FXML
    private void btnEnvoyerReponse(ActionEvent event) {
        try {
            if (!(ecranReponseReclamation.getText().trim().length() > 0 ))
                iedr.dialog(Alert.AlertType.ERROR, "Erreur", "Veuillez renseigner une réponse à envoyer");
            else {
                String message = ecranReponseReclamation.getText();
                String destinataire = emailClientConcerne.getText();
                rs.repondreAUneReclamation(destinataire, message, 35);
                iedr.dialog(Alert.AlertType.INFORMATION, "Info", "Votre réponse a été envoyé !");
                openVendeurPage(event);
            }
                
        } 
        catch (IOException e) {
            iedr.dialog(Alert.AlertType.ERROR, "Echec d'envoi de message !", "Erreur");
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void btnRetourPage(ActionEvent event) throws IOException {
        ircc.loadWindows(event, "/Views/InterfaceReclamationsClients.fxml", "Gestion des Réclamations - Souk El Medina");
    }
    
}
