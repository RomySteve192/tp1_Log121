package simulation;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import material.*;

public class PanneauPrincipal extends JPanel{

        private static final long serialVersionUID = 1L;
	
	// Variables temporaires de la demonstration:
        private static final int CONSTANT_X = 15;
        private static final int CONSTANT_Y = 15;
	private Point position = new Point(0,0);
	private Point vitesse = new Point(1,1);
        private Point _vitesse = new Point(-1, -1);
        private Boolean isMove = false;
	private int taille = 32;
        private Point posComp;
        private static ArrayList<Usine> usines = null;
	
	@Override
        // @override
         public void paintComponent(Graphics g){
             
            super.paintComponent(g);
		// On ajoute à la position le delta x et y de la vitesse
             position.translate(vitesse.x, vitesse.y);
             g.fillRect(position.x, position.y, taille, taille);
              System.out.println(position.x);
             
             MenuFenetre menuFen = new MenuFenetre();
             ArrayList<Usine> usines = menuFen.getarrListUsine();
             ArrayList<Chemin> chemins = menuFen.getarrListChemin();
             if(usines != null && chemins != null){
                this.drawChemin(g, chemins, usines);
                this.drawUsine(g, usines);
                this.deplacerLesComposantSurLesChemin(g, chemins, usines);
             }
         }
         
         private void drawUsine(Graphics g, ArrayList<Usine> usines){
         
             for(Usine usine: usines){
                  if(usine instanceof UsineMateriel){
                            UsineMateriel usineMat = (UsineMateriel)usine;
                            ImageIcon icon = new ImageIcon(usineMat.getIdentity().getIcon().get(0).getPath());
                            icon.paintIcon(this, g, usineMat.getIdentity().getPoint().x - CONSTANT_X, usineMat.getIdentity().getPoint().y - CONSTANT_Y);
                        } else if (usine instanceof UsineMoteur){
                            UsineMoteur usineMo = (UsineMoteur)usine;
                            ImageIcon icon = new ImageIcon(usineMo.getIdentity().getIcon().get(0).getPath());
                            icon.paintIcon(this, g, usineMo.getIdentity().getPoint().x - CONSTANT_X, usineMo.getIdentity().getPoint().y - CONSTANT_Y);
                        } else if (usine instanceof UsineAssemblage){
                            UsineAssemblage usineAss = (UsineAssemblage)usine;
                            ImageIcon icon = new ImageIcon(usineAss.getIdentity().getIcon().get(0).getPath());
                            icon.paintIcon(this, g, usineAss.getIdentity().getPoint().x - CONSTANT_X, usineAss.getIdentity().getPoint().y - CONSTANT_Y);
                        } else if (usine instanceof UsineAile){
                            UsineAile usineAile = (UsineAile)usine;
                            ImageIcon icon = new ImageIcon(usineAile.getIdentity().getIcon().get(0).getPath());
                            icon.paintIcon(this, g, usineAile.getIdentity().getPoint().x - CONSTANT_X, usineAile.getIdentity().getPoint().y - CONSTANT_Y);
                        } else if(usine instanceof Entrepot){
                            Entrepot entrepot = (Entrepot)usine;
                            ImageIcon icon = new ImageIcon(entrepot.getIdentity().getIcon().get(0).getPath());
                            icon.paintIcon(this, g, entrepot.getIdentity().getPoint().x - CONSTANT_X, entrepot.getIdentity().getPoint().y - CONSTANT_Y);
                        }
             }
         }
         
         private ArrayList<Point[]> getPointUsinesOfEachChemin(ArrayList<Chemin> chemins, ArrayList<Usine> usines){
             ArrayList<Point[]> PtUsinesChemin = new ArrayList<Point[]>();
             for(Chemin chemin : chemins){
                 PtUsinesChemin.add(chemin.getPointUsinesChemin(usines));
             }
             return PtUsinesChemin;
         }
         
         private void drawChemin(Graphics g, ArrayList<Chemin> chemins, ArrayList<Usine> usines){
             g.setColor(Color.black);
             for(Point[] tabPointUsine: this.getPointUsinesOfEachChemin(chemins, usines)){
                 g.drawLine(tabPointUsine[0].x, tabPointUsine[0].y, tabPointUsine[1].x, tabPointUsine[1].y);
             }
         }
         
         private Point getPositionComposantSortie(Composant composant){
              if(composant instanceof ComposantAile){
                 return ((ComposantAile)composant).getPosition();
             }else if(composant instanceof ComposantMetal){
                 return ((ComposantMetal)composant).getPosition();
             }else if(composant instanceof ComposantAvion){
                  return ((ComposantAvion)composant).getPosition();
             }else if(composant instanceof ComposantMoteur){
                  return ((ComposantMoteur)composant).getPosition();
             }
             return null;
         }
         
         private void setPositionComposantSortie(Composant composant, Point point){
              if(composant instanceof ComposantAile){
                 ((ComposantAile)composant).setPosition(point);
             }else if(composant instanceof ComposantMetal){
                 ((ComposantMetal)composant).setPosition(point);
             }else if(composant instanceof ComposantAvion){
                  ((ComposantAvion)composant).setPosition(point);
             }else if(composant instanceof ComposantMoteur){
                  ((ComposantMoteur)composant).setPosition(point);
             }
         }
         
         private Boolean getStatutStartMoveComposantSortie(Composant composant){
             if(composant instanceof ComposantAile){
                 return ((ComposantAile)composant).getStartMove();
             }else if(composant instanceof ComposantMetal){
                 return ((ComposantMetal)composant).getStartMove();
             }else if(composant instanceof ComposantAvion){
                 return ((ComposantAvion)composant).getStartMove();
             }else if(composant instanceof ComposantMoteur){
                 return ((ComposantMoteur)composant).getStartMove();
             }
             return false;
         }
         
         private void setStatutStartMoveComposantSortie(Composant composant, Boolean status){
             if(composant instanceof ComposantAile){
                  ((ComposantAile)composant).setStartMove(status);
             }else if(composant instanceof ComposantMetal){
                ((ComposantMetal)composant).setStartMove(status);
             }else if(composant instanceof ComposantAvion){
                 ((ComposantAvion)composant).setStartMove(status);
             }else if(composant instanceof ComposantMoteur){
                 ((ComposantMoteur)composant).setStartMove(status);
             }
             
         }
         
         private String getPathIconComposant(Composant composant){
              if(composant instanceof ComposantAile){
                 return ((ComposantAile)composant).getIcon().getPath();
             }else if(composant instanceof ComposantMetal){
                 return ((ComposantMetal)composant).getIcon().getPath();
             }else if(composant instanceof ComposantAvion){
                  return ((ComposantAvion)composant).getIcon().getPath();
             }else if(composant instanceof ComposantMoteur){
                  return ((ComposantMoteur)composant).getIcon().getPath();
             }
             return null;
         }
         private void deplacerLesComposantSurLesChemin(Graphics g, ArrayList<Chemin> chemins, ArrayList<Usine> usines){
             for(Chemin chemin: chemins){
                Usine[] usineChemin = chemin.getUsinesChemin(usines);
                Composant compSortieUsineDep = this.getComposantUsine(usineChemin[0]);
                Point ptUsineDest = chemin.getPointUsinesChemin(usines)[1];
                
                this.deplacement(g, ptUsineDest, compSortieUsineDep);
             }
         }
         
         private void setIconComposant(Graphics g, String pathIcon, Point pointDep){
             ImageIcon icon = new ImageIcon(pathIcon);
             icon.paintIcon(this, g, pointDep.x - CONSTANT_X, pointDep.y - CONSTANT_Y);
         }
         
         private void deplacement(Graphics g, Point pointFin, Composant comp){
             String pathIcon = this.getPathIconComposant(comp);
             Point pointDep = this.getPositionComposantSortie(comp);
             int x = pointDep.x, y = pointDep.y;
             if(this.getStatutStartMoveComposantSortie(comp) == false){
                x = pointDep.x;
                y = pointDep.y;
                this.setPositionComposantSortie(comp, new Point(pointDep.x, pointDep.y));
             }
             
             if(this.getPositionComposantSortie(comp).x < pointFin.x &&  this.getPositionComposantSortie(comp).y == pointFin.y){
                if(!this.isCollision(this.getPositionComposantSortie(comp), pointFin)){
                   this.getPositionComposantSortie(comp).translate(vitesse.x, 0);
                   this.setIconComposant(g, pathIcon, this.getPositionComposantSortie(comp));
                }
                 
                this.setStatutStartMoveComposantSortie(comp, true);
             } else if((this.getPositionComposantSortie(comp).x > pointFin.x) && (this.getPositionComposantSortie(comp).y > pointFin.y)){
                 
                if(!this.isCollision(this.getPositionComposantSortie(comp), pointFin)){
                     
                   this.getPositionComposantSortie(comp).translate(-vitesse.x, -vitesse.y);
                   this.setIconComposant(g, pathIcon, this.getPositionComposantSortie(comp));
                }
                 
             } else if((this.getPositionComposantSortie(comp).x > pointFin.x) && (this.getPositionComposantSortie(comp).y < pointFin.y)){
                 if(!this.isCollision(this.getPositionComposantSortie(comp), pointFin)){
                     
                   this.getPositionComposantSortie(comp).translate(-vitesse.x, vitesse.y);
                   this.setIconComposant(g, pathIcon, this.getPositionComposantSortie(comp));
                }
             }
         }
         
         private Boolean isCollision(Point pointDep, Point pointFin){
             if(pointDep.x == pointFin.x && pointDep.y == pointFin.y){
                return true;
             }
             return false;
         }
         
         private Composant getComposantUsine(Usine usine){
             if(usine instanceof UsineMateriel){
                 return ((UsineMateriel)usine).getComposantSortie();
             } else if(usine instanceof UsineAile){
                 return ((UsineAile)usine).getComposantSortie();
             } else if(usine instanceof UsineMoteur){
                 return ((UsineMoteur)usine).getComposantSortie();
             } if(usine instanceof UsineAssemblage){
                 return ((UsineAssemblage)usine).getComposantSortie();
             } if(usine instanceof Entrepot){
                 return ((Entrepot)usine).getComposantSortie();
             }
             return null;
         }
         
         public static void setListUsine(ArrayList<Usine> usin){
             usines = usin;
         }
         
         




}