/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package material;

import java.awt.Point;
import java.lang.Cloneable;

/**
 * Cette classe représente le Composant Avion
 * @author Romy Steve
 */
public class ComposantAvion implements Composant, Cloneable {

    private Boolean isCollision = false;
    private Point position;
    private Icon icon;

    /**
     *
     * @param pt
     * @param ic
     */
    public ComposantAvion(Point pt, Icon ic) {
        this.icon = ic;
        this.position = pt;
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
     * @return
     */
    public Boolean getCollision() {
        return this.isCollision;
    }

    @Override
    public Object clone() {
        try {
            ComposantAvion c = (ComposantAvion) super.clone();
            c.position = (Point) this.position.clone();
            return c;
        } catch (CloneNotSupportedException e) {
            return new ComposantAvion(this.position, this.icon);
        }
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
