/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package material;

import java.awt.Point;
import java.lang.Cloneable;
/**
 *
 * @author Romy Steve
 */
public class ComposantMoteur implements Composant, Cloneable {
    
    private Boolean isCollision = false;
    static int noOfObjects = 0;
    private Point position;
    private Icon icon;
    
    public ComposantMoteur(Point pt, Icon ic){
        noOfObjects++;
        this.icon = ic;
        this.position = pt;
    }
    @Override
    public Object clone() {
        try {
            ComposantMoteur c = (ComposantMoteur) super.clone();
            c.position = (Point)this.position.clone();
            return c;
        } catch (CloneNotSupportedException e) {
            return new ComposantMoteur(this.position, this.icon);
        }
    }
      public void setIsCollision(Boolean b){
        this.isCollision = b;
    }
     public Boolean getCollision(){
         return this.isCollision;
     }
    public Icon getIcon(){
        return this.icon ;
    }
    
    public void setPosition(Point pt){
        this.position = pt;
    }
    
    public Point getPosition(){
        return this.position;
    }
    
    public void setIcon(Icon icon){
        this.icon = icon;
    }
    public void deplacer(){
        
    }
}
