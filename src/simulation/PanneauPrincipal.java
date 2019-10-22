package simulation;

import Vente.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import material.*;

/**
 *
 * @author Romy Steve
 */
public class PanneauPrincipal extends JPanel {

    private static final long serialVersionUID = 1L;

    // Variables temporaires de la demonstration:
    private static final int CONSTANT_X = 15;
    private static final int CONSTANT_Y = 15;
    private Point position = new Point(0, 0);
    private Point vitesse = new Point(2, 2);
    private int taille = 32;
    private int nbreAvionEntrepot = 0;

    public static VenteStrategy venteStra = null;

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        // On ajoute à la position le delta x et y de la vitesse
        position.translate(vitesse.x, vitesse.y);
        g.fillRect(position.x, position.y, taille, taille);

        MenuFenetre menuFen = new MenuFenetre();
        ArrayList<Usine> usines = menuFen.getarrListUsine();
        ArrayList<Chemin> chemins = menuFen.getarrListChemin();
        if (usines != null && chemins != null) {
            this.drawChemin(g, chemins, usines);
            this.drawUsine(g, usines);
            this.deplacerLesComposantSurLesChemin(g, chemins, usines);
        }
    }

    /**
     * *
     * dessine les usines du reseau
     *
     * @param Graphics g
     * @param ArrayList<Usine> usines
     */
    private void drawUsine(Graphics g, ArrayList<Usine> usines) {

        for (Usine usine : usines) {
            if (usine instanceof UsineMateriel) {
                UsineMateriel usineMat = (UsineMateriel) usine;
                ImageIcon icon = new ImageIcon(usineMat.getIconPrincipal());
                icon.paintIcon(this, g, usineMat.getIdentity().getPoint().x - CONSTANT_X, usineMat.getIdentity().getPoint().y - CONSTANT_Y);
            } else if (usine instanceof UsineMoteur) {
                UsineMoteur usineMo = (UsineMoteur) usine;
                ImageIcon icon = new ImageIcon(usineMo.getIconPrincipal());
                icon.paintIcon(this, g, usineMo.getIdentity().getPoint().x - CONSTANT_X, usineMo.getIdentity().getPoint().y - CONSTANT_Y);
            } else if (usine instanceof UsineAssemblage) {
                UsineAssemblage usineAss = (UsineAssemblage) usine;
                ImageIcon icon = new ImageIcon(usineAss.getIconPrincipal());
                icon.paintIcon(this, g, usineAss.getIdentity().getPoint().x - CONSTANT_X, usineAss.getIdentity().getPoint().y - CONSTANT_Y);
            } else if (usine instanceof UsineAile) {
                UsineAile usineAile = (UsineAile) usine;
                ImageIcon icon = new ImageIcon(usineAile.getIconPrincipal());
                icon.paintIcon(this, g, usineAile.getIdentity().getPoint().x - CONSTANT_X, usineAile.getIdentity().getPoint().y - CONSTANT_Y);
            } else if (usine instanceof Entrepot) {
                Entrepot entrepot = (Entrepot) usine;
                ImageIcon icon;
                this.setIconEntrepot(entrepot, this.nbreAvionEntrepot);

                icon = new ImageIcon(entrepot.getIconPrincipal());

                icon.paintIcon(this, g, entrepot.getIdentity().getPoint().x - CONSTANT_X, entrepot.getIdentity().getPoint().y - CONSTANT_Y);
            }
        }
    }

    /**
     * Obtenir les postions de chaque usine du réseau
     *
     * @param ArrayList<Chemin> chemins
     * @param ArrayList<Usine> usines
     * @return
     */
    private ArrayList<Point[]> getPointUsinesOfEachChemin(ArrayList<Chemin> chemins, ArrayList<Usine> usines) {
        ArrayList<Point[]> PtUsinesChemin = new ArrayList<Point[]>();
        for (Chemin chemin : chemins) {
            PtUsinesChemin.add(chemin.getPointUsinesChemin(usines));
        }
        return PtUsinesChemin;
    }

    /**
     * *
     * déssine les chemins du reseau
     *
     * @param Graphics g
     * @param ArrayList<Chemin> chemins
     * @param ArrayList<Usine> usines
     */
    private void drawChemin(Graphics g, ArrayList<Chemin> chemins, ArrayList<Usine> usines) {
        g.setColor(Color.black);
        for (Point[] tabPointUsine : this.getPointUsinesOfEachChemin(chemins, usines)) {
            g.drawLine(tabPointUsine[0].x, tabPointUsine[0].y, tabPointUsine[1].x, tabPointUsine[1].y);
        }
    }

    /**
     * *
     * obtenir la postion du composant de sortie
     *
     * @param Composant composant
     * @return Point
     */
    private Point getPositionComposantSortie(Composant composant) {
        if (composant instanceof ComposantAile) {
            return ((ComposantAile) composant).getPosition();
        } else if (composant instanceof ComposantMetal) {
            return ((ComposantMetal) composant).getPosition();
        } else if (composant instanceof ComposantAvion) {
            return ((ComposantAvion) composant).getPosition();
        } else if (composant instanceof ComposantMoteur) {
            return ((ComposantMoteur) composant).getPosition();
        }
        return null;
    }

    /**
     * permet d'obtenir le statut de collision entre un composant et l'usine si
     * True collision si False pas collision
     *
     * @param Composant composant
     * @return
     */
    private Boolean getStatutIsCollisionComposantSortie(Composant composant) {
        if (composant instanceof ComposantAile) {
            return ((ComposantAile) composant).getCollision();
        } else if (composant instanceof ComposantMetal) {
            return ((ComposantMetal) composant).getCollision();
        } else if (composant instanceof ComposantAvion) {
            return ((ComposantAvion) composant).getCollision();
        } else if (composant instanceof ComposantMoteur) {
            return ((ComposantMoteur) composant).getCollision();
        }
        return false;
    }

    /**
     * mettre le status de collision sur un composant
     *
     * @param Composant composant
     * @param Boolean status
     */
    private void setStatusIscollision(Composant composant, Boolean status) {
        if (composant instanceof ComposantAile) {
            ((ComposantAile) composant).setIsCollision(status);
        } else if (composant instanceof ComposantMetal) {
            ((ComposantMetal) composant).setIsCollision(status);
        } else if (composant instanceof ComposantAvion) {
            ((ComposantAvion) composant).setIsCollision(status);
        } else if (composant instanceof ComposantMoteur) {
            ((ComposantMoteur) composant).setIsCollision(status);
        }
    }

    /**
     * obtenir le chemin de le l'icon d'un composant
     *
     * @param Composant composant
     * @return String
     */
    private String getPathIconComposant(Composant composant) {
        if (composant instanceof ComposantAile) {
            return ((ComposantAile) composant).getIcon().getPath();
        } else if (composant instanceof ComposantMetal) {
            return ((ComposantMetal) composant).getIcon().getPath();
        } else if (composant instanceof ComposantAvion) {
            return ((ComposantAvion) composant).getIcon().getPath();
        } else if (composant instanceof ComposantMoteur) {
            return ((ComposantMoteur) composant).getIcon().getPath();
        }
        return null;
    }

    /**
     * permet de déplacer le composant sur le chemin
     *
     * @param Graphics g
     * @param ArrayList<Chemin> chemins
     * @param ArrayList<Usine> usines
     */
    private void deplacerLesComposantSurLesChemin(Graphics g, ArrayList<Chemin> chemins, ArrayList<Usine> usines) {
        for (Chemin chemin : chemins) {
            Usine[] usineChemin = chemin.getUsinesChemin(usines);
            Composant compSortieUsineDep = this.getComposantUsine(usineChemin[0]);
            Point ptUsineDest = chemin.getPointUsinesChemin(usines)[1];

            this.deplacement(g, ptUsineDest, usineChemin[1], usineChemin[0], compSortieUsineDep);
        }
    }

    /**
     * permet de placer l'icon d'un composant
     *
     * @param Graphics g
     * @param String pathIcon
     * @param Point pointDep
     */
    private void setIconComposant(Graphics g, String pathIcon, Point pointDep) {
        ImageIcon icon = new ImageIcon(pathIcon);
        icon.paintIcon(this, g, pointDep.x - CONSTANT_X, pointDep.y - CONSTANT_Y);
    }

    /**
     * permet de placer le composant dans la liste des composants d'entrées de
     * l'usine de destination
     *
     * @param Usine usineDest
     * @param Composant comp
     */
    private void addComposantEntreUsineDest(Usine usineDest, Composant comp) {
        if (usineDest instanceof UsineAile) {
            ((UsineAile) usineDest).setComposantEntres(comp);
        } else if (usineDest instanceof UsineMoteur) {
            ((UsineMoteur) usineDest).setComposantEntres(comp);
        }
        if (usineDest instanceof UsineAssemblage) {
            ((UsineAssemblage) usineDest).setComposantEntres(comp);
        }
        if (usineDest instanceof Entrepot) {
            ((Entrepot) usineDest).setComposantEntres(comp);
        }
    }

    /**
     * obtenir le statut de production de l'usine
     *
     * @param Usine usine
     * @return
     */
    private Boolean getStatusProduction(Usine usine) {

        if (usine instanceof UsineMateriel) {
            return ((UsineMateriel) usine).getCanProduct();
        } else if (usine instanceof UsineAile) {
            return ((UsineAile) usine).getCanProduct();
        } else if (usine instanceof UsineMoteur) {
            return ((UsineMoteur) usine).getCanProduct();
        } else if (usine instanceof UsineAssemblage) {
            return ((UsineAssemblage) usine).getCanProduct();
        }
        return false;

    }

    /**
     *
     * @param Usine usine
     * @param Composant composant
     */
    private void setComposantListCompSortie(Usine usine, Composant composant) {
        if (usine instanceof UsineMateriel) {
            ((UsineMateriel) usine).setlistComposantSorti(composant);
        } else if (usine instanceof UsineAile) {
            ((UsineAile) usine).setlistComposantSorti(composant);
        } else if (usine instanceof UsineMoteur) {
            ((UsineMoteur) usine).setlistComposantSorti(composant);
        } else if (usine instanceof UsineAssemblage) {
            ((UsineAssemblage) usine).setlistComposantSorti(composant);
        }
    }

    /**
     * obtenir le clone d'un composant
     *
     * @param Composant composant
     * @return Composant
     */
    private Composant getCloneComposant(Composant composant) {
        if (composant instanceof ComposantAile) {
            return (ComposantAile) ((ComposantAile) composant).clone();
        } else if (composant instanceof ComposantMetal) {
            return (ComposantMetal) ((ComposantMetal) composant).clone();
        } else if (composant instanceof ComposantAvion) {
            return (ComposantAvion) ((ComposantAvion) composant).clone();
        } else if (composant instanceof ComposantMoteur) {
            return (ComposantMoteur) ((ComposantMoteur) composant).clone();
        }
        return null;
    }

    /**
     * obtenir la liste des composants de sortie d'une usine
     *
     * @param Usine usine
     * @return ArrayList<Composant>
     */
    private ArrayList<Composant> getListComposantSorti(Usine usine) {

        if (usine instanceof UsineMateriel) {
            return ((UsineMateriel) usine).getlistComposantSorti();
        } else if (usine instanceof UsineAile) {
            return ((UsineAile) usine).getlistComposantSorti();
        } else if (usine instanceof UsineMoteur) {
            return ((UsineMoteur) usine).getlistComposantSorti();
        } else if (usine instanceof UsineAssemblage) {
            return ((UsineAssemblage) usine).getlistComposantSorti();
        }
        return null;
    }

    /**
     * mettre le statut de production d'une usine
     *
     * @param Usine usine
     */
    private void setStatusCanProductionUsine(Usine usine) {
        if (usine instanceof UsineMateriel) {
            ((UsineMateriel) usine).setCanProduct(false);
        } else if (usine instanceof UsineAile) {
            ((UsineAile) usine).setCanProduct(false);
        } else if (usine instanceof UsineMoteur) {
            ((UsineMoteur) usine).setCanProduct(false);
        } else if (usine instanceof UsineAssemblage) {
            ((UsineAssemblage) usine).setCanProduct(false);
        }
    }

    /**
     * deplacer le composant d'une usine de départ vers une usine de destination
     *
     * @param Graphics g
     * @param Point pointFin
     * @param Usine usineDest
     * @param Usine usineDep
     * @param Composant comp
     */
    private void deplacement(Graphics g, Point pointFin, Usine usineDest, Usine usineDep, Composant comp) {
        if (this.getStatusProduction(usineDep) == true) {

            this.setComposantListCompSortie(usineDep, this.getCloneComposant(comp));
            this.setStatusCanProductionUsine(usineDep);
        }
        if (venteStra instanceof VenteIntvalFixeStrategy) {
            if (usineDest instanceof Entrepot) {
                this.venteAvion(new VenteIntvalFixeStrategy(((Entrepot) usineDest).getComposantEntres(), 4),
                        (Entrepot) usineDest);
            }
        }
        for (Composant composant : this.getListComposantSorti(usineDep)) {
            String pathIcon = this.getPathIconComposant(composant);
            Point pointDep = this.getPositionComposantSortie(composant);
            if (this.getPositionComposantSortie(composant).x < pointFin.x && this.getPositionComposantSortie(composant).y == pointFin.y) {
                if (!this.isCollision(this.getPositionComposantSortie(composant), pointFin)) {
                    this.getPositionComposantSortie(composant).translate(vitesse.x, 0);
                    this.setIconComposant(g, pathIcon, this.getPositionComposantSortie(composant));
                } else if (this.isCollision(this.getPositionComposantSortie(composant), pointFin)
                        && this.getStatutIsCollisionComposantSortie(composant) == false) {
                    this.setStatusIscollision(composant, true);
                    if (usineDest instanceof Entrepot) {
                        this.nbreAvionEntrepot++;
                        this.addComposantEntreUsineDest(usineDest, composant);
                        if (((Entrepot) usineDest).getStateEntrepot() == true) {
                            ((Entrepot) usineDest).notifyUsine(false);
                        }
                    } else {
                        this.addComposantEntreUsineDest(usineDest, composant);
                    }
                }

            } else if ((this.getPositionComposantSortie(composant).x > pointFin.x) && (this.getPositionComposantSortie(composant).y > pointFin.y)) {

                if (!this.isCollision(this.getPositionComposantSortie(composant), pointFin)) {

                    this.getPositionComposantSortie(composant).translate(-vitesse.x, -vitesse.y);
                    this.setIconComposant(g, pathIcon, this.getPositionComposantSortie(composant));
                } else if (this.isCollision(this.getPositionComposantSortie(composant), pointFin)
                        && this.getStatutIsCollisionComposantSortie(composant) == false) {
                    this.setStatusIscollision(composant, true);
                    if (usineDest instanceof Entrepot) {
                        this.nbreAvionEntrepot++;
                        this.addComposantEntreUsineDest(usineDest, composant);
                        if (((Entrepot) usineDest).getStateEntrepot() == true) {
                            ((Entrepot) usineDest).notifyUsine(false);
                        }
                    } else {
                        this.addComposantEntreUsineDest(usineDest, composant);
                    }
                }
            } else if ((this.getPositionComposantSortie(composant).x > pointFin.x) && (this.getPositionComposantSortie(composant).y < pointFin.y)) {
                if (!this.isCollision(this.getPositionComposantSortie(composant), pointFin)) {
                    this.getPositionComposantSortie(composant).translate(-vitesse.x, vitesse.y);
                    this.setIconComposant(g, pathIcon, this.getPositionComposantSortie(composant));
                } else if (this.isCollision(this.getPositionComposantSortie(composant), pointFin)
                        && this.getStatutIsCollisionComposantSortie(composant) == false) {
                    this.setStatusIscollision(composant, true);
                    if (usineDest instanceof Entrepot) {
                        this.nbreAvionEntrepot++;
                        this.addComposantEntreUsineDest(usineDest, composant);
                        if (((Entrepot) usineDest).getStateEntrepot() == true) {
                            ((Entrepot) usineDest).notifyUsine(false);
                        }
                    } else {
                        this.addComposantEntreUsineDest(usineDest, composant);
                    }
                }

            }

        }
    }

    private Boolean isCollision(Point pointDep, Point pointFin) {
        if (this.calculateDistanceBetweenPoints(pointDep.x, pointDep.y, pointFin.x, pointFin.y) <= 2) {
            return true;
        }
        return false;
    }

    /**
     * calcul la collision
     *
     * @param double x1
     * @param double y1
     * @param double x2
     * @param double y2
     * @return int
     */
    public int calculateDistanceBetweenPoints(double x1, double y1, double x2, double y2) {
        return (int) Math.sqrt((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1));
    }

    /**
     * obtenir le composant de sortie de l'usine
     *
     * @param Usine usine
     * @return Composant
     */
    private Composant getComposantUsine(Usine usine) {
        if (usine instanceof UsineMateriel) {
            return ((UsineMateriel) usine).getComposantSortie();
        } else if (usine instanceof UsineAile) {
            return ((UsineAile) usine).getComposantSortie();
        } else if (usine instanceof UsineMoteur) {
            return ((UsineMoteur) usine).getComposantSortie();
        } else if (usine instanceof UsineAssemblage) {
            return ((UsineAssemblage) usine).getComposantSortie();
        } else if (usine instanceof Entrepot) {
            return ((Entrepot) usine).getComposantSortie();
        }
        return null;
    }

    /**
     * permet de ventre les avions
     *
     * @param VenteStrategy venteStra
     * @param Entrepot entrepot
     */
    private void venteAvion(VenteStrategy venteStra, Entrepot entrepot) {
        String str;

        if (venteStra.vente()) {
            str = entrepot.getIdentity().getIcon().get(0).getPath();
            entrepot.setIconPrincipal(str);
            this.nbreAvionEntrepot=0;
            entrepot.notifyUsine(true);
        }
    }

    /**
     * mettre a jour l'état de l'entrepot
     *
     * @param Usine usineDest
     * @param int nbreAvion
     */
    private void setIconEntrepot(Usine usineDest, int nbreAvion) {
        String str;
        if (usineDest instanceof Entrepot) {
            if (nbreAvion == 0) {
                str = ((Entrepot) usineDest).getIdentity().getIcon().get(0).getPath();
                ((Entrepot) usineDest).setIconPrincipal(str);
            } else if (nbreAvion <= (int) (0.3 * ((Entrepot) usineDest).getCapacity())) {
                str = ((Entrepot) usineDest).getIdentity().getIcon().get(1).getPath();
                ((Entrepot) usineDest).setIconPrincipal(str);
            } else if (nbreAvion <= (int) (0.6 * ((Entrepot) usineDest).getCapacity())) {
                str = ((Entrepot) usineDest).getIdentity().getIcon().get(2).getPath();
                ((Entrepot) usineDest).setIconPrincipal(str);
            } else if (nbreAvion == ((Entrepot) usineDest).getCapacity()) {
                str = ((Entrepot) usineDest).getIdentity().getIcon().get(3).getPath();
                ((Entrepot) usineDest).setIconPrincipal(str);
            }

        }

    }

}
