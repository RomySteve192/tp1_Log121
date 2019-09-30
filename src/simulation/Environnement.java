package simulation;

import java.util.List;
import java.util.ArrayList;
import javax.swing.SwingWorker;
import material.*;

public class Environnement extends SwingWorker<Object, String[]> {
	private boolean actif = true;
	private static final int DELAI = 100;
        private static final int TOUR = 1000;
        
       
	
	@Override
	protected Object doInBackground() throws Exception {
                int temp = 0;
                String[] pathIconUsine = null;
                MenuFenetre menuFen = new MenuFenetre();
                ArrayList<Usine> usines = menuFen.getarrListUsine();
                
		while(actif) {
			Thread.sleep(DELAI);
                        
                        if(usines != null){
                            temp++;
                            pathIconUsine = this.testCritereNecessaireProduction(usines, temp).
                                            toArray(new String[this.testCritereNecessaireProduction(usines, temp).size()]);
                            publish(pathIconUsine);
                        if(temp == 104){
                           temp = 0;
                        }
                        }else{
                            usines = menuFen.getarrListUsine();
                        }
                       /* publish(pathIconUsine);
                        if(temp == 104){
                           temp = 0;
                        }*/
			/**
			 * C'est ici que vous aurez � faire la gestion de la notion de tour.
			 */
			firePropertyChange("TEST", null, "Ceci est un test");
		}
		return null;
	}
        
       protected void process(List<String[]> v) {
            System.out.print("process() receiving values: "+v.get(0));
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
        
        private ArrayList<String> testCritereNecessaireProduction(ArrayList<Usine> usines ,int temp ){
            ArrayList<String> tabPath = new ArrayList<String>();
            for(Usine usine: usines){
                if(usine instanceof UsineMateriel){
                    //double x = (0.3*((UsineMateriel)usine).getIntervalProd());
                   if( temp <= (int)(0.3*((UsineMateriel)usine).getIntervalProd())){
                      tabPath.add(((UsineMateriel)usine).getIdentity().getIcon().get(1).getPath());
                   }else if( temp > (int)(0.3*((UsineMateriel)usine).getIntervalProd()) 
                           && temp < (int)((UsineMateriel)usine).getIntervalProd()){
                      tabPath.add(((UsineMateriel)usine).getIdentity().getIcon().get(2).getPath());
                   }else if(temp == (int)((UsineMateriel)usine).getIntervalProd()){
                      tabPath.add(((UsineMateriel)usine).getIdentity().getIcon().get(3).getPath());
                   }
                } else if(usine instanceof UsineAile){
                   if( temp <= (int)(0.3*((UsineAile)usine).getIntervalProd())){
                       tabPath.add(((UsineAile)usine).getIdentity().getIcon().get(1).getPath());
                   }else if( temp > (int)(0.3*((UsineAile)usine).getIntervalProd()) 
                           && temp < (int)((UsineAile)usine).getIntervalProd()){
                      tabPath.add(((UsineAile)usine).getIdentity().getIcon().get(2).getPath());
                   }else if(temp == (int)((UsineAile)usine).getIntervalProd()){
                       tabPath.add(((UsineAile)usine).getIdentity().getIcon().get(3).getPath());
                   }
                } else if(usine instanceof UsineMoteur){
                   if( temp <= (int)(0.3*((UsineMoteur)usine).getIntervalProd())){
                      tabPath.add(((UsineMoteur)usine).getIdentity().getIcon().get(1).getPath());
                   }else if( temp > (int)(0.3*((UsineMoteur)usine).getIntervalProd()) 
                           && temp < (int)((UsineMoteur)usine).getIntervalProd()){
                     tabPath.add(((UsineMoteur)usine).getIdentity().getIcon().get(2).getPath());
                   }else if(temp == (int)((UsineMoteur)usine).getIntervalProd()){
                      tabPath.add( ((UsineMoteur)usine).getIdentity().getIcon().get(3).getPath());
                   }
                } if(usine instanceof UsineAssemblage){
                  if( temp <= (int)(0.3*((UsineAssemblage)usine).getIntervalProd())){
                      tabPath.add( ((UsineAssemblage)usine).getIdentity().getIcon().get(1).getPath());
                   }else if( temp > (int)(0.3*((UsineAssemblage)usine).getIntervalProd()) 
                           && temp < (int)((UsineAssemblage)usine).getIntervalProd()){
                     tabPath.add( ((UsineAssemblage)usine).getIdentity().getIcon().get(2).getPath());
                   }else if(temp == (int)((UsineAssemblage)usine).getIntervalProd()){
                     tabPath.add(  ((UsineAssemblage)usine).getIdentity().getIcon().get(3).getPath());
                   }
                }
            }
            return tabPath;
        }
        
        /*private void checkTempProd(ArrayList<Usine> usines ,int temp ){
            
        }*/
        
        
        
        

}