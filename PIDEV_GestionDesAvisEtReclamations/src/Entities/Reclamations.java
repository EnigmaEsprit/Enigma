/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Ivan Landry ONANA
 */
public class Reclamations {
    
    
    private int idReclamation;
    private String typeReclamation;
    private String objetReclamation;
    private String contenuReclamation;
    private String nomClient;
    private String emailClient;
    private Date dateEnvoiReclamation;  
    private int idUser;

    private String dateF;

    public Reclamations() {
    }

    public Reclamations(int idReclamation, String typeReclamation, String objetReclamation, String contenuReclamation, String nomClient, String emailClient, Date dateEnvoiReclamation, int idUser, String dateF) {
        this.idReclamation = idReclamation;
        this.typeReclamation = typeReclamation;
        this.objetReclamation = objetReclamation;
        this.contenuReclamation = contenuReclamation;
        this.nomClient = nomClient;
        this.emailClient = emailClient;
        this.dateEnvoiReclamation = dateEnvoiReclamation;
        this.idUser = idUser;
        this.dateF = dateF;
    }

    public int getIdReclamation() {
        return idReclamation;
    }

    public void setIdReclamation(int idReclamation) {
        this.idReclamation = idReclamation;
    }

    public String getTypeReclamation() {
        return typeReclamation;
    }

    public void setTypeReclamation(String typeReclamation) {
        this.typeReclamation = typeReclamation;
    }

    public String getObjetReclamation() {
        return objetReclamation;
    }

    public void setObjetReclamation(String objetReclamation) {
        this.objetReclamation = objetReclamation;
    }

    public String getContenuReclamation() {
        return contenuReclamation;
    }

    public void setContenuReclamation(String contenuReclamation) {
        this.contenuReclamation = contenuReclamation;
    }

    public String getNomClient() {
        return nomClient;
    }

    public void setNomClient(String nomClient) {
        this.nomClient = nomClient;
    }

    public String getEmailClient() {
        return emailClient;
    }

    public void setEmailClient(String emailClient) {
        this.emailClient = emailClient;
    }

    public Date getDateEnvoiReclamation() {
        return dateEnvoiReclamation;
    }

    public void setDateEnvoiReclamation(Date dateEnvoiReclamation) {
        this.dateEnvoiReclamation = dateEnvoiReclamation;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getDateF() {
        return dateF;
    }

    public void setDateF(String dateF) {
        this.dateF = dateF;
    }
    
    

    public String getFormatDate() {
        Date dateFormat = getDateEnvoiReclamation();
        SimpleDateFormat df;
        df = new SimpleDateFormat("dd MMM yyyy");
        String formatt;
        formatt = df.format(dateFormat);
        return formatt;
    }

    public void setFormatDate(String formatDate) {
        this.dateF = formatDate;
    }

    @Override
    public String toString() {
        return "Reclamations{" + "idReclamation=" + idReclamation + ", typeReclamation=" + typeReclamation + ", objetReclamation=" + objetReclamation + ", contenuReclamation=" + contenuReclamation + ", nomClient=" + nomClient + ", emailClient=" + emailClient + ", dateEnvoiReclamation=" + dateEnvoiReclamation + '}';
    }
    
}
