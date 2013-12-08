package model;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JOptionPane;


import view.CaracteristicView;
import view.ColorView;
import view.MainView;
import view.ModificationView;
import view.RoomView;
import view.RoomsView;


public class HouseModel implements Cloneable{
	
	public static ArrayList<RoomModel> pieceList;
	int cpt;
	
	public HouseModel(){
		pieceList = new ArrayList<RoomModel> ();
		cpt =0;	
	}

	
	public static RoomModel getPieceWhereNom(String i)
	{
		for(RoomModel p : pieceList)
		{
			if (p.getNom().equals(i)  )
			{
				return p;
			}
		}
		return null;
	}
	

	public void appliquerPartout(){
		int temp = (Integer) CaracteristicView.s.getValue();
		boolean lum =CaracteristicView.swt.isSelected();
		Color cf =ColorView.pnlFondApercu.getBackground();
		Color cc =ColorView.pnlContourApercu.getBackground();

		for(RoomModel p : pieceList)
		{
			p.setLumiere(lum);
			p.setTemperature(temp);
			p.setCouleurContour(cc);
			p.setCouleurFond(cf);
		}
	}

	public static void supprimerPiece(String s){
		int i =-1;
		for(RoomModel p : pieceList)
		{
			if (p.getNom().equals(s) )
			{
				i =	pieceList.lastIndexOf(p);
			}
		}
		if (i != -1){
			pieceList.remove(i);
		}

	}


	public void newChambre(String s){
		RoomsView r =(RoomsView)
		ModificationView.getRoomView();
		boolean present= false;
		for(RoomModel p : pieceList)
		{
			if (p.nom.equals(s) ){
				present = true;
				JOptionPane.showMessageDialog(null,
				"Erreur, nom deja existant");
			}
		}
		if (present == false && !s.equals(""))
		{
			RoomModel p = new RoomModel(s,cpt);
			cpt++;
			pieceList.add(p);
			r.update();
		}
	}

	public static ArrayList<RoomModel> getPieceList() {
		return pieceList;
	}

	public static void setPieceList(ArrayList<RoomModel> pieceList) {
		HouseModel.pieceList = pieceList;
	}

	public RoomModel getFirstPiece(){
		if (pieceList.size() != 0 ){
			return pieceList.get(0);
		}
		else return null;
	}
	public HouseModel clone() {
		Object o = null;
		try {
			// On récupère l'instance à renvoyer par l'appel de la 
			// méthode super.clone()
			o = super.clone();
		} catch(CloneNotSupportedException cnse) {
			// Ne devrait jamais arriver car nous implémentons 
			// l'interface Cloneable
			cnse.printStackTrace(System.err);
		}
		// on renvoie le clone
		return (HouseModel)o;
	}

	
}
