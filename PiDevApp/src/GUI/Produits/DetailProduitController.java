package GUI.Produits;

import GUI.Utilisateur.ClientInterfaceController;
import GUI.Utilisateur.VendeurInterfaceController;
import Util.Util;
import entites.Utilisateur.Utilisateur;
import entites.Magasins.magasins;
import entites.Produit.produits;
import entites.Utilisateur.Vendeur;
import java.io.IOException;

import service.Magasins.magasinsServices;
import service.Produits.ProduitServices;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import service.Utilisateur.VendeurService;

public class DetailProduitController  implements Initializable {

    produits produit = new produits();
    String pic;
      private Image i;
      magasins m;
    @FXML
    private ImageView imageproduit;
    @FXML
    private Label nomproduit;
    @FXML
    private Label reference;
    @FXML
    private Label prix;
    @FXML
    private Label quantite;
    @FXML
    private Label nommagasin;
    @FXML
    private Label categorie;
    @FXML
    private Label nomvendeur;
    int idProduit;
    magasins nommagasinn = new magasins();
    
    Vendeur user;
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
    
    public void DetailProduitController() throws SQLException
    {
        System.out.println("---------------------------------------------");
        idProduit= AfficheListProduitsController.recupid;

         

        
        System.out.println("id : "+AfficheListProduitsController.recupid);
          ProduitServices ps= new ProduitServices();

         magasinsServices ms= new magasinsServices();
         System.out.println(ps.rechercherProduitsById(idProduit));
      produit=new produits( ps.rechercherProduitsById(idProduit));
      
        System.out.println("------------------------------------------02222222222222222222222221111111111111111111");
      nommagasinn=ms.rechercherMagasinsById(produit.getIdMagasin());
      
      
      
   VendeurService us = new VendeurService();
   
   
        System.out.println("--------------------"+nommagasinn.getIdUser()+"--------------------0000000000000");
   
  user=us.rechercherVendeurById(nommagasinn.getIdUser());
  
        System.out.println("id: "+us.rechercherVendeurById(nommagasinn.getIdUser()));
    }
 @Override
    public void initialize(URL location, ResourceBundle resources) {
       
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
        try {
            DetailProduitController();
        } catch (SQLException ex) {
            Logger.getLogger(DetailProduitController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        System.out.println(produit.getReferenceProduit());
        System.out.println(produit.getNomProduit());
        
       nomproduit.setText(produit.getNomProduit());
       reference.setText(produit.getReferenceProduit());
       prix.setText(""+produit.getPrixProduit());
       quantite.setText(""+produit.getQuantiteProduit());
       nommagasin.setText(nommagasinn.getNomMagasin());
       categorie.setText(produit.getCategorieMagasin());
       nomvendeur.setText(user.getNom()+" "+user.getPrenom());

       pic= produit.getPhotoProduit();
     
          i= new Image("http://localhost/uimg/"+pic);      
          imageproduit.setImage(i);
      
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
                  window.setResizable(false);
                window.show();

            } catch (IOException ex) {
                System.out.println("catch : " + ex.getMessage());
                Logger.getLogger(DetailProduitController.class.getName()).log(Level.SEVERE, null, ex);
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
                  window.setResizable(false);
                window.show();

            } catch (IOException ex) {
                System.out.println("catch : " + ex.getMessage());
                Logger.getLogger(DetailProduitController.class.getName()).log(Level.SEVERE, null, ex);
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
        
       LoadWindowParent("/GUI/Panier/FXMLPanierInterface.fxml",event);
    }

    private void LoadWindow(String loc){
         try {
            Parent homePageParent = FXMLLoader.load(getClass().getResource(loc));
            Stage stage = new Stage(StageStyle.DECORATED);
            //stage.setTitle(name);
            stage.setScene(new Scene(homePageParent));
            stage.setResizable(false);
            stage.show();

        } catch (IOException ex) {
            Logger.getLogger(DetailProduitController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(DetailProduitController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    @FXML
    private void btnEventAction(ActionEvent event) {
    }
   

   

}
