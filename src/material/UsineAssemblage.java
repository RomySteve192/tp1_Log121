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
public class UsineAssemblage implements Usine{
    
    private Identity identity;
    private int intervalleProd;
    private ArrayList<Composant> composantsEntree;
    private Composant composantSortie;
    
    public UsineAssemblage(Identity id, int intervalProd, Composant compoSortie){
        this.identity = id;
        this.intervalleProd = intervalProd;
        this.composantSortie = compoSortie;
    }
    public Identity getIdentity(){
        return this.identity ;
    }
    public void setIdentity(Identity iden){
        this.identity = iden;
    }
    public void setIndEtatUsine(Icon icon){
    }
    
}
