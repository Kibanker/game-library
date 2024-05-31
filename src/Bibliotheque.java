import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.*;


import com.formdev.flatlaf.FlatDarkLaf;

public class Bibliotheque { // Contrôleur
	
	public static void main(String[] args) {
		JFrame f = new JFrame();
		
		try {
			UIManager.put("Menu.background", new Color(173, 216, 230)); // Définir la couleur de fond du menu
            UIManager.put("MenuItem.background", new Color(173, 216, 230)); // Définir la couleur de fond des éléments de menu
		} catch( Exception ex ) {
		    System.err.println( "Failed to initialize LaF" );
		}
		
		ArrayList<Jeu> biblio = readGamesFromCSV("files/games.csv");
        SwingUtilities.invokeLater(() -> new Vue(biblio));
		
	}

	public static ArrayList<Jeu> readGamesFromCSV(String fileName) {
        ArrayList<Jeu> jeux = new ArrayList<>();
        String line;
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            // Skip the header line if exists
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] data = line.split(";");
                Jeu jeu = createJeuFromCSV(data);
                jeux.add(jeu);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jeux;
    }

	public static Jeu createJeuFromCSV(String[] data) {
	    if (data.length < 11) {
	        // Gérer le cas où la ligne ne contient pas toutes les données attendues
	        System.err.println("Erreur: La ligne CSV ne contient pas toutes les données attendues.");
	        return null;
	    }
	    
	    String resume = data[0];
	    String nom = data[1];
	    String categorie = data[2];
	    String dateDeSortie = data[3];
	    String entreprise = data[4];
	    
	    double noteGen;
	    try {
	        noteGen = Double.parseDouble(data[5]); // Convertir en double
	    } catch (NumberFormatException e) {
	        noteGen = -1; // Gérer les valeurs non valides en attribuant une valeur par défaut
	    }
	    
	    double noteCrea;
	    try {
	        noteCrea = Double.parseDouble(data[6]); // Convertir en double
	    } catch (NumberFormatException e) {
	        noteCrea = -1; // Gérer les valeurs non valides en attribuant une valeur par défaut
	    }
	    
	    String img = data[7];
	    String media = data[8];
	    String son = data[9];
	    String nominations = data[10];
	    
	    // Gérer les valeurs manquantes pour les trois dernières colonnes
	    if (media.isEmpty()) {
	        media = null;
	    }
	    if (son.isEmpty()) {
	        son = null;
	    }
	    if (nominations.isEmpty()) {
	        nominations = null;
	    }
	    
	    Jeu jeu = new Jeu(resume, nom, categorie, dateDeSortie, entreprise, noteGen, noteCrea, img, media, son, nominations);
	    return jeu;
	}



}
