/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Magasins;

import GUI.Produits.FTTS;
import GUI.Utilisateur.ClientInterfaceController;
import GUI.Utilisateur.VendeurInterfaceController;
import Util.Util;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import entites.Magasins.magasins;
import entites.Produit.produits;
import service.Produits.ProduitServices;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import service.Magasins.magasinsServices;


public class AfficheMagasinsController implements Initializable {
    
   

    ToggleGroup group = new ToggleGroup();
    Label l = new Label();
    private JFXTextField txPays;

    @FXML
    private AnchorPane anchorPaneA;
    @FXML
    private ScrollPane scrollPaneMagasin;

    private AnchorPane pass;
  
    public static int recupid;
      @FXML
    private JFXTextField rechercheMag;

   
     magasinsServices a = new magasinsServices();
    
       ObservableList<magasins> data= FXCollections.observableArrayList();
        // magasinsServices evt = new magasinsServices();
Image[] images;
    @FXML
    private Pane paneimage2;
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
          
        
            AnchorPane p = new AnchorPane();
            GridPane grid = new GridPane();
           
        
            //data = FXCollections.observableArrayList();
         
            
//            System.out.println(a.afficherMagasinc());
                       
            data.addAll(a.afficherMagasinc());
            
            
            System.out.println(data);

            int k = 0;
            grid.setHgap(1);
            grid.setVgap((data.stream().count()) + 1);
            grid.setPadding(new Insets(50, 50, 50, 50));
            for (int i = 0; i < (data.stream().count() / 1) + 1; i++) {
                for (int j = 0; j < 1; j++) {
                    if (k < data.stream().count()) {
                        String urli = data.get(k).getPhotoMagasin();
                          Group root = new Group();
                           // images = new Image[1];
                            ImageView im= new ImageView(new Image("http://localhost/uimg/"+urli));
                                im.setFitWidth(250);

                        im.setFitHeight(200);
                          
                
  Label visit = new Label("");

visit.setOnMouseEntered(event -> {
                    visit.setUnderline(true);
                });
                visit.setOnMouseExited(event -> {
                    visit.setUnderline(false);
                });
                visit.setOnMouseClicked(event -> {
                });
                
                        root.getChildren().add(im);
                         root.setAccessibleText(Integer.valueOf(data.get(k).getIdMagasin()).toString());

                       System.out.println(urli);
                        
//                    
                        l.setStyle("-fx-font-size: 20px;"
                                + " -fx-font-weight: bold;"
                                + "-fx-text-fill: #818181;"
                                + "-fx-effect: innershadow( three-pass-box , rgba(0,0,0,0.7) , 6, 0.0 , 0 , 2 );"
                                + "-fx-font-family: Arial Black;"
                        );
                        Label id = new Label(String.valueOf(data.get(k).getIdMagasin()));
                        id.setVisible(false);
                        id.setAccessibleText("id");
                        Pane p1 = new Pane();
                        p1.setStyle("-fx-background-color: white;"
                                + "-fx-background-radius: 10px;"
                                + "-fx-border-color: black;"
                                + "-fx-border-radius:10px;"
                                + "-fx-opacity: 0.6;");
                        Label l2 = new Label("clik me !!!");
                        l2.setStyle("-fx-font-size: 20px;"
                                + " -fx-font-weight: bold;"
                                + "-fx-text-fill: #818181;"
                                + "-fx-effect: innershadow( three-pass-box , rgba(0,0,0,0.7) , 6, 0.0 , 0 , 2 );"
                                + "-fx-font-family: Arial Black;"
                        );
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
                     
VBox vv = new VBox();
          vv.setSpacing(5);

          
          
         VBox v1 = new VBox();
          v1.setSpacing(5);
          
                  Label l = new Label("");
           Label l1 = new Label("");
           Label nom = new Label("          Nom magasin: ");
          nom.setStyle("-fx-text-fill: #7f171f;-fx-font-weight: bold; -fx-font: 20px Tahoma;");
              Label nommagasin = new Label(data.get(k).getNomMagasin());
                 nommagasin.setStyle("-fx-text-fill:  #27313a;-fx-font-weight: bold; -fx-font: 20px Tahoma;");
             Label descriptionsmagasin = new Label("          Descriptions: ");
            descriptionsmagasin.setStyle("-fx-text-fill: #7f171f;-fx-font-weight: bold; -fx-font: 20px Tahoma;");
            
              Label description = new Label(data.get(k).getDescriptionMagasin());
                 description.setStyle("-fx-text-fill:  #27313a;-fx-font-weight: bold; -fx-font: 20px Tahoma;");
                 Label contactmagasin = new Label("          Contact magasin: ");
                    contactmagasin.setStyle("-fx-text-fill: #7f171f;-fx-font-weight: bold; -fx-font: 20px Tahoma;");
              Label contact = new Label(data.get(k).getContactMagasin());
                 contact.setStyle("-fx-text-fill:  #27313a;-fx-font-weight: bold; -fx-font: 20px Tahoma;");
                    Label nummagasin = new Label("          Numero magasin: ");
                       nummagasin.setStyle("-fx-text-fill: #7f171f;-fx-font-weight: bold; -fx-font: 20px Tahoma;");
                   Label num = new Label(data.get(k).getNumeroMagasin());
                      num.setStyle("-fx-text-fill:  #27313a;-fx-font-weight: bold; -fx-font: 20px Tahoma;");
                 nummagasin.setPrefWidth(200);
                 
                    Label Adresse = new Label("          Adresse: ");
                     Adresse.setStyle("-fx-text-fill: #7f171f;-fx-font-weight: bold; -fx-font: 20px Tahoma;");
                   Label adresseMagasin = new Label(data.get(k).getAdresseMagasin());
                      adresseMagasin.setStyle("-fx-text-fill:  #27313a;-fx-font-weight: bold; -fx-font: 20px Tahoma;");
                      
                       Label dateCreation = new Label("          Date de creation: ");
                     dateCreation.setStyle("-fx-text-fill: #7f171f;-fx-font-weight: bold; -fx-font: 20px Tahoma;");
                   Label dateCreationMagasin = new Label(data.get(k).getDateCreationMagasin().toString());
                      dateCreationMagasin.setStyle("-fx-text-fill:  #27313a;-fx-font-weight: bold; -fx-font: 20px Tahoma;");
                        JFXButton affProduit = new JFXButton();
                            
                              affProduit.setText("Voir Produits");
                affProduit.setStyle("-fx-text-fill: hite;-fx-font: 11 'system'; -fx-background-radius: 5px; -padding-left: 50px;-fx-background-color:#c49e56;");
                affProduit.prefWidth(120);
                
                   affProduit.setAccessibleText(Integer.valueOf(data.get(k).getIdMagasin()).toString());

                        
                        
                
                      affProduit.setOnAction(new EventHandler<ActionEvent>() {
                                                @Override
                                                public void handle(ActionEvent event) {
                                                                      recupid=Integer.valueOf(affProduit.getAccessibleText());      ;
                                                               try {
                                    
                                        Pane newLoadedPaneExp = FXMLLoader.load(getClass().getResource("/GUI/Magasins/ListProduitParMagasin.fxml"));
                                        pass.getChildren().clear();
                                        pass.getChildren().add(newLoadedPaneExp);
                                    } catch (IOException ex) {
                                        Logger.getLogger(AfficheMagasinsController.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                             
                                                }
                                            });
                
                
                   grid.add(new VBox(root, id, l, p1), j, i);
                   vv.getChildren().addAll(l,nommagasin,description,contact,adresseMagasin , num,dateCreationMagasin ,affProduit);
                     v1.getChildren().addAll(l1,nom,descriptionsmagasin,contactmagasin,Adresse ,nummagasin,dateCreation );
       grid.add((v1), 1, i);
         grid.add((vv), 2, i);                

                   k++;
                        scrollPaneMagasin.setContent(grid);
                      
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

           


            }

    }
 @FXML
    void rechercherMagasin(KeyEvent event) {
   magasinsServices evt = new magasinsServices();

          Image[] images;
        
            AnchorPane p = new AnchorPane();
            GridPane grid = new GridPane();
           
        
            data = FXCollections.observableArrayList();
         
            
            data.addAll(a.rechercherMagasinsByNamee(rechercheMag.getText()));

            int k = 0;
            grid.setHgap(1);
            grid.setVgap((data.stream().count()) + 1);
            grid.setPadding(new Insets(50, 50, 50, 50));
            for (int i = 0; i < (data.stream().count() / 1) + 1; i++) {
                for (int j = 0; j < 1; j++) {
                    if (k < data.stream().count()) {
                        String urli = data.get(k).getPhotoMagasin();
                          Group root = new Group();
                           // images = new Image[1];
                            ImageView im= new ImageView(new Image("http://localhost/uimg/"+urli));
                                im.setFitWidth(250);

                        im.setFitHeight(200);
                          
                
  Label visit = new Label("");
    
                        root.getChildren().add(im);
                         root.setAccessibleText(String.valueOf(data.get(k).getIdMagasin()));

                       System.out.println(urli);
                        
//                    
                        l.setStyle("-fx-font-size: 20px;"
                                + " -fx-font-weight: bold;"
                                + "-fx-text-fill: #818181;"
                                + "-fx-effect: innershadow( three-pass-box , rgba(0,0,0,0.7) , 6, 0.0 , 0 , 2 );"
                                + "-fx-font-family: Arial Black;"
                        );
                        Label id = new Label(String.valueOf(data.get(k).getIdMagasin()));
                        id.setVisible(false);
                        id.setAccessibleText("id");
                        Pane p1 = new Pane();
                        p1.setStyle("-fx-background-color: white;"
                                + "-fx-background-radius: 10px;"
                                + "-fx-border-color: black;"
                                + "-fx-border-radius:10px;"
                                + "-fx-opacity: 0.6;");
                        Label l2 = new Label("clik me !!!");
                        l2.setStyle("-fx-font-size: 20px;"
                                + " -fx-font-weight: bold;"
                                + "-fx-text-fill: #818181;"
                                + "-fx-effect: innershadow( three-pass-box , rgba(0,0,0,0.7) , 6, 0.0 , 0 , 2 );"
                                + "-fx-font-family: Arial Black;"
                        );
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
           Label nom = new Label("          Nom magasin: ");
          nom.setStyle("-fx-text-fill: #7f171f;-fx-font-weight: bold; -fx-font: 20px Tahoma;");
              Label nommagasin = new Label(data.get(k).getNomMagasin());
                 nommagasin.setStyle("-fx-text-fill:  #27313a;-fx-font-weight: bold; -fx-font: 20px Tahoma;");
             Label descriptionsmagasin = new Label("          Descriptions: ");
            descriptionsmagasin.setStyle("-fx-text-fill: #7f171f;-fx-font-weight: bold; -fx-font: 20px Tahoma;");
            
              Label description = new Label(data.get(k).getDescriptionMagasin());
                 description.setStyle("-fx-text-fill:  #27313a;-fx-font-weight: bold; -fx-font: 20px Tahoma;");
                 Label contactmagasin = new Label("          Contact magasin: ");
                    contactmagasin.setStyle("-fx-text-fill: #7f171f;-fx-font-weight: bold; -fx-font: 20px Tahoma;");
              Label contact = new Label(data.get(k).getContactMagasin());
                 contact.setStyle("-fx-text-fill:  #27313a;-fx-font-weight: bold; -fx-font: 20px Tahoma;");
                    Label nummagasin = new Label("          Numero magasin: ");
                       nummagasin.setStyle("-fx-text-fill: #7f171f;-fx-font-weight: bold; -fx-font: 20px Tahoma;");
                   Label num = new Label(data.get(k).getNumeroMagasin());
                      num.setStyle("-fx-text-fill:  #27313a;-fx-font-weight: bold; -fx-font: 20px Tahoma;");
                 nummagasin.setPrefWidth(200);
                 
                    Label Adresse = new Label("          Adresse: ");
                     Adresse.setStyle("-fx-text-fill: #7f171f;-fx-font-weight: bold; -fx-font: 20px Tahoma;");
                   Label adresseMagasin = new Label(data.get(k).getAdresseMagasin());
                      adresseMagasin.setStyle("-fx-text-fill:  #27313a;-fx-font-weight: bold; -fx-font: 20px Tahoma;");
                      
                       Label dateCreation = new Label("          Date de creation: ");
                     dateCreation.setStyle("-fx-text-fill: #7f171f;-fx-font-weight: bold; -fx-font: 20px Tahoma;");
                   Label dateCreationMagasin = new Label(data.get(k).getDateCreationMagasin().toString());
                      dateCreationMagasin.setStyle("-fx-text-fill:  #27313a;-fx-font-weight: bold; -fx-font: 20px Tahoma;");
                        JFXButton affProduit = new JFXButton();
                            
                              affProduit.setText("Voir Produits");
                affProduit.setStyle("-fx-text-fill: hite;-fx-font: 11 'system'; -fx-background-radius: 5px; -padding-left: 50px;-fx-background-color:#c49e56;");
                affProduit.prefWidth(120);
                
                   affProduit.setAccessibleText(Integer.valueOf(data.get(k).getIdMagasin()).toString());

                        
                        
                
                      affProduit.setOnAction(new EventHandler<ActionEvent>() {
                                                @Override
                                                public void handle(ActionEvent event) {
                                                                      recupid=Integer.valueOf(affProduit.getAccessibleText());      ;
                                                               try {
                                    
                                        Pane newLoadedPaneExp = FXMLLoader.load(getClass().getResource("/GUI/Magasins/ListProduitParMagasin.fxml"));
                                        pass.getChildren().clear();
                                        pass.getChildren().add(newLoadedPaneExp);
                                    } catch (IOException ex) {
                                        Logger.getLogger(AfficheMagasinsController.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                             
                                                }
                                            });
                
                
                   grid.add(new VBox(root, id, l, p1), j, i);
                   vv.getChildren().addAll(l,nommagasin,description,contact,adresseMagasin , num,dateCreationMagasin ,affProduit);
                     v1.getChildren().addAll(l1,nom,descriptionsmagasin,contactmagasin,Adresse ,nummagasin,dateCreation );
       grid.add((v1), 1, i);
         grid.add((vv), 2, i);                

                   k++;
                        scrollPaneMagasin.setContent(grid);
                      
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

             

            }
    }
  
    @FXML
    private void Speak(ActionEvent event) {
      
       String recherche = rechercheMag.getText();
        String desc = rechercheMag.getText();
      
        FTTS freeTTS = new FTTS(desc);
        freeTTS.speak();
    }
//  

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
                Logger.getLogger(AfficheMagasinsController.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(AfficheMagasinsController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(AfficheMagasinsController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(AfficheMagasinsController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    @FXML
    private void btnEventAction(ActionEvent event) {
    }

   @FXML
    private void btncataloguesAction(ActionEvent event) {
         LoadWindowParent("/GUI/Magasins/AfficheListProduits.fxml", event);
    }

    @FXML
    private void btnBoutiquesAction(ActionEvent event) {
        LoadWindowParent("/GUI/Magasins/AfficheMagasins.fxml", event);
    }
    
     
}