import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.*;

public class Vue extends JFrame {
	
    private JPanel accueilPanel;
    private JPanel cataloguePanel;
    private JPanel categoriePanel;
    Color lightBlue = new Color(180, 220, 250);

    public Vue(ArrayList<Jeu> biblio) {
        super("Virtual Arcade");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.BLACK); // Définir le fond noir pour la fenêtre
        
        ImageIcon icon = new ImageIcon("files/appIcon.jpg"); // icon de l'app
        setIconImage(icon.getImage());
        
        
        JLabel titleLabel = new JLabel("Virtual Arcade");
        titleLabel.setForeground(lightBlue);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 45)); // Définir la police et la taille du texte
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        
        // Créer les labels et définition de leur taille
        JLabel catalogueLabel = new JLabel("CATALOGUE");
        JLabel categorieLabel = new JLabel("CATEGORIE");
        
        catalogueLabel.setForeground(Color.WHITE);
        catalogueLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        catalogueLabel.setFont(new Font("Arial", Font.BOLD, 35));
        catalogueLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        
        categorieLabel.setForeground(Color.WHITE);
        categorieLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        categorieLabel.setFont(new Font("Arial", Font.BOLD, 35));
        categorieLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
     // Ajout des écouteurs de souris pour gérer les clics sur les labels
        catalogueLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                afficherPageCatalogue();
            }
            
            @Override
            public void mouseEntered(MouseEvent e) {
                catalogueLabel.setForeground(lightBlue); // Changer la couleur lors du survol
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                catalogueLabel.setForeground(Color.WHITE); // Revenir à la couleur normale
            }
        });
        
        categorieLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                afficherPageCategorie();
            }
            
            @Override
            public void mouseEntered(MouseEvent e) {
                categorieLabel.setForeground(lightBlue); // Changer la couleur lors du survol
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                categorieLabel.setForeground(Color.WHITE); // Revenir à la couleur normale
            }
        });
        
        // Création du panneau d'accueil avec les labels
        accueilPanel = new JPanel(new GridLayout(3, 1)); // Utiliser un gestionnaire de mise en page en grille
        accueilPanel.setBackground(Color.BLACK);
        accueilPanel.add(titleLabel);
        accueilPanel.add(catalogueLabel); // Ajouter le label Catalogue
        accueilPanel.add(categorieLabel); // Ajouter le label Catégorie
        
        // Ajouter le panneau d'accueil à la fenêtre principale
        add(accueilPanel, BorderLayout.CENTER);
        
        // Initialiser les panneaux pour le catalogue et les catégories (vide pour l'instant)
        cataloguePanel = new JPanel();
        cataloguePanel.setBackground(Color.BLACK);
        
        categoriePanel = new JPanel();
        categoriePanel.setBackground(Color.BLACK);

        
        initMenuBar();
        
        // Création des panneaux pour le catalogue et les catégories
        
        categoriePanel = new JPanel();
        categoriePanel.setBackground(Color.BLACK);
        // Code pour remplir le panneau des catégories...

        int numColumns = 4;
        int numRows = (int) Math.ceil((double) biblio.size() / numColumns);

        cataloguePanel = new JPanel(new GridLayout(numRows, numColumns, 20, 15)); // Espacement de 10 pixels entre les jeux
        cataloguePanel.setBackground(Color.BLACK); // Définir le fond noir pour le contenu

        for (Jeu jeu : biblio) {
            JPanel panel = new JPanel(new BorderLayout());
            panel.setBorder(createGameBorder(jeu.nom)); // Créer une bordure personnalisée pour chaque jeu avec le nom à l'intérieur
            panel.setBackground(Color.BLACK); // Définir le fond noir pour chaque jeu
            
            // Image du jeu
            JLabel imageLabel = new JLabel(jeu.image);
            imageLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0)); // Ajouter des marges à l'image
            imageLabel.setBackground(Color.BLACK); // Définir le fond noir pour l'image
            imageLabel.setOpaque(true);
            imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
            imageLabel.setVerticalAlignment(SwingConstants.CENTER);
            panel.add(imageLabel, BorderLayout.CENTER);

            cataloguePanel.add(panel);
        }

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Ouvre l'application en plein écran
        
        afficherPageAccueil();
        
    }
    
    
    private void afficherPageAccueil() {
        getContentPane().removeAll(); // Supprimer tout contenu précédent
        add(accueilPanel, BorderLayout.CENTER); // Ajouter le panneau de l'accueil
        revalidate(); // Rafraîchir l'affichage
        repaint();
    }
    
    private void afficherPageCatalogue() {
        getContentPane().removeAll(); // Supprimer tout contenu précédent
        add(cataloguePanel, BorderLayout.CENTER); // Ajouter le panneau du catalogue
        JScrollPane catalogueScrollPane = new JScrollPane(cataloguePanel);
        catalogueScrollPane.setBorder(BorderFactory.createEmptyBorder());
        catalogueScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        catalogueScrollPane.setOpaque(true); // Définir à true pour peindre le contenu
        catalogueScrollPane.getViewport().setOpaque(true); // Définir à true pour peindre le contenu
        add(catalogueScrollPane,BorderLayout.CENTER);
        revalidate(); // Rafraîchir l'affichage
        repaint();
    }
    
    private void afficherPageCategorie() {
        getContentPane().removeAll(); // Supprimer tout contenu précédent
        add(categoriePanel, BorderLayout.CENTER); // Ajouter le panneau des catégories
        revalidate(); // Rafraîchir l'affichage
        repaint();
    }
    
    
    private void initMenuBar() {
        JMenuBar menuBar = new JMenuBar();
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
    
    private Border createGameBorder(String name) {
        Border lineBorder = BorderFactory.createLineBorder(Color.GRAY); // Bordure simple
        Border emptyBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5); // Marges
        TitledBorder titledBorder = BorderFactory.createTitledBorder(lineBorder, name); // Bordure avec le nom du jeu à l'intérieur
        titledBorder.setTitleColor(Color.WHITE); // Définir la couleur du titre en blanc
        Border compoundBorder = BorderFactory.createCompoundBorder(titledBorder, emptyBorder); // Bordure composée de la bordure avec le nom et des marges
        return BorderFactory.createCompoundBorder(new DropShadowBorder(), compoundBorder); // Ajout d'une ombre derrière la bordure composée
    }
}