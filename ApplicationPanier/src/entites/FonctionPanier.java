 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

import iservice.IPanier;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jean
 */
public class FonctionPanier implements IPanier{
    
    private static  List<Produit>  panier=new ArrayList<>();

    public FonctionPanier() {
      // panier = new ArrayList<>();
    }
    
    
    public static List<Produit> getListeProduit(){
        
        return  panier;
    }
    
    //@Override
    public  static int rechercherProduit(String nomProduit) {

   for(int i=0; i<panier.size() ;i++){ 
       
        if(panier.get(i).getNomProduit().equals(nomProduit))
            return i;
    }
        return 0;
    
    }
    
   // @Override
    public  static void  ajouterArticle(Produit p){
    
     if(!panier.isEmpty()){
         
       if(ProduitExiste(p))
       {
       
        int positionProduit=1;
        
         positionProduit+= rechercherProduit(p.getNomProduit());
        
         
        if(positionProduit !=0){
 
        panier.get(positionProduit-1).setQuantiteProduit(panier.get(positionProduit-1).getQuantiteProduit()+p.getQuantiteProduit());
            System.out.println("modification quantité du produit: "+ panier.get(positionProduit-1).getNomProduit());
        }
       
       }else{
             
        panier.add(p);
            System.out.println("ajout nouveau produit");
        }
         
     }else{
             
        panier.add(p);
            System.out.println("ajout nouveau produit");
        }
        
        System.out.println(panier);
    }
    
    
    
   // @Override
    public static Boolean modifierQTeArticle(Produit p,int qteProduit){
    
        Boolean result=false;
        if(!panier.isEmpty()){
            
            System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
            System.out.println(panier);
            System.out.println(p);
             System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
             if(ProduitExiste(p))
       {
           System.out.println("oooooooooooooooooooooooooooo");
           if(qteProduit>0)
           {
            int positionProduit=1;
        
         positionProduit+= rechercherProduit(p.getNomProduit());
         System.out.println("oooooooooooooooooooooooooooo");
           if(positionProduit !=0){
                 panier.get(positionProduit-1).setQuantiteProduit(qteProduit);
                 System.out.println("oooooooooooooooooooooooooooo");
                 result=true;
             }
           
           }
       
       }
        else
        {
            supprimerArticle(p);
        }
             
        }
        return result;
    }
    
    //@Override
    public static Boolean supprimerArticle(Produit p){
    
        boolean result = false ;
        if(!panier.isEmpty()){
            
            if(ProduitExiste(p))
       {
       
        int positionProduit=1;
        
         positionProduit+= rechercherProduit(p.getNomProduit());
        
         
        if(positionProduit !=0){
 
        panier.remove(p);
        result=true;
        }
            
         
        }
        
        } 
        
        return result;
    }
    //@Override
    public static void afficherPanier(){
        
        //panier.forEach(p -> System.out.println(p));
        panier.forEach((p) -> {
            System.out.println(p);
        });
    }
    //@Override
    public static double MontantGlobal(){
    
    double total=0;
    for(int i=0; i<panier.size();i++){
    total += panier.get(i).getQuantiteProduit()*panier.get(i).getPrixProduit();
    }
    return total;
    }

    
    
   // @Override
    public static void  supprimePanier(){
  panier.clear();
}
    
   // @Override
    public static int compterArticles(){
    
        if(!panier.isEmpty()){
        return panier.size();
        }
        return 0;
    }

    //@Override
    public static boolean ProduitExiste(Produit p) {
    return panier.contains(p);
    }
    
}
