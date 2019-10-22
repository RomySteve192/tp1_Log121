/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package material;

import java.awt.Point;
import java.lang.Cloneable;

/**
 * Cette classe représente le composant Aile
 * @author Romy Steve
 */
public class ComposantAile implements Composant, Cloneable {

    private Boolean isCollision = false;
    private Point position;
    private Icon icon;

    /**
     *
     * @param Point p
     * @param Icon i
     */
    public ComposantAile(Point p, Icon i) {
        this.position = p;
        this.icon = i;
    }

    @Override
    public Object clone() {
        try {
            ComposantAile c = (ComposantAile) super.clone();
            c.position = (Point) this.position.clone();
            return c;
        } catch (CloneNotSupportedException e) {
            return new ComposantAile(this.position, this.icon);
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
     * @return Icon
     */
    public Icon getIcon() {
        return this.icon;
    }

    /**
     *
     * @param Icon icon
     */
    public void setIcon(Icon icon) {
        this.icon = icon;
    }

}
