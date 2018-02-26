/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IServices;

import Entities.magasins;
import Entities.produits;
import java.util.List;

/**
 *
 * @author besmelah
 */
public interface Iproduits {
    public void ajouterProduits(produits p);
    public void supprimerProduit (produits p);
    public List<produits> afficherProduis();
    public produits rechercherProduitsById(int i);
    public produits rechercherProduitsByName(String nom);   
     public void modifierProduit(produits p );
   
    
}
