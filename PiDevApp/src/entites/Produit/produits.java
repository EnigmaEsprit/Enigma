/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites.Produit;

import java.util.Objects;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;




public class produits {
 private int idProduit;
     

 private String referenceProduit;
 private String nomProduit;
 private Double prixProduit;
 private String photoProduit;
 private int quantiteProduit;
 private int active;
 private int idpromotion;
 private String categorieMagasin;
 private int idMagasin;
 private double valeval;
  private Button bouton;
 
  private double prixTotal;
   private ImageView imageProduit;
 
 private int quantiteProduitClient;

    public produits() {
    }

    public produits(String nomProduit, Double prixProduit, int quantiteProduit,String referenceProduit) {
        this.referenceProduit = referenceProduit;
        this.nomProduit = nomProduit;
        this.prixProduit = prixProduit;
        this.quantiteProduit = quantiteProduit;
    }

     public produits(int idProduit, String nomProduit, int quantiteProduit, double prixProduit, ImageView imageProduit, Button bouton) {
        this.idProduit = idProduit;
        this.nomProduit = nomProduit;
        this.quantiteProduit = quantiteProduit;
        this.prixProduit = prixProduit;
        this.imageProduit = imageProduit;
        this.bouton = bouton;
    }
    public  produits(produits p){
        
        this.idProduit = p.getIdProduit();
        this.referenceProduit = p.getReferenceProduit();
        this.nomProduit = p.getNomProduit();
        this.prixProduit = p.getPrixProduit();
        this.photoProduit = p.getPhotoProduit();
        this.quantiteProduit = p.getQuantiteProduit();
        this.active = active;
        this.idpromotion = p.getIdpromotion();
        this.categorieMagasin = p.getCategorieMagasin();
        this.idMagasin = p.getIdMagasin();
        this.valeval = p.getValeval();  
        this.bouton = p.getBouton();
    }
    
    
    
    public produits(int idProduit, String referenceProduit, String nomProduit, Double prixProduit, String photoProduit, int quantiteProduit, int active, int idpromotion, String categorieMagasin, int idMagasin, double valeval) {
        this.idProduit = idProduit;
        this.referenceProduit = referenceProduit;
        this.nomProduit = nomProduit;
        this.prixProduit = prixProduit;
        this.photoProduit = photoProduit;
        this.quantiteProduit = quantiteProduit;
        this.active = active;
        this.idpromotion = idpromotion;
        this.categorieMagasin = categorieMagasin;
        this.idMagasin = idMagasin;
        this.valeval = valeval;
    }

    public produits(int idProduit, String referenceProduit, String nomProduit, Double prixProduit, String photoProduit, int quantiteProduit, int active, int idpromotion, String categorieMagasin, int idMagasin) {
        this.idProduit = idProduit;
        this.referenceProduit = referenceProduit;
        this.nomProduit = nomProduit;
        this.prixProduit = prixProduit;
        this.photoProduit = photoProduit;
        this.quantiteProduit = quantiteProduit;
        this.active = active;
        this.idpromotion = idpromotion;
        this.categorieMagasin = categorieMagasin;
        this.idMagasin = idMagasin;
    }

    public produits(String referenceProduit, String nomProduit, Double prixProduit, String photoProduit, int quantiteProduit, int active, String categorieMagasin, int idMagasin) {
        this.referenceProduit = referenceProduit;
        this.nomProduit = nomProduit;
        this.prixProduit = prixProduit;
        this.photoProduit = photoProduit;
        this.quantiteProduit = quantiteProduit;
        this.active = active;
        
        this.categorieMagasin = categorieMagasin;
        this.idMagasin = idMagasin;
    }
    
 public produits(int idProduit, String nomProduit, int quantiteProduit, double prixProduit, ImageView imageProduit, Button bouton, double prixTotal) {
        this.idProduit = idProduit;
        this.nomProduit = nomProduit;
        this.quantiteProduit = quantiteProduit;
        this.prixProduit = prixProduit;
        this.imageProduit = imageProduit;
        this.bouton = bouton;
        this.prixTotal = prixTotal;
    }
    public produits(int idProduit, String referenceProduit, String nomProduit, Double prixProduit, String photoProduit, int quantiteProduit, int idpromotion, int idMagasin) {
        this.idProduit = idProduit;
        this.referenceProduit = referenceProduit;
        this.nomProduit = nomProduit;
        this.prixProduit = prixProduit;
        this.photoProduit = photoProduit;
        this.quantiteProduit = quantiteProduit;
        this.idpromotion = idpromotion;
        this.idMagasin = idMagasin;
    }

    public produits(int idProduit, String referenceProduit, String nomProduit, Double prixProduit, String photoProduit, int quantiteProduit, String categorieMagasin, int idMagasin) {
        this.idProduit = idProduit;
        this.referenceProduit = referenceProduit;
        this.nomProduit = nomProduit;
        this.prixProduit = prixProduit;
        this.photoProduit = photoProduit;
        this.quantiteProduit = quantiteProduit;
        this.categorieMagasin = categorieMagasin;
        this.idMagasin = idMagasin;
    }

   
    public ImageView getImageProduit() {
        return imageProduit;
    }

    public void setImageProduit(ImageView imageProduit) {
        this.imageProduit = imageProduit;
    }

  

    public produits(int idProduit, String nomProduit, int quantiteProduit, Double prixProduit) {
        this.idProduit = idProduit;
        this.nomProduit = nomProduit;
        this.prixProduit = prixProduit;
        this.quantiteProduit = quantiteProduit;
    }

    public void setPrixTotal(double prixTotal) {
        this.prixTotal = prixTotal;
    }
    
     public double getPrixTotal() {
       return getPrixProduit()*getQuantiteProduit();
    }
    
  public double prixTotal(){
        return getPrixProduit()*getQuantiteProduit();
    }
    
    public Button getBouton() {
        return bouton;
    }

    public void setBouton(Button bouton) {
        this.bouton = bouton;
    }

    
    public int getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(int idProduit) {
        this.idProduit = idProduit;
    }

    public String getReferenceProduit() {
        return referenceProduit;
    }

    public void setReferenceProduit(String referenceProduit) {
        this.referenceProduit = referenceProduit;
    }

    public String getNomProduit() {
        return nomProduit;
    }

    public void setNomProduit(String nomProduit) {
        this.nomProduit = nomProduit;
    }

    public Double getPrixProduit() {
        return prixProduit;
    }

    public void setPrixProduit(Double prixProduit) {
        this.prixProduit = prixProduit;
    }

    public String getPhotoProduit() {
        return photoProduit;
    }

    public void setPhotoProduit(String photoProduit) {
        this.photoProduit = photoProduit;
    }

    public int getQuantiteProduit() {
        return quantiteProduit;
    }

    public void setQuantiteProduit(int quantiteProduit) {
        this.quantiteProduit = quantiteProduit;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public int getIdpromotion() {
        return idpromotion;
    }

    public void setIdpromotion(int idpromotion) {
        this.idpromotion = idpromotion;
    }

    public String getCategorieMagasin() {
        return categorieMagasin;
    }

    public void setCategorieMagasin(String categorieMagasin) {
        this.categorieMagasin = categorieMagasin;
    }

    public int getIdMagasin() {
        return idMagasin;
    }

    public void setIdMagasin(int idMagasin) {
        this.idMagasin = idMagasin;
    }

    public double getValeval() {
        return valeval;
    }

    public void setValeval(double valeval) {
        this.valeval = valeval;
    }

    public int getQuantiteProduitClient() {
        return quantiteProduitClient;
    }

    public void setQuantiteProduitClient(int quantiteProduitClient) {
        this.quantiteProduitClient = quantiteProduitClient;
    }
   

  
 
     
    


    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final produits other = (produits) obj;
        if (this.idProduit != other.idProduit) {
            return false;
        }
        return true;
    }

   

    public produits(int idProduit, String referenceProduit, String nomProduit, Double prixProduit, int quantiteProduit) {
        this.idProduit = idProduit;
        this.referenceProduit = referenceProduit;
        this.nomProduit = nomProduit;
        this.prixProduit = prixProduit;
        this.quantiteProduit = quantiteProduit;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + this.idProduit;
        hash = 97 * hash + Objects.hashCode(this.nomProduit);
        return hash;
    }
 
    
  
 

    @Override
    public String toString() {
        return "produits{" + "nomProduit=" + nomProduit +", prixProduit=" + prixProduit +", referenceProduit=" + referenceProduit + ", prixProduit=" + prixProduit +  "quantiteProduit=" + quantiteProduit + ", active=" + active + ", idpromotion=" + idpromotion + ", categorieMagasin=" + categorieMagasin + ", idMagasin=" + idMagasin + '}';
    }
 
    
}
