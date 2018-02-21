/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Date;

/**
 *
 * @author wieme
 */
public class magasins {
    private int idMagasin ;
    private String nomMagasin ;
    private String photoMagasin;
    private String descriptionMagasin;
    private Date dateCreationMagasin;
    private String contactMagasin;
    private String adresseMagasin;
    private String numeroMagasin;

    private int idUser;

    public magasins() {
    }


    public magasins( String nomMagasin, String photoMagasin, String descriptionMagasin, Date dateCreationMagasin, String contactMagasin, String adresseMagasin, String numeroMagasin , int idUser) {
     
        this.nomMagasin = nomMagasin;
        this.photoMagasin = photoMagasin;
        this.descriptionMagasin = descriptionMagasin;
        this.dateCreationMagasin = dateCreationMagasin;
        this.contactMagasin = contactMagasin;
        this.adresseMagasin = adresseMagasin;
        this.numeroMagasin = numeroMagasin;

        this.idUser = idUser;
    }

     public magasins( String nomMagasin,String descriptionMagasin, Date dateCreationMagasin, String contactMagasin,String photo, int idUser) {
     
        this.nomMagasin = nomMagasin;
        this.descriptionMagasin = descriptionMagasin;
        this.dateCreationMagasin = dateCreationMagasin;
        this.contactMagasin = contactMagasin;
        this.idUser = idUser;
        this.photoMagasin=photo;
    }

    public magasins(int idMagasin, String nomMagasin, String photoMagasin, String descriptionMagasin, Date dateCreationMagasin, String contactMagasin) {
        this.idMagasin = idMagasin;
        this.nomMagasin = nomMagasin;
        this.photoMagasin = photoMagasin;
        this.descriptionMagasin = descriptionMagasin;
        this.dateCreationMagasin = dateCreationMagasin;
        this.contactMagasin = contactMagasin;
    }

    public magasins(String recherche) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

  

    public int getIdMagasin() {
        return idMagasin;
    }

    public void setIdMagasin(int idMagasin) {
        this.idMagasin = idMagasin;
    }

    public String getNomMagasin() {
        return nomMagasin;
    }

    public void setNomMagasin(String nomMagasin) {
        this.nomMagasin = nomMagasin;
    }

    public String getPhotoMagasin() {
        return photoMagasin;
    }

    public void setPhotoMagasin(String photoMagasin) {
        this.photoMagasin = photoMagasin;
    }

    public String getDescriptionMagasin() {
        return descriptionMagasin;
    }

    public void setDescriptionMagasin(String descriptionMagasin) {
        this.descriptionMagasin = descriptionMagasin;
    }


    

    public String getContactMagasin() {
        return contactMagasin;
    }

    public void setContactMagasin(String contactMagasin) {
        this.contactMagasin = contactMagasin;
    }

    public Date getDateCreationMagasin() {
        return dateCreationMagasin;
    }

    public void setDateCreationMagasin(Date dateCreationMagasin) {
        this.dateCreationMagasin = dateCreationMagasin;
    }

    public String getAdresseMagasin() {
        return adresseMagasin;
    }

    public void setAdresseMagasin(String adresseMagasin) {
        this.adresseMagasin = adresseMagasin;
    }

    public String getNumeroMagasin() {
        return numeroMagasin;
    }

    public void setNumeroMagasin(String numeroMagasin) {
        this.numeroMagasin = numeroMagasin;
    }

   

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
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
        final magasins other = (magasins) obj;
        if (this.idMagasin != other.idMagasin) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "magasins{" + "idMagasin=" + idMagasin + ", nomMagasin=" + nomMagasin + ", photoMagasin=" + photoMagasin + ", descriptionMagasin=" + descriptionMagasin + ", dateCreationMagasin=" + dateCreationMagasin + ", contactMagasin=" + contactMagasin + ", idUser=" + idUser + '}';
    }
    
    
}
