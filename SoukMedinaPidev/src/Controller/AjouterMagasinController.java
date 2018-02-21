package Controller;

import static Controller.MasterPageController.respansev;
import Entities.Upload;
import Entities.magasins;
import Services.magasinsServices;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

public class AjouterMagasinController {

    @FXML
    private AnchorPane AP1;

    @FXML
    private Group Typegroup;

    @FXML
    private DatePicker datemagasin;
    @FXML
    private JFXTextField adressemagasin;
    
      @FXML
    private Label imgnom;

    @FXML
    private JFXTextField descriptionmagasin;

    @FXML
    private JFXTextField emailmagasin;

    @FXML
    private JFXButton btnajoutermagasin;

    @FXML
    private JFXTextField numeromagasin;

    @FXML
    private JFXTextField nommagasin;
 @FXML
    private JFXButton listmagasin;

   
    @FXML
    private JFXButton photomagasin;
  private File file;
    private Image image;
    String pic;
    static magasins m ;
    @FXML
    void ajouterMagasin(ActionEvent event) {
        magasinsServices ms= new magasinsServices();
        
        if (validateFields()){
        TrayNotification tray = new TrayNotification("Notification !", "magasin Crée avec succée", NotificationType.SUCCESS);
        tray.showAndDismiss(javafx.util.Duration.seconds(2));
       
        System.out.println("********************\n"+nommagasin.getText());
      
          m  = new magasins(nommagasin.getText(),pic,descriptionmagasin.getText(),java.sql.Date.valueOf(datemagasin.valueProperty().getValue()),emailmagasin.getText(),adressemagasin.getText(),numeromagasin.getText(),11);
        ms.ajouterMagasins(m);
          
        try {
            AP1.getChildren().clear();
            AnchorPane newLoadedPaneA = FXMLLoader.load(getClass().getResource("/Fxml/ListMagasins.fxml"));

            respansev(newLoadedPaneA, 900, 450, 100,0);
            AP1.getChildren().add(newLoadedPaneA);
        } catch (IOException ex) {
            Logger.getLogger(MasterPageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    }
        private boolean validateFields () {
           
         if (nommagasin.getText().isEmpty() | emailmagasin.getText().isEmpty() |  adressemagasin.getText().isEmpty()|  numeromagasin.getText().isEmpty()|  descriptionmagasin.getText().isEmpty() ){
         Alert alert =new Alert (Alert.AlertType.ERROR);
        alert.setTitle("Validation Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Vérifiez les champs !");
                         alert.showAndWait();

         return false;
         }
           if(!isValidEmailAddress(emailmagasin.getText()))
         {
              Alert alert =new Alert (Alert.AlertType.ERROR);
        alert.setTitle("Validation Dialog");
        alert.setHeaderText(null);
          alert.setContentText("Adresse email invalide !");
                           alert.showAndWait();

         return false;

         }
         if (new Date().before(java.sql.Date.valueOf(datemagasin.valueProperty().getValue())))
         {
              Alert alert =new Alert (Alert.AlertType.ERROR);
        alert.setTitle("Validation Dialog");
        alert.setHeaderText(null);
         alert.setContentText("verifier la date saisie !");
                          alert.showAndWait();

         return false;

         }
         if(!isValidatePhoneNumber(numeromagasin.getText()))
         {
              Alert alert =new Alert (Alert.AlertType.ERROR);
        alert.setTitle("Validation Dialog");
        alert.setHeaderText(null);
         alert.setContentText("Numero de telephone incorrecte !");
           alert.showAndWait();

                  return false;

         }
        return true;
    }
        public boolean isValidatePhoneNumber(String number)
        {
       Pattern pattern = Pattern.compile("\\d{8}");
        Matcher matcher = pattern.matcher(number); 
        return matcher.matches();
        }
        public boolean isValidEmailAddress(String email) {
           String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
           java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
           java.util.regex.Matcher m = p.matcher(email);
           return m.matches();
    }
        
    @FXML
    void ajouterPhoto(ActionEvent event) throws IOException {
     FileChooser fileChooser = new FileChooser();
            file= fileChooser.showOpenDialog(null);
             FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
            FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
            fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);


            //pic=(file.toURI().toString());
            pic=new Upload().upload(file,"img");
            System.out.println(pic);
            image= new Image("http://localhost/uimg/"+pic);
            imgnom.setText(pic);
            //photo.setImage(image);
    }
     @FXML
    void ListerMagasin(ActionEvent event) {
try {
            AP1.getChildren().clear();
            AnchorPane newLoadedPaneA = FXMLLoader.load(getClass().getResource("/Fxml/ListMagasins.fxml"));

            respansev(newLoadedPaneA, 900, 450, 100,0);
            AP1.getChildren().add(newLoadedPaneA);
        } catch (IOException ex) {
            Logger.getLogger(MasterPageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
