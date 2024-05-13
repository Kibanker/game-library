import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;

public class DescriptionJeuPanel extends JPanel {
    private Jeu jeu;
    private boolean voted; // Variable pour suivre si l'utilisateur a déjà voté pour ce jeu
    String noteCreaNA;

    public DescriptionJeuPanel(Jeu jeu) {
        this.jeu = jeu;
        voted = false; // Initialiser voted à false pour chaque jeu
        setLayout(new BorderLayout());
        setBackground(Color.BLACK);
        setBorder(createGameBorder(jeu.nom));

        JPanel nomPanel = new JPanel(new BorderLayout());
        nomPanel.setBackground(Color.BLACK);
        JLabel nameLabel = new JLabel(jeu.nom);
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 24));
        nameLabel.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20)); // Ajouter des marges au label du nom
        nomPanel.add(nameLabel);
        add(nomPanel, BorderLayout.NORTH);

        // Image du jeu en haut à gauche
        JLabel imageLabel = new JLabel(jeu.image);
        imageLabel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Ajouter des marges à l'image
        imageLabel.setBackground(Color.BLACK);
        imageLabel.setOpaque(true);
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
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

        JTextArea descriptionArea = new JTextArea();
        if (jeu.noteCrea == -1.0) {
            noteCreaNA = "N/A";
        }
        descriptionArea.setText("Catégorie: " + jeu.categorie + "\n" +
                "Date de sortie: " + jeu.dateDeSortie + "\n" +
                "Entreprise: " + jeu.entreprise + "\n" +
                "Note générale: " + jeu.noteGen + "\n" +
                "Note de création: " + (jeu.noteCrea == -1.0 ? noteCreaNA : "N/A") + "\n" +
                "Nominations: " + (jeu.nominations.equals("null") ? "N/A" : jeu.nominations));
        descriptionArea.setForeground(Color.WHITE);
        descriptionArea.setBackground(Color.BLACK);
        descriptionArea.setFont(new Font("Arial", Font.PLAIN, 18));
        descriptionArea.setEditable(false);
        descriptionArea.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 20)); // Ajouter des marges à la zone de texte
        descriptionPanel.add(descriptionArea, BorderLayout.CENTER);

        // Ajouter des étoiles pour permettre à l'utilisateur de donner une note
        JPanel ratingPanel = new JPanel();
        ratingPanel.setBackground(Color.BLACK);
        ratingPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        JLabel ratingLabel = new JLabel("Donner une note: ");
        ratingLabel.setForeground(Color.WHITE);
        ratingPanel.add(ratingLabel);

        // Créer des boutons d'étoile pour permettre à l'utilisateur de donner une note
        for (int i = 1; i <= 5; i++) {
            final int rating = i; // Déclarer la variable finale pour être utilisée dans l'expression lambda
            ImageIcon starIcon = new ImageIcon("files/star.png"); // Remplacez "star.png" par le chemin de votre icône d'étoile
            Image image = starIcon.getImage();
            Image newImage = image.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
            ImageIcon newStarIcon = new ImageIcon(newImage);
            JButton starButton = new JButton(newStarIcon);
            starButton.setBorderPainted(false); // Supprimer la bordure du bouton
            starButton.setContentAreaFilled(false); // Supprimer le remplissage du bouton
            starButton.setFocusPainted(false); // Supprimer la mise au point du bouton
            starButton.setOpaque(false); // Rendre le bouton transparent
            starButton.addActionListener(e -> {
                // Vérifier si l'utilisateur a déjà voté pour ce jeu
                if (!voted) {
                    // Mettre à jour la note dans la description du jeu
                    descriptionArea.append("\nNote donnée par l'utilisateur: " + rating);
                    // Désactiver les boutons d'étoiles après que l'utilisateur a voté
                    for (Component component : ratingPanel.getComponents()) {
                        if (component instanceof JButton) {
                            ((JButton) component).setEnabled(false);
                        }
                    }
                    voted = true; // Mettre à jour l'état du vote à true
                } else {
                    JOptionPane.showMessageDialog(this, "Vous avez déjà voté pour ce jeu.", "Vote déjà effectué", JOptionPane.WARNING_MESSAGE);
                }
            });
            ratingPanel.add(starButton);
        }

        descriptionPanel.add(ratingPanel, BorderLayout.SOUTH);

        add(descriptionPanel, BorderLayout.EAST);
    }

    private Border createGameBorder(String name) {
        Border emptyBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        return BorderFactory.createCompoundBorder(new DropShadowBorder(), emptyBorder);
    }
}

