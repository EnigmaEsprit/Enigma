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
public class Client extends Utilisateur{
   
    /**
     *
     */
    private String nbc;
    private String date_validation;
    private int code_s;

    public Client(String nom, String prenom, String dn,String sexe,String ad, String vl, int zip,String nt ,String email, String pw/*, String img*/,String nbc,String dv,int cs) {
        super(nom, prenom, dn,sexe, ad, vl, zip, nt,email, pw/*, img*/);
            this.nbc=nbc;
        this.date_validation=dv;
        this.code_s=cs;
    }
 public Client(int id,String nom, String prenom, String dn,String sexe,String ad, String vl, int zip,String nt ,String email, String pw/*, String img*/,String nbc,String dv,int cs) {
        super(id,nom, prenom, dn,sexe, ad, vl, zip, nt,email, pw/*, img*/);
            this.nbc=nbc;
        this.date_validation=dv;
        this.code_s=cs;
    }

  
    


    

    public String getNbc() {
        return nbc;
    }

    public void setNbc(String nbc) {
        this.nbc = nbc;
    }

    public String getDate_validation() {
        return date_validation;
    }

    public void setDate_validation(String date_validation) {
        this.date_validation = date_validation;
    }

    public int getCode_s() {
        return code_s;
    }

    public void setCode_s(int code_s) {
        this.code_s = code_s;
    }
    public String toString()
    {
        return "nom: "+getNom()+" prenom: "+getPrenom()+" DN: "+getDate_naissance()+" AD: "+getAdresse()+" VL: "+getVille()+" ZIP: "+getZip()+" NTE: "+getNt()+" MAIL: "+getEmail()+" MDP: "+getMdp()+" NBC: "+getNbc()+" DV: "+getDate_validation()+" CS: "+getCode_s();
    }
   
    /**
     *
     */
  

   
}
