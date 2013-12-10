package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import controller.MusicController;
import controller.CaracteristicViewController;
import model.HouseModel;
import model.RoomModel;

public class RoomsView extends JPanel implements View {
	private static final long serialVersionUID = 1L;
	
	JPanel roomsPanel;
	JPanel staticPanel;
	CaracteristicViewController controller; 


	MusicController musicController;
	
	RepresentationView representation;
	MainView main;
	public RoomsView(int width, int height, MusicController mc, RepresentationView rep) {
		super();
		setBackground(Color.BLACK);
		setLayout(new BorderLayout(width/10, 0));
		setSize(width, height);
		controller = new CaracteristicViewController(mc) ; 

		musicController = mc;										// needed to play music
		
		representation = rep;										// needed to add rooms
		
		init();
		
		addComponentListener(new ComponentListener() {
			public void componentResized(ComponentEvent arg0) { update(); }	// resize all rooms' views

			public void componentMoved(ComponentEvent arg0) {}
			public void componentShown(ComponentEvent arg0) {}
			public void componentHidden(ComponentEvent arg0) {}
		});
	}
	
	
	private void init() {
		
		roomsPanel = new JPanel();
		roomsPanel.setLayout(new GridLayout(0,3));
		initRoomsPanel();
		
		initStaticPanel();
		
		add(roomsPanel, BorderLayout.CENTER);
		add(staticPanel, BorderLayout.LINE_END);
	}
	
	
	private void initRoomsPanel() {
		for(RoomModel i : HouseModel.pieceList)
		{
			int width = roomsPanel.getWidth()/3;
			int height = roomsPanel.getHeight()/(((HouseModel.pieceList.size() - 1)/3) + 1);
			RoomView rv = new RoomView(i, width, height, i.getNom(), musicController, i.getForme());
			roomsPanel.add(rv);
		}
	}
	
	
	private void initStaticPanel() {
	
		staticPanel = new JPanel();
		staticPanel.setPreferredSize(new Dimension(getWidth()/4, getHeight()));
		staticPanel.setLayout(new GridLayout(3,1));
		
		JButton btnPlus = new JButton("+");			// add rooms
		btnPlus.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				main = MainView.getMain();
				String roomsName = new AddView(getParent()).getRoomsName();
				if(main.getMaison().newChambre(roomsName)) {
					musicController.addRoom(roomsName);
					representation.addRoom(roomsName);
				}
			}
		});
		
		JButton btnDelete = new JButton("Delete");			// add rooms
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				main = MainView.getMain();
				int size = main.getMaison().getPieceList().size();
				if ( size != 0) 
				{
					DeleteRoomView view = new DeleteRoomView(getParent());
					
				}
				else 
					JOptionPane.showMessageDialog(null,
							"No rooms in the house");
				update();
			}
		});
		JButton btnAll = new JButton("ALL");		
		btnAll.addActionListener(controller);
		staticPanel.add(btnPlus);
		staticPanel.add(btnDelete);
		staticPanel.add(btnAll);
	}
	
	@Override
	public void update() {
		roomsPanel.removeAll();
		roomsPanel.setLayout(new GridLayout(0,3));
		initRoomsPanel();
		for(Component c: roomsPanel.getComponents()) ((View) c).update();
		roomsPanel.updateUI();
	}

}
