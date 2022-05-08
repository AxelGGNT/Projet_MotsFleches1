package com.brpi.projet_motsfleches1.Controller;

import com.brpi.projet_motsfleches1.Modele.I_modeleGrille;
import com.brpi.projet_motsfleches1.Vue.I_Grille;
import com.brpi.projet_motsfleches1.Vue.GrilleGraphiqueJFX;

/**
 * Classe controleur - Effectue les traitement entre la grille virtuelle et la grille graphique
 */
public class Controleur implements I_Controleur{
    private I_Grille grille;
    private I_modeleGrille modele;
    public javafx.scene.Scene getScene() {return grille.getScene();}

    /**
     * Constructeur du controleur
     * @param modeleGrille
     */
    public Controleur(I_modeleGrille modeleGrille){
        this.modele = modeleGrille;
        this.grille = new GrilleGraphiqueJFX(modele, this);
    }

    /**
     * Ajout du mot
     * @param message
     * @param x
     * @param y
     * @param direction
     */
    public void ajouterMotCtrl(String message, int x, int y, String direction){
        boolean res = modele.ajouterMot(message, x, y, direction);
        if (!res){
            grille.sendMessage(String.format("Erreur, le mot ajouté est trop long."));
        }
    }

    /**
     * Ajout d'une définition
     * @param x
     * @param y
     * @param libDef
     */
    public void ajouterDef(int x, int y, String libDef, String direction){
        boolean res = modele.ajouterDef(x,y,libDef, direction);
        if (!res){
            grille.sendMessage(String.format("Erreur, une definition ne peut pas être ajoutée ici."));
        }
    }

    /**
     * Obtient la valeur de la case en fonction de la position
     * @param x
     * @param y
     * @return Une string contenant la valeur
     */
    public String getValeurCases(int x, int y){
        String res = modele.getValueCase(x,y);
        return res;
    }
}
