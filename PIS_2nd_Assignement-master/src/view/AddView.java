package view;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class AddView extends JDialog {
	private static final long serialVersionUID = 1L;

	JTextField roomsName;
	
	public AddView(Component parent) {
		super();
		setTitle("Add a room");
		setLocationRelativeTo(parent);
		setModal(true);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		
		init();
		
		setSize(300, 100);
		setVisible(true);
	}
	
	
	private void init() {
		ActionListener enter = new ActionListener() {
			public void actionPerformed(ActionEvent event) { dispose(); }
		};
		
		roomsName = new JTextField(20);
		roomsName.addActionListener(enter);
		
		JButton validate = new JButton("Validate");
		validate.addActionListener(enter);
		
		JButton cancel = new JButton("Cancel");
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				roomsName.setText(null);
				dispose();
			}
		});
		
		Container mainPane = getContentPane();
		mainPane.setLayout(new FlowLayout(FlowLayout.CENTER));
		mainPane.add(roomsName);
		mainPane.add(validate);
		mainPane.add(cancel);
	}
	
	public String getRoomsName() {
		return roomsName.getText();
	}
}


/* public class AddView extends JPanel implements View{

	controllerCaracteristicView controller;
	private static JTextField nomTxt;
	
	public AddView(MusicController mc){
		super();
		controller = new controllerCaracteristicView(mc);
		init();
	}

	private void init(){
		JPanel pnl = new JPanel ();
		pnl.setLayout(new GridLayout(3, 1));

		JLabel nom = new JLabel ();
		nom.setText("Entrez le nom de la chambre svp");
		pnl.add(nom);
		nomTxt = new JTextField (10);
		pnl.add(nomTxt);
		
		JButton btnValider = new JButton ("Validez nom chambre");
		btnValider.addActionListener(controller);
		pnl.add(btnValider, BorderLayout.SOUTH);
		add(pnl);
	}
	
	public static String getNomTxt(){
		return nomTxt.getText();
	}
	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
} */

