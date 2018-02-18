/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applicationpanier;

import entites.LigneCommande;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.Pane;
import services.LigneCommandesServices;

/**
 * FXML Controller class
 *
 * @author jean
 */
public class FXMLStatistiquesController implements Initializable {

    @FXML
    private Pane PaneView;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadData();
    }    
    private void loadData(){
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
    
}
