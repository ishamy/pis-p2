package view;


import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;

import controller.MusicController;

import model.HouseModel;
import model.MemoryActionGestion;
import model.RoomModel;

public class MainView extends JFrame {
	private static final long serialVersionUID = 1L;

	// three main panels
	private View modif;
	private View music;
	private static View rep;
	private HouseModel m;
	private	static MemoryActionGestion houseGestion;
	private	static MusicController musicController;
	static MainView main ;

	public MainView() {
		super("Control panel");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(700, 500);
		m = new HouseModel();
		houseGestion = new MemoryActionGestion();
		
		init();				// initialize every components 

		// pack();
		setVisible(true);
		// setResizable(false);
		setLocationRelativeTo(null);
	}


	private void init() {
		/* better aspect (code from oracle tutorial) */
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
			UIManager.put("swing.boldMetal", false);
		} catch (Exception exn) { exn.printStackTrace(); }
		
		
		musicController = new MusicController();			// main controller to hear music
		
		JTabbedPane mainPanel = new JTabbedPane(); 
		// mainPanel.setLayout(new CardLayout());
		rep = new RepresentationView(getWidth(), getHeight(), musicController);
		
		modif = new ModificationView(getWidth(), getHeight(), musicController, (RepresentationView) rep);
		
		music = new MusicView(getWidth(), getHeight(), musicController);
		
		mainPanel.addTab("MODIFICATION", (JPanel) modif);
		mainPanel.addTab("MUSIC", (JPanel) music);
		mainPanel.addTab("HOUSE REPRESENTATION", (JPanel) rep);
		
		getContentPane().add(mainPanel);
	}
	
	
	public static void main(String args[]) {
		main = new MainView();
	}

	
	public HouseModel getMaison(){
		return m;
	}
	public void setMaison(HouseModel house){
		m = house;
	}
	public static MusicController getMusicController() {
		return musicController;
	}


	public static void setMusicController(MusicController musicControlle) {
		musicController = musicControlle;
	}


	public static  MemoryActionGestion getHouseGestion() {
		return houseGestion;
	}


	public static void setHouseGestion(MemoryActionGestion houseGestion) {
		MainView.houseGestion = houseGestion;
	}


	public static MainView getMain() {
		return main;
	}


	public static void setMain(MainView main) {
		MainView.main = main;
	}


	public static View getRep() {
		return rep;
	}


	public static void setRep(View r) {
		rep = r;
	}

}
