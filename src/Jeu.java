import java.awt.Image;

import javax.swing.ImageIcon;

public class Jeu { // Modèle
	
	// Attributs généraux
	String resume;
	String nom;
	String categorie;
	String dateDeSortie;
	String entreprise;
	ImageIcon image;
	
	
	double noteGen; // note générale
	double noteCrea; // notre note (nos notes?)
	
	// Attributs éventuels
	String mediaAssocie; //  film ou serie ou anime
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
		
		ImageIcon image = new ImageIcon(img);
		this.image = new ImageIcon(image.getImage().getScaledInstance(130*2, 180*2, Image.SCALE_SMOOTH));
		
		this.mediaAssocie = media;
		this.son = son;
		this.nominations = nominations;
	}
	
	
	
	
	
	
}
