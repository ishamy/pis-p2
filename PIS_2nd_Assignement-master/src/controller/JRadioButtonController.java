package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.html.FormView;

import model.HouseModel;
import model.MemoryAction;
import model.RoomModel;
import view.CaracteristicView;
import view.FormsView;
import view.MainView;
import view.ModificationView;
import view.RepresentationView;
import view.RoomView;
import view.RoomsView;

public class JRadioButtonController implements ActionListener
	 {

	public JRadioButtonController(){
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		MemoryAction hg= new MemoryAction();

		RoomModel p = HouseModel.
		getPieceWhereNom(CaracteristicView.nomPiece);
		RoomsView rm = (RoomsView)ModificationView.getRoomView();
		if (p != null) 
			{	
				hg.enregistrerHouse(p,
						"Form", p.getForme(),arg0.getActionCommand() );
			
				p.setForme(arg0.getActionCommand());
				rm.update();
	
			}
		RepresentationView r = (RepresentationView) MainView.getRep();
		//r.deleteRoom(p.getNom());
		//r.addRoom(p.getNom());

		r.update();
	}

}