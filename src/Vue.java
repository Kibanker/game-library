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
    private JPanel detailJeuPanel;
    Color lightBlue = new Color(180, 220, 250);

    public Vue(ArrayList<Jeu> biblio) {
        super("Virtual Arcade");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.BLACK); 

        ImageIcon icon = new ImageIcon("files/appIcon.jpg"); 
        setIconImage(icon.getImage());

        JLabel titleLabel = new JLabel("Virtual Arcade");
        titleLabel.setForeground(lightBlue);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 45)); 
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

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

        catalogueLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                afficherPageCatalogue();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                catalogueLabel.setForeground(lightBlue); 
            }

            @Override
            public void mouseExited(MouseEvent e) {
                catalogueLabel.setForeground(Color.WHITE); 
            }
        });

        categorieLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                afficherPageCategorie();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                categorieLabel.setForeground(lightBlue); 
            }

            @Override
            public void mouseExited(MouseEvent e) {
                categorieLabel.setForeground(Color.WHITE); 
            }
        });

        accueilPanel = new JPanel(new GridLayout(3, 1)); 
        accueilPanel.setBackground(Color.BLACK);
        accueilPanel.add(titleLabel);
        accueilPanel.add(catalogueLabel);
        accueilPanel.add(categorieLabel);

        add(accueilPanel, BorderLayout.CENTER);

        cataloguePanel = new JPanel();
        cataloguePanel.setBackground(Color.BLACK);

        categoriePanel = new JPanel();
        categoriePanel.setBackground(Color.BLACK);
        
        detailJeuPanel = new JPanel();
        detailJeuPanel.setBackground(Color.BLACK);
        detailJeuPanel.setLayout(new BorderLayout());

        initMenuBar();

        int numColumns = 4;
        int numRows = (int) Math.ceil((double) biblio.size() / numColumns);

        cataloguePanel = new JPanel(new GridLayout(numRows, numColumns, 20, 15)); 
        cataloguePanel.setBackground(Color.BLACK); 

        for (Jeu jeu : biblio) {
            JPanel panel = new JPanel(new BorderLayout());
            panel.setBorder(createGameBorder(jeu.nom)); 
            panel.setBackground(Color.BLACK); 

            JLabel imageLabel = new JLabel(jeu.image);
            imageLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0)); 
            imageLabel.setBackground(Color.BLACK); 
            imageLabel.setOpaque(true);
            imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
            imageLabel.setVerticalAlignment(SwingConstants.CENTER);
            panel.add(imageLabel, BorderLayout.CENTER);

            imageLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    afficherFenetreDetailJeu(jeu);
                }
            });

            cataloguePanel.add(panel);
        }

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH); 

        afficherPageAccueil();
    }


    private void afficherPageAccueil() {
        getContentPane().removeAll(); 
        add(accueilPanel, BorderLayout.CENTER); 
        revalidate(); 
        repaint();
    }

    private void afficherPageCatalogue() {
        getContentPane().removeAll(); 
        add(cataloguePanel, BorderLayout.CENTER); 
        JScrollPane catalogueScrollPane = new JScrollPane(cataloguePanel);
        catalogueScrollPane.setBorder(BorderFactory.createEmptyBorder());
        catalogueScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        catalogueScrollPane.setOpaque(true); 
        catalogueScrollPane.getViewport().setOpaque(true); 
        add(catalogueScrollPane, BorderLayout.CENTER);
        revalidate(); 
        repaint();
    }

    private void afficherPageCategorie() {
        getContentPane().removeAll(); 
        add(categoriePanel, BorderLayout.CENTER); 
        revalidate(); 
        repaint();
    }


    private void initMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(lightBlue);
        setJMenuBar(menuBar);

        JMenu menu = new JMenu("Accueil");
        JMenu menu2 = new JMenu("Catégories");
        menuBar.add(menu);
        menuBar.add(menu2);

        menu.setBackground(new Color(173, 216, 230)); 
        menu2.setBackground(new Color(173, 216, 230)); 

        String[] categories = {"Action", "Aventure", "Course", "Réflexion", "Simulation", "Stratégie", "Sport"};
        for (String category : categories) {
            JMenuItem subMenu = new JMenuItem(category);
            menu2.add(subMenu);
            subMenu.setBackground(new Color(173, 216, 230)); 
        }
    }

    private Border createGameBorder(String name) {
        Border lineBorder = BorderFactory.createLineBorder(Color.GRAY); 
        Border emptyBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5); 
        TitledBorder titledBorder = BorderFactory.createTitledBorder(lineBorder, name); 
        titledBorder.setTitleColor(Color.WHITE); 
        Border compoundBorder = BorderFactory.createCompoundBorder(titledBorder, emptyBorder); 
        return BorderFactory.createCompoundBorder(new DropShadowBorder(), compoundBorder); 
    }

    private void afficherFenetreDetailJeu(Jeu jeu) {
        getContentPane().removeAll();
        detailJeuPanel.removeAll(); 

        DescriptionJeuPanel descriptionPanel = new DescriptionJeuPanel(jeu);
        
        JButton retourButton = new JButton("← Retour au catalogue");
        retourButton.setForeground(Color.WHITE);
        retourButton.setBackground(Color.BLACK);
        retourButton.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        retourButton.addActionListener(e -> afficherPageCatalogue());

        JPanel buttonPanel = new JPanel(new BorderLayout());
        buttonPanel.setBackground(Color.BLACK);
        buttonPanel.add(retourButton, BorderLayout.WEST);

        detailJeuPanel.add(buttonPanel, BorderLayout.NORTH);
        detailJeuPanel.add(descriptionPanel, BorderLayout.CENTER);

        JScrollPane detailScrollPane = new JScrollPane(detailJeuPanel);
        detailScrollPane.setBorder(BorderFactory.createEmptyBorder());
        detailScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        detailScrollPane.setOpaque(true);
        detailScrollPane.getViewport().setOpaque(true);
        add(detailScrollPane, BorderLayout.CENTER);

        revalidate();
        repaint();
    }
}
