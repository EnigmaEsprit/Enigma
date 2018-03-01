/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iservice.Commentaires;

import entites.Commentaires.Commentaires;
import java.util.List;

/**
 *
 * @author Ivan Landry ONANA
 */
public interface ICommentaires {
    public void ajouterCommentaires(Commentaires c, int i, int p);
    public void supprimerCommentaires(Commentaires c);
    public void modifierCommentaires(Commentaires c);
    public List<Commentaires> afficherCommentaires(int i);
    
}
