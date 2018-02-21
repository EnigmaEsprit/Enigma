/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import static Controller.MasterPageController.respansev;
import Entities.produits;
import Services.produitServices;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.Connection;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableColumnBuilder;
import javafx.scene.control.TableView;
import javafx.scene.control.TableViewBuilder;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.ImageViewBuilder;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Callback;


/**
 * FXML Controller class
 *
  * @author wieme
 */
public class AfficheProduitController implements Initializable {
 @FXML
    private AnchorPane AP1;
    @FXML
    private TableColumn<produits, String> colNom;
    @FXML
    private TableColumn<produits, Integer> colPrix;
    @FXML
    private TableColumn<produits, Integer> colRef;
    @FXML
    private TableColumn<produits, Integer> colQuantite;
    @FXML
    private JFXButton Btnafficher;
        private Connection connection;
            List<produits> users;
    Services.produitServices u=new produitServices();

   // FilteredList<produits> filteredData = new FilteredList<>(data);

    private ObservableList<produits> data = FXCollections.observableArrayList();
    @FXML
    private TableView<produits> tableArticle;
    @FXML
    private JFXTextField recherche;
        FilteredList<produits> filteredData = new FilteredList<>(data);
    @FXML
    private TableColumn<File, Image> colPhoto;

    /**
     * Initializes the controller class.
     */
   
    
   public AfficheProduitController() {
        
        users =u.afficherProduis();
        
        data.addAll(users);
        
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {

     
    load();
    
    }    


    @FXML
    private void afficherArticles(ActionEvent event) {
    

 
        
        
    }
      
      private void load() {
       //TableColumn<File, Image> imageColumn = TableColumnBuilder.<File, Image>create().text("Image").build();
        colPhoto.setCellValueFactory(new Callback<CellDataFeatures<File, Image>, ObservableValue<Image>>() {
            @Override
            public ObservableValue<Image> call(CellDataFeatures<File, Image> p) {
                File file = p.getValue();
                return new SimpleObjectProperty<>(new Image(file.toURI().toString(), 100, 100, true, true, true));
            }
        });
        colPhoto.setCellFactory(new Callback<TableColumn<File, Image>, TableCell<File, Image>>(){
  @Override
            public TableCell<File, Image> call(TableColumn<File, Image> p) {
                return new TableCell<File, Image>(){
 
                    @Override
                    protected void updateItem(Image i, boolean empty) {
                        super.updateItem(i, empty);
                        setText(null);
                        setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                        ImageView imageView = (i == null || empty) ? null : ImageViewBuilder.create().image(i).build();
                        setGraphic(imageView);
                    }                    
                };
            }
        });
            
            
            TableColumn<File, String> pathColumn = TableColumnBuilder.<File, String>create().text("Path").build();
        pathColumn.setCellValueFactory(new Callback<CellDataFeatures<File, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(CellDataFeatures<File, String> p) {
                File file = p.getValue();
                                                System.out.println("    ffff"+file);

                return new SimpleStringProperty(file.getAbsolutePath());

            }
        });
        //
        TableView<File> tableView = TableViewBuilder.<File>create().columns(colPhoto, pathColumn).columnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY).build();
        //
        String path = "Applications\\XAMPP\\xamppfiles\\htdocs\\u";
        File folder = new File(path);
        File[] files = folder.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".jpg") || name.endsWith(".jpeg") || name.endsWith(".png") || name.endsWith(".gif");
            }
        });
        if (files != null) {
            for (File file : files) {
                tableView.getItems().add(file);
            }
        }
        //
            
            
        colNom.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNomProduit()));
        colPrix.setCellValueFactory(cellData -> new SimpleObjectProperty(cellData.getValue().getPrixProduit()));
               colRef.setCellValueFactory(cellData -> new SimpleObjectProperty(cellData.getValue().getReferenceProduit()));

        colQuantite.setCellValueFactory(cellData -> new SimpleObjectProperty(cellData.getValue().getQuantiteProduit()));
                colPhoto.setCellValueFactory(cellData -> new SimpleObjectProperty(cellData.getValue().getAbsolutePath()));

     
          System.out.println("hhhh "+data);
               tableArticle.setItems(data); 
               
      }
      
    
      private ObservableList<produits> getArticlesList() {
 produitServices ps = new produitServices();
          
      ObservableList<produits> list = FXCollections.observableArrayList(ps.afficherProduis());
      return list;
  }

    @FXML
    private void rechercherArticles(ActionEvent event) {
 
        recherche.textProperty().addListener(
                (observable, oldValue, newValue) -> {
                    filteredData.setPredicate((produits produit)-> {
                        
                            if (newValue == null || newValue.isEmpty()) {
                                return true;
                            }

                            // compare avec le filtre
                            String lowerCaseFilter = newValue.toLowerCase();

                            if (produit.getNomProduit().toLowerCase()
                                    .indexOf(lowerCaseFilter) != -1) {
                                return true; 
                            }
                          
                            return false; // Does not match.
                        });
                });
    
       SortedList<produits> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableArticle.comparatorProperty());
        tableArticle.setItems(filteredData);
         
        
    
    }
 @FXML
    void ajouterProduit(ActionEvent event) {
 try {
            AP1.getChildren().clear();
            AnchorPane newLoadedPaneA = FXMLLoader.load(getClass().getResource("/Fxml/AjoutArticle.fxml"));

            respansev(newLoadedPaneA, 900, 450, 100,0);
            AP1.getChildren().add(newLoadedPaneA);
        } catch (IOException ex) {
            Logger.getLogger(MasterPageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   
}
