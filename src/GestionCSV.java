import org.w3c.dom.*; // Import des classes pour manipuler le DOM XML
import javax.xml.parsers.*; // Import des classes pour le parsing XML
import javax.xml.transform.*; // Import des classes pour la transformation XML
import javax.xml.transform.dom.*; // Import des classes pour la transformation du DOM vers XML
import javax.xml.transform.stream.*; // Import des classes pour la sortie du stream XML
import javax.swing.*; // Import des classes pour l'interface graphique Swing
import java.awt.*; // Import des classes pour les composants graphiques AWT
import java.awt.event.*; // Import des classes pour les événements AWT
import java.io.File; // Import des classes pour la manipulation des fichiers

public class GestionCSV {
	public Document document; // Le document XML en mémoire
    private String xmlFilePath; // Chemin vers le fichier XML
    
    public GestionCSV(String chemin) {
    	this.xmlFilePath=chemin;
    }
    
    public Document chargerXML(String xmlFilePath) {
    	try{
    		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder builder = factory.newDocumentBuilder();
	        this.document = builder.parse(new File(xmlFilePath));
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	return this.document;
    }
    
    public NodeList ListeJeux() {
    	NodeList gamesList = document.getElementsByTagName("jeu"); // récupérer une liste des jeux
    	return gamesList;
    }

}
