/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Produits;

import entites.Panier.FonctionPanier;
import entites.Produit.Produit;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;

import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import service.Produits.ProduitServices2;

/**
 * FXML Controller class
 *
 * @author jean
 */
public class FXMLInterface2Controller implements Initializable {

    @FXML
    private TableView<Produit> listeProduit;
    @FXML
    private Button btViderPanier;
    @FXML
    private TableColumn<Produit, String> imageColumn;
    @FXML
    private TableColumn<Produit, String> libelleColumn;
    @FXML
    private TableColumn<Produit, String> quantiteColumn;
    @FXML
    private TableColumn<Produit, String> prixColumn;
    @FXML
    private TableColumn<Produit, String> prixTotalColumn;
    @FXML
    private Button btReload;
    
    FonctionPanier panier = new FonctionPanier();

    /**
     * Initializes the controller class.
     */
    private ObservableList<Produit> data = FXCollections.observableArrayList();
    ProduitServices2 ps = new ProduitServices2();
    @FXML
    private TableColumn<?, ?> suppressionColumn;
    @FXML
    private Button btnPanier;
    @FXML
    private ImageView imagePanier;
    @FXML
    private Label nombreProduitsDansPanier;
    @FXML
    private AnchorPane AnchorPaneView2;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        nombreProduitsDansPanier.setText("= "+FonctionPanier.compterArticles());
        loadList();
       setColumn();
         nombreProduitsDansPanier.setText("= "+FonctionPanier.compterArticles());
         
         
        
    }
  

    @FXML
    private void Actualiser(ActionEvent event) throws SQLException {

    }

    @FXML
    private void accederAuPanier(ActionEvent event) throws IOException {
        
        Parent homePageParent = FXMLLoader.load(getClass().getResource("/GUI/Panier/FXMLPanierInterface.fxml"));
        Scene homePageScene = new Scene(homePageParent);
        Stage appStage =(Stage)((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(homePageScene);
        appStage.setResizable(false);
        appStage.show();
    }
    private void loadList()
    {
     int i = 1;
        
        ImageView image=null;

        for (Produit p : ps.selectProduits()) {
        Button btSupp = new Button("Add to Cart");
            i++;
            System.out.println("*************************************************"+data);
           
            System.out.println(ps.selectProduits());
           image = new ImageView("/Images/Image/"+p.getIdProduit()+".jpg");
              System.out.println(image.toString());
                  System.out.println(ps.selectProduits());
           
            image.setFitHeight(90.0);
            image.setFitWidth(75.0);
            System.out.println("*************************************************"+data);
           data.add(new Produit(p.getIdProduit(),p.getNomProduit(), p.getQuantiteProduit(), p.getPrixProduit(),image, btSupp));
           
            System.out.println("*************************************************"+data);
            System.out.println(p);
           
            
            btSupp.setOnAction((event) -> {
                
                /*Ajout Produit dans le panier*/
                
               // p.setQuantiteProduit(1);
                System.out.println(p.getQuantiteProduit());
                System.out.println(p.getQuantiteProduitClient());
                p.setQuantiteProduitClient(1);
                System.out.println(p.getQuantiteProduitClient());
                 nombreProduitsDansPanier.setText("= "+FonctionPanier.compterArticles());
                FonctionPanier.ajouterArticle(p);
                 nombreProduitsDansPanier.setText("= "+FonctionPanier.compterArticles());
                /*********************/
              Alert alert = new Alert(AlertType.INFORMATION);
              alert.setTitle("Information Dialog");
              alert.setHeaderText(null);
              alert.setContentText("produit "+p.getNomProduit()+" a ete ajoute");
              alert.showAndWait();
            });
        }
        
        listeProduit.setItems(data);
    
    }
    
     private void setColumn(){
    
        libelleColumn.setCellValueFactory(new PropertyValueFactory<>("nomProduit"));
        quantiteColumn.setCellValueFactory(new PropertyValueFactory<>("quantiteProduit"));
        prixColumn.setCellValueFactory(new PropertyValueFactory<>("prixProduit"));
      imageColumn.setCellValueFactory(new PropertyValueFactory<>("imageProduit"));
        suppressionColumn.setCellValueFactory(new PropertyValueFactory<>("bouton"));
    
    }

}
