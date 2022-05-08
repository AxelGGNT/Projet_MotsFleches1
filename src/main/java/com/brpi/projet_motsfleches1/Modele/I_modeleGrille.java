package com.brpi.projet_motsfleches1.Modele;

import com.brpi.projet_motsfleches1.Vue.I_Observateur;

/**
 * Interface définissant les méthodes utilisées dans la classe Grille et dans le controleur
 */
public interface I_modeleGrille {
    int getLargeur();
    int getHauteur();
    Case[][] getTabGrille();
    void addObservateur(I_Observateur o);
    void removeObservateur(I_Observateur o);
    void notifyObservateur();
    boolean ajouterMot(String mot, int x, int y, String direction);
    boolean ajouterDef(int x, int y, String libDef, String direction);
    void supprimerDef(int x, int y);
    String getValueCase(int x, int y);
    int findCaseDispo(int x, int y, String direction);
}
