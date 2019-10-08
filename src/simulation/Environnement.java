package simulation;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.SwingWorker;
import material.*;

public class Environnement extends SwingWorker<Object, Usine[]/*ArrayList<Usine>*/> {
	private boolean actif = true;
	private static final int DELAI = 100;
        private static final int TOUR = 1000;
        private int test;
        private int temProdAil = 0;
        private int temProdMo = 0;
        private int temProdAss = 0;
        
       
	
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
                        if(temp == 113){
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
            int t =0;
           System.out.print("process() receiving values: "+test +v.get(0));
            /*for(ArrayList<Usine> u: v){
               // PanneauPrincipal.setListUsine(u);
            }*/
            //PanneauPrincipal.setListUsine(v);
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
                      ((UsineMateriel)usine).setCanProduct(true);
                    }
                    tabPath.add((UsineMateriel)usine);
                } else if(usine instanceof UsineAile){
                    if(temProdAil == 0){
                       str = ((UsineAile)usine).getIdentity().getIcon().get(0).getPath();
                      ((UsineAile)usine).setIconPrincipal(str);
                     // Boolean b = this.testComposantUsineAile(usine);
                      if(this.testComposantUsineAile(usine) == true){
                          temProdAil++;
                      }
                      //System.out.print(b);
                    }else if( temProdAil <= (int)(0.3*((UsineAile)usine).getIntervalProd())){
                       str = ((UsineAile)usine).getIdentity().getIcon().get(1).getPath();
                       ((UsineAile)usine).setIconPrincipal(str);
                    }else if( temProdAil > (int)(0.3*((UsineAile)usine).getIntervalProd()) 
                           && temProdAil < (int)((UsineAile)usine).getIntervalProd()){
                       str = ((UsineAile)usine).getIdentity().getIcon().get(2).getPath();
                      ((UsineAile)usine).setIconPrincipal(str);
                    }else if(temProdAil == (int)((UsineAile)usine).getIntervalProd()){
                       str = ((UsineAile)usine).getIdentity().getIcon().get(3).getPath();
                       ((UsineAile)usine).setIconPrincipal(str);
                       ((UsineAile)usine).setCanProduct(true);
                       temProdAil = 0;
                    }
                    if(temProdAil != 0){
                        temProdAil++;
                    }
                    tabPath.add((UsineAile)usine);
                } else if(usine instanceof UsineMoteur){
                   if(temProdMo == 0){
                        str = ((UsineMoteur)usine).getIdentity().getIcon().get(0).getPath();
                      ((UsineMoteur)usine).setIconPrincipal(str);
                      if(this.testComposantUsineMoteur(usine)){
                          temProdMo++;
                      }
                    }else if( temProdMo <= (int)(0.3*((UsineMoteur)usine).getIntervalProd())){
                       str = ((UsineMoteur)usine).getIdentity().getIcon().get(1).getPath();
                       ((UsineMoteur)usine).setIconPrincipal(str);
                    }else if( temProdMo > (int)(0.3*((UsineMoteur)usine).getIntervalProd()) 
                           && temProdMo < (int)((UsineMoteur)usine).getIntervalProd()){
                        str = ((UsineMoteur)usine).getIdentity().getIcon().get(2).getPath();
                       ((UsineMoteur)usine).setIconPrincipal(str);
                    }else if(temProdMo == (int)((UsineMoteur)usine).getIntervalProd()){
                        str = ((UsineMoteur)usine).getIdentity().getIcon().get(3).getPath();
                        ((UsineMoteur)usine).setIconPrincipal(str);
                        ((UsineMoteur)usine).setCanProduct(true);
                        temProdMo = 0;
                    }
                   if(temProdMo != 0){
                        temProdMo++;
                    }
                    tabPath.add((UsineMoteur)usine);
                }else if(usine instanceof UsineAssemblage){
                    if(temProdAss == 0){
                        str = ((UsineAssemblage)usine).getIdentity().getIcon().get(0).getPath();
                        ((UsineAssemblage)usine).setIconPrincipal(str);
                         if(this.testComposantUsineAssemblage(usine)){
                          temProdAss++;
                      }
                    }else if( temProdAss <= (int)(0.3*((UsineAssemblage)usine).getIntervalProd())){
                        str = ((UsineAssemblage)usine).getIdentity().getIcon().get(1).getPath();
                       ((UsineAssemblage)usine).setIconPrincipal(str);
                    }else if( temProdAss > (int)(0.3*((UsineAssemblage)usine).getIntervalProd()) 
                           && temProdAss < (int)((UsineAssemblage)usine).getIntervalProd()){
                        str = ((UsineAssemblage)usine).getIdentity().getIcon().get(2).getPath();
                        ((UsineAssemblage)usine).setIconPrincipal(str);
                    }else if(temProdAss == (int)((UsineAssemblage)usine).getIntervalProd()){
                        str = ((UsineAssemblage)usine).getIdentity().getIcon().get(3).getPath();
                        ((UsineAssemblage)usine).setIconPrincipal(str);
                        ((UsineAssemblage)usine).setCanProduct(true);
                        temProdAss = 0;
                   }
                   if(temProdAss != 0){
                        temProdAss++;
                    }
                   tabPath.add((UsineAssemblage)usine);
                }
            }
            return tabPath;
        }
        
        private Boolean testComposantUsineAile(Usine usine){
            int b = ((UsineAile)usine).getComposantEntres().size();
            if(((UsineAile)usine).getComposantEntres().size() >= 2){
                        /*((UsineAile)usine).getComposantEntres().remove(0);
                        ((UsineAile)usine).getComposantEntres().remove(1);*/
                         for(int i=0; i< 2; i++){
                            ((UsineAile)usine).getComposantEntres().remove(0);
                        }
                        return true;
                    }
            return false;
        }
        
         private Boolean testComposantUsineMoteur(Usine usine){
             int b = ((UsineMoteur)usine).getComposantEntres().size();
            if(((UsineMoteur)usine).getComposantEntres().size() >= 4){
                for(int i=0; i< 4; i++){
                    ((UsineMoteur)usine).getComposantEntres().remove(0);
                }
                        /*((UsineMoteur)usine).getComposantEntres().remove(0);
                        ((UsineMoteur)usine).getComposantEntres().remove(1);
                        ((UsineMoteur)usine).getComposantEntres().remove(2);
                        ((UsineMoteur)usine).getComposantEntres().remove(3);*/
                        return true;
                    }
            return false;
        }
         
          private Boolean testComposantUsineAssemblage(Usine usine){
               int b = ((UsineAssemblage)usine).getComposantEntres().size();
            int nbreAile = 0, nbreMoteur = 0;
            Iterator<Composant> itCompEntree = ((UsineAssemblage)usine).getComposantEntres().iterator();
            if(((UsineAssemblage)usine).getComposantEntres().size() >= 6){
                        for(Composant comp: ((UsineAssemblage)usine).getComposantEntres()){
                            if(comp instanceof ComposantAile){
                                nbreAile++;
                            }else if(comp instanceof ComposantMoteur){
                                nbreMoteur++;
                            }
                            if(nbreAile>= 2 && nbreMoteur >= 4){
                                break;
                            }
                        }
                        if(nbreAile>= 2 && nbreMoteur >= 4){
                             int nbreM=0, nbreA=0;
                             while(itCompEntree.hasNext()){
                                Composant composant = itCompEntree.next();
                                if(composant instanceof ComposantMoteur && nbreM < 4){
                                    itCompEntree.remove();
                                    nbreM++;
                                } else if(composant instanceof ComposantAile && nbreA < 2){
                                    itCompEntree.remove();
                                    nbreA++;
                                }
                            }
                            nbreAile = 0;
                            nbreMoteur = 0;
                            
                             return true;
                         }
                             nbreAile = 0;
                            nbreMoteur = 0;
                    }
            return false;
        }
        
        
        
        
        

}