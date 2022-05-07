package com.brpi.projet_motsfleches1.Dico;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Fichier {
	private static final String FICHIER = "/liste_francais.txt";
	private ArrayList <String> listeDeMots = null;
	
	public Fichier() {
		super();
		listeDeMots = new ArrayList<>();
		ouvrirFichier();
	}
	
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

	public void afficherMots(){
		System.out.println("Affichage des mots presents dans le dictionnaire");
		for (String m  : listeDeMots) {
			System.out.println(m);
		}
	}
		
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

	// A changer pour un tableau un paramètre
	public void getListMots(int nbLettres, char lettre, int position){
		System.out.printf("Affichage des mots de %d lettres, commençant par %s%n",nbLettres,lettre);
		for (String mot : listeDeMots){
			if (mot.length() == nbLettres && mot.charAt(position) == lettre){
				System.out.println(mot);
			}
		}
	}
}
