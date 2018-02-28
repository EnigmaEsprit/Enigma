/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Panier;

import entites.Produit.Produit;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import service.Produits.ProduitServices2;

/**
 *
 * @author jean
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label label;
    @FXML
    private Button button;
    @FXML
    private TextField nom;
    @FXML
    private TextField prix;
    @FXML
    private TextField qte;
    
    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
        
        
        
         /*Etudiant etudiant1 = new Etudiant(Integer.parseInt(id.getText()),nom.getText(),Integer.parseInt(age.getText()));
        
        System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\n\n\n\n\n");
        EtudiantServices es = new EtudiantServices();
        es.ajouterEtudiant(etudiant1); */
         Produit p1 = new Produit(nom.getText(), Integer.parseInt(qte.getText()), Double.parseDouble(prix.getText()));
         
         ProduitServices2 ps = new ProduitServices2();
        
        ps.ajouterProduit(p1);
        
        
        
        
        
        
        
        Parent homePageParent = FXMLLoader.load(getClass().getResource("FXMLInterface2.fxml"));
        Scene homePageScene = new Scene(homePageParent);
        Stage appStage =(Stage)((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(homePageScene);
        appStage.show();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
