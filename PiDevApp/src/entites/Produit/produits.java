/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites.Produit;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class produits {
 private int idProduit;
     private final IntegerProperty Prix = new SimpleIntegerProperty(0);
     private final StringProperty Nom = new SimpleStringProperty();
     private final IntegerProperty Ref = new SimpleIntegerProperty(0);
     private final IntegerProperty Quantite = new SimpleIntegerProperty(0);

 private String referenceProduit;
 private String nomProduit;
 private String prixProduit;
 private String photoProduit;
 private String quantiteProduit;
 private int active;
 private int idpromotion;
 private String categorieMagasin;
 private int idMagasin;
 private double valeval;

    public produits() {
    }
     public produits( String nomProduit, String prixProduit,  String quantiteProduit, String referenceProduit ) {
        this.referenceProduit = referenceProduit;
        this.nomProduit = nomProduit;
        this.prixProduit = prixProduit;
        this.quantiteProduit = quantiteProduit;
     
        
    }

    public produits(int idProduit, String referenceProduit, String nomProduit, String prixProduit, String photoProduit, String quantiteProduit, int active, int idpromotion, String categorieMagasin, int idMagasin, double valeval) {
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

    public produits(int idProduit, String referenceProduit, String nomProduit, String prixProduit, String photoProduit, String quantiteProduit, int active, int idpromotion, String categorieMagasin, int idMagasin) {
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

    public produits(  String referenceProduit, String nomProduit ,String prixProduit , String  photoProduit , String quantiteProduit, int active, String categorieMagasin, int idMagasin) {
        this.referenceProduit = referenceProduit;
        this.nomProduit = nomProduit;
        this.prixProduit = prixProduit;
        this.photoProduit = photoProduit;
        this.quantiteProduit = quantiteProduit;
        this.active = active;
        this.categorieMagasin = categorieMagasin;
        this.idMagasin = idMagasin;
    }

    public produits(int idProduit, String referenceProduit, String nomProduit, String prixProduit, String photoProduit, String quantiteProduit, int idpromotion, int idMagasin) {
        this.idProduit = idProduit;
        this.referenceProduit = referenceProduit;
        this.nomProduit = nomProduit;
        this.prixProduit = prixProduit;
        this.photoProduit = photoProduit;
        this.quantiteProduit = quantiteProduit;
        this.idpromotion = idpromotion;
        this.idMagasin = idMagasin;
    }
     public produits(int idProduit, String referenceProduit ,String nomProduit, String prixProduit , String photoProduit, String quantiteProduit,String categorieMagasin,  int idMagasin) {
        this.idProduit = idProduit;
        this.referenceProduit = referenceProduit;
        this.nomProduit = nomProduit;
        this.prixProduit = prixProduit;
        this.photoProduit = photoProduit;
        this.categorieMagasin=categorieMagasin;
        this.quantiteProduit = quantiteProduit;
        this.idMagasin = idMagasin;
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

    public String getPrixProduit() {
        return prixProduit;
    }

    public void setPrixProduit(String prixProduit) {
        this.prixProduit = prixProduit;
    }

    public String getPhotoProduit() {
        return photoProduit;
    }

    public void setPhotoProduit(String photoProduit) {
        this.photoProduit = photoProduit;
    }

    public String getQuantiteProduit() {
        return quantiteProduit;
    }

    public void setQuantiteProduit(String quantiteProduit) {
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + this.idProduit;
        return hash;
    }

    public IntegerProperty Prix() {
        return Prix;
    }

    public Integer Prixx() {
        return Prix().get();
    }

    public double getValeval() {
        return valeval;
    }

    public void setValeval(double valeval) {
        this.valeval = valeval;
    }


    public StringProperty Nom() {
        return Nom;
    }


      public produits Nommm(String value) {
        Nom().set(value);
        return this;
    }
           public produits Prixxx(int value) {
        Prix().set(value);
        return this;
    }
      
      
    public String Nomm() {
        return Nom().get();
    }

    public IntegerProperty Ref() {
        return Ref;
    }

    public int Reff() {
        return Ref().get();
    }

    
    public IntegerProperty Quantite() {
        return Quantite;
    }

    public int Quantitee() {
        return Quantite().get();
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

    public produits(int idProduit, String referenceProduit, String nomProduit, String prixProduit, String quantiteProduit) {
        this.idProduit = idProduit;
        this.referenceProduit = referenceProduit;
        this.nomProduit = nomProduit;
        this.prixProduit = prixProduit;
        this.quantiteProduit = quantiteProduit;
    }
 
    
  
 

    @Override
    public String toString() {
        return "produits{" + "nomProduit=" + nomProduit +", prixProduit=" + prixProduit +", referenceProduit=" + referenceProduit + ", prixProduit=" + prixProduit +  "quantiteProduit=" + quantiteProduit + ", active=" + active + ", idpromotion=" + idpromotion + ", categorieMagasin=" + categorieMagasin + ", idMagasin=" + idMagasin + '}';
    }
 
    
}
