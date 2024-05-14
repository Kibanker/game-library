import java.awt.Image;
import java.io.Serializable;

import javax.swing.ImageIcon;

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
    
    public void changeNote(double note) {
    	this.noteUtilisateur=note;
    }
    
//    public void changeComment(String comment) {
//    	this.comment=comment;
//    }
}
