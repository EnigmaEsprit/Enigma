/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soukmedinapidev;

import Entities.magasins;
import Entities.produits;
import Services.magasinsServices;
import Services.produitServices;
import java.io.IOException;
//import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
//import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.sql.Date;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author besmelah
 */
public class SoukMedinaPidev extends Application {
    
    @Override
    public void start(Stage primaryStage) throws IOException {

                 FXMLLoader loader = new FXMLLoader();
                  loader.setLocation(getClass().getResource("/Fxml/MasterPage.fxml"));
                  Parent content = loader.load();
                 Scene scene = new Scene(content);
                 primaryStage.setScene(scene);
                 primaryStage.initStyle(StageStyle.TRANSPARENT);
                 primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
        magasinsServices ms = new magasinsServices();
        produitServices ps = new produitServices();
       ms.supprimerMagasins(6);
        Date d = new Date(2018-02-06);
        System.out.println(d);
       // magasins m1 = new magasins("az","fo","lla",d,"tu",3);
       // produits p1 = new produits(1,"az",1,"fo",1,1,"tu",3);

       // magasins m2 = new magasins(8,"magasinsGeneral","fotoooo","ariana",d,"ariana");
       // magasins m3 = new magasins(9,"Carrefour","foto","PresDeGazella",d,"ariana");
       
        
       // System.out.println(ms.afficherMagasinc());
      //  ps.ajouterProduits(p1);
       // System.out.println(m1);
      //  System.out.println("*******************");
      /*  ms.ajouterMagasins(m2);
        System.out.println(m2);
        System.out.println("*******************");
        ms.ajouterMagasins(m3);
        System.out.println(m3);*/
    }
    
}
