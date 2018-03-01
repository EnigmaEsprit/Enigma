/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Commentaires;

import entites.Utilisateur.Client;
import entites.Commentaires.Commentaires;
import GUI.Panier.FXMLPaymentCardController;
import GUI.Reclamation.InterfaceReclamationsClientsController;
import entites.Produit.produits;

import service.Commentaires.CommentairesServices;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Ivan Landry ONANA
 */
public class InterfaceCommentairesController implements Initializable {

    @FXML
    private AnchorPane container;
    private InterfaceReclamationsClientsController ircc = new InterfaceReclamationsClientsController();
    private ImageView imageProduitSelectionne;
    private Label refProduitSelectionne;
    private Label nomProduitSelectionne;
    private Text description;
    @FXML
    private ImageView photoUser;
    @FXML
    private TextField champCommentaire;
    private produits produitCommente;
    private Label labelIdentifiant;
    private ImageView photoView;
    CommentairesServices cs = new CommentairesServices();
    public Client clientCte = Util.Util.connectedUser;
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
        labelIdentifiant.setText("Hello, " + clientCte.getNom() + " !");
       // produitCommente = InterfaceAccueilController.produitSelectionne;
       // Image imageProduit = new Image(produitCommente.getUrlPhoto());
       // imageProduitSelectionne.setImage(imageProduit);
        imageProduitSelectionne.setFitHeight(170.0);
        imageProduitSelectionne.setFitWidth(175.0);
        nomProduitSelectionne.setText(produitCommente.getNomProduit());
        refProduitSelectionne.setText(String.valueOf((produitCommente.getReferenceProduit())));
        Image avatar = new Image(cs.getPhotoUser(clientCte.getEmail()));
        photoUser.setImage(avatar);
        photoUser.setFitHeight(25.0);
        photoUser.setFitWidth(25.0);
        chargerCommentaires();       
       
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
            Logger.getLogger(InterfaceCommentairesController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

}
