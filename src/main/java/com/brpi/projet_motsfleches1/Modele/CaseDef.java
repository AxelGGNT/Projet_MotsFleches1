package com.brpi.projet_motsfleches1.Modele;

public class CaseDef {
    int nbMots;
    int positionX;
    int positionY;
    String def;
    String libelle;

    public enum directionDef{
        HAUT, BAS, GAUCHE, DROITE
    }

    // Getters & Setters
    public int getNbMots() {
        return nbMots;
    }

    public int getPositionX() {
        return positionX;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    public void setNbMots(int nbMots) {
        this.nbMots = nbMots;
    }

    public String getDef() {
        return def;
    }

    public void setDef(String def) {
        this.def = def;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    // Constructor
    public CaseDef(int positionX, int positionY, String libelle) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.libelle = libelle;
    }
}
