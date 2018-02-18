/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

import java.util.Date;

/**
 *
 * @author user
 */
public class Vendeur extends Utilisateur{
    private String sutiation_fiscal;
    private String numero_bancaire;
    /*  public enum situation_fiscal{
    Mauvaise,
    Moyenne,
    Bien,
    TresBien;
    
    }*/
    public Vendeur(String nom, String prenom, String dn, String sexe, String ad, String vl, int zip, String nt, String email, String pw,String sf,String rip) {
        super(nom, prenom, dn, sexe, ad, vl, zip, nt, email, pw);
        this.sutiation_fiscal = sf;
        this.numero_bancaire = rip;
    }
    public Vendeur(int id,String nom, String prenom, String dn, String sexe, String ad, String vl, int zip, String nt, String email, String pw,String sf,String rip) {
        super(id,nom, prenom, dn, sexe, ad, vl, zip, nt, email, pw);
        this.sutiation_fiscal = sf;
        this.numero_bancaire = rip;
    }

  
    public String getSutiation_fiscal() {
        return sutiation_fiscal;
    }

    public void setSutiation_fiscal(String sutiation_fiscal) {
        this.sutiation_fiscal = sutiation_fiscal;
    }

    public String getNumero_bancaire() {
        return numero_bancaire;
    }

    public void setNumero_bancaire(String numero_bancaire) {
        this.numero_bancaire = numero_bancaire;
    }
     public String toString()
    {
        return "nom: "+getNom()+" prenom: "+getPrenom()+" DN: "+getDate_naissance()+" AD: "+getAdresse()+" VL: "+getVille()+" ZIP: "+getZip()+" NTE: "+getNt()+" MAIL: "+getEmail()+" MDP: "+getMdp()+"SF: "+getSutiation_fiscal()+"RIB: "+getNumero_bancaire();
   
}
}