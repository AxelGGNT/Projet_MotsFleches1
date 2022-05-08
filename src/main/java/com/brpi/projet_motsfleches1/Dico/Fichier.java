package com.brpi.projet_motsfleches1.Dico;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe Fichier - Gestion du dictionnaire de mots
 */
public class Fichier {
	private static final String FICHIER = "/liste_francais.txt";
	private ArrayList <String> listeDeMots = null;

	/**
	 * Constructeur de la classe de la Fichier.
	 */
	public Fichier() {
		super();
		listeDeMots = new ArrayList<>();
		ouvrirFichier();
	}

	/**
	 * Lecture d'un txt pour remplir un tableau de mots.
	 * @return Lit le fichier passé en paramétre en le retourne dans une liste
	 */
	private void ouvrirFichier(){
		try{
			InputStream flux = Fichier.class.getResourceAsStream(FICHIER);
			InputStreamReader lecture = new InputStreamReader(flux);
			BufferedReader buff = new BufferedReader(lecture);
			String ligne;
			while ((ligne=buff.readLine())!=null){
				listeDeMots.add(ligne);
			}
			buff.close();
		}
		catch (Exception e){
			System.out.println(e.toString());
		}
	}

	/**
	 * Liste de mots en fonction de la lettre de départ.
	 */
	public void afficherMots(){
		System.out.println("Affichage des mots presents dans le dictionnaire");
		for (String m  : listeDeMots) {
			System.out.println(m);
		}
	}

	/**
	 * Liste de mots en fonction de la lettre de départ.
	 * @param d
	 */
	public void afficherMotsDebut(String d){
			System.out.printf("Affichage des mots commencant par %s%n", d);
			int i = 0;
			for (String mot : listeDeMots) {
				i++;
			}
			System.out.println(i);
			for (String m  : listeDeMots) {
				if (m.startsWith(d)){
					System.out.println(m);
				}
			}
	}

	/**
	* Liste de mots en fonction du nombres de lettres et de la pos d'une lettre.
	* @param nbLettres
	* @return Liste de mots en fonction des paramétres passés
	*/
	public ArrayList<String> getListeDeMots(int nbLettres){
		ArrayList <String> listeMots = new ArrayList<>() {};
		for (String mot : listeDeMots){
			if (mot.length() == nbLettres){
				listeMots.add(mot);
			}
		}
		return listeMots;
	}
}
