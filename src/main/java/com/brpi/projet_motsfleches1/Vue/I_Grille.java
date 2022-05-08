package com.brpi.projet_motsfleches1.Vue;

import com.brpi.projet_motsfleches1.Modele.Grille;

/**
 * Interface définissant les méthodes utilisées dans la classe grille graphique et le controleur
 */
public interface I_Grille {
    void sendMessage(String msg);
    javafx.scene.Scene getScene();
}
