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

/**
 * Classe Main - Définition de la scène et démarrage de l'application
 */
public class Main extends Application {
    public static void main(String[] args) {
        launch();
    }
    @Override
    public void start(Stage stage) throws IOException {
        Grille grilleVirtuelle = new Grille(7,7);
        Controleur ctrl = new Controleur(grilleVirtuelle);
        Scene scene = ctrl.getScene();
        stage.setScene(scene);
        stage.setTitle("Concepteur de mots croisés");
        stage.show();
    }
}