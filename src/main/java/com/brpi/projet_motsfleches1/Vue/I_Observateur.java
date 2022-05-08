package com.brpi.projet_motsfleches1.Vue;

import com.brpi.projet_motsfleches1.Modele.Grille;

/**
 * Interface de l'observateur
 */
public interface I_Observateur {
    void update(Grille grille);
}
