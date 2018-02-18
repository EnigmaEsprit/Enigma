/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iservice;

import entites.Client;
import entites.Vendeur;
import javafx.collections.ObservableList;

/**
 *
 * @author user
 */
public interface IAdmin {
    public ObservableList<Client> SelectClient ();
    public ObservableList<Vendeur> SelectVendeur ();
    public void UpdateClient(String id,String npassword);
    public void SupprimerClient(Client c);
    public boolean recherche(String mail);
    public void modifierVendeur(Vendeur v,String email);
    public void SupprimerVendeur(Vendeur v);
    
}
