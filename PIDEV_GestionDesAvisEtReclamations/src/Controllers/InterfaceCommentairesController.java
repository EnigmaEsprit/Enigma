/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Client;
import Entities.Commentaires;
import Entities.Produits;
import Entities.UtilisateurCourant;
import Services.CommentairesServices;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Ivan Landry ONANA
 */
public class InterfaceCommentairesController implements Initializable {

    @FXML
    private AnchorPane container;
    private InterfaceReclamationsClientsController ircc = new InterfaceReclamationsClientsController();
    @FXML
    private ImageView imageProduitSelectionne;
    @FXML
    private Label refProduitSelectionne;
    @FXML
    private Label nomProduitSelectionne;
    private Text description;
    @FXML
    private ImageView photoUser;
    @FXML
    private TextField champCommentaire;
    private Produits produitCommente;
    @FXML
    private Label labelIdentifiant;
    private ImageView photoView;
    CommentairesServices cs = new CommentairesServices();
    public Client clientCte = UtilisateurCourant.getClientConnecte();
    @FXML
    private Button btnOK;
    @FXML
    private TableColumn<Commentaires, String> userprofil;
    @FXML
    private TableColumn<Commentaires, String> userComments;
    @FXML
    private TableColumn<Commentaires, String> editCell;
    @FXML
    private TableColumn<Commentaires, String> deleteCell;
    @FXML
    private TableColumn<Commentaires, String> dateCell;
    @FXML
    private TableView<Commentaires> tableCommentaires;
    private ObservableList<Commentaires> listeDesCommentaires = FXCollections.observableArrayList();
    private int temoin;
    private int idCommentaireAModifier;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        temoin = 0;
        labelIdentifiant.setText("Hello, " + clientCte.getUsername() + " !");
        produitCommente = InterfaceAccueilController.produitSelectionne;
        Image imageProduit = new Image(produitCommente.getUrlPhoto());
        imageProduitSelectionne.setImage(imageProduit);
        imageProduitSelectionne.setFitHeight(170.0);
        imageProduitSelectionne.setFitWidth(175.0);
        nomProduitSelectionne.setText(produitCommente.getNomProduit());
        refProduitSelectionne.setText(String.valueOf((produitCommente.getReferenceProduit())));
        Image avatar = new Image(cs.getPhotoUser(clientCte.getEmail()));
        photoUser.setImage(avatar);
        photoUser.setFitHeight(25.0);
        photoUser.setFitWidth(25.0);
        chargerCommentaires();       
//        btnOK.setOnAction((event) -> {
//            if (temoin == 1){            
//                Commentaires c = new Commentaires(idCommentaireAModifier, champCommentaire.getText());
//                cs.modifierCommentaires(c);
//                temoin = 0;
//            }
//            else{
//                Commentaires c = new Commentaires(champCommentaire.getText());
//                cs.ajouterCommentaires(c, cs.selectID(clientCte.getEmail()), produitCommente.getIdProduit());
//            }
//            refresh();
//                
//        });        
        champCommentaire.setOnKeyPressed((KeyEvent ke) -> {
                if (ke.getCode() == KeyCode.ENTER){
                    if (temoin == 1){            
                        Commentaires c = new Commentaires(idCommentaireAModifier, champCommentaire.getText());
                        cs.modifierCommentaires(c);
                        temoin = 0;
                    }
                    else{
                        Commentaires c = new Commentaires(champCommentaire.getText());
                        cs.ajouterCommentaires(c, cs.selectID(clientCte.getEmail()), produitCommente.getIdProduit());
                    }
                    refresh();
                }
        });
    }    

    @FXML
    private void openPageAccueil(ActionEvent event) throws IOException {
        ircc.loadWindows(event, "/Views/InterfaceAccueil.fxml", "Souk El Medina");
    }

    @FXML
    private void openClientPage(ActionEvent event) throws IOException {
        ircc.loadWindows(event, "/Views/InterfaceEnvoiDesReclamations.fxml", "Envoi des réclamations - Souk El Medina");
    }

    @FXML
    private void openVendeurPage(ActionEvent event) throws IOException {
        ircc.loadWindows(event, "/Views/InterfaceReclamationsClients.fxml", "Gestion des Réclamations - Souk El Medina");
    }

    
    private void chargerCommentaires() {
        userprofil.setCellValueFactory(new PropertyValueFactory<>("imageUser"));
        userComments.setCellValueFactory(new PropertyValueFactory<>("contenuCommentaire"));
        dateCell.setCellValueFactory(new PropertyValueFactory<>("dateAjoutCommentaire"));
        editCell.setCellValueFactory(new PropertyValueFactory<>("bouton"));
        deleteCell.setCellValueFactory(new PropertyValueFactory<>("bouton2"));
        ImageView photoUsers;

        for (Commentaires c : cs.afficherCommentaires(produitCommente.getIdProduit())) {
            Button btEdit = new Button("    Edit    ");
            Button btDelete = new Button(" Delete ");
           
            photoUsers = new ImageView(c.getUrlUserPhoto());
            photoUsers.setFitHeight(30.0);
            photoUsers.setFitWidth(30.0);
            listeDesCommentaires.add(new Commentaires(c.getIdCommentaire(), c.getContenuCommentaire(), c.getDateAjoutCommentaire(), photoUsers, btEdit, btDelete));
            btEdit.setOnAction((event) -> {
                champCommentaire.setText(c.getContenuCommentaire());
                temoin = 1;
                idCommentaireAModifier = c.getIdCommentaire();
            });
            btDelete.setOnAction((event) -> {
                cs.supprimerCommentaires(c);
                refresh();
            });                   
        }
        tableCommentaires.setItems(listeDesCommentaires);
        
    }
    
    
    private void refresh(){
        listeDesCommentaires.clear();
        listeDesCommentaires.removeAll(listeDesCommentaires);
        chargerCommentaires();
    }

}
