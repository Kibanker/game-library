import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class Vue extends JFrame { // Vue

    public Vue(ArrayList<Jeu> biblio) {
        super("Bibliothèque de jeux vidéo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        for (Jeu jeu : biblio) {
            JPanel panel = new JPanel();
            panel.setLayout(new BorderLayout());

            // Image du jeu
            JLabel imageLabel = new JLabel(jeu.image);
            panel.add(imageLabel, BorderLayout.CENTER);

            // Informations sur le jeu
            JTextArea infoArea = new JTextArea();
            infoArea.append("Nom: " + jeu.nom + "\n");
            infoArea.append("Catégorie: " + jeu.categorie + "\n");
            infoArea.append("Date de sortie: " + jeu.dateDeSortie + "\n");
            infoArea.append("Entreprise: " + jeu.entreprise + "\n");
            infoArea.append("Note générale: " + jeu.noteGen + "\n");
            infoArea.append("Note créateur: " + jeu.noteCrea + "\n");
            if (jeu.mediaAssocie != null) {
                infoArea.append("Media associé: " + jeu.mediaAssocie + "\n");
            }
            if (jeu.son != null) {
                infoArea.append("Son: " + jeu.son + "\n");
            }
            if (jeu.nominations != null) {
                infoArea.append("Nominations: " + jeu.nominations + "\n");
            }
            infoArea.setEditable(false);

            panel.add(infoArea, BorderLayout.SOUTH);
            add(panel);
        }

        pack();
        setLocationRelativeTo(null); // Centrer la fenêtre
        setVisible(true);
    }
}
