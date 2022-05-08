package com.brpi.projet_motsfleches1.Modele;

/**
 * Classe Mot - Non utilisée
 */
public class Mot {
    int longueurMot;
    int positionDepartMot;
    int positionDef;
    String def;
    String libelleMot;

    public enum directionMot{
        HAUT,BAS,GAUCHE,DROITE
    }

    // Getters & Setters //
    public int getLongueurMot() {
        return longueurMot;
    }

    public void setLongueurMot(int longueurMot) {
        this.longueurMot = longueurMot;
    }

    public int getPositionDepartMot() {
        return positionDepartMot;
    }

    public void setPositionDepartMot(int positionDepartMot) {
        this.positionDepartMot = positionDepartMot;
    }

    public int getPositionDef() {
        return positionDef;
    }

    public void setPositionDef(int positionDef) {
        this.positionDef = positionDef;
    }

    public String getDef() {
        return def;
    }

    public void setDef(String def) {
        this.def = def;
    }

    public String getLibelleMot() {
        return libelleMot;
    }

    public void setLibelleMot(String libelleMot) {
        this.libelleMot = libelleMot;
    }

    public Mot(int longueurMot, int positionDepartMot, int positionDef, String def, String libelleMot) {
        this.longueurMot = longueurMot;
        this.positionDepartMot = positionDepartMot;
        this.positionDef = positionDef;
        this.def = def;
        this.libelleMot = libelleMot;
    }
}
