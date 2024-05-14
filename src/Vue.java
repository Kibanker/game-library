import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.*;

public class Vue extends JFrame {

	private BackgroundPanel accueilPanel;
    private JPanel cataloguePanel;
    private JPanel categoriePanel;
    private JPanel detailJeuPanel;
    private JPanel contactPanel;
    Color lightBlue = new Color(180, 220, 250);
    
    private ArrayList<Jeu> biblio;


    public Vue(ArrayList<Jeu> biblio) {
        super("Virtual Arcade");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.BLACK); 

        ImageIcon icon = new ImageIcon("files/appBg.png"); 
        setIconImage(icon.getImage());

        JLabel titleLabel = new JLabel("Virtual Arcade");
        titleLabel.setForeground(lightBlue);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 45)); 
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel catalogueLabel = new JLabel("CATALOGUE");
        JLabel categorieLabel = new JLabel("CATEGORIES");

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
                afficherPageAccueilCategorie();
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
        
        accueilPanel = new BackgroundPanel("files/appBg.png");
        accueilPanel.setLayout(new GridLayout(3, 1));
        accueilPanel.setBackground(Color.BLACK);
        accueilPanel.add(titleLabel);
        accueilPanel.add(catalogueLabel);
        accueilPanel.add(categorieLabel);
        
        this.biblio = biblio;
        

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
            imageLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
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
    
    private void afficherPageContact() {
        getContentPane().removeAll();
        
        contactPanel = new JPanel();
        contactPanel.setBackground(Color.BLACK);
        contactPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel titleLabel = new JLabel("Des questions? Des recommandations?");
        titleLabel.setForeground(lightBlue);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 35));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        contactPanel.add(titleLabel, gbc);

        JLabel subtitleLabel = new JLabel("Contactez-nous!");
        subtitleLabel.setForeground(Color.WHITE);
        subtitleLabel.setFont(new Font("Arial", Font.BOLD, 25));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        contactPanel.add(subtitleLabel, gbc);

        JLabel nameLabel = new JLabel("Nom:");
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        contactPanel.add(nameLabel, gbc);

        JTextField nameField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.LINE_START;
        contactPanel.add(nameField, gbc);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setForeground(Color.WHITE);
        emailLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.LINE_END;
        contactPanel.add(emailLabel, gbc);

        JTextField emailField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.LINE_START;
        contactPanel.add(emailField, gbc);

        JLabel messageLabel = new JLabel("Message:");
        messageLabel.setForeground(Color.WHITE);
        messageLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.LINE_END;
        contactPanel.add(messageLabel, gbc);

        JTextArea messageArea = new JTextArea(5, 20);
        messageArea.setLineWrap(true);
        messageArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(messageArea);
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.LINE_START;
        contactPanel.add(scrollPane, gbc);

        JButton submitButton = new JButton("Envoyer");
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.LINE_START;
        contactPanel.add(submitButton, gbc);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String email = emailField.getText();
                String message = messageArea.getText();

                if (name.isEmpty() || email.isEmpty() || message.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Tous les champs doivent être remplis", "Erreur", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Message envoyé avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
                    nameField.setText("");
                    emailField.setText("");
                    messageArea.setText("");
                }
            }
        });

        add(contactPanel, BorderLayout.CENTER);

        revalidate();
        repaint();
    }


    private void afficherPageAccueilCategorie() {
        getContentPane().removeAll(); 

        JPanel categoryPanel = new JPanel();
        categoryPanel.setLayout(new GridLayout(0, 1)); // Utiliser une disposition en colonnes pour afficher les catégories
        categoryPanel.setBackground(Color.BLACK); 
        JLabel titleLabel = new JLabel("CATEGORIES");
        titleLabel.setForeground(lightBlue);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 45)); 
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        categoryPanel.add(titleLabel);

        // Liste des catégories disponibles
        String[] categories = {"Action", "Aventure", "Course", "Réflexion", "Simulation", "Stratégie", "Sport", "Combat"};

        for (String category : categories) {
            JLabel categoryLabel = new JLabel(category);
            categoryLabel.setForeground(Color.WHITE);
            categoryLabel.setFont(new Font("Arial", Font.BOLD, 30)); 
            categoryLabel.setHorizontalAlignment(SwingConstants.CENTER);
            categoryLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));

            // Ajouter un écouteur d'événements pour rediriger vers la page de la catégorie correspondante
            categoryLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    afficherPageCategorie(category);
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    categoryLabel.setForeground(lightBlue);
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    categoryLabel.setForeground(Color.WHITE);
                }
            });

            categoryPanel.add(categoryLabel);
        }

        JScrollPane categoryScrollPane = new JScrollPane(categoryPanel);
        categoryScrollPane.setBorder(BorderFactory.createEmptyBorder());
        categoryScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        categoryScrollPane.setOpaque(true); 
        categoryScrollPane.getViewport().setOpaque(true); 

        add(categoryScrollPane, BorderLayout.CENTER); 

        revalidate(); 
        repaint();
    }


    private void initMenuBar() {
        // Création de la barre de menu
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(Color.LIGHT_GRAY);
        setJMenuBar(menuBar);
        
     // Code ajouté pour le titre de l'application dans la barre de menu
        JLabel titleLabel = new JLabel("Virtual Arcade");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 25)); // Utilisez une police similaire à Netflix
        titleLabel.setForeground(lightBlue); // Couleur bleu clair
        menuBar.add(Box.createHorizontalStrut(20)); // Ajoute un espace
        menuBar.add(titleLabel);

        // Création des menus "Accueil" et "Catégories"
        JMenu menu = new JMenu("Accueil");
        menu.setCursor(new Cursor(Cursor.HAND_CURSOR));
        JMenu categoriesMenu = new JMenu("Catégories");
        categoriesMenu.setCursor(new Cursor(Cursor.HAND_CURSOR));
        JMenu catalogueMenu = new JMenu("Catalogue");
        catalogueMenu.setCursor(new Cursor(Cursor.HAND_CURSOR));// Nouveau menu pour le catalogue
        JMenu contactMenu = new JMenu("Contact");
        contactMenu.setCursor(new Cursor(Cursor.HAND_CURSOR));
        

        // Ajout d'un écouteur d'événements pour le menu "Accueil"
        menu.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                afficherPageAccueil();
            }
        });

        // Ajout d'un écouteur d'événements pour le menu "Catalogue"
        catalogueMenu.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                afficherPageCatalogue();
            }
        });
        
        contactMenu.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                afficherPageContact();
            }
            
        });

        // Ajout des menus à la barre de menu
        menuBar.add(menu);
        menuBar.add(catalogueMenu); // Ajout du nouveau menu "Catalogue"
        menuBar.add(categoriesMenu);
        

        // Configuration des couleurs des menus
        menu.setBackground(new Color(173, 216, 230));
        contactMenu.setBackground(new Color(124, 154, 187));
        categoriesMenu.setBackground(new Color(173, 216, 230));
        catalogueMenu.setBackground(new Color(173, 216, 230));

        // Liste des catégories disponibles
        String[] categories = {"Action", "Aventure", "Course", "Réflexion", "Simulation", "Stratégie", "Sport", "Combat"};

        // Création d'un bouton pour chaque catégorie
        for (String category : categories) {
            JMenuItem categoryButton = new JMenuItem(category);
            categoryButton.setBackground(new Color(173, 216, 230));

            // Ajout d'un écouteur d'événements pour chaque bouton de catégorie
            categoryButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Affichage de la page correspondante à la catégorie sélectionnée
                    afficherPageCategorie(category);
                }
            });

            // Ajout des boutons de catégorie au menu "Catégories"
            categoriesMenu.add(categoryButton);
        }

        // Barre de recherche
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panel.setBackground(Color.LIGHT_GRAY);

        JTextField searchField = new JTextField(20);
        JButton searchButton = new JButton("Rechercher");
        
        
        panel.add(contactMenu);
        panel.add(searchField);
        panel.add(searchButton);
        menuBar.add(panel, BorderLayout.PAGE_END);

        // Ajout d'un ActionListener pour le bouton de recherche
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtenir le texte de recherche saisi par l'utilisateur
                String searchTerm = searchField.getText();
                // Appel de la méthode de recherche avec le terme de recherche
                afficherRecherche(searchTerm);
            }
        });
    }


    private Border createGameBorder(String name) {
        Border lineBorder = BorderFactory.createLineBorder(Color.GRAY); 
        Border emptyBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5); 
        TitledBorder titledBorder = BorderFactory.createTitledBorder(lineBorder, name); 
        titledBorder.setTitleColor(Color.WHITE); 
        Border compoundBorder = BorderFactory.createCompoundBorder(titledBorder, emptyBorder); 
        return BorderFactory.createCompoundBorder(new DropShadowBorder(), compoundBorder); 
    }
    
    private void afficherRecherche(String search) {
    	getContentPane().removeAll();
    	
    	
    	JPanel recherchePanel = new JPanel(new BorderLayout());
    	recherchePanel.setBackground(Color.BLACK);
    	
    	ArrayList<Jeu> rechercheJeux = new ArrayList<>();
        for (Jeu jeu : biblio) {
            if (jeu.nom.toLowerCase().contains(search.toLowerCase())) {
            	rechercheJeux.add(jeu);
            }
        }
        
        System.out.println("Jeux dans la recherche " + search);
        for (int i = 0; i < rechercheJeux.size(); i++) {
			System.out.println(rechercheJeux.get(i).nom);
		}
        
        
        JPanel jeuxRecherchePanel = new JPanel(new GridLayout(0, 4, 20, 15)); // Utiliser une grille
        jeuxRecherchePanel.setBackground(Color.BLACK);

        for (Jeu jeu : rechercheJeux) {
            JPanel panel = new JPanel(new BorderLayout());
            panel.setBorder(createGameBorder(jeu.nom));
            panel.setBackground(Color.BLACK);

            JLabel imageLabel = new JLabel(jeu.image);
            imageLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
            imageLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
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

            jeuxRecherchePanel.add(panel);
        }
        
        
        // Ajouter le panneau des jeux à un JScrollPane pour permettre le défilement
        JScrollPane jeuxScrollPane = new JScrollPane(jeuxRecherchePanel);
        jeuxScrollPane.setBorder(BorderFactory.createEmptyBorder());
        jeuxScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        jeuxScrollPane.setOpaque(true);
        jeuxScrollPane.getViewport().setOpaque(true);
        recherchePanel.add(jeuxScrollPane, BorderLayout.CENTER); // Ajouter le JScrollPane au centre

        
        add(recherchePanel, BorderLayout.CENTER);

        revalidate();  // Mettre à jour l'interface utilisateur
        repaint();
    	
    }

    private void afficherFenetreDetailJeu(Jeu jeu) {
        getContentPane().removeAll();
        detailJeuPanel.removeAll(); 

        DescriptionJeuPanel descriptionPanel = new DescriptionJeuPanel(jeu);
        
        JButton retourButton = new JButton("◄ Retour au catalogue");
        retourButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        retourButton.setForeground(Color.WHITE);
        retourButton.setBackground(Color.BLACK);
        retourButton.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        retourButton.addActionListener(e -> afficherPageCatalogue());
        retourButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
            	retourButton.setForeground(lightBlue); 
            }

            @Override
            public void mouseExited(MouseEvent e) {
            	retourButton.setForeground(Color.WHITE); 
            }
        });

        JPanel buttonPanel = new JPanel(new BorderLayout());
        buttonPanel.setBackground(Color.BLACK);
        buttonPanel.add(retourButton, BorderLayout.WEST);
        
        // Utilisation d'un GridBagLayout pour disposer les composants
        detailJeuPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        // Ajout des détails à gauche de la description
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.LINE_START;
        detailJeuPanel.add(buttonPanel, gbc); // Ajout du bouton de retour en haut à gauche
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        detailJeuPanel.add(descriptionPanel, gbc); // Ajout de la description au centre
     
        
        
        JPanel recommendations = new JPanel(new GridLayout(0, 3, 20, 15));
        recommendations.setBackground(Color.BLACK);
        
        ArrayList<Jeu> reco = new ArrayList<Jeu>();
        
        for (Jeu j : biblio) {
        	if (j.categorie.equals(jeu.categorie) && !j.nom.equals(jeu.nom)) {
				reco.add(j);
			}
        }
        
        while(reco.size() > 3) {
        	reco.remove(reco.size()-1);
        }
        
        for (Jeu j : reco) {
			JPanel panel = new JPanel(new BorderLayout());
			panel.setBorder(createGameBorder(j.nom));
            panel.setBackground(Color.BLACK);
            

            JLabel imageLabel = new JLabel(j.image);
            imageLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
            imageLabel.setBackground(Color.BLACK);
            imageLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
            imageLabel.setOpaque(true);
            imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
            imageLabel.setVerticalAlignment(SwingConstants.CENTER);
            panel.add(imageLabel, BorderLayout.CENTER);
            
            imageLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    afficherFenetreDetailJeu(j);
                }
            });

            recommendations.add(panel);
			
		}
        
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.ABOVE_BASELINE;
        JLabel rec = new JLabel("Dans la même catégorie: " + jeu.categorie);
        rec.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0));
        rec.setForeground(Color.WHITE);
        rec.setFont(new Font("Arial", Font.BOLD, 25));
        JPanel recoPanel = new JPanel(new BorderLayout());
        recoPanel.setBackground(Color.BLACK);
        recoPanel.add(rec, BorderLayout.NORTH);
        if(reco.size() >= 1) {
        	detailJeuPanel.add(recoPanel, gbc);
        }
        
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.BASELINE;

        if(reco.size() >= 1) {
        	detailJeuPanel.add(recommendations, gbc);
        }
        
        
        
        JScrollPane detailScrollPane = new JScrollPane(detailJeuPanel);
        detailScrollPane.setBorder(BorderFactory.createEmptyBorder());
        detailScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        detailScrollPane.setOpaque(true);
        detailScrollPane.getViewport().setOpaque(true);
        add(detailScrollPane, BorderLayout.CENTER);

        revalidate();
        repaint();
    }
    
    private void afficherPageCategorie(String category) {
        getContentPane().removeAll();  // Nettoyer le contenu actuel

        // Créer un nouveau panneau pour afficher les jeux de la catégorie sélectionnée
        JPanel categoryPanel = new JPanel(new BorderLayout()); // Utiliser BorderLayout
        categoryPanel.setBackground(Color.BLACK);

        // Ajouter un titre pour indiquer la catégorie sélectionnée
        JLabel categoryTitle = new JLabel(category);
        categoryTitle.setForeground(Color.WHITE);
        categoryTitle.setFont(new Font("Arial", Font.BOLD, 35));
        categoryTitle.setHorizontalAlignment(SwingConstants.CENTER);
        categoryPanel.add(categoryTitle, BorderLayout.NORTH); // Ajouter le titre en haut

        // Filtrer les jeux de la catégorie sélectionnée
        ArrayList<Jeu> jeuxDeCategorie = new ArrayList<>();
        for (Jeu jeu : biblio) {
            if (jeu.categorie.equals(category)) {
                jeuxDeCategorie.add(jeu);
            }
        }
        
        System.out.println("Nombre de jeux dans la catégorie " + category + ": " + jeuxDeCategorie.size());


        // Créer un panneau pour les jeux avec une grille pour les organiser
        JPanel jeuxPanel = new JPanel(new GridLayout(0, 4, 20, 15)); // Utiliser une grille
        jeuxPanel.setBackground(Color.BLACK);

        for (Jeu jeu : jeuxDeCategorie) {
            JPanel panel = new JPanel(new BorderLayout());
            panel.setBorder(createGameBorder(jeu.nom));
            panel.setBackground(Color.BLACK);

            JLabel imageLabel = new JLabel(jeu.image);
            imageLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
            imageLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
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

            jeuxPanel.add(panel);
        }

        // Ajouter le panneau des jeux à un JScrollPane pour permettre le défilement
        JScrollPane jeuxScrollPane = new JScrollPane(jeuxPanel);
        jeuxScrollPane.setBorder(BorderFactory.createEmptyBorder());
        jeuxScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        jeuxScrollPane.setOpaque(true);
        jeuxScrollPane.getViewport().setOpaque(true);
        categoryPanel.add(jeuxScrollPane, BorderLayout.CENTER); // Ajouter le JScrollPane au centre

        // Ajouter le panneau de la catégorie à la vue
        add(categoryPanel, BorderLayout.CENTER);

        revalidate();  // Mettre à jour l'interface utilisateur
        repaint();
    }
}
