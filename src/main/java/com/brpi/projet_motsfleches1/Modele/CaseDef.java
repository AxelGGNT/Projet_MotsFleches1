package com.brpi.projet_motsfleches1.Modele;

/**
 * Classe CaseDef -  Héritée de Case, gère les cases contenant des définitions
 */
public class CaseDef implements Case {
    int nbDefs;
    int positionX;
    int positionY;
    String libelle;

    // Getters & Setters
    public int getNbMots() {
        return nbDefs;
    }
    public String getLibelle() {
        return libelle;
    }

    /**
     * Constructeur de la classe CaseDef
     */
    public CaseDef(int positionX, int positionY, String libelle) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.libelle = libelle;
    }
}
