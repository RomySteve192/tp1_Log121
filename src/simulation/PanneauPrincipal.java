package simulation;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import material.*;

public class PanneauPrincipal extends JPanel {

    private static final long serialVersionUID = 1L;

    // Variables temporaires de la demonstration:
    private static final int CONSTANT_X = 15;
    private static final int CONSTANT_Y = 15;
    private Point position = new Point(0, 0);
    private Point vitesse = new Point(2, 2);
    private int taille = 32;
    private int nbreAvionEntrepot = 0;

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

    private ArrayList<Point[]> getPointUsinesOfEachChemin(ArrayList<Chemin> chemins, ArrayList<Usine> usines) {
        ArrayList<Point[]> PtUsinesChemin = new ArrayList<Point[]>();
        for (Chemin chemin : chemins) {
            PtUsinesChemin.add(chemin.getPointUsinesChemin(usines));
        }
        return PtUsinesChemin;
    }

    private void drawChemin(Graphics g, ArrayList<Chemin> chemins, ArrayList<Usine> usines) {
        g.setColor(Color.black);
        for (Point[] tabPointUsine : this.getPointUsinesOfEachChemin(chemins, usines)) {
            g.drawLine(tabPointUsine[0].x, tabPointUsine[0].y, tabPointUsine[1].x, tabPointUsine[1].y);
        }
    }

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

    private void deplacerLesComposantSurLesChemin(Graphics g, ArrayList<Chemin> chemins, ArrayList<Usine> usines) {
        for (Chemin chemin : chemins) {
            Usine[] usineChemin = chemin.getUsinesChemin(usines);
            Composant compSortieUsineDep = this.getComposantUsine(usineChemin[0]);
            Point ptUsineDest = chemin.getPointUsinesChemin(usines)[1];

            this.deplacement(g, ptUsineDest, usineChemin[1], usineChemin[0], compSortieUsineDep);
        }
    }

    private void setIconComposant(Graphics g, String pathIcon, Point pointDep) {
        ImageIcon icon = new ImageIcon(pathIcon);
        icon.paintIcon(this, g, pointDep.x - CONSTANT_X, pointDep.y - CONSTANT_Y);
    }

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

    private void deplacement(Graphics g, Point pointFin, Usine usineDest, Usine usineDep, Composant comp) {
        if (this.getStatusProduction(usineDep) == true) {

            this.setComposantListCompSortie(usineDep, this.getCloneComposant(comp));
            this.setStatusCanProductionUsine(usineDep);
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
                        if(((Entrepot)usineDest).getStateEntrepot() == true){
                            ((Entrepot)usineDest).notifyUsine();
                        }
                    }else{
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
                        if(((Entrepot)usineDest).getStateEntrepot() == true){
                            ((Entrepot)usineDest).notifyUsine();
                        }
                    }else{
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
                        if(((Entrepot)usineDest).getStateEntrepot() == true){
                            ((Entrepot)usineDest).notifyUsine();
                        }
                    }else{
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

    public int calculateDistanceBetweenPoints(double x1, double y1, double x2, double y2) {
        return (int) Math.sqrt((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1));
    }

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
