import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class DescriptionJeuPanel extends JPanel {
    private Jeu jeu;
    private boolean voted; // Variable pour suivre si l'utilisateur a déjà voté pour ce jeu
    private String noteCreaNA = "N/A";
    private ArrayList<String> comments; // Liste des commentaires
    JPanel commentsPanel;
    JTextArea commentField;
    String FilePathXml = "files/games.xml";

    public DescriptionJeuPanel(Jeu jeu) {
    	
        this.jeu = jeu;
        this.voted = false; // Initialiser voted à false pour chaque jeu
        this.comments = new ArrayList<>(); // Initialiser la liste des commentaires
        setLayout(new BorderLayout());
        setBackground(Color.BLACK);
        setBorder(createGameBorder(jeu.nom));
       

        // Nom du jeu
        JPanel nomPanel = new JPanel(new BorderLayout());
        nomPanel.setOpaque(false);
        nomPanel.setBackground(Color.BLACK);
        JLabel nameLabel = new JLabel(jeu.nom);
        nameLabel.setForeground(Color.BLACK);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 24));
        nameLabel.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));
        nomPanel.add(nameLabel);
        add(nomPanel, BorderLayout.NORTH);
        setOpaque(false);

        // Image du jeu en haut à gauche
        JLabel imageLabel = new JLabel(jeu.image);
        imageLabel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        //imageLabel.setBackground(Color.BLACK);
        imageLabel.setOpaque(false);
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(imageLabel, BorderLayout.WEST);

        JPanel resumePanel = new JPanel(new BorderLayout());
        resumePanel.setOpaque(false); // Permet au fond d'écran de s'afficher
        add(resumePanel, BorderLayout.CENTER);

        // Résumé du jeu dans un JScrollPane
        JTextArea summaryArea = new JTextArea(jeu.resume);
        summaryArea.setForeground(Color.BLACK);
        summaryArea.setFont(new Font("Arial", Font.PLAIN, 18));
        summaryArea.setEditable(false);
        summaryArea.setLineWrap(true); // Saut de ligne automatique
        summaryArea.setWrapStyleWord(true); // Saut de ligne après le mot entier
        summaryArea.setOpaque(false);

        

        JPanel summaryPanel = new JPanel(new BorderLayout());
        summaryPanel.setOpaque(false);
        summaryPanel.add(summaryArea, BorderLayout.CENTER);
        resumePanel.add(summaryPanel, BorderLayout.CENTER);

        // Description du jeu à droite de l'image
        JPanel descriptionPanel = new JPanel(new BorderLayout());
        descriptionPanel.setOpaque(false);
        ArrayList<ArrayList<String>> details = getGameDetails(FilePathXml, jeu.nom);
        String note = details.get(0).get(0) ; // On récupère la première liste puis on récupère son premier élément

        JTextArea descriptionArea = new JTextArea();
        if (jeu.noteCrea == -1.0) {
            descriptionArea.setText("Catégorie: " + jeu.categorie + "\n" +
                    "Date de sortie: " + jeu.dateDeSortie + "\n" +
                    "Entreprise: " + jeu.entreprise + "\n" +
                    "Note générale: " + jeu.noteGen + "\n" +
                    "Notre note: " + noteCreaNA + "\n" +
                    "Média associée: " + (jeu.mediaAssocie.equals("null") ? "N/A" : jeu.mediaAssocie) + "\n" +
                    "Nominations: " + (jeu.nominations.equals("null") ? "N/A" : jeu.nominations)+ "\n"+
                    "Note donnée par l'utilisateur: "+ (note.equals("-1") ? "N/A" :note));
        } else {
            descriptionArea.setText("Catégorie: " + jeu.categorie + "\n" +
                    "Date de sortie: " + jeu.dateDeSortie + "\n" +
                    "Entreprise: " + jeu.entreprise + "\n" +
                    "Note générale: " + jeu.noteGen + "\n" +
                    "Notre note: " + jeu.noteCrea + "\n" +
                    "Média associée: " + (jeu.mediaAssocie.equals("null") ? "N/A" : jeu.mediaAssocie) + "\n" +
                    "Nominations: " + (jeu.nominations.equals("null") ? "N/A" : jeu.nominations)+ "\n" +
                    "Note donnée par l'utilisateur: "+ (note.equals("-1") ? "N/A" :note));
        }

        descriptionArea.setForeground(Color.BLACK);
        descriptionArea.setOpaque(false);
        descriptionArea.setFont(new Font("Arial", Font.PLAIN, 18));
        descriptionArea.setEditable(false);
        descriptionArea.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 20));
        descriptionPanel.add(descriptionArea, BorderLayout.CENTER);

        // Ajouter des étoiles pour permettre à l'utilisateur de donner une note
        JPanel ratingPanel = new JPanel();
        ratingPanel.setOpaque(false);
        ratingPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        JLabel ratingLabel = new JLabel("Donner une note: ");
        ratingLabel.setForeground(Color.BLACK);
        ratingLabel.setOpaque(false);
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
                	descriptionArea.setText(enleverDerniereLigne(descriptionArea));
                    descriptionArea.append("\nNote donnée par l'utilisateur: " + rating);
                    modifyGameNote(FilePathXml, jeu.nom, rating);
                    for (Component component : ratingPanel.getComponents()) {
                        if (component instanceof JButton) {
                            ((JButton) component).setEnabled(false);
                        }
                    }
                    voted = true;
                } else {
                    descriptionArea.setText(enleverDerniereLigne(descriptionArea));
                    descriptionArea.append("\n Note donnée par l'utilisateur: " + rating);
                    modifyGameNote(FilePathXml, jeu.nom, rating);
                }
            });
            ratingPanel.add(starButton);
        }

        descriptionPanel.add(ratingPanel, BorderLayout.SOUTH);
        add(descriptionPanel, BorderLayout.EAST);

        // Ajout des images en dessous de la description
        JPanel imagesPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        imagesPanel.setOpaque(false);

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
                    imageLabel1.setOpaque(false);
                    imagesPanel.add(imageLabel1);
                }
            }
        }

        // Ajout de la section de commentaires en dessous des jeux en recommandation
        commentsPanel = new JPanel(new BorderLayout());
        commentsPanel.setOpaque(false);
        
        // Panneau des recommandations
        JPanel commentairesBorder = new JPanel(new BorderLayout());
        commentairesBorder.setOpaque(false);
        commentairesBorder.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.getColor(note, getBackground())), "Commentaires", 0, 0, new Font("Arial", Font.BOLD, 18), Color.BLACK));
        commentairesBorder.add(commentsPanel);

        // Panneau sud intermédiaire
        JPanel southPanel = new JPanel(new BorderLayout());
        southPanel.setOpaque(false);
        southPanel.add(imagesPanel, BorderLayout.NORTH);
        southPanel.add(commentairesBorder, BorderLayout.SOUTH);

        // Ajout du panneau sud au panneau principal
        add(southPanel, BorderLayout.SOUTH);
    }
    
    public String getGameID() {
    	return jeu.nom ;
    }
    
    public String enleverDerniereLigne(JTextArea descriptionArea) {
        int indexDernierN = descriptionArea.getText().lastIndexOf("\n");
        if (indexDernierN != -1) {
            descriptionArea.setText(descriptionArea.getText().substring(0, indexDernierN)) ;
            return descriptionArea.getText();
        } else {
            return descriptionArea.getText(); // Si aucun retour à la ligne n'est trouvé, retourner la description inchangée
        }
    }
    
    public ArrayList<ArrayList<String>> getGameDetails(String filePathXml, String gameId) {
        ArrayList<ArrayList<String>> details = new ArrayList<>();
        try {
            File file = new File(filePathXml);
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(file);
            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName("jeu");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    String id = element.getElementsByTagName("id").item(0).getTextContent();

                    if (id.equals(gameId)) {
                    	ArrayList<String> laNote = new ArrayList<>();
                    	laNote.add(element.getElementsByTagName("note").item(0).getTextContent());
                        details.add(laNote);
                        
                        NodeList commentNodes = element.getElementsByTagName("uncommentaire"); // On accède à la balise commentaire du jeu
                        ArrayList<String> comments = new ArrayList<>();
                        for (int j = 0; j < commentNodes.getLength(); j++) {
                            Node commentNode = commentNodes.item(j);
                            comments.add(commentNode.getTextContent());
                        }
                        for(String com: comments) {
                        	System.out.println(com );
                        	System.out.println("sep");
                        	System.out.println(comments.size());
                        	System.out.println("");
                        }
                        details.add(comments);
                        
                        break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return details ;
    }
    public static void modifyGameNote(String filePath, String gameId, int newNote) {
        try {
            File file = new File(filePath);
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(file);
            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName("jeu");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Element element = (Element) nodeList.item(i);
                String id = element.getElementsByTagName("id").item(0).getTextContent();
                String note = element.getElementsByTagName("id").item(0).getTextContent();

                if (id.equals(gameId) && !note.equals(String.valueOf(i))) {
                    // Mettre à jour la note du jeu
                    Element noteElement = (Element) element.getElementsByTagName("note").item(0);
                    noteElement.setTextContent(Integer.toString(newNote));
                    break;
                }
            }

            // Écrire les changements dans le fichier XML
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new FileOutputStream(filePath));
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(source, result);

            System.out.println("Note du jeu " + gameId + " modifiée avec succès.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void modifyGameComment(String filePath, String gameId, String newComment) {
        try {
            File file = new File(filePath);
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(file);
            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName("jeu");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Element element = (Element) nodeList.item(i);
                String id = element.getElementsByTagName("id").item(0).getTextContent();
                String commentaire = element.getElementsByTagName("commentaires").item(0).getTextContent();

                if (id.equals(gameId) && !commentaire.equals(newComment)) {
                    // Mettre à jour la note du jeu
                    Element noteElement = (Element) element.getElementsByTagName("commentaires").item(element.getElementsByTagName("commentaire").getLength());
                    // Création d'un nouveau noeud uncommentaire
                    Element newCommentNode = element.getElementsByTagName("commentaires").item(0).getOwnerDocument().createElement("uncommentaire");
                    // Ajout du nouveau commentaire au noeud uncommentaire
                    newCommentNode.setTextContent(newComment);
                    // Ajout du uncommentaire au noeud commentaire
                    noteElement.appendChild(newCommentNode);
                    break;
                }
            }

            // Écrire les changements dans le fichier XML
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new FileOutputStream(filePath));
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(source, result);

            System.out.println("Commentaire ajouter au jeu " + gameId + " modifiée avec succès.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Border createGameBorder(String name) {
        Border emptyBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        return BorderFactory.createCompoundBorder(new DropShadowBorder(), emptyBorder);
    }

    void displayComments(String comment) {
        commentsPanel.removeAll();
        commentsPanel.setLayout(new BoxLayout(commentsPanel, BoxLayout.Y_AXIS));
        
        comments.add(comment);
        
        
        for (String com : comments) {
            JLabel commentLabel = new JLabel(com);
            commentLabel.setForeground(Color.BLACK);
            commentsPanel.add(commentLabel, FlowLayout.LEFT);
		}
        
        commentsPanel.revalidate();
        commentsPanel.repaint();
    }
}
