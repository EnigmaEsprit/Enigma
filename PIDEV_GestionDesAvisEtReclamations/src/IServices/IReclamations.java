/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IServices;

import java.util.List;
import Entities.Reclamations;

/**
 *
 * @author Ivan Landry ONANA
 */
public interface IReclamations {
    public void envoyerUneReclamation(Reclamations c, String emailUser, String nomMag);
    public void supprimerUneReclamation(Reclamations c);
    public void repondreAUneReclamation(String dst, String msg, int id);
    public List<Reclamations> afficherReclamations(String emailVendeur);    
    
}
