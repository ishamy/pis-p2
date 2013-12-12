package model;

import java.awt.Color;

import javax.swing.JColorChooser;

public class RoomModel {
	
	public static final int RECTANGLE = 0;
	public static final int ELLIPSE = 1;
	public static final int TRIANGLE = 2;
	
	String nom;
	boolean lumiere;
	int temperature;
	Color couleurFond;
	Color couleurContour;
	int forme;
	JColorChooser colorChooser;
	
	

	public RoomModel (String nom) {
		this.nom = nom;
		lumiere = false;
		temperature = 20;
		couleurFond = Color.WHITE;
		couleurContour = Color.BLACK;
		forme = RECTANGLE;
		colorChooser = new JColorChooser();
	}

	public int getForme() {
		return forme;
	}

	public void setForme(int forme) {
		this.forme = forme;
	}

	public int getTemperature() {
		return temperature;
	}

	public void setTemperature(int temperature) {
		this.temperature = temperature;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public boolean isLumiere() {
		return lumiere;
	}

	public void setLumiere(boolean lumiere) {
		this.lumiere = lumiere;
	}
	
	public Color getCouleurFond(){
		return couleurFond;
	}
	public Color getCouleurContour(){
		return couleurContour;
	}
	

	public void setCouleurFond(Color color){
		couleurFond = color;
	}
	public void setCouleurContour(Color color){
		couleurContour= color;
	}
	
	public JColorChooser getColorChooser() {
		return colorChooser;
	}

	public void setColorChooser(JColorChooser colorChooser) {
		this.colorChooser = colorChooser;
	}
	
}
