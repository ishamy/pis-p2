package view;

import java.awt.Color;
import java.awt.geom.Point2D;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;

import model.HouseModel;
import model.RoomModel;

import controller.MusicController;

import fr.lri.swingstates.canvas.CImage;
import fr.lri.swingstates.canvas.CPolyLine;
import fr.lri.swingstates.canvas.CShape;
import fr.lri.swingstates.canvas.CText;
import fr.lri.swingstates.canvas.Canvas;
import fr.lri.swingstates.sm.JStateMachine;
import fr.lri.swingstates.sm.State;
import fr.lri.swingstates.sm.Transition;
import fr.lri.swingstates.sm.transitions.Click;
import fr.lri.swingstates.sm.transitions.Enter;
import fr.lri.swingstates.sm.transitions.Leave;

public class RoomView extends Canvas implements View {
	private static final long serialVersionUID = 1L;

	private static final String ICONS_DIR = "icons/"; 
	
	public static final int RECTANGLE = 0;
	public static final int ELLIPSE = 1;
	public static final int TRIANGLE = 2;
	
	RoomModel model;
	int currentShape;
	
	JLabel labelName;
	MusicController musicController;
	
	static CShape room;
	CImage light;
	CImage sound;
	
	public RoomView(RoomModel m, int width, int height, String name, MusicController mc,String type) {
		super(width, height);
		setSize(width, height);
		setFocusable(true);
		setLayout(new FlowLayout(FlowLayout.RIGHT));
		setName(name);
		
		model = m;
		musicController = mc;
		if (type.equals("Rectangle")) currentShape= RECTANGLE;
		else if (type.equals("Triangle")) currentShape= TRIANGLE;
		else if (type.equals("Ellipse")) currentShape= ELLIPSE;

		
		light = newImage(5, 5, ICONS_DIR+"light_off.png");
		
		light.setOutlined(false);
		
		sound = newImage(5, 35, ICONS_DIR+"sound_off.png");
		sound.setOutlined(false);
		
		init();
		setVisible(true);
	}
	
	private void init() {
		
		changeShape(currentShape);
		
		
		
		setBorder(new LineBorder(Color.black, 2));
		labelName = new JLabel();
		add(labelName);
		new JStateMachine() {
			State out = new State() {
				Transition mouseEnter = new Enter(">> in") {
					public void action() {
						musicController.setRoomGain(RoomView.this.getName(), 1);
					}
				};
			};
			
			State in = new State() {
				Transition mouseLeave = new Leave(">> out") {
					public void action() {
						musicController.setRoomGain(RoomView.this.getName(), 0);
					}
				};
				
				Transition mouseClick = new Click() {
					public void action() {
						CaracteristicView.btnValider.setText("Validate");
						RoomModel p = HouseModel.
						getPieceWhereNom(RoomView.this.getName());
						if (p != null)
						{
							System.out.println(p.getNom());
							CaracteristicView.nomPiece = p.getNom();
							CaracteristicView.lblNom.setText(p.getNom());
							CaracteristicView.s.setValue(p.getTemperature());
							CaracteristicView.swt.setSelected(p.isLumiere());
							CaracteristicView.swt.setName(p.getNom());
							ColorView.pnlFondApercu.setBackground
							(p.getCouleurFond());
							ColorView.pnlContourApercu.setBackground
							(p.getCouleurContour());
							FormsView.miseAJourJRadio(p.getForme());
						}
					}
				};
			};
			
		}.attachTo(this);
		update();
	}
	
	public static void setColors( Color f,Color ol) { // change canvas colors
		if(f != null) room.setFillPaint(f);
		if(ol != null) room.setOutlinePaint(ol);
	}
	
	
	public void changeShape(int kind) {
		int width = getWidth()/2;		// unchanged values	
		int height = getHeight()/2;
		removeShape(room);
		
		currentShape = kind;
		
		switch(currentShape) {
		case RECTANGLE:
			room = newRectangle(getWidth()/2 - width/2, getHeight()/2 - height/2, width, height);
			break;
		case ELLIPSE:
			room = newEllipse(getWidth()/2 - width/2, getHeight()/2 - height/2, width, height);
			break;
		default:  // TRIANGLE
			int cWidth = getWidth();
			int cHeight = getHeight();
			CPolyLine lines = newPolyLine(cWidth/2, cHeight/2 - height/2);	// upper vertex
			lines.lineTo(cWidth/2 + width/2, cHeight/2 + height/2);						// down right vertex
			lines.lineTo(cWidth/2 - width/2, cHeight/2 + height/2);						// down left vertex
			lines.close();
			room = lines;
		}
	}
	
	@Override
	public void update() {
		labelName.setText(model.getNom());
		
		String icon = model.isLumiere()?				// change light icon
				"light_on.png":"light_off.png";
		(new CImage(ICONS_DIR + icon, 
				new Point2D.Double(5, 5))).copyTo(light); 			
		light.setOutlined(false);	
		addShape(light);
		
		icon = musicController.isPlaying(getName())?	// change sound icon
				"sound_on.png":"sound_off.png";
		(new CImage(ICONS_DIR + icon, 
						new Point2D.Double(5, 35))).copyTo(sound);	
		sound.setOutlined(false);
		addShape(sound);
		
		changeShape(currentShape);						// resize the shape
		setColors(model.getCouleurFond(),model.getCouleurContour());
	}

}
