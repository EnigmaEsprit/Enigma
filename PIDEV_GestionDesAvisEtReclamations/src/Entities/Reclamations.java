/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.text.SimpleDateFormat;
import java.util.Date;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Ivan Landry ONANA
 */
public class Reclamations {
    
    
    private final IntegerProperty idReclamation;
    private final StringProperty typeReclamation;
    private final StringProperty objetReclamation;
    private final StringProperty contenuReclamation;
    private final StringProperty nomClient;
    private final StringProperty emailClient;
    private final ObjectProperty<Date> dateEnvoiReclamation;  
    private int idUser;

    private String dateF;

    public Reclamations(int idReclamation, String typeReclamation, String objetReclamation, String contenuReclamation, String nomClient, String emailClient, Date dateEnvoiReclamation) {
        this.idReclamation = new SimpleIntegerProperty(idReclamation);
        this.typeReclamation = new SimpleStringProperty(typeReclamation);
        this.objetReclamation = new SimpleStringProperty(objetReclamation);
        this.contenuReclamation = new SimpleStringProperty(contenuReclamation);
        this.nomClient = new SimpleStringProperty(nomClient);
        this.emailClient = new SimpleStringProperty(emailClient);
        this.dateEnvoiReclamation = new SimpleObjectProperty<>(dateEnvoiReclamation);
    }
    

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    
    
    public int getIdReclamation() {
        return idReclamation.get();
    }

    public String getTypeReclamation() {
        return typeReclamation.get();
    }

    public String getObjetReclamation() {
        return objetReclamation.get();
    }

    public String getContenuReclamation() {
        return contenuReclamation.get();
    }

    public String getNomClient() {
        return nomClient.get();
    }

    public String getEmailClient() {
        return emailClient.get();
    }    
    
    public Date getDateEnvoiReclamation() {
        return dateEnvoiReclamation.get();
    }

    public void setIdReclamation(int value) {
        idReclamation.set(value);
    }

    public void setTypeReclamation(String value) {
        typeReclamation.set(value);
    }

    public void setObjetReclamation(String value) {
        objetReclamation.set(value);
    }

    public void setContenuReclamation(String value) {
        contenuReclamation.set(value);
    }

    public void setNomClient(String value) {
        nomClient.set(value);
    }

    public void setEmailClient(String value) {
        emailClient.set(value);
    }    

    public void setDateEnvoiReclamation(Date value) {
        dateEnvoiReclamation.set(value);
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
