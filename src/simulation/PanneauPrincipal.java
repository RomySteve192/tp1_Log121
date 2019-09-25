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
        private static final int CONSTANT_Y = 10;
	private Point position = new Point(0,0);
	private Point vitesse = new Point(1,1);
	private int taille = 32;
	
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
         
         private Boolean Collision(Point point, Composant composant){
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
         }
         
         




}