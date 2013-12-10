package view;

import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import controller.MusicController;

public class ModificationView extends JPanel implements View {
	private static final long serialVersionUID = 1L;
	
	private static View roomsView;
	private static View caracteristicsView;

	
	public ModificationView(int width, int height, MusicController mc, RepresentationView rep) { 
		super();
		setSize(width, height);

		roomsView = new RoomsView(getWidth(), getHeight()/2, mc, rep);	// all rooms use the same mc
		caracteristicsView = new CaracteristicsView(mc);
		
		init();
	}
	
	
	
	private void init() {
		setLayout(new GridLayout(2, 1));
		
		add((JPanel) roomsView);
		add((JTabbedPane) caracteristicsView);
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
	
	public static View getCaracView(){
		return caracteristicsView;
	}
	
	public static View getRoomView(){
		return roomsView;
	}

}
