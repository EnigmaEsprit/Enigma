/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IServices;

import Entities.magasins;
import java.util.List;

/**
 *
 * @author besmelah
 */
public interface Imagasins {
    public void ajouterMagasins(magasins m);
    public void supprimerMagasins(magasins m);
    public List<magasins> afficherMagasinc();
    public magasins rechercherMagasinsById(int i);
    public magasins rechercherMagasinsByName(String nom); 
    public void modifierMagasin(magasins m );

    
}
