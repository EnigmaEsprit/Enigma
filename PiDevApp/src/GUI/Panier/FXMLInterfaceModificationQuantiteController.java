/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Panier;

import com.jfoenix.controls.JFXTextField;
import entites.Panier.FonctionPanier;
import entites.Produit.Produit;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author jean
 */
public class FXMLInterfaceModificationQuantiteController implements Initializable {

    @FXML
    private JFXTextField nomProduitLabel;
    @FXML
    private JFXTextField PrixProduitLabel;
    @FXML
    private TextField quantiteProduit;
    @FXML
    private Button btnok;
    @FXML
    private Button btnannuler;
    @FXML
    private AnchorPane rootPane;
    
    private Boolean estEnModeEdit= false;
    
    public static int idProduitstatic;
    public static int quantiteProduitTotal;

    public static int getIdProduitstatic() {
        return idProduitstatic;
    }

    public static void setIdProduitstatic(int idProduitstatic) {
        FXMLInterfaceModificationQuantiteController.idProduitstatic = idProduitstatic;
    }
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }    

    @FXML
    private void quantiteTextFielEdit(ActionEvent event) {
    }

    @FXML
    private void modifierQuantiteSceneBa(ActionEvent event) {
        
       
        
        String libelle = nomProduitLabel.getText();
        Double prix = Double.parseDouble(PrixProduitLabel.getText());
        int quantite = Integer.parseInt(quantiteProduit.getText());
        
        Produit p = new Produit(idProduitstatic,libelle,quantite,prix);
        
            if(gererModification(p)){
                 Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.close(); 
            }       
        
        
        
    }

    @FXML
    private void annulermodifierQuantiteSceneBa(ActionEvent event) {
        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.close();
        
    }
     public void inflateUI(Produit p){
         
         nomProduitLabel.setText(""+p.getNomProduit());
         PrixProduitLabel.setText(""+p.getPrixProduit());
         quantiteProduit.setText(""+p.getQuantiteProduitClient());
         
         nomProduitLabel.setEditable(false);
        PrixProduitLabel.setEditable(false);
         estEnModeEdit=true;
     }
     
     private Boolean gererModification(Produit p)
     {
         if(FonctionPanier.modifierQTeArticle(p, p.getQuantiteProduitClient())){
             
             
             AlertMaker.showSimpleAlert("Success", "Quantite Modifie");
             return true;
         }else{
             AlertMaker.showErrorMessage("Failed","Ne peut pas modifier, veuillez saisir une quantite inférieure");
             return false;
         }
         
     }
}
