package Controller;

import Entities.Upload;
import Entities.magasins;
import Services.magasinsServices;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.DatePicker;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

public class ModifierMagasinController implements Initializable {

    @FXML
    private AnchorPane AP1;

    @FXML
    private Group Typegroup;

    @FXML
    private DatePicker datemagasin;

    @FXML
    private JFXTextField adressemagasin;

    @FXML
    private JFXTextField descriptionmagasin;

    @FXML
    private JFXTextField emailmagasin;

    @FXML
    private JFXButton btnModifiermagasin;

    @FXML
    private JFXTextField numeromagasin;

    @FXML
    private JFXTextField nommagasin;

    @FXML
    private JFXButton photomagasin;

    magasins m;
        magasins mag;
private File file;
    private Image image;
    String pic;
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
            //photo.setImage(image);
    
    }

    @FXML
    void modifierMagasin(ActionEvent event) {
       // System.out.println(mag.getIdMagasin());
             magasinsServices ms= new magasinsServices();

        mag=ms.rechercherMagasinsByName(m.getNomMagasin());
     mag.setAdresseMagasin(adressemagasin.getText());
     mag.setContactMagasin(emailmagasin.getText());
     mag.setDateCreationMagasin(java.sql.Date.valueOf(datemagasin.valueProperty().getValue()));
     mag.setDescriptionMagasin(descriptionmagasin.getText());
     mag.setNomMagasin(nommagasin.getText());
     mag.setNumeroMagasin(numeromagasin.getText());
    if (pic==null)
     {
     mag.setPhotoMagasin(m.getPhotoMagasin());}
    else{
        mag.setPhotoMagasin(pic);
    }
      ms.modifierMagasin(mag);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
         m= AjouterMagasinController.m;

      // magasin= ms.rechercherMagasinsById(m.getIdMagasin());
       nommagasin.setText(m.getNomMagasin());
       numeromagasin.setText(m.getNumeroMagasin());
       adressemagasin.setText(m.getAdresseMagasin());
       descriptionmagasin.setText(m.getDescriptionMagasin());
       emailmagasin.setText(m.getContactMagasin());
       numeromagasin.setText(m.getNomMagasin());
         Date d= (Date) m.getDateCreationMagasin();
        datemagasin.setValue(d.toLocalDate());
      
    }

}
