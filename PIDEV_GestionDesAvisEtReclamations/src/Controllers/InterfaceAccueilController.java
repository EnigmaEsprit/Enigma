/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Produits;
import Services.ProduitsServices;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Ivan Landry ONANA
 */
public class InterfaceAccueilController implements Initializable {

    @FXML
    private AnchorPane container;
    @FXML
    private TableView<Produits> listeProduits;
    @FXML
    private TableColumn<Produits, String> colonneImage;
    @FXML
    private TableColumn<Produits, String> colonneNomProduit;
    @FXML
    private TableColumn<Produits, String> colonneQte;
    @FXML
    private TableColumn<Produits, String> colonnePrix;
    @FXML
    private TableColumn<Produits, String> colonneBoutonCommenter;
    
    private ObservableList<Produits> listeDesProduits = FXCollections.observableArrayList();
    ProduitsServices ps = new ProduitsServices();
    InterfaceReclamationsClientsController ircc = new InterfaceReclamationsClientsController();
    public static Produits produitSelectionne;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //labelIdentifiant.setText("Hello, " + clientCte.getUsername() + " !");
        chargerListerProduits();
    }    

    @FXML
    private void openPageAccueil(ActionEvent event) {
    }

    @FXML
    private void openClientPage(ActionEvent event) throws IOException {
        ircc.loadWindows(event, "/Views/InterfaceEnvoiDesReclamations.fxml", "Espace Client - Souk El Medina");
    }

    @FXML
    private void openVendeurPage(ActionEvent event) throws IOException {
        ircc.loadWindows(event, "/Views/InterfaceReclamationsClients.fxml", "Gestion des Réclamations - Souk El Medina");
    }


    private void chargerListerProduits() {
        colonnePrix.setCellValueFactory(new PropertyValueFactory<>("prixProduit"));
        colonneImage.setCellValueFactory(new PropertyValueFactory<>("imageProduit"));
        colonneNomProduit.setCellValueFactory(new PropertyValueFactory<>("nomProduit"));
        colonneQte.setCellValueFactory(new PropertyValueFactory<>("referenceProduit"));
        colonneBoutonCommenter.setCellValueFactory(new PropertyValueFactory<>("boutonCommentaire"));
        
        ImageView photoProduit;

        for (Produits p : ps.affichageProduits()) {
            Button btComment = new Button(" Ajouter un commentaire à ce produit ");
           
            photoProduit = new ImageView(p.getUrlPhoto());
            photoProduit.setFitHeight(100.0);
            photoProduit.setFitWidth(125.0);
            listeDesProduits.add(new Produits(p.getIdProduit(), p.getReferenceProduit(), p.getNomProduit(), p.getQuantiteProduit(), p.getPrixProduit(), photoProduit, btComment));
            btComment.setOnAction((event) -> {
                try { 
                    produitSelectionne = p;
                    ircc.loadWindows(event, "/Views/InterfaceCommentaires.fxml", "Commenter un produit - Souk El Medina");
                } catch (IOException ex) {
                    Logger.getLogger(InterfaceAccueilController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
        } 
        listeProduits.setItems(listeDesProduits);
    }

}
