package com.brpi.projet_motsfleches1.Contrôleur;

import com.brpi.projet_motsfleches1.Modele.Grille;

public interface I_Controleur {
    void ajouterMotCtrl(String mot, int x, int y, String position);
    void setController(Controleur ctrl) ;
}
