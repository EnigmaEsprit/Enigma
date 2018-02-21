/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entities.magasins;
import Entities.produits;
import Services.produitServices;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author besmelah
 */
public class AfficherProduitsController implements Initializable {

  static produits annonce = new produits();
    ToggleGroup group = new ToggleGroup();
    Label l = new Label();
    private JFXTextField txPays;

    @FXML
    private AnchorPane anchorPaneA;
    @FXML
    private ScrollPane scrollPaneAnnonce;

  @FXML
    private AnchorPane pass;
    public static int recupid;
    //ImageView v ;
     produitServices a = new produitServices();
       ObservableList<produits> data;
            public static produits annonce2;
    @FXML
    private JFXTextField recherche;
int idmagasin;
    @FXML
    private Button btnspeak;
    public AfficherProduitsController()
    {
                idmagasin= AfficheMagasinsController.recupid;

    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    

        produitServices evt = new produitServices();

          Image[] images;
        
            AnchorPane p = new AnchorPane();
            GridPane grid = new GridPane();
           
        
            data = FXCollections.observableArrayList();
         
            
            data.addAll(a.rechercherProduitsByMagasin(idmagasin));
        System.out.println(idmagasin);
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
                         root.setAccessibleText(String.valueOf(data.get(k).getIdProduit()));

                       System.out.println(urli);
                        
//                    
                        l.setStyle("-fx-font-size: 20px;"
                                + " -fx-font-weight: bold;"
                                + "-fx-text-fill: #818181;"
                                + "-fx-effect: innershadow( three-pass-box , rgba(0,0,0,0.7) , 6, 0.0 , 0 , 2 );"
                                + "-fx-font-family: Arial Black;"
                        );
                        Label id = new Label(String.valueOf((char) data.get(k).getIdProduit()));
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
           Label nom = new Label("Nom produit: ");
          nom.setStyle("-fx-text-fill: #7f171f;-fx-font-weight: bold; -fx-font: 20px Tahoma;");
              Label nomproduit = new Label(data.get(k).getNomProduit());
                 nomproduit.setStyle("-fx-text-fill:  #27313a;-fx-font-weight: bold; -fx-font: 20px Tahoma;");
          
           Label prix = new Label("prix produit: ");
          nom.setStyle("-fx-text-fill: #7f171f;-fx-font-weight: bold; -fx-font: 20px Tahoma;");
              Label prixproduit = new Label(data.get(k).getPrixProduit());
                 prixproduit.setStyle("-fx-text-fill:  #27313a;-fx-font-weight: bold; -fx-font: 20px Tahoma;");
          
           Label quantite = new Label("quantite produit");
          nom.setStyle("-fx-text-fill: #7f171f;-fx-font-weight: bold; -fx-font: 20px Tahoma;");
              Label quantiteproduit = new Label(data.get(k).getQuantiteProduit());
                 quantiteproduit.setStyle("-fx-text-fill:  #27313a;-fx-font-weight: bold; -fx-font: 20px Tahoma;");
          
            Label reference = new Label("refernce produit");
          reference.setStyle("-fx-text-fill: #7f171f;-fx-font-weight: bold; -fx-font: 20px Tahoma;");
              Label referenceproduit = new Label(data.get(k).getReferenceProduit());
                 referenceproduit.setStyle("-fx-text-fill:  #27313a;-fx-font-weight: bold; -fx-font: 20px Tahoma;");
          
                 
               
                 JFXButton supprimer = new JFXButton();
                        
                              supprimer.setText("supprimer ");
                supprimer.setStyle("-fx-text-fill: hite;-fx-font: 11 'system'; -fx-background-radius: 5px; -padding-left: 50px;-fx-background-color:#c49e56;");
         //       supprimer.setStyle("-fx-text-fill:#077F6F;-fx-font-weight: bold; -fx-font: 15px Tahoma;-fx-background-color: #C4CDCC;");
                supprimer.prefWidth(80);
                supprimer.setAccessibleHelp("Bouton");
                supprimer.setAccessibleText(Integer.toString(data.get(0).getIdProduit()));
                        
                        
                        
                
                      supprimer.setOnAction(new EventHandler<ActionEvent>() {
                                                @Override
                                                public void handle(ActionEvent event) {
                                                a.supprimerProduit(data.get(1));
                                                   
                                                      Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Votre produit a été supprimer avec succées");
        alert.show();
        
                                                               try {
                                    
                                        Pane newLoadedPaneExp = FXMLLoader.load(getClass().getResource("/View/Afficher2.fxml"));
                                        pass.getChildren().clear();
                                        pass.getChildren().add(newLoadedPaneExp);
                                    } catch (IOException ex) {
                                        Logger.getLogger(AfficherProduitsController.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                                   
                                                   
                                                   
                                                }
                                            });
                
                    
                            
               
//                 JFXButton modifier = new JFXButton();
//                        
//                              modifier.setText("modifier ");
//                supprimer.setStyle("-fx-text-fill: hite;-fx-font: 11 'system'; -fx-background-radius: 5px; -padding-left: 50px;-fx-background-color:#c49e56;");
//         //       supprimer.setStyle("-fx-text-fill:#077F6F;-fx-font-weight: bold; -fx-font: 15px Tahoma;-fx-background-color: #C4CDCC;");
//                supprimer.prefWidth(80);
//                supprimer.setAccessibleHelp("Bouton");
//                supprimer.setAccessibleText(Integer.toString(data.get(0).getIdProduit()));
//                        
//                        
//                        
//                
//                      btnmodif.setOnAction(new EventHandler<ActionEvent>() {
//                                                @Override
//                                                public void handle(ActionEvent event) {
//                                                a.modifierProduit(data.get(1));
//                                                   
//                                                      Alert alert = new Alert(Alert.AlertType.INFORMATION);
//        alert.setTitle("Information Dialog");
//        alert.setHeaderText(null);
//        alert.setContentText("Votre produit a été modifier avec succées");
//        alert.show();
//        
//                                                               try {
//                                    
//                                        Pane newLoadedPaneExp = FXMLLoader.load(getClass().getResource("/View/AjoutArticle.fxml"));
//                                        pass.getChildren().clear();
//                                        pass.getChildren().add(newLoadedPaneExp);
//                                    } catch (IOException ex) {
//                                        Logger.getLogger(AfficherProduitsController.class.getName()).log(Level.SEVERE, null, ex);
//                                    }
//                                                   
//                                                   
//                                                   
//                                                }
//                                            });
//                
//                     
                
                   grid.add(new VBox(root, id, l, p1), j, i);
                   vv.getChildren().addAll(l,nomproduit,referenceproduit,prixproduit,quantiteproduit);
                     v1.getChildren().addAll(l1,nom,reference,prix,quantite );
       grid.add((v1), 1, i);
         grid.add((vv), 2, i);                

                   k++;
                        scrollPaneAnnonce.setContent(grid);
                      
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
                                        
                                        
                                        recupid = Integer.valueOf(node1.getAccessibleText());
                                      System.out.println(recupid+"-------------------");
                                        Pane newLoadedPaneExp = FXMLLoader.load(getClass().getResource("/View/AjoutArticle.fxml"));
                                        //   a.findMyannonce(recupid);
                                        pass.getChildren().clear();
                                        pass.getChildren().add(newLoadedPaneExp);
                                        //      System.out.println(recupid);
                                        //        System.out.println(annonce);
                                    } catch (IOException ex) {
                                        Logger.getLogger(AfficherProduitsController.class.getName()).log(Level.SEVERE, null, ex);
                                    }

                                  
                                });
                            }
                        }
                    }
                });


            }


     

    }

    @FXML
    private void rechercherArticles(ActionEvent event) {
    
        
    
    }

    @FXML
    private void speak(ActionEvent event) {
    }



}