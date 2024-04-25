import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.*;


import com.formdev.flatlaf.FlatDarkLaf;

public class Bibliotheque {
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

	    JMenu menu = new JMenu("Accueuil");
	    JMenu menu2= new JMenu("Catégorie");
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


		ArrayList<Jeu> biblio = new ArrayList<Jeu>();
		
		
		// resume nom categorie date entreprise noteG noteC image media son nomination
		Jeu baldursGate = new Jeu("viande", "viande", "viande", "19/04/04", "viande", 5, 5, "files/bg3.jpg", null, null, null);
		Jeu eldenRing = new Jeu("viande", "viande", "viande", "19/04/04", "viande", 5, 5, "files/eldenring.jpeg", null, null, null);
		Jeu eldenRingg = new Jeu("viande", "viande", "viande", "19/04/04", "viande", 5, 5, "files/eldenring.jpeg", null, null, null);
		
		biblio.add(baldursGate);
		biblio.add(eldenRing);
		biblio.add(eldenRingg);
		
		for (int i = 0; i < biblio.size(); i++) {
			final JLabel label = new JLabel(biblio.get(i).image);
			
			f.add(label);
		}
		
		f.setSize(512*3, 512*3);
		f.setVisible(true);
		
	}
}
