/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

import java.sql.Date;



/**
 *
 * @author user
 */
public class Client extends Utilisateur{
   
    /**
     *
     */
    private String nbc;
    private Date date_validation;
    private int code_s;

    public Client(String nom, String prenom, Date dn,Character sexe,String ad, String vl, int zip, String email, String pw, String img,String nbc,Date dv,int cs) {
        super(nom, prenom, dn,sexe, ad, vl, zip, email, pw, img);
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

    public Date getDate_validation() {
        return date_validation;
    }

    public void setDate_validation(Date date_validation) {
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
        return "nom: "+getNom()+"prenom: "+getPrenom()+"DN: "+getDate_naissance()+"AD: "+getAdresse()+"VL: "+getVille()+"ZIP: "+getZip()+"MAIL: "+getEmail()+"MDP: "+getMdp()+"NBC: "+getNbc()+"DV: "+getDate_validation()+"CS: "+getCode_s();
    }
   
    /**
     *
     */
  

   
}
