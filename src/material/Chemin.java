/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package material;

import java.awt.Point;
import java.util.ArrayList;

/**
 * cette classe permet d'identifier les usine d'un chemin avec de le tracer
 * facilement
 *
 * @author Romy Steve
 */
public class Chemin {

    private String de;
    private String vers;

    /**
     *
     * @param String dep
     * @param String vers
     */
    public Chemin(String dep, String vers) {
        this.de = dep;
        this.vers = vers;
    }

    /**
     *
     * @param ArrayList<Usine> usines
     * @return Point[]
     */
    public Point[] getPointUsinesChemin(ArrayList<Usine> usines) {
        Point[] ptUsineChemin = new Point[2];
        ptUsineChemin[0] = this.getPointUsine(usines, this.de);
        ptUsineChemin[1] = this.getPointUsine(usines, this.vers);
        return ptUsineChemin;
    }

    /**
     * obtenir les usines d'un chemin
     *
     * @param ArrayList<Usine> usines
     * @return Usine[]
     */
    public Usine[] getUsinesChemin(ArrayList<Usine> usines) {
        Usine[] UsineChemin = new Usine[2];
        UsineChemin[0] = this.getUsinePointChemin(usines, this.de);
        UsineChemin[1] = this.getUsinePointChemin(usines, this.vers);
        return UsineChemin;
    }

    /**
     * obtenir les positions des usines d'un chemein
     *
     * @param ArrayList<Usine> usines
     * @param String id
     * @return Usine
     */
    private Usine getUsinePointChemin(ArrayList<Usine> usines, String id) {
        for (Usine usine : usines) {
            if (usine instanceof UsineAile) {
                UsineAile usineAile = (UsineAile) usine;
                if (id.equals(usineAile.getIdentity().getId())) {
                    return usineAile;
                }

            } else if (usine instanceof UsineMateriel) {
                UsineMateriel usineMat = (UsineMateriel) usine;
                if (id.equals(usineMat.getIdentity().getId())) {
                    return usineMat;
                }

            } else if (usine instanceof UsineMoteur) {
                UsineMoteur usineMo = (UsineMoteur) usine;
                if (id.equals(usineMo.getIdentity().getId())) {
                    return usineMo;
                }

            } else if (usine instanceof UsineAssemblage) {
                UsineAssemblage usineAss = (UsineAssemblage) usine;
                if (id.equals(usineAss.getIdentity().getId())) {
                    return usineAss;
                }

            } else if (usine instanceof Entrepot) {
                Entrepot entrepot = (Entrepot) usine;
                if (id.equals(entrepot.getIdentity().getId())) {
                    return entrepot;
                }
            }
        }
        return null;
    }

    /**
     * obtenir la position d'une usine
     *
     * @param ArrayList<Usine> usines
     * @param String id
     * @return Point
     */
    private Point getPointUsine(ArrayList<Usine> usines, String id) {

        for (Usine usine : usines) {
            if (usine instanceof UsineAile) {
                UsineAile usineAile = (UsineAile) usine;
                if (id.equals(usineAile.getIdentity().getId())) {
                    return usineAile.getIdentity().getPoint();
                }

            } else if (usine instanceof UsineMateriel) {
                UsineMateriel usineMat = (UsineMateriel) usine;
                if (id.equals(usineMat.getIdentity().getId())) {
                    return usineMat.getIdentity().getPoint();
                }

            } else if (usine instanceof UsineMoteur) {
                UsineMoteur usineMo = (UsineMoteur) usine;
                if (id.equals(usineMo.getIdentity().getId())) {
                    return usineMo.getIdentity().getPoint();
                }

            } else if (usine instanceof UsineAssemblage) {
                UsineAssemblage usineAss = (UsineAssemblage) usine;
                if (id.equals(usineAss.getIdentity().getId())) {
                    return usineAss.getIdentity().getPoint();
                }

            } else if (usine instanceof Entrepot) {
                Entrepot entrepot = (Entrepot) usine;
                if (id.equals(entrepot.getIdentity().getId())) {
                    return entrepot.getIdentity().getPoint();
                }
            }
        }
        return null;
    }

    /**
     *
     * @return String
     */
    public String getDep() {
        return this.de;
    }

    /**
     *
     * @return String
     */
    public String getVers() {
        return this.vers;
    }

}
