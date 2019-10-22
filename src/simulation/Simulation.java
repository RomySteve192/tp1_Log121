package simulation;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 *
 * @author Romy Steve
 */
public class Simulation {

    /**
     * Cette classe repr�sente l'application dans son ensemble.
     *
     * @param args
     */
    public static void main(String[] args) {
        Environnement environnement = new Environnement();
        environnement.execute();
        FenetrePrincipale fenetre = new FenetrePrincipale();
        environnement.addPropertyChangeListener(fenetre);
        environnement.execute();
    }

}
