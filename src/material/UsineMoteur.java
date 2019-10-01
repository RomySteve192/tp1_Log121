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
public class UsineMoteur implements Usine{
    
    
    private Identity identity;
    private int intervalleProd;
    private ArrayList<Composant> composantsEntree = new ArrayList<Composant>();
    private Composant composantSortie;
    private Boolean isOneTour = false;
    private String iconPrincipal;
    
    public UsineMoteur(Identity id, int intervalProd, Composant compoSortie){
        this.identity = id;
        this.intervalleProd = intervalProd;
        this.composantSortie = compoSortie;
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
    
    public Boolean getMessageToUsine(){
        return this.isOneTour;
    }
    
    public void setMessageToUsine(Boolean oneTour){
        this.isOneTour = oneTour;
    }
    
    public int getIntervalProd(){
        return this.intervalleProd;
    }
    public void setIdentity(Identity iden){
        this.identity = iden;
    }
    public Composant getComposantSortie(){
        return this.composantSortie;
    }
    public Identity getIdentity(){
        return this.identity ;
    }
    public void setIndEtatUsine(Icon icon){
    }
    
}
