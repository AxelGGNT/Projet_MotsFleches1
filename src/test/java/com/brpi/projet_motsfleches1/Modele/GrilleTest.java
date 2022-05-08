package com.brpi.projet_motsfleches1.Modele;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GrilleTest {

    /**
     * Méthode pour tester la grille - Non fini
     */
    /*void creerGrille(){
        Grille grille = new Grille(-3,-3);
    }*/

    /**
     * Test d'ajout d'un mot
     */
    @Test
    void ajouterMot() {
        Grille grille = new Grille(10,10);
        assertEquals(" ", grille.getValueCase(1,1));
        grille.ajouterMot("Test",1,1,"VD");
        assertEquals( "T", grille.getValueCase(1,2));
        assertEquals( "e", grille.getValueCase(1,3));
        assertEquals( "s", grille.getValueCase(1,4));
        assertEquals( "t", grille.getValueCase(1,5));
    }

    /**
     * Test d'ajout d'une définition
     */
    @Test
    void ajouterDef() {
        Grille grille = new Grille(10,10);
        assertEquals(" ", grille.getValueCase(1,1));
        grille.ajouterDef(1,1,"Définition","HI");
        assertEquals( "Définition", grille.getValueCase(1,1));
        assertEquals( " ", grille.getValueCase(2,1));
    }

    /**
     * Test de suppression d'une définition
     */
    @Test
    void supprimerDef() {
        Grille grille = new Grille(10,10);
        grille.ajouterDef(1,1,"Définition","HI");
        grille.supprimerDef(1,1);
    }

    /**
     * Test du nombre de cases dispos
     */
    @Test
    void findCaseDispo() {
        Grille grille = new Grille(10,10);
        assertEquals(8, grille.findCaseDispo(1,1, "HD"));
        grille.ajouterMot("Test",1,1,"HD");
        assertEquals(4, grille.findCaseDispo(5,1, "HD"));
    }

    /**
     * Test de la valeur d'une case
     */
    @Test
    void getValueCase() {
        Grille grille = new Grille(10,10);
        grille.ajouterMot("Test",4,3,"VI");
        grille.ajouterDef(1,0,"Mot","HI");
        assertEquals("T", grille.getValueCase(5,3));
        assertEquals("e", grille.getValueCase(5,4));
        assertEquals("s", grille.getValueCase(5,5));
        assertEquals("t", grille.getValueCase(5,6));
        assertEquals(" ", grille.getValueCase(5,7));
        assertEquals("Mot", grille.getValueCase(1,0));
        assertEquals(" ", grille.getValueCase(2,0));
    }
}