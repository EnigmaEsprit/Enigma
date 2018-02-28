package GUI.Produits;

import entites.Utilisateur.Utilisateur;
import entites.Magasins.magasins;
import entites.Produit.produits;

import service.Magasins.magasinsServices;
import service.Produits.produitServices;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import service.Utilisateur.VendeurService;

public class DetailProduitController  implements Initializable {
  @FXML
    private AnchorPane AP1;

    produits produit;
    String pic;
      private Image i;
      magasins m;
    @FXML
    private ImageView imageproduit;
    @FXML
    private Label nomproduit;
    @FXML
    private Label reference;
    @FXML
    private Label prix;
    @FXML
    private Label quantite;
    @FXML
    private Label nommagasin;
    @FXML
    private Label categorie;
    @FXML
    private Label nomvendeur;
    int idProduit;
    magasins nommagasinn;
    Utilisateur user;
    
    public DetailProduitController()
    {
        idProduit= AfficheListProduitsController.recupid;
          produitServices ps= new produitServices();
         magasinsServices ms= new magasinsServices();
      produit= ps.rechercherProduitsById(idProduit);
      nommagasinn=ms.rechercherMagasinsById(produit.getIdMagasin());
   VendeurService us = new VendeurService();
                   user=us.rechercherVendeurById(m.getIdUser());
    }
 @Override
    public void initialize(URL location, ResourceBundle resources) {
       
       
       nomproduit.setText(produit.getNomProduit());
       reference.setText(produit.getReferenceProduit());
       prix.setText(produit.getPrixProduit());
       quantite.setText(produit.getQuantiteProduit());
       nommagasin.setText(nommagasinn.getNomMagasin());
       categorie.setText(produit.getCategorieMagasin());
       nomvendeur.setText(user.getNom()+" "+user.getPrenom());

       pic= produit.getPhotoProduit();
     
          i= new Image("http://localhost/uimg/"+pic);      
          imageproduit.setImage(i);
      
    }
    
   

   

}
