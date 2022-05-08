package com.brpi.projet_motsfleches1.Controller;

/**
 * Interface du controleur
 */
public interface I_Controleur {
    void ajouterMotCtrl(String mot, int x, int y, String position);
    void ajouterDef(int x, int y, String libDef, String direction);
    String getValeurCases(int x, int y);
}
