package controller;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import sun.applet.Main;
import view.MainView;
import view.ModificationView;
import view.MusicView;
import view.RepresentationView;

import javazoom.jlgui.basicplayer.BasicController;
import javazoom.jlgui.basicplayer.BasicPlayer;
import javazoom.jlgui.basicplayer.BasicPlayerEvent;
import javazoom.jlgui.basicplayer.BasicPlayerException;
import javazoom.jlgui.basicplayer.BasicPlayerListener;

public class MusicController {

	public static final String MUSIC_DIR = "musics/";

	public static final int PLAY = 0;
	public static final int PAUSE = 1;
	public static final int STOP = 2;

	MusicView view;

	HashMap<String, BasicPlayer> controllers;
	BasicPlayerListener listener;				// same for all players

	public MusicController() {
		controllers = new HashMap<String, BasicPlayer>();
		listener = new BasicPlayerListener() {
			public void stateUpdated(BasicPlayerEvent event) {
				int code = event.getCode();				// update UI

				if(code == BasicPlayerEvent.STOPPED) {
					BasicPlayer player = ((BasicPlayer) event.getSource());

					for(Map.Entry<String, BasicPlayer> players : controllers.entrySet())	// want to know which room ended its song
						if(players.getValue() == player) {
							view.changePlayIcon(players.getKey());		// change icon in MusicView
							return;
						}
				}

				if(code == BasicPlayerEvent.PLAYING
						|| code == BasicPlayerEvent.PAUSED
						|| code == BasicPlayerEvent.STOPPED)
					ModificationView.getRoomView().update();
			}

			public void setController(BasicController arg0) {}
			public void progress(int arg0, long arg1, byte[] arg2, Map arg3) {}
			public void opened(Object arg0, Map arg1) {}
		};
	}


	public void setView(MusicView v) {
		view = v;
	}


	// fetch all songs in the specified directory
	public String[] getMusicFromDir() {
		String[] filesNames = new File(MUSIC_DIR).list();

		ArrayList<String> tmp = new ArrayList<String>();	// filter hidden file
		for(String fileName : filesNames)
			if(fileName.charAt(0) != '.')					// don't want hidden file
				tmp.add(fileName);

		String[] tmpType = new String[tmp.size()];
		return tmp.toArray(tmpType);
	}

	// add a room to the list
	public void addRoom(String name) {
		String initSong = view.addRoom(name);
		try {
			BasicPlayer player = new BasicPlayer();
			player.addBasicPlayerListener(listener);
			if(initSong != null)
				player.open(new File(MUSIC_DIR+initSong));	// try to open
			controllers.put(name, player);
		} catch(Exception exn) { exn.printStackTrace(); }
	}


	// remove controller from the map
	public boolean removeRoom(String name) {
		view.removeRoom(name);
		return (controllers.remove(name) != null);
	}


	public void buttonPlayListener(JButton button, String roomsName) {
		final String name = roomsName;

		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				BasicPlayer player = controllers.get(name);	// get the player to activate
				String icon;
				JButton button = (JButton) event.getSource();

				try {
					if (button.getIcon().toString() == MusicView.PLAY_ICON) {  		// play
						if(player.getStatus() == BasicPlayer.PAUSED)
							player.resume();
						else
							player.play();
						player.setGain(0);	// mute
						icon = MusicView.PAUSE_ICON;
					}
					else {					// pause
						player.pause();
						icon = MusicView.PLAY_ICON;
					}
					((JButton) event.getSource()).setIcon(new ImageIcon(icon));
				} catch (BasicPlayerException exn) { exn.printStackTrace(); }
			}
		});
	}


	public void buttonStopListener(JButton button, JButton playButton, String roomsName) {
		final String name = roomsName;
		final JButton play = playButton;

		button.addActionListener(new ActionListener() {			
			public void actionPerformed(ActionEvent event) {
				BasicPlayer player = controllers.get(name);	// get the player to activate

				view.changePlayIcon(name);

				try {
					player.stop();
				} catch (BasicPlayerException exn) { exn.printStackTrace(); }
			}
		});
	}


	public ActionListener comboBoxActionListener(String roomsName) {
		final String name = roomsName;

		return new ActionListener() {				// create listener
			@Override
			public void actionPerformed(ActionEvent event) {
				JComboBox menu = (JComboBox) event.getSource();

				if(menu.getSelectedIndex() == menu.getItemCount() - 1) {	// last index -> add song					
					String song = addSong();

					if (song != null) {
						int lastIndex = menu.getItemCount() - 1;			
						Object lastObject = (String) menu.getItemAt(lastIndex);  // add new song to menu without changing order
						menu.removeItemAt(lastIndex);

						int songCounter = 0;
						while(!((String)menu.getItemAt(songCounter)).equals(song)) songCounter++;	// check if song not already added

						if(songCounter == menu.getItemCount())				// new song
							menu.addItem(song);

						menu.addItem(lastObject);

						menu.setSelectedItem(song);
					} else
						menu.setSelectedIndex(0);							// don't want to select last item
				}

				if(menu.getItemCount() > 1)
					changeSongListener(menu, name);								// change song
			}
		};
	}


	private String addSong(){								// add a song to the selection menu
		JFileChooser chooser = new JFileChooser();			
		FileNameExtensionFilter filter = new FileNameExtensionFilter("mp3", "mp3");	// choose only mp3 files
		chooser.setFileFilter(filter);
		int returnVal = chooser.showOpenDialog(null);		// open file chooser
		if(returnVal == JFileChooser.APPROVE_OPTION) {
			File selection = chooser.getSelectedFile();		// get selection 
			System.out.println(selection.getAbsolutePath());	    	
			try {
				new BasicPlayer().open(selection);			// check file format

				FileReader reader = new FileReader(selection);	// file copy

				FileWriter writer = new FileWriter(new File(MUSIC_DIR + selection.getName()));


				int c = reader.read();
				while(c != -1) {
					writer.write(c);
					c = reader.read();
				}
				writer.write(-1);							// EOF

				reader.close();
				writer.close();
			}
			catch (BasicPlayerException exn) { 
				JOptionPane.showMessageDialog(null, JOptionPane.WARNING_MESSAGE);
				return null; 
			}
			catch (Exception exn) { exn.printStackTrace(); }

			return selection.getName();						// name of the song to add
		}
		return null;
	}


	public void changeSongListener(JComboBox menu, String roomsName) {
		String filename = (String) menu.getSelectedItem();

		try {
			BasicPlayer player = controllers.get(roomsName);
			if(player.getStatus() != BasicPlayer.STOPPED) 
				player.stop();									// forces player to stop

			view.changePlayIcon(roomsName);

			player.open(new File(MUSIC_DIR+filename));			// open new file

		} catch (Exception exn) { System.err.println("The file " + filename + " can not be opened."); }
	}


	public void setRoomGain(String roomsName, float gain) {	// set gain at gain value in given room
		BasicPlayer player = controllers.get(roomsName);
		if (player != null){
			if (player.getStatus() == BasicPlayer.PLAYING)
				try {
					player.setGain(gain);
				} catch (BasicPlayerException exn) { exn.printStackTrace(); }
		}
	}

	public boolean isPlaying(String roomsName) {		// check if music is playing in the specified room
		try {
			return controllers.get(roomsName).getStatus() == BasicPlayer.PLAYING;
		} catch (NullPointerException exn) { return false; }
	}

	public void changeRoomName(String init,String change){
		removeRoom(init);
		addRoom(change);

	}
}
