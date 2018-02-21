package Controller;

import static Controller.MasterPageController.respansev;
import Entities.magasins;
import Services.magasinsServices;
import com.jfoenix.controls.JFXDatePicker;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class DetailMagasinController  implements Initializable {
  @FXML
    private AnchorPane AP1;
    
    @FXML
    private ImageView imagemag;

    @FXML
    private Label nommag;

    @FXML
    private Label emailmag;

    @FXML
    private Label numeromag;

    @FXML
    private Label descriptionmag;

    @FXML
    private JFXDatePicker datemag;

    @FXML
    private Label adressemag;
    magasins magasin;
    String pic;
      private Image i;
      magasins m;
 @Override
    public void initialize(URL location, ResourceBundle resources) {
       m= AjouterMagasinController.m;

        magasinsServices ms= new magasinsServices();
      // magasin= ms.rechercherMagasinsById(m.getIdMagasin());
       nommag.setText(m.getNomMagasin());
       numeromag.setText(m.getNumeroMagasin());
       adressemag.setText(m.getAdresseMagasin());
       descriptionmag.setText(m.getDescriptionMagasin());
       emailmag.setText(m.getContactMagasin());
       numeromag.setText(m.getNomMagasin());
         Date d= (Date) m.getDateCreationMagasin();
        datemag.setValue(d.toLocalDate());

       pic= m.getPhotoMagasin();
     
          i= new Image("http://localhost/uimg/"+pic);      
          imagemag.setImage(i);
      
    }
    @FXML
    void modifierMagasin(MouseEvent event) {
    try {
            AP1.getChildren().clear();
            AnchorPane newLoadedPaneA = FXMLLoader.load(getClass().getResource("/Fxml/ModifierMagasin.fxml"));

            respansev(newLoadedPaneA, 900, 450, 100,0);
            AP1.getChildren().add(newLoadedPaneA);
        } catch (IOException ex) {
            Logger.getLogger(MasterPageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void supprimerMagasin(MouseEvent event) {
     magasinsServices ms= new magasinsServices();
    // ms.supprimerMagasins(8);
    }

   

}
