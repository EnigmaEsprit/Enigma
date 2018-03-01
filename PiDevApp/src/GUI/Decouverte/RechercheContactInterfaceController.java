/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Decouverte;

import entites.Magasins.magasins;
import GUI.Utilisateur.ClientInterfaceController;
import GUI.Utilisateur.InscriptionController;
import GUI.Utilisateur.LoginController;
import GUI.Utilisateur.VendeurInterfaceController;
import Util.Util;
import entites.Utilisateur.Client;
import entites.Decouverte.Recherche;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import service.Utilisateur.AdminService;
import service.Decouverte.RechercheService;

/**
 * FXML Controller class
 *
 * @author user
 */
public class RechercheContactInterfaceController implements Initializable {

    @FXML
    private Label labmsg;
    @FXML
    private TextField recherche;
    @FXML
    private TableColumn<Recherche, String> nom;
    @FXML
    private TableColumn<Recherche, String> prenom;
    @FXML
    private TableColumn<Recherche, String> adresse;
    @FXML
    private TableColumn<Recherche, String> ville;
    @FXML
    private TableColumn<Recherche, Integer> zip;
    @FXML
    private TableColumn<Recherche, String> ntelephone;
    @FXML
    private TableColumn<Recherche, String> mail;
    @FXML
    private TableColumn<Recherche, String> nommagazin;
    @FXML
    private TableView<Recherche> tablerecherche;
 private ObservableList<Recherche>data;
    @FXML
    private Button Editer;
    @FXML
    private Pane menu;
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
    private Pane menu3;
    @FXML
    private Button recherche1;
    @FXML
    private Button Maps;
    @FXML
    private Button Contacts;
    @FXML
    private ImageView profil;
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
      if(Util.connectedUser== null)
        {
            
        }
      else if(Util.connectedUser.getImg()==null)
                {
                    
                profil.setImage(new javafx.scene.image.Image("http://localhost/uimg/user.jpg"));
                }
                else
                {
                    profil.setImage(new javafx.scene.image.Image("http://localhost/uimg/"+Util.connectedUser.getImg()));
               
             
                }
    menu2.setVisible(false);
    menu3.setVisible(false);
    }    

         @FXML
    private void btnespClientAction(ActionEvent event) {
              System.out.println(Util.connectedUser);
        if (Util.connectedUser==null){
            LoadWindowParent("/GUI/Utilisateur/Login.fxml", event);
        }
        else
        {
           try {
              
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/Utlisateur/ClientInterface.fxml"));
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
                window.setResizable(false);
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
    private void btnChercherAction(ActionEvent event) {
         System.out.println("+++++++-------"+recherche.getText());
        RechercheService rs = new RechercheService();
        if(recherche.getText().length()==0)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Email Change");
            alert.setHeaderText("Bienvenue");
            alert.setContentText("Verifier votre saisie");
            alert.showAndWait();
        }
        else{
       data=rs.searsh(recherche.getText());
        
           
        if(data.isEmpty()==true)
        {
        
            
               Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Email Change");
            alert.setHeaderText("Bienvenue");
            alert.setContentText("introvable");
            alert.showAndWait();
             
        
        }
        else
        {
            data=rs.searsh(recherche.getText());
            System.out.println("aa");
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        
        mail.setCellValueFactory(new PropertyValueFactory<>("email"));
        adresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        ville.setCellValueFactory(new PropertyValueFactory<>("ville"));
        zip.setCellValueFactory(new PropertyValueFactory<>("zip"));
        nommagazin.setCellValueFactory(new PropertyValueFactory<>("nom_magazin"));
        ntelephone.setCellValueFactory(new PropertyValueFactory<>("nt"));
        tablerecherche.setItems(null);
        tablerecherche.setItems(data);
        
        }
       
        
        System.out.println("bb");
    }
    
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
    private void btnEventAction(ActionEvent event) {
    }

      @FXML
    private void afficherSuitemenu3(MouseEvent event) {
         menu.setVisible(true);
        menu3.setVisible(true);
 
    }
    @FXML
    private void exitemenu3(MouseEvent event) {
        menu3.setOnMouseEntered(evt -> {menu3.setVisible(true);});
         menu3.setOnMouseExited(evt -> {menu3.setVisible(false);});
       
        menu3.setVisible(false);
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
            Logger.getLogger(InscriptionController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    /* @FXML
    private void btnChercherAction(KeyEvent event) {
    FilteredList<Recherche> filterData;
    RechercheService rs = new RechercheService();
    data = FXCollections.observableArrayList(rs.searsh(recherche.getText()));
    filterData = new FilteredList<>(data, H -> true);
    recherche.textProperty().addListener((obsevable, oldvalue, newvalue) -> {
    filterData.setPredicate(A -> {
    if (newvalue == null || newvalue.isEmpty()) {
    return true;
    }
    String typedText = newvalue.toLowerCase();
    if (A.getNom_magazin().toLowerCase().contains(typedText)) {
    return true;
    }
    return false;
    });
    SortedList<Recherche> sortedList = new SortedList<>(filterData);
    sortedList.comparatorProperty().bind(tablerecherche.comparatorProperty());
    tablerecherche.setItems(sortedList);
    });
    }*/
   @FXML
    private void btncataloguesAction(ActionEvent event) {
         LoadWindowParent("/GUI/Magasins/AfficheListProduits.fxml", event);
    }

    @FXML
    private void btnBoutiquesAction(ActionEvent event) {
        LoadWindowParent("/GUI/Magasins/AfficheMagasins.fxml", event);
}
}