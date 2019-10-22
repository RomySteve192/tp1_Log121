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

    private ArrayList<Composant> listAvion;
    private int nbLimiteAvionAVend;

    /**
     *
     * @param avions
     * @param nbreAvion
     */
    public VenteIntvalFixeStrategy(ArrayList<Composant> avions, int nbreAvion) {
        this.listAvion = avions;
        this.nbLimiteAvionAVend = nbreAvion;
    }

    /**
     *
     * @return Boolean
     */
    @Override
    public Boolean vente() {
       // String str;
        if (this.listAvion.size() >= this.nbLimiteAvionAVend) {
            this.listAvion.remove(0);
            this.listAvion.remove(0);
            this.listAvion.remove(0);
            this.listAvion.remove(0);
            return true;
        }
        return false;
    }

}
