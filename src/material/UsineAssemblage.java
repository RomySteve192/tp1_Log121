/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package material;

import java.util.ArrayList;

/**
 * Cette classe permet de décrire l'usine d'assemeblage
 *
 * @author Romy Steve
 */
public class UsineAssemblage implements Usine {

    private Boolean canProduct = false;
    private Identity identity;
    private int intervalleProd;
    private ArrayList<Composant> composantsEntree = new ArrayList<Composant>();
    private ArrayList<Composant> listComposantsSortie = new ArrayList<Composant>();
    private Composant composantSortie;
    private String iconPrincipal;
    private Boolean startTimeProd = true;

    /**
     *
     * @param Identity id
     * @param int intervalProd
     * @param Composant compoSortie
     */
    public UsineAssemblage(Identity id, int intervalProd, Composant compoSortie) {
        this.identity = id;
        this.intervalleProd = intervalProd;
        this.composantSortie = compoSortie;
    }

    /**
     *
     * @return ArrayList<Composant>
     */
    public ArrayList<Composant> getlistComposantSorti() {
        return this.listComposantsSortie;
    }

    /**
     *
     * @param Composant comp
     */
    public void setlistComposantSorti(Composant comp) {
        this.listComposantsSortie.add(comp);
    }

    /**
     * obtenir la liste des composnts de sortie
     *
     * @return ArrayList<Composant>
     */
    public ArrayList<Composant> getComposantEntres() {
        return this.composantsEntree;
    }

    /**
     *
     * @return Boolean
     */
    public Boolean getCanProduct() {
        return this.canProduct;
    }

    /**
     *
     * @param Boolean b
     */
    public void setCanProduct(Boolean b) {
        this.canProduct = b;
    }

    /**
     *
     * @param Composant comp
     */
    public void setComposantEntres(Composant comp) {
        this.composantsEntree.add(comp);
    }

    /**
     *
     * @return String
     */
    public String getIconPrincipal() {
        return this.iconPrincipal;
    }

    /**
     *
     * @param String ic
     */
    public void setIconPrincipal(String ic) {
        this.iconPrincipal = ic;
    }

    /**
     *
     * @return Identity
     */
    public Identity getIdentity() {
        return this.identity;
    }

    /**
     *
     * @return int
     */
    public int getIntervalProd() {
        return this.intervalleProd;
    }

    /**
     * obtenir le composant de sortie
     *
     * @return Composant
     */
    public Composant getComposantSortie() {
        return this.composantSortie;
    }

    /**
     *
     * @param Identity iden
     */
    public void setIdentity(Identity iden) {
        this.identity = iden;
    }

    /**
     *
     * @return Boolean
     */
    public Boolean getStartTimeProd() {
        return this.startTimeProd;
    }

    /**
     * mettre à jour l'état de production de l'usine
     *
     * @param Boolean flag
     */
    public void updateStateUsine(Boolean flag) {
        this.canProduct = flag;
        this.startTimeProd = flag;

    }

}
