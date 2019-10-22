/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package material;

import java.util.ArrayList;

/**
 * Cette classe représente l'entrepot
 *
 * @author Romy Steve
 */
public class Entrepot implements Usine {

    private Identity identity;
    private int capacity = 5;
    private static ArrayList<Composant> composantsEntree = new ArrayList<Composant>();
    private Composant composantSortie = null;
    private String iconPrincipal;
    private ArrayList<Usine> listUsine = new ArrayList<Usine>();

    /**
     *
     * @param Identity id
     * @param int cap
     */
    public Entrepot(Identity id, int cap) {
        this.identity = id;
        this.capacity = cap;
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
     * retourne le chemin de l'icon
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
     * @param Boolean flag
     */
    public void updateStateUsine(Boolean flag) {
    }

    /**
     * retourne l'état de l'entrepot si True, l'entrepot est plein si False
     * l'entrepot n'est pas plein
     *
     * @return Boolean
     */
    public Boolean getStateEntrepot() {
        return this.composantsEntree.size() == this.capacity;
    }

    /**
     * notify l'état de l'entrepot aux autres usines
     *
     * @param Boolean flag
     */
    public void notifyUsine(Boolean flag) {
        for (Usine usine : this.listUsine) {
            if (usine instanceof UsineMateriel) {
                ((UsineMateriel) usine).updateStateUsine(flag);
            } else if (usine instanceof UsineAile) {
                ((UsineAile) usine).updateStateUsine(flag);
            } else if (usine instanceof UsineMoteur) {
                ((UsineMoteur) usine).updateStateUsine(flag);
            } else if (usine instanceof UsineAssemblage) {
                ((UsineAssemblage) usine).updateStateUsine(flag);
            }
        }
    }

    /**
     *
     * @return ArrayList<Usine>
     */
    public ArrayList<Usine> getListUsine() {
        return this.listUsine;
    }

    /**
     *
     * @param Usine usine
     */
    public void setListUsine(Usine usine) {
        this.listUsine.add(usine);
    }

    /**
     *
     * @return
     */
    public Composant getComposantSortie() {
        return this.composantSortie;
    }

    /**
     *
     * @return
     */
    public int getCapacity() {
        return this.capacity;
    }

    /**
     *
     * @return
     */
    public Identity getIdentity() {
        return this.identity;
    }

    /**
     *
     * @param iden
     */
    public void setIdentity(Identity iden) {
        this.identity = iden;
    }

}
