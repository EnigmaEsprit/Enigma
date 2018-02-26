/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Panier;

import GUI.Utilisateur.ClientInterfaceController;
import GUI.Utilisateur.LoginController;
import GUI.Utilisateur.VendeurInterfaceController;
import Util.Util;
import entites.Panier.LigneCommande;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import service.Panier.LigneCommandesServices;

/**
 * FXML Controller class
 *
 * @author jean
 */
public class FXMLStatistiquesController implements Initializable {

    @FXML
    private Pane PaneView;
    @FXML
    private PieChart pieChart;
    @FXML
    private DatePicker dateStats;
    @FXML
    private Button btnStats;
    @FXML
    private Pane menu;
    private Pane menu3;
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
    private Button Reclamation;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        
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
 
        loadDataBarChart();
        
        btnStats.setOnAction((event) -> {
          load2DataPieChart();  
        });
        
    }    
    private void loadDataBarChart(){
        System.out.println("ooooooooooooooooooooooooooooooooooooooo");
        
        
        PaneView.getChildren().clear();
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("libelle des Produits");
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Quantites vendues");
        BarChart statsChart = new BarChart(xAxis, yAxis);
        statsChart.setTitle("Statitisques Produits Vendus");
        XYChart.Series series = new XYChart.Series();
        series.setName("stats prod");
        
        LigneCommandesServices lcmdServices = new LigneCommandesServices();
        LigneCommande lcmd = new LigneCommande();
        List<LigneCommande> statsLCMD = lcmdServices.rechercherProduitEtQuantiteDefaut();
        
        System.out.println(statsChart);
        
        for(LigneCommande l : statsLCMD){
            series.getData().add(new XYChart.Data<>(l.getLibelle(),l.getQuantiteTotale()));
        }
        
       
        statsChart.getData().add(series);
        PaneView.getChildren().add(statsChart);
        
    }
    
    private void load2DataPieChart(){
        
        
        LigneCommandesServices lcmdServices = new LigneCommandesServices();
        LigneCommande lcmd = new LigneCommande();
        List<LigneCommande> statsLCMD = lcmdServices.rechercherProduitEtQuantite(Date.valueOf(dateStats.getValue()));
        
        System.out.println(statsLCMD);
        
        
        ObservableList<PieChart.Data> pieChartData 
                =FXCollections.observableArrayList();
        
        for(LigneCommande l :statsLCMD){
            
            pieChartData.add(new PieChart.Data(l.getLibelle(),l.getQuantiteTotale()));
            
        }
        
        pieChart.setData(pieChartData);
        
        System.out.println(dateStats.getValue());
        System.out.println(Date.valueOf(dateStats.getValue()));
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
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
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
         menu2.setOnMouseEntered(evt -> {menu2.setVisible(true);});
         menu2.setOnMouseExited(evt -> {menu2.setVisible(false);});
       
        menu2.setVisible(false);
    }


    private void exitemenu3(MouseEvent event) {
        menu3.setOnMouseEntered(evt -> {menu3.setVisible(true);});
         menu3.setOnMouseExited(evt -> {menu3.setVisible(false);});
       
        menu3.setVisible(false);
    }

    private void afficherSuitemenu3(MouseEvent event) {
         menu.setVisible(true);
        menu3.setVisible(true);
 
    }

    private void btnRechercheAction(ActionEvent event) {
        LoadWindowParent("/GUI/Decouverte/RechercheContactInterface.fxml", event);
    }

    private void btnMapsAction(ActionEvent event) {
        LoadWindowParent("/GUI/Decouverte/Maps.fxml", event);
    }

    private void PanierFenetre(ActionEvent event) {
        
       LoadWindowParent("/GUI/Panier/FXMLPanierInterface.fxml",event);
    }

    private void LoadWindow(String loc){
         try {
            Parent homePageParent = FXMLLoader.load(getClass().getResource(loc));
            Stage stage = new Stage(StageStyle.DECORATED);
            //stage.setTitle(name);
            stage.setScene(new Scene(homePageParent));
            stage.show();

        } catch (IOException ex) {
            Logger.getLogger(FXMLStatistiquesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void LoadWindowParent(String loc,ActionEvent event){
        try {
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource(loc));
            
            Parent root = (Parent) loader.load();
           
             
            Stage window;
            window = (Stage) ((Node) event.getSource()).getScene().getWindow();

            window.setScene(new Scene(root));

            window.show();

           
        } catch (IOException ex) {
            Logger.getLogger(FXMLStatistiquesController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    @FXML
    private void btnEventAction(ActionEvent event) {
    }

      @FXML
    private void statswindow(ActionEvent event) {
          LoadWindowParent("/GUI/Panier/FXMLStatistiques.fxml", event);
    }

    @FXML
    private void CommandesFenetre(ActionEvent event) {
         LoadWindowParent("/GUI/Panier/FXMLCommandesInterface.fxml", event);
    }

    @FXML
    private void btnReclamationAction(ActionEvent event) {
    }


}
