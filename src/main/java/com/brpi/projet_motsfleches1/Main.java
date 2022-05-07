package com.brpi.projet_motsfleches1;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.control.Button;

import com.brpi.projet_motsfleches1.Modele.Grille;
import com.brpi.projet_motsfleches1.Contrôleur.Controleur;

import java.io.IOException;

public class Main extends Application {
    public static void main(String[] args) {
        launch();
    }
    @Override
    public void start(Stage stage) throws IOException {
        Grille grilleVirtuelle = new Grille(6,6);
        Controleur ctrl = new Controleur(grilleVirtuelle);
        Scene scene = ctrl.getScene();
        stage.setScene(scene);
        stage.show();

        /*Grille grid = new Grille(10, 10);
        grid.ajouterMot("test",0,2, "V");
        System.out.println(grid.getMotCroisement("tutu",2,0,"H"));
        System.out.println(grid.getMotCroisement("yoyo",2,0,"H"));

        grid.ajouterDef(5,5,"Déf");
        grid.afficherGrille();

        System.out.println(grid.findCaseDef(5,5));
        System.out.println(grid.findCaseDispo(2,5,"V"));
        grid.findCaseDef(5,5);*/

        /*Fichier fichier = new Fichier();
        fichier.getListMots(4,'a',3);*/
    }
}