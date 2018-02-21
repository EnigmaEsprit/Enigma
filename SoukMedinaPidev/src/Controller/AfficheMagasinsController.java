/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTextField;

import Entities.magasins;
import Services.magasinsServices;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.text.Text;
import sun.rmi.transport.TransportConstants;

/**
 * FXML Controller class
 *
 * @author wieme
 */
public class AfficheMagasinsController implements Initializable {

    ToggleGroup group = new ToggleGroup();
    Label l = new Label();
    private JFXTextField txPays;

    @FXML
    private AnchorPane anchorPaneA;
    @FXML
    private ScrollPane scrollPaneMagasin;

  @FXML
    private AnchorPane pass;
  
    public static int recupid;
    //ImageView v ;
      @FXML
    private JFXTextField rechercheMag;

   
     magasinsServices a = new magasinsServices();
  //  private final listAnnonce listannonce = new listAnnonce();
    @FXML
    private JFXButton stat;
       ObservableList<magasins> data;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    


          Image[] images;
        
            AnchorPane p = new AnchorPane();
            GridPane grid = new GridPane();
           
        
            data = FXCollections.observableArrayList();
         
            
            data.addAll(a.afficherMagasinc());

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
//                    viewMaison(annonce);
                });
                
                        
                       // DisplayShelf.Shelf displayShelf = new DisplayShelf.Shelf(images);
                        //displayShelf.setPrefSize(300, 200);
                        root.getChildren().add(im);
                         root.setAccessibleText(Integer.valueOf(data.get(k).getIdMagasin()).toString());
                         //root.setAccessibleText(data.get(k).getIdMagasin());

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
                      //  p.getChildren().add(v);
                       // for (entities.Image i11 : data.get(k).getImageas()) {
                        //    System.out.println(i11.getUrl());
                      //  }
                      //  System.out.println("-------------------------");
VBox vv = new VBox();
          vv.setSpacing(5);

          
          
         VBox v1 = new VBox();
          v1.setSpacing(5);
          
                  Label l = new Label("");
           Label l1 = new Label("");
           Label nom = new Label("Nom magasin");
          nom.setStyle("-fx-text-fill: #7f171f;-fx-font-weight: bold; -fx-font: 20px Tahoma;");
              Label nommagasin = new Label(data.get(k).getNomMagasin());
                 nommagasin.setStyle("-fx-text-fill:  #27313a;-fx-font-weight: bold; -fx-font: 20px Tahoma;");
             Label descriptionsmagasin = new Label("Descriptions");
            descriptionsmagasin.setStyle("-fx-text-fill: #7f171f;-fx-font-weight: bold; -fx-font: 20px Tahoma;");
            
              Label description = new Label(data.get(k).getDescriptionMagasin());
                 description.setStyle("-fx-text-fill:  #27313a;-fx-font-weight: bold; -fx-font: 20px Tahoma;");
                 Label contactmagasin = new Label("Contact magasin");
                    contactmagasin.setStyle("-fx-text-fill: #7f171f;-fx-font-weight: bold; -fx-font: 20px Tahoma;");
              Label contact = new Label(data.get(k).getContactMagasin());
                 contact.setStyle("-fx-text-fill:  #27313a;-fx-font-weight: bold; -fx-font: 20px Tahoma;");
                    Label nummagasin = new Label("Numero magasin");
                       nummagasin.setStyle("-fx-text-fill: #7f171f;-fx-font-weight: bold; -fx-font: 20px Tahoma;");
                   Label num = new Label(data.get(k).getNumeroMagasin());
                      num.setStyle("-fx-text-fill:  #27313a;-fx-font-weight: bold; -fx-font: 20px Tahoma;");
                 nummagasin.setPrefWidth(200);
                 
                    Label Adresse = new Label("Adresse");
                     Adresse.setStyle("-fx-text-fill: #7f171f;-fx-font-weight: bold; -fx-font: 20px Tahoma;");
                   Label adresseMagasin = new Label(data.get(k).getAdresseMagasin());
                      adresseMagasin.setStyle("-fx-text-fill:  #27313a;-fx-font-weight: bold; -fx-font: 20px Tahoma;");
                      
                       Label dateCreation = new Label("Date de creation");
                     dateCreation.setStyle("-fx-text-fill: #7f171f;-fx-font-weight: bold; -fx-font: 20px Tahoma;");
                   Label dateCreationMagasin = new Label(data.get(k).getDateCreationMagasin().toString());
                      dateCreationMagasin.setStyle("-fx-text-fill:  #27313a;-fx-font-weight: bold; -fx-font: 20px Tahoma;");
                        JFXButton affProduit = new JFXButton();
                            
                              affProduit.setText("Voir Produits ");
                affProduit.setStyle("-fx-text-fill: hite;-fx-font: 11 'system'; -fx-background-radius: 5px; -padding-left: 50px;-fx-background-color:#c49e56;");
         //       supprimer.setStyle("-fx-text-fill:#077F6F;-fx-font-weight: bold; -fx-font: 15px Tahoma;-fx-background-color: #C4CDCC;");
                affProduit.prefWidth(120);
                
                   affProduit.setAccessibleText(Integer.valueOf(data.get(k).getIdMagasin()).toString());

                        
                        
                
                      affProduit.setOnAction(new EventHandler<ActionEvent>() {
                                                @Override
                                                public void handle(ActionEvent event) {
                                                                      recupid=Integer.valueOf(affProduit.getAccessibleText());
                                                                        System.out.println(recupid+"jjjj");
      ;
                                                               try {
                                    
                                        Pane newLoadedPaneExp = FXMLLoader.load(getClass().getResource("/Fxml/AfficherProduits.fxml"));
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

                grid.setOnMouseClicked((MouseEvent E) -> {
                    for (Node node : grid.getChildren()) {

                        for (Node node1 : ((VBox) node).getChildren()) {
                            if (node1 instanceof Group) {
                                node1.setOnMouseClicked((MouseEvent E1) -> {

                                    try {
                                        // recupid = Integer.valueOf(node1.getAccessibleText());
                                        
                                        
                                     //   recupid = Integer.valueOf(node1.getAccessibleText());
                                      System.out.println(recupid+"-------------------");
                                        Pane newLoadedPaneExp = FXMLLoader.load(getClass().getResource("/Fxml/AfficherProduits.fxml"));
                                        //   a.findMyannonce(recupid);
                                        pass.getChildren().clear();
                                        pass.getChildren().add(newLoadedPaneExp);
                                        //      System.out.println(recupid);
                                        //        System.out.println(annonce);
                                    } catch (IOException ex) {
                                        Logger.getLogger(AfficheMagasinsController.class.getName()).log(Level.SEVERE, null, ex);
                                    }

                                  
                                });
                            }
                        }
                    }
                });


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
//
//visit.setOnMouseEntered(event -> {
//                    visit.setUnderline(true);
//                });
//                visit.setOnMouseExited(event -> {
//                    visit.setUnderline(false);
//                });
//                visit.setOnMouseClicked(event -> {
////                    viewMaison(annonce);
//                });
                
                        
                       // DisplayShelf.Shelf displayShelf = new DisplayShelf.Shelf(images);
                        //displayShelf.setPrefSize(300, 200);
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
                      //  l.setOnMouseEntered((MouseEvent event) -> {
//                        p1.setVisible(true);
//
//                        });
//                        l.setOnMouseExited((MouseEvent event) -> {
//                            p1.setVisible(false);
//                        });
                        p1.getChildren().add(l2);
                        l.setAlignment(Pos.CENTER);
                      //  p.getChildren().add(v);
                       // for (entities.Image i11 : data.get(k).getImageas()) {
                        //    System.out.println(i11.getUrl());
                      //  }
                      //  System.out.println("-------------------------");
VBox vv = new VBox();
          vv.setSpacing(5);

          
          
         VBox v1 = new VBox();
          v1.setSpacing(5);
          
                  Label l = new Label("");
           Label l1 = new Label("");
           Label nom = new Label("Nom magasin");
          nom.setStyle("-fx-text-fill: #7f171f;-fx-font-weight: bold; -fx-font: 20px Tahoma;");
              Label nommagasin = new Label(data.get(k).getNomMagasin());
                 nommagasin.setStyle("-fx-text-fill:  #27313a;-fx-font-weight: bold; -fx-font: 20px Tahoma;");
             Label descriptionsmagasin = new Label("Descriptions");
            descriptionsmagasin.setStyle("-fx-text-fill: #7f171f;-fx-font-weight: bold; -fx-font: 20px Tahoma;");
            
              Label description = new Label(data.get(k).getDescriptionMagasin());
                 description.setStyle("-fx-text-fill:  #27313a;-fx-font-weight: bold; -fx-font: 20px Tahoma;");
                 Label contactmagasin = new Label("Contact magasin");
                    contactmagasin.setStyle("-fx-text-fill: #7f171f;-fx-font-weight: bold; -fx-font: 20px Tahoma;");
              Label contact = new Label(data.get(k).getContactMagasin());
                 contact.setStyle("-fx-text-fill:  #27313a;-fx-font-weight: bold; -fx-font: 20px Tahoma;");
                    Label nummagasin = new Label("Numero magasin");
                       nummagasin.setStyle("-fx-text-fill: #7f171f;-fx-font-weight: bold; -fx-font: 20px Tahoma;");
                   Label num = new Label(data.get(k).getNumeroMagasin());
                      num.setStyle("-fx-text-fill:  #27313a;-fx-font-weight: bold; -fx-font: 20px Tahoma;");
                 nummagasin.setPrefWidth(200);
                 
                    Label Adresse = new Label("Adresse");
                     Adresse.setStyle("-fx-text-fill: #7f171f;-fx-font-weight: bold; -fx-font: 20px Tahoma;");
                   Label adresseMagasin = new Label(data.get(k).getAdresseMagasin());
                      adresseMagasin.setStyle("-fx-text-fill:  #27313a;-fx-font-weight: bold; -fx-font: 20px Tahoma;");
                      
                       Label dateCreation = new Label("Date de creation");
                     dateCreation.setStyle("-fx-text-fill: #7f171f;-fx-font-weight: bold; -fx-font: 20px Tahoma;");
                   Label dateCreationMagasin = new Label(data.get(k).getDateCreationMagasin().toString());
                      dateCreationMagasin.setStyle("-fx-text-fill:  #27313a;-fx-font-weight: bold; -fx-font: 20px Tahoma;");
                        JFXButton supprimer = new JFXButton();
                        
                              supprimer.setText("supprimer ");
                supprimer.setStyle("-fx-text-fill: hite;-fx-font: 11 'system'; -fx-background-radius: 5px; -padding-left: 50px;-fx-background-color:#c49e56;");
         //       supprimer.setStyle("-fx-text-fill:#077F6F;-fx-font-weight: bold; -fx-font: 15px Tahoma;-fx-background-color: #C4CDCC;");
                supprimer.prefWidth(80);
                supprimer.setAccessibleHelp("Bouton");
              //  supprimer.setAccessibleText(Integer.toString(data.get(0).getIdMagasin()));
                        
                        
                        
                
                      supprimer.setOnAction(new EventHandler<ActionEvent>() {
                                                @Override
                                                public void handle(ActionEvent event) {
                                                   a.supprimerMagasins(data.get(1).getIdMagasin());
                                                   
                                                      Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Votre magasin a été supprimer avec succées");
        alert.show();
                                                               try {
                                    
                                        Pane newLoadedPaneExp = FXMLLoader.load(getClass().getResource("/Fxml/AfficheMagasins.fxml"));
                                        pass.getChildren().clear();
                                        pass.getChildren().add(newLoadedPaneExp);
                                    } catch (IOException ex) {
                                        Logger.getLogger(AfficheMagasinsController.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                                   
                                                   
                                                   
                                                }
                                            });
                
                
                   grid.add(new VBox(root, id, l, p1), j, i);
                   vv.getChildren().addAll(l,nommagasin,description,contact,adresseMagasin , num,dateCreationMagasin ,supprimer);
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

                grid.setOnMouseClicked((MouseEvent E) -> {
                    for (Node node : grid.getChildren()) {

                        for (Node node1 : ((VBox) node).getChildren()) {
                            if (node1 instanceof Group) {
                                node1.setOnMouseClicked((MouseEvent E1) -> {

                                    try {
                                        // recupid = Integer.valueOf(node1.getAccessibleText());
                                        
                                        
                                      //  recupid = Integer.valueOf(node1.getAccessibleText());
                                      System.out.println(recupid+"-------------------");
                                        Pane newLoadedPaneExp = FXMLLoader.load(getClass().getResource("/Fxml/AjouterMagasin.fxml"));
                                        //   a.findMyannonce(recupid);
                                        pass.getChildren().clear();
                                        pass.getChildren().add(newLoadedPaneExp);
                                        //      System.out.println(recupid);
                                        //        System.out.println(annonce);
                                    } catch (IOException ex) {
                                        Logger.getLogger(AfficheMagasinsController.class.getName()).log(Level.SEVERE, null, ex);
                                    }

                                  
                                });
                            }
                        }
                    }
                });


            }
    }
    @FXML
    private void stat(ActionEvent event) throws IOException {
        
//          Pane newLoadedPaneExp = FXMLLoader.load(getClass().getResource("/fxml/statistique.fxml"));
//                                    //   a.findMyannonce(recupid);
//                                        pass.getChildren().clear();
//                                        pass.getChildren().add(newLoadedPaneExp);
                                       
        
        
        
    }
//  

    private void speak(ActionEvent event) {
      
       String recherche = rechercheMag.getText();
        magasins a = new magasins(recherche);
         magasinsServices ps= new magasinsServices();
        // ps.add(a);
        FTTS freeTTS = new FTTS(a.getNomMagasin());
        freeTTS.speak();
       
        magasinsServices evt = new magasinsServices();
    }

    }
     
