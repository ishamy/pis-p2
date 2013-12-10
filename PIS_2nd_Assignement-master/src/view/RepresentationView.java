package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.image.VolatileImage;

import javax.swing.border.LineBorder;

import controller.MusicController;

import model.HouseModel;
import model.RoomModel;

import fr.lri.swingstates.canvas.CRectangle;
import fr.lri.swingstates.canvas.Canvas;
import fr.lri.swingstates.sm.JStateMachine;
import fr.lri.swingstates.sm.State;
import fr.lri.swingstates.sm.Transition;
import fr.lri.swingstates.sm.transitions.Click;
import fr.lri.swingstates.sm.transitions.Press;
import fr.lri.swingstates.sm.transitions.Release;

public class RepresentationView extends Canvas implements View {
	private static final long serialVersionUID = 1L;

	MusicController controller;
	
	Canvas selection;
	CRectangle dragableObject;
	
	JStateMachine dragMachine;
	
	public RepresentationView(int width, int height, MusicController c) { 
		super();
		controller = c;
		
		setSize(width, height);
		
		// init();
		
		addComponentListener(new ComponentListener() {
			public void componentResized(ComponentEvent arg0) { update(); }	// resize all rooms' views

			public void componentShown(ComponentEvent arg0) { 
				update(); 
				dragMachine.detachFrom(RepresentationView.this);
				createDragMachine().attachTo(RepresentationView.this); 
			}
			
			public void componentHidden(ComponentEvent arg0) {}
			public void componentMoved(ComponentEvent arg0) {}
		});
		
		createDragMachine();
	}
	
	
	public void init() {
		int roomsNumber = HouseModel.pieceList.size();
		
		setLayout(new GridLayout(roomsNumber, roomsNumber));
		
		
		for(int index = 0; index < roomsNumber * roomsNumber; index++) {
			if(index < roomsNumber) {
				RoomModel model = HouseModel.pieceList.get(index);
				add(new RoomView(model, 
					getWidth()/roomsNumber, 
					getHeight()/roomsNumber,
					model.getNom(),
					controller, model.getForme()));
			}
			
		}
	}
	
	
	
	private JStateMachine createDragMachine() {
		dragMachine = new JStateMachine() {
			State over = new State() {
				Transition select = new Press(">> move") {
					public void action() {
						selection = (Canvas) RepresentationView.this.getComponentAt(getMousePosition());
						selection.setBorder(new LineBorder(Color.RED, 4));
						
						findComponentNumber(selection);
						
						/* int width = selection.getWidth()/2;
						int height = selection.getHeight()/2;
						int dx = getMousePosition().x - width/2;
						int dy = getMousePosition().y - height/2;
						dragableObject = newRectangle(dx, dy, width, height);
						dragableObject.setFillPaint(Color.BLACK);
						above(dragableObject);
						repaint(); */
					}					
				};
			};
			
			State move = new State() {
				Transition select = new Release(">> over") {
					public void action() {
						selection.setBorder(new LineBorder(Color.BLACK, 2));
						
						Canvas target = (Canvas) RepresentationView.this.getComponentAt(getMousePosition());
						
						int selectionNumber = findComponentNumber(selection);
						int targetNumber = findComponentNumber(target);
						
						// remove(selectionNumber);
						add(target, selectionNumber);		// move target
						
						// remove(targetNumber);
						add(selection, targetNumber);		// move selection
						
						repaint();
					}					
				};
			};
		};
		
		return dragMachine;
	}

	
	private int findComponentNumber(Component component) {
		int componentNumber = 0;
		while(component != getComponent(componentNumber)) componentNumber++;
		return componentNumber;
	}
	
	
	
	
	public void addRoom(String roomsName) {
		Component[] components = getComponents();				// get all old components
		removeAll();
		
		int roomsNumber = HouseModel.pieceList.size();				// new size
		setLayout(new GridLayout(roomsNumber, roomsNumber));

		add(new RoomView(HouseModel.getPieceWhereNom(roomsName),	// add new room  
				 getWidth()/roomsNumber, 
				 getHeight()/roomsNumber,
				 roomsName,
				 controller,
				 HouseModel.getPieceWhereNom(roomsName).getForme()), 0);
		
		int oldCounter = 0;
		
		for(int index = 1; index < (roomsNumber * roomsNumber); index++)
			if(index > roomsNumber && index % roomsNumber != 0)
				add(components[oldCounter++], index);
			else {
				Canvas blank = new Canvas();
				blank.setBackground(Color.WHITE);
				blank.setBorder(new LineBorder(Color.BLACK, 2));
				add(blank, index);
			}
		
		update();
	}
	
	public void deleteRoom(String roomsName) {
		Component[] components = getComponents();				// get all old components
		removeAll();
		
		int roomsNumber = HouseModel.pieceList.size();				// new size

		int oldCounter = 0;
		for(int index = 1; index < (roomsNumber * roomsNumber); index++)
			if(index > roomsNumber && index % roomsNumber != 0)
				remove(components[oldCounter++]);
			
		
		update();
	}
	
	
	@Override
	public void update() {
		int roomsNumber = HouseModel.pieceList.size();
		
		for(Component c : getComponents())
			if(c instanceof View) {
				if(roomsNumber != 0)
				c.setSize(getWidth()/roomsNumber, getHeight()/roomsNumber);
				else 
				c.setSize(getWidth()/1, getHeight()/1);

				((View) c).update();
			}
		repaint();
	}


}
