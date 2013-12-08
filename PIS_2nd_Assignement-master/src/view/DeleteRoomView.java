package view;

import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.MusicController;

import model.HouseModel;
import model.RoomModel;

public class DeleteRoomView extends JDialog{
	JCheckBox check;
	ArrayList<JCheckBox> listCheck;
	public DeleteRoomView(Component parent){
		super();
		setTitle("Delete a room");
		setLocationRelativeTo(parent);
		setModal(true);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

		init();
		pack();
		setVisible(true);
	}

	public void init(){

		JPanel pnl = new JPanel();
		pnl.setLayout(new GridLayout(HouseModel.pieceList.size()+1,1));
		JCheckBox check = new JCheckBox();
		listCheck = new ArrayList<JCheckBox>();
		for(RoomModel p : HouseModel.pieceList){
			check = new JCheckBox();
			check.setName(p.getNom());
			listCheck.add(check);
			JLabel lbl = new JLabel(p.getNom());
			pnl.add(check);
			pnl.add(lbl);		
		}

		JButton validate = new JButton("Validate");
		ActionListener enter = new ActionListener() {
			public void actionPerformed(ActionEvent event) { 
				deleteSelection();
				dispose();
			}
		};
		validate.addActionListener(enter);


		JButton cancel = new JButton("Cancel");
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				dispose();
			}
		});
		pnl.add(validate);
		pnl.add(cancel);
		Container mainPane = getContentPane();
		mainPane.setLayout(new FlowLayout(FlowLayout.CENTER));
		mainPane.add(pnl);
	}

	public void deleteSelection(){
		MusicController mc = MainView.getMusicController();
		for(JCheckBox p : listCheck){
			if(p.isSelected())
			{
				HouseModel.supprimerPiece(p.getName());
				mc.removeRoom(p.getName());
			}
		}
		if( HouseModel.pieceList.size() == 0){
			CaracteristicView.lblNom.setText("-");
		}

	}
}
