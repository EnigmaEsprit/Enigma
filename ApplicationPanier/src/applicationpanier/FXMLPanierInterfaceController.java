/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applicationpanier;

import applicationpanier.AlertMaker;
import applicationpanier.FXMLInterfaceModificationQuantiteController;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.jfoenix.controls.JFXAlert;
import entites.Commande;
import entites.FonctionPanier;
import entites.LigneCommande;
import entites.Produit;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import services.CommandesServices;
import services.LigneCommandesServices;
import services.ProduitServices;

/**
 * FXML Controller class
 *
 * @author jean
 */
public class FXMLPanierInterfaceController implements Initializable {

    @FXML
    private TableView<Produit> listeProduit;
    @FXML
    private TableColumn<Produit, String> imageColumn;
    @FXML
    private TableColumn<Produit, String> libelleColumn;
    @FXML
    private TableColumn<Produit, String> quantiteColumn;
    @FXML
    private TableColumn<Produit, String> prixColumn;
    @FXML
    private TableColumn<Produit, String> prixTotalColumn;
    @FXML
    private TableColumn<Produit, String> suppressionColumn;

    /**
     * Initializes the controller class.
     */
    /**
     * Initializes the controller class.
     */
    private ObservableList<Produit> data = FXCollections.observableArrayList();
    ProduitServices ps = new ProduitServices();
    @FXML
    private Label prixTotal;
    @FXML
    private Label nombreProduitsDansPanier;
    @FXML
    private Button btViderPanier;
    @FXML
    private Button btPageProduit;
    @FXML
    private Button btnPayer;
    @FXML
    private TableColumn<?, ?> qtechange;
    @FXML
    private ImageView imagePanier;
    @FXML
    private AnchorPane AnchorPaneView3;
    @FXML
    private Pane paneView;
    @FXML
    private Button btnGenererPdf;
    
    static NumberFormat format;
    
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       
        loadData();
        setColumn();
        actualiserPrix();
        btnGenererPdf.setOnMouseClicked((MouseEvent e) ->{
            
           GenererPdf();
        });

    }

    @FXML
    private void pageProduitFonction(ActionEvent event) throws IOException {
        Parent homePageParent = FXMLLoader.load(getClass().getResource("FXMLInterface2.fxml"));
        Scene homePageScene = new Scene(homePageParent);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(homePageScene);
        appStage.show();
    }

    private void setColumn() {

        libelleColumn.setCellValueFactory(new PropertyValueFactory<>("nomProduit"));
        quantiteColumn.setCellValueFactory(new PropertyValueFactory<>("quantiteProduit"));
        prixColumn.setCellValueFactory(new PropertyValueFactory<>("prixProduit"));
        imageColumn.setCellValueFactory(new PropertyValueFactory<>("imageProduit"));
        suppressionColumn.setCellValueFactory(new PropertyValueFactory<>("bouton"));
        prixTotalColumn.setCellValueFactory(new PropertyValueFactory<>("prixTotal"));

    }

    public void actualiserPrix() {
        format = NumberFormat.getCurrencyInstance(Locale.FRANCE);
        

        prixTotal.setText("Prix Total: " + format.format(FonctionPanier.MontantGlobal()));

        nombreProduitsDansPanier.setText("= " + FonctionPanier.compterArticles());
    }

    private void actualiserPage() {

    }

    @FXML
    private void optionSuppression(ActionEvent event) {

        Produit selectPourSuppression = listeProduit.getSelectionModel().getSelectedItem();
        if (selectPourSuppression == null) {
            AlertMaker.showErrorMessage("Aucun produit selectionne", "s'il vous plait selectionner un produit pour la suppression");
            System.out.println("erorrrrrrrrrrrrrrrrrr");
            return;

        }

        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Suppression Produit");
        alert.setContentText("Êtes vous sure de vouloir supprimer le produit " + selectPourSuppression.getNomProduit());
        Optional<ButtonType> reponse = alert.showAndWait();

        if (reponse.get() == ButtonType.OK) {
            Boolean result = FonctionPanier.supprimerArticle(selectPourSuppression);
            if (result) {
                AlertMaker.showSimpleAlert("Article Supprime", selectPourSuppression.getNomProduit() + " a ete supprime");
                data.remove(selectPourSuppression);
                actualiserPrix();
            } else {
                AlertMaker.showSimpleAlert(" Echec", selectPourSuppression.getNomProduit() + " ne peut pas être supprime");
            }
        } else {

            AlertMaker.showSimpleAlert(" Suppression annule", "Annulation processus de Suppression");
        }

    }

    public void loadData() {

        data.clear();
        int i = 1;

        ImageView image;

        System.out.println(FonctionPanier.getListeProduit());

        for (Produit p : FonctionPanier.getListeProduit()) {

            Button btSupp = new Button("Supprimer");
            //TextField quantiteTextField = new TextField();
            

            i++;

            image = new ImageView("Image/" + p.getIdProduit() + ".jpg");
            image.setFitHeight(90.0);
            image.setFitWidth(75.0);
            //quantiteTextField.setText("" + p.getQuantiteProduit());
            data.add(new Produit(p.getIdProduit(), p.getNomProduit(), p.getQuantiteProduitClient(), p.getPrixProduit(), image, btSupp, p.getPrixTotal()));
            System.out.println(p);

            btSupp.setOnAction(event -> {
                for (Produit verifier : listeProduit.getItems()) {
                    if (verifier.getBouton() == btSupp) {

                       
                         Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Suppression Produit");
        alert.setContentText("Êtes vous sure de vouloir supprimer le produit " + p.getNomProduit());
        Optional<ButtonType> reponse = alert.showAndWait();

        if (reponse.get() == ButtonType.OK) {
          
            if (   FonctionPanier.supprimerArticle(p)) {
                AlertMaker.showSimpleAlert("Article Supprime", p.getNomProduit() + " a ete supprime");
                data.remove(p);
                 FonctionPanier.supprimerArticle(p);
                 listeProduit.getSelectionModel().clearSelection();
                        listeProduit.getItems().remove(verifier);

                        actualiserPrix();
            } else {
                AlertMaker.showSimpleAlert(" Echec", p.getNomProduit() + " ne peut pas être supprime");
            }
        } else {

            AlertMaker.showSimpleAlert(" Suppression annule", "Annulation processus de Suppression");
        }

                        break;
                    }
                }

                System.out.println(FonctionPanier.getListeProduit());
            });

            //FonctionPanier.modifierQTeArticle(p, Integer.parseInt(quantiteTextField.getText()));
        }
        listeProduit.setItems(data);
    }

    @FXML
    private void optionModifierQuantiteProduit(ActionEvent event) {
        Produit selectPourModification = listeProduit.getSelectionModel().getSelectedItem();
        if (selectPourModification == null) {
            AlertMaker.showErrorMessage("Aucun produit selectionne", "s'il vous plait selectionner un produit pour la suppression");
            return;

        }

        //  LoadWindow("FXMLInterfaceModificationQuantite.fxml", "Modification Quantite");
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLInterfaceModificationQuantite.fxml"));

            Parent homePageParent = loader.load();
            FXMLInterfaceModificationQuantiteController controller = (FXMLInterfaceModificationQuantiteController) loader.getController();

            controller.inflateUI(selectPourModification);
            //////recupere l'id produit et la qte totale
            System.out.println(selectPourModification.getQuantiteProduit());
            System.out.println("ooooooooooooooooooooooooooooooo");
            FXMLInterfaceModificationQuantiteController.idProduitstatic = selectPourModification.getIdProduit();
           // FXMLInterfaceModificationQuantiteController.quantiteProduitTotal= selectPourModification.getQuantiteProduit();

            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle("Modification Quantite");
            stage.setScene(new Scene(homePageParent));
            stage.show();

        } catch (IOException ex) {
            System.out.println("");
            System.out.println("aaaaaaaaaaaaa");
            Logger.getLogger(FXMLPanierInterfaceController.class.getName()).log(Level.SEVERE, null, ex);
        }
        /* stage.setOnCloseRequest((e)->{
           actualiserPage( new ActionEvent());
        });*/
    }

    @FXML
    private void viderPanier(ActionEvent event) {
        FonctionPanier.supprimePanier();
        optionActualiser(new ActionEvent());
        actualiserPrix();

    }

    @FXML
    private void optionActualiser(ActionEvent event) {

        // FonctionPanier.modifierQTeArticle(p, Integer.parseInt(quantiteTextField.getText()));
        actualiserPrix();
        loadData();
    }

    private void LoadWindow(String loc, String title) {
        try {
            Parent homePageParent = FXMLLoader.load(getClass().getResource(loc));
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle(title);
            stage.setScene(new Scene(homePageParent));
            stage.show();

        } catch (IOException ex) {
            Logger.getLogger(FXMLPanierInterfaceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void ValiderPaiement(ActionEvent event) {
       
        LoadWindow("FXMLPaymentCard.fxml", "Paiement Par Carte");

    }

  

    @FXML
    private void statitisques(ActionEvent event) {

        LoadWindow("FXMLStatistiques.fxml", "Statistiques");
    }

    @FXML
    private void ConsulterCommandes(ActionEvent event) {
        LoadWindow("FXMLCommandesInterface.fxml", "Liste des Commandes");
    }

    @FXML
    private void GenererPdfMethode(ActionEvent event) {
    }
    private void GenererPdf() {
         Document document =new Document();
        try{
           
           PdfWriter writer= PdfWriter.getInstance(document, new FileOutputStream("test.pdf"));
            document.open();
             Image image = Image.getInstance("MILK_Logo_WEB_MEDIUM-01_495x.png");
            document.add(new Paragraph("Exemple"));
           
            
            document.add(image);
            document.add(new Paragraph("Exemple"));
            
            PdfPTable table = new PdfPTable(5);
            table.setWidthPercentage(105);
            table.setSpacingBefore(11f);
            table.setSpacingAfter(11f);
            
            float[] colWidth={2f,2f,2f,2f,2f};
            table.setWidths(colWidth);
            PdfPCell c1=new PdfPCell(new Paragraph(""));
             PdfPCell c2=new PdfPCell(new Paragraph("Libelle"));
              PdfPCell c3=new PdfPCell(new Paragraph("Quantite"));
              PdfPCell c4=new PdfPCell(new Paragraph("prix Unitaire"));
              PdfPCell c5=new PdfPCell(new Paragraph("Prix Total"));
              
              c1.setHorizontalAlignment(Element.ALIGN_CENTER);
     
              c1.setBackgroundColor(BaseColor.DARK_GRAY);
              
              table.addCell(c1);
              table.addCell(c2);
              table.addCell(c3);
              table.addCell(c4);
              table.addCell(c5);
              
              
              for(Produit p: FonctionPanier.getListeProduit()){
                  table.addCell(new Paragraph(""));
              table.addCell(new Paragraph(""+p.getNomProduit()));
              table.addCell(new Paragraph(""+p.getQuantiteProduitClient()));
              table.addCell(new Paragraph(""+p.getPrixProduit()));
              table.addCell(new Paragraph(""+p.getPrixTotal()));
              
              }
           
              
              document.add(table);
              
              List orderList = new List(List.ORDERED);
              orderList.add(new ListItem("Fun"));
              orderList.add(new ListItem("Technology"));
              orderList.add(new ListItem("Tic"));
              orderList.add(new ListItem("Java"));
              orderList.add(new ListItem("mation"));
              
              document.add(orderList);
              
              
              
            document.close();
            writer.close();
            
            AlertMaker.showSimpleAlert("Success", "Generation du panier.pdf \n Location:G:\\3ieme_annee\\PIDEV\\Atelier\\ApplicationPanier");
            
        }catch(Exception e){
            System.out.println(e);
        }
        System.out.println("itext PDF program executed");
    }

}
