package simulation;

import java.util.List;
import java.util.ArrayList;
import javax.swing.SwingWorker;
import material.*;

public class Environnement extends SwingWorker<Object, Usine[]/*ArrayList<Usine>*/> {
	private boolean actif = true;
	private static final int DELAI = 100;
        private static final int TOUR = 1000;
        private int test;
        
       
	
	@Override
	protected Object doInBackground() throws Exception {
                int temp = 0;
                ArrayList<Usine> listUsine = new ArrayList<Usine>();
                MenuFenetre menuFen = new MenuFenetre();
                ArrayList<Usine> usines = menuFen.getarrListUsine();
                Usine[] item = null;
              
                
		while(actif) {
			Thread.sleep(DELAI);
                        
                        if(usines != null){
                            listUsine = this.testCritereNecessaireProduction(usines, temp);
                            item = new Usine[listUsine.size()];
                           // item = listUsine.toArray ( new  String [listUsine.size ()]); 
                            //listUsine = this.testCritereNecessaireProduction(usines, temp);
                            for(int i=0; i<listUsine.size(); i++){
                                item[i] = listUsine.get(i);
                            }
                            String[] t = {"allo", "allo"};
                            publish(item);
                            temp++;
                            test = temp;
                        if(temp == 104){
                           temp = 0;
                        }
                        }else{
                            usines = menuFen.getarrListUsine();
                           // 
                           
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
        
       protected void process(List<Usine[]> v) {
            System.out.print("process() receiving values: "+test +v.get(0));
            /*for(ArrayList<Usine> u: v){
               // PanneauPrincipal.setListUsine(u);
            }*/
            //PanneauPrincipal.setListUsine(v);
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
        
        private ArrayList<Usine> testCritereNecessaireProduction(ArrayList<Usine> usines ,int temp ){
            ArrayList<Usine> tabPath = new ArrayList<Usine>();
            String str ="";
            for(Usine usine: usines){
                if(usine instanceof UsineMateriel){
                    if(temp == 0){
                        str = ((UsineMateriel)usine).getIdentity().getIcon().get(0).getPath();
                        
                      ((UsineMateriel)usine).setIconPrincipal(str);
                    }else if( temp <= (int)(0.3*((UsineMateriel)usine).getIntervalProd())){
                       str = ((UsineMateriel)usine).getIdentity().getIcon().get(1).getPath();
                      ((UsineMateriel)usine).setIconPrincipal(str);
                   }else if( temp > (int)(0.3*((UsineMateriel)usine).getIntervalProd()) 
                           && temp < (int)((UsineMateriel)usine).getIntervalProd()){
                       str = ((UsineMateriel)usine).getIdentity().getIcon().get(2).getPath();
                      ((UsineMateriel)usine).setIconPrincipal(str);
                   }else if(temp == (int)((UsineMateriel)usine).getIntervalProd()){
                       str = ((UsineMateriel)usine).getIdentity().getIcon().get(3).getPath();
                      ((UsineMateriel)usine).setIconPrincipal(str);
                   }
                    tabPath.add((UsineMateriel)usine);
                } else if(usine instanceof UsineAile){
                   if(temp == 0){
                        str = ((UsineAile)usine).getIdentity().getIcon().get(0).getPath();
                      ((UsineAile)usine).setIconPrincipal(str);
                    }else if( temp <= (int)(0.3*((UsineAile)usine).getIntervalProd())){
                       str = ((UsineAile)usine).getIdentity().getIcon().get(1).getPath();
                       ((UsineAile)usine).setIconPrincipal(str);
                   }else if( temp > (int)(0.3*((UsineAile)usine).getIntervalProd()) 
                           && temp < (int)((UsineAile)usine).getIntervalProd()){
                       str = ((UsineAile)usine).getIdentity().getIcon().get(2).getPath();
                      ((UsineAile)usine).setIconPrincipal(str);
                   }else if(temp == (int)((UsineAile)usine).getIntervalProd()){
                       str = ((UsineAile)usine).getIdentity().getIcon().get(3).getPath();
                       ((UsineAile)usine).setIconPrincipal(str);
                   }
                   tabPath.add((UsineAile)usine);
                } else if(usine instanceof UsineMoteur){
                   if(temp == 0){
                        str = ((UsineMoteur)usine).getIdentity().getIcon().get(0).getPath();
                      ((UsineMoteur)usine).setIconPrincipal(str);
                    }else if( temp <= (int)(0.3*((UsineMoteur)usine).getIntervalProd())){
                       str = ((UsineMoteur)usine).getIdentity().getIcon().get(1).getPath();
                       ((UsineMoteur)usine).setIconPrincipal(str);
                   }else if( temp > (int)(0.3*((UsineMoteur)usine).getIntervalProd()) 
                           && temp < (int)((UsineMoteur)usine).getIntervalProd()){
                     str = ((UsineMoteur)usine).getIdentity().getIcon().get(2).getPath();
                       ((UsineMoteur)usine).setIconPrincipal(str);
                   }else if(temp == (int)((UsineMoteur)usine).getIntervalProd()){
                      str = ((UsineMoteur)usine).getIdentity().getIcon().get(3).getPath();
                       ((UsineMoteur)usine).setIconPrincipal(str);
                   }
                   tabPath.add((UsineMoteur)usine);
                } if(usine instanceof UsineAssemblage){
                  if(temp == 0){
                        str = ((UsineAssemblage)usine).getIdentity().getIcon().get(0).getPath();
                      ((UsineAssemblage)usine).setIconPrincipal(str);
                    }else if( temp <= (int)(0.3*((UsineAssemblage)usine).getIntervalProd())){
                      str = ((UsineAssemblage)usine).getIdentity().getIcon().get(1).getPath();
                       ((UsineAssemblage)usine).setIconPrincipal(str);
                   }else if( temp > (int)(0.3*((UsineAssemblage)usine).getIntervalProd()) 
                           && temp < (int)((UsineAssemblage)usine).getIntervalProd()){
                     str = ((UsineAssemblage)usine).getIdentity().getIcon().get(2).getPath();
                       ((UsineAssemblage)usine).setIconPrincipal(str);
                   }else if(temp == (int)((UsineAssemblage)usine).getIntervalProd()){
                     str = ((UsineAssemblage)usine).getIdentity().getIcon().get(3).getPath();
                       ((UsineAssemblage)usine).setIconPrincipal(str);
                   }
                  tabPath.add((UsineAssemblage)usine);
                }
            }
            return tabPath;
        }
        
        /*private void checkTempProd(ArrayList<Usine> usines ,int temp ){
            
        }*/
        
        
        
        

}