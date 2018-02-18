/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

/**
 *
 * @author user
 */
public class Recherche {
    private String nom;
    private String prenom;

    
    private String adresse;
    private String ville;
    private int zip;
    private String nt;
    private String email;
    private String nom_magazin;

    public Recherche(String nom, String prenom, String adresse, String ville, int zip, String nt, String email, String nom_magazin) {
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.ville = ville;
        this.zip = zip;
        this.nt = nt;
        this.email = email;
        this.nom_magazin = nom_magazin;
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

    public String getNt() {
        return nt;
    }

    public void setNt(String nt) {
        this.nt = nt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNom_magazin() {
        return nom_magazin;
    }

    public void setNom_magazin(String nom_magazin) {
        this.nom_magazin = nom_magazin;
    }

    @Override
    public String toString() {
        return "recherche{" + "nom=" + nom + ", prenom=" + prenom + ", adresse=" + adresse + ", ville=" + ville + ", zip=" + zip + ", nt=" + nt + ", email=" + email + ", nom_magazin=" + nom_magazin + '}';
    }
    
    
}
