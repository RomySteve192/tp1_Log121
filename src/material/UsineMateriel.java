/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package material;

import java.util.ArrayList;

/**
 * Cette classe permet de représenter l'usine de production des métaux
 *
 * @author Romy Steve
 */
public class UsineMateriel implements Usine {

    private Identity identity;
    private int intervalleProd;
    private ArrayList<Composant> composantsEntree = new ArrayList<Composant>();
    private ArrayList<Composant> listComposantsSortie = new ArrayList<Composant>();
    private Composant composantSortie;
    private String iconPrincipal;
    private Boolean canProduct = false;
    private Boolean startTimeProd = true;

    /**
     *
     * @param Identity id
     * @param int intervalProd
     * @param Composant compoSortie
     */
    public UsineMateriel(Identity id, int intervalProd, Composant compoSortie) {
        this.identity = id;
        this.intervalleProd = intervalProd;
        this.composantSortie = compoSortie;
    }

    /**
     *
     * @return Boolean
     */
    public Boolean getCanProduct() {
        return this.canProduct;
    }

    /**
     * obtenir la liste des composants de sorti
     *
     * @return ArrayList<Composant>
     */
    public ArrayList<Composant> getlistComposantSorti() {
        return this.listComposantsSortie;
    }

    /**
     * ajoute le composant à la liste des composants de sortie
     *
     * @param Composant comp
     */
    public void setlistComposantSorti(Composant comp) {
        this.listComposantsSortie.add(comp);
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
     * ajouter le composant à la liste des composants d'entrées
     *
     * @param comp
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
     * obtenir le composant de sortie
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
     * verifie si la production à déjà commencé à l'usine
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
