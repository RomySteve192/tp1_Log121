/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package material;

import java.awt.Point;
import java.lang.Cloneable;

/**
 * Cette classe représente le composant Moteur
 * @author Romy Steve
 */
public class ComposantMoteur implements Composant, Cloneable {

    private Boolean isCollision = false;
    private Point position;
    private Icon icon;

    /**
     *
     * @param pt
     * @param ic
     */
    public ComposantMoteur(Point pt, Icon ic) {
        this.icon = ic;
        this.position = pt;
    }

    @Override
    public Object clone() {
        try {
            ComposantMoteur c = (ComposantMoteur) super.clone();
            c.position = (Point) this.position.clone();
            return c;
        } catch (CloneNotSupportedException e) {
            return new ComposantMoteur(this.position, this.icon);
        }
    }

    /**
     *
     * @param b
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
     * @param pt
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
     * @param icon
     */
    public void setIcon(Icon icon) {
        this.icon = icon;
    }
}
