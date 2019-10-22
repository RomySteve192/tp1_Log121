/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package material;

/**
 * Cette classe représente les différentes icons utilisés par les objets du
 * réseau
 *
 * @author Romy Steve
 */
public class Icon {

    private String type;
    private String path;

    /**
     *
     * @param String typ
     * @param String pat
     */
    public Icon(String typ, String pat) {
        this.type = typ;
        this.path = pat;
    }

    /**
     *
     * @return String
     */
    public String getPath() {
        return this.path;
    }

    /**
     *
     * @param pat
     */
    public void setPath(String pat) {
        this.path = pat;
    }

    /**
     *
     * @return String
     */
    public String getType() {
        return this.type;
    }

    /**
     *
     * @param String typ
     */
    public void setType(String typ) {
        this.type = typ;
    }

}
