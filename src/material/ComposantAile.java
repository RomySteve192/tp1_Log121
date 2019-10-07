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
public class ComposantAile implements Composant, Cloneable {
    
    private Boolean isCollision = false;
    static int noOfObjects = 0; 
    private Point position;
    private Icon icon;
   // private Boolean isCollision = 
    
    public ComposantAile(Point p, Icon i){
        noOfObjects++;
        this.position = p;
        this.icon = i;
    }
    @Override
    public Object clone() {
        try {
            ComposantAile c = (ComposantAile) super.clone();
            c.position = (Point)this.position.clone();
            return c;
        } catch (CloneNotSupportedException e) {
            return new ComposantAile(this.position, this.icon);
        }
    }
     public void setIsCollision(Boolean b){
        this.isCollision = b;
    }
     public Boolean getCollision(){
         return this.isCollision;
     }
     
     public void setPosition(Point pt){
        this.position = pt;
    }
    public Point getPosition(){
        return this.position;
    }
    
    public Icon getIcon(){
        return this.icon ;
    }
    
    
    
    
    public void setIcon(Icon icon){
        this.icon = icon;
    }
    
    public void deplacer(){
        
    }
}
