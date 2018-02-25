/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applicationpanier;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.stripe.model.Charge;
import com.stripe.model.Token;
import entites.Commande;
import entites.FonctionPanier;
import entites.LigneCommande;
import entites.Produit;
import entites.StripePayement;
import java.net.URL;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import services.CommandesServices;
import services.LigneCommandesServices;

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
    @FXML
    private AnchorPane rootPane;
    @FXML
    private Label labelPayment;

    /**
     * Initializes the controller class.
     */
    static NumberFormat format;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         format = NumberFormat.getCurrencyInstance(Locale.FRANCE);
        

        
         email.setText("jean.bikiembida@esprit.tn");
      //  email.setPromptText("jean.bikiembida@esprit.tn");
      
        //String mail = email.getText();
          email.setEditable(false);
          labelPayment.setText("Montant: "+format.format(FonctionPanier.MontantGlobal()));
    }    

    @FXML
    private void validerFunction(ActionEvent event) throws SQLException {
        
        
       // email.setText("jean.bikiembida@esprit.tn");
      //  email.setPromptText("jean.bikiembida@esprit.tn");
      
        //String mail = email.getText();
          //email.setEditable(false);
        Long numero= Long.parseLong(numeroCarte.getText());
        int ccv = Integer.parseInt(ccvTextField.getText());
        int mois = Integer.parseInt(MoisValidite.getText());
        int annee = Integer.parseInt(AnneeValidite.getText());
        
        
//        int codezip = Integer.parseInt(codeZip.getText());
        
        
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
        cmd.setIdUser(3);
        cmd.setIdTransaction(idTransaction);
        cmd.setEtat(0);
        cmd.setPrixTotal(FonctionPanier.MontantGlobal());
        cmdService.ajouterCommande(cmd);
        //cmdService.modifierEtat();

        /**
         * ****************************ligne**********
         */
        int idCmd = cmdService.rechercherParIdTransaction(idTransaction);
        System.out.println(idCmd);
        
         cmdService.modifierEtat(idCmd);

        for (Produit p : FonctionPanier.getListeProduit()) {
            int i = 0;
            System.out.println(i++);
            LigneCommande lcmd = new LigneCommande();
            lcmd.setIdProduit(p.getIdProduit());
            System.out.println(i++);
            lcmd.setQuantite(p.getQuantiteProduitClient());
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
}
