package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.CaracteristicViewController;
import model.HouseModel;
import model.RoomModel;

public class ColorView extends JPanel implements View {
	private static final long serialVersionUID = 1L;
	CaracteristicViewController controller;
	public static JPanel pnlFondApercu;
	public static JPanel pnlContourApercu;

	public ColorView() {
		super();
		setLayout(new BorderLayout(40, 40));
		controller = new CaracteristicViewController ();
		init();
	}

	private void init(){
		JPanel grid = new JPanel();
		
		grid.setLayout(new GridLayout(2,3));
		
		JLabel lblFond = new JLabel();
		lblFond.setText("Background color -  Overview");
		grid.add(lblFond);
		pnlFondApercu = new JPanel();
		pnlFondApercu.setBackground(Color.white);
		JLabel txt = new JLabel("Click to change color");
		pnlFondApercu.add(txt, BorderLayout.CENTER);
		pnlFondApercu.setName("Change Background");
		pnlFondApercu.addMouseListener(controller);
		grid.add(pnlFondApercu);

		JLabel lblContour = new JLabel();
		lblContour.setText("Outline color  -  Overview");
		grid.add(lblContour);
		pnlContourApercu = new JPanel();
		pnlContourApercu.setName("Change Outline");
		pnlContourApercu.addMouseListener(controller);
		pnlContourApercu.setBackground(Color.white);
		JLabel txt2 = new JLabel("Click to change color");
		pnlContourApercu.add(txt2, BorderLayout.CENTER);

		grid.add(pnlContourApercu);
		
		add(grid);
	}
	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

}
