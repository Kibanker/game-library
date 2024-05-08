import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class Vue extends JFrame {

    public Vue(ArrayList<Jeu> biblio) {
        super("Bibliothèque de jeux vidéo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        initMenuBar();

        JPanel contentPane = new JPanel(new GridLayout(0, 1));

        for (Jeu jeu : biblio) {
            JPanel panel = new JPanel(new BorderLayout());

            // Image du jeu
            JLabel imageLabel = new JLabel(jeu.image);
            panel.add(imageLabel, BorderLayout.CENTER);

            // Informations sur le jeu
            JTextArea infoArea = new JTextArea();
            infoArea.append("Nom: " + jeu.nom + "\n");
            infoArea.append("Description: " + jeu.resume + "\n");
            infoArea.append("Catégorie: " + jeu.categorie + "\n");
            infoArea.append("Date de sortie: " + jeu.dateDeSortie + "\n");
            infoArea.append("Entreprise: " + jeu.entreprise + "\n");
            infoArea.append("Note générale: " + jeu.noteGen + "\n");
            
            if (jeu.noteCrea == -1.0) {
            	infoArea.append("Note créateur: N/A \n");
			} else {
	            infoArea.append("Note créateur: " + jeu.noteCrea + "\n");
			}
            
            if (!jeu.mediaAssocie.equals("null")) {
                infoArea.append("Media associé: " + jeu.mediaAssocie + "\n");
            } else {
            	infoArea.append("Media associé: N/A \n");
            }
            if (!jeu.son.equals("null")) {
                infoArea.append("Son: " + jeu.son + "\n");
            } else {
            	infoArea.append("Son associé: N/A \n");
            }
            if (!jeu.nominations.equals("null")) {
                infoArea.append("Nominations: " + jeu.nominations + "\n");
            } else {
            	infoArea.append("Nominations: N/A \n");
			}
            
            infoArea.setEditable(false);
            
            // Ajout de la zone de texte à un JScrollPane
            JScrollPane scrollPane = new JScrollPane(infoArea);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

            panel.add(scrollPane, BorderLayout.SOUTH);
            contentPane.add(panel);
        }

        // Ajout du JPanel à un JScrollPane pour permettre le défilement
        JScrollPane mainScrollPane = new JScrollPane(contentPane);
        mainScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        add(mainScrollPane, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    private void initMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        Color lightBlue = new Color(180, 220, 250);
        menuBar.setBackground(lightBlue);
        setJMenuBar(menuBar);

        JMenu menu = new JMenu("Accueil");
        JMenu menu2= new JMenu("Catégories");
        menuBar.add(menu);
        menuBar.add(menu2);

        menu.setBackground(new Color(173, 216, 230)); // Bleu clair
        menu2.setBackground(new Color(173, 216, 230)); // Bleu clair

        // Ajouter les sous-menus
        String[] categories = {"Action", "Aventure", "Course", "Réflexion", "Simulation", "Stratégie", "Sport"};
        for (String category : categories) {
            JMenuItem subMenu = new JMenuItem(category);
            menu2.add(subMenu);
            // Définir la couleur de fond pour les sous-menus
            subMenu.setBackground(new Color(173, 216, 230)); // Bleu clair
        }
    }
}
