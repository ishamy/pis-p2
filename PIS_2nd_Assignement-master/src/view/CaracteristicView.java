

package view;

import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controller.CaracteristicViewController;
import model.JSwitchBox;

public class CaracteristicView extends JPanel implements View {
	private static final long serialVersionUID = 1L;
	public static JSpinner spinner;
	public static JSwitchBox swt;
	public static JTextField lblNom;
	public static JButton btnValider;
	public static JSpinner s;
	public static JButton btnBack;
	JPanel buttonsPane;
	JPanel grid ;
	JPanel gridBtn ;
	JPanel gridModif;

	public static String nomPiece;

	private CaracteristicViewController controller;
	public CaracteristicView() {
		super();
		setLayout(new BorderLayout(40, 40));
		controller = new CaracteristicViewController();
		init();
	}

	private void init() {
		gridModif = new JPanel();
		gridModif.setLayout(new GridLayout(1,2));

		initModificationCaracteristic();
		initUndoRedoButtonPanel();
		initButtonPanel();

		gridModif.add(grid);
		gridModif.add(gridBtn);
		add(gridModif, BorderLayout.CENTER);

		add(buttonsPane, BorderLayout.SOUTH);


	}


	private void initModificationCaracteristic(){

		grid = new JPanel();

		String[] fields = {"Name", "Light", "Temperature"};	// static fields
		grid.setLayout(new GridLayout(2, fields.length));
		for(int index = 0; index < fields.length; index++)
			grid.add(new JLabel(fields[index], SwingConstants.CENTER));						// add static fields in the grid

		lblNom = new JTextField("", SwingConstants.CENTER);
		grid.add(lblNom);			// name

		JPanel cbp = new JPanel(new GridBagLayout());
		swt = new JSwitchBox( "On", "Off" );
		swt.setSelected(false);
		swt.setName("lumiere");
		cbp.add(swt);
		grid.add(cbp);			// light

		JPanel sp = new JPanel(new GridBagLayout());
		s = new JSpinner();
		JSpinner.NumberEditor spinnerModel =
				new JSpinner.NumberEditor(s);
		s.setEditor(spinnerModel); 
		spinnerModel.getModel().setMinimum(0);
		spinnerModel.getModel().setMaximum(50);
		s.setPreferredSize(new Dimension(40, 20));
		sp.add(s);
		grid.add(sp);			// temperature


	}

	private void initUndoRedoButtonPanel(){
		gridBtn = new JPanel();
		
		
		gridBtn.setLayout(new GridLayout(2,2));

		JPanel pnlNoir =new JPanel ();
		pnlNoir.setBackground(Color.BLACK);
		gridBtn.add(pnlNoir);

		btnBack = new JButton("Previous");
		btnBack.addActionListener(controller);

		gridBtn.add(btnBack);
		
		JPanel pnlNoirBis =new JPanel ();
		pnlNoirBis.setBackground(Color.BLACK);
		gridBtn.add(pnlNoirBis);
		


	}



	private void initButtonPanel(){
		buttonsPane = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		btnValider = new JButton("Validate");
		btnValider.addActionListener(controller);
		buttonsPane.add(btnValider);
		buttonsPane.add(new JButton("Cancel"));
	}


	@Override
	public void update() {

	}

}

