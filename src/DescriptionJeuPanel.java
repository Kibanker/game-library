import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;
import java.io.File;
import java.util.ArrayList;

public class DescriptionJeuPanel extends JPanel {
    private Jeu jeu;
    private boolean voted; // Variable pour suivre si l'utilisateur a déjà voté pour ce jeu
    private String noteCreaNA = "N/A";
    private ArrayList<String> comments; // Liste des commentaires

    public DescriptionJeuPanel(Jeu jeu) {
        this.jeu = jeu;
        this.voted = false; // Initialiser voted à false pour chaque jeu
        this.comments = new ArrayList<>(); // Initialiser la liste des commentaires
        setLayout(new BorderLayout());
        setBackground(Color.BLACK);
        setBorder(createGameBorder(jeu.nom));

        // Nom du jeu
        JPanel nomPanel = new JPanel(new BorderLayout());
        nomPanel.setBackground(Color.BLACK);
        JLabel nameLabel = new JLabel(jeu.nom);
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 24));
        nameLabel.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));
        nomPanel.add(nameLabel);
        add(nomPanel, BorderLayout.NORTH);

        // Image du jeu en haut à gauche
        JLabel imageLabel = new JLabel(jeu.image);
        imageLabel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
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
        summaryScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        summaryScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        summaryScrollPane.setPreferredSize(new Dimension(350, 50));

        JPanel summaryPanel = new JPanel(new BorderLayout());
        summaryPanel.add(summaryScrollPane, BorderLayout.CENTER);
        resumePanel.add(summaryPanel, BorderLayout.CENTER);

        // Description du jeu à droite de l'image
        JPanel descriptionPanel = new JPanel(new BorderLayout());
        descriptionPanel.setBackground(Color.BLACK);

        JTextArea descriptionArea = new JTextArea();
        if (jeu.noteCrea == -1.0) {
            descriptionArea.setText("Catégorie: " + jeu.categorie + "\n" +
                    "Date de sortie: " + jeu.dateDeSortie + "\n" +
                    "Entreprise: " + jeu.entreprise + "\n" +
                    "Note générale: " + jeu.noteGen + "\n" +
                    "Notre note: " + noteCreaNA + "\n" +
                    "Média associée: " + (jeu.mediaAssocie.equals("null") ? "N/A" : jeu.mediaAssocie) + "\n" +
                    "Nominations: " + (jeu.nominations.equals("null") ? "N/A" : jeu.nominations));
        } else {
            descriptionArea.setText("Catégorie: " + jeu.categorie + "\n" +
                    "Date de sortie: " + jeu.dateDeSortie + "\n" +
                    "Entreprise: " + jeu.entreprise + "\n" +
                    "Note générale: " + jeu.noteGen + "\n" +
                    "Notre note: " + jeu.noteCrea + "\n" +
                    "Média associée: " + (jeu.mediaAssocie.equals("null") ? "N/A" : jeu.mediaAssocie) + "\n" +
                    "Nominations: " + (jeu.nominations.equals("null") ? "N/A" : jeu.nominations));
        }

        descriptionArea.setForeground(Color.WHITE);
        descriptionArea.setBackground(Color.BLACK);
        descriptionArea.setFont(new Font("Arial", Font.PLAIN, 18));
        descriptionArea.setEditable(false);
        descriptionArea.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 20));
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
            final int rating = i;
            ImageIcon starIcon = new ImageIcon("files/star.png");
            Image image = starIcon.getImage();
            Image newImage = image.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
            ImageIcon newStarIcon = new ImageIcon(newImage);
            JButton starButton = new JButton(newStarIcon);
            starButton.setBorderPainted(false);
            starButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            starButton.setContentAreaFilled(false);
            starButton.setFocusPainted(false);
            starButton.setOpaque(false);
            starButton.setEnabled(false);

            starButton.addMouseListener(new MouseAdapter() {
                public void mouseEntered(MouseEvent e) {
                    for (Component component : ratingPanel.getComponents()) {
                        if (component instanceof JButton) {
                            ((JButton) component).setEnabled(true);
                        }
                    }
                }

                public void mouseExited(MouseEvent e) {
                    for (Component component : ratingPanel.getComponents()) {
                        if (component instanceof JButton) {
                            ((JButton) component).setEnabled(false);
                        }
                    }
                }
            });

            starButton.addActionListener(e -> {
                if (!voted) {
                    descriptionArea.append("\nNote donnée par l'utilisateur: " + rating);
                    for (Component component : ratingPanel.getComponents()) {
                        if (component instanceof JButton) {
                            ((JButton) component).setEnabled(false);
                        }
                    }
                    voted = true;
                } else {
                    int lastIndex = descriptionArea.getText().lastIndexOf('\n');
                    if (lastIndex != -1) {
                        descriptionArea.setText(descriptionArea.getText().substring(0, lastIndex));
                    }
                    descriptionArea.append("\nNote donnée par l'utilisateur: " + rating);
                }
            });
            ratingPanel.add(starButton);
        }

        descriptionPanel.add(ratingPanel, BorderLayout.SOUTH);
        add(descriptionPanel, BorderLayout.EAST);

        // Ajout des images en dessous de la description
        JPanel imagesPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        imagesPanel.setBackground(Color.BLACK);

        int maxWidth = 400;
        File folder = new File("files/" + jeu.nom);
        File[] listOfFiles = folder.listFiles();

        if (listOfFiles != null) {
            for (int i = 0; i < Math.min(3, listOfFiles.length); i++) {
                if (listOfFiles[i].isFile()) {
                    ImageIcon icon = new ImageIcon(listOfFiles[i].getAbsolutePath());
                    Image scaledImage = icon.getImage().getScaledInstance(maxWidth, -1, Image.SCALE_SMOOTH);
                    ImageIcon scaledIcon = new ImageIcon(scaledImage);
                    JLabel imageLabel1 = new JLabel(scaledIcon);
                    imagesPanel.add(imageLabel1);
                }
            }
        }

        add(imagesPanel, BorderLayout.SOUTH);

        // Ajout de la section de commentaires en dessous des jeux en recommandation
        JPanel commentsPanel = new JPanel(new BorderLayout());
        commentsPanel.setBackground(Color.BLACK);
        
        // Panneau des recommandations
        JPanel recommendationsPanel = new JPanel(new BorderLayout());
        recommendationsPanel.setBackground(Color.BLACK);
        recommendationsPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.WHITE), "Commentaire", 0, 0, new Font("Arial", Font.BOLD, 18), Color.WHITE));
        

        // Ajouter les commentaires en dessous des recommandations
        JPanel commentsSection = new JPanel(new BorderLayout());
        commentsSection.setBackground(Color.BLACK);
        
        // Zone de texte pour entrer les commentaires
        JTextField commentField = new JTextField();
        commentField.setForeground(Color.WHITE);
        commentField.setBackground(Color.DARK_GRAY);
        commentField.setFont(new Font("Arial", Font.PLAIN, 16));
        commentsSection.add(commentField, BorderLayout.CENTER);

        // Bouton pour soumettre les commentaires
        JButton submitCommentButton = new JButton("Ajouter un commentaire");
        submitCommentButton.setFont(new Font("Arial", Font.BOLD, 16));
        commentsSection.add(submitCommentButton, BorderLayout.EAST);

        commentsPanel.add(commentsSection, BorderLayout.NORTH);

        // Panneau pour afficher les commentaires soumis
        JPanel displayCommentsPanel = new JPanel();
        displayCommentsPanel.setBackground(Color.BLACK);
        commentsPanel.add(displayCommentsPanel, BorderLayout.CENTER);
        
        submitCommentButton.addActionListener(e -> {
            String comment = commentField.getText().trim();
            if (!comment.isEmpty()) {
                comments.add(comment);
                commentField.setText("");
                displayComments(displayCommentsPanel);
            }
        });
        
        recommendationsPanel.add(commentsPanel, BorderLayout.SOUTH);
        add(recommendationsPanel, BorderLayout.SOUTH);
    }

    private Border createGameBorder(String name) {
        Border emptyBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        return BorderFactory.createCompoundBorder(new DropShadowBorder(), emptyBorder);
    }

    private void displayComments(JPanel commentsPanel) {
        commentsPanel.removeAll();
        commentsPanel.setLayout(new BoxLayout(commentsPanel, BoxLayout.Y_AXIS));
        for (String comment : comments) {
            JLabel commentLabel = new JLabel(comment);
            commentLabel.setForeground(Color.WHITE);
            commentLabel.setFont(new Font("Arial", Font.PLAIN, 16));
            commentsPanel.add(commentLabel);
        }
        commentsPanel.revalidate();
        commentsPanel.repaint();
    }
}
