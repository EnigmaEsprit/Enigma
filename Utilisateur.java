/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

import java.sql.Date;
import sun.security.util.Password;

/**
 *
 * @author user
 */
public class Utilisateur {
     private String nom;
    private String prenom;
    private Date date_naissance;
    private String adresse;
    private String ville;
    private int zip;
    private String email;
    private String mdp;
    private String img;
      public Utilisateur(String nom,String prenom,Date dn,String ad,String vl,int zip,String email,String pw,String img){
       this.nom = nom;
       this.prenom = prenom;
       this.date_naissance = dn;
       this.adresse = ad;
       this.ville = vl;
       this.zip = zip;
       this.email = email;
       this.mdp = pw;
       this.img=img;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Date getDate_naissance() {
        return date_naissance;
    }

    public void setDate_naissance(Date date_naissance) {
        this.date_naissance = date_naissance;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public int getZip() {
        return zip;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
    
}
