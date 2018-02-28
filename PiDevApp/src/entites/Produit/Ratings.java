/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author jean
 */
public class Ratings {

    private int idRatings;
    private Double sommeEvaluation;
    private int nombreEvaluation;
    private Double MoyenneEvaluation;
    private int idProduit;

    public Ratings(int idRatings, Double sommeEvaluation, int nombreEvaluation, Double MoyenneEvaluation, int idProduit) {
        this.idRatings = idRatings;
        this.sommeEvaluation = sommeEvaluation;
        this.nombreEvaluation = nombreEvaluation;
        this.MoyenneEvaluation = MoyenneEvaluation;
        this.idProduit = idProduit;
    }

    public Ratings(Double sommeEvaluation, int nombreEvaluation, Double MoyenneEvaluation, int idProduit) {
        this.sommeEvaluation = sommeEvaluation;
        this.nombreEvaluation = nombreEvaluation;
        this.MoyenneEvaluation = MoyenneEvaluation;
        this.idProduit = idProduit;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + this.idRatings;
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
        final Ratings other = (Ratings) obj;
        if (this.idRatings != other.idRatings) {
            return false;
        }
        if (this.idProduit != other.idProduit) {
            return false;
        }
        return true;
    }

    public int getIdRatings() {
        return idRatings;
    }

    public void setIdRatings(int idRatings) {
        this.idRatings = idRatings;
    }

    public Double getSommeEvaluation() {
        return sommeEvaluation;
    }

    public void setSommeEvaluation(Double sommeEvaluation) {
        this.sommeEvaluation = sommeEvaluation;
    }

    public int getNombreEvaluation() {
        return nombreEvaluation;
    }

    public void setNombreEvaluation(int nombreEvaluation) {
        this.nombreEvaluation = nombreEvaluation;
    }

    public Double getMoyenneEvaluation() {
        return MoyenneEvaluation;
    }

    public void setMoyenneEvaluation(Double MoyenneEvaluation) {
        this.MoyenneEvaluation = MoyenneEvaluation;
    }

    public int getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(int idProduit) {
        this.idProduit = idProduit;
    }

    @Override
    public String toString() {
        return "ratings{" + "idRatings=" + idRatings + ", sommeEvaluation=" + sommeEvaluation + ", nombreEvaluation=" + nombreEvaluation + ", MoyenneEvaluation=" + MoyenneEvaluation + ", idProduit=" + idProduit + '}';
    }

}
