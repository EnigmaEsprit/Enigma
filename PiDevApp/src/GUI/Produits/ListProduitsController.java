/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Produits;

import GUI.Panier.FXMLPanierInterfaceController;
import GUI.Utilisateur.ClientInterfaceController;
import GUI.Utilisateur.LoginController;
import GUI.Utilisateur.VendeurInterfaceController;
import Util.Util;
import entites.Utilisateur.Utilisateur;
import entites.Magasins.magasins;
import entites.Produit.produits;
import service.Magasins.magasinsServices;
import service.Produits.ProduitServices;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.TranslateTransition;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;


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
<<<<<<< HEAD
    ProduitServices ps=new ProduitServices();
    List<produits> produits;
=======
    produitServices ps=new produitServices();
    List<produits> produits = new ArrayList<>();
>>>>>>> 9528d53b6df132dcdafc39bd7109f8806e119712

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
     magasins m=new magasins();
   magasinsServices ms =new magasinsServices();
   
    
    @FXML
    private Pane menu;
    @FXML
    private Pane menu3;
    @FXML
    private Button Maps;
    @FXML
    private Button recherche;
    @FXML
    private Pane menu2;
    @FXML
    private Button espClient1;
    @FXML
    private Button espVendeur1;
    @FXML
    private Button espAdmin1;
    @FXML
    private Button Log;
    @FXML
    private Button Event;
    @FXML
    private Button Contacts;
 
   public ListProduitsController()
   {
       
   //idUser=Util.connectedUserVendeur.getId();
    m=ms.rechercherMagasinsByVendeur(Util.connectedUserVendeur.getId());
       System.out.println(m);

   }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        System.out.println("3333333333333333333333333333333333333333333333333333333333333");
          
    menu.setTranslateX(-190);
    TranslateTransition menuTranslation = new TranslateTransition(Duration.millis(500), menu);

    menuTranslation.setFromX(-190);
    menuTranslation.setToX(0);

    menu.setOnMouseEntered(evt -> {
        menuTranslation.setRate(1);
        menuTranslation.play();
    });
    menu.setOnMouseExited(evt -> {
        menuTranslation.setRate(-1);
        menuTranslation.play();
    });
    menu2.setVisible(false);
    menu3.setVisible(false);
          //idUser=11; 
        produits=ps.rechercherProduitsByMagasins(m.getIdMagasin());
        data.addAll(produits);
        // TODO
        load();
         
         
         
   }    
   
     private void load() {
      
         /*nomproduit.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNomProduit()));
         referenceproduit.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getReferenceProduit()));
         prixproduit.setCellValueFactory(cellData -> new SimpleObjectProperty(cellData.getValue().getPrixProduit()));
         
         quantiteproduit.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getQuantiteProduit()));
         
         categorieproduit.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCategorieMagasin()));*/
        
         nomproduit.setCellValueFactory(new PropertyValueFactory<>("nomProduit"));
        quantiteproduit.setCellValueFactory(new PropertyValueFactory<>("quantiteProduit"));
        prixproduit.setCellValueFactory(new PropertyValueFactory<>("prixProduit"));
      categorieproduit.setCellValueFactory(new PropertyValueFactory<>("categorieMagasin"));
        referenceproduit.setCellValueFactory(new PropertyValueFactory<>("referenceProduit"));

               tableM.setItems(data); 
      }

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
   prix.setText(p.getPrixProduit().toString());
   quantite.setText(""+p.getQuantiteProduit());
   categorie.setText(p.getCategorieMagasin());

    }

    @FXML
    private void supprimerproduit(ActionEvent event) {
         ps.supprimerProduit(tableM.getSelectionModel().getSelectedItem().getIdProduit());
          tableM.getItems().clear();
          tableM.getItems().addAll(ps.rechercherProduitByMagasin(m.getIdMagasin()));
        //  tableM.refresh();
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
            p.setQuantiteProduit(Integer.parseInt(quantite.getText()));
            p.setPrixProduit(Double.parseDouble(prix.getText()));
            p.setCategorieMagasin(categorie.getText());
             ps.modifierProduit(p);
             
        }
         tableM.getItems().clear();
          tableM.getItems().addAll(ps.rechercherProduitByMagasin(m.getIdMagasin()));
        //    tableM.refresh();
            
             tfnom.setText("");
            reference.setText("");
            prix.setText("");
            quantite.setText("");
            categorie.setText("");
     
    }

    private void afficherproduits(MouseEvent event) {
        p =   ps.rechercherProduitsById(tableM.getSelectionModel().getSelectedItem().getIdProduit());

            tfnom.setText(p.getNomProduit());
            reference.setText(p.getReferenceProduit());
            prix.setText(p.getPrixProduit().toString());
            quantite.setText(""+p.getQuantiteProduit());
            categorie.setText(p.getCategorieMagasin());
    }

    @FXML
    private void serachMagasin(KeyEvent event) {
    }

    @FXML
    private void affichermagasin(MouseEvent event) {
    }

    @FXML
    private void btnespClientAction(ActionEvent event) {
              System.out.println(Util.connectedUser);
        if (Util.connectedUser==null)
        {
            LoadWindowParent("/GUI/Utilisateur/Login.fxml", event);
        }
        else
        {
           try {
              
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/Utilisateur/ClientInterface.fxml"));
                Parent root = (Parent) loader.load();
                ClientInterfaceController ClientIn = loader.getController();
                ClientIn.myFunction();
                Stage window;
                window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(new Scene(root));
                window.show();

            } catch (IOException ex) {
                System.out.println("catch : " + ex.getMessage());
                Logger.getLogger(ListProduitsController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        }

       
    
    @FXML
    private void btnespVendeurAction(ActionEvent event) {
        if(Util.connectedUserVendeur==null){
            LoadWindowParent("/GUI/Utilisateur/LoginVendeur.fxml", event);
        }
        else 
        {
              try {
                
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/Utilisateur/VendeurInterface.fxml"));
                Parent root = (Parent) loader.load();
                VendeurInterfaceController ClientIn = loader.getController();
                ClientIn.myFunction();
                Stage window;
                window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(new Scene(root));
                window.show();

            } catch (IOException ex) {
                System.out.println("catch : " + ex.getMessage());
                Logger.getLogger(ListProduitsController.class.getName()).log(Level.SEVERE, null, ex);
            }

    }
    }
 @FXML
    private void btnespAdminAction(ActionEvent event) {
            
             LoadWindowParent("/GUI/Utilisateur/LoginAdmin.fxml", event);
    }

    @FXML
    private void afficherSuite(MouseEvent event) {
        menu.setVisible(true);
        menu2.setVisible(true);
 
    }

    @FXML
    private void exite(MouseEvent event) {
         menu2.setOnMouseEntered(evt -> {menu2.setVisible(true);});
         menu2.setOnMouseExited(evt -> {menu2.setVisible(false);});
       
        menu2.setVisible(false);
    }


    @FXML
    private void exitemenu3(MouseEvent event) {
        menu3.setOnMouseEntered(evt -> {menu3.setVisible(true);});
         menu3.setOnMouseExited(evt -> {menu3.setVisible(false);});
       
        menu3.setVisible(false);
    }

    @FXML
    private void afficherSuitemenu3(MouseEvent event) {
         menu.setVisible(true);
        menu3.setVisible(true);
 
    }

    @FXML
    private void btnRechercheAction(ActionEvent event) {
        LoadWindowParent("/GUI/Decouverte/RechercheContactInterface.fxml", event);
    }

    @FXML
    private void btnMapsAction(ActionEvent event) {
        LoadWindowParent("/GUI/Decouverte/Maps.fxml", event);
    }

    @FXML
    private void PanierFenetre(ActionEvent event) {
        
       // LoadWindow("/GUI/Panier/FXMLPanierInterface.fxml", "Panier");
    }

    private void LoadWindow(String loc){
         try {
            Parent homePageParent = FXMLLoader.load(getClass().getResource(loc));
            Stage stage = new Stage(StageStyle.DECORATED);
            //stage.setTitle(name);
            stage.setScene(new Scene(homePageParent));
            stage.show();

        } catch (IOException ex) {
            Logger.getLogger(ListProduitsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void LoadWindowParent(String loc,ActionEvent event){
        try {
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource(loc));
            
            Parent root = (Parent) loader.load();
           
             
            Stage window;
            window = (Stage) ((Node) event.getSource()).getScene().getWindow();

            window.setScene(new Scene(root));
            
            window.setResizable(false);

            window.show();

           
        } catch (IOException ex) {
            Logger.getLogger(ListProduitsController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    @FXML
    private void btnEventAction(ActionEvent event) {
    }

}
