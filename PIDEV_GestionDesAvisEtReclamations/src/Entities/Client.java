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
public class Client extends User{
    private String mdp;

    public Client(String mdp, String username, String email) {
        super(username, email);
        this.mdp = mdp;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    @Override
    public String toString() {
        return "Client{" + "mdp=" + mdp + '}';
    }
    
    
    
}
