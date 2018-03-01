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
public class Vendeur extends User{
    private String mdps;

    public Vendeur(String mdp, String username, String email) {
        super(username, email);
        this.mdps = mdp;
    }

    public String getMdps() {
        return mdps;
    }

    public void setMdps(String mdp) {
        this.mdps = mdp;
    }

    @Override
    public String toString() {
        return "Vendeur{" + "mdp=" + mdps + '}';
    }
    
    
    
}
