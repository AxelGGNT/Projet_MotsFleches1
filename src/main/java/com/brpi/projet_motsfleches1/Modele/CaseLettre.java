package com.brpi.projet_motsfleches1.Modele;

/**
 * Classe CaseLettre -  Héritée de Case, gère les cases contenant des lettres (non Def)
 */
public class CaseLettre implements Case {
    char lettre;
    public char getLettre() {
        return lettre;
    }
    public String getLibelle(){
        return Character.toString(lettre);
    }

    /**
     * Constructeur de la classe CaseLettre
     * @param lettre
     */
    public CaseLettre(char lettre){
        this.lettre = lettre;
    }
}
