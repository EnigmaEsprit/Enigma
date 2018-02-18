/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

import java.util.Date;

/**
 *
 * @author jean
 */
public class Commande {
   
    private int idCommande;
    private double prixTotal;
    private int idUser;
    private int etat;
    long time = System.currentTimeMillis();
    private java.sql.Date dateDeCommande = new java.sql.Date(time);
    
    private int idTransaction;

    public Commande() {
    }

    public int getIdCommande() {
        return idCommande;
    }

    public java.sql.Date getDateDeCommande() {
        return dateDeCommande;
    }

    public void setDateDeCommande(java.sql.Date dateDeCommande) {
        this.dateDeCommande = dateDeCommande;
    }
    
    
    public void setIdCommande(int idCommande) {
        this.idCommande = idCommande;
    }

    public double getPrixTotal() {
        return prixTotal;
    }

    public void setPrixTotal(double prixTotal) {
        this.prixTotal = prixTotal;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public int getIdTransaction() {
        
        return idTransaction;
    }

    public void setIdTransaction(int idTransaction) {
        this.idTransaction = idTransaction;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.idCommande;
        return hash;
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
        final Commande other = (Commande) obj;
        if (this.idCommande != other.idCommande) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Commande{" + "idCommande=" + idCommande + ", prixTotal=" + prixTotal + ", idUser=" + idUser + ", etat=" + etat + ", time=" + time + ", dateDeCommande=" + dateDeCommande + ", idTransaction=" + idTransaction + '}';
    }
    
    
    
}
