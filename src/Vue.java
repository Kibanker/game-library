import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.*;

public class Vue extends JFrame {

    public Vue(ArrayList<Jeu> biblio) {
        super("Virtual Arcade");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.BLACK); // Définir le fond noir pour la fenêtre
        
        initMenuBar();

        int numColumns = 4;
        int numRows = (int) Math.ceil((double) biblio.size() / numColumns);

        JPanel contentPane = new JPanel(new GridLayout(numRows, numColumns, 20, 15)); // Espacement de 10 pixels entre les jeux
        contentPane.setBackground(Color.BLACK); // Définir le fond noir pour le contenu
        

        for (Jeu jeu : biblio) {
            JPanel panel = new JPanel(new BorderLayout());
            panel.setBorder(createGameBorder(jeu.nom)); // Créer une bordure personnalisée pour chaque jeu avec le nom à l'intérieur
            panel.setBackground(Color.BLACK); // Définir le fond noir pour chaque jeu
            
            // Image du jeu
            JLabel imageLabel = new JLabel(jeu.image);
            imageLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); // Ajouter des marges à l'image
            imageLabel.setBackground(Color.BLACK); // Définir le fond noir pour l'image
            imageLabel.setOpaque(true);
            imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
            imageLabel.setVerticalAlignment(SwingConstants.CENTER);
            panel.add(imageLabel, BorderLayout.CENTER);

            contentPane.add(panel);
        }

        // Ajout du JPanel à un JScrollPane pour permettre le défilement
        JScrollPane mainScrollPane = new JScrollPane(contentPane);
        mainScrollPane.setBorder(BorderFactory.createEmptyBorder());
        mainScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        mainScrollPane.setOpaque(false);
        mainScrollPane.getViewport().setOpaque(false);
        add(mainScrollPane, BorderLayout.CENTER);
        
        // Ajout du titre "Virtual Arcade" centré au-dessus du JScrollPane
        JLabel titleLabel = new JLabel("Virtual Arcade", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0)); // Espacement par rapport aux jeux
        add(titleLabel, BorderLayout.NORTH);

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
    
    private Border createGameBorder(String name) {
        Border lineBorder = BorderFactory.createLineBorder(Color.GRAY); // Bordure simple
        Border emptyBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5); // Marges
        TitledBorder titledBorder = BorderFactory.createTitledBorder(lineBorder, name); // Bordure avec le nom du jeu à l'intérieur
        titledBorder.setTitleColor(Color.WHITE); // Définir la couleur du titre en blanc
        Border compoundBorder = BorderFactory.createCompoundBorder(titledBorder, emptyBorder); // Bordure composée de la bordure avec le nom et des marges
        return BorderFactory.createCompoundBorder(new DropShadowBorder(), compoundBorder); // Ajout d'une ombre derrière la bordure composée
    }
}
