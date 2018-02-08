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
public class Vendeur extends Utilisateur{
    private String sutiation_fiscal;
    private String numero_bancaire;
    
    public Vendeur(String nom, String prenom, Date dn, Character sexe, String ad, String vl, int zip, String email, String pw,String img,String stf,String nc) {
        super(nom, prenom, dn,sexe, ad ,vl, zip, email, pw,img);
        this.sutiation_fiscal=stf;
        this.numero_bancaire=nc;
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
    
}
