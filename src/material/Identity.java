/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package material;

import java.awt.Point;
import java.util.ArrayList;

/**
 * Cette classe permet d'identifie une usine en regroupant
 * les caractéristiques communes
 * @author Romy Steve
 */
public class Identity {

    private String type;
    private String id;
    private Point point = new Point(0, 0);
    private ArrayList<Icon> icons;

    /**
     *
     * @param String t
     * @param String i
     * @param Point p
     * @param ArrayList<Icon> ic
     */
    public Identity(String t, String i, Point p, ArrayList<Icon> ic) {
        this.type = t;
        this.id = i;
        this.point = p;
        this.icons = ic;
    }

    /**
     *
     * @return Point
     */
    public Point getPoint() {
        return this.point;
    }

    /**
     *
     * @param Point p
     */
    public void setPoint(Point p) {
        this.point = p;
    }

    /**
     * Identification unique de l'usine sur le réseau
     * @return String
     */
    public String getId() {
        return this.id;
    }

    /**
     *
     * @return String
     */
    public String getType() {
        return this.type;
    }

    /**
     * Obtenir les Icons associés à l' usine
     * @return ArrayList<Icon>
     */
    public ArrayList<Icon> getIcon() {
        return this.icons;
    }

    /**
     * mettre les différents Icons associés aux Usines
     * @param ArrayList<Icon> ic
     */
    public void setIcon(ArrayList<Icon> ic) {
        this.icons = ic;
    }

}
