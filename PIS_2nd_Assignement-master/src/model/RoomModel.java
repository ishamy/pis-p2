package model;

import java.awt.Color;

public class RoomModel {
	

	String nom;
	boolean lumiere;
	int temperature;
	Color couleurFond;
	Color couleurContour;
	String forme;
	
	public RoomModel (String nom, int id) {
		this.nom = nom;
		lumiere = false;
		temperature = 20;
		couleurFond = Color.WHITE;
		couleurContour = Color.WHITE;
		forme = "Triangle";
	}

	public String getForme() {
		return forme;
	}

	public void setForme(String forme) {
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
	
	
	
}
