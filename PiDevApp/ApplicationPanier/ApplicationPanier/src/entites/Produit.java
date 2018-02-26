/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

import java.text.NumberFormat;
import java.util.Objects;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

/**
 *
 * @author jean
 */
public class Produit {
    
   
    private int idProduit;
    private String nomProduit;
    private int quantiteProduit;
    private double prixProduit;
    private ImageView imageProduit;
    private Button bouton;
    private TextField quantiteTextField;
    private double prixTotal;
 
    private int quantiteProduitClient;
   
    public Produit() {
    }

    public Produit(int idProduit, String nomProduit, int quantiteProduit, double prixProduit, ImageView imageProduit, Button bouton) {
        this.idProduit = idProduit;
        this.nomProduit = nomProduit;
        this.quantiteProduit = quantiteProduit;
        this.prixProduit = prixProduit;
        this.imageProduit = imageProduit;
        this.bouton = bouton;
    }
    
    
    public Produit(String nomProduit, int quantiteProduit, double prixProduit,ImageView imageProduit,Button bouton) {
        this.nomProduit = nomProduit;
        this.quantiteProduit = quantiteProduit;
        this.prixProduit = prixProduit;
        this.imageProduit = imageProduit;
        this.bouton=bouton;
        
       
        
    }

    public Produit(String nomProduit,  double prixProduit, ImageView imageProduit, Button bouton, TextField quantiteTextField) {
        this.nomProduit = nomProduit;
        
        this.prixProduit = prixProduit;
        this.imageProduit = imageProduit;
        this.bouton = bouton;
        this.quantiteTextField = quantiteTextField;
    }

    public Produit(int idProduit, String nomProduit, int quantiteProduit, double prixProduit, ImageView imageProduit, Button bouton, double prixTotal) {
        this.idProduit = idProduit;
        this.nomProduit = nomProduit;
        this.quantiteProduit = quantiteProduit;
        this.prixProduit = prixProduit;
        this.imageProduit = imageProduit;
        this.bouton = bouton;
        this.prixTotal = prixTotal;
    }
    
    

    public Produit(String nomProduit, int quantiteProduitClient, double prixProduit) {
        this.nomProduit = nomProduit;
        this.quantiteProduitClient = quantiteProduitClient;
        this.prixProduit = prixProduit;
    }

    public Produit(int idProduit, String nomProduit, int quantiteProduitClient, double prixProduit) {
        this.idProduit = idProduit;
        this.nomProduit = nomProduit;
        this.quantiteProduitClient = quantiteProduitClient;
        this.prixProduit = prixProduit;
    }

    public Produit(int idProduit, String nomProduit, int quantiteProduit, double prixProduit, int quantiteProduitClient) {
        this.idProduit = idProduit;
        this.nomProduit = nomProduit;
        this.quantiteProduit = quantiteProduit;
        this.prixProduit = prixProduit;
        this.quantiteProduitClient = quantiteProduitClient;
    }
    
    
     public double prixTotal(){
        return getPrixProduit()*getQuantiteProduit();
    }

    public int getQuantiteProduitClient() {
        return quantiteProduitClient;
    }

    public void setQuantiteProduitClient(int quantiteProduitClient) {
        this.quantiteProduitClient = quantiteProduitClient;
    }

    public double getPrixTotal() {
       return getPrixProduit()*getQuantiteProduit();
    }

    public void setPrixTotal(double prixTotal) {
        this.prixTotal = prixTotal;
    }

   
    
        
    @Override
    public String toString() {
        return "Produit{" + "idProduit=" + idProduit + ", nomProduit=" + nomProduit + ", quantiteProduit=" + quantiteProduit + ", prixProduit=" + prixProduit + '}';
    }

    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + this.idProduit;
        return hash;
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
        final Produit other = (Produit) obj;
        if (this.idProduit != other.idProduit) {
            return false;
        }
        if (!Objects.equals(this.nomProduit, other.nomProduit)) {
            return false;
        }
        return true;
    }

    public ImageView getImageProduit() {
        return imageProduit;
    }

    public void setImageProduit(ImageView image) {
        this.imageProduit = image;
    }
    
    

    public int getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(int idProduit) {
        this.idProduit = idProduit;
    }

    public String getNomProduit() {
        return nomProduit;
    }

    public void setNomProduit(String nomProduit) {
        this.nomProduit = nomProduit;
    }

    public int getQuantiteProduit() {
        return quantiteProduit;
    }

    public void setQuantiteProduit(int quantiteProduit) {
        this.quantiteProduit = quantiteProduit;
    }

    public double getPrixProduit() {
        return prixProduit;
    }

    public void setPrixProduit(double prixProduit) {
        this.prixProduit = prixProduit;
    }

    public Button getBouton() {
        return bouton;
    }

    public void setBouton(Button bouton) {
        this.bouton = bouton;
    }

    public TextField getQuantiteTextField() {
        return quantiteTextField;
    }

    public void setQuantiteTextField(TextField quantiteTextField) {
        this.quantiteTextField = quantiteTextField;
    }

    
    
    
}
