package com.brpi.projet_motsfleches1.Modele;

import java.util.ArrayList;

import com.brpi.projet_motsfleches1.Vue.I_Observateur;

/**
 * Classe en charge de la grilleVirtuelle
 */
public class Grille implements I_modeleGrille {
    private final int hauteur;
    private final int largeur;
    public Case[][] tabGrille;
    private ArrayList<I_Observateur> tableauObservateurs;

    public Case[][] getTabGrille() {
        return tabGrille;
    }

    @Override
    public void addObservateur(I_Observateur o) {
        tableauObservateurs.add(o);
    }

    @Override
    public void removeObservateur(I_Observateur o) {
        tableauObservateurs.remove(o);
    }

    @Override
    public void notifyObservateur() {
        for (I_Observateur o :tableauObservateurs) {
            o.update(this);
        }
    }

    public int getHauteur() {
        return hauteur;
    }

    public int getLargeur() {
        return largeur;
    }

    /**
     * Constructeur de la grille
     * @param hauteur
     * @param largeur
     */
    public Grille(int hauteur, int largeur) {
        super();
        this.hauteur = hauteur;
        this.largeur = largeur;
        tabGrille = new Case[largeur][hauteur];
        initTableau();
        tableauObservateurs = new ArrayList<>();
    }

    /**
     * Initialise le tableau de cases
     */
    private void initTableau(){
        for (int h = 0; h < hauteur; h++) {
            for (int l = 0; l < largeur; l++) {
                System.out.print("");
            }
        }
    }

    /**
     * Affiche la grille en mode console
     */
    public void afficherGrille(){
        for (int h = 0; h < largeur; h++) {
            for (int l = 0; l < hauteur; l++) {
                if(tabGrille[h][l] != null) {
                    System.out.print(tabGrille[h][l]);
                }
                else {
                    System.out.print('.');
                }
            }
            System.out.println();
        }
    }

    /**
     * Ajoute un mot (CaseLettre)
     * @param mot
     * @param x
     * @param y
     * @param direction
     * @return Retourne un booleen en fonction du résultat
     */
    public boolean ajouterMot(String mot, int x, int y, String direction){
        if(mot.length() > findCaseDispo(x, y, direction)){
            return false;
        }
        switch (direction) {
            // Horizontal (x) = gauche / droite | Vertical (y) = haut / bas
            // Si la direction choisie est verticale
            case "VD":
                    for (int i = 0; i < mot.length(); i++) {
                        tabGrille[x][y + i] = new CaseLettre(mot.charAt(i));
                        System.out.println(getValueCase(x, y + i));
                    }
                break;
            case "VI":
                    for (int i = mot.length() - 1; i >= 0; i--) {
                        System.out.println("dfgdfgf");
                        tabGrille[x][y - i] = new CaseLettre(mot.charAt(i));
                        System.out.println(getValueCase(x, y - i));
                    }
                break;
            // Sinon c'est horizontal
            case "HD":
                    for (int i = 0; i < mot.length(); i++) {
                        tabGrille[x + i][y] = new CaseLettre(mot.charAt(i));
                        System.out.println(getValueCase(x + i, y));
                    }
                break;
            default :
                    for (int i = mot.length() - 1; i >= 0; i--) {
                        tabGrille[x - i][y] = new CaseLettre(mot.charAt(i));
                        System.out.println(getValueCase(x- i, y));
                    }
        }
        notifyObservateur();
        return true;
    }

    /**
     * Ajoute une definition
     * @param x
     * @param y
     * @param libDef
     * @return Retourne un booleen en fonction du résultat
     */
    public boolean ajouterDef(int x, int y, String libDef){
        if(!(tabGrille[x][y] instanceof CaseLettre)) {
            tabGrille[x][y] = new CaseDef(x, y, libDef);
            notifyObservateur();
            return true;
        }
        return false;
    }

    /**
     * Supprime une définition
     * @param x
     * @param y
     */
    public void supprimerDef(int x, int y){
        if (findCaseDef(x, y)){
            tabGrille[x][y] = null;
        }
    }

    /**
     * Trouve une case de type CaseDef
     * @param x
     * @param y
     * @return Retourne un booleen en fonction du type de la case
     */
    public boolean findCaseDef(int x, int y){
        if (tabGrille[x][y] instanceof CaseDef) {
            return true;
        }
        return false;
    }

    /**
     * Trouve le nombre de cases dispo pour la pos et la direction indiquée
     * @param x
     * @param y
     * @param direction
     * @return Un nombre de cases dispo
     */
    public int findCaseDispo(int x, int y, String direction){
        int nbCasesDispo = 0;
        switch (direction){
            case "VD":
                nbCasesDispo = (hauteur - 1) - y;
                return nbCasesDispo;
            case "VI":
                nbCasesDispo = y;
                return nbCasesDispo;
            case "HD":
                nbCasesDispo = (largeur - 1) - x;
                return nbCasesDispo;
            default:
                nbCasesDispo = x;
                return nbCasesDispo;
        }
    }

    /**
     * Regarde si il y a un croisement de mots
     * @param mot
     * @param x
     * @param y
     * @param direction
     * @return Retourne un booleen en fonction du résultat
     */
    public boolean getMotCroisement(String mot, int x, int y, String direction){
        // Si la direction choisie est horizontale
        boolean isCroisementOK = true;
        if (direction.equals("H")){
            for (int i = 0; i < mot.length(); i++) {
                if(tabGrille[i][y+x] == null){
                    isCroisementOK = false;
                    break;
                }
            }
            return isCroisementOK;
        }
        // Sinon c'est à la verticale
        for (int i = 0; i < mot.length(); i++) {
            if(tabGrille[i+x][y] == null){
                isCroisementOK = false;
                break;
            }
        }
        return isCroisementOK;
    }

    /**
     *
     * @param x
     * @param y
     * @return Retourne un booleen en fonction du résultat
     */
    public String getValueCase(int x, int y){
        if(tabGrille[x][y] instanceof CaseLettre || tabGrille[x][y] instanceof CaseDef) {
            return (tabGrille[x][y]).getLibelle();
        }
        else{
            return " ";
        }
    }
}
