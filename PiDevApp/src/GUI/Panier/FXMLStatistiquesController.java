/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Panier;

import entites.Panier.LigneCommande;
import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.Pane;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
    
}
