/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.Date;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

/**
 *
 * @author Ivan Landry ONANA
 */
public class Commentaires {
    private int idCommentaire;
    private String contenuCommentaire;
    private Date dateAjoutCommentaire;
    private String urlUserPhoto;
    private ImageView imageUser;
    private Button bouton;
    private Button bouton2;

    public Commentaires() {
    }

    public Commentaires(String contenuCommentaire) {
        this.contenuCommentaire = contenuCommentaire;
    }
    
    public Commentaires(int idCommentaire, String contenuCommentaire, Date dateAjoutCommentaire, Date dateModificationCommentaire) {
        this.idCommentaire = idCommentaire;
        this.contenuCommentaire = contenuCommentaire;
        this.dateAjoutCommentaire = dateAjoutCommentaire;
        //this.dateModificationCommentaire = dateModificationCommentaire;
    }

    public Commentaires(int idCommentaire, String contenuCommentaire, Date dateAjoutCommentaire, ImageView imageUser, Button bouton) {
        this.idCommentaire = idCommentaire;
        this.contenuCommentaire = contenuCommentaire;
        this.dateAjoutCommentaire = dateAjoutCommentaire;
        this.imageUser = imageUser;
        this.bouton = bouton;
    }

    public Commentaires(int idCommentaire, String contenuCommentaire, Date dateAjoutCommentaire, String urlUserPhoto, Button bouton) {
        this.idCommentaire = idCommentaire;
        this.contenuCommentaire = contenuCommentaire;
        this.dateAjoutCommentaire = dateAjoutCommentaire;
        this.urlUserPhoto = urlUserPhoto;
        this.bouton = bouton;
    } 

    public Commentaires(int idCommentaire, String contenuCommentaire, Date dateAjoutCommentaire, ImageView imageUser, Button bouton, Button bouton2) {
        this.idCommentaire = idCommentaire;
        this.contenuCommentaire = contenuCommentaire;
        this.dateAjoutCommentaire = dateAjoutCommentaire;
        this.imageUser = imageUser;
        this.bouton = bouton;
        this.bouton2 = bouton2;
    }

    public Commentaires(int idCommentaire, String text) {
         this.idCommentaire = idCommentaire;
        this.contenuCommentaire = text;
        }

    public Button getBouton2() {
        return bouton2;
    }

    public void setBouton2(Button bouton2) {
        this.bouton2 = bouton2;
    }
    
    public String getUrlUserPhoto() {
        return urlUserPhoto;
    }

    public void setUrlUserPhoto(String urlUserPhoto) {
        this.urlUserPhoto = urlUserPhoto;
    }

    public ImageView getImageUser() {
        return imageUser;
    }

    public void setImageUser(ImageView imageUser) {
        this.imageUser = imageUser;
    }

    public Button getBouton() {
        return bouton;
    }

    public void setBouton(Button bouton) {
        this.bouton = bouton;
    }
    
    public int getIdCommentaire() {
        return idCommentaire;
    }

    public String getContenuCommentaire() {
        return contenuCommentaire;
    }

    public Date getDateAjoutCommentaire() {
        return dateAjoutCommentaire;
    }

//    public Date getDateModificationCommentaire() {
//        return dateModificationCommentaire;
//    }

    public void setIdCommentaire(int idCommentaire) {
        this.idCommentaire = idCommentaire;
    }

    public void setContenuCommentaire(String contenuCommentaire) {
        this.contenuCommentaire = contenuCommentaire;
    }

    public void setDateAjoutCommentaire(Date dateAjoutCommentaire) {
        this.dateAjoutCommentaire = dateAjoutCommentaire;
    }

//    public void setDateModificationCommentaire(Date dateModificationCommentaire) {
//        this.dateModificationCommentaire = dateModificationCommentaire;
//    }

    @Override
    public String toString() {
        return "Commentaires{" + "idCommentaire=" + idCommentaire + ", contenuCommentaire=" + contenuCommentaire + ", dateAjoutCommentaire=" + dateAjoutCommentaire + '}';
    }
    
    
}
