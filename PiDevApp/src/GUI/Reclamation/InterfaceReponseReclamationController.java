/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Reclamation;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import entites.Reclamation.Reclamations;
import entites.Utilisateur.Vendeur;
import service.Reclamation.ReclamationsServices;
import java.io.IOException;
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
        labelIdentifiant.setText("Hello, " + vd.getPrenom() + " !");
        emailClientConcerne.setText("<" + rc.getEmailClient() + ">");
    }    

    @FXML
    private void openPageAccueil(MouseEvent event) throws IOException {
        ircc.openPageAccueil(event);
    }

    @FXML
    private void openClientPage(MouseEvent event) throws IOException {
        ircc.openClientPage(event);
    }

    @FXML
    private void openVendeurPage(MouseEvent event) throws IOException {
        ircc.openVendeurPage(event);
    }

    @FXML
    private void openFAQPage(MouseEvent event) throws IOException {
        ircc.openFAQPage(event);
    }

    @FXML
    private void btnEnvoyerReponse(MouseEvent event) {

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
}
