package model;

import java.awt.Color;

import view.MainView;

public class MemoryAction {

	public RoomModel r;
	public String element;
	public String lastValue;
	public String newValue;
	public Color lastColor;
	public Color newColor;
	public MemoryActionGestion mag;

	public MemoryAction(RoomModel r, String elementModif,
			String lValue, String nValue){
		this.r = r;
		element = elementModif;
		lastValue =lValue ;
		newValue =nValue;
		mag = MainView.getHouseGestion();
	}
	public MemoryAction(RoomModel r, String elementModif,
			Color lValue,Color nValue){
		this.r = r;
		element = elementModif;
		lastColor =lValue ;
		newColor =nValue;
		mag = MainView.getHouseGestion();
	}

	public MemoryAction(){
		r = null;
		element = null;
		lastValue =null;
		newValue =null;
		mag = MainView.getHouseGestion();
	}




	public void enregistrerHouse(RoomModel r, String elementModif,
			String lValue, String nValue ){
		MemoryAction ma = new MemoryAction
		(r, elementModif, lValue, nValue);
		mag.enregistrerHouse(ma);
	}

	public void enregistrerHouse(RoomModel r, String elementModif,
			int lValue, int nValue ){
		MemoryAction ma = new MemoryAction
		(r, elementModif, Integer.toString(lValue), Integer.toString(nValue));
		mag.enregistrerHouse(ma);
	}
	
	public void enregistrerHouse(RoomModel r, String elementModif,
			boolean lValue, boolean nValue ){
		MemoryAction ma = new MemoryAction
		(r, elementModif, new Boolean(lValue).toString(),new Boolean(nValue).toString());
		mag.enregistrerHouse(ma);
	}
	
	public void enregistrerHouse(RoomModel r, String elementModif,
			Color lValue, Color nValue ){
		MemoryAction ma = new MemoryAction
		(r, elementModif, lValue,nValue);
		mag.enregistrerHouse(ma);
	}
}
