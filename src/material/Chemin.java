/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package material;

import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author Romy Steve
 */
public class Chemin {
    
    private String de;
    private String vers;
    
    public Chemin(String dep, String vers){
        this.de = dep;
        this.vers = vers;
    }
    
    public Point[] getPointUsinesChemin(ArrayList<Usine> usines){
        Point[] ptUsineChemin = new Point[2];
        ptUsineChemin[0] = this.getPointUsine(usines, this.de);
        ptUsineChemin[1] = this.getPointUsine(usines, this.vers);
        return ptUsineChemin;
    }
    
    private Point getPointUsine(ArrayList<Usine> usines, String id){
        
        for(Usine usine: usines){
            if(usine instanceof UsineAile){
                UsineAile usineAile = (UsineAile)usine;
                if(id.equals(usineAile.getIdentity().getId())){
                    return usineAile.getIdentity().getPoint();
                }
            
            }else if(usine instanceof UsineMateriel){
                UsineMateriel usineMat = (UsineMateriel)usine;
                if(id.equals(usineMat.getIdentity().getId())){
                    return usineMat.getIdentity().getPoint();
                }
            
            }else if(usine instanceof UsineMoteur){
                UsineMoteur usineMo = (UsineMoteur)usine;
                if(id.equals(usineMo.getIdentity().getId())){
                    return usineMo.getIdentity().getPoint();
                }
            
            }else if(usine instanceof UsineAssemblage){
                UsineAssemblage usineAss = (UsineAssemblage)usine;
                if(id.equals(usineAss.getIdentity().getId())){
                    return usineAss.getIdentity().getPoint();
                }
            
            }else if(usine instanceof Entrepot){
                Entrepot entrepot = (Entrepot)usine;
                if(id.equals(entrepot.getIdentity().getId())){
                    return entrepot.getIdentity().getPoint();
                }
            }
        }
        return null;
    }
    
    public String getDep(){
        return this.de;
    }
    
    public String getVers(){
        return this.vers;
    }
    
    public void deplacerComposantSurChemin(Composant composant){}
}
