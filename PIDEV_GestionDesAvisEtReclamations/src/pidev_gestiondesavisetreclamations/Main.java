/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_gestiondesavisetreclamations;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author Ivan Landry ONANA
 */
public class Main extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Souk El Medina");
        Parent root = FXMLLoader.load(getClass().getResource("/Views/InterfaceAccueil.fxml"));
        
        Scene scene = new Scene(root);
        Image applicationIcon = new Image(getClass().getResourceAsStream("/Images/souk.png"));
        stage.getIcons().add(applicationIcon);        
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(e -> Platform.exit());
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}