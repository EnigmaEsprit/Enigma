/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Reclamations;
import Services.ReclamationsServices;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Ivan Landry ONANA
 */
public class InterfaceEnvoiDesReclamationsController implements Initializable {

    @FXML
    private AnchorPane container;
    @FXML
    private TextField champEmail;
    @FXML
    private ComboBox<String> typeReclamation;
    @FXML
    private TextField champObjetReclamation;
    @FXML
    private TextArea champContenuReclamation;
    @FXML
    private Button btnEnvoiReclamation;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        typeReclamation.getItems().addAll("Réclamation liée au produit","Réclamation liée à la commande","Réclamation liée à la livraison");
        //typeReclamation.setValue("Réclamation liée au produit");
        System.out.println(typeReclamation.getValue());
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

    @FXML
    private void envoyerUneReclamation(MouseEvent event) {
        try {
            String nulll = null;
            String output = typeReclamation.getSelectionModel().getSelectedItem();
            Reclamations c = new Reclamations(1, output, champObjetReclamation.getText(), champContenuReclamation.getText(), nulll, nulll, Date.valueOf(LocalDate.MAX));
            ReclamationsServices rs = new ReclamationsServices();
            if(controleSaisieEmail(champEmail.getText())){
                dialog(Alert.AlertType.ERROR, "Veuillez saisir une adresse mail valide !");
            }
            else if(rs.compareEmails(champEmail.getText())){
                dialog(Alert.AlertType.ERROR, "Vous n'êtes pas authentifié dans le Souk !");
            }
            else
                rs.envoyerUneReclamation(c, champEmail.getText()); 
                dialog(Alert.AlertType.INFORMATION, "Votre réclamation a bien été envoyée !");
        } catch (NumberFormatException e) {
            System.out.println("Erreur !\n" + e.getMessage());
        }
    }
    
    private void dialog(Alert.AlertType alertType,String s){
        Alert alert = new Alert(alertType,s);
        alert.initStyle(StageStyle.UTILITY);
        alert.setTitle("Info");
        alert.showAndWait();
    }
    
    public boolean controleSaisieEmail(String saisieUser) {
        String regex = "^[a-zA-Z]+[a-zA-Z0-9\\._-]*[a-zA-Z0-9]@[a-zA-Z]+"+ "[a-zA-Z0-9\\._-]*[a-zA-Z0-9]+\\.[a-zA-Z]{2,4}$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(saisieUser);
        return m.matches();
    }
    

}
