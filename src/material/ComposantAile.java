/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package material;

import java.awt.Point;

/**
 *
 * @author Romy Steve
 */
public class ComposantAile implements Composant {
    
    private Boolean startMove = false;
    static int noOfObjects = 0; 
    private Point position;
    private Icon icon;
    
    public ComposantAile(Point p, Icon i){
        noOfObjects++;
        this.position = p;
        this.icon = i;
    }
    
     public void setStartMove(Point pt){
        this.position = pt;
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
    
     public void setStartMove(Boolean s){
        this.startMove = s;
    }
    
    public Boolean getStartMove(){
        return this.startMove;
    }
    
    
    public void setIcon(Icon icon){
        this.icon = icon;
    }
    
    public void deplacer(){
        
    }
}
