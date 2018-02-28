/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Produits;

import static  GUI.Produits.MasterPageController.respansev;
import Entities.Upload;
import entites.Magasins.magasins;
import entites.Produit.produits;
import service.Magasins.magasinsServices;
import service.Produits.produitServices;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;


public class AjoutArticleController implements Initializable {

    @FXML
    private TextField TFNom;
    @FXML
    private TextField TFRef;
    @FXML
    private TextField TFPrix;
        @FXML
    private ImageView imharticle;
    private JFXComboBox<String> lstmagasin;
    @FXML
    private JFXTextField TFQuantite;
    private TableColumn<?, ?> colNomProduit;
    private TableColumn<?, ?> colRef;
    private TableColumn<?, ?> colPrix;
    @FXML
    private Button saveArticle;
    
    private Connection connection;
    private ObservableList<produits> data=FXCollections.observableArrayList();
    private TableView<produits> ArticleTable;
    @FXML
    private Label AcrticleId;
    @FXML
    private Button upload;
  private File file;
    private Image image;
    String pic;
    int  idUser;
    magasinsServices ms=new magasinsServices();
    @FXML
    private AnchorPane AP1;
    @FXML
    private JFXButton saveArticle1;
    @FXML
    private JFXTextField TFCategorie;
    
    public AjoutArticleController()
    {
        //id vendeur conecte
     idUser=11;   
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       

    }  

    @FXML
    private void AjouterArticle(ActionEvent event) {
          produitServices ps= new produitServices();
        

        if (validateFields()){
        TrayNotification tray = new TrayNotification("Notification !", "Produit Crée avec succée", NotificationType.SUCCESS);
        tray.showAndDismiss(javafx.util.Duration.seconds(2));
            
        magasins m=new magasins();
        
        m=ms.rechercherMagasinsByVendeur(idUser);
         produits P = new produits(TFRef.getText(),TFNom.getText(),TFPrix.getText(),pic,TFQuantite.getText(),0,TFCategorie.getText(),m.getIdMagasin());
        ps.ajouterProduits(P);
        
        
        }
        
    }
         private boolean validateFields () {
         if (TFNom.getText().isEmpty() | TFPrix.getText().isEmpty() | TFCategorie.getText().isEmpty() |   TFQuantite.getText().isEmpty()|TFRef.getText().isEmpty() |!isValidatePhoneNumber(TFPrix.getText())| !isValidatePhoneNumber(TFQuantite.getText())|!isValidatePhoneNumber(TFRef.getText())){
        Alert alert =new Alert (Alert.AlertType.ERROR);
        alert.setTitle("Validation Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Vérifiez les champs !");
        alert.showAndWait();
        return false;
        }
        return true;

    } 
         public boolean isValidatePhoneNumber(String number)
        {
       Pattern pattern = Pattern.compile("[0-9]+");
        Matcher matcher = pattern.matcher(number); 
        return matcher.matches();
        }
    public void affichage(){
                
        colRef.setCellValueFactory(new PropertyValueFactory<>("referenceProduit"));
        colNomProduit.setCellValueFactory(new PropertyValueFactory<>("nomProduit"));
        colPrix.setCellValueFactory(new PropertyValueFactory<>("prixProduit"));
     
        
        data.clear();
        getAll();
      
      
    }
    


   
    public List<produits> getAll() {
           
        List<produits> produit = new ArrayList<>();
       try {
            String req = "SELECT `idProduit`, `referenceProduit` , `nomProduit`, `prixProduit`, `photoProduit`,`quantiteProduit`,`categorieMagasin`, `idMagasin` FROM `produits`";
            PreparedStatement ps = connection.prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                
                data.add(new produits(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7) ,rs.getInt(8)));
                
                ArticleTable.setItems(data);
            
            }
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
 
        return produit;
         
    }

    @FXML
    private void UpladImage(ActionEvent event) throws IOException  {
  
     FileChooser fileChooser = new FileChooser();
            file= fileChooser.showOpenDialog(null);
             FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
            FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
            fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);


            //pic=(file.toURI().toString());
            pic=new Upload().upload(file,"img");
            System.out.println(pic);
            image= new Image("http://localhost/uimg/"+pic);
            imharticle.setImage(image);
    }
    
    
    @FXML
    void afficherListProduit(ActionEvent event) {
         try {
            AP1.getChildren().clear();
            AnchorPane newLoadedPaneA = FXMLLoader.load(getClass().getResource("/GUI/Produits/ListProduits.fxml"));

            respansev(newLoadedPaneA, 900, 450, 100,0);
            AP1.getChildren().add(newLoadedPaneA);
        } catch (IOException ex) {
            Logger.getLogger(AjoutArticleController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    }
    

