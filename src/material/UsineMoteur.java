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
public class UsineMoteur implements Usine {

    private Boolean canProduct = false;
    private Identity identity;
    private int intervalleProd;
    private ArrayList<Composant> composantsEntree = new ArrayList<Composant>();
    private ArrayList<Composant> listComposantsSortie = new ArrayList<Composant>();
    private Composant composantSortie;
    private String iconPrincipal;
    private Boolean startTimeProd = true;

    public UsineMoteur(Identity id, int intervalProd, Composant compoSortie) {
        this.identity = id;
        this.intervalleProd = intervalProd;
        this.composantSortie = compoSortie;
    }

    public ArrayList<Composant> getlistComposantSorti() {
        return this.listComposantsSortie;
    }

    public void setlistComposantSorti(Composant comp) {
        this.listComposantsSortie.add(comp);
    }

    public Boolean getCanProduct() {
        return this.canProduct;
    }

    public void setCanProduct(Boolean b) {
        this.canProduct = b;
    }

    public ArrayList<Composant> getComposantEntres() {
        return this.composantsEntree;
    }

    public void setComposantEntres(Composant comp) {
        if (this.composantsEntree.size() == 4) {
            System.out.println("allo");
        }
        this.composantsEntree.add(comp);
    }

    public String getIconPrincipal() {
        return this.iconPrincipal;
    }

    public void setIconPrincipal(String ic) {
        this.iconPrincipal = ic;
    }

    public int getIntervalProd() {
        return this.intervalleProd;
    }

    public void setIdentity(Identity iden) {
        this.identity = iden;
    }

    public Composant getComposantSortie() {
        return this.composantSortie;
    }

    public Identity getIdentity() {
        return this.identity;
    }

    public Boolean getStartTimeProd() {
        return this.startTimeProd;
    }

    public void updateStateUsine() {
        this.canProduct = false;
        this.startTimeProd = false;

    }

}
