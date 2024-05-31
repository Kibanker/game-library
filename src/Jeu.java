import java.awt.Image;
import java.io.File;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.ImageIcon;
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

public class Jeu implements Serializable{ // Modèle
    
    private static final long serialVersionUID = 1L;
	// Attributs généraux
    String resume;
    String nom;
    String categorie;
    String dateDeSortie;
    String entreprise;
    ImageIcon image;
    //String comment;
    double noteGen; // note générale
    double noteCrea; // note de création
    double noteUtilisateur; // note donnée par l'utilisateur
    
    // Attributs éventuels
    String mediaAssocie; // film, série ou anime associé
    String son;
    String nominations;
    // Chemin du fichier XML
    String FilePathXml = "files/games.xml";

    
    public Jeu(String resume, String nom, String cat, String date, String entreprise, double noteG, double noteC, String img, String media, String son, String nominations) {
        this.resume = resume;
        this.nom = nom;
        this.categorie = cat;
        this.dateDeSortie = date;
        this.entreprise = entreprise;
        this.noteGen = noteG;
        this.noteCrea = noteC;
        this.noteUtilisateur = -1; // Initialiser la note utilisateur à -1 par défaut
        //this.comment= ; // Ne pas oublier d'ajouter un attribut commentaire au constructeur et compléter en fonction
        
        ImageIcon image = new ImageIcon(img);
        this.image = new ImageIcon(image.getImage().getScaledInstance(130*2, 180*2, Image.SCALE_SMOOTH));
        
        this.mediaAssocie = media;
        this.son = son;
        this.nominations = nominations;
    }
    
    public String getNom() {
    	return this.nom;   
    }
    public String getCategorie() {
    	return this.categorie;   
    }
    public String getDateSortie() {
    	return this.dateDeSortie;
    }
    public String getEntreprise() {
    	return this.entreprise;
    }
    public double getNoteGen() {
    	return this.noteGen;
    }
    public double getNoteCrea() {
    	return this.noteCrea;
    }

    public ImageIcon getImage() {
    	return this.image;
    }
    public String getMedia() {
    	return this.mediaAssocie;
    }
    public String getSon() {
    	return this.son;
    }
    public String getNominations() {
    	return this.nominations;
    }
    protected void modifyGameNote(String gameId, int newNote) {
        try {
            File file = new File(FilePathXml);
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
            StreamResult result = new StreamResult(new FileOutputStream(FilePathXml));
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(source, result);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    protected void modifyGameComment( String gameId, String newComment) {
        try {
            File file = new File(FilePathXml);
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
            StreamResult result = new StreamResult(new FileOutputStream(FilePathXml));
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(source, result);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	 
    public String getGameNote(String gameId) {
    	String GameNote = new String();
        try {
            File file = new File(FilePathXml);
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
                    	String laNote = new String();
                    	laNote = element.getElementsByTagName("note").item(0).getTextContent();
                        GameNote = laNote;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return GameNote ;
    }
    public ArrayList<String> getGameComment(String gameId) {
    	ArrayList<String> GameComment = new ArrayList<String>();
        try {
            File file = new File(FilePathXml);
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(file);
            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName("jeu");
            
            for (int i = 0; i < nodeList.getLength(); i++) { 
            	Node node =nodeList.item(i);
            		  
				if (node.getNodeType() == Node.ELEMENT_NODE) { Element element = (Element)
				node; String id =
				element.getElementsByTagName("id").item(0).getTextContent();
				  
					if (id.equals(gameId)) {   
						NodeList commentNodes = element.getElementsByTagName("uncommentaire"); 
						// On accède à la balise commentaire du jeu
						for (int j = 0; j < commentNodes.getLength(); j++) {
							Node commentNode = commentNodes.item(j);
							GameComment.add(commentNode.getTextContent()); 
						}					  
					}
				}
			}
        }
         catch (Exception e) {
            e.printStackTrace();
        }
        return GameComment ;
    }
}
