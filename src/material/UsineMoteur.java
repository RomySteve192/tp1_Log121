/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package material;

import java.util.ArrayList;

/**
 * Cette classe représente l'usine de production des moteurs
 *
 * @author Romy Steve
 */
public class UsineMoteur implements Usine {

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
    public UsineMoteur(Identity id, int intervalProd, Composant compoSortie) {
        this.identity = id;
        this.intervalleProd = intervalProd;
        this.composantSortie = compoSortie;
    }

    /**
     * obtenir la liste des composants de sortie
     *
     * @return ArrayList<Composant>
     */
    public ArrayList<Composant> getlistComposantSorti() {
        return this.listComposantsSortie;
    }

    /**
     * ajouter un composnt dans la liste des composants de sortie
     *
     * @param Composant comp
     */
    public void setlistComposantSorti(Composant comp) {
        this.listComposantsSortie.add(comp);
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
     * @return ArrayList<Composant>
     */
    public ArrayList<Composant> getComposantEntres() {
        return this.composantsEntree;
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
     * @return int
     */
    public int getIntervalProd() {
        return this.intervalleProd;
    }

    /**
     *
     * @param Identity iden
     */
    public void setIdentity(Identity iden) {
        this.identity = iden;
    }

    /**
     * obtenir le composant de sortie de l'usine
     *
     * @return Composant
     */
    public Composant getComposantSortie() {
        return this.composantSortie;
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
