package simulation;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.SwingWorker;
import material.*;

public class Environnement extends SwingWorker<Object, Usine[]> {

    private boolean actif = true;
    private static final int DELAI = 25;
    private int m_delai = 25;
    private int temProdAil = 0;
    private int temProdMo = 0;
    private int temProdAss = 0;
    // private int temProdMat = 0;

    @Override
    protected Object doInBackground() throws Exception {
        int temProdMat = 0;
        ArrayList<Usine> listUsine = new ArrayList<Usine>();
        MenuFenetre menuFen = new MenuFenetre();
        ArrayList<Usine> usines = menuFen.getarrListUsine();
        Usine[] item = null;

        while (actif) {
            Thread.sleep(m_delai);
            //System.out.println("allo" + m_delai);
            if (usines != null) {
                listUsine = this.testCritereNecessaireProduction(usines, temProdMat);
                item = new Usine[listUsine.size()];
                for (int i = 0; i < listUsine.size(); i++) {
                    item[i] = listUsine.get(i);
                }
                publish(item);
                temProdMat += 2;
                if (temProdMat == 112) {
                    temProdMat = 0;
                }
            } else {
                usines = menuFen.getarrListUsine();
            }
            /**
             * C'est ici que vous aurez � faire la gestion de la notion de tour.
             */
            firePropertyChange("TEST", null, "Ceci est un test " + m_delai);
        }
        return null;
    }

    protected void process(List<Usine[]> v) {
        System.out.print("process() receiving values: " + v.get(0));
    }

    private ArrayList<Usine> testCritereNecessaireProduction(ArrayList<Usine> usines, int temp) {
        ArrayList<Usine> tabPath = new ArrayList<Usine>();
        String str = "";
        for (Usine usine : usines) {
            if (usine instanceof UsineMateriel) {
                if (((UsineMateriel) usine).getStartTimeProd() == false) {
                    temp = 0;
                    str = ((UsineMateriel) usine).getIdentity().getIcon().get(0).getPath();
                    ((UsineMateriel) usine).setIconPrincipal(str);
                } else {
                    if (temp == 0) {
                        str = ((UsineMateriel) usine).getIdentity().getIcon().get(0).getPath();
                        ((UsineMateriel) usine).setIconPrincipal(str);
                        // temProdMat++;
                    } else if (temp <= (int) (0.3 * ((UsineMateriel) usine).getIntervalProd())) {
                        str = ((UsineMateriel) usine).getIdentity().getIcon().get(1).getPath();
                        ((UsineMateriel) usine).setIconPrincipal(str);
                    } else if (temp > (int) (0.3 * ((UsineMateriel) usine).getIntervalProd())
                            && temp < (int) ((UsineMateriel) usine).getIntervalProd()) {
                        str = ((UsineMateriel) usine).getIdentity().getIcon().get(2).getPath();
                        ((UsineMateriel) usine).setIconPrincipal(str);
                    } else if (temp == (int) ((UsineMateriel) usine).getIntervalProd()) {
                        str = ((UsineMateriel) usine).getIdentity().getIcon().get(3).getPath();
                        ((UsineMateriel) usine).setIconPrincipal(str);
                        ((UsineMateriel) usine).setCanProduct(true);
                    }
                }
                tabPath.add((UsineMateriel) usine);
            } else if (usine instanceof Entrepot) {
                m_delai = updateDelai((Entrepot) usine);
            } else if (usine instanceof UsineAile) {
                if (((UsineAile) usine).getStartTimeProd() == false) {
                    temProdAil = 0;
                    str = ((UsineAile) usine).getIdentity().getIcon().get(0).getPath();
                    ((UsineAile) usine).setIconPrincipal(str);
                } else {
                    if (temProdAil == 0) {
                        str = ((UsineAile) usine).getIdentity().getIcon().get(0).getPath();
                        ((UsineAile) usine).setIconPrincipal(str);
                        if (this.testComposantUsineAile(usine) == true) {
                            temProdAil += 2;
                        }
                    } else if (temProdAil <= (int) (0.3 * ((UsineAile) usine).getIntervalProd())) {
                        str = ((UsineAile) usine).getIdentity().getIcon().get(1).getPath();
                        ((UsineAile) usine).setIconPrincipal(str);
                    } else if (temProdAil > (int) (0.3 * ((UsineAile) usine).getIntervalProd())
                            && temProdAil < (int) ((UsineAile) usine).getIntervalProd()) {
                        str = ((UsineAile) usine).getIdentity().getIcon().get(2).getPath();
                        ((UsineAile) usine).setIconPrincipal(str);
                    } else if (temProdAil == (int) ((UsineAile) usine).getIntervalProd()) {
                        str = ((UsineAile) usine).getIdentity().getIcon().get(3).getPath();
                        ((UsineAile) usine).setIconPrincipal(str);
                        ((UsineAile) usine).setCanProduct(true);
                        temProdAil = 0;
                    }
                    if (temProdAil != 0) {
                        temProdAil += 2;
                    }
                }
                tabPath.add((UsineAile) usine);
            } else if (usine instanceof UsineMoteur) {
                if (((UsineMoteur) usine).getStartTimeProd() == false) {
                    temProdMo = 0;
                    str = ((UsineMoteur) usine).getIdentity().getIcon().get(0).getPath();
                    ((UsineMoteur) usine).setIconPrincipal(str);
                } else {
                    if (temProdMo == 0) {
                        str = ((UsineMoteur) usine).getIdentity().getIcon().get(0).getPath();
                        ((UsineMoteur) usine).setIconPrincipal(str);
                        if (this.testComposantUsineMoteur(usine)) {
                            temProdMo += 3;
                        }
                    } else if (temProdMo <= (int) (0.3 * ((UsineMoteur) usine).getIntervalProd())) {
                        str = ((UsineMoteur) usine).getIdentity().getIcon().get(1).getPath();
                        ((UsineMoteur) usine).setIconPrincipal(str);
                    } else if (temProdMo > (int) (0.3 * ((UsineMoteur) usine).getIntervalProd())
                            && temProdMo < (int) ((UsineMoteur) usine).getIntervalProd()) {
                        str = ((UsineMoteur) usine).getIdentity().getIcon().get(2).getPath();
                        ((UsineMoteur) usine).setIconPrincipal(str);
                    } else if (temProdMo == (int) ((UsineMoteur) usine).getIntervalProd()) {
                        str = ((UsineMoteur) usine).getIdentity().getIcon().get(3).getPath();
                        ((UsineMoteur) usine).setIconPrincipal(str);
                        ((UsineMoteur) usine).setCanProduct(true);
                        temProdMo = 0;
                    }
                    if (temProdMo != 0) {
                        temProdMo += 2;
                    }
                }
                tabPath.add((UsineMoteur) usine);
            } else if (usine instanceof UsineAssemblage) {
                if (((UsineAssemblage) usine).getStartTimeProd() == false) {
                    temProdAss = 0;
                    str = ((UsineAssemblage) usine).getIdentity().getIcon().get(0).getPath();
                    ((UsineAssemblage) usine).setIconPrincipal(str);
                } else {
                    if (temProdAss == 0) {
                        str = ((UsineAssemblage) usine).getIdentity().getIcon().get(0).getPath();
                        ((UsineAssemblage) usine).setIconPrincipal(str);
                        if (this.testComposantUsineAssemblage(usine)) {
                            temProdAss += 2;
                        }
                    } else if (temProdAss <= (int) (0.3 * ((UsineAssemblage) usine).getIntervalProd())) {
                        str = ((UsineAssemblage) usine).getIdentity().getIcon().get(1).getPath();
                        ((UsineAssemblage) usine).setIconPrincipal(str);
                    } else if (temProdAss > (int) (0.3 * ((UsineAssemblage) usine).getIntervalProd())
                            && temProdAss < (int) ((UsineAssemblage) usine).getIntervalProd()) {
                        str = ((UsineAssemblage) usine).getIdentity().getIcon().get(2).getPath();
                        ((UsineAssemblage) usine).setIconPrincipal(str);
                    } else if (temProdAss == (int) ((UsineAssemblage) usine).getIntervalProd()) {
                        str = ((UsineAssemblage) usine).getIdentity().getIcon().get(3).getPath();
                        ((UsineAssemblage) usine).setIconPrincipal(str);
                        ((UsineAssemblage) usine).setCanProduct(true);
                        temProdAss = 0;
                    }
                    if (temProdAss != 0) {
                        temProdAss += 2;
                    }
                }
                tabPath.add((UsineAssemblage) usine);
            }
        }
        return tabPath;
    }

    private int updateDelai(Usine usine) {
        if (usine instanceof Entrepot) {
            if (((Entrepot) usine).getComposantEntres().size() <= (int) (0.4 * ((Entrepot) usine).getCapacity())) {
                return 25;
            } else if (((Entrepot) usine).getComposantEntres().size() <= (int) (0.6 * ((Entrepot) usine).getCapacity())) {
                return 50;
            } else if (((Entrepot) usine).getComposantEntres().size() <= (int) (0.8 * ((Entrepot) usine).getCapacity())) {
                return 75;
            } else if (((Entrepot) usine).getComposantEntres().size() > (int) (0.8 * ((Entrepot) usine).getCapacity())
                    && ((Entrepot) usine).getComposantEntres().size() <= (int) (((Entrepot) usine).getCapacity())) {
                return 100;
            }
        }
        return 25;
    }

    private Boolean testComposantUsineAile(Usine usine) {
        if (((UsineAile) usine).getComposantEntres().size() >= 2) {
            for (int i = 0; i < 2; i++) {
                ((UsineAile) usine).getComposantEntres().remove(0);
            }
            return true;
        }
        return false;
    }

    private Boolean testComposantUsineMoteur(Usine usine) {
        if (((UsineMoteur) usine).getComposantEntres().size() >= 4) {
            for (int i = 0; i < 4; i++) {
                ((UsineMoteur) usine).getComposantEntres().remove(0);
            }
            return true;
        }
        return false;
    }

    private Boolean testComposantUsineAssemblage(Usine usine) {
        int nbreAile = 0, nbreMoteur = 0;
        Iterator<Composant> itCompEntree = ((UsineAssemblage) usine).getComposantEntres().iterator();
        if (((UsineAssemblage) usine).getComposantEntres().size() >= 6) {
            for (Composant comp : ((UsineAssemblage) usine).getComposantEntres()) {
                if (comp instanceof ComposantAile) {
                    nbreAile++;
                } else if (comp instanceof ComposantMoteur) {
                    nbreMoteur++;
                }
                if (nbreAile >= 2 && nbreMoteur >= 4) {
                    break;
                }
            }
            if (nbreAile >= 2 && nbreMoteur >= 4) {
                int nbreM = 0, nbreA = 0;
                while (itCompEntree.hasNext()) {
                    Composant composant = itCompEntree.next();
                    if (composant instanceof ComposantMoteur && nbreM < 4) {
                        itCompEntree.remove();
                        nbreM++;
                    } else if (composant instanceof ComposantAile && nbreA < 2) {
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
