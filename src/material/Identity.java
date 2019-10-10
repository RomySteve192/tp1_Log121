/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package material;

import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author Romy Steve
 */
public class Identity {

    private String type;
    private String id;
    private Point point = new Point(0, 0);
    private ArrayList<Icon> icons;

    public Identity(String t, String i, Point p, ArrayList<Icon> ic) {
        this.type = t;
        this.id = i;
        this.point = p;
        this.icons = ic;
    }

    public Point getPoint() {
        return this.point;
    }

    public void setPoint(Point p) {
        this.point = p;
    }

    public String getId() {
        return this.id;
    }

    public String getType() {
        return this.type;
    }

    public ArrayList<Icon> getIcon() {
        return this.icons;
    }

    public void setIcon(ArrayList<Icon> ic) {
        this.icons = ic;
    }

}
