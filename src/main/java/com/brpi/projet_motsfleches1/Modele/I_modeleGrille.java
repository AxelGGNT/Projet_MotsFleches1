package com.brpi.projet_motsfleches1.Modele;

import com.brpi.projet_motsfleches1.Vue.I_Observateur;

public interface I_modeleGrille {
    int getLargeur();
    int getHauteur();
    char[][] getTabGrille();

    void addObservateur(I_Observateur o);
    void removeObservateur(I_Observateur o);
    void notifyObservateur();
    boolean ajouterMot(String mot, int x, int y, String direction);
}
