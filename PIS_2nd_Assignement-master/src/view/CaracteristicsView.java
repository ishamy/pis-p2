package view;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import controller.MusicController;

public class CaracteristicsView extends JTabbedPane implements View {
	private static final long serialVersionUID = 1L;

	private View caracteristicView;
	private View colorView;
	private View formView;
	
	MusicController musicController;
	
	public CaracteristicsView(MusicController mc) {
		super();
		
		musicController = mc;
		
		init();
	}


	private void init() {
		caracteristicView = new CaracteristicView();
		colorView = new ColorView();
		formView = new FormsView();
		addTab("CARACTERISTICS", (JPanel) caracteristicView);
		addTab("COLORS", (JPanel) colorView);
		addTab("FORMS", (JPanel) formView);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}
	public void miseAJourOnglet(int i){
		setSelectedIndex(i);
	}
	
}
