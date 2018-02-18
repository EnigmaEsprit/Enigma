/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applicationpanier;

import applicationpanier.AlertMaker;
import applicationpanier.FXMLInterfaceModificationQuantiteController;
import com.jfoenix.controls.JFXAlert;
import entites.Commande;
import entites.FonctionPanier;
import entites.LigneCommande;
import entites.Produit;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
    private Button btViderPanier;
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
    private TableColumn<?, ?> qtechange;
    @FXML
    private Button btPageProduit;
    @FXML
    private Label prixTotal;
    @FXML
    private ImageView imagePanier;
    @FXML
    private Label nombreProduitsDansPanier;
    @FXML
    private Button btnPayer;
    
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       
        loadData();
        setColumn();
        actualiserPrix();

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

    }

    private void actualiserPrix() {

        prixTotal.setText("Prix Total: " + FonctionPanier.MontantGlobal());

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
                AlertMaker.showSimpleAlert("Article Supprime", selectPourSuppression + " a ete supprime");
                data.remove(selectPourSuppression);
                actualiserPrix();
            } else {
                AlertMaker.showSimpleAlert(" Echec", selectPourSuppression + " ne peut pas être supprime");
            }
        } else {

            AlertMaker.showSimpleAlert(" Suppression annule", "Annulation processus de Suppression");
        }

    }

    private void loadData(){
        
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
            data.add(new Produit(p.getIdProduit(),p.getNomProduit(),p.getQuantiteProduit(), p.getPrixProduit(), image, btSupp));
            System.out.println(p);

            btSupp.setOnAction(event -> {
                for (Produit verifier : listeProduit.getItems()) {
                    if (verifier.getBouton() == btSupp) {

                        FonctionPanier.supprimerArticle(p);
                        listeProduit.getSelectionModel().clearSelection();
                        listeProduit.getItems().remove(verifier);

                        actualiserPrix();

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
         try{
             FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLInterfaceModificationQuantite.fxml"));
             
            Parent homePageParent = loader.load();
            FXMLInterfaceModificationQuantiteController  controller = (FXMLInterfaceModificationQuantiteController)loader.getController();
             
            controller.inflateUI(selectPourModification);
            //////recupere l'id produit
            FXMLInterfaceModificationQuantiteController.idProduitstatic=selectPourModification.getIdProduit();
           
            
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle("Modification Quantite");
            stage.setScene(new Scene(homePageParent));
            stage.show();
      
        }catch(IOException ex){
             System.out.println("");
             System.out.println("aaaaaaaaaaaaa");
            Logger.getLogger(FXMLPanierInterfaceController.class.getName()).log(Level.SEVERE,null,ex);
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
    private void LoadWindow(String loc,String title){
        try{
            Parent homePageParent = FXMLLoader.load(getClass().getResource(loc));
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle(title);
            stage.setScene(new Scene(homePageParent));
            stage.show();
      
        }catch(IOException ex){
            Logger.getLogger(FXMLPanierInterfaceController.class.getName()).log(Level.SEVERE,null,ex);
        }
    }

    @FXML
    private void ValiderPaiement(ActionEvent event)  {
        /*
        $Cmd->AddCmdInBd($idTrans);
			$idCmd = Commande::findByIdCmdWithIdTrans($idTrans);
			$Cmd->updateEtat($idCmd);
			
			echo "<br><br> id transaction: ";
			var_dump($idCmd);

			echo "<br><br>";
			echo "<br><br> id commande: ";
			//var_dump($Lcmd->getIdCmd());

			echo "<br><br>";

			$ligneCmd->AddLigneCmdInBd($idCmd);*/
         CommandesServices cmdService = new CommandesServices();
         System.out.println("******************");
         int idTransaction = Integer.parseInt(generate(5));
         System.out.println("*****************");
         Commande cmd = new Commande();
         cmd.setIdUser(3);
         cmd.setIdTransaction(idTransaction);
         cmd.setEtat(0);
         cmd.setPrixTotal(FonctionPanier.MontantGlobal());
         cmdService.ajouterCommande(cmd);
         //cmdService.modifierEtat();
         
         /******************************ligne***********/
         
         int idCmd =cmdService.rechercherParIdTransaction(idTransaction);
         System.out.println(idCmd);
       //  cmdService.modifierEtat(idCmd);
         
         for(Produit p: FonctionPanier.getListeProduit()){
             int i=0;
             System.out.println(i++);
             LigneCommande lcmd = new LigneCommande();
             lcmd.setIdProduit(p.getIdProduit());
              System.out.println(i++);
             lcmd.setQuantite(p.getQuantiteProduit());
              System.out.println(i++);
             
             lcmd.setPrixUnitaire(p.getPrixProduit());
                     lcmd.setPrixTotal(p.prixTotal());
              System.out.println(i++);
           
            
            lcmd.setIdCommande(idCmd);
             System.out.println(idCmd);
             System.out.println(i++);
            
             LigneCommandesServices lcmdService = new LigneCommandesServices();
            lcmdService.ajouterProduit(lcmd);
              System.out.println(i++);
         }
         
         
    }
    
   
    
      public String generate(int length)
{
	    String chars = "1234567890"; // Tu supprimes les lettres dont tu ne veux pas
	    String pass = "";
	    for(int x=0;x<length;x++)
	    {
	       int i = (int)Math.floor(Math.random() * 10); // Si tu supprimes des lettres tu diminues ce nb
	       pass += chars.charAt(i);
	    }
	   System.out.println(pass);
	    return pass;
}

    @FXML
    private void statitisques(ActionEvent event) {
        
        LoadWindow("FXMLStatistiques.fxml", "Statistiques");
    }
   
}
