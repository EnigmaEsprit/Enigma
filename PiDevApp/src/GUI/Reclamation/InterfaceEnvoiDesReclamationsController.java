/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Reclamation;

import GUI.Utilisateur.ClientInterfaceController;
import GUI.Utilisateur.LoginController;
import Util.Util;
import entites.Utilisateur.Client;
import entites.Reclamation.Reclamations;

import service.Reclamation.ReclamationsServices;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import service.Utilisateur.ClientService;
import service.Utilisateur.ServiceAuthentification;

/**
 * FXML Controller class
 *
 * @author Ivan Landry ONANA
 */
public class InterfaceEnvoiDesReclamationsController implements Initializable {

    @FXML
    private AnchorPane container;
    @FXML
    private ComboBox<String> typeReclamation;
    @FXML
    private TextField champObjetReclamation;
    @FXML
    private TextArea champContenuReclamation;
    @FXML
    private Button btnEnvoiReclamation;
    private Client clientConnecte;
    @FXML
    private Label labelIdentifiant;
    @FXML
    private ComboBox<String> magasinSelection;
    @FXML
    private CheckBox docPdf;
    ReclamationsServices rs = new ReclamationsServices();
    private String notifEmail;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        typeReclamation.getItems().addAll("Réclamation liée au produit","Réclamation liée à la commande","Réclamation liée à la livraison");
        //typeReclamation.setValue("Réclamation liée au produit");
        magasinSelection.getItems().addAll(rs.getListeMagasins());
        clientConnecte = Util.connectedUser;
        labelIdentifiant.setText("Hello, " + clientConnecte.getPrenom()+ " !");
        notifEmail = "";
    }

    public InterfaceEnvoiDesReclamationsController() {
    }
    

    @FXML
    private void openPageAccueil(MouseEvent event) throws IOException {
        Parent homePageParent = FXMLLoader.load(getClass().getResource("/GUI/Reclamation/InterfaceAccueil.fxml"));
        Scene homePageScene = new Scene(homePageParent);
        Stage appStage =(Stage)((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(homePageScene);
        appStage.setTitle("Souk El Medina");
        appStage.show();
    }

    @FXML
    private void openClientPage(MouseEvent event) throws IOException {
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

    @FXML
    private void openVendeurPage(MouseEvent event) throws IOException {
        Parent homePageParent = FXMLLoader.load(getClass().getResource("/GUI/Reclamation/InterfaceVendeur.fxml"));
        Scene homePageScene = new Scene(homePageParent);
        Stage appStage =(Stage)((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(homePageScene);
        appStage.show();
        appStage.setTitle("Espace Vendeur - Souk El Medina");
    }

    @FXML
    private void openFAQPage(MouseEvent event) {
    }

    @FXML
    private void envoyerUneReclamation(MouseEvent event) throws IOException {
        try {
            String nulll = null;
            String output = typeReclamation.getSelectionModel().getSelectedItem();
            String nomMagasin = magasinSelection.getSelectionModel().getSelectedItem();
            Reclamations reclamationClient = new Reclamations();
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
                rs.envoyerUneReclamation(reclamationClient, clientConnecte.getEmail(), nomMagasin);
                if(docPdf.isSelected()) {
                    genererReclamationPdf();
                    rs.envoyerMail(clientConnecte.getEmail(), "Recapitulatif réclamation");
                    notifEmail = "\nUn email contenant le récapitulatif de votre réclamation vous a été envoyé.";
                }
                dialog(Alert.AlertType.INFORMATION, "Info", "Votre réclamation a bien été envoyée !"+ notifEmail);
                openClientPage(event);
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
            document.addTitle("Réclamation de Mr. "+clientConnecte.getPrenom());
            document.open();
            Paragraph paragraph = new Paragraph("Recapitulatif de la réclamation du client: \t\t\t\t\t "+clientConnecte.getPrenom()+"\n\n\n");
            document.add(paragraph);
            paragraph.setExtraParagraphSpace(5);
            PdfPTable tableau = new PdfPTable(1);
            tableau.addCell(new Paragraph("Nom du client: \t\t"+clientConnecte.getPrenom()+"\n\n\n\n\n"));
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
}
