/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Ivan Landry ONANA
 */
public class InterfaceVendeurController implements Initializable {

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
    private void openPageGererReclamation(MouseEvent event) throws IOException {
        Parent homePageParent = FXMLLoader.load(getClass().getResource("/Views/InterfaceReclamationsClients.fxml"));
        Scene homePageScene = new Scene(homePageParent);
        Stage appStage =(Stage)((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(homePageScene);
        appStage.show();
        appStage.setTitle("Gestion des Reclamations - Souk El Medina");
    }

    @FXML
    private void openPageRepondreQuestion(MouseEvent event) {
    }

    @FXML
    private void openFAQPage(MouseEvent event) {
    }
    
}
