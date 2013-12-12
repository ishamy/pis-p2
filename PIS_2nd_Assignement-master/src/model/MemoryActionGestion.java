package model;

import java.awt.Color;

import controller.MusicController;
import view.CaracteristicView;
import view.ColorView;
import view.FormsView;
import view.MainView;
import view.ModificationView;
import view.RepresentationView;
import view.RoomsView;

public class MemoryActionGestion {
	int curr_pos;
	MemoryAction[] houseList;
	MusicController musicController;
	public MemoryActionGestion(){
		curr_pos= 0;
		houseList = new MemoryAction[20];
		for (int i = 0; i<20;i++){
			houseList[i] = null;
		}

	}

	public void getLastAction(){
		//curr_pos++;

		if (curr_pos < 20)
		{
			if (houseList[curr_pos] != null)
				execute(houseList[curr_pos]) ;
			else execute(houseList[0]) ;
		}
		else  execute(houseList[0]);
		curr_pos++;
	}


	public void enregistrerHouse(MemoryAction m ){
		int drap=0;
		int j =0;

		while( j <20 && drap ==0 )
		{
			if(houseList[j] == null) drap = j;
			else if(j == 19) drap =j;
			j++;
		}
		for( int g = drap; g>=0 ;g-- ){
			if (g != 19) houseList[g+1] =houseList[g];
			else houseList[19] = houseList[18];
		}

		houseList[0] =m;
		System.out.println(m.r.getTemperature());
		System.out.println("**************");
		for(int a = 19; a >=0 ;a-- ){
			if (houseList[a] != null )
			{
				System.out.println("------------------");
				System.out.println(houseList[a].r.getNom());
				System.out.println(houseList[a].element);
				System.out.println(houseList[a].lastValue);
				System.out.println(houseList[a].newValue);
			}
		}
		curr_pos =0;

	}

	public void execute(MemoryAction m ){

		if (m != null){
			RepresentationView viewRep =(RepresentationView)MainView.getRep();
			musicController = MainView.getMusicController();

			RoomModel rm = m.r;
			System.out.println(m.r.getNom());
			if (m.element == "Temperature")
			{
				rm.setTemperature(Integer.parseInt(m.lastValue));
				CaracteristicView.s.setValue(rm.getTemperature());
			}
			else if (m.element == "Form")
			{
				rm.setForme(Integer.parseInt(m.lastValue));
				FormsView.miseAJourJRadio(Integer.parseInt(m.lastValue));
				RoomsView rv = (RoomsView)ModificationView.getRoomView();
				rv.update();
			}
			else if (m.element == "Name")
			{
				System.out.println(m.lastValue);
				rm.setNom(m.lastValue);
				CaracteristicView.lblNom.setText(rm.getNom());
				musicController.changeRoomName(m.newValue,
						m.lastValue);
				viewRep.deleteRoom(m.newValue);
				viewRep.addRoom(m.lastValue);
				ModificationView.getRoomView().update();
			}
			else if (m.element == "Light")
			{
				System.out.println(m.lastValue);
				rm.setLumiere(Boolean.valueOf(m.lastValue));
				CaracteristicView.swt.setSelected(rm.isLumiere());
			}
			else if (m.element == "OutlineColor"){

				rm.setCouleurContour(m.lastColor);
				ModificationView.getRoomView().update();
			}
			else if (m.element == "BackColor"){

				rm.setCouleurFond(m.lastColor);
				ModificationView.getRoomView().update();
			}
		}
		/*CaracteristicView.nomPiece = p.getNom();
		CaracteristicView.lblNom.setText(p.getNom());
		CaracteristicView.s.setValue(p.getTemperature());
		CaracteristicView.swt.setSelected(p.isLumiere());
		CaracteristicView.swt.setName(p.getNom());
		ColorView.pnlFondApercu.setBackground
		(p.getCouleurFond());
		ColorView.pnlContourApercu.setBackground
		(p.getCouleurContour());
		FormsView.miseAJourJRadio(p.getForme());*/
	}
}

