package controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JColorChooser;
import javax.swing.JOptionPane;

import view.*;

import model.HouseGestion;
import model.HouseModel;
import model.RoomModel;

public class CaracteristicViewController implements ActionListener,MouseListener {

	MusicController musicController;

	public CaracteristicViewController() {}

	public CaracteristicViewController(MusicController mc){ musicController = mc; }

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		MainView main = MainView.getMain();
		HouseModel m =  main.getMaison();
		HouseGestion hg= MainView.getHouseGestion();
		ModificationView.getCaracView();
		musicController = MainView.getMusicController();
		RepresentationView viewRep =(RepresentationView)MainView.getRep();

		if (arg0.getActionCommand().equals("Validate")){
			RoomModel p = HouseModel.
			getPieceWhereNom(CaracteristicView.nomPiece);
			if (p != null) 
			{
				if (!CaracteristicView.nomPiece.equals(CaracteristicView.lblNom.getText()))
				{
					if (HouseModel.getPieceWhereNom(CaracteristicView.lblNom.getText()) == null){
						p.setNom(CaracteristicView.lblNom.getText() );
						musicController.changeRoomName(CaracteristicView.nomPiece,
								CaracteristicView.lblNom.getText() );
						viewRep.deleteRoom(CaracteristicView.nomPiece);
						viewRep.addRoom(CaracteristicView.lblNom.getText());
						CaracteristicView.lblNom.setText("-"); 
					}
					else JOptionPane.showMessageDialog(null,
					"This name already exists");
				}

				p.setTemperature((Integer)CaracteristicView.s.getValue());
				hg.enregistrerHouse(m);
			}
			else 
				JOptionPane.showMessageDialog(null,
				"No rooms selected");


			ModificationView.getRoomView().update();
		}
		else if(arg0.getActionCommand().equals("Previous")){

		}
		else if (arg0.getActionCommand().equals("ALL")){
			CaracteristicView.btnValider.setText("Validate all");
			CaracteristicView.lblNom.setText("General");

		}
		else if (arg0.getActionCommand().equals("Validate all")){
			if (HouseModel.pieceList.size() == 0) JOptionPane.showMessageDialog(null,
			"No rooms in the house");
			else m.appliquerPartout();
		}

	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		RepresentationView r = (RepresentationView) MainView.getRep();
		// TODO Auto-generated method stub
		if (arg0.getComponent().getName().equals("Change Background")){
			RoomModel p = HouseModel.
			getPieceWhereNom(CaracteristicView.nomPiece);
			Color color = JColorChooser.showDialog
			(null, "Choisir une couleur",Color.white); 
			if (color != null){
				ColorView.pnlFondApercu.setBackground
				(color);
				if (p != null) 
				{
					p.setCouleurFond(color);
				}	
			}
		}

		else if (arg0.getComponent().getName().equals("Change Outline")){
			RoomModel p = HouseModel.
			getPieceWhereNom(CaracteristicView.nomPiece);
			Color color = JColorChooser.showDialog
			(null, "Choisir une couleur",Color.white); 
			if (color != null){
				ColorView.pnlContourApercu.setBackground
				(color);
				if (p != null) 
				{
					p.setCouleurContour(color);

				}
			}
		}
		ModificationView.getRoomView().update();
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}
}
