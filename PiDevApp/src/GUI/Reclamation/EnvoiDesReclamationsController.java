/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Reclamation;

import GUI.Utilisateur.ClientInterfaceController;
import GUI.Utilisateur.LoginController;
import GUI.Utilisateur.VendeurInterfaceController;
import Util.Util;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import entites.Reclamation.Reclamations;
import entites.Utilisateur.Client;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import service.Reclamation.ReclamationsServices;
import service.Utilisateur.ClientService;
import service.Utilisateur.ServiceAuthentification;

/**
 * FXML Controller class
 *
 * @author user
 */
public class EnvoiDesReclamationsController implements Initializable {

    @FXML
    private Label labelIdentifiant;
    @FXML
    private ComboBox<String> typeReclamation;
    @FXML
    private TextField champObjetReclamation;
    @FXML
    private ComboBox<String> magasinSelection;
    @FXML
    private TextArea champContenuReclamation;
    @FXML
    private CheckBox docPdf;
    @FXML
    private Button btnEnvoiReclamation;
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
    private Button Reclamation;
    @FXML
    private Button Contacts;
   ReclamationsServices rs = new ReclamationsServices();
    private String notifEmail;
        private Client clientConnecte;

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
     menu3.setVisible(false);
      typeReclamation.getItems().addAll("Réclamation liée au produit","Réclamation liée à la commande","Réclamation liée à la livraison");
        //typeReclamation.setValue("Réclamation liée au produit");
        magasinSelection.getItems().addAll(rs.getListeMagasins());
        clientConnecte = Util.connectedUser;
        labelIdentifiant.setText("Hello, " + clientConnecte.getPrenom()+ " !");
        notifEmail = "";
    }    

    @FXML
    private void envoyerUneReclamation(ActionEvent event) {
     try {
            String nulll = null;
            String output = typeReclamation.getSelectionModel().getSelectedItem();
            String nomMagasin = magasinSelection.getSelectionModel().getSelectedItem();
            Reclamations reclamationClient = new Reclamations();
         
            reclamationClient.setTypeReclamation(output);
            reclamationClient.setObjetReclamation(champObjetReclamation.getText());
            reclamationClient.setContenuReclamation(champContenuReclamation.getText());
            if(!(magasinSelection.getValue() != null)
                    || !(champObjetReclamation.getText().trim().length() > 0 )
                    || !(typeReclamation.getValue() != null)
                    || !(champContenuReclamation.getText().trim().length() > 0)) {
                dialog(Alert.AlertType.ERROR, "Erreur", "Veuillez renseigner les champs requis : \n"
                        + "\tType réclamation"
                        + "\n\tObjet réclamation "
                        + "\n\tMagasin à adresser la réclamation "
                        + "\n\tContenu réclamation.");                
            }
            
            else {
                rs.envoyerUneReclamation(reclamationClient, Util.connectedUser.getEmail(), nomMagasin);
                if(docPdf.isSelected()) {
                    genererReclamationPdf();
                    rs.envoyerMail(Util.connectedUser.getEmail(), "Recapitulatif réclamation");
                    notifEmail = "\nUn email contenant le récapitulatif de votre réclamation vous a été envoyé.";
                }
                dialog(Alert.AlertType.INFORMATION, "Info", "Votre réclamation a bien été envoyée !"+ notifEmail);
                 ServiceAuthentification ser = new ServiceAuthentification();
            ClientService cs = new ClientService();
       

                    try {
               
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/Utilisateur/ClientInterface.fxml"));
                Parent root = (Parent) loader.load();
                ClientInterfaceController ClientIn = loader.getController();
                
                ClientIn.myFunction();
                Stage window;
                window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(new Scene(root));
                window.show();

            } catch (IOException ex) {
                System.out.println("catch : " + ex.getMessage());
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
        } catch (NumberFormatException e) {
            System.out.println("Erreur !\n" + e.getMessage());
        }
    }
    
    public void dialog(Alert.AlertType alertType,String title,String s){
        Alert alert = new Alert(alertType,s);
        alert.initStyle(StageStyle.UTILITY);
        alert.setTitle(title);
        alert.showAndWait();
    }
    
    private void genererReclamationPdf() {
        Document document = new Document(PageSize.A4, 30, 30, 30, 30);
        try {
            PdfWriter.getInstance(document, new FileOutputStream("Recapitulatif_reclamation.pdf"));
            document.addAuthor("Souk El Medina");
            document.addTitle("Réclamation de Mr. "+Util.connectedUser.getPrenom());
            document.open();
            Paragraph paragraph = new Paragraph("Recapitulatif de la réclamation du client: \t\t\t\t\t "+Util.connectedUser.getPrenom()+"\n\n\n");
            document.add(paragraph);
            paragraph.setExtraParagraphSpace(5);
            PdfPTable tableau = new PdfPTable(1);
            tableau.addCell(new Paragraph("Nom du client: \t\t"+Util.connectedUser.getPrenom()+"\n\n\n\n\n"));
            tableau.addCell(new Paragraph("Type de réclamation: \t\t"+typeReclamation.getSelectionModel().getSelectedItem()+"\n\n\n\n\n"));
            tableau.addCell(new Paragraph("Objet de la réclamation: \t\t"+champObjetReclamation.getText()+"\n\n\n\n\n"));
            tableau.addCell(new Paragraph("Contenu de la réclamation: \n"+champContenuReclamation.getText()+"\n\n\n\n\n"));
            tableau.addCell(new Paragraph("Magasin à adresser la réclamation: \t\t"+magasinSelection.getSelectionModel().getSelectedItem()+"\n\n\n\n\n"));
            tableau.addCell(new Paragraph("Nom du vendeur: \t\tSabri Lochtanis")+"\n\n\n\n\n");
            tableau.addCell(new Paragraph("Date: \t\t"+Date.valueOf(LocalDate.now())+"\n\n\n\n\n"));
            document.add(tableau);
            document.add(new Paragraph("\n\n\nMerci de votre fidélité !"));
            
        } catch (DocumentException | FileNotFoundException e) {
            System.out.println(e);
        }
        document.close();
        System.out.println("Génération du document reussie !..");
    }

    @FXML
    private void btnMapsAction(ActionEvent event) {
         LoadWindowParent("/GUI/Decouverte/Maps.fxml", event);
    }

    @FXML
    private void btnRechercheAction(ActionEvent event) {
         LoadWindowParent("/GUI/Decouverte/RechercheContactInterface.fxml", event);
    }

    @FXML
    private void afficherSuite(MouseEvent event) {
         menu.setVisible(true);
        menu2.setVisible(true);
    }

    @FXML
    private void btnespClientAction(ActionEvent event) {
         Util.connectedUser=null;
          LoadWindowParent("/GUI/Utilisateur/Login.fxml", event);
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
    private void PanierFenetre(ActionEvent event) {
         LoadWindowParent("/GUI/Panier/FXMLPanierInterface.fxml",event);
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
    private void btnReclamation(ActionEvent event) {
        LoadWindowParent("/GUI/Reclamation/EnvoiDesReclamations", event);
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
            Logger.getLogger(ClientInterfaceController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
      
    @FXML
    private void btncataloguesAction(ActionEvent event) {
         LoadWindowParent("/GUI/Magasins/AfficheListProduits.fxml", event);
    }

    @FXML
    private void btnBoutiquesAction(ActionEvent event) {
        LoadWindowParent("/GUI/Magasins/AfficheMagasins.fxml", event);
    }
}
