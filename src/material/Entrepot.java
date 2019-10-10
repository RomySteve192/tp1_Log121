/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package material;

import java.util.ArrayList;

/**
 *
 * @author Romy Steve
 */
public class Entrepot implements Usine {

    private Identity identity;
    private int capacity = 5;
    private ArrayList<Composant> composantsEntree = new ArrayList<Composant>();
    private Composant composantSortie = null;
    private String iconPrincipal;

    private ArrayList<Usine> listUsine = new ArrayList<Usine>();

    public Entrepot(Identity id, int cap) {
        this.identity = id;
        this.capacity = cap;
    }

    public ArrayList<Composant> getComposantEntres() {
        return this.composantsEntree;
    }

    public void setComposantEntres(Composant comp) {
        this.composantsEntree.add(comp);
    }

    public String getIconPrincipal() {
        return this.iconPrincipal;
    }

    public void setIconPrincipal(String ic) {
        this.iconPrincipal = ic;
    }

    public void updateStateUsine() {
    }

    public Boolean getStateEntrepot() {
        return this.composantsEntree.size() == this.capacity;
    }

    public void notifyUsine() {
        for (Usine usine : this.listUsine) {
            if (usine instanceof UsineMateriel) {
                ((UsineMateriel) usine).updateStateUsine();
            } else if (usine instanceof UsineAile) {
                ((UsineAile) usine).updateStateUsine();
            } else if (usine instanceof UsineMoteur) {
                ((UsineMoteur) usine).updateStateUsine();
            } else if (usine instanceof UsineAssemblage) {
                ((UsineAssemblage) usine).updateStateUsine();
            }
        }
    }

    public ArrayList<Usine> getListUsine() {
        return this.listUsine;
    }

    public void setListUsine(Usine usine) {
        this.listUsine.add(usine);
    }

    public Composant getComposantSortie() {
        return this.composantSortie;
    }

    public int getCapacity() {
        return this.capacity;
    }

    public Identity getIdentity() {
        return this.identity;
    }

    public void setIdentity(Identity iden) {
        this.identity = iden;
    }

}
