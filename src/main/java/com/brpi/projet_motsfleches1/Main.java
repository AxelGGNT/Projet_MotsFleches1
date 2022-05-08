package com.brpi.projet_motsfleches1;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;

import com.brpi.projet_motsfleches1.Modele.Grille;
import com.brpi.projet_motsfleches1.Controller.Controleur;

import java.io.IOException;

/**
 * Classe Main - Définition de la scène et démarrage de l'application
 */
public class Main extends Application {
    /**
     * Lancement de l'application
     * @param args
     */
    public static void main(String[] args) {
        launch();
    }

    /**
     * Création de la grille graphique (stage/scene)
     * @param stage
     * @throws IOException
     */
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