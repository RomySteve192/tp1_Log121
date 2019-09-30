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
public class UsineAile implements Usine{
    
    private Identity identity;
    private int intervalleProd;
    private ArrayList<Composant> composantsEntree;
    private Composant composantSortie;
    private Boolean isOneTour = false;
    private String iconPrincipal;
    
    public UsineAile(Identity id, int intervalProd, Composant compoSortie){
        this.identity = id;
        this.intervalleProd = intervalProd;
        this.composantSortie = compoSortie;
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
    
    public int getIntervalProd(){
        return this.intervalleProd;
    }
    
    public void setMessageToUsine(Boolean oneTour){
        this.isOneTour = oneTour;
    }
    
    public Identity getIdentity(){
        return this.identity ;
    }
    
    public Composant getComposantSortie(){
        return this.composantSortie;
    }
    public void setIdentity(Identity iden){
        this.identity = iden;
    }
    
    public void setIndEtatUsine(Icon icon){
    }
    
}