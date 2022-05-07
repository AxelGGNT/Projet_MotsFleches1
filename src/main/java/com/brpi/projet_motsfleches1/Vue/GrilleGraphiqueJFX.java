package com.brpi.projet_motsfleches1.Vue;

import java.util.ArrayList;

import com.brpi.projet_motsfleches1.Contrôleur.I_Controleur;
import javafx.collections.ObservableMap;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
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
import com.brpi.projet_motsfleches1.Modele.Grille;
import com.brpi.projet_motsfleches1.Modele.I_modeleGrille;
import com.brpi.projet_motsfleches1.Vue.I_Observateur;

public class GrilleGraphiqueJFX implements I_Observateur, I_Grille, EventHandler<MouseEvent> {
	private static final int HAUTEUR_MENU = 26;
	private int largeur;
	private int hauteur;
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

	public GrilleGraphiqueJFX(I_modeleGrille modele) {
		this.modele= modele;
		modele.addObservateur(this);
		this.largeur = modele.getLargeur();
		this.hauteur= modele.getHauteur();
		System.out.printf("création d'une grille %s x %s%n",this.largeur,this.hauteur);
	}
	
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

	private void buildGrilleChiffres() {
		for (int i=1;i<=hauteur * largeur;i++) {
			Label jl = jl(".");
			jl.setOnMouseClicked(this);
			tableau.add(jl);
			layout.getChildren().add(jl);
		}
		
	}
	
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

	private MenuBar createControls() {
		 menuBar = new MenuBar();
	        menu = new Menu("actions");
	        addTestMenuItem = new MenuItem("ajouter test en 0,0");
	        menu.getItems().add(addTestMenuItem);
	        addTestMenuItem.setOnAction(new EventHandler<ActionEvent>() {
	        	@Override
	        	public void handle(ActionEvent e) {
					controller.ajouterMotCtrl("test", 0, 0,"H");
	        	}
	        });
	        addEssaiMenuItemOk = new MenuItem("ajouter essai en 5,4 (ok)");
	        menu.getItems().add(addEssaiMenuItemOk); 
	        addEssaiMenuItemOk.setOnAction(new EventHandler<ActionEvent>() {
	        	@Override
	        	public void handle(ActionEvent e) {
					controller.ajouterMotCtrl("essai", 5, 4,"V");
	        	}
	        });
	        addEssaiMenuItemKO = new MenuItem("ajouter essai en 6,4 (KO)");
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

	@Override
	public void sendMessage(String s) {
		Alert alert = new Alert(Alert.AlertType.WARNING);
		alert.setContentText(s);
		alert.showAndWait();
	}

	@Override
	public void update(Grille gv) {
		for (int i=1;i<=hauteur * largeur;i++) {
		}
		
		char[][] tab = gv.getTabGrille();
		for (int h = 0; h < gv.getHauteur(); h++) {
			for (int l = 0; l < gv.getLargeur(); l++) {
				 tableau.get(h * gv.getLargeur()+ l).setText(String.valueOf(tab[l][h]));
			}
		}		
	}

	//@Override
	public void setController(I_Controleur ctrl) {
		this.controller = ctrl;
	}

	@Override
	public void handle(MouseEvent arg0) {
		// coordonnées dans le Label
		double x = arg0.getX();
		double y = arg0.getY();
		// coordonnées dans la scEne
		double xScene = arg0.getSceneX();
		double yScene = arg0.getSceneY();
		if (arg0.getSource() instanceof Label) {
			Label j = (Label) arg0.getSource();
			System.out.println(j.getText());
//			System.out.printf("x=%s , y=%s, xScene=%s , yScene = %s%n",x,y,xScene,yScene);
			int xcase = (int) xScene / TAILLECASE;
			int ycase = (int) (yScene - HAUTEUR_MENU) / TAILLECASE;
			
			System.out.printf("case y=%s par  x=%s%n", ycase, xcase);
//			g.gereCase(xcase,ycase);
		}
	}

	@Override
	public void ajouterDef(int x, int y, String libDef) {

	}

	@Override
	public void supprimerDef(int x, int y) {

	}
}
