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
                            ImageIcon icon = new ImageIcon(usineMat.getIconPrincipal());
                            icon.paintIcon(this, g, usineMat.getIdentity().getPoint().x - CONSTANT_X, usineMat.getIdentity().getPoint().y - CONSTANT_Y);
                        } else if (usine instanceof UsineMoteur){
                            UsineMoteur usineMo = (UsineMoteur)usine;
                            ImageIcon icon = new ImageIcon(usineMo.getIconPrincipal());
                            icon.paintIcon(this, g, usineMo.getIdentity().getPoint().x - CONSTANT_X, usineMo.getIdentity().getPoint().y - CONSTANT_Y);
                        } else if (usine instanceof UsineAssemblage){
                            UsineAssemblage usineAss = (UsineAssemblage)usine;
                            ImageIcon icon = new ImageIcon(usineAss.getIconPrincipal());
                            icon.paintIcon(this, g, usineAss.getIdentity().getPoint().x - CONSTANT_X, usineAss.getIdentity().getPoint().y - CONSTANT_Y);
                        } else if (usine instanceof UsineAile){
                            UsineAile usineAile = (UsineAile)usine;
                            ImageIcon icon = new ImageIcon(usineAile.getIconPrincipal());
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
         
         private void setStatutStartMoveComposantSortie(Usine usine, Composant composant, Boolean status){
             
             if(usine instanceof UsineMateriel){
                    this.setStatus(composant, status);
                } else if(usine instanceof UsineAile){
                    this.setStatus(composant, status);
                } else if(usine instanceof UsineMoteur){
                    this.setStatus(composant, status);
                } if(usine instanceof UsineAssemblage){
                    this.setStatus(composant, status);
                }
               // this.reinitializePosComp(usine, composant);
             
             
             
             
         }
         
         private void setStatus(Composant composant, Boolean status){
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
                
                this.deplacement(g, ptUsineDest, usineChemin[1],usineChemin[0], compSortieUsineDep);
             }
         }
         
         private void setIconComposant(Graphics g, String pathIcon, Point pointDep){
             ImageIcon icon = new ImageIcon(pathIcon);
             icon.paintIcon(this, g, pointDep.x - CONSTANT_X, pointDep.y - CONSTANT_Y);
         }
         
         private void addComposantEntreUsineDest(Usine usineDest, Composant comp){
             if(usineDest instanceof UsineAile){
                 ((UsineAile)usineDest).setComposantEntres(comp);
             } else if(usineDest instanceof UsineMoteur){
                 ((UsineMoteur)usineDest).setComposantEntres(comp);
             } if(usineDest instanceof UsineAssemblage){
                 ((UsineAssemblage)usineDest).setComposantEntres(comp);
             } if(usineDest instanceof Entrepot){
                 ((Entrepot)usineDest).setComposantEntres(comp);
             }
         }
         
         private void reinitializePosComp(Usine usineDep, Composant composant){
             Point p;
             if(usineDep instanceof UsineMateriel){
                 p = new Point(((UsineMateriel)usineDep).getIdentity().getPoint().x, ((UsineMateriel)usineDep).getIdentity().getPoint().y);
                  if(composant instanceof ComposantAile){
                      
                        ((ComposantAile)composant).setPosition(p);
                    }else if(composant instanceof ComposantMetal){
                        ((ComposantMetal)composant).setPosition(p);
                    }else if(composant instanceof ComposantAvion){
                         ((ComposantAvion)composant).setPosition(p);
                    }else if(composant instanceof ComposantMoteur){
                         ((ComposantMoteur)composant).setPosition(p);
                    }
             } else if(usineDep instanceof UsineAile){
                  p = new Point(((UsineAile)usineDep).getIdentity().getPoint().x, ((UsineAile)usineDep).getIdentity().getPoint().y);
                 if(composant instanceof ComposantAile){
                        ((ComposantAile)composant).setPosition(p);
                    }else if(composant instanceof ComposantMetal){
                        ((ComposantMetal)composant).setPosition(p);
                    }else if(composant instanceof ComposantAvion){
                         ((ComposantAvion)composant).setPosition(p);
                    }else if(composant instanceof ComposantMoteur){
                         ((ComposantMoteur)composant).setPosition(p);
                    }
             } else if(usineDep instanceof UsineMoteur){
                 p = new Point(((UsineMoteur)usineDep).getIdentity().getPoint().x, ((UsineMoteur)usineDep).getIdentity().getPoint().y);
                  if(composant instanceof ComposantAile){
                        ((ComposantAile)composant).setPosition(p);
                    }else if(composant instanceof ComposantMetal){
                        ((ComposantMetal)composant).setPosition(p);
                    }else if(composant instanceof ComposantAvion){
                         ((ComposantAvion)composant).setPosition(p);
                    }else if(composant instanceof ComposantMoteur){
                         ((ComposantMoteur)composant).setPosition(p);
                    }
             }else if(usineDep instanceof UsineAssemblage){
                  p = new Point(((UsineAssemblage)usineDep).getIdentity().getPoint().x, ((UsineAssemblage)usineDep).getIdentity().getPoint().y);
                 if(composant instanceof ComposantAile){
                        ((ComposantAile)composant).setPosition(p);
                    }else if(composant instanceof ComposantMetal){
                        ((ComposantMetal)composant).setPosition(p);
                    }else if(composant instanceof ComposantAvion){
                         ((ComposantAvion)composant).setPosition(p);
                    }else if(composant instanceof ComposantMoteur){
                         ((ComposantMoteur)composant).setPosition(p);
                    }
             }
         }
         private void deplacement(Graphics g, Point pointFin, Usine usineDest, Usine usineDep, Composant comp){
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
                   this.setStatutStartMoveComposantSortie(usineDep,comp, true);
                }else  if(this.isCollision(this.getPositionComposantSortie(comp), pointFin)){
                    this.addComposantEntreUsineDest(usineDest, comp);
                    this.setStatutStartMoveComposantSortie(usineDep, comp, false);
                    this.reinitializePosComp(usineDep,comp);
                }
                //this.addComposantSortieUsineDest(usineDest, comp);
                //this.setStatutStartMoveComposantSortie(comp, true);
             } else if((this.getPositionComposantSortie(comp).x > pointFin.x) && (this.getPositionComposantSortie(comp).y > pointFin.y)){
                 
                if(!this.isCollision(this.getPositionComposantSortie(comp), pointFin)){
                     
                   this.getPositionComposantSortie(comp).translate(-vitesse.x, -vitesse.y);
                   this.setIconComposant(g, pathIcon, this.getPositionComposantSortie(comp));
                    this.setStatutStartMoveComposantSortie(usineDep,comp, true);
                }else  if(this.isCollision(this.getPositionComposantSortie(comp), pointFin)){
                    this.addComposantEntreUsineDest(usineDest, comp);
                    this.setStatutStartMoveComposantSortie(usineDep,comp, false);
                     this.reinitializePosComp(usineDep,comp);
                }
             } else if((this.getPositionComposantSortie(comp).x > pointFin.x) && (this.getPositionComposantSortie(comp).y < pointFin.y)){
                 if(!this.isCollision(this.getPositionComposantSortie(comp), pointFin)){
                     
                   this.getPositionComposantSortie(comp).translate(-vitesse.x, vitesse.y);
                   this.setIconComposant(g, pathIcon, this.getPositionComposantSortie(comp));
                    this.setStatutStartMoveComposantSortie(usineDep,comp, true);
                }else if(this.isCollision(this.getPositionComposantSortie(comp), pointFin)){
                    this.addComposantEntreUsineDest(usineDest, comp);
                    this.setStatutStartMoveComposantSortie(usineDep,comp, false);
                    this.reinitializePosComp(usineDep,comp);
                }
             }
         }
         
         
         private Boolean isCollision(Point pointDep, Point pointFin){
                //calculateDistanceBetweenPoints(pointDep.x, pointDep.y, pointFin.x,pointFin.y) 
             if(this.calculateDistanceBetweenPoints(pointDep.x, pointDep.y, pointFin.x,pointFin.y) <= 3){
                return true;
             }
             return false;
         }
         public double calculateDistanceBetweenPoints(double x1, double y1, double x2, double y2) {       
                return Math.sqrt((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1));
        }
         
         private Composant getComposantUsine(Usine usine){
             if(usine instanceof UsineMateriel){
                 return ((UsineMateriel)usine).getComposantSortie();
             } else if(usine instanceof UsineAile){
                 return ((UsineAile)usine).getComposantSortie();
             } else if(usine instanceof UsineMoteur){
                 return ((UsineMoteur)usine).getComposantSortie();
             } else if(usine instanceof UsineAssemblage){
                 return ((UsineAssemblage)usine).getComposantSortie();
             } else if(usine instanceof Entrepot){
                 return ((Entrepot)usine).getComposantSortie();
             }
             return null;
         }
         
         public static void setListUsine(ArrayList<Usine> usin){
             usines = usin;
         }
         
         




}