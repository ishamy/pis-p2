package view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import controller.CaracteristicViewController;
import controller.JRadioButtonController;
import fr.lri.swingstates.canvas.CRectangle;
import fr.lri.swingstates.canvas.CShape;
import fr.lri.swingstates.canvas.Canvas;

public class FormsView  extends JPanel implements View {

	JButton btnValider;
	CRectangle room;
	JRadioButtonController controller;
	CaracteristicViewController controllerBtn;
	static JRadioButton radioButtonRectangle;
	static JRadioButton radioButtonEllipse ;
	static JRadioButton radioButtonTriangle ;
	public static ButtonGroup group;

	public FormsView() {
		super();
		setLayout(new BorderLayout(40, 40));
		controller = new JRadioButtonController();
		controllerBtn  = new CaracteristicViewController();
		init();
	}

	private void init() {
		JPanel grid = new JPanel();
		grid.setLayout(new GridLayout(3,2));
		//ImageIcon  imageRec = new ImageIcon("icons/rectangle.jpg");
		//ImageIcon  imageElli = new ImageIcon("icons/ellispe.jpg");
//		ImageIcon  imageTri = new ImageIcon("icons/triangle.jpg");

		radioButtonRectangle = new JRadioButton("Rectangle" );
		radioButtonEllipse = new JRadioButton("Ellipse" );
		radioButtonTriangle = new JRadioButton("Triangle" );

		radioButtonRectangle.addActionListener(controller);
		radioButtonEllipse.addActionListener(controller);
		radioButtonTriangle.addActionListener(controller);

		group = new ButtonGroup();
		group.add(radioButtonRectangle);
		group.add(radioButtonEllipse);
		group.add(radioButtonTriangle);

		grid.add(radioButtonRectangle);
	//	grid.add( new JLabel(imageRec) );

		grid.add(radioButtonEllipse);
	///	grid.add( new JLabel(imageElli) );

		grid.add(radioButtonTriangle);
	//	grid.add( new JLabel(imageTri) );

		add(grid);

	}

	@Override
	public void update() {

	}

	public static void miseAJourJRadio (String s ){
		if (s.equals("Rectangle"))
			radioButtonRectangle.setSelected(true);
		else if (s.equals("Ellipse"))
			radioButtonEllipse.setSelected(true);
		else if (s.equals("Triangle"))
			radioButtonTriangle.setSelected(true);
	}


}
