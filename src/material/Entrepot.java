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
    
    public static final Boolean AcompEntre = true; 
    private Identity identity;
    private int capacity = 5;
    private ArrayList<Composant> composantsEntree = new ArrayList<Composant>();
    private Composant composantSortie = null;
    private String iconPrincipal;
    public Entrepot(Identity id, int cap){
        this.identity = id;
        this.capacity = cap;
    }
     public ArrayList<Composant> getComposantEntres(){
        return this.composantsEntree;
    }
    
    public void setComposantEntres(Composant comp){
        this.composantsEntree.add(comp);
    }
     public String getIconPrincipal(){
        return this.iconPrincipal;
    }
    
    public void setIconPrincipal(String ic){
        this.iconPrincipal = ic;
    }
    
    public void setIndEtatUsine(Icon icon){
    }
    public Composant getComposantSortie(){
        return this.composantSortie;
    }
    public void setCapacity(int cap){
        this.capacity = cap;
    }
    
    public int getCapacity(){
        return this.capacity;
    }
    
    public Identity getIdentity(){
        return this.identity ;
    }
    public void setIdentity(Identity iden){
        this.identity = iden;
    }
    
}
