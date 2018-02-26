/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iservice.Utilisateur;

import entites.Utilisateur.Client;
import entites.Utilisateur.Vendeur;
import javafx.collections.ObservableList;

/**
 *
 * @author user
 */
public interface IAdmin {
    public ObservableList<Client> SelectClient ();
    public ObservableList<Vendeur> SelectVendeur ();
    public boolean UpdateClient(String email,String npassword);
    public void SupprimerClient(Client c);
    public boolean recherche(String mail);
    public void modifierVendeur(Vendeur v,String email);
    public void SupprimerVendeur(Vendeur v);
    
}
