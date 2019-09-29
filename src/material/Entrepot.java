/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package material;

import java.util.ArrayList;

/**
 *
 * @author Romy Steve
 */
public class Entrepot implements Usine{
    
    private Identity identity;
    private int capacity;
    private ArrayList<Composant> composantsEntree;
    private Composant composantSortie = null;
    
    public Entrepot(Identity id, int cap){
        this.identity = id;
        this.capacity = cap;
    }
    
    
    
    public void setIndEtatUsine(Icon icon){
    }
    public Composant getComposantSortie(){
        return this.composantSortie;
    }
    public void setCapacity(int cap){
        this.capacity = cap;
    }
    
    public Identity getIdentity(){
        return this.identity ;
    }
    public void setIdentity(Identity iden){
        this.identity = iden;
    }
    
}
