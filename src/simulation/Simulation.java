package simulation;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class Simulation {

	/**
	 * Cette classe repr�sente l'application dans son ensemble.
	 */
	public static void main(String[] args) {
		Environnement environnement = new Environnement();
               /* environnement.addPropertyChangeListener(new PropertyChangeListener() {
                        public void propertyChange(PropertyChangeEvent evt) {
                          if ("progress".equals(evt.getPropertyName())) {
                           System.out.print((String) evt.getNewValue());
                          }
                        }
                      });*/
                environnement.execute();
		FenetrePrincipale fenetre = new FenetrePrincipale();

		environnement.addPropertyChangeListener(fenetre);
		environnement.execute();
	}

}
