package controller;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JRadioButton;
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
			int currentShape = Integer.parseInt(((JRadioButton) arg0.getSource()).getName());

			switch(currentShape) {
			case 0 :
			{
				 p.setForme(RoomModel.RECTANGLE);
				 break;

			}
			case 1:
			{
				 p.setForme(RoomModel.ELLIPSE);
				 break;

			}
			case 2:
			{
				 p.setForme(RoomModel.TRIANGLE);
				 break;

			}
			}
			

			hg.enregistrerHouse(p,
					"Form", p.getForme(), currentShape);

			p.setForme(currentShape);
			rm.update();

		}
		RepresentationView r = (RepresentationView) MainView.getRep();
		//r.deleteRoom(p.getNom());
		//r.addRoom(p.getNom());

		r.update();
	}

}
