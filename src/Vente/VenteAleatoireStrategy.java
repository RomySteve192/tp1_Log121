/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vente;

import java.util.ArrayList;
import java.util.Random;
import material.*;

/**
 *
 * @author Romy Steve
 */
public class VenteAleatoireStrategy implements VenteStrategy {

    private int timelow;
    private int timehigh;
    private Random rdm;
    private ArrayList<ComposantAvion> listAvion;

    /**
     *
     * @param avions
     * @param time1
     * @param time2
     */
    public VenteAleatoireStrategy(ArrayList<ComposantAvion> avions, int time1, int time2) {
        this.timelow = time1;
        this.timehigh = time2;
        this.listAvion = avions;
        this.rdm = new Random();
        int result = this.rdm.nextInt(this.timehigh - this.timelow) + this.timelow;

    }

    /**
     *
     * @return
     */
    @Override
    public Boolean vente() {
        return true;
    }
}
