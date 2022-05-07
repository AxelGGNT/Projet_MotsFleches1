package com.brpi.projet_motsfleches1.Vue;

import com.brpi.projet_motsfleches1.Modele.Grille;

public interface I_Grille {
    void sendMessage(String msg);
    void ajouterDef(int x, int y, String libDef);
    void supprimerDef(int x, int y);
    javafx.scene.Scene getScene();
}
