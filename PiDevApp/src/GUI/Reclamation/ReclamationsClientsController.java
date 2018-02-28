/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Reclamation;

import static GUI.Reclamation.InterfaceReclamationsClientsController.reclamationATraiter;
import static GUI.Reclamation.InterfaceReclamationsClientsController.vendeurCte;
import GUI.Utilisateur.ClientInterfaceController;
import GUI.Utilisateur.LoginController;
import GUI.Utilisateur.VendeurInterfaceController;
import Util.Util;
import entites.Reclamation.Reclamations;
import entites.Utilisateur.Vendeur;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.TranslateTransition;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import service.Reclamation.ReclamationsServices;

/**
 * FXML Controller class
 *
 * @author user
 */
public class ReclamationsClientsController implements Initializable {

    @FXML
    private Label labelIdentifiant;
    @FXML
    private TextField rechercheReclamation;
    @FXML
    private TableView<Reclamations> tableReclamations;
    @FXML
    private TableColumn<Reclamations, String> colIdReclamation;
    @FXML
    private TableColumn<Reclamations, String> colObjetReclamation;
    @FXML
    private TableColumn<Reclamations, String> colTypeReclamation;
    @FXML
    private TableColumn<Reclamations, String> colDate;
    @FXML
    private TableColumn<Reclamations, String> colNomlUser;
    @FXML
    private TableColumn<Reclamations, String> colEmail;
    @FXML
    private Label reclamationsDes3jours;
    @FXML
    private Label reclamationsDeLaSemaine;
    @FXML
    private Label reclamationsDuMois;
    @FXML
    private Label reclamationsAll;
    @FXML
    private TextArea ecranAffichageReclamation;
    @FXML
    private Button btnReponseReclamation;
    @FXML
    private Button btnSuppressionReclamation;
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
    private Button Reclamation;
    public Vendeur vendeurConnecte;
    private SortedList<Reclamations> listeReclamationsTrie;
    ReclamationsServices rs = new ReclamationsServices();
    ObservableList<Reclamations> listeReclamations;
    private Reclamations selectedReclamation;
    public static Reclamations reclamationATraiter;
    public static Vendeur vendeurCte = Util.connectedUserVendeur;

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
     
         vendeurConnecte = Util.connectedUserVendeur;
        labelIdentifiant.setText("Hello, " + vendeurConnecte.getPrenom() +  " !");
        listeReclamations = rs.afficherReclamations(vendeurConnecte.getEmail()); 
        reclamationsAll();
        // selection d'une réclamation à l'aide d'un click
        tableReclamations.setOnMouseClicked((MouseEvent event) -> {
            if (event.getClickCount() >=1 ) {
                getReclamationContent();
            }
        });  
        reclamationsDes3jours.setOnMouseClicked((event) -> {
            reclamationsDes3jours();
        });
        reclamationsDeLaSemaine.setOnMouseClicked((event) -> {
            reclamationsDeLaSemaine();
        });
        reclamationsDuMois.setOnMouseClicked((event) -> {
            reclamationsDuMois();
        });
        reclamationsAll.setOnMouseClicked((event) -> {
            reclamationsAll();
        });
    }    

    @FXML
    private void repondreReclamation(ActionEvent event) throws IOException {
        if (tableReclamations.getSelectionModel().getSelectedItem() == null) 
            dialog(Alert.AlertType.INFORMATION, "Veuillez selectionner une réclamation !", "Info");
        else {
            reclamationATraiter = tableReclamations.getSelectionModel().getSelectedItem();
            vendeurCte = Util.connectedUserVendeur;
            LoadWindowParent("ReponseReclamation.fxml", event);
        }
    }

    @FXML
   private void supprimerReclamation(MouseEvent event) {
        if (tableReclamations.getSelectionModel().getSelectedItem() != null) {
            selectedReclamation = tableReclamations.getSelectionModel().getSelectedItem();
            Boolean res = dialog(Alert.AlertType.CONFIRMATION, "Voulez-vous vraiment supprimer cette reclamation ?", "Confirmation");
            if(res){
                rs.supprimerUneReclamation(selectedReclamation);
                dialog(Alert.AlertType.INFORMATION, "Une réclamation a été supprimée !", "Info");
                refresh();
            }
        }
        else{
            dialog(Alert.AlertType.INFORMATION, "Veuillez selectionner une réclamation !", "Info");
        }
    }

    private void getReclamationContent() {
        if (tableReclamations.getSelectionModel().getSelectedItem() != null) {
            selectedReclamation = tableReclamations.getSelectionModel().getSelectedItem();
            ecranAffichageReclamation.setText(selectedReclamation.getContenuReclamation());
        }
    }

     private void reclamationsDes3jours() {
        listeReclamations = rs.afficherReclamationsDes3DerniersJours(Util.connectedUserVendeur.getEmail());        
        loadData(listeReclamations);       
}

    private void reclamationsDeLaSemaine() {
        listeReclamations = rs.afficherReclamationsDeLaSemaine(Util.connectedUserVendeur.getEmail());        
        loadData(listeReclamations);        
}

    private void reclamationsDuMois() {
        listeReclamations = rs.afficherReclamationsDuMois(Util.connectedUserVendeur.getEmail());        
        loadData(listeReclamations);        
    }

    private void reclamationsAll() {
        listeReclamations = rs.afficherReclamations(Util.connectedUserVendeur.getEmail()); 
        loadData(listeReclamations);        
    }

    private Boolean dialog(Alert.AlertType alertType, String s, String i){
        Alert alert = new Alert(alertType,s);
        alert.initStyle(StageStyle.UTILITY);
        alert.setTitle(i);
        final Optional<ButtonType> result = alert.showAndWait();
        return result.get() == ButtonType.OK;
    }
    
    private void loadData(ObservableList<Reclamations> liste) {
        //récuperation des réclamations adressées au vendeur connecté depuis la base de données
        // Chargement des données dans la table
        colIdReclamation.setCellValueFactory(new PropertyValueFactory<>("idReclamation"));        
        colTypeReclamation.setCellValueFactory(new PropertyValueFactory<>("typeReclamation"));        
        colObjetReclamation.setCellValueFactory(new PropertyValueFactory<>("ObjetReclamation")); 
        colNomlUser.setCellValueFactory(new PropertyValueFactory<>("nomClient"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("emailClient"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("dateEnvoiReclamation"));    
        // Insertion de la liste des réclamations dans une liste filtrée
        FilteredList<Reclamations> filtreDonnees = new FilteredList<>(liste, rcl -> true);
        rechercheReclamation.textProperty().addListener((observable, oldValue, texteSaisi) -> {
            
            // Définir le filtre Predicate chaque fois que le filtre change
            filtreDonnees.setPredicate((reclamation) -> {
                if (texteSaisi.isEmpty())
                    return true;
                else 
                    return reclamation.getNomClient().toLowerCase().contains(texteSaisi.toLowerCase());
            });
        });
        
        // Association de la liste des réclamations filtrée à une liste triée 
        listeReclamationsTrie = new SortedList<>(filtreDonnees);
        
        // Liaison du comparateur de la liste de réclamation triée au comparateur de la table d'affichage
        listeReclamationsTrie.comparatorProperty().bind(tableReclamations.comparatorProperty());
        tableReclamations.setItems(listeReclamationsTrie);
    }

    private void refresh(){
        listeReclamations.clear();
        listeReclamations.removeAll(listeReclamations);
        loadData(listeReclamations);
    }

    public void loadWindows(MouseEvent event, String resource, String title) throws IOException {
        Parent homePageParent = FXMLLoader.load(getClass().getResource(resource));
        Scene homePageScene = new Scene(homePageParent);
        Stage appStage =(Stage)((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(homePageScene);
        appStage.show();
        appStage.setTitle(title);
    }    
    @FXML
    private void afficherSuite(MouseEvent event) {
         menu.setVisible(true);
        menu2.setVisible(true);
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
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    @FXML
    private void btnespVendeurAction(ActionEvent event) {
        try {
            Util.connectedUserVendeur = null;
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/Utilisateur/LoginVendeur.fxml"));
            
            Parent root = (Parent) loader.load();
           
             
            Stage window;
            window = (Stage) ((Node) event.getSource()).getScene().getWindow();

            window.setScene(new Scene(root));

            window.show();

           
        } catch (IOException ex) {
            Logger.getLogger(ClientInterfaceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btnespAdminAction(ActionEvent event) {
             LoadWindowParent("/GUI/Utilisateur/LoginAdmin.fxml", event);

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
    private void exite(MouseEvent event) {
         menu2.setOnMouseEntered(evt -> {menu2.setVisible(true);});
         menu2.setOnMouseExited(evt -> {menu2.setVisible(false);});
       
        menu2.setVisible(false);
    }

    @FXML
    private void btnEventAction(ActionEvent event) {

    }

    @FXML
    private void btnReclamationAction(ActionEvent event) {
          LoadWindowParent("/GUI/Reclamation/ReclamationsClients.fxml", event);
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
            Logger.getLogger(VendeurInterfaceController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
}
