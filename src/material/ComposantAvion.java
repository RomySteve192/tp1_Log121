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
public class ComposantAvion implements Composant {
    
   
    static int noOfObjects = 0; 
    private Point position;
    private Icon icon;
    
    public ComposantAvion(Point pt, Icon ic){
        noOfObjects++;
        this.icon = ic;
        this.position = pt;
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
