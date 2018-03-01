/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Panier;

import GUI.Panier.AlertMaker;
import GUI.Panier.FXMLInterfaceModificationQuantiteController;
import GUI.Utilisateur.ClientInterfaceController;
import GUI.Utilisateur.LoginController;
import GUI.Utilisateur.VendeurInterfaceController;
import Util.Util;
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
import entites.Panier.Commande;
import entites.Panier.FonctionPanier;
import entites.Panier.LigneCommande;

import entites.Produit.produits;
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
import javafx.animation.TranslateTransition;
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
import javafx.util.Duration;
import service.Panier.CommandesServices;
import service.Panier.LigneCommandesServices;
import service.Produits.ProduitServices;


/**
 * FXML Controller class
 *
 * @author jean
 */
public class FXMLPanierInterfaceController implements Initializable {

    @FXML
    private TableView<produits> listeProduit;
    @FXML
    private TableColumn<produits, String> imageColumn;
    @FXML
    private TableColumn<produits, String> libelleColumn;
    @FXML
    private TableColumn<produits, String> quantiteColumn;
    @FXML
    private TableColumn<produits, String> prixColumn;
    @FXML
    private TableColumn<produits, String> prixTotalColumn;
    @FXML
    private TableColumn<produits, String> suppressionColumn;

    /**
     * Initializes the controller class.
     */
    /**
     * Initializes the controller class.
     */
    private ObservableList<produits> data = FXCollections.observableArrayList();
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
    private Pane paneView;
    @FXML
    private Button btnGenererPdf;
    
    static NumberFormat format;
    
    
    public static Boolean UtilisateurConnectePourPayer=true;
    private ImageView profil;
    Image images;

    private Button Reclamation;
    
    @FXML
    private Button Maps1;
    @FXML
    private Button recherche1;
    
    @FXML
    private Button espClient11;
    @FXML
    private Button espVendeur11;
    @FXML
    private Button espAdmin11;
    @FXML
    private Button Log1;
    @FXML
    private Button Event1;
    @FXML
    private Button Contacts1;
    @FXML
    private Pane menu1;
    @FXML
    private Pane menu31;
    @FXML
    private Pane menu21;
    @FXML
    private Pane menu;

    
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        menu1.setTranslateX(-190);
                    TranslateTransition menuTranslation = new TranslateTransition(Duration.millis(500), menu1);

    menuTranslation.setFromX(-190);
    menuTranslation.setToX(0);

    menu1.setOnMouseEntered(evt -> {
        menuTranslation.setRate(1);
        menuTranslation.play();
    });
    menu1.setOnMouseExited(evt -> {
        menuTranslation.setRate(-1);
        menuTranslation.play();
    });
     menu21.setVisible(false);
     menu31.setVisible(false);

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
    menu21.setVisible(false);
    menu31.setVisible(false);
        loadData();
        setColumn();
        actualiserPrix();
        btnGenererPdf.setOnMouseClicked((MouseEvent e) ->{
            
           GenererPdf();
        });

    }

    @FXML
    private void pageProduitFonction(ActionEvent event) throws IOException {
        Parent homePageParent = FXMLLoader.load(getClass().getResource("/GUI/Produits/AfficheListProduits.fxml"));
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

        produits selectPourSuppression = listeProduit.getSelectionModel().getSelectedItem();
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

        for (produits p : FonctionPanier.getListeProduit()) {

            Button btSupp = new Button("Supprimer");
            //TextField quantiteTextField = new TextField();
            

            i++;

            /*  image = new ImageView("/Images/Image/"+p.getIdProduit()+".jpg");
            image.setFitHeight(90.0);
            image.setFitWidth(75.0);*/
            //quantiteTextField.setText("" + p.getQuantiteProduit());
            
            data.add(new produits(p.getIdProduit(), p.getNomProduit(), p.getQuantiteProduitClient(), p.getPrixProduit(), p.getImageProduit(), btSupp, p.getPrixTotal()));
            
            System.out.println(p);
                System.out.println(p.getQuantiteProduit());

            btSupp.setOnAction(event -> {
                for (produits verifier : listeProduit.getItems()) {
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
        
        produits selectPourModification = listeProduit.getSelectionModel().getSelectedItem();
        System.out.println(selectPourModification);
        System.out.println(selectPourModification.getQuantiteProduit());
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
            
            System.out.println(selectPourModification+"++++++++++++++++++++++++++++++++++++++++++++++++");
            //////recupere l'id produit et la qte totale
            System.out.println("----------------------------------------------**********"+selectPourModification.getQuantiteProduit());
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
       
        if(Util.connectedUser !=null){
             LoadWindowParent("FXMLPaymentCard.fxml",event);
        }else{
            
            UtilisateurConnectePourPayer = false;
            LoadWindowParent("/GUI/Utilisateur/Login.fxml", event);
        }
       

    }

  

    private void statitisques(ActionEvent event) {

        LoadWindowParent("FXMLStatistiques.fxml",event);
    }

    private void ConsulterCommandes(ActionEvent event) {
        LoadWindowParent("FXMLCommandesInterface.fxml",event);
    }

    @FXML
    private void GenererPdfMethode(ActionEvent event) {
    }
    private void GenererPdf() {
         Document document =new Document();
        try{
           
           PdfWriter writer= PdfWriter.getInstance(document, new FileOutputStream("src\\pdf\\Facture.pdf"));
            document.open();
             Image image = Image.getInstance("src\\Images\\LOGO_SOUK_Noir2.png");
            
           
            
            document.add(image);
            document.add(new Paragraph("Facture Client "));
            
            PdfPTable table = new PdfPTable(4);
            table.setWidthPercentage(105);
            table.setSpacingBefore(11f);
            table.setSpacingAfter(11f);
            
            float[] colWidth={2f,2f,2f,2f,2f};
            table.setWidths(colWidth);
         
             PdfPCell c2=new PdfPCell(new Paragraph("Libelle"));
              PdfPCell c3=new PdfPCell(new Paragraph("Quantite"));
              PdfPCell c4=new PdfPCell(new Paragraph("prix Unitaire"));
              PdfPCell c5=new PdfPCell(new Paragraph("Prix Total"));
              
              
              table.addCell(c2);
              table.addCell(c3);
              table.addCell(c4);
              table.addCell(c5);
              
              
              for(produits p: FonctionPanier.getListeProduit()){
                  table.addCell(new Paragraph(""));
              table.addCell(new Paragraph(""+p.getNomProduit()));
              table.addCell(new Paragraph(""+p.getQuantiteProduitClient()));
              table.addCell(new Paragraph(""+p.getPrixProduit()));
              table.addCell(new Paragraph(""+p.getPrixTotal()));
              
              }
           
              
              document.add(table);
              

              
             document.add(new Paragraph("\t\t\t\t\t Prix Total: "+FonctionPanier.MontantGlobal()));
              
              
              
            document.close();
            writer.close();
            
            AlertMaker.showSimpleAlert("Success", "Generation du panier.pdf \n Location: G:\\3ieme_annee\\PIDEV\\Atelier\\ApplicationPanier ");
            
        }catch(Exception e){
            System.out.println(e);
        }
        System.out.println("itext PDF program executed");
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
    private void afficherSuite(MouseEvent event) {
        menu1.setVisible(true);
        menu21.setVisible(true);
 
    }

    @FXML
    private void exite(MouseEvent event) {
         menu21.setOnMouseEntered(evt -> {menu21.setVisible(true);});
         menu21.setOnMouseExited(evt -> {menu21.setVisible(false);});
       
        menu21.setVisible(false);
    }


    @FXML
    private void exitemenu3(MouseEvent event) {
        menu31.setOnMouseEntered(evt -> {menu31.setVisible(true);});
         menu31.setOnMouseExited(evt -> {menu31.setVisible(false);});
       
        menu31.setVisible(false);
    }

    @FXML
    private void afficherSuitemenu3(MouseEvent event) {
         menu1.setVisible(true);
        menu31.setVisible(true);
 
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
        
       // LoadWindow("/GUI/Panier/FXMLPanierInterface.fxml", "Panier");
    }

    private void LoadWindow(String loc){
         try {
            Parent homePageParent = FXMLLoader.load(getClass().getResource(loc));
            Stage stage = new Stage(StageStyle.DECORATED);
            //stage.setTitle(name);
            stage.setScene(new Scene(homePageParent));
            stage.show();

        } catch (IOException ex) {
            Logger.getLogger(FXMLPanierInterfaceController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ClientInterfaceController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    @FXML
    private void btnEventAction(ActionEvent event) {
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
