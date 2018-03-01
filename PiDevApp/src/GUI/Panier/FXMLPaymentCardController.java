/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Panier;

import GUI.Panier.AlertMaker;
import GUI.Utilisateur.ClientInterfaceController;
import GUI.Utilisateur.LoginController;
import GUI.Utilisateur.VendeurInterfaceController;
import Util.Util;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.stripe.model.Charge;
import com.stripe.model.Token;
import entites.Panier.Commande;
import entites.Panier.FonctionPanier;
import entites.Panier.LigneCommande;

import entites.Panier.StripePayement;
import entites.Produit.produits;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.Locale;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
public class FXMLPaymentCardController implements Initializable {

    @FXML
    private JFXTextField email;
    @FXML
    private JFXTextField numeroCarte;
    @FXML
    private JFXPasswordField ccvTextField;
    @FXML
    private JFXTextField codeZip;
    @FXML
    private JFXButton btnValider;
    @FXML
    private JFXButton btnAnnuler;
    @FXML
    private JFXTextField MoisValidite;
    @FXML
    private JFXTextField AnneeValidite;
    private AnchorPane rootPane;
    @FXML
    private Label labelPayment;

    /**
     * Initializes the controller class.
     */
    static NumberFormat format;
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
    private Button Contacts;
    @FXML
    private Button Reclamation;
    @FXML
    private ImageView profil;
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
         if(Util.connectedUser.getImg()==null)
                {
                    
                profil.setImage(new javafx.scene.image.Image("http://localhost/uimg/user.jpg"));
                }
                else
                {
                    profil.setImage(new javafx.scene.image.Image("http://localhost/uimg/"+Util.connectedUser.getImg()));
               
             
                }
    menu2.setVisible(false);
    menu3.setVisible(false);
         format = NumberFormat.getCurrencyInstance(Locale.FRANCE);
        

        
         //email.setText("jean.bikiembida@esprit.tn");
         
         if(Util.connectedUser!= null){
            email.setText(Util.connectedUser.getEmail()); 
            numeroCarte.setText(Util.connectedUser.getNbc());
            ccvTextField.setText(Util.connectedUser.getCode_s());
            
            
            email.setEditable(false);
          labelPayment.setText("Montant: "+format.format(FonctionPanier.MontantGlobal()));
         }
         else{
            AlertMaker.showErrorMessage("Erreur de Connexion","Vous n'êtes pas conneté");
         }
       
         
      //  email.setPromptText("jean.bikiembida@esprit.tn");
      
        //String mail = email.getText();
          
    }    

    @FXML
    private void validerFunction(ActionEvent event) throws SQLException {
        
        
       // email.setText("jean.bikiembida@esprit.tn");
      //  email.setPromptText("jean.bikiembida@esprit.tn");
      
        //String mail = email.getText();
          //email.setEditable(false);
//        Long numero= Long.parseLong(numeroCarte.getText());
//        int ccv = Integer.parseInt(ccvTextField.getText());
        int mois = Integer.parseInt(MoisValidite.getText());
        int annee = Integer.parseInt(AnneeValidite.getText());
        
        
//        int codezip = Integer.parseInt(codeZip.getText());
        
        if(Util.connectedUser !=null){
           
        
        
        Token token = StripePayement.getToken("pk_test_Dzbm4KQQrkWxbmai0DEiNKC6", numeroCarte.getText(), mois, annee, ccvTextField.getText(), email.getText());
        if(FonctionPanier.MontantGlobal()!= 0){
             if(token !=null){
             Charge ch= StripePayement.ChargePayement("rk_test_oGfrFNOjpnRPklUVzjelPHgf", "usd", "tok_visa", FonctionPanier.MontantGlobal(),"sk_test_kXdQWE7YYBfcaZK9OoH63KaR", numeroCarte.getText(), mois, annee, ccvTextField.getText(), email.getText());
                EnregisterPaiement();
                
                AlertMaker.showSimpleAlert("Paiement Succes", "Paiement Effectué avec succès , veuillez télécharger votre facture");
               Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.close();
        }else{
            AlertMaker.showErrorMessage("Erreur de Paiement", "Numéro de Carte Invalide");
        }
        }
        else{
            AlertMaker.showErrorMessage("Panier Vide", "Desolez vous ne pouvez pas effectuer de paiement car votre panier est vide , veuillez remplir votre panier");
        }
       
       } else{
            AlertMaker.showErrorMessage("Erreur de Connexion","Vous n'êtes pas conneté");
        }
        
        
        
        
    }

    @FXML
    private void AnnulerFunction(ActionEvent event) {
        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.close();
        
    }
    
    private void EnregisterPaiement() throws SQLException{
         CommandesServices cmdService = new CommandesServices();
        System.out.println("******************");
        int idTransaction = Integer.parseInt(generate(5));
        System.out.println("*****************");
        Commande cmd = new Commande();
        
        System.out.println("id user: "+Util.connectedUser);
        cmd.setIdUser(Util.connectedUser.getId());
        cmd.setIdTransaction(idTransaction);
        cmd.setEtat(0);
        cmd.setPrixTotal(FonctionPanier.MontantGlobal());
        cmdService.ajouterCommande(cmd);
        

        /*
         * *******************ligne*******************
         */
       int idCmd = cmdService.rechercherParIdTransaction(idTransaction);
        System.out.println(idCmd);
        
         cmdService.modifierEtat(idCmd);

       ProduitServices ps = new ProduitServices();

        for (produits p : FonctionPanier.getListeProduit()) {
            int i = 0;
            System.out.println(i++);
            LigneCommande lcmd = new LigneCommande();
            lcmd.setIdProduit(p.getIdProduit());
            System.out.println(i++);
            lcmd.setQuantite(p.getQuantiteProduitClient());
            System.out.println(i++);

            
            
            
            ps.modifierProduit2(p);
            
            
            lcmd.setPrixUnitaire(p.getPrixProduit());
            lcmd.setPrixTotal(p.prixTotal());
            System.out.println(i++);
            System.out.println();
            lcmd.setIdMagasin(p.getIdMagasin());

            lcmd.setIdCommande(idCmd);
            System.out.println(idCmd);
            System.out.println(i++);

            LigneCommandesServices lcmdService = new LigneCommandesServices();
            lcmdService.ajouterProduit(lcmd);
            System.out.println(i++);
        }
    }
      public String generate(int length) {
        String chars = "1234567890"; // Tu supprimes les lettres dont tu ne veux pas
        String pass = "";
        for (int x = 0; x < length; x++) {
            int i = (int) Math.floor(Math.random() * 10); // Si tu supprimes des lettres tu diminues ce nb
            pass += chars.charAt(i);
        }
        System.out.println(pass);
        return pass;
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

    private void LoadWindow(String loc){
         try {
            Parent homePageParent = FXMLLoader.load(getClass().getResource(loc));
            Stage stage = new Stage(StageStyle.DECORATED);
            //stage.setTitle(name);
            stage.setScene(new Scene(homePageParent));
            stage.setResizable(false);
            stage.show();

        } catch (IOException ex) {
            Logger.getLogger(FXMLPaymentCardController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(FXMLPaymentCardController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    @FXML
    private void btnEventAction(ActionEvent event) {
    }
     @FXML
    private void btnReclamation(ActionEvent event) {
         LoadWindowParent("/GUI/Reclamation/EnvoiDesReclamations", event);
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
