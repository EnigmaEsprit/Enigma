    package GUI.Magasins;


   // import static  GUI.Produits.MasterPageController.respansev;
    import entites.Produit.Upload;

   // import static  GUI.Produits.MasterPageController.respansev;
import entites.Produit.Upload;
import GUI.Utilisateur.ClientInterfaceController;
import GUI.Utilisateur.VendeurInterfaceController;
import Util.Util;

    import entites.Utilisateur.Utilisateur;
    import entites.Magasins.magasins;
    
import service.Magasins.magasinsServices;
import service.Produits.ProduitServices;
    import com.jfoenix.controls.JFXButton;
    import com.jfoenix.controls.JFXComboBox;
    import com.jfoenix.controls.JFXTextField;
import entites.Utilisateur.Vendeur;
    import java.io.File;
    import java.io.IOException;
    import java.net.URL;
    import java.util.Date;
    import java.util.List;
    import java.util.ResourceBundle;
    import java.util.logging.Level;
    import java.util.logging.Logger;
    import java.util.regex.Matcher;
    import java.util.regex.Pattern;
import javafx.animation.TranslateTransition;
    import javafx.collections.FXCollections;
    import javafx.collections.ObservableList;
    import javafx.event.ActionEvent;
    import javafx.fxml.FXML;
    import javafx.fxml.FXMLLoader;
    import javafx.fxml.Initializable;
    import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
    import javafx.scene.control.Alert;
import javafx.scene.control.Button;
    import javafx.scene.control.DatePicker;
    import javafx.scene.control.Label;
    import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
    import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
    import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import service.Utilisateur.AdminService;
import service.Utilisateur.VendeurService;
    import tray.notification.NotificationType;
    import tray.notification.TrayNotification;

    public class AjouterMagasinController  implements Initializable {

    //private AnchorPane AP1;

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
    private JFXComboBox<String> lstvendeur;
        VendeurService us= new VendeurService();
        AdminService as = new AdminService();
    @FXML
    private Pane menu;
    @FXML
    private Pane menu3;
    @FXML
    private Button Maps;
    @FXML
    private Button recherche;
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
    private Button Contacts;
     @Override
    public void initialize(URL location, ResourceBundle resources) {
        
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
        menu3.setVisible(false);
        
      //  us=new VendeurService();
        
        ObservableList<String> data = FXCollections.observableArrayList();
        ObservableList<Vendeur> lsm=as.SelectVendeur();
        for (int i=0; i<lsm.size();i++)
        {
        data.add(lsm.get(i).getNom());
        
                }
  lstvendeur.setItems(data);
         
        lstvendeur.getSelectionModel().selectFirst();

    }



    @FXML
    void ajouterMagasin(ActionEvent event) {
        magasinsServices ms= new magasinsServices();

        if (validateFields()){
        TrayNotification tray = new TrayNotification("Notification !", "magasin Crée avec succée", NotificationType.SUCCESS);
        tray.showAndDismiss(javafx.util.Duration.seconds(2));

        System.out.println("********************\n"+nommagasin.getText());
       Utilisateur u=new Utilisateur();
        u=us.rechercherVendeurByName(lstvendeur.getSelectionModel().getSelectedItem());
          m  = new magasins(nommagasin.getText(),pic,descriptionmagasin.getText(),java.sql.Date.valueOf(datemagasin.valueProperty().getValue()),emailmagasin.getText(),adressemagasin.getText(),numeromagasin.getText(),u.getId());
           ms.ajouterMagasins(m);
            //us.modifierActifUser(u,1);
        try {
           // AP1.getChildren().clear();
            AnchorPane newLoadedPaneA = FXMLLoader.load(getClass().getResource("/GUI/Magasins/ListMagasins.fxml"));

            //respansev(newLoadedPaneA, 900, 450, 100,0);
           // AP1.getChildren().add(newLoadedPaneA);
        } catch (IOException ex) {
            Logger.getLogger(AjouterMagasinController.class.getName()).log(Level.SEVERE, null, ex);
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


            pic=new Upload().upload(file,"img");
            System.out.println(pic);
            image= new Image("http://localhost/uimg/"+pic);
            imgnom.setText(pic);
    }
     @FXML
    void ListerMagasin(ActionEvent event) {
    try {
           // AP1.getChildren().clear();
            AnchorPane newLoadedPaneA = FXMLLoader.load(getClass().getResource("/GUI/Magasins/ListMagasins.fxml"));

           // respansev(newLoadedPaneA, 900, 450, 100,0);
           // AP1.getChildren().add(newLoadedPaneA);
        } catch (IOException ex) {
            Logger.getLogger(AjouterMagasinController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
                  window.setResizable(false);
                window.show();

            } catch (IOException ex) {
                System.out.println("catch : " + ex.getMessage());
                Logger.getLogger(AfficheMagasinsController.class.getName()).log(Level.SEVERE, null, ex);
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
                  window.setResizable(false);
                window.show();

            } catch (IOException ex) {
                System.out.println("catch : " + ex.getMessage());
                Logger.getLogger(AfficheMagasinsController.class.getName()).log(Level.SEVERE, null, ex);
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


    @FXML
    private void exitemenu3(MouseEvent event) {
        menu3.setOnMouseEntered(evt -> {menu3.setVisible(true);});
         menu3.setOnMouseExited(evt -> {menu3.setVisible(false);});
       
        menu3.setVisible(false);
    }

    @FXML
    private void afficherSuitemenu3(MouseEvent event) {
         menu.setVisible(true);
        menu3.setVisible(true);
 
    }

    @FXML
    private void btnRechercheAction(ActionEvent event) {
        LoadWindowParent("/GUI/Decouverte/RechercheContactInterface.fxml", event);
    }

    @FXML
    private void btnMapsAction(ActionEvent event) {
        LoadWindowParent("/GUI/Decouverte/Maps.fxml", event);
    }

    @FXML
    private void PanierFenetre(ActionEvent event) {
        
       LoadWindowParent("/GUI/Panier/FXMLPanierInterface.fxml",event);
    }

    private void LoadWindow(String loc){
         try {
            Parent homePageParent = FXMLLoader.load(getClass().getResource(loc));
            Stage stage = new Stage(StageStyle.DECORATED);
            //stage.setTitle(name);
            stage.setScene(new Scene(homePageParent));
            stage.setResizable(false);
            stage.show();

        } catch (IOException ex) {
            Logger.getLogger(AjouterMagasinController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void LoadWindowParent(String loc,ActionEvent event){
        try {
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource(loc));
            
            Parent root = (Parent) loader.load();
           
             
            Stage window;
            window = (Stage) ((Node) event.getSource()).getScene().getWindow();

            window.setScene(new Scene(root));
            
            window.setResizable(false);

            window.show();

           
        } catch (IOException ex) {
            Logger.getLogger(AjouterMagasinController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    @FXML
    private void btnEventAction(ActionEvent event) {
    }

    }
