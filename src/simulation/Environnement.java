package simulation;

import java.util.ArrayList;
import javax.swing.SwingWorker;
import material.*;

public class Environnement extends SwingWorker<Object, String> {
	private boolean actif = true;
	private static final int DELAI = 100;
        private static final int TOUR = 1000;
	
	@Override
	protected Object doInBackground() throws Exception {
		while(actif) {
			Thread.sleep(DELAI);
			/**
			 * C'est ici que vous aurez � faire la gestion de la notion de tour.
			 */
			firePropertyChange("TEST", null, "Ceci est un test");
		}
		return null;
	}
        // informe les usines qu'un tour est passé
        private void sendMessageToUsine(ArrayList<Usine> usines, Boolean isOneTour){
            for(Usine usine: usines){
                if(usine instanceof UsineMateriel){
                    ((UsineMateriel)usine).setMessageToUsine(isOneTour);
                } else if(usine instanceof UsineAile){
                    ((UsineAile)usine).setMessageToUsine(isOneTour);
                } else if(usine instanceof UsineMoteur){
                    ((UsineMoteur)usine).setMessageToUsine(isOneTour);
                } if(usine instanceof UsineAssemblage){
                    ((UsineAssemblage)usine).setMessageToUsine(isOneTour);
                }
            }
        }
        
        private void testCritereNecessaireProduction(ArrayList<Usine> usines ,int temp ){
            for(Usine usine: usines){
                if(usine instanceof UsineMateriel){
                   // ((UsineMateriel)usine).setMessageToUsine(isOneTour);
                } else if(usine instanceof UsineAile){
                   // ((UsineAile)usine).setMessageToUsine(isOneTour);
                } else if(usine instanceof UsineMoteur){
                   // ((UsineMoteur)usine).setMessageToUsine(isOneTour);
                } if(usine instanceof UsineAssemblage){
                  //  ((UsineAssemblage)usine).setMessageToUsine(isOneTour);
                }
            }
        }
        
        
        
        

}