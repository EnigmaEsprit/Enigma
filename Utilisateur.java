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
public class Utilisateur {
    private int id;
    private String nom;
    private String prenom;
    private String date_naissance;
    private String sexe;
    private String adresse;
    private String ville;
    private int zip;
    private String nt;
    private String email;
    private String mdp;
    // private String img;
 public Utilisateur(int id,String nom, String prenom, String dn, String sexe, String ad, String vl, int zip, String nt, String email, String pw/*,String img*/) {
        this.id=id;
        this.nom = nom;
        this.prenom = prenom;
        this.date_naissance = dn;
        this.sexe = sexe;
        this.adresse = ad;
        this.ville = vl;
        this.zip = zip;
        this.nt =nt;
        this.email = email;
        this.mdp = pw;
        // this.img=img;
    }
    public Utilisateur(String nom, String prenom, String dn, String sexe, String ad, String vl, int zip, String nt, String email, String pw/*,String img*/) {
        this.nom = nom;
        this.prenom = prenom;
        this.date_naissance = dn;
        this.sexe = sexe;
        this.adresse = ad;
        this.ville = vl;
        this.zip = zip;
        this.nt =nt;
        this.email = email;
        this.mdp = pw;
        // this.img=img;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getDate_naissance() {
        return date_naissance;
    }

    public void setDate_naissance(String date_naissance) {
        this.date_naissance = date_naissance;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
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

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    /* public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
     */
}
