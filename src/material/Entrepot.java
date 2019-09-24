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
public class Entrepot implements Usine{
    
    private Identity identity;
    private int capacity;
    
    public Entrepot(Identity id, int cap){
        this.identity = id;
        this.capacity = cap;
    }
    
    public void setIndEtatUsine(Icon icon){
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
