/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vente;
import java.util.ArrayList;
import material.*;

/**
 *
 * @author Romy Steve
 */
public class VenteIntvalFixeStrategy implements VenteStrategy {
    
    private ArrayList<ComposantAvion> listAvion;
    private int nbLimiteAvionAVend;
    
    
    public VenteIntvalFixeStrategy(ArrayList<ComposantAvion> avions, int nbreAvion){
        this.listAvion = avions;
        this.nbLimiteAvionAVend = nbreAvion;
    }
     
    @Override
    public Boolean vente(){
        if(this.listAvion.size() == this.nbLimiteAvionAVend){
            this.listAvion.remove(0);
            return true;
        }
        return false;
    }
    
}
