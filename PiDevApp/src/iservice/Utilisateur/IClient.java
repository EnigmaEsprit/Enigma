/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iservice.Utilisateur;

import entites.Utilisateur.Client;
import entites.Utilisateur.Utilisateur;

/**
 *
 * @author user
 */
public interface IClient {

    /**
     *
     * @param c
     * @return
     */
    public void ajouterClient(Client c);
    public void supprimerClient(String a);
    public void modifierClient(Client c,String mail);
    public void modifierClientImg(Client c,String img);
    public void afficherClient(Client c);
    public Client rechercheParMail(String mail);
    public boolean recherche(String mail);
}
