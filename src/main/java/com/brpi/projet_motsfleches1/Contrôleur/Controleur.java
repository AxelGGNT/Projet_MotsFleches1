package com.brpi.projet_motsfleches1.Contrôleur;

import com.brpi.projet_motsfleches1.Modele.I_modeleGrille;
import com.brpi.projet_motsfleches1.Vue.I_Grille;
import com.brpi.projet_motsfleches1.Vue.GrilleGraphiqueJFX;

public class Controleur implements I_Controleur{
    private I_Grille grille;
    private I_modeleGrille modele;

    @Override
    public void setController(Controleur ctrl) {

    }

    public javafx.scene.Scene getScene() {return grille.getScene();}

    public Controleur(I_modeleGrille modeleGrille){
        this.modele = modeleGrille;
        this.grille = new GrilleGraphiqueJFX(modele);
    }
    public void ajouterMotCtrl(String message, int x, int y, String direction){
        boolean res = modele.ajouterMot(message, x, y, direction);
        if (!res){
            grille.sendMessage(String.format("erreur"));
        }
    }

}
