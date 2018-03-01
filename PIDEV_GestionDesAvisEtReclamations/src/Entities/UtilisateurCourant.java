/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author Ivan Landry ONANA
 */
public class UtilisateurCourant {
    private static Client clientConnecte = new Client("", "IvanClient", "ivanlandry.onanabakout@esprit.tn");
    private static Vendeur vendeurConnecte = new Vendeur("", "IvanVendeur", "ivan.onana2013@gmail.com");

    public static Client getClientConnecte() {
        return clientConnecte;
    }

    public static void setClientConnecte(Client clientConnecte) {
        UtilisateurCourant.clientConnecte = clientConnecte;
    }

    public static Vendeur getVendeurConnecte() {
        return vendeurConnecte;
    }

    public static void setVendeurConnecte(Vendeur vendeurConnecte) {
        UtilisateurCourant.vendeurConnecte = vendeurConnecte;
    }
    
    
    
}
