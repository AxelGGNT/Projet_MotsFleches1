package com.brpi.projet_motsfleches1.Vue;

import java.lang.reflect.Array;
import java.util.ArrayList;

import com.brpi.projet_motsfleches1.Contrôleur.Controleur;
import com.brpi.projet_motsfleches1.Contrôleur.I_Controleur;
import com.brpi.projet_motsfleches1.Modele.*;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import com.brpi.projet_motsfleches1.Dico.Fichier;
import com.brpi.projet_motsfleches1.Vue.I_Observateur;
import java.util.Optional;

public class GrilleGraphiqueJFX implements I_Observateur, I_Grille, EventHandler<MouseEvent> {
	private static final int HAUTEUR_MENU = 26;
	private final int largeur;
	private final int hauteur;
	private final int TAILLECASE = 100;
	private final ArrayList<Label> tableau = new ArrayList<>();
	private MenuBar menuBar;
	private Menu menu;
	private MenuItem addTestMenuItem;
	private MenuItem addEssaiMenuItemOk;
    private MenuItem addEssaiMenuItemKO;
	private I_Controleur controller;
	private final I_modeleGrille modele;
	private TilePane layout;
	private Fichier listeMots = new Fichier();

	/**
	 * Constructeur de la classe
	 * @param modele
	 * @param ctrl
	 */
	public GrilleGraphiqueJFX(I_modeleGrille modele, Controleur ctrl) {
		this.controller = ctrl;
		this.modele= modele;
		modele.addObservateur(this);
		this.largeur = modele.getLargeur();
		this.hauteur= modele.getHauteur();
		System.out.printf("création d'une grille %s x %s%n",this.largeur,this.hauteur);
	}

	/**
	 * Méthode créant une scène ainsi qu'une fenêtre
	 * @return Une fenêtre
	 */
	public Scene getScene() {
		Group root = new Group();
		Scene scene = new Scene(root,TAILLECASE * largeur, TAILLECASE * hauteur+HAUTEUR_MENU, Color.LIGHTGRAY);
		layout = new TilePane();
		layout.setVgap(0);
		layout.setHgap(0);
		layout.setMinSize(TAILLECASE * largeur, TAILLECASE * hauteur);
		buildGrilleChiffres();
		VBox vBox = new VBox();
		vBox.getChildren().add(createControls());
		vBox.getChildren().add(layout);
		root.getChildren().add(vBox);
		return scene;
	}

	/**
	 * Initialisation de la grille avec des points
	 */
	private void buildGrilleChiffres() {
		for (int i=1;i<=hauteur * largeur;i++) {
			Label jl = jl(".");
			jl.setOnMouseClicked(this);
			tableau.add(jl);
			layout.getChildren().add(jl);
		}
		
	}

	/**
	 * Création des labels et de leurs propriétés
	 * @param lettre
	 * @return un label contenant la string passée en paramétre
	 */
	private Label jl(String lettre) {
		Label label = new Label(lettre);
		label.setOpacity(100);
		label.setBackground(Background.EMPTY);
		label.setAlignment(Pos.BASELINE_CENTER);
		label.setFont(new Font("Arial", TAILLECASE/2));
		label.setPrefSize(TAILLECASE , TAILLECASE );
		label.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1))));
		return label;
	}

	/**
	 * Création du menu "Actions"
	 * @return Un menu contenant plusieurs menuitem
	 */
	private MenuBar createControls() {
		 menuBar = new MenuBar();
	        menu = new Menu("Actions");
	        addTestMenuItem = new MenuItem("Ajouter test en 0,0");
	        menu.getItems().add(addTestMenuItem);
	        addTestMenuItem.setOnAction(new EventHandler<ActionEvent>() {
	        	@Override
	        	public void handle(ActionEvent e) {
					controller.ajouterMotCtrl("test", 0, 0,"H");
	        	}
	        });
	        addEssaiMenuItemOk = new MenuItem("Ajouter essai en 5,4 (ok)");
	        menu.getItems().add(addEssaiMenuItemOk); 
	        addEssaiMenuItemOk.setOnAction(new EventHandler<ActionEvent>() {
	        	@Override
	        	public void handle(ActionEvent e) {
					controller.ajouterMotCtrl("essai", 5, 4,"V");
	        	}
	        });
	        addEssaiMenuItemKO = new MenuItem("Ajouter essai en 6,4 (KO)");
	        menu.getItems().add(addEssaiMenuItemKO); 
	        addEssaiMenuItemKO.setOnAction(new EventHandler<ActionEvent>() {
	        	@Override
	        	public void handle(ActionEvent e) {
					controller.ajouterMotCtrl("essai", 6, 4,"H");
	        	}
	        });
	        MenuItem exit = new MenuItem("Quitter");
	        exit.setOnAction(new EventHandler<ActionEvent>() {
	        	@Override
	        	public void handle(ActionEvent e) {
	        	 	System.exit(0);
	        	}
	        });

	        menu.getItems().add(exit);
	        menuBar.getMenus().add(menu);
	        //layout.getChildren().add()setMenuBar(menuBar);
	        return menuBar;
	}

	/**
	 * Méthode générique pour les messages d'erreur
	 * @param s
	 */
	@Override
	public void sendMessage(String s) {
		Alert alert = new Alert(Alert.AlertType.WARNING);
		alert.setContentText(s);
		alert.showAndWait();
	}

	/**
	 * Update de la grille quand est changement (update) est détecté par l'observateur
	 * @param gv
	 */
	@Override
	public void update(Grille gv) {
		Case[][] tabCases = gv.getTabGrille();
		for (int i=1;i<=hauteur * largeur;i++) {
			for (int h = 0; h < gv.getHauteur(); h++) {
				for (int l = 0; l < gv.getLargeur(); l++) {
					if (tabCases[l][h] instanceof CaseLettre) {
						tableau.get(h * gv.getLargeur() + l).setText(controller.getValeurCases(l,h));
					}
					else if (tabCases[l][h] instanceof CaseDef) {
						tableau.get(h * gv.getLargeur() + l).setText(controller.getValeurCases(l,h));
						tableau.get(h * gv.getLargeur() + l).setFont(new Font("Arial", TAILLECASE/8));
					}
				}
			}
		}
	}

	/**
	 * Gestion de l'événement du clic de souris sur un label (ouverture d'un menu contextuel)
	 * @param arg0
	 */
	@Override
	public void handle(MouseEvent arg0) {
		// Coordonnées dans le label
		double x = arg0.getX();
		double y = arg0.getY();
		// Coordonnées dans la scène
		double xScene = arg0.getSceneX();
		double yScene = arg0.getSceneY();
		if (arg0.getSource() instanceof Label) {
			Label j = (Label) arg0.getSource();
			System.out.println(j.getText());
			int xcase = (int) xScene / TAILLECASE;
			int ycase = (int) (yScene - HAUTEUR_MENU) / TAILLECASE;
			((Label) arg0.getSource()).setContextMenu(createMenuCase(xcase, ycase));

			System.out.printf("case y=%s par  x=%s%n", ycase, xcase);
//			g.gereCase(xcase,ycase);
		}
	}

	/**
	 * Création du menu contextuel au clic sur un label de la fenêtre et des événements associés
	 * @param x
	 * @param y
	 * @return Un menu contextuel contenant des menu items avec des événéments associés
	 */
	public ContextMenu createMenuCase(int x, int y){
		ContextMenu menu = new ContextMenu();
		MenuItem def = new MenuItem("Ajouter une définition");
		MenuItem mot = new MenuItem("Ajouter un mot");
		def.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				System.out.printf("Cliquer sur le menu pour ajouter une définition.");
				setDefinition(x, y);
			}
		});
		mot.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				System.out.printf("Cliquer sur le menu pour ajouter un mot.");
				setMot(x, y);
			}
		});
		menu.getItems().add(def);
		menu.getItems().add(mot);
		return menu;
	}

	/**
	 * Sous menu permettant de créer une définition au clic sur une case
	 * @param x
	 * @param y
	 */
	private void setDefinition(int x, int y){
		TextInputDialog dialog = new TextInputDialog("");

		dialog.setTitle("Ajouter une définition");
		dialog.setHeaderText(null);
		dialog.setContentText("Libellé: ");
		dialog.showAndWait();

		String result = dialog.getResult();

		if(result != " "){
			modele.ajouterDef(x,y,result);
		};
	}

	/**
	 * Sous menu permettant d'ajouter un mot à partir d'une liste et d'une direction donnée
	 * @param x
	 * @param y
	 */
	private void setMot(int x, int y){
		String[] directions = {"HD","HI","VD","VI"};
		ChoiceDialog choixDirection = new ChoiceDialog(directions[0],directions);

		choixDirection.setTitle("Choisir une direction");
		choixDirection.setHeaderText(null);
		choixDirection.setContentText("Choix: ");
		choixDirection.showAndWait();

		String choix = (String) choixDirection.getSelectedItem();

		int nbCasesDispo = modele.findCaseDispo(x,y,choix);
		ArrayList<String> listeMotNb = listeMots.getListeDeMots(nbCasesDispo);

		ChoiceDialog choixMots = new ChoiceDialog(listeMotNb.get(2), listeMotNb);

		choixMots.setTitle("Ajouter un mot");
		choixMots.setHeaderText(null);
		choixMots.setContentText("Libellé: ");
		choixMots.showAndWait();

		String result = (String) choixMots.getSelectedItem();

		System.out.println(result);
		if(result != " "){
			modele.ajouterMot(result,x,y,choix);
		};
	}
}
