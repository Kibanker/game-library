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

		
		f.setLayout(new FlowLayout());
		
//		final ImageIcon img = new ImageIcon("files/bg3.jpg");
//		final ImageIcon imgScaled = new ImageIcon(img.getImage()
//                .getScaledInstance(130,
//                        180, Image.SCALE_SMOOTH));
//		
//		final JLabel label = new JLabel(imgScaled);
//		
//		f.add(label);

		Color lightBlue = new Color(180, 220, 250);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(lightBlue);
	    f.setJMenuBar(menuBar);

	    JMenu menu = new JMenu("Accueil");
	    JMenu menu2= new JMenu("Catégories");
	    menuBar.add(menu);
	    menuBar.add(menu2);
	    
	    menu.setBackground(new Color(173, 216, 230)); // Bleu clair
        menu2.setBackground(new Color(173, 216, 230)); // Bleu clair

        // Ajouter les sous-menus
        JMenu[] subMenus = {
            new JMenu("Action"),
            new JMenu("Aventure"),
            new JMenu("Course"),
            new JMenu("Réflexion"),
            new JMenu("Simulation"),
            new JMenu("Stratégie"),
            new JMenu("Sport")
        };
        for (JMenu subMenu : subMenus) {
            menu2.add(subMenu);
            // Définir la couleur de fond pour les sous-menus
            subMenu.setBackground(new Color(173, 216, 230)); // Bleu clair
        }


//		ArrayList<Jeu> biblio = new ArrayList<Jeu>();
//		
//		
//		// resume nom categorie date entreprise noteG noteC image media son nomination
//		Jeu baldursGate = new Jeu("viande", "viande", "viande", "19/04/04", "viande", 5, 5, "files/bg3.jpg", null, null, null);
//		Jeu eldenRing = new Jeu("viande", "viande", "viande", "19/04/04", "viande", 5, 5, "files/eldenring.jpeg", null, null, null);
//		Jeu eldenRingg = new Jeu("viande", "viande", "viande", "19/04/04", "viande", 5, 5, "files/eldenring.jpeg", null, null, null);
//		
//		biblio.add(baldursGate);
//		biblio.add(eldenRing);
//		biblio.add(eldenRingg);
//		
//		for (int i = 0; i < biblio.size(); i++) {
//			final JLabel label = new JLabel(biblio.get(i).image);
//			
//			f.add(label);
//		}
		
		f.setSize(512*3, 512*3);
		f.setVisible(true);
		
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
	        noteGen = 0.0; // Gérer les valeurs non valides en attribuant une valeur par défaut
	    }
	    
	    double noteCrea;
	    try {
	        noteCrea = Double.parseDouble(data[6]); // Convertir en double
	    } catch (NumberFormatException e) {
	        noteCrea = 0.0; // Gérer les valeurs non valides en attribuant une valeur par défaut
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
