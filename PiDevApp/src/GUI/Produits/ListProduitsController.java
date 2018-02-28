/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Produits;

import entites.Utilisateur.Utilisateur;
import entites.Magasins.magasins;
import entites.Produit.produits;
import service.Magasins.magasinsServices;
import service.Produits.produitServices;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;


public class ListProduitsController implements Initializable {

    @FXML
    private JFXTextField tfSearch;
    @FXML
    private TableView<produits> tableM;
   
    @FXML
    private Button btnmodifier;
      
    @FXML
    private TextField tfnom;
   produits p;
   //UtilisateurService us;
   Utilisateur u;
    produitServices ps=new produitServices();
    List<produits> produits;

    private ObservableList<produits> data = FXCollections.observableArrayList();
    
    FilteredList<produits> filteredData = new FilteredList<>(data);
    magasins mag;
    magasins magasin;
    
    @FXML
    private TextField reference;
    @FXML
    private TextField quantite;
    @FXML
    private TextField prix;
    @FXML
    private TextField categorie;
    @FXML
    private TableColumn<produits, String>  nomproduit;
    @FXML
    private TableColumn<produits, String>  referenceproduit;
    @FXML
    private TableColumn<produits, String>  prixproduit;
    @FXML
    private TableColumn<produits, String>  quantiteproduit;
    @FXML
    private TableColumn<produits, String>  categorieproduit;
     int idUser;
     magasins m;
   magasinsServices ms =new magasinsServices();
 
   public ListProduitsController()
   {
   idUser=11;
    m=ms.rechercherMagasinsByVendeur(idUser);

   }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          
    
          //idUser=11; 
        produits=ps.rechercherProduitsByMagasins(m.getIdMagasin());
        data.addAll(produits);
        // TODO
        load();
         
         
         
   }    
   
     private void load() {
      
        nomproduit.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNomProduit()));
        referenceproduit.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getReferenceProduit()));
        prixproduit.setCellValueFactory(cellData -> new SimpleObjectProperty(cellData.getValue().getPrixProduit()));
        
        quantiteproduit.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getQuantiteProduit()));
     
        categorieproduit.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCategorieMagasin()));
        
       

               tableM.setItems(data); 
      }

    @FXML
    private void serachProduit(KeyEvent event) {
         tfSearch.textProperty().addListener(
                (observable, oldValue, newValue) -> {
                    filteredData.setPredicate((produits prod)-> {
                        
                            if (newValue == null || newValue.isEmpty()) {
                                return true;
                            }

                            // compare avec le filtre
                            String lowerCaseFilter = newValue.toLowerCase();

                            if (prod.getNomProduit().toLowerCase()
                                    .indexOf(lowerCaseFilter) != -1) {
                                return true; 
                            }
                          
                            return false; // Does not match.
                        });
                });
    
       SortedList<produits> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableM.comparatorProperty());
        tableM.setItems(filteredData);
         
        
    }


    
    void afficherProduit(MouseEvent event) {
        
   
    p =   ps.rechercherProduitsById(tableM.getSelectionModel().getSelectedItem().getIdProduit());
     tfnom.setText(p.getNomProduit());
   reference.setText(p.getReferenceProduit());
   prix.setText(p.getPrixProduit());
   quantite.setText(p.getQuantiteProduit());
   categorie.setText(p.getCategorieMagasin());

    }

    @FXML
    private void supprimerproduit(ActionEvent event) {
         ps.supprimerProduit(tableM.getSelectionModel().getSelectedItem().getIdProduit());
          tableM.getItems().clear();
          tableM.getItems().addAll(ps.rechercherProduitByMagasin(m.getIdMagasin()));
          tableM.refresh();
           tfnom.setText("");
            reference.setText("");
            prix.setText("");
            quantite.setText("");
            categorie.setText("");
    }

    @FXML
    private void modifierproduit(ActionEvent event) {
         if(!tfnom.getText().isEmpty()|!reference.getText().isEmpty()|!prix.getText().isEmpty()|!quantite.getText().isEmpty()|!categorie.getText().isEmpty())
        {
        p =   ps.rechercherProduitsById(tableM.getSelectionModel().getSelectedItem().getIdProduit());
            
            p.setNomProduit(tfnom.getText());
            p.setReferenceProduit(reference.getText());
            p.setQuantiteProduit(quantite.getText());
            p.setPrixProduit(prix.getText());
            p.setCategorieMagasin(categorie.getText());
             ps.modifierProduit(p);
             
        }
         tableM.getItems().clear();
          tableM.getItems().addAll(ps.rechercherProduitByMagasin(m.getIdMagasin()));
            tableM.refresh();
            
             tfnom.setText("");
            reference.setText("");
            prix.setText("");
            quantite.setText("");
            categorie.setText("");
     
    }

    @FXML
    private void afficherproduits(MouseEvent event) {
        p =   ps.rechercherProduitsById(tableM.getSelectionModel().getSelectedItem().getIdProduit());

            tfnom.setText(p.getNomProduit());
            reference.setText(p.getReferenceProduit());
            prix.setText(p.getPrixProduit());
            quantite.setText(p.getQuantiteProduit());
            categorie.setText(p.getCategorieMagasin());
    }
}
