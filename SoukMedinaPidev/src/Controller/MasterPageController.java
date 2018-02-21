/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author wieme
 */
public class MasterPageController implements Initializable {

    @FXML
    private AnchorPane AP_header;
    @FXML
    public AnchorPane AP_contenu;
    @FXML
    private AnchorPane drawer;
    @FXML
    private HBox hBoxTopBar;
    public static boolean maximized = false;
    public static AnchorPane anchorPaneTout;
  
    Image i;
    @FXML
    private AnchorPane nav;
    @FXML
    private Rectangle navRec;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       // user = FXMLAuthentificationController.user;
        /* Timer timer = new Timer();
            timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
             user=   FXMLAuthentificationController.user;  
                System.out.println("run");
                
            }
        }, 0,1000);
         */
      
       

        try {
            AP_contenu.getChildren().clear();
            AnchorPane newLoadedPaneA = FXMLLoader.load(getClass().getResource("/Fxml/AfficheMagasins.fxml"));
            AP_contenu.getChildren().add(newLoadedPaneA);
            respansev(newLoadedPaneA, 900, 450, 100, 0);
        } catch (IOException ex) {
            Logger.getLogger(MasterPageController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        // TODO
        AP_header.setOnMouseEntered((javafx.scene.input.MouseEvent event1) -> {
            TranslateTransition openNav = new TranslateTransition(new Duration(500), drawer);
            openNav.setToY(drawer.getHeight());
            openNav.play();
        });
        AP_header.setOnMouseExited((javafx.scene.input.MouseEvent event1) -> {
            TranslateTransition closeNav = new TranslateTransition(new Duration(500), drawer);
            closeNav.setToY(-drawer.getHeight());
            closeNav.play();
        });
        drawer.setOnMouseEntered((javafx.scene.input.MouseEvent event1) -> {
            TranslateTransition openNav = new TranslateTransition(new Duration(500), drawer);
            openNav.setToY(drawer.getHeight());
            openNav.play();
        });
        drawer.setOnMouseExited((javafx.scene.input.MouseEvent event1) -> {
            TranslateTransition closeNav = new TranslateTransition(new Duration(500), drawer);
            closeNav.setToY(-drawer.getHeight());
            closeNav.play();
        });
        for (Node node : hBoxTopBar.getChildren()) {
            if (node.getAccessibleText() != null) {
                node.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, (e) -> {
                    switch (node.getAccessibleText()) {
                        case "boutique":
                       try { 
                       AP_contenu.getChildren().clear();
                       AnchorPane newLoadedPaneA = FXMLLoader.load(getClass().getResource("/Fxml/AjouterMagasin.fxml"));
                       Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
                       respansev( newLoadedPaneA,900,450,100,0);
                       AP_contenu.getChildren().add(newLoadedPaneA); 
                       } catch (IOException ex) {
                         Logger.getLogger(MasterPageController.class.getName()).log(Level.SEVERE, null, ex);
                       }  
                            break;

                        case "Accueil":
                            try {
                                AP_contenu.getChildren().clear();
                                AnchorPane newLoadedPaneA = FXMLLoader.load(getClass().getResource("/Fxml/AfficheMagasins.fxml"));
                                respansev(newLoadedPaneA, 900, 450, 100, 0);
                                AP_contenu.getChildren().add(newLoadedPaneA);
                            } catch (IOException ex) {
                                Logger.getLogger(MasterPageController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            break;

                        case "Catalogue":
                            try {
                                AP_contenu.getChildren().clear();
                                AnchorPane newLoadedPaneA = FXMLLoader.load(getClass().getResource("/Fxml/AfficheProduit.fxml"));
                                respansev(newLoadedPaneA, 900, 450, 100, 0);
                                AP_contenu.getChildren().add(newLoadedPaneA);
                            } catch (IOException ex) {
                                Logger.getLogger(MasterPageController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            break;

                        case "panier":
                            System.out.print("5\n");
                            break;

                        case "promotion":
                            try {
                                AP_contenu.getChildren().clear();
                                AnchorPane newLoadedPaneA = FXMLLoader.load(getClass().getResource("/Fxml/AfficherProduits.fxml"));
                                respansev(newLoadedPaneA, 900, 450, 100, 0);
                                AP_contenu.getChildren().add(newLoadedPaneA);
                            } catch (IOException ex) {
                                Logger.getLogger(MasterPageController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            break;

                        case "espacevendeur":
                            try {
                                AP_contenu.getChildren().clear();
                                AnchorPane newLoadedPaneA = FXMLLoader.load(getClass().getResource("/Fxml/AjoutArticle.fxml"));
                                respansev(newLoadedPaneA, 900, 450, 100, 0);
                                AP_contenu.getChildren().add(newLoadedPaneA);
                            } catch (IOException ex) {
                                Logger.getLogger(MasterPageController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            System.out.print("5\n");
                            break;
                        case "espaceadmin":
                            try {
                                System.out.println("lalalala");
                                AP_contenu.getChildren().clear();
                                AnchorPane newLoadedPaneA = FXMLLoader.load(getClass().getResource("/Fxml/AjouterMagasin.fxml"));
                                respansev(newLoadedPaneA, 900, 450, 100, 0);
                                AP_contenu.getChildren().add(newLoadedPaneA);
                            } catch (IOException ex) {
                                Logger.getLogger(MasterPageController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            break;
                    }
                });
            }
        }
    }

    @FXML
    private void close(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    private void maximize(ActionEvent event) {
        Stage stage;
        Button b = (Button) event.getSource();
        stage = (Stage) b.getScene().getWindow();
        if (maximized) {

            stage.setMaximized(false);
            maximized = false;

        } else {
            stage.setMaximized(true);
            Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
            stage.setX(primaryScreenBounds.getMinX());
            stage.setY(primaryScreenBounds.getMinY());
            stage.setWidth(primaryScreenBounds.getWidth());
            stage.setHeight(primaryScreenBounds.getHeight());
            maximized = true;

        }

    }

    @FXML
    private void minimize(ActionEvent event) {
        Stage stage;
        Button b = (Button) event.getSource();
        stage = (Stage) b.getScene().getWindow();
        stage.setIconified(true);
    }

    public static boolean respansev(AnchorPane newLoadedPaneA, int w, int h, int minimizeHeight, int HeightWidth) {
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        Timer timer = new Timer();

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (maximized == true) {
                    newLoadedPaneA.setPrefWidth(primaryScreenBounds.getWidth() - HeightWidth);
                    newLoadedPaneA.setPrefHeight(primaryScreenBounds.getHeight() - minimizeHeight);
                } else {
                    newLoadedPaneA.setPrefWidth(w);
                    newLoadedPaneA.setPrefHeight(h);
                }
            }
        }, 0, 10);
        //  timer.cancel();
        return maximized;
    }

    Stage stage;
    Parent root;

   
}
