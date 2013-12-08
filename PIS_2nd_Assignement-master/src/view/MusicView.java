package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.HashMap;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import controller.MusicController;

public class MusicView extends JPanel implements View {
	private static final long serialVersionUID = 1L;
	
	public static String PLAY_ICON = "icons/play.png";
	public static String PAUSE_ICON = "icons/pause.png";
	public static String STOP_ICON = "icons/stop.png";
	
	Color backgroundColor = Color.white;
	
	MusicController controller;
	
	JPanel mainPane;
	JPanel roomsList;
	
	HashMap<String, JButton> playButtons;
	public MusicView(int width, int height, MusicController c) { 
		super(new GridBagLayout());
		controller = c;
		c.setView(this);
		
		roomsList = new JPanel();
		roomsList.setBackground(backgroundColor);
		roomsList.setLayout(new BoxLayout(roomsList, BoxLayout.PAGE_AXIS));
		
		playButtons = new HashMap<String, JButton>();
		
		setSize(width, height);
		init();
		
		addComponentListener(new ComponentListener() {
			public void componentResized(ComponentEvent e) {		// resize mainPane too
				mainPane.setPreferredSize(new Dimension((getWidth()*5)/6, (getHeight()*4)/6));
			}
			
			public void componentHidden(ComponentEvent e) {}
			public void componentMoved(ComponentEvent e) {}
			public void componentShown(ComponentEvent e) {}
		});
	}
	
	
	private void init() {
		mainPane = new JPanel();
		mainPane.setLayout(new BoxLayout(mainPane, BoxLayout.Y_AXIS));
		
		
		JPanel staticPanel = new JPanel(new GridLayout(1,3));
		staticPanel.setPreferredSize(new Dimension((getWidth()*5)/6, (getHeight())/6));
		// staticPanel.setBackground(backgroundColor);
		JLabel[] labels = {new JLabel("Room's name", SwingConstants.CENTER),
						   new JLabel("Songs list", SwingConstants.CENTER),
						   new JLabel("Music interaction", SwingConstants.CENTER)};
		
		for(int i = 0; i < 3; i++) {
			JPanel tmp = new JPanel(new GridBagLayout());
			tmp.setBorder(new LineBorder(Color.BLACK, 2));
			tmp.setBackground(backgroundColor);
			
			labels[i].setFont(new Font("Cousine", 0, 15));
			tmp.add(labels[i]);
			staticPanel.add(tmp);
		}
		
		JScrollPane sp = new JScrollPane(roomsList);
		sp.setPreferredSize(new Dimension((getWidth()*5)/6, (getHeight()*4)/6));
		
		mainPane.setPreferredSize(new Dimension((getWidth()*5)/6, (getHeight()*5)/6));
		mainPane.add(staticPanel);
		mainPane.add(sp);
		
		add(mainPane);
	}
	
	public String addRoom(String name) {
		JPanel roomPanel = new JPanel(new GridLayout(1, 3));			// main panel for this room
		roomPanel.setBackground(backgroundColor);
	
		roomPanel.add(new JLabel(name, SwingConstants.CENTER)); 		// name of the room
		
		JPanel pane = new JPanel(new GridBagLayout());			
		pane.setBackground(backgroundColor);
		
		JComboBox menu = new JComboBox(controller.getMusicFromDir());	// music choice
		
		String selection = (String) menu.getSelectedItem(); 			// add the first room of the list
		
		menu.addItem("Add song...");
		pane.add(menu);
		roomPanel.add(pane);
		
		
		menu.addActionListener(controller.comboBoxActionListener(name));
		
		JButton[] buttons = { new JButton(new ImageIcon(PLAY_ICON)),
						      new JButton(new ImageIcon(STOP_ICON)) };
		pane = new JPanel(new GridBagLayout());					// interaction with the music of this room
		pane.setBackground(backgroundColor);
		
		for(int index = 0; index < buttons.length; index++) {	// instantiates all buttons
	        buttons[index].setBorderPainted(false); 			// erase button background, show icon only
	        buttons[index].setContentAreaFilled(false);
	        buttons[index].setFocusPainted(false); 
	        buttons[index].setOpaque(false);
	        
	        if (index == 0) {
	        	controller.buttonPlayListener(buttons[index], name);
	        	playButtons.put(name, buttons[index]);
	        }
	        else
	        	controller.buttonStopListener(buttons[index], buttons[index-1], name);
			pane.add(buttons[index]);
		}
		roomPanel.add(pane);
		
		roomPanel.setName(name);							// to remove the panel if we have to
		roomsList.add(roomPanel);
		
		update();
		
		return selection;
	}
	
	
	public void changePlayIcon(String roomsName) {
		playButtons.get(roomsName).setIcon(new ImageIcon(PLAY_ICON));
	}
	
	
	// remove panel from roomsList
	public boolean removeRoom(String name) {		
		Component[] components = roomsList.getComponents();
		int panelNumber = 0;
		while(panelNumber < components.length 
		   && !components[panelNumber].getName().equals(name)) panelNumber++; // stop when panel's found
		
		if(panelNumber < components.length) {
			roomsList.remove(components[panelNumber]);
			return true && controller.removeRoom(name);
		} else return false;
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

}

