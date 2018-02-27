/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Reclamation;

import service.Reclamation.ReclamationsServices;
import entites.Reclamation.Reclamations;

import entites.Utilisateur.Vendeur;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Ivan Landry ONANA
 */
public class InterfaceReclamationsClientsController implements Initializable {

    @FXML
    private AnchorPane container;
    @FXML
    private TableView<Reclamations> tableReclamations;
    @FXML
    private TableColumn<Reclamations, String> colIdReclamation;
    @FXML
    private TableColumn<Reclamations, String> colObjetReclamation;
    @FXML
    private TableColumn<Reclamations, String> colTypeReclamation;
    @FXML
    private TableColumn<Reclamations, String> colNomlUser;
    @FXML
    private TableColumn<Reclamations, String> colEmail;
    @FXML
    private TableColumn<Reclamations, String> colDate;
    @FXML
    private TextArea ecranAffichageReclamation;
    @FXML
    private Button btnReponseReclamation;
    @FXML
    private Button btnSuppressionReclamation;
    @FXML
    private Label labelIdentifiant;
    @FXML
    private TextField rechercheReclamation;
    public Vendeur vendeurConnecte;
    private SortedList<Reclamations> listeReclamationsTrie;
    ReclamationsServices rs = new ReclamationsServices();
    ObservableList<Reclamations> listeReclamations;
    private Reclamations selectedReclamation;
    public static Reclamations reclamationATraiter;
    public static Vendeur vendeurCte;
    @FXML
    private Label reclamationsDes3jours;
    @FXML
    private Label reclamationsDeLaSemaine;
    @FXML
    private Label reclamationsDuMois;
    @FXML
    private Label reclamationsAll;
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        vendeurConnecte = Util.Util.connectedUserVendeur;
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

    @FXML
    public void repondreReclamation(MouseEvent event) throws IOException {
        if (tableReclamations.getSelectionModel().getSelectedItem() == null) 
            dialog(Alert.AlertType.INFORMATION, "Veuillez selectionner une réclamation !", "Info");
        else {
            reclamationATraiter = tableReclamations.getSelectionModel().getSelectedItem();
            vendeurCte = Util.Util.connectedUserVendeur;
            loadWindows(event, "InterfaceReponseReclamation.fxml", "Répondre à une réclamation - Souk El Medina");
        }
    }
    
    @FXML
    public void openPageAccueil(MouseEvent event) throws IOException {
        loadWindows(event, "/Views/InterfaceAccueil.fxml", "Souk El Medina");
    }

    @FXML
    public void openClientPage(MouseEvent event) throws IOException {
        loadWindows(event, "/Views/InterfaceClients.fxml", "Espace Client - Souk El Medina");
    }

    @FXML
    public void openVendeurPage(MouseEvent event) throws IOException {
        String resource = "/Views/InterfaceVendeur.fxml";
        String title = "Espace Vendeur - Souk El Medina";
        loadWindows(event, resource, title);
    }

    @FXML
    public void openFAQPage(MouseEvent event) {
    }

    private void reclamationsDes3jours() {
        listeReclamations = rs.afficherReclamationsDes3DerniersJours(vendeurConnecte.getEmail());        
        loadData(listeReclamations);       
}

    private void reclamationsDeLaSemaine() {
        listeReclamations = rs.afficherReclamationsDeLaSemaine(vendeurConnecte.getEmail());        
        loadData(listeReclamations);        
}

    private void reclamationsDuMois() {
        listeReclamations = rs.afficherReclamationsDuMois(vendeurConnecte.getEmail());        
        loadData(listeReclamations);        
    }

    private void reclamationsAll() {
        listeReclamations = rs.afficherReclamations(vendeurConnecte.getEmail()); 
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

}
