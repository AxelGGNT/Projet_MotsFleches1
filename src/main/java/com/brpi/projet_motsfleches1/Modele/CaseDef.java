package com.brpi.projet_motsfleches1.Modele;

/**
 * Classe CaseDef -  Héritée de Case, gère les cases contenant des définitions
 */
public class CaseDef implements Case {
    int positionX;
    int positionY;
    String libelle;
    String direction;

    // Getters & Setters
    public String getLibelle() {
        return libelle;
    }

    public String getDirection() {
        return direction;
    }

    /**
     * Constructeur de la classe CaseDef
     */
    public CaseDef(int positionX, int positionY, String libelle, String direction) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.libelle = libelle;
        this.direction = direction;
    }
}
