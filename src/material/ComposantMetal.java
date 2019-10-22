/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package material;

import java.awt.Point;
import java.lang.Cloneable;

/**
 * Cette classe représente le Composant métal
 * @author Romy Steve
 */
public class ComposantMetal implements Composant, Cloneable {

    private Boolean isCollision = false;
    private Point position;
    private Icon icon;

    /**
     *
     * @param Point pt
     * @param Icon ic
     */
    public ComposantMetal(Point pt, Icon ic) {
        this.icon = ic;
        this.position = pt;
    }

    @Override
    public Object clone() {
        try {
            ComposantMetal c = (ComposantMetal) super.clone();
            c.position = (Point) this.position.clone();
            return c;
        } catch (CloneNotSupportedException e) {
            return new ComposantMetal(this.position, this.icon);
        }
    }

    /**
     *
     * @param Boolean b
     */
    public void setIsCollision(Boolean b) {
        this.isCollision = b;
    }

    /**
     *
     * @return Boolean
     */
    public Boolean getCollision() {
        return this.isCollision;
    }

    /**
     *
     * @return Icon
     */
    public Icon getIcon() {
        return this.icon;
    }

    /**
     *
     * @param Point pt
     */
    public void setPosition(Point pt) {
        this.position = pt;
    }

    /**
     *
     * @return Point
     */
    public Point getPosition() {
        return this.position;
    }

    /**
     *
     * @param Icon icon
     */
    public void setIcon(Icon icon) {
        this.icon = icon;
    }
}
