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
public class UsineMateriel implements Usine {
    
    private Identity identity;
    private int intervalleProd;
    
    public UsineMateriel(Identity id, int intervalProd){
        this.identity = id;
        this.intervalleProd = intervalProd;
    }

    public void setIdentity(Identity iden){
        this.identity = iden;
    }
    
    public Identity getIdentity(){
        return this.identity ;
    }
    
    public void setIndEtatUsine(Icon icon){
    }
}
