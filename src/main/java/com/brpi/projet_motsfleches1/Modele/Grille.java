package com.brpi.projet_motsfleches1.Modele;

import java.lang.reflect.Array;
import java.util.ArrayList;

import com.brpi.projet_motsfleches1.Vue.I_Observateur;

public class Grille implements I_modeleGrille {
    private final int hauteur;
    private final int largeur;
    public char[][] tabGrille;
    private ArrayList<CaseDef> CasesDefs = new ArrayList<CaseDef>();
    private ArrayList<I_Observateur> tableauObservateurs;

    // Getters & Setters
    public char[][] getTabGrille() {
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

    // Constructeur
    public Grille(int hauteur, int largeur) {
        super();
        this.hauteur = hauteur;
        this.largeur = largeur;
        tabGrille = new char [largeur][hauteur];
        initTableau();
        tableauObservateurs = new ArrayList<>();
    }

    // Méthodes grilles
    private void initTableau(){
        for (int h = 0; h < hauteur; h++) {
            for (int l = 0; l < largeur; l++) {
                tabGrille[l][h] = ' ';
            }
        }
    }

    public void afficherGrille(){
        for (int h = 0; h < largeur; h++) {
            for (int l = 0; l < hauteur; l++) {
                if(tabGrille[h][l] != ' ') {
                    System.out.print(tabGrille[h][l]);
                }
                else {
                    System.out.print(' ');
                }
            }
            System.out.println();
        }
    }

    public boolean ajouterMot(String mot, int x, int y, String direction){
        // A préciser
        if(mot.length() > largeur){
            System.out.println("Le mot ajouté est trop long.");
            return false;
        }
            // Horizontal (x) = gauche / droite | Vertical (y) = haut / bas
            // Si la direction choisie est verticale
            if (direction.equals("V")){
                for (int i = 0; i < mot.length(); i++) {
                    tabGrille[i+x][y] = mot.charAt(i);
                }
            }
            else {
                // Sinon c'est horizontal
                for (int i = 0; i < mot.length(); i++) {
                    tabGrille[x][y+i] = mot.charAt(i);
                }
            }
            notifyObservateur();
        return true;
    }

    // Gestion des définitions
    public void ajouterDef(int x, int y, String libDef){
        CaseDef caseDef = new CaseDef(x, y, libDef);
        CasesDefs.add(caseDef);
    }

    // A refaire
    public void supprimerDef(int x, int y){
        CasesDefs.forEach((caseDef) -> {
            if(caseDef.positionX == x && caseDef.positionY == y){
                CasesDefs.remove(caseDef);
            }
        });
    }
    public CaseDef findCaseDef(int x, int y){
        for(CaseDef caseDef : CasesDefs) {
            if (caseDef.positionX == x && caseDef.positionY == y) {
                return caseDef;
            }
        }
        return null;
    }

    public int findCaseDispo(int x, int y, String direction){
        int cptDispo = 0;
        // Si la direction choisie est horizontale (gauche --> droite)
        if(direction.equals("H")){
            do {
                y++;
                cptDispo++;
            }
            while(y < hauteur && findCaseDef(x, y) == null);
            return cptDispo;
        }
        // Sinon c'est vertical (Haut --> bas)
        else{
            do {
                x++;
                cptDispo++;
            }
            while(x < largeur && findCaseDef(x, y) == null);
            return cptDispo;
        }
    }

    // Changer pour prendre une proposition du dico à la place
    public boolean getMotCroisement(String mot, int x, int y, String direction){
        // Si la direction choisie est horizontale
        boolean isCroisementOK = true;
        if (direction.equals("H")){
            for (int i = 0; i < mot.length(); i++) {
                if(tabGrille[i+x][y] != ' '){
                    if(mot.charAt(i) != tabGrille[i+x][y]){
                        isCroisementOK = false;
                        break;
                    }
                }
            }
            return isCroisementOK;
        }
        // Sinon c'est à la verticale
        for (int i = 0; i < mot.length(); i++) {
                if(tabGrille[x][y+i] != ' '){
                    if(mot.charAt(i) != tabGrille[x][y+i]){
                        isCroisementOK = false;
                        break;
                    }
                }
        }
        return isCroisementOK;
    }
}
