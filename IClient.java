/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iservice;

import entites.Client;
import entites.Utilisateur;

/**
 *
 * @author user
 */
public interface IClient {
    public void ajouterClient(Client c);
    public void supprimerClient(Client c);
    public void modifierClient(Client c);
    public void modifierClientImg(Client c,String img);
    public void afficherClient(Client c);
    
}
