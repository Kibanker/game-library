import javax.swing.*;
import java.awt.*;

public class DescriptionJeuPanel extends JPanel {
    private Jeu jeu;

    public DescriptionJeuPanel(Jeu jeu) {
        this.jeu = jeu;
        setLayout(new BorderLayout());
        setBackground(Color.BLACK);

        // Image du jeu en haut à gauche
        JLabel imageLabel = new JLabel(jeu.image);
        imageLabel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Ajouter des marges à l'image
        imageLabel.setBackground(Color.BLACK);
        imageLabel.setOpaque(true);
        add(imageLabel, BorderLayout.WEST);
        
        JPanel resumePanel = new JPanel(new BorderLayout());
        resumePanel.setOpaque(false); // Permet au fond d'écran de s'afficher
        add(resumePanel, BorderLayout.CENTER);

        // Résumé du jeu dans un JScrollPane
        JTextArea summaryArea = new JTextArea(jeu.resume);
        summaryArea.setForeground(Color.WHITE);
        summaryArea.setBackground(Color.BLACK);
        summaryArea.setFont(new Font("Arial", Font.PLAIN, 18));
        summaryArea.setEditable(false);
        summaryArea.setLineWrap(true); // Saut de ligne automatique
        summaryArea.setWrapStyleWord(true); // Saut de ligne après le mot entier

        JScrollPane summaryScrollPane = new JScrollPane(summaryArea);
        summaryScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); // Défilement vertical
        summaryScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); // Pas de défilement horizontal
        summaryScrollPane.setPreferredSize(new Dimension(350, 50)); // Définir la taille préférée du JScrollPane

        // Ajouter le résumé dans un panneau défilable
        JPanel summaryPanel = new JPanel(new BorderLayout());
        summaryPanel.add(summaryScrollPane, BorderLayout.CENTER);
        resumePanel.add(summaryPanel, BorderLayout.CENTER);

        // Description du jeu à droite de l'image
        JPanel descriptionPanel = new JPanel(new BorderLayout());
        descriptionPanel.setBackground(Color.BLACK);
        JLabel nameLabel = new JLabel(jeu.nom);
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 24));
        nameLabel.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20)); // Ajouter des marges au label du nom
        descriptionPanel.add(nameLabel, BorderLayout.NORTH);

        JTextArea descriptionArea = new JTextArea();
        descriptionArea.setText("Catégorie: " + jeu.categorie + "\n" +
                "Date de sortie: " + jeu.dateDeSortie + "\n" +
                "Entreprise: " + jeu.entreprise + "\n" +
                "Note générale: " + jeu.noteGen + "\n" +
                "Note de création: " + jeu.noteCrea + "\n" +
                "Nominations: " + (jeu.nominations == "null" ? jeu.nominations : "N/A"));
        descriptionArea.setForeground(Color.WHITE);
        descriptionArea.setBackground(Color.BLACK);
        descriptionArea.setFont(new Font("Arial", Font.PLAIN, 18));
        descriptionArea.setEditable(false);
        descriptionArea.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 20)); // Ajouter des marges à la zone de texte
        descriptionPanel.add(descriptionArea, BorderLayout.CENTER);

        add(descriptionPanel, BorderLayout.EAST);
    }
}