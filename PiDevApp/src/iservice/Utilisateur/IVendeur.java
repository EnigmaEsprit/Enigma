/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iservice.Utilisateur;

import entites.Utilisateur.Vendeur;

/**
 *
 * @author user
 */
public interface IVendeur {
     public void ajouterVendeur(Vendeur v);
    public void supprimerVendeur(Vendeur v);
    public void modifierVendeur(Vendeur v);
    public void modifierVendeurImg(Vendeur v,String img);
    public void afficherVendeur(Vendeur v);
}