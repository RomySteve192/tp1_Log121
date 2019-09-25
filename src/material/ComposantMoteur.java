/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package material;

/**
 *
 * @author Romy Steve
 */
public class ComposantMoteur implements Composant {
    
    private Identity identity;
    static int noOfObjects = 0; 
    
    public ComposantMoteur(Identity id){
        noOfObjects++;
        this.identity = id;
    }
    
    public Identity getIdentity(){
        return this.identity ;
    }
    public void deplacer(){
        
    }
}
