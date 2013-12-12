package view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import model.RoomModel;

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
		setLayout(new GridBagLayout());
		
		JPanel grid = new JPanel();
		// grid.setLayout(new BoxLayout(grid, BoxLayout.Y_AXIS));
		grid.setLayout(new GridLayout(3,1));
		ImageIcon  imageRec = new ImageIcon("icons/rectangle.jpg");
		ImageIcon  imageElli = new ImageIcon("icons/ellispe.jpg");
		ImageIcon  imageTri = new ImageIcon("icons/triangle.jpg");

		radioButtonRectangle = new JRadioButton(/* "Rectangle" */);
		radioButtonRectangle.setName("" + RoomModel.RECTANGLE);
		
		radioButtonEllipse = new JRadioButton(/* "Ellipse" */);
		radioButtonEllipse.setName("" + RoomModel.ELLIPSE);
		
		radioButtonTriangle = new JRadioButton(/* "Triangle" */);
		radioButtonTriangle.setName("" + RoomModel.TRIANGLE);
		
		radioButtonRectangle.addActionListener(controller);
		radioButtonEllipse.addActionListener(controller);
		radioButtonTriangle.addActionListener(controller);

		group = new ButtonGroup();
		group.add(radioButtonRectangle);
		group.add(radioButtonEllipse);
		group.add(radioButtonTriangle);

		
		radioButtonRectangle.setIcon(imageRec);
		grid.add(radioButtonRectangle);
		// grid.add( new JLabel(imageRec) );

		radioButtonEllipse.setIcon(imageElli);
		grid.add(radioButtonEllipse);
		// grid.add( new JLabel(imageElli) );

		radioButtonTriangle.setIcon(imageTri);
		grid.add(radioButtonTriangle);
		// grid.add( new JLabel(imageTri) );

		add(grid);
	}

	@Override
	public void update() {

	}

	public static void miseAJourJRadio (int s ){
		if (s == RoomModel.RECTANGLE)
			radioButtonRectangle.setSelected(true);
		else if (s == RoomModel.ELLIPSE)
			radioButtonEllipse.setSelected(true);
		else if (s == RoomModel.TRIANGLE)
			radioButtonTriangle.setSelected(true);
	}


}
