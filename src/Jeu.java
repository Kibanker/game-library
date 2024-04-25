import java.awt.Image;

import javax.swing.ImageIcon;

public class Jeu {
	
	// Attributs généraux
	String resume;
	String nom;
	String categorie;
	String dateDeSortie;
	String entreprise;
	ImageIcon image;
	
	
	int noteGen; // note générale
	int noteCrea; // notre note (nos notes?)
	
	// Attributs éventuels
	String mediaAssocie; //  film ou serie ou anime
	String son;
	String nominations;
	
	public Jeu(String resume, String nom, String cat, String annee, String entreprise, int noteG, int noteC, String img) {
		this.resume = resume;
		this.nom = resume;
		this.categorie = resume;
		this.dateDeSortie = annee;
		this.entreprise = entreprise;
		this.noteGen = noteG;
		this.noteCrea = noteC;
		
		ImageIcon image = new ImageIcon(img);
		this.image = new ImageIcon(image.getImage().getScaledInstance(130, 180, Image.SCALE_SMOOTH));
	}
	
	public Jeu(String resume, String nom, String cat, String date, String entreprise, int noteG, int noteC, String img, String media, String son, String nominations) {
		this.resume = resume;
		this.nom = resume;
		this.categorie = resume;
		this.dateDeSortie = date;
		this.entreprise = entreprise;
		this.noteGen = noteG;
		this.noteCrea = noteC;
		
		ImageIcon image = new ImageIcon(img);
		this.image = new ImageIcon(image.getImage().getScaledInstance(130, 180, Image.SCALE_SMOOTH));
		
		this.mediaAssocie = media;
		this.son = son;
		this.nominations = nominations;
	}
	
	
	
	
	
	
}
