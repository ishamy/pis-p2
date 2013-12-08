package controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ButtonModel;
import javax.swing.JColorChooser;
import javax.swing.JOptionPane;
import javax.swing.text.html.FormView;

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

		if (arg0.getActionCommand().equals("Validate")){
			RoomModel p = HouseModel.
			getPieceWhereNom(CaracteristicView.nomPiece);
			if (p != null) 
			{
				if (!CaracteristicView.nomPiece.equals(CaracteristicView.lblNom.getText()))
				{
					p.setNom(CaracteristicView.lblNom.getText() );
					musicController.changeRoomName(CaracteristicView.nomPiece,
							CaracteristicView.lblNom.getText() );

				}

				p.setTemperature((Integer)CaracteristicView.s.getValue());
				hg.enregistrerHouse(m);
				ModificationView.getRoomView().update();
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
			m.appliquerPartout();
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
					RoomView.setColors(color,null);
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
					RoomView.setColors(null,color);

				}
			}
		}
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
