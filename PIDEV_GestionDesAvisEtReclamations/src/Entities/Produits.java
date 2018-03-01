/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

/**
 *
 * @author Ivan Landry ONANA
 */
public class Produits {
    private int idProduit;
    private String nomProduit;
    private int referenceProduit;
    private int quantiteProduit;
    private double prixProduit;
    private ImageView imageProduit;
    private String urlPhoto;
    private Button boutonCommentaire;

    public Produits() {
    }

    public Produits(int idProduit, int refProduit, String nomProduit, int quantiteProduit, double prixProduit, ImageView imageProduit, Button bouton) {
        this.idProduit = idProduit;
        this.nomProduit = nomProduit;
        this.referenceProduit = refProduit;
        this.quantiteProduit = quantiteProduit;
        this.prixProduit = prixProduit;
        this.imageProduit = imageProduit;
        this.boutonCommentaire = bouton;
    }

    public Produits(int idProduit, int refProduit, String nomProduit, int quantiteProduit, double prixProduit, String urlPhoto, Button boutonCommentaire) {
        this.idProduit = idProduit;
        this.nomProduit = nomProduit;
        this.referenceProduit = refProduit;
        this.quantiteProduit = quantiteProduit;
        this.prixProduit = prixProduit;
        this.urlPhoto = urlPhoto;
        this.boutonCommentaire = boutonCommentaire;
    }

    public int getReferenceProduit() {
        return referenceProduit;
    }

    public void setReferenceProduit(int referenceProduit) {
        this.referenceProduit = referenceProduit;
    }
    
    public String getUrlPhoto() {
        return urlPhoto;
    }

    public void setUrlPhoto(String urlPhoto) {
        this.urlPhoto = urlPhoto;
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

    public ImageView getImageProduit() {
        return imageProduit;
    }

    public void setImageProduit(ImageView imageProduit) {
        this.imageProduit = imageProduit;
    }

    public Button getBoutonCommentaire() {
        return boutonCommentaire;
    }

    public void setBoutonCommentaire(Button bouton) {
        this.boutonCommentaire = bouton;
    }

    @Override
    public String toString() {
        return "Produits{" + "idProduit=" + idProduit + ", nomProduit=" + nomProduit + ", quantiteProduit=" + quantiteProduit + ", prixProduit=" + prixProduit + '}';
    }
    
}
