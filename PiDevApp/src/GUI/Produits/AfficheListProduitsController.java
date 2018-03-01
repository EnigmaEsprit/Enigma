/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Produits;

import GUI.Panier.FXMLPaymentCardController;
import GUI.Utilisateur.ClientInterfaceController;
import GUI.Utilisateur.LoginController;
import GUI.Utilisateur.VendeurInterfaceController;
import Util.Util;
import entites.Utilisateur.Utilisateur;
import entites.Magasins.magasins;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import entites.Panier.FonctionPanier;

import entites.Produit.produits;
import entites.Utilisateur.Vendeur;

import service.Magasins.magasinsServices;
import service.Produits.ProduitServices;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import org.controlsfx.control.Rating;
import service.Utilisateur.VendeurService;

public class AfficheListProduitsController implements Initializable {
    
    ToggleGroup group = new ToggleGroup();
    Label l = new Label();
    @FXML
    private AnchorPane anchorPaneA;
    @FXML
    private ScrollPane scrollPaneProduit;
    
    private AnchorPane pass;
    
    public static int recupid;
    @FXML
    private JFXTextField rechercheProduit;
   

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
    @FXML
    private Pane paneimage2;

    
    int k = 0;
    
     ProduitServices listeproduitsServices = new ProduitServices();
    ObservableList<produits> data = FXCollections.observableArrayList();


        ProduitServices evt = new ProduitServices();

          Image[] images;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        
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
        
       
        
       // ProduitServices evt = new ProduitServices();
        
        Image[] images;
        
        AnchorPane p = new AnchorPane();
        GridPane grid = new GridPane();
        
       // data.addAll(listeproduitsServices.afficherProduis());
        
        
        grid.setHgap(1);
        
    
       
        grid.setVgap(listeproduitsServices.afficherProduis().size());
        grid.setPadding(new Insets(50, 50, 50, 50));
        
          for(produits prod : listeproduitsServices.afficherProduis() ) {
              
       // for (int i = 0; i < listeproduitsServices.afficherProduis().size() ; i++) {
            
            for (int j = 0; j < 1; j++) {
                
             
            

                    String urli =prod.getPhotoProduit();
                    Group root = new Group();
                    // images = new Image[1];
                   
                    ImageView im = new ImageView(new Image("http://localhost/uimg/" + urli));
                    im.setFitWidth(150);
                    
                    im.setFitHeight(100);
                    
                    Label visit = new Label("");
                    
                    visit.setOnMouseEntered(event -> {
                        visit.setUnderline(true);
                    });
                    visit.setOnMouseExited(event -> {
                        visit.setUnderline(false);
                    });
                    visit.setOnMouseClicked(event -> {
//                    viewMaison(annonce);
                    });

                    // DisplayShelf.Shelf displayShelf = new DisplayShelf.Shelf(images);
                    //displayShelf.setPrefSize(300, 200);
                    root.getChildren().add(im);
                    root.setAccessibleText(Integer.valueOf(prod.getIdProduit()).toString());
                    //root.setAccessibleText(data.get(k).getIdMagasin());

                    System.out.println(urli);

//                    
                    Label id = new Label(String.valueOf(prod.getIdProduit()));
                    id.setVisible(false);
                    id.setAccessibleText("id");
                    Pane p1 = new Pane();
                    
                    Label l2 = new Label("clik me !!!");
                    
                    p1.setVisible(false);
                    l2.setVisible(true);
                    l.setOnMouseEntered((MouseEvent event) -> {
                        p1.setVisible(true);
                        
                    });
                    l.setOnMouseExited((MouseEvent event) -> {
                        p1.setVisible(false);
                    });
                    p1.getChildren().add(l2);
                    l.setAlignment(Pos.CENTER);
                    //  p.getChildren().add(v);
                    // for (entities.Image i11 : data.get(k).getImageas()) {
                    //    System.out.println(i11.getUrl());
                    //  }
                    //  System.out.println("-------------------------");
                    HBox hbox = new HBox();
                    hbox.setSpacing(5);
                    
                    VBox vv = new VBox();
                    vv.setSpacing(5);
                    
                    VBox v1 = new VBox();
                    v1.setSpacing(5);
                    
                    Label l = new Label("");
                    Label l1 = new Label("");
                    Label nomproduit = new Label("          Nom produit: ");
                    nomproduit.setStyle("-fx-text-fill: #7f171f;-fx-font-weight: bold; -fx-font: 20px Tahoma;");
                    Label lnomproduit = new Label(prod.getNomProduit());
                    lnomproduit.setStyle("-fx-text-fill:  #27313a;-fx-font-weight: bold; -fx-font: 20px Tahoma;");
                    Label categorie = new Label("          Categorie: ");
                    categorie.setStyle("-fx-text-fill: #7f171f;-fx-font-weight: bold; -fx-font: 20px Tahoma;");                    
                    Label categorieproduit = new Label(prod.getCategorieMagasin());
                    categorieproduit.setStyle("-fx-text-fill:  #27313a;-fx-font-weight: bold; -fx-font: 20px Tahoma;");                    
                    Label nommagasin = new Label("          Nom magasin: ");
                    nommagasin.setStyle("-fx-text-fill: #7f171f;-fx-font-weight: bold; -fx-font: 20px Tahoma;");
                    magasinsServices mss = new magasinsServices();
                    magasins m = mss.rechercherMagasinsById(prod.getIdMagasin());
                    
                    Label lnommagasin = new Label(m.getNomMagasin());
                    lnommagasin.setStyle("-fx-text-fill:  #27313a;-fx-font-weight: bold; -fx-font: 20px Tahoma;");
                    VendeurService us = new VendeurService();
                    Vendeur u = new Vendeur();
                    try {
                        u = us.rechercherVendeurById(m.getIdUser());
                    } catch (SQLException ex) {
                        Logger.getLogger(AfficheListProduitsController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    Label vendeur = new Label("          Nom vendeur: ");
                    vendeur.setStyle("-fx-text-fill: #7f171f;-fx-font-weight: bold; -fx-font: 20px Tahoma;");
                    Label lvendeur = new Label(u.getNom() + " " + u.getPrenom());
                    lvendeur.setStyle("-fx-text-fill:  #27313a;-fx-font-weight: bold; -fx-font: 20px Tahoma;");
                    
                    Button ajouterPanier = new Button("Ajouter panier ");
                   
                    
                    Rating eval = new Rating(5);
                    eval.setPartialRating(true);
                    eval.setRating(prod.getValeval());
                    eval.setOnMouseClicked(event -> {
                        System.out.println(eval.getRating());
                        
                        
                    });
                    System.out.println(prod.getValeval());
                    // ajouterPanier.setStyle("-fx-text-fill: hite;-fx-font: 11 'system'; -fx-background-radius: 5px; -padding-left: 50px;-fx-background-color:#c49e56;");
System.out.println("bikie bikiek bikie bkiek ");
                    System.out.println("id: "+prod.getNomProduit());
                    System.out.println("quantite: "+prod.getQuantiteProduit());
                 
                   
                      
               prod.setImageProduit(im);
                      data.add(new produits(prod.getIdProduit(),prod.getNomProduit(),prod.getQuantiteProduit(),prod.getPrixProduit(),prod.getImageProduit(),ajouterPanier));
           
                    
                   grid.add(new VBox(root, id, p1), j, k);
                    vv.getChildren().addAll(l, lnomproduit, categorieproduit, lnommagasin, lvendeur, eval, ajouterPanier);
                    v1.getChildren().addAll(l1, nomproduit, categorie, nommagasin, vendeur);
                 
                    
                    grid.add((v1), 1, k);
                    
                    grid.add((vv), 2, k);                    

                    System.out.println("");
               
                    
                ajouterPanier.setOnMouseClicked((event) -> {
                    
                           // System.out.println(data.get(k));
                
               
              System.out.println(prod.getQuantiteProduit());
                System.out.println(prod.getQuantiteProduitClient());
                prod.setQuantiteProduitClient(1);
                System.out.println(prod.getQuantiteProduitClient());
                // nombreProduitsDansPanier.setText("= "+FonctionPanier.compterArticles());
              FonctionPanier.ajouterArticle(prod);
                 //nombreProduitsDansPanier.setText("= "+FonctionPanier.compterArticles());
                System.out.println("bikie bikiek bikie bkiek ");
                    System.out.println("id: "+prod.getNomProduit());
                    System.out.println("quantite: "+prod.getQuantiteProduit());
                    
              Alert alert = new Alert(Alert.AlertType.INFORMATION);
              alert.setTitle("Information Dialog");
              alert.setHeaderText(null);
              alert.setContentText("produit "+prod.getNomProduit()+" a ete ajoute");
              alert.showAndWait();
                            System.out.println("ééééééé\nééééé\néééééé\nééééééééééééé\nééééééééééééé\n");
                        });
                
                    k++;
                    scrollPaneProduit.setContent(grid);
                    
                }
                
              
                
          //  }
            
            for (Node node : grid.getChildren()) {
                if (node instanceof VBox) {
                    for (Node node1 : ((VBox) node).getChildren()) {
                        if (node1 instanceof ImageView) {
                            node1.setOnMouseClicked((MouseEvent E) -> {
                            });
                        }
                        
                    }
                }
            }
            
            grid.setOnMouseClicked((MouseEvent E) -> {
                for (Node node : grid.getChildren()) {
                    
                    for (Node node1 : ((VBox) node).getChildren()) {
                        if (node1 instanceof Group) {
                            node1.setOnMouseClicked((MouseEvent E1) -> {
                                
                                recupid = Integer.valueOf(node1.getAccessibleText());
                                System.out.println(recupid + "-------------------");
                                //                                    Pane newLoadedPaneExp = FXMLLoader.load(getClass().getResource("/GUI/Produits/detailProduit.fxml"));
                                // pass.getChildren().clear();
                                // pass.getChildren().add(newLoadedPaneExp);
                                LoadWindow("/GUI/Produits/detailProduit.fxml");
                                
                            });
                        }
                    }
                }
            });
            
        }
        
    }

 @FXML
    void rechercherMagasin(KeyEvent event) throws SQLException {
   ProduitServices evt = new ProduitServices();


  
        
        Image[] images;
        
        AnchorPane p = new AnchorPane();
        GridPane grid = new GridPane();
        
        data = FXCollections.observableArrayList();
        
        if (rechercheProduit.getText().isEmpty()) {
            data.addAll(listeproduitsServices.afficherProduis());
        } else {
            data.addAll(listeproduitsServices.rechercherProduitsByName(rechercheProduit.getText()));
        }
        int k = 0;
        grid.setHgap(1);
        grid.setVgap((data.stream().count()) + 1);
        grid.setPadding(new Insets(50, 50, 50, 50));
        for (int i = 0; i < (data.stream().count() / 1) + 1; i++) {
            for (int j = 0; j < 1; j++) {
                if (k < data.stream().count()) {
                    String urli = data.get(k).getPhotoProduit();
                    Group root = new Group();
                    // images = new Image[1];
                    ImageView im = new ImageView(new Image("http://localhost/uimg/" + urli));
                    im.setFitWidth(250);
                    
                    im.setFitHeight(200);
                    
                    Label visit = new Label("");
                    
                    root.getChildren().add(im);
                    root.setAccessibleText(String.valueOf(data.get(k).getIdProduit()));
                    
                    System.out.println(urli);
                    
                    Label id = new Label(String.valueOf(data.get(k).getIdProduit()));
                    id.setVisible(false);
                    id.setAccessibleText("id");
                    Pane p1 = new Pane();
                    
                    Label l2 = new Label("clik me !!!");
                    
                    p1.setVisible(false);
                    l2.setVisible(true);
                    
                    p1.getChildren().add(l2);
                    l.setAlignment(Pos.CENTER);
                    
                    VBox vv = new VBox();
                    vv.setSpacing(5);
                    
                    VBox v1 = new VBox();
                    v1.setSpacing(5);
                    
                    Label l = new Label("");
                    Label l1 = new Label("");
                    Label nomproduit = new Label("          Nom produit: ");
                    nomproduit.setStyle("-fx-text-fill: #7f171f;-fx-font-weight: bold; -fx-font: 20px Tahoma;");
                    Label lnomproduit = new Label(data.get(k).getNomProduit());
                    lnomproduit.setStyle("-fx-text-fill:  #27313a;-fx-font-weight: bold; -fx-font: 20px Tahoma;");
                    Label categorie = new Label("          Categorie: ");
                    categorie.setStyle("-fx-text-fill: #7f171f;-fx-font-weight: bold; -fx-font: 20px Tahoma;");                    
                    Label categorieproduit = new Label(data.get(k).getCategorieMagasin());
                    categorieproduit.setStyle("-fx-text-fill:  #27313a;-fx-font-weight: bold; -fx-font: 20px Tahoma;");                    
                    Label nommagasin = new Label("          Nom magasin: ");
                    nommagasin.setStyle("-fx-text-fill: #7f171f;-fx-font-weight: bold; -fx-font: 20px Tahoma;");
                    magasinsServices mss = new magasinsServices();
                    magasins m = mss.rechercherMagasinsById(data.get(k).getIdMagasin());
                    
                    Label lnommagasin = new Label(m.getNomMagasin());
                    lnommagasin.setStyle("-fx-text-fill:  #27313a;-fx-font-weight: bold; -fx-font: 20px Tahoma;");
                    VendeurService us = new VendeurService();
                    Vendeur u = us.rechercherVendeurById(m.getIdUser());
                    
                    Label vendeur = new Label("          Nom vendeur: ");
                    vendeur.setStyle("-fx-text-fill: #7f171f;-fx-font-weight: bold; -fx-font: 20px Tahoma;");
                    Label lvendeur = new Label(u.getNom() + " " + u.getPrenom());
                    lvendeur.setStyle("-fx-text-fill:  #27313a;-fx-font-weight: bold; -fx-font: 20px Tahoma;");
                    
                    JFXButton ajouterPanier = new JFXButton();
                    ajouterPanier.setText("Ajouter panier ");
                    // ajouterPanier.setStyle("-fx-text-fill: hite;-fx-font: 11 'system'; -fx-background-radius: 5px; -padding-left: 50px;maging-left: 50px;-fx-background-color:#c49e56;");

                    grid.add(new VBox(root, id, l, p1), j, i);
                    vv.getChildren().addAll(l, lnomproduit, categorieproduit, lnommagasin, lvendeur, ajouterPanier);
                    v1.getChildren().addAll(l1, nomproduit, categorie, nommagasin, vendeur);
                    grid.add((v1), 1, i);
                    grid.add((vv), 2, i);                    
                    
                    k++;
                    scrollPaneProduit.setContent(grid);
                    
                }
                
            }
            
            for (Node node : grid.getChildren()) {
                if (node instanceof VBox) {
                    for (Node node1 : ((VBox) node).getChildren()) {
                        if (node1 instanceof ImageView) {
                            node1.setOnMouseClicked((MouseEvent E) -> {
                            });
                        }
                        
                    }
                }
            }
            
            grid.setOnMouseClicked((MouseEvent E) -> {
                for (Node node : grid.getChildren()) {
                    
                    for (Node node1 : ((VBox) node).getChildren()) {
                        if (node1 instanceof Group) {
                            node1.setOnMouseClicked((MouseEvent E1) -> {
                                
                                recupid = Integer.valueOf(node1.getAccessibleText());
                                //Pane newLoadedPaneExp = FXMLLoader.load(getClass().getResource("/GUI/Produits/detailProduit.fxml"));
                                //pass.getChildren().clear();
                                //pass.getChildren().add(newLoadedPaneExp);
                                LoadWindow("/GUI/Produits/detailProduit.fxml");
                               
                               
                            });
                        }
                    }
                }
            });
            
        }
    }
    
    @FXML
    private void Speak(ActionEvent event) {
        
        String recherche = rechercheProduit.getText();
        String desc = rechercheProduit.getText();
        
        FTTS freeTTS = new FTTS(desc);
        freeTTS.speak();
    }
//  

    @FXML
    private void btnespClientAction(ActionEvent event) {
        System.out.println(Util.connectedUser);
        if (Util.connectedUser == null) {
            LoadWindowParent("/GUI/Utilisateur/Login.fxml", event);
        } else {
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
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }
    
    @FXML
    private void btnespVendeurAction(ActionEvent event) {
        if (Util.connectedUserVendeur == null) {
            LoadWindowParent("/GUI/Utilisateur/LoginVendeur.fxml", event);
        } else {
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
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
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
        menu2.setOnMouseEntered(evt -> {
            menu2.setVisible(true);
        });
        menu2.setOnMouseExited(evt -> {
            menu2.setVisible(false);
        });
        
        menu2.setVisible(false);
    }
    
    @FXML
    private void exitemenu3(MouseEvent event) {
        menu3.setOnMouseEntered(evt -> {
            menu3.setVisible(true);
        });
        menu3.setOnMouseExited(evt -> {
            menu3.setVisible(false);
        });
        
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
        
        LoadWindowParent("/GUI/Panier/FXMLPanierInterface.fxml", event);
    }
    
    private void LoadWindow(String loc) {
        try {
            Parent homePageParent = FXMLLoader.load(getClass().getResource(loc));
            Stage stage = new Stage(StageStyle.DECORATED);
            //stage.setTitle(name);
            stage.setScene(new Scene(homePageParent));
            stage.setResizable(false);
            stage.show();
            
        } catch (IOException ex) {
            Logger.getLogger(AfficheListProduitsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void LoadWindowParent(String loc, ActionEvent event) {
        try {
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource(loc));
            
            Parent root = (Parent) loader.load();
            
            Stage window;
            window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            
            window.setScene(new Scene(root));
            
            window.setResizable(false);
            
            window.show();
            
        } catch (IOException ex) {
            Logger.getLogger(AfficheListProduitsController.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
    
    @FXML
    private void btnEventAction(ActionEvent event) {
    }

    @FXML
    private void cataloguesFenetre(ActionEvent event) {
        LoadWindowParent("/GUI/Produits/AfficheListProduits.fxml", event);
    }

    @FXML
    private void BoutiquesFenetre(ActionEvent event) {
        LoadWindowParent("/GUI/Magasins/AfficheListProduits.fxml", event);
    }
    
}
