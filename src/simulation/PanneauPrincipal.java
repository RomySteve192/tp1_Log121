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
        private static final int CONSTANT_X = 5;
        private static final int CONSTANT_Y = 15;
	private Point position = new Point(0,0);
	private Point vitesse = new Point(1,1);
        private Boolean isMove = false;
	private int taille = 32;
        private Point posComp;
	
	@Override
        // @override
         public void paintComponent(Graphics g){
             
               super.paintComponent(g);
		// On ajoute à la position le delta x et y de la vitesse
		position.translate(vitesse.x, vitesse.y);
		g.fillRect(position.x, position.y, taille, taille);
                
                /*ImageIcon im = new ImageIcon("src/ressources/UMP0%.png");
                im.paintIcon(this, g, 10, 10);*/
              
             
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
         
         private Point getPointComposant(Composant composant){
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
                String pathIcon = this.getPathIconComposant(compSortieUsineDep);
                Point ptCompSortieUsineDep = this.getPointComposant(compSortieUsineDep);
                Point ptUsineDest = chemin.getPointUsinesChemin(usines)[1];
                
                this.deplacement(g, ptCompSortieUsineDep, ptUsineDest, pathIcon);
                //ImageIcon icon = new ImageIcon(usineMat.getIdentity().getIcon().get(0).getPath());
                //icon.paintIcon(this, g, usineMat.getIdentity().getPoint().x - CONSTANT_X, usineMat.getIdentity().getPoint().y - CONSTANT_Y);
             }
         }
         
         private void setIconComposant(Graphics g, String pathIcon, Point pointDep){
             ImageIcon icon = new ImageIcon(pathIcon);
             icon.paintIcon(this, g, pointDep.x, pointDep.y - CONSTANT_Y);
         }
         
         private void deplacement(Graphics g, Point pointDep, Point pointFin, String pathIcon){
             int x, y;
             if(this.isMove == false){
                x = pointDep.x;
                y = pointDep.y;
                this.posComp = new Point(pointDep.x, pointDep.y);
             }
            // Point pointIni = new Point(x, y);
             if( this.posComp.x < pointFin.x &&  this.posComp.y == pointFin.y){
                 if(!this.isCollision(this.posComp, pointFin)){
                     this.posComp.translate(vitesse.x, 0);
                     this.setIconComposant(g, pathIcon, this.posComp);
                 }
                 this.isMove = true;
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
         
         /*private Boolean Collision(Point point, Composant composant){
             if(composant instanceof ComposantAile){
                 ComposantAile compAile = (ComposantAile)composant;
                 return (point.x == compAile.getIdentity().getPoint().x) && (point.y == compAile.getIdentity().getPoint().y);
             }else if(composant instanceof ComposantMetal){
                 ComposantMetal compMetal = (ComposantMetal)composant;
                 return (point.x == compMetal.getIdentity().getPoint().x) && (point.y == compMetal.getIdentity().getPoint().y);
             }else if(composant instanceof ComposantAvion){
                  ComposantAvion compAvion = (ComposantAvion)composant;
                  return (point.x == compAvion.getIdentity().getPoint().x) && (point.y == compAvion.getIdentity().getPoint().y);
             }else if(composant instanceof ComposantMoteur){
                  ComposantMoteur compMoteur = (ComposantMoteur)composant;
                  return (point.x == compMoteur.getIdentity().getPoint().x) && (point.y == compMoteur.getIdentity().getPoint().y);
             }
             return false;
         }
         private Boolean isCollision(Usine usine, Composant composant){
            if(usine instanceof UsineMateriel){
                UsineMateriel usineMat = (UsineMateriel)usine;
                return this.Collision(usineMat.getIdentity().getPoint(), composant);
                
            }else if(usine instanceof UsineMoteur){
                UsineMoteur usineMo = (UsineMoteur)usine;
                return this.Collision(usineMo.getIdentity().getPoint(), composant);
            }else if(usine instanceof UsineAile){
                UsineAile usineAile = (UsineAile)usine;
                return this.Collision(usineAile.getIdentity().getPoint(), composant);
            }else if(usine instanceof UsineAssemblage){
                UsineAssemblage usineAss = (UsineAssemblage)usine;
                return this.Collision(usineAss.getIdentity().getPoint(), composant);
            }
             return false;
         }*/
         
         




}