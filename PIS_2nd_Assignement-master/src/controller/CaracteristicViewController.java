package controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JColorChooser;
import javax.swing.JOptionPane;

import view.*;

import model.HouseModel;
import model.MemoryAction;
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
		MemoryAction hg= new MemoryAction();

		ModificationView.getCaracView();
		musicController = MainView.getMusicController();
		RepresentationView viewRep =(RepresentationView)MainView.getRep();
		RoomModel p = HouseModel.
		getPieceWhereNom(CaracteristicView.nomPiece);

		if (arg0.getActionCommand().equals("Validate")){

			if (p != null) 
			{

				if (!CaracteristicView.nomPiece.equals(CaracteristicView.lblNom.getText()))
				{
					if (HouseModel.getPieceWhereNom(CaracteristicView.lblNom.getText()) == null){
						hg.enregistrerHouse(p,"Name",CaracteristicView.nomPiece ,CaracteristicView.lblNom.getText() );

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

				int lastTemp = p.getTemperature();
				p.setTemperature((Integer)CaracteristicView.s.getValue());
				if (lastTemp != (Integer)CaracteristicView.s.getValue())
				{ 
					hg.enregistrerHouse(p,"Temperature", lastTemp,(Integer)CaracteristicView.s.getValue() );
				}
			}
			else 
				JOptionPane.showMessageDialog(null,
				"No rooms selected");


			ModificationView.getRoomView().update();
		}
		else if(arg0.getActionCommand().equals("Previous")){
			hg.mag.getLastAction();
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
		MemoryAction hg= new MemoryAction();

		RepresentationView r = (RepresentationView) MainView.getRep();
		// TODO Auto-generated method stub
		if (arg0.getComponent().getName().equals("Change Background")){
			RoomModel p = HouseModel.
			getPieceWhereNom(CaracteristicView.nomPiece);
			if (p != null ){
				Color color = p.getColorChooser().showDialog
				(null, "Choisir une couleur",Color.white);  
				if (color != null){
					ColorView.pnlFondApercu.setBackground
					(color);
					hg.enregistrerHouse(p,"BackColor",p.getCouleurFond(), color  );
					p.setCouleurFond(color);
				}
			}
		}

		else if (arg0.getComponent().getName().equals("Change Outline")){
			RoomModel p = HouseModel.
			getPieceWhereNom(CaracteristicView.nomPiece);
			if (p != null) 
			{
				Color color = p.getColorChooser().showDialog
				(null, "Choisir une couleur",Color.white); 
				if (color != null){
					ColorView.pnlContourApercu.setBackground
					(color);
					hg.enregistrerHouse(p,"OutlineColor",p.getCouleurContour(), color  );
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
